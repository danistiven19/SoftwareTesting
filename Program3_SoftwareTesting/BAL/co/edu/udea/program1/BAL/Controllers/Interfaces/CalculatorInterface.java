package co.edu.udea.program1.BAL.Controllers.Interfaces;

import co.edu.udea.program1.Common.Models.Regression;
import co.edu.udea.program1.Common.Models.Response;

public interface CalculatorInterface {

	public Response CalculateRegressionAndCorrelation(String[] x, String[] y);
	public Response ResolveRegression(Regression r, String xValue);
}
