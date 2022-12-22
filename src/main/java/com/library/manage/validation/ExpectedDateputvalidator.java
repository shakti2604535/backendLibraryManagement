package com.library.manage.validation;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.library.manage.entity.BookTrack;
import com.library.manage.entity.BookTrackput;

public class ExpectedDateputvalidator implements ConstraintValidator<ExpectedDatePut,BookTrackput> { 



@Override
public boolean isValid(BookTrackput value, ConstraintValidatorContext context) {
	// TODO Auto-generated method stub
	
	if(value.getStartDate() == null || value.getExpectedReturnDate() == null)
	{
		return false;
	}
	
	 System.out.println("i am inside expected");
	   ZonedDateTime zdt = value.getStartDate().toInstant().atZone(ZoneId.systemDefault());
	   System.out.println(value);
	   ZonedDateTime zdt1 = value.getExpectedReturnDate().toInstant().atZone(ZoneId.systemDefault());
	   System.out.println(zdt1);
	   if(zdt.getYear() - zdt1.getYear() > 0)
	   {	 System.out.println("i am inside expected");

	   context.buildConstraintViolationWithTemplate("test");
		   return false;
	   }
	   else {
	     if(zdt.getMonthValue() - zdt1.getMonthValue() > 0)
	          {
		   return false;
	       }
	     else {
	      if(zdt.getDayOfMonth() - zdt1.getDayOfMonth()>0 && zdt.getMonthValue() - zdt1.getMonthValue() == 0 )
	       {
		   return false;
	      }
	     }
	   }
	   
	return true;
}
}
