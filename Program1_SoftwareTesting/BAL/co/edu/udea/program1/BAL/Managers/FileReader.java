package co.edu.udea.program1.BAL.Managers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

import co.edu.udea.program1.BAL.Managers.Interfaces.FileReaderInterface;
import co.edu.udea.program1.Common.Exceptions.NullParamException;
import co.edu.udea.program1.DAL.Repository.FilesLoader;
import co.edu.udea.program1.DAL.Repository.Interfaces.FilesLoaderInterface;

public class FileReader implements FileReaderInterface {
	
	private static FilesLoaderInterface _fileLoader;
	
	public FileReader(){
		_fileLoader = new FilesLoader();
	}

	@Override
	public String ReadFile(String route) throws NullParamException, IOException {
		java.io.FileReader reader = _fileLoader.LoadFile(route);
		BufferedReader bufferedReader = 
                new BufferedReader(reader);
		String result = "", line = null;
            while((line = bufferedReader.readLine()) != null) {
                result = result +line;
            }  
		return result;
	}

}
