package co.edu.udea.program1.BAL.Managers.Interfaces;

import co.edu.udea.program1.Common.Exceptions.NullParamException;
import co.edu.udea.program1.Common.Exceptions.ObjectNotFound;
import co.edu.udea.program1.Common.Models.LinkedList;
import co.edu.udea.program1.Common.Models.Node;

public interface LinkedListManagerInterface {
	public LinkedList CreateList();
	public double Length(LinkedList list) throws NullParamException;
	public LinkedList AddNode(LinkedList list, Node node) throws NullParamException;
	public LinkedList RemoveNode(LinkedList list, int nodeId) throws ObjectNotFound, NullParamException;
	public Node NextNode(LinkedList list, int nodeId) throws ObjectNotFound, NullParamException;
	public Node PriorNode(LinkedList list, int nodeId) throws ObjectNotFound, NullParamException;
	public LinkedList FillList(LinkedList list, Object[] array) throws NullParamException;
	public Boolean IsEmpty(LinkedList list) throws NullParamException;
	Node GetNode(LinkedList list, int id) throws ObjectNotFound, NullParamException;
	
}
