package co.edu.udea.program1.Common.Tests.BAL;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.InvalidParameterException;

import org.junit.BeforeClass;
import org.junit.Test;

import co.edu.udea.program1.BAL.Managers.FileReader;
import co.edu.udea.program1.BAL.Managers.Interfaces.FileReaderInterface;
import co.edu.udea.program1.Common.Exceptions.NullParamException;
import co.edu.udea.program1.DAL.Repository.FilesLoader;
import co.edu.udea.program1.DAL.Repository.Interfaces.FilesLoaderInterface;

public class FileReaderTest {

	private static FileReaderInterface _fileReader;
	private static FilesLoaderInterface _fileLoader;
	private static File _fileExample;
	private static String _text;
	
	@BeforeClass
	public static void onceExecutedBeforeAll() throws IOException {
		_fileReader = new FileReader();
		_fileLoader = new FilesLoader();
		_fileExample = new File("test.txt");
		_fileExample.createNewFile();
		_text = "1,2,3";
		PrintWriter write  = new PrintWriter(_fileExample.getName());
		write.println(_text);
		write.close();
	}
	
	@Test
	public void ReadFile_Success() throws IOException, NullParamException {
	String result = _fileReader.ReadFile(_fileExample.getName());
		assertEquals(_text, result);
		
	}

	@Test
	(expected = NullParamException.class)
	public void ReadFile_NullRoute() throws IOException, NullParamException {
	 _fileReader.ReadFile(null);
	}
	
	@Test
	(expected = InvalidParameterException.class)
	public void ReadFile_EmptyRoute() throws IOException, NullParamException {
	 _fileReader.ReadFile("");
	}
	
	@Test
	(expected = FileNotFoundException.class)
	public void ReadFile_NotFound() throws IOException, NullParamException {
	 _fileReader.ReadFile("test1.txt");
	}
}
