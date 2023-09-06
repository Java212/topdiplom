package ru.top.java212.model;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import javax.annotation.processing.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Person.class)
public abstract class Person_ {

	public static volatile SingularAttribute<Person, String> phoneNumber;
	public static volatile SingularAttribute<Person, String> name;
	public static volatile SingularAttribute<Person, Integer> id;
	public static volatile SingularAttribute<Person, User> user;

	public static final String PHONE_NUMBER = "phoneNumber";
	public static final String NAME = "name";
	public static final String ID = "id";
	public static final String USER = "user";

}

