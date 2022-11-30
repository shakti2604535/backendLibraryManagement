package com.library.manage.validation;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DobValidator implements ConstraintValidator<Dateofbirth, Date>{



	@Override
	public boolean isValid(Date value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		 if(value == null)
		 {
			 return false;
		 }
		
		   Date date = new Date();
		   ZonedDateTime zdt = date.toInstant().atZone(ZoneId.systemDefault());
		   System.out.println(value);
		   ZonedDateTime zdt1 = value.toInstant().atZone(ZoneId.systemDefault());
		   System.out.println(zdt1);

		   if(zdt.getYear() - zdt1.getYear() >= 10)
		   {
			   return true;
		   }
		return false;
	}

}
