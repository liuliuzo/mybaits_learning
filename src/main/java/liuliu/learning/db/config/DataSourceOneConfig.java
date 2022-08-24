package liuliu.learning.db.config;

import java.util.List;

import javax.sql.DataSource;

import org.apache.ibatis.mapping.DatabaseIdProvider;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.ConfigurationCustomizer;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.alibaba.druid.pool.DruidDataSource;

/**
 * 
 * @author liuliu
 *
 */
@org.springframework.context.annotation.Configuration
@EnableConfigurationProperties(MybatisProperties.class)
@MapperScan(basePackages = "liuliu.learning.db.dao.one", sqlSessionTemplateRef  = "sqlSessionTemplateOne")
public class DataSourceOneConfig implements InitializingBean {

    private final MybatisProperties properties;

    private final Interceptor[] interceptors;

    private final ResourceLoader resourceLoader;

    private final DatabaseIdProvider databaseIdProvider;

    private final List<ConfigurationCustomizer> configurationCustomizers;

    public DataSourceOneConfig(MybatisProperties properties,
        ObjectProvider<Interceptor[]> interceptorsProvider,
        ResourceLoader resourceLoader,
        ObjectProvider<DatabaseIdProvider> databaseIdProvider,
        ObjectProvider<List<ConfigurationCustomizer>> configurationCustomizersProvider) {
      this.properties = properties;
      this.interceptors = interceptorsProvider.getIfAvailable();
      this.resourceLoader = resourceLoader;
      this.databaseIdProvider = databaseIdProvider.getIfAvailable();
      this.configurationCustomizers = configurationCustomizersProvider.getIfAvailable();
    }

    @Override
    public void afterPropertiesSet() {
      checkConfigFileExists();
    }

    private void checkConfigFileExists() {
      if (this.properties.isCheckConfigLocation() && StringUtils.hasText(this.properties.getConfigLocation())) {
        Resource resource = this.resourceLoader.getResource(this.properties.getConfigLocation());
        Assert.state(resource.exists(), "Cannot find config location: " + resource
            + " (please add config file or check your Mybatis configuration)");
      }
    }
    
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.druid.one")
    public DataSource dataSourceOne() {
        return DataSourceBuilder.create().type(DruidDataSource.class).build();
    }

    @Bean
    @ConditionalOnMissingBean
    public SqlSessionFactory sqlSessionFactoryOne(@Qualifier("dataSourceOne") DataSource dataSource) throws Exception {
      SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
      factory.setDataSource(dataSource);
      factory.setVfs(SpringBootVFS.class);
      if (StringUtils.hasText(this.properties.getConfigLocation())) {
        factory.setConfigLocation(this.resourceLoader.getResource(this.properties.getConfigLocation()));
      }
      applyConfiguration(factory);
      if (this.properties.getConfigurationProperties() != null) {
        factory.setConfigurationProperties(this.properties.getConfigurationProperties());
      }
      if (!ObjectUtils.isEmpty(this.interceptors)) {
        factory.setPlugins(this.interceptors);
      }
      if (this.databaseIdProvider != null) {
        factory.setDatabaseIdProvider(this.databaseIdProvider);
      }
      if (StringUtils.hasLength(this.properties.getTypeAliasesPackage())) {
        factory.setTypeAliasesPackage(this.properties.getTypeAliasesPackage());
      }
      if (this.properties.getTypeAliasesSuperType() != null) {
        factory.setTypeAliasesSuperType(this.properties.getTypeAliasesSuperType());
      }
      if (StringUtils.hasLength(this.properties.getTypeHandlersPackage())) {
        factory.setTypeHandlersPackage(this.properties.getTypeHandlersPackage());
      }
      if (!ObjectUtils.isEmpty(this.properties.resolveMapperLocations())) {
        factory.setMapperLocations(this.properties.resolveMapperLocations());
      }
      return factory.getObject();
    }

    private void applyConfiguration(SqlSessionFactoryBean factory) {
      Configuration configuration = this.properties.getConfiguration();
      if (configuration == null && !StringUtils.hasText(this.properties.getConfigLocation())) {
        configuration = new Configuration();
      }
      if (configuration != null && !CollectionUtils.isEmpty(this.configurationCustomizers)) {
        for (ConfigurationCustomizer customizer : this.configurationCustomizers) {
          customizer.customize(configuration);
        }
      }
      factory.setConfiguration(configuration);
    }

    @Bean
    public SqlSessionTemplate sqlSessionTemplateOne(@Qualifier("sqlSessionFactoryOne") SqlSessionFactory sqlSessionFactory) {
      ExecutorType executorType = this.properties.getExecutorType();
      if (executorType != null) {
        return new SqlSessionTemplate(sqlSessionFactory, executorType);
      } else {
        return new SqlSessionTemplate(sqlSessionFactory);
      }
    }
}
