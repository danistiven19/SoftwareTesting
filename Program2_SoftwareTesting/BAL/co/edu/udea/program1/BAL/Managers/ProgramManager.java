package co.edu.udea.program1.BAL.Managers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

import co.edu.udea.program1.BAL.Managers.Interfaces.FileReaderInterface;
import co.edu.udea.program1.BAL.Managers.Interfaces.ProgramManagerInterface;
import co.edu.udea.program1.Common.Exceptions.IsNotProgramException;
import co.edu.udea.program1.Common.Exceptions.NullParamException;
import co.edu.udea.program1.Common.Models.Part;
import co.edu.udea.program1.Common.Models.Program;
import co.edu.udea.program1.DAL.Repository.FilesLoader;
import co.edu.udea.program1.DAL.Repository.Interfaces.FilesLoaderInterface;

public class ProgramManager implements ProgramManagerInterface {

	private FileReaderInterface _fileReader;
	private FilesLoaderInterface _fileLoader;
	
	public ProgramManager() {
		_fileReader = new FileReader();
		_fileLoader = new FilesLoader();
	}
	
	@Override
	public Program ProcessProgram(Program program) throws FileNotFoundException, NullParamException, IOException {
		Program p = _internalProcess(program, program.getDirectory(), program.getName());
		return p;
	}
	
	private Program _internalProcess(Program p, File directory, String name) throws FileNotFoundException, NullParamException, IOException{
		int flag = 0;
		Part part = new Part();
    	part.setName(name);
    	
		for (final File fileEntry : directory.listFiles()) {
	        if (fileEntry.isDirectory()) {
	        	_internalProcess(p, fileEntry, name+"\\"+fileEntry.getName());
	        } else {
	            int dot = fileEntry.getAbsolutePath().lastIndexOf(".");
	            String extension =  fileEntry.getAbsolutePath().substring(dot + 1);
	            if(extension.equals("java")){
	            	part.setNumberOfItems(part.getNumberOfItems() +1);
	            	int size = part.getPartSize();
	            	part.setPartSize(size + _fileReader.CalculateSize(fileEntry));
	            	flag = 1;
	            }
	        }
	    }
		if(flag == 1){
			List<Part> parts = p.getParts();
			parts.add(part);
			p.setParts(parts);
			p.setTotalSize(p.getTotalSize()+part.getPartSize());
		}
		return p;
	}

	@Override
	public Program CreateProgram(String route) throws FileNotFoundException, NullParamException, IsNotProgramException {
		File directory = _fileLoader.LoadFile(route);
		if(this.IsjavaProgram(directory, false)){
			Program program = new Program();
			program.setDirectory(directory);
			program.setName(directory.getName());
			program.setTotalSize(0);
			program.setParts(new ArrayList<Part>());
			return program;
		}else{
			throw new IsNotProgramException("The directory doesn't contains Java files");
		}
	}
	
	@Override
	public boolean IsjavaProgram(File directory, boolean ret){
		if(directory == null){
			throw new InvalidParameterException("The directory can't be NULL");
		}
		if(ret){
			return true;
		}
		 for (final File fileEntry : directory.listFiles()) {
		        if (fileEntry.isDirectory()) {
		        	if(fileEntry.getName().charAt(0) != '.'){
		        		return IsjavaProgram(fileEntry, ret);	
		        	}
		        } else {
		            int dot = fileEntry.getAbsolutePath().lastIndexOf(".");
		            String extension =  fileEntry.getAbsolutePath().substring(dot + 1);
		            if(extension.equals("java")){
		            	return true;
		            }
		        }
		    }
		 return false;
	}
	
}
