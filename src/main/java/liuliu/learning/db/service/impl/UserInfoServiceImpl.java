package liuliu.learning.db.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import liuliu.learning.db.dao.one.UserInfoMapperOne;
import liuliu.learning.db.dao.two.UserInfoMapperTwo;
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

	@Autowired
	private UserInfoMapperOne userInfoMapperOne;
	
	@Autowired
	private UserInfoMapperTwo userInfoMapperTwo;

	@Override
	public List<UserInfo> listAllOne() {
		return userInfoMapperOne.listAll();
	}
	
    @Override
    public int insertOne(UserInfo userInfo) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public List<UserInfo> listAllTwo() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int insertTwo(UserInfo userInfo) {
        // TODO Auto-generated method stub
        return 0;
    }
}
