package co.edu.udea.program1.BAL.Managers.Interfaces;

import java.io.FileNotFoundException;
import java.io.IOException;

import co.edu.udea.program1.Common.Exceptions.NullParamException;

public interface FileReaderInterface {
	public String ReadFile(String route) throws FileNotFoundException, NullParamException, IOException;
}
