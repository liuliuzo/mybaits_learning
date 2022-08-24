package liuliu.learning.db.dao.one;

import java.util.List;

import liuliu.learning.db.entity.User;

/**
 * 
 * @description:
 * @author: liuliu
 * @email: 165348097@qq.com
 * @since JDK 1.8
 */
public interface UserMapperOne {
    List<User> listAll();

    int insert(User user);
}
