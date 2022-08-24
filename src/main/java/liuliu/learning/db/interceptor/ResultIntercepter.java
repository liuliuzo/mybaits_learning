package liuliu.learning.db.interceptor;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @description:
 * @author: liuliu
 * @email: 165348097@qq.com
 * @since JDK 1.8
 */
@Intercepts({ 
    @Signature(type = ResultSetHandler.class, method = "handleResultSets", args = { Statement.class })
})
public class ResultIntercepter implements Interceptor {

    private static final Logger log = LoggerFactory.getLogger(ResultIntercepter.class);

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Statement statement = (Statement) invocation.getArgs()[0];
        ResultSet resultSet = statement.getResultSet();
        while(resultSet.next()) {
            String name = resultSet.getString("name");
            log.info("name:{}", name);
            if(!name.equals("checksum")){
                //throw new RuntimeException(new MyException("checksum fail", name));
            }
        }
        log.info("resultSet:{}", resultSet.toString());
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return (target instanceof ResultSetHandler) ? Plugin.wrap(target, this) : target;
    }

    @Override
    public void setProperties(Properties properties) {
        log.info("properties:{}", properties.toString());
    }
}
