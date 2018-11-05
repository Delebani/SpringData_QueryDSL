package com.deleba.dsl.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.deleba.dsl.QUser;
import com.deleba.dsl.dao.UserDao;
import com.deleba.dsl.model.User;
import com.querydsl.core.types.Predicate;

public class AppTest {

	@Autowired
	private UserDao userDao;
	
	@Test
	public User findUserByUserName(final String userName){
		/**
		 * 该例是使用spring data QueryDSL实现
		 */
		QUser quser = QUser.user;
		Predicate predicate = quser.name.eq(userName);// 根据用户名，查询user表
		return userDao.findOne(predicate);
	}

}
