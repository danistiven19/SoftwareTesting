package co.edu.udea.program1.BAL.Managers;

import java.security.InvalidParameterException;

import co.edu.udea.program1.BAL.Managers.Interfaces.MathManagerInterface;
import co.edu.udea.program1.Common.Exceptions.NullParamException;
import co.edu.udea.program1.Common.Models.LinkedList;
import co.edu.udea.program1.Common.Models.Node;

public class MathManager implements MathManagerInterface {

	private static LinkedListManager _listManager;
	
	public MathManager(){
		_listManager = new LinkedListManager();
	}
	
	@Override
	public double CalculateMean(LinkedList list) throws NullParamException {
		if(list == null){
			throw new NullParamException("The list param can't be NULL");	
		}
		Boolean empty =_listManager.IsEmpty(list);
		if(empty){
			throw new InvalidParameterException("The list can't be empty");
		}
		double summation = this.SumList(list);
		return summation/_listManager.Length(list);
	}

	@Override
	public double CalculateStandarDeviation(LinkedList list) throws NullParamException {
		if(list == null){
			throw new NullParamException("The list param can't be NULL");
		}
		if(_listManager.Length(list) < 2){
			throw new InvalidParameterException("Please provide at least two values");
		}
		double mean = this.CalculateMean(list);
		
		LinkedList auxCalc = _listManager.CreateList();
		int i =1;
		Node node = list.getHead().getNext();
		while(node != null){
			Node nodeCalc = new Node(i+1);
			double value = node.getData()-mean;
			value = Math.pow(value, 2);
			nodeCalc.setData(value);
			_listManager.AddNode(auxCalc, nodeCalc);
			node = node.getNext();
			i=i+1;
		}
		
		double summation = this.SumList(auxCalc);		
		return summation/(_listManager.Length(list)-1);
	}
	
	private double SumList(LinkedList list) throws NullParamException{
		double sum =0;
		Node node = list.getHead().getNext();
		while(node != null){
			sum = sum + node.getData();
			node = node.getNext();
		}
		return sum;
	}

}
