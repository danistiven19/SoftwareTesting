package co.edu.udea.program1.BAL.Managers;

import java.security.InvalidParameterException;
import java.util.List;

import co.edu.udea.program1.BAL.Managers.Interfaces.MathManagerInterface;
import co.edu.udea.program1.Common.Exceptions.NullParamException;
import co.edu.udea.program1.Common.Models.Correlation;
import co.edu.udea.program1.Common.Models.LinkedList;
import co.edu.udea.program1.Common.Models.Node;
import co.edu.udea.program1.Common.Models.Regression;

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

	public Regression CalculateRegression(LinkedList x, LinkedList y) throws NullParamException {
		if(x == null || y == null){
			throw new NullParamException("The params can't be NULL");
		}
		if(_listManager.Length(x) != _listManager.Length(y)){
			throw new InvalidParameterException("The list must have the same lenght");
		}
		if(_listManager.Length(x) < 2){
			throw new InvalidParameterException("The lists must have at least 2 values");
		}
		Regression r = new Regression();
		
		//Calculating values for the regression
		LinkedList xyList = this._productMix(x, y);
		double xySum = this.SumList(xyList);
		double xAvg = this.CalculateMean(x);
		double yAvg = this.CalculateMean(y);
		LinkedList xQuadratic = this._quadraticList(x);
		double n = _listManager.Length(x);
		double sumXQuadratic = this.SumList(xQuadratic);
		
		//Calculating the params of the regression
		double b1 =(xySum-(n*xAvg*yAvg))/(sumXQuadratic-(n*(Math.pow(xAvg,2))));
		double b0 =yAvg-(b1*xAvg);
		r.setB1(b1);
		r.setB0(b0);
		
		return r;
	}
	
	private LinkedList _quadraticList(LinkedList a) throws NullParamException{
		LinkedList total = _listManager.CreateList();
		int i =1;
		Node aNode = a.getHead(); 
		
		while(aNode.getNext()!= null){
			aNode = aNode.getNext();
			Node node = new Node(i);
			node.setData(aNode.getData()* aNode.getData());
			total = _listManager.AddNode(total, node);
			i++;
		}
		return total;
	}
	
	private LinkedList _productMix(LinkedList a, LinkedList b) throws NullParamException{
		LinkedList total = _listManager.CreateList();
		int i =1;
		Node aNode = a.getHead(); 
		Node bNode = b.getHead();
		
		while(aNode.getNext()!= null){
			aNode = aNode.getNext();
			bNode = bNode.getNext();
			Node node = new Node(i);
			node.setData(aNode.getData()* bNode.getData());
			total = _listManager.AddNode(total, node);
			i++;
		}
		return total;
	}

	@Override
	public Regression ResolveRegression(Regression r) {
		r.setyValue(r.getB0()+(r.getB1()*r.getxValue()));
		return r;
	}

	public Correlation CalculateCorrelation(LinkedList x, LinkedList y) throws NullParamException {
		
		if(x == null || y == null){
			throw new NullParamException("The params can't be NULL");
		}
		if(_listManager.Length(x) != _listManager.Length(y)){
			throw new InvalidParameterException("The list must have the same lenght");
		}
		if(_listManager.IsEmpty(x) || _listManager.IsEmpty(x)){
			throw new InvalidParameterException("The params can't be EMPTY");
		}
		if(_listManager.Length(x) < 2){
			throw new InvalidParameterException("The lists must have at least 2 values");
		}
		Correlation c = new Correlation();
		
		LinkedList xyList = this._productMix(x, y);
		double xySum = this.SumList(xyList);
		LinkedList xQuadratic = this._quadraticList(x);
		LinkedList yQuadratic = this._quadraticList(y);
		double n = _listManager.Length(x);
		double sumXQuadratic = this.SumList(xQuadratic);
		double sumYQuadratic = this.SumList(yQuadratic);
		double xSum = this.SumList(x);
		double ySum = this.SumList(y);
		
		double correlation = ((n*xySum)-(xSum*ySum))/
				Math.sqrt(((n*sumXQuadratic)-(Math.pow(xSum,2)))*((n*sumYQuadratic)-(Math.pow(ySum,2)))); 
		c.setCorrelation(correlation);
		c.setCoefficientDetermination(Math.pow(c.getCorrelation(),2));
		
		return c;
	}

}
