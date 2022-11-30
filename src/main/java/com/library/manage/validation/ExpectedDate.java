package com.library.manage.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;



@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Constraint(validatedBy = ExpectedValidator.class)


public @interface ExpectedDate {

   public String message() default "custom exception -  Expected date should be greater then StartDate";



   public Class<?>[] groups() default {};



   public Class<? extends Payload>[] payload() default {};



}

