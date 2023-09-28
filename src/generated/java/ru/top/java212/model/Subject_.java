package ru.top.java212.model;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import javax.annotation.processing.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Subject.class)
public abstract class Subject_ {

	public static volatile SingularAttribute<Subject, Teacher> teacher;
	public static volatile SingularAttribute<Subject, String> name;
	public static volatile SingularAttribute<Subject, Integer> id;

	public static final String TEACHER = "teacher";
	public static final String NAME = "name";
	public static final String ID = "id";

}

