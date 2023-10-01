package ru.top.java212.model;

import jakarta.persistence.metamodel.SingularAttribute;
import jakarta.persistence.metamodel.StaticMetamodel;
import java.time.LocalDate;
import javax.annotation.processing.Generated;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Period.class)
public abstract class Period_ {

	public static volatile SingularAttribute<Period, Integer> id;
	public static volatile SingularAttribute<Period, LocalDate> startDate;
	public static volatile SingularAttribute<Period, LocalDate> stopDate;

	public static final String ID = "id";
	public static final String START_DATE = "startDate";
	public static final String STOP_DATE = "stopDate";

}

