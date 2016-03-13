package co.edu.udea.program1.Common.Utils;

import java.security.InvalidParameterException;

import co.edu.udea.program1.Common.Exceptions.NullParamException;

public class Utils {

	public String[] SplitByComma(String text) throws NullParamException {
		int i =0;
		if(text == null){
			throw new NullParamException("The param can't be NULL");
		}
		if(text.isEmpty()){
			throw new InvalidParameterException("The param can't be empty");
		}
		String[] array  = text.split(",");
		for(String val : array){
			try{
				Double.parseDouble(val);
				i++;
			}catch(NumberFormatException e){
				throw new InvalidParameterException();
			}
		}
		
		return array;
		
	}

	
}
