package co.edu.udea.program1.BAL.Managers.Interfaces;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import co.edu.udea.program1.Common.Exceptions.IsNotProgramException;
import co.edu.udea.program1.Common.Exceptions.NullParamException;
import co.edu.udea.program1.Common.Models.Program;

public interface ProgramManagerInterface {

	public Program ProcessProgram(Program program) throws FileNotFoundException, NullParamException, IOException;
	public Program CreateProgram(String route) throws FileNotFoundException, NullParamException, IsNotProgramException;
	public boolean IsjavaProgram(File directory,boolean ret);

}
