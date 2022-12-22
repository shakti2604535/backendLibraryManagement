package com.library.manage.validation;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.library.manage.entity.BookTrack;
import com.library.manage.entity.BookTrackput;

public class ActualValidator implements ConstraintValidator<ActualReturnDate,BookTrackput> {

	@Override
	public boolean isValid(BookTrackput value, ConstraintValidatorContext context) {
		// TODO Auto-generated method stub
		if(value.getActualReturnDate() == null || value.getExpectedReturnDate() == null || value.getStartDate() == null)
		{
			return false;
		}
		   ZonedDateTime zdt = value.getStartDate().toInstant().atZone(ZoneId.systemDefault());
		   System.out.println(value);
//		   ZonedDateTime zdt = value.getExpectedReturnDate().toInstant().atZone(ZoneId.systemDefault());
		   ZonedDateTime zdt1 = value.getActualReturnDate().toInstant().atZone(ZoneId.systemDefault());
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
