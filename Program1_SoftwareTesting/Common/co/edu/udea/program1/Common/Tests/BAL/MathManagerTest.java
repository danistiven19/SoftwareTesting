package co.edu.udea.program1.Common.Tests.BAL;

import static org.junit.Assert.*;

import java.security.InvalidParameterException;

import org.junit.BeforeClass;
import org.junit.Test;

import co.edu.udea.program1.BAL.Managers.LinkedListManager;
import co.edu.udea.program1.BAL.Managers.MathManager;
import co.edu.udea.program1.Common.Exceptions.NullParamException;
import co.edu.udea.program1.Common.Models.LinkedList;
import co.edu.udea.program1.Common.Models.Node;

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
	

}
