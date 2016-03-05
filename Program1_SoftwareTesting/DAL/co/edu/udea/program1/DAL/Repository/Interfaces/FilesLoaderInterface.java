package co.edu.udea.program1.DAL.Repository.Interfaces;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import co.edu.udea.program1.Common.Exceptions.NullParamException;

public interface FilesLoaderInterface {
	public FileReader LoadFile(String route) throws NullParamException, FileNotFoundException;
}
