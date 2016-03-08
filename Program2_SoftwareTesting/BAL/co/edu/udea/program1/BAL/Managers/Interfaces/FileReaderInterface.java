package co.edu.udea.program1.BAL.Managers.Interfaces;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import co.edu.udea.program1.Common.Exceptions.NullParamException;

public interface FileReaderInterface {
	public int CalculateSize(File file) throws FileNotFoundException, NullParamException, IOException;
}
