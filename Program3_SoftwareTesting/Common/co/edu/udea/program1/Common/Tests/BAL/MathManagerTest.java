package co.edu.udea.program1.Common.Tests.BAL;

import static org.junit.Assert.*;

import java.security.InvalidParameterException;

import org.junit.BeforeClass;
import org.junit.Test;

import co.edu.udea.program1.BAL.Managers.LinkedListManager;
import co.edu.udea.program1.BAL.Managers.MathManager;
import co.edu.udea.program1.Common.Exceptions.NullParamException;
import co.edu.udea.program1.Common.Models.Correlation;
import co.edu.udea.program1.Common.Models.LinkedList;
import co.edu.udea.program1.Common.Models.Node;
import co.edu.udea.program1.Common.Models.Regression;

public class MathManagerTest {

	private static MathManager _mathManager;
	private static LinkedListManager _listManager;
	
	@BeforeClass
	public static void onceExecutedBeforeAll() {
		_mathManager = new MathManager();
		_listManager = new LinkedListManager();
	}
	
	@Test(expected= NullParamException.class)
	public void CalculateSD_ListIsNull() throws NullParamException {
		_mathManager.CalculateStandarDeviation(null);
	}

	@Test(expected= InvalidParameterException.class)
	public void CalculateSD_ListIsEmpty() throws NullParamException{
		LinkedList list = _listManager.CreateList();
		_mathManager.CalculateStandarDeviation(list);
	}
	
	@Test
	public void CalculateSD_TwoNumbers_Success() throws NullParamException{
		LinkedList list = _listManager.CreateList();
		Node node1 = new Node(1);
		node1.setData(1);
		Node node2 = new Node(2);
		node2.setData(2);
		list = _listManager.AddNode(list, node1);
		list = _listManager.AddNode(list, node2);
		
		double std = _mathManager.CalculateStandarDeviation(list);
		assertEquals(0,Double.compare(0.5, std));
	}
	
	@Test
	public void CalculateSD_NegativeNumbers_Success() throws NullParamException{
		LinkedList list = _listManager.CreateList();
		Node node1 = new Node(1);
		node1.setData(-1);
		Node node2 = new Node(2);
		node2.setData(-2);
		list = _listManager.AddNode(list, node1);
		list = _listManager.AddNode(list, node2);
		
		double std = _mathManager.CalculateStandarDeviation(list);
		assertEquals(0,Double.compare(0.5, std));
	}
	
	@Test
	public void CalculateSD_NegativeRealNumbers_Success() throws NullParamException{
		LinkedList list = _listManager.CreateList();
		Node node1 = new Node(1);
		node1.setData(-1.5);
		Node node2 = new Node(2);
		node2.setData(-0.5);
		list = _listManager.AddNode(list, node1);
		list = _listManager.AddNode(list, node2);
		
		double std = _mathManager.CalculateStandarDeviation(list);
		assertEquals(0,Double.compare(0.5, std));
	}
	
	
	@Test(expected= NullParamException.class)
	public void CalculateMean_WhenListIsNull() throws NullParamException{
		_mathManager.CalculateMean(null);
	}
	
	@Test(expected= InvalidParameterException.class)
	public void CalculateMean_WhenListIsEmpty() throws NullParamException{
		LinkedList list = _listManager.CreateList();
		_mathManager.CalculateMean(list);
	}
	
	@Test
	public void CalculateMean_Success() throws NullParamException{
		LinkedList list = _listManager.CreateList();
		Node node1 = new Node(1);
		node1.setData(1);
		Node node2 = new Node(2);
		node1.setData(2);
		list = _listManager.AddNode(list, node1);
		list = _listManager.AddNode(list, node2);
		
		double mean = _mathManager.CalculateMean(list);
		assertEquals(1, Double.compare(1.5, mean));
	}
	
	@Test
	public void CalculateMean_OneElement() throws NullParamException{
		LinkedList list = _listManager.CreateList();
		Node node1 = new Node(1);
		node1.setData(1);

		list = _listManager.AddNode(list, node1);
		
		double mean = _mathManager.CalculateMean(list);
		assertEquals(0, Double.compare(1, mean));
	}
	
