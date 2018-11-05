package com.deleba.dsl;

import javax.annotation.Generated;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;

import com.deleba.dsl.model.User;
import com.querydsl.core.types.Path;

@Generated("com.querydsl.codegen.EntitySerializer")
public class QUser extends EntityPathBase<User> {

	private static final long serialVersionUID = 1153899872L;

	public static final QUser user = new QUser("user");

	public final StringPath address = createString("address");

	public final NumberPath<Integer> age = createNumber("age", Integer.class);

	public final NumberPath<Integer> id = createNumber("id", Integer.class);

	public final StringPath name = createString("name");

	public QUser(String variable) {
		super(User.class, forVariable(variable));
	}

	public QUser(Path<? extends User> path) {
		super(path.getType(), path.getMetadata());
	}

	public QUser(PathMetadata metadata) {
		super(User.class, metadata);
	}

}
