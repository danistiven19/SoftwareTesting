package co.edu.udea.program1.BAL.Managers;

import java.util.List;

import co.edu.udea.program1.BAL.Managers.Interfaces.LinkedListManagerInterface;
import co.edu.udea.program1.Common.Exceptions.NullParamException;
import co.edu.udea.program1.Common.Exceptions.ObjectNotFound;
import co.edu.udea.program1.Common.Models.LinkedList;
import co.edu.udea.program1.Common.Models.Node;

public class LinkedListManager implements LinkedListManagerInterface {

	@Override
	public LinkedList CreateList() {
		LinkedList list = new LinkedList();
		list.setHead(new Node(0));
		return list;
	}

	@Override
	public Node GetNode(LinkedList list, int id) throws ObjectNotFound, NullParamException {
		if( list == null){
			throw new NullParamException("The params can't be NULL");
		}
		return this.getNode(list.getHead(), id);
	}

	@Override
	public double Length(LinkedList list) throws NullParamException {
	 if(list == null){
		throw new NullParamException("The list can't be NULL");
	}
		return list.getHead().getData();
	}

	@Override
	public LinkedList AddNode(LinkedList list, Node node) throws NullParamException {
		if(node == null || list == null){
			throw new NullParamException("The params can't be NULL");
		}
		Node head = list.getHead();
		if(head.getNext() == null){
			head.setNext(node);
			head.setPrevious(node);
			node.setPrevious(head);
		}else{
			Node lastNode = head.getPrevious();
			lastNode.setNext(node);
			node.setPrevious(lastNode);
			head.setPrevious(node);			
		}
		head.setData(head.getData()+1);
			
			list.setHead(head);
			return list;		
	}

	@Override
	public LinkedList RemoveNode(LinkedList list, int nodeId) throws ObjectNotFound, NullParamException  {
		if(list == null){
			throw new NullParamException("The params can't be NULL");
		}
		Node node = this.getNode(list.getHead(), nodeId);
		Node newLast = node.getPrevious();
		newLast.setNext(null);
		Node head = list.getHead();
		head.setPrevious(newLast);
		head.setData(head.getData() - 1);
		list.setHead(head);
		return list;		
	}

	@Override
	public Node NextNode(LinkedList list, int nodeId) throws ObjectNotFound, NullParamException {
		if(list == null){
			throw new NullParamException("The params can't be NULL");
		}
		Node findNode = this.getNode(list.getHead(), nodeId);
		return findNode.getNext();
		
	}

	@Override
	public Node PriorNode(LinkedList list, int nodeId) throws ObjectNotFound, NullParamException {
		if(list == null){
			throw new NullParamException("The params can't be NULL");
		}
		Node findNode = this.getNode(list.getHead(), nodeId);
		return findNode.getPrevious();
	}


	@Override
	public LinkedList FillList(LinkedList list, Object[] array) throws NullParamException {
		if(list == null || array == null){
			throw new NullParamException("The params can't be NULL");
		}
		int limit = array.length;
		for(int i=0; i<limit; i++){
			Node node = new Node(i+1);
			node.setData(Double.parseDouble(array[i].toString()));			
			this.AddNode(list, node);
		}
		return list;
	}

	@Override
	public Boolean IsEmpty(LinkedList list) throws NullParamException {
		if(list == null){
			throw new NullParamException("The list can't be NULL");
		}
		if(list.getHead().getNext() == null){
			return true;
		}
		return false;
	}
	
	private Node getNode(Node head, int nodeId) throws ObjectNotFound{
		Node node = head;
		while(node != null){
			if(node.getId() == nodeId){
				return node;
			}
			node = node.getNext();
		}
		throw new ObjectNotFound("The node id "+nodeId+" isn't found");
	}

}
