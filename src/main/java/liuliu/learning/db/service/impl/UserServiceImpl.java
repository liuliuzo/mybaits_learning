package liuliu.learning.db.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import liuliu.learning.db.dao.one.UserMapperOne;
import liuliu.learning.db.dao.two.UserMapperTwo;
import liuliu.learning.db.entity.User;
import liuliu.learning.db.service.UserService;

/**
 * 
 * @description:
 * @author: liuliu
 * @email: 165348097@qq.com
 * @since JDK 1.8
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
	private UserMapperOne userMapperOne;

    @Autowired
    private UserMapperTwo userMapperTwo;

    @Override
    public List<User> listAllOne() {
        return userMapperOne.listAll();
    }

    @Override
    public int insertOne(User user) {
        return userMapperOne.insert(user);
    }

    @Override
    public List<User> listAllTwo() {
        return userMapperTwo.listAll();
    }

    @Override
    public int insertTwo(User user) {
        return userMapperTwo.insert(user);
    }

}
