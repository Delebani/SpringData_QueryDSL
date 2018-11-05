package com.deleba.dsl.dao;

import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.deleba.dsl.model.User;

public interface UserDao extends QueryDslPredicateExecutor<User>{

}
