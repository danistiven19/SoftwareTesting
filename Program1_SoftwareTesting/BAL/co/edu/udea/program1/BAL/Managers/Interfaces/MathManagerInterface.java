package co.edu.udea.program1.BAL.Managers.Interfaces;

import co.edu.udea.program1.Common.Exceptions.NullParamException;
import co.edu.udea.program1.Common.Models.LinkedList;

public interface MathManagerInterface {

	public double CalculateMean(LinkedList list) throws NullParamException;
	public double CalculateStandarDeviation(LinkedList list) throws NullParamException;
}
