package co.edu.udea.program1.BAL.Managers;

import java.io.BufferedReader;
import java.io.File;
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
	public int CalculateSize(File file) throws NullParamException, IOException {
		if(file == null){
			throw new NullParamException("The param can't be NULL");
		}
		java.io.FileReader reader = new java.io.FileReader(file);
		BufferedReader bufferedReader = 
                new BufferedReader(reader);
		int result = 0, stack = 0;
		String line = null;
            while((line = bufferedReader.readLine()) != null) {
            	String arr[] = line.split(" ", 2);
            	String compare = arr[0];
            	if(compare.equals("")|| compare.equals(" ")){
            		String first = _findFirstWord(line);
            		if(first.isEmpty()){
            			compare =" ";
            		}else{
            			compare = first.split(" ",2)[0];
            		}
            	}
            	if(compare.contains("/*") || stack > 0){
            		if(searchEndComment(line)){
            			stack = 0;
            		}else{
            			stack = 1;
            		}
            	}else if(!compare.equals("import") && !compare.equals(" ")&& !compare.contains("//")){
            		result ++;	
            	}
            }  
		return result;
	}
	
	private boolean searchEndComment(String line){
		if(line.contains("*/")){
			return true;
		}
		return false;
	}
	
	
	private String _findFirstWord(String text){
		int count = text.length();
		for(int i = 0; i< count; i++){
			if(text.charAt(i) != ' '){
				return text.substring(i);
			}
		}
		return "";
	}

}
