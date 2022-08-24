package liuliu.learning.db.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "liuliu.learning.db.dao.two", sqlSessionTemplateRef  = "sqlSessionTemplateTwo")
public class SqlSessionTemplateTwo {

}
