package liuliu.learning.db.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import liuliu.learning.db.dao.UserInfoMapper;
import liuliu.learning.db.entity.UserInfo;
import liuliu.learning.db.service.UserInfoService;

/**
 * 
 * @description:
 * @author: liuliu
 * @email: 165348097@qq.com
 * @since JDK 1.8
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

	@Resource
	private UserInfoMapper userInfoMapper;

	@Override
	public List<UserInfo> listAll() {
		return userInfoMapper.listAll();
	}

	@Override
	public int insert(UserInfo userInfo) {
		return userInfoMapper.insert(userInfo);
	}
}
