package liuliu.learning.db.dao.one;

import java.util.List;

import liuliu.learning.db.entity.UserInfo;

/**
 * 
 * @description:
 * @author: liuliu
 * @email: 165348097@qq.com
 * @since JDK 1.8
 */
public interface UserInfoMapperOne {
    
    List<UserInfo> listAll();

    int insert(UserInfo userInfo);
}
