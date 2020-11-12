package liuliu.learning.db.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import liuliu.learning.db.dao.UserMapper;
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

	@Resource
	private UserMapper userMapper;

	@Override
	public List<User> listAll() {
		return userMapper.listAll();
	}

	@Override
	public int insert(User user) {
		return userMapper.insert(user);
	}
}
