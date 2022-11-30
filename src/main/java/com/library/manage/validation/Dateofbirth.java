package com.library.manage.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = DobValidator.class)
public @interface Dateofbirth {



   public String message() default "custom exception -  Age should be 10 atleast";



   public Class<?>[] groups() default {};



   public Class<? extends Payload>[] payload() default {};



}