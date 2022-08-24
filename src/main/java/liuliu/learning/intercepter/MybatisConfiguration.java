package liuliu.learning.intercepter;

import java.util.Properties;

import org.springframework.context.annotation.Bean;

import liuliu.learning.db.interceptor.FirstIntercepter;

/**
 * 
 * @description:
 * @author: liuliu
 * @email: 165348097@qq.com
 * @since JDK 1.8
 */
//@Configuration
public class MybatisConfiguration {

    @Bean
    public FirstIntercepter firstIntercepter() {
    	FirstIntercepter interceptor = new FirstIntercepter();
        Properties properties = new Properties();
        interceptor.setProperties(properties);
        return interceptor;
    }

}

