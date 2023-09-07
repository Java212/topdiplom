package ru.top.java212.model;

import jakarta.persistence.metamodel.ListAttribute;
import jakarta.persistence.metamodel.SetAttribute;
import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import javax.annotation.processing.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(User.class)
public abstract class User_ {

	public static volatile SingularAttribute<User, String> password;
	public static volatile SetAttribute<User, Role> roles;
	public static volatile ListAttribute<User, Order> orders;
	public static volatile SingularAttribute<User, Integer> id;
	public static volatile SingularAttribute<User, String> login;
	public static volatile ListAttribute<User, Tool> tools;

	public static final String PASSWORD = "password";
	public static final String ROLES = "roles";
	public static final String ORDERS = "orders";
	public static final String ID = "id";
	public static final String LOGIN = "login";
	public static final String TOOLS = "tools";

}

