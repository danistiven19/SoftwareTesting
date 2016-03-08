package co.edu.udea.program1.BAL.Controllers;

import java.io.FileNotFoundException;
import java.io.IOException;

import co.edu.udea.program1.BAL.Controllers.Interfaces.SizeCalculatorInterface;
import co.edu.udea.program1.BAL.Managers.FileReader;
import co.edu.udea.program1.BAL.Managers.ProgramManager;
import co.edu.udea.program1.BAL.Managers.Interfaces.ProgramManagerInterface;
import co.edu.udea.program1.Common.Exceptions.IsNotProgramException;
import co.edu.udea.program1.Common.Exceptions.NullParamException;
import co.edu.udea.program1.Common.Models.LinkedList;
import co.edu.udea.program1.Common.Models.Program;
import co.edu.udea.program1.Common.Models.Response;
import co.edu.udea.program1.Common.Utils.Utils;

public class SizeCalculator implements SizeCalculatorInterface {

	private FileReader _fileManager;
	private ProgramManagerInterface _programManager;
	private Utils _utils;
	
	public SizeCalculator(){
		_fileManager = new FileReader();
		_utils = new Utils();
		_programManager = new ProgramManager();
	}

	@Override
	public Response ProcessProgram(String route) {
		Response response = new Response();
		try {
			Program p = _programManager.CreateProgram(route);
			p = _programManager.ProcessProgram(p);
			response.setData(p);
			response.setMessage("Success");
			response.setStatus(1);
			return response;
		} catch (FileNotFoundException e) {
			response.setData(-1);
			response.setStatus(0);
			response.setMessage("File not found");
			return response;
		} catch (NullParamException e) {
			response.setData(-1);
			response.setStatus(0);
			response.setMessage("The param must be valid");
			return response;
		} catch (IsNotProgramException e) {
			response.setData(-1);
			response.setStatus(0);
			response.setMessage("The folder must contain java files");
			return response;
		} catch (IOException e) {
			response.setData(-1);
			response.setStatus(0);
			response.setMessage("Problem open file");
			return response;
		}
		
	}
	
}
