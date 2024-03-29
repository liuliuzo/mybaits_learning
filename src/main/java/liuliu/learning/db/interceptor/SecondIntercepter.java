package liuliu.learning.db.interceptor;

import java.util.List;
import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import liuliu.learning.db.entity.UserInfo;

/**
 * 
 * @description:
 * @author: liuliu
 * @email: 165348097@qq.com
 * @since JDK 1.8
 */
@Intercepts({
    @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})
})
public class SecondIntercepter implements Interceptor {

    private static final Logger log = LoggerFactory.getLogger(SecondIntercepter.class);

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object target = invocation.getTarget();
        log.info("target:{}", target);
        String methodName = invocation.getMethod().getName();
        log.info("methodName:{}", methodName);
        MappedStatement ms = (MappedStatement) invocation.getArgs()[0];
        String sqlid = ms.getId();
        log.info("sqlid:{}", sqlid);
        SqlCommandType commandType = ms.getSqlCommandType();
        log.info("MappedStatement:{},commandType:{}", ms, commandType); 
        Object parameter = invocation.getArgs()[1];

        if (parameter instanceof UserInfo){
            UserInfo entity = (UserInfo) parameter;
            if (commandType.equals(SqlCommandType.INSERT)){
                log.info("entity:{}", entity.toString());
                String hashpan = "pan";
                entity.setName("Change me");
                entity.setHash(hashpan);
            }
        }

        RowBounds rowBounds = (RowBounds) invocation.getArgs()[2];
        log.info("rowBounds:{}", rowBounds.toString());

        ResultHandler resultHandler = (ResultHandler) invocation.getArgs()[3];
        log.info("resultHandler:{}", resultHandler);

        Object result = invocation.proceed();
        if (result instanceof List<?>){
            List<?> list = (List<UserInfo>) result;
            list.forEach(entity-> log.info(entity.toString()));
        }

        log.info("result:{}", result.toString());
        return result;
    }

    @Override
    public Object plugin(Object target) {
        return (target instanceof Executor) ? Plugin.wrap(target, this) : target;
    }

    @Override
    public void setProperties(Properties properties) {
        log.info("properties:{}", properties.toString());
    }
}
