package co.edu.udea.program1.Common.Tests.DAL;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.InvalidParameterException;

import org.junit.BeforeClass;
import org.junit.Test;


import co.edu.udea.program1.Common.Exceptions.NullParamException;
import co.edu.udea.program1.Common.Models.LinkedList;
import co.edu.udea.program1.DAL.Repository.FilesLoader;
import co.edu.udea.program1.DAL.Repository.Interfaces.FilesLoaderInterface;
import junit.framework.Assert;

public class FilesLoaderTest {

	private static FilesLoaderInterface _fileLoader;
	
	@BeforeClass
	public static void onceExecutedBeforeAll() {
		_fileLoader = new FilesLoader();
	}
	  
	
	@Test
	(expected= NullParamException.class)
	public void LoadFile_RouteIsNull() throws NullParamException, FileNotFoundException {
		_fileLoader.LoadFile(null);
	}
	
	
	@Test
	(expected= InvalidParameterException.class)
	public void LoadFile_RouteIsEmpty() throws NullParamException, FileNotFoundException {
		_fileLoader.LoadFile("");
	}
	
	@Test
	public void LoadFile_Success() throws NullParamException, IOException {
		String fileName = "test.txt";
		File file = new File(fileName);
		file.createNewFile();
		File loadFile = _fileLoader.LoadFile(fileName);
		file.delete();	
		assertNotNull(loadFile);
	}
	
	@Test
	(expected= FileNotFoundException.class)
	public void LoadFile_FileIsNotFound() throws NullParamException, FileNotFoundException {
		String fileName = "test50.txt";
		File file = _fileLoader.LoadFile(fileName);
	}
	////
	@Test
	public void LoadFile_IsDirectory() throws FileNotFoundException, NullParamException{
		String directory = "directoryTest";
		_createDirectoryTest(directory);
		File file = _fileLoader.LoadFile(directory);
		assertNotNull(file);
		_deleteDirectoryTest(directory);
	}

	private void _createDirectoryTest(String name){
		File directoryTest = new File(name);
		if(!directoryTest.exists()){
			directoryTest.mkdir();
		}
	}

	private void _deleteDirectoryTest(String name){
		File directoryTest = new File(name);
		if(directoryTest.exists()){
			directoryTest.delete();
		}
	}
}
