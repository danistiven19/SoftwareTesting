package co.edu.udea.program1.BAL.Controllers.Interfaces;

import co.edu.udea.program1.Common.Models.Response;

public interface CalculatorInterface {

	public int Sum(int a, int b);
	public Response StandardDeviation(String[] array);
	public Response Mean(String[] array);
	public Response ReadValues(String route);
}