	@Test
	public void CalculateMean_RealNumbers() throws NullParamException{
		LinkedList list = _listManager.CreateList();
		Node node1 = new Node(1);
		node1.setData(1.5);
		Node node2 = new Node(2);
		node2.setData(2.5);
		list = _listManager.AddNode(list, node1);
		list = _listManager.AddNode(list, node2);
		
		double mean = _mathManager.CalculateMean(list);
		assertEquals(0, Double.compare(2, mean));
	}
@Test
public void CalculateMean_NegativeRealNumbers() throws NullParamException{
	LinkedList list = _listManager.CreateList();
	Node node1 = new Node(1);
	node1.setData(-1.5);
	Node node2 = new Node(2);
	node2.setData(2.5);
	list = _listManager.AddNode(list, node1);
	list = _listManager.AddNode(list, node2);
	
	double mean = _mathManager.CalculateMean(list);
	assertEquals(0, Double.compare(0.5, mean));
}

//Program 3

//Regresssion


@Test
(expected = NullParamException.class)
public void CalculateRegression_NullParams() throws NullParamException{
	_mathManager.CalculateRegression(null,null);
}


@Test
(expected = InvalidParameterException.class)
public void CalculateRegression_EmptyLists() throws NullParamException{
	LinkedList x = _listManager.CreateList();
	LinkedList y = _listManager.CreateList();
	_mathManager.CalculateRegression(x,y);
}

@Test
(expected = InvalidParameterException.class)
public void CalculateRegression_DifferentLengths() throws NullParamException{
	LinkedList x = _listManager.CreateList();
	LinkedList y = _listManager.CreateList();
	String[] xArray = {"1","2"};
	String[] yArray = {"3"};
	x = _listManager.FillList(x, xArray);
	y = _listManager.FillList(y, yArray);
	
	Regression r = _mathManager.CalculateRegression(x,y);
}

@Test
(expected = InvalidParameterException.class)
public void CalculateRegression_LessThan2Values() throws NullParamException{
	LinkedList x = _listManager.CreateList();
	LinkedList y = _listManager.CreateList();
	String[] xArray = {"1"};
	String[] yArray = {"2"};
	x = _listManager.FillList(x, xArray);
	y = _listManager.FillList(y, yArray);
	
	_mathManager.CalculateRegression(x,y);

}
	
@Test
public void CalculateRegression_NegativeNumbers() throws NullParamException{
	LinkedList x = _listManager.CreateList();
	LinkedList y = _listManager.CreateList();
	String[] xArray = {"-1","-2"};
	String[] yArray = {"-3","-4"};
	x = _listManager.FillList(x, xArray);
	y = _listManager.FillList(y, yArray);
	
	Regression r = _mathManager.CalculateRegression(x,y);
	assertEquals(0, Double.compare(-2,r.getB0()));
	assertEquals(0, Double.compare(1,r.getB1()));
}

@Test
public void CalculateRegression_Success() throws NullParamException{
	LinkedList x = _listManager.CreateList();
	LinkedList y = _listManager.CreateList();
	String[] xArray = {"1","2"};
	String[] yArray = {"3","4"};
	x = _listManager.FillList(x, xArray);
	y = _listManager.FillList(y, yArray);
	
	Regression r = _mathManager.CalculateRegression(x,y);
	assertEquals(0, Double.compare(2,r.getB0()));
	assertEquals(0, Double.compare(1,r.getB1()));
}
	
//Resolve regression
@Test
public void ResolveRegression_Success(){
	Regression r = new Regression();
	r.setB0(5);
	r.setB1(2);
	r.setxValue(50);
	_mathManager.ResolveRegression(r);
	assertEquals(0, Double.compare(105, r.getyValue()));
}

@Test
public void ResolveRegression_NegativeNumbers(){
	Regression r = new Regression();
	r.setB0(-5);
	r.setB1(2);
	r.setxValue(50);
	_mathManager.ResolveRegression(r);
	assertEquals(0, Double.compare(95, r.getyValue()));
}

@Test
public void ResolveRegression_WithoutX(){
	Regression r = new Regression();
	r.setB0(-5);
	r.setB1(2);
	_mathManager.ResolveRegression(r);
	assertEquals(0, Double.compare(-5, r.getyValue()));
}

//Correlation
@Test
(expected = NullParamException.class)
public void CalculateCorrelation_NullParams() throws NullParamException{
	_mathManager.CalculateCorrelation(null,null);
}


@Test
(expected = InvalidParameterException.class)
public void CalculateCorrelation_EmptyLists() throws NullParamException{
	LinkedList x = _listManager.CreateList();
	LinkedList y = _listManager.CreateList();
	_mathManager.CalculateCorrelation(x,y);
}

@Test
(expected = InvalidParameterException.class)
public void CalculateCorrelation_DifferentLengths() throws NullParamException{
	LinkedList x = _listManager.CreateList();
	LinkedList y = _listManager.CreateList();
	String[] xArray = {"1","2"};
	String[] yArray = {"3"};
	x = _listManager.FillList(x, xArray);
	y = _listManager.FillList(y, yArray);
	
	 _mathManager.CalculateCorrelation(x, y);
}
	
@Test
public void CalculateCorrelation_NegativeNumbers() throws NullParamException{
	LinkedList x = _listManager.CreateList();
	LinkedList y = _listManager.CreateList();
	String[] xArray = {"-1","-2"};
	String[] yArray = {"-3","-4"};
	x = _listManager.FillList(x, xArray);
	y = _listManager.FillList(y, yArray);
	
	Correlation c = _mathManager.CalculateCorrelation(x,y);
	assertEquals(0, Double.compare(1,c.getCorrelation()));
	assertEquals(0, Double.compare(1,c.getCoefficientDetermination()));
}

@Test
public void CalculateCorrelation_Success() throws NullParamException{
	LinkedList x = _listManager.CreateList();
	LinkedList y = _listManager.CreateList();
	String[] xArray = {"1","2"};
	String[] yArray = {"3","4"};
	x = _listManager.FillList(x, xArray);
	y = _listManager.FillList(y, yArray);
	
	Correlation c = _mathManager.CalculateCorrelation(x,y);
	assertEquals(0, Double.compare(1,c.getCorrelation()));
	assertEquals(0, Double.compare(1,c.getCoefficientDetermination()));
}
@Test
(expected = InvalidParameterException.class)
public void Calculatecorrelation_LessThan2Values() throws NullParamException{
	LinkedList x = _listManager.CreateList();
	LinkedList y = _listManager.CreateList();
	String[] xArray = {"1"};
	String[] yArray = {"2"};
	x = _listManager.FillList(x, xArray);
	y = _listManager.FillList(y, yArray);
	
	_mathManager.CalculateCorrelation(x, y);

}

}
