package co.edu.udea.program1.DAL.Repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.security.InvalidParameterException;

import co.edu.udea.program1.Common.Exceptions.NullParamException;
import co.edu.udea.program1.DAL.Repository.Interfaces.FilesLoaderInterface;

public class FilesLoader implements FilesLoaderInterface {

	@Override
	public File LoadFile(String route) throws NullParamException, FileNotFoundException {
		if(route == null){
			throw new NullParamException("The route can't be NULL");
		}
		if(route.isEmpty()){
			throw new InvalidParameterException("The param can't be Empty");
		}
		
		File file = new File(route);
		if(file.exists()){
			return file;
		}else{
			throw new FileNotFoundException();
		}
		
	}

}
