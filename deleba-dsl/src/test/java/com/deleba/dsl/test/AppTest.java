package com.deleba.dsl.test;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.deleba.dsl.QUser;
import com.deleba.dsl.dao.UserDao;
import com.deleba.dsl.model.User;
import com.querydsl.core.types.Predicate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AppTest {

	@Autowired
	private UserDao userDao;

	@Test
	public User findUserByUserName(final String userName) {
		/**
		 * 该例是使用spring data QueryDSL实现
		 */
		QUser quser = QUser.user;
		Predicate predicate = quser.name.eq(userName);// 根据用户名，查询user表
		return userDao.findOne(predicate);
	}

	/**
	 * BeanWrapperImpl 使用
	 * 
	 */
	@Test
	public void BeanWrapperImpl() {
		BeanWrapperImpl bean = new BeanWrapperImpl(User.class);
		// 获取包装对象的实例
		User user = (User) bean.getWrappedInstance();
		System.err.println(user);
		// 获取包装对象的类型
		Class<?> wrappedClass = bean.getWrappedClass();
		System.err.println(wrappedClass);
		System.err.println(Object.class.isAssignableFrom(wrappedClass));
		// 获取包装对象所有属性的PropertyDescriptor.就是这个属性的上下文。
		PropertyDescriptor[] propertyDescriptors = bean.getPropertyDescriptors();
		System.err.println(propertyDescriptors);
		// 获取包装对象指定属性的上下文。
		PropertyDescriptor propertyDescriptor = bean.getPropertyDescriptor("name");
		System.err.println(propertyDescriptor);

	}

	/**
	 * 反射
	 */
	@Test
	public void cfor() {
		try {
			User user = new User();
			Field declaredField = user.getClass().getDeclaredField("name");
			declaredField.setAccessible(true);//AccessibleTest类中的成员变量为private,故必须进行此操zuo
			declaredField.set(user, "k");
			System.out.println(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 内省
	 * @param args
	 */
	public static void main(String[] args){
	
		try {
			User user = new User();
			PropertyDescriptor p = new PropertyDescriptor("name", User.class);
			Method writeMethod = p.getWriteMethod();
			writeMethod.invoke(user, "dd");
			System.out.println(user);
			
			BeanInfo beanInfo = Introspector.getBeanInfo(User.class);
			PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
			for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
				if(propertyDescriptor.getDisplayName().equals("age")){
					Method w = propertyDescriptor.getWriteMethod();
					w.invoke(user, 123);
				}
			}
			System.out.println(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
