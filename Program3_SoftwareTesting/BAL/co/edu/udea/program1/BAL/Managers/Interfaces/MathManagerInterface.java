package co.edu.udea.program1.BAL.Managers.Interfaces;

import co.edu.udea.program1.Common.Exceptions.NullParamException;
import co.edu.udea.program1.Common.Models.Correlation;
import co.edu.udea.program1.Common.Models.LinkedList;
import co.edu.udea.program1.Common.Models.Regression;

public interface MathManagerInterface {

	public double CalculateMean(LinkedList list) throws NullParamException;
	public double CalculateStandarDeviation(LinkedList list) throws NullParamException;
	public Regression CalculateRegression(LinkedList x, LinkedList y) throws NullParamException;
	public Regression ResolveRegression(Regression r);
	public Correlation CalculateCorrelation(LinkedList x, LinkedList y) throws NullParamException;
}
