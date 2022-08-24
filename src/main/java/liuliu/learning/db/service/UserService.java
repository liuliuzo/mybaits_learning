package liuliu.learning.db.service;

import java.util.List;

import liuliu.learning.db.entity.User;

/**
 * 
 * @description:
 * @author: liuliu
 * @email: 165348097@qq.com
 * @since JDK 1.8
 */
public interface UserService {
    List<User> listAllOne();

    int insertOne(User user);

    List<User> listAllTwo();

    int insertTwo(User user);
}
