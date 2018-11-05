package com.deleba.dsl.demo;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.deleba.dsl.QUser;
import com.deleba.dsl.dao.UserDao;
import com.deleba.dsl.model.User;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAQueryFactory;

/**
 * 描述：QueryDSL JPA
 * 
 * @author chhliu
 */
@Component
@Transactional
public class UserRepositoryManagerDsl {
	@Autowired
	private UserDao repository;

	@Autowired
	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private JPAQueryFactory queryFactory;

	@PostConstruct
	public void init() {
		queryFactory = new JPAQueryFactory(entityManager);
	}

	public User findUserByUserName(final String userName) {
		/**
		 * 该例是使用spring data QueryDSL实现
		 */
		QUser quser = QUser.user;
		Predicate predicate = quser.name.eq(userName);
		return repository.findOne(predicate);
	}

	/**
	 * attention: Details：查询user表中的所有记录
	 */
	public List<User> findAll() {
		QUser quser = QUser.user;
		return queryFactory.selectFrom(quser).fetch();
	}

	/**
	 * Details：单条件查询
	 */
	public User findOneByUserName(final String userName) {
		QUser quser = QUser.user;
		return queryFactory.selectFrom(quser).where(quser.name.eq(userName)).fetchOne();
	}

	/**
	 * Details：单表多条件查询
	 */
	public User findOneByUserNameAndAddress(final String userName, final String address) {
		QUser quser = QUser.user;
		return queryFactory.select(quser).from(quser) // 上面两句代码等价与selectFrom
				.where(quser.name.eq(userName).and(quser.address.eq(address)))// 这句代码等同于where(quser.name.eq(userName),
																				// quser.address.eq(address))
				.fetchOne();
	}

	/**
	 * Details：使用join查询
	 */
	public List<User> findUsersByJoin() {
		QUser quser = QUser.user;
		QUser userName = new QUser("name");
		return queryFactory.selectFrom(quser).innerJoin(quser).on(quser.id.intValue().eq(userName.id.intValue()))
				.fetch();
	}

	/**
	 * Details：将查询结果排序
	 */
	public List<User> findUserAndOrder() {
		QUser quser = QUser.user;
		return queryFactory.selectFrom(quser).orderBy(quser.id.desc()).fetch();
	}

	/**
	 * Details：Group By使用
	 */
	public List<String> findUserByGroup() {
		QUser quser = QUser.user;
		return queryFactory.select(quser.name).from(quser).groupBy(quser.name).fetch();
	}

	/**
	 * Details：删除用户
	 */
	public long deleteUser(String userName) {
		QUser quser = QUser.user;
		return queryFactory.delete(quser).where(quser.name.eq(userName)).execute();
	}

	/**
	 * Details：更新记录
	 */
	public long updateUser(final User u, final String userName) {
		QUser quser = QUser.user;
		return queryFactory.update(quser).where(quser.name.eq(userName)).set(quser.name, u.getName())
				.set(quser.age, u.getAge()).set(quser.address, u.getAddress()).execute();
	}

	/**
	 * Details：使用原生Query
	 */
	public User findOneUserByOriginalSql(final String userName) {
		QUser quser = QUser.user;
		Query query = queryFactory.selectFrom(quser).where(quser.name.eq(userName)).createQuery();
		return (User) query.getSingleResult();
	}

	/**
	 * Details：分页查询单表
	 */
	public Page<User> findAllAndPager(final int offset, final int pageSize) {
		Predicate predicate = QUser.user.id.lt(10);
		Sort sort = new Sort(new Sort.Order(Sort.Direction.DESC, "id"));
		PageRequest pr = new PageRequest(offset, pageSize, sort);
		return repository.findAll(predicate, pr);
	}
}
