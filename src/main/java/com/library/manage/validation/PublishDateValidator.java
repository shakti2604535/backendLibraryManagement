package com.library.manage.validation;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PublishDateValidator implements ConstraintValidator<PublishDate,Date> {

	@Override
	public boolean isValid(Date value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		 if(value == null)
		 {
			 return false;
		 }
//		System.out.println(value.getActualReturnDate() +"--------->"+value.getExpectedReturnDate());
		   Date date = new Date();
		   ZonedDateTime zdt = date.toInstant().atZone(ZoneId.systemDefault());
		   System.out.println(value);
		   ZonedDateTime zdt1 = value.toInstant().atZone(ZoneId.systemDefault());
		   System.out.println(zdt1);

//		   if(zdt.getDayOfMonth() == zdt1.getDayOfMonth() && zdt.getMonth() == zdt1.getMonth() && zdt1.getYear() == zdt.getYear())
//		   {
//			   return true;
//		   }
		   if(zdt1.isBefore(zdt))
		   {
			   return true;
		   }
		return false;
	
	}

}
