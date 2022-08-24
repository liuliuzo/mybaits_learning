package liuliu.learning.db.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "liuliu.learning.db.dao.one", sqlSessionTemplateRef  = "SqlSessionTemplateOne")
public class SqlSessionTemplateOne {

}
