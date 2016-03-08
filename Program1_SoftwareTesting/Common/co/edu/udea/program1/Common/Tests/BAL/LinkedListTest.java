package co.edu.udea.program1.Common.Tests.BAL;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import co.edu.udea.program1.BAL.Managers.LinkedListManager;
import co.edu.udea.program1.Common.Exceptions.NullParamException;
import co.edu.udea.program1.Common.Exceptions.ObjectNotFound;
import co.edu.udea.program1.Common.Models.LinkedList;
import co.edu.udea.program1.Common.Models.Node;

public class LinkedListTest {

	private LinkedList _list;
	private static LinkedListManager _listManager;
	
	@BeforeClass
	public static void onceExecutedBeforeAll() {
		_listManager = new LinkedListManager();
	}
	  
	@Test
	public void CreateList_Success() {
		LinkedList list = _listManager.CreateList();
		assertNotNull(list.getHead());
	}
	
	@Test
	public void AddFirstNode_Success(){
		LinkedList list = _listManager.CreateList();
		Node node = new Node(1);
		try {
			list = _listManager.AddNode(list, node);
			assertNotNull(list.getHead().getNext());
		} catch (NullParamException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void AddFirstNode_NodeNull(){
		LinkedList list = _listManager.CreateList();
		try {
			list = _listManager.AddNode(list, null);
		} catch (NullParamException e) {
			assertEquals(0, 0);
		}
	}
	
	@Test
	public void AddFirstNode_NodeNull_HeaderNext(){
		LinkedList list = _listManager.CreateList();
		try {
			list = _listManager.AddNode(list, null);
		} catch (NullParamException e) {
			assertNull(list.getHead().getNext());
		}
	}
	
	@Test(expected = NullParamException.class)
	public void AddFirstNode_NodeNull_Exception() throws NullParamException{
		LinkedList list = _listManager.CreateList();
		list = _listManager.AddNode(list, null);
	}
	
	
	@Test
	public void AddNode_NoFirst_HeadInfoSuccess() throws NullParamException{
		LinkedList list = _listManager.CreateList();
		list = _listManager.AddNode(list, new Node(1));
		list = _listManager.AddNode(list, new Node(2));
		assertEquals(list.getHead().getPrevious().getId(), 2);
		assertEquals(list.getHead().getNext().getId(), 1);
	}
	
	@Test
	public void AddNode_NoFirst_LastNodeInfo_Success() throws NullParamException{
		LinkedList list = _listManager.CreateList();
		list = _listManager.AddNode(list, new Node(1));
		list = _listManager.AddNode(list, new Node(2));
		Node first = list.getHead().getNext();
		assertEquals(first.getNext().getId(), 2);
	}
	
	@Test
	public void AddNode_NoFirst_headerCounter_Success() throws NullParamException{
		LinkedList list = _listManager.CreateList();
		list = _listManager.AddNode(list, new Node(1));
		list = _listManager.AddNode(list, new Node(2));
		assertEquals(0, Double.compare(2, list.getHead().getData()));
	}
	
	
	@Test
	public void AddNode_NoFirst_NewLastNodeInfo_Success() throws NullParamException{
		LinkedList list = _listManager.CreateList();
		Node firstNode = new Node(1);
		list = _listManager.AddNode(list, firstNode);
		list = _listManager.AddNode(list, new Node(2));
		Node first = list.getHead().getNext();
		assertEquals(first.getNext().getPrevious().getId(), firstNode.getId());
		assertNull(first.getNext().getNext());
	}
	
	
	@Test
	public void NextNode_NextOfHeadWhenListIsEmpty() throws ObjectNotFound, NullParamException {
		LinkedList list = _listManager.CreateList();
		assertNull(_listManager.NextNode(list, list.getHead().getId()));
	}
	
	@Test
	public void NextNode_NextOfHeadWhenListIsNotEmpty() throws NullParamException, ObjectNotFound {
		LinkedList list = _listManager.CreateList();
		list = _listManager.AddNode(list, new Node(1));
		
		assertNotNull(_listManager.NextNode(list, list.getHead().getId()));
	}
	
	
	@Test
	public void NextNode_NextOfExistentNode() throws NullParamException, ObjectNotFound {
		LinkedList list = _listManager.CreateList();
		list = _listManager.AddNode(list, new Node(1));
		list = _listManager.AddNode(list, new Node(2));
		
		Node next = _listManager.NextNode(list, 1);
		assertEquals(next.getId(), 2);
	}

	@Test(expected= ObjectNotFound.class)
	public void NextNode_NextOfNonExistentNode() throws NullParamException, ObjectNotFound {
		LinkedList list = _listManager.CreateList();
		
		_listManager.NextNode(list, 1);
	}
	
	/*Prior*/
	
	@Test
	public void PriorNode_PriorOfHeadWhenListIsEmpty() throws ObjectNotFound, NullParamException {
		LinkedList list = _listManager.CreateList();
		assertNull(_listManager.PriorNode(list, list.getHead().getId()));
	}
	
	@Test
	public void PriorNode_priorOfHeadWhenListIsNotEmpty() throws NullParamException, ObjectNotFound {
		LinkedList list = _listManager.CreateList();
		list = _listManager.AddNode(list, new Node(1));
		
		assertNotNull(_listManager.PriorNode(list, 1));
	}
	
	
	@Test
	public void PriorNode_PriorOfExistentNode() throws NullParamException, ObjectNotFound {
		LinkedList list = _listManager.CreateList();
		list = _listManager.AddNode(list, new Node(1));
		list = _listManager.AddNode(list, new Node(2));
		
		Node next = _listManager.PriorNode(list, 2);
		assertEquals(next.getId(), 1);
	}
	
	@Test(expected= ObjectNotFound.class)
	public void PriorNode_PriorOfNonExistentNode() throws NullParamException, ObjectNotFound {
		LinkedList list = _listManager.CreateList();
		
		 _listManager.PriorNode(list, 2);
		
	}
	
	@Test
	public void GetNode_SearchHeader() throws ObjectNotFound, NullParamException{
		LinkedList list = _listManager.CreateList();
		Node head = _listManager.GetNode(list, list.getHead().getId());
		assertNotNull(head);
	}
	
	@Test(expected= NullParamException.class)
	public void GetNode_SearchInListNull() throws ObjectNotFound, NullParamException{
		LinkedList list = _listManager.CreateList();
		 _listManager.GetNode(null, list.getHead().getId());
		assertNotNull(null);
	}
	
	@Test
	public void GetNode_SearchExistentNode() throws ObjectNotFound, NullParamException{
		LinkedList list = _listManager.CreateList();
		_listManager.AddNode(list, new Node(1));
		_listManager.AddNode(list, new Node(2));
		Node result = _listManager.GetNode(list, 2);
		assertEquals(2, result.getId());
	}
	
	@Test(expected= ObjectNotFound.class)
	public void GetNode_SearchNonExistentNode() throws ObjectNotFound, NullParamException{
		LinkedList list = _listManager.CreateList();
		_listManager.GetNode(list, 1);
	}
	
	@Test(expected= ObjectNotFound.class)
	public void RemoveNode_NonExistentNode() throws ObjectNotFound, NullParamException{
		LinkedList list = _listManager.CreateList();
		_listManager.RemoveNode(list, 1);
	}
	
	@Test
	public void RemoveNode_ExistentNode() throws ObjectNotFound, NullParamException{
		LinkedList list = _listManager.CreateList();
		_listManager.AddNode(list, new Node(1));
		_listManager.AddNode(list, new Node(2));
		list = _listManager.RemoveNode(list, 2);
		Node first = list.getHead().getNext();
		assertEquals(1, list.getHead().getPrevious().getId());
		assertEquals(null, first.getNext());
	}
	
	@Test
	public void RemoveNode_UpdateHeadData() throws ObjectNotFound, NullParamException{
		LinkedList list = _listManager.CreateList();
		_listManager.AddNode(list, new Node(1));
		_listManager.AddNode(list, new Node(2));
		list = _listManager.RemoveNode(list, 2);
		assertEquals(0, Double.compare(1, list.getHead().getData()));
	}
	
	@Test(expected= NullParamException.class)
	public void RemoveNode_NullList() throws ObjectNotFound, NullParamException{
		_listManager.RemoveNode(null, 1);
	}
	
	@Test(expected= NullParamException.class)
	public void IsEmpty_NullList() throws ObjectNotFound, NullParamException{
		_listManager.IsEmpty(null);
	}
	
	@Test
	public void IsEmpty_EmptyList() throws NullParamException{
		LinkedList list = _listManager.CreateList();
		Boolean response = _listManager.IsEmpty(list);
		assertTrue(response);
	}
	
	@Test
	public void IsEmpty_NonEmptyList() throws NullParamException{
		LinkedList list = _listManager.CreateList();
		_listManager.AddNode(list, new Node(1));
		Boolean response = _listManager.IsEmpty(list);
		assertFalse(response);
	}
	
	@Test(expected= NullParamException.class)
	public void Length_WhenListIsNull() throws NullParamException{
		_listManager.Length(null);
	}
	
	@Test
	public void Length_WhenListIsEmpty() throws NullParamException{
		LinkedList list = _listManager.CreateList();
		double numberOfNodes= _listManager.Length(list);
		assertEquals(0, Double.compare(numberOfNodes,0));
	}

	@Test
	public void Length_WhenListIsNotEmpty() throws NullParamException{
		LinkedList list = _listManager.CreateList();
		_listManager.AddNode(list, new Node(1));
		_listManager.AddNode(list, new Node(2));
		double numberOfNodes= _listManager.Length(list);		
		assertEquals(0, Double.compare(2, numberOfNodes));
	}
	
	@Test(expected= NullParamException.class)
	public void FillList_ListsAreNull() throws NullParamException{
		_listManager.FillList(null, null);
	}
	
	@Test
	public void FillList_Success() throws NullParamException{
		LinkedList list = _listManager.CreateList();
		String[] array = {"1","2","3"};
		_listManager.FillList(list, array);
		assertEquals(0, Double.compare(3, _listManager.Length(list)));
	}
	
	
	@Test
	public void FillList_EmptyArray() throws NullParamException{
		LinkedList list = _listManager.CreateList();
		String[] array = {};
		_listManager.FillList(list, array);
		assertEquals(0, Double.compare(0, _listManager.Length(list)));
	}
	
	@Test(expected= NumberFormatException.class)
	public void FillList_InvalidNumber() throws NullParamException{
		LinkedList list = _listManager.CreateList();
		String[] array = {"1","2","a"};
		_listManager.FillList(list, array);
	}
	
	@Test
	public void FillList_NegativeNumber() throws NullParamException{
		LinkedList list = _listManager.CreateList();
		String[] array = {"-1","-2","-3"};
		_listManager.FillList(list, array);
		assertEquals(0, Double.compare(3, _listManager.Length(list)));
	}
	
}


