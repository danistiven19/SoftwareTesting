package co.edu.udea.program1.BAL.Controllers;

import java.io.IOException;
import java.security.InvalidParameterException;

import co.edu.udea.program1.BAL.Controllers.Interfaces.CalculatorInterface;
import co.edu.udea.program1.BAL.Managers.LinkedListManager;
import co.edu.udea.program1.BAL.Managers.MathManager;
import co.edu.udea.program1.Common.Exceptions.NullParamException;
import co.edu.udea.program1.Common.Models.LinkedList;
import co.edu.udea.program1.Common.Models.MathResult;
import co.edu.udea.program1.Common.Models.Regression;
import co.edu.udea.program1.Common.Models.Response;
import co.edu.udea.program1.Common.Utils.Utils;

public class Calculator implements CalculatorInterface {

	private LinkedListManager _listManager;
	private MathManager _mathManager;
	private Utils _utils;
	
	public Calculator(){
		_listManager = new LinkedListManager();
		_mathManager = new MathManager();
		_utils = new Utils();
	}



	private LinkedList arrayToList(String[] array) throws NullParamException{
		LinkedList list = _listManager.CreateList();
		try {
			return(_listManager.FillList(list, array));
		} catch (NullParamException e) {
			throw e;
		}
	}


	@Override
	public Response CalculateRegressionAndCorrelation(String[] x, String[] y) {
		Response response = new Response();
		MathResult result = new MathResult();
		
		try {
			result.setxArray(x);;
			result.setxArray(y);
			LinkedList xList = this.arrayToList(x);
			LinkedList yList = this.arrayToList(y);
			result.setResultRegression(_mathManager.CalculateRegression(xList, yList));
			result.setResultCorrelation(_mathManager.CalculateCorrelation(xList, yList));
			response.setData(result);
			response.setMessage("Success");
			response.setStatus(1);
		} catch (NullParamException e) {
			response.setData(-1);
			response.setStatus(0);
			response.setMessage("The param must be valid");
			return response;
		} catch (NumberFormatException e) {
			response.setData(-1);
			response.setStatus(0);
			response.setMessage("The param must be valid");
			return response;
		} catch (InvalidParameterException e) {
			response.setData(-1);
			response.setStatus(0);
			response.setMessage("X and Y Values must have the same length and to contain at least two values");
			return response;
		} 
		 
		return response;
	}



	@Override
	public Response ResolveRegression(Regression r, String xValue) {
		Response response = new Response();
	try{
			r.setxValue(Double.parseDouble(xValue));
			r = _mathManager.ResolveRegression(r);
			response.setData(r);
			response.setMessage("Success");
			response.setStatus(1);
	}catch(NumberFormatException e){
		response.setData(-1);
		response.setStatus(0);
		response.setMessage("The param must be valid");
		return response;
	}
		 
		return response;
	}
	
}
