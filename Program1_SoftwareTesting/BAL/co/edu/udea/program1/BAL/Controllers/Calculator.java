package co.edu.udea.program1.BAL.Controllers;

import java.io.IOException;

import co.edu.udea.program1.BAL.Controllers.Interfaces.CalculatorInterface;
import co.edu.udea.program1.BAL.Managers.FileReader;
import co.edu.udea.program1.BAL.Managers.LinkedListManager;
import co.edu.udea.program1.BAL.Managers.MathManager;
import co.edu.udea.program1.Common.Exceptions.NullParamException;
import co.edu.udea.program1.Common.Models.LinkedList;
import co.edu.udea.program1.Common.Models.Response;
import co.edu.udea.program1.Common.Utils.Utils;

public class Calculator implements CalculatorInterface {

	private LinkedListManager _listManager;
	private MathManager _mathManager;
	private FileReader _fileManager;
	private Utils _utils;
	
	public Calculator(){
		_listManager = new LinkedListManager();
		_mathManager = new MathManager();
		_fileManager = new FileReader();
		_utils = new Utils();
	}
	@Override
	public int Sum(int a, int b) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	
	
	public Response StandardDeviation(String[] array) {
		Response response = new Response();
		
		try {
			LinkedList list = this.arrayToList(array);
			double std = _mathManager.CalculateStandarDeviation(list);
			response.setData(std);
			response.setMessage("Success");
			response.setStatus(1);
		} catch (NullParamException e) {
			e.printStackTrace();
			response.setData(-1);
			response.setStatus(0);
			response.setMessage("The param must be valid");
			return response;
		} 
		 
		return response;
	}

	private LinkedList arrayToList(String[] array) throws NullParamException{
		LinkedList list = _listManager.CreateList();
		try {
			return(_listManager.FillList(list, array));
		} catch (NullParamException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	@Override
	public Response Mean(String[] array) {
Response response = new Response();
		
		try {
			LinkedList list = this.arrayToList(array);
			double mean = _mathManager.CalculateMean(list);
			response.setData(mean);
			response.setMessage("Success");
			response.setStatus(1);
		} catch (NullParamException e) {
			e.printStackTrace();
			response.setData(-1);
			response.setStatus(0);
			response.setMessage("The param must be valid");
			return response;
		} 
		 
		return response;
	}
	@Override
	public Response ReadValues(String route) {
		Response response = new Response();
		
	try {
		String text = _fileManager.ReadFile(route);
		response.setData(text);
		response.setMessage("Success");
		response.setStatus(1);
	} catch (NullParamException e) {
		response.setData(null);
		response.setStatus(0);
		response.setMessage("Invalid parameter has been sended");
	} catch (IOException e) {
		response.setData(null);
		response.setStatus(0);
		response.setMessage("Problem opening the file");
	}
		return response;
	}
}
