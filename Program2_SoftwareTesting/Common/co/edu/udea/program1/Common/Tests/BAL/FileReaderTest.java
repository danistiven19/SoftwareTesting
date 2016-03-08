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
	}
	
	@Test
	public void SizeFile_Success() throws IOException, NullParamException {
		_createAndWriteFile("this is a test \n hello");
		int result = _fileReader.CalculateSize(_fileExample);
		assertEquals(2, result);
		_fileExample.delete();
	}

	@Test
	public void SizeFile_HasImport() throws IOException, NullParamException {
		_createAndWriteFile("import libraryx \n import library y");
		int result = _fileReader.CalculateSize(_fileExample);
		assertEquals(0, result);
		_fileExample.delete();
	}
	
	@Test
	public void SizeFile_OneBlankSpace() throws IOException, NullParamException {
		_createAndWriteFile(" ");
		int result = _fileReader.CalculateSize(_fileExample);
		assertEquals(0, result);
		_fileExample.delete();
	}
	
	@Test
	public void SizeFile_MoreThanOneBlankSpaces() throws IOException, NullParamException {
		_createAndWriteFile(" \n \n \n");
		int result = _fileReader.CalculateSize(_fileExample);
		assertEquals(0, result);
		_fileExample.delete();
	}

	@Test
	public void SizeFile_TwoLinesWithOneBlankSpace() throws IOException, NullParamException {
		_createAndWriteFile("Hello \n ");
		int result = _fileReader.CalculateSize(_fileExample);
		assertEquals(1, result);
		_fileExample.delete();
	}
	
	@Test
	public void SizeFile_OneBrace() throws IOException, NullParamException {
		_createAndWriteFile("}");
		int result = _fileReader.CalculateSize(_fileExample);
		assertEquals(1, result);
		_fileExample.delete();
	}
	
	@Test
	public void SizeFile_TwoBraces() throws IOException, NullParamException {
		_createAndWriteFile("}\n }");
		int result = _fileReader.CalculateSize(_fileExample);
		assertEquals(2, result);
		_fileExample.delete();
	}
	
	@Test
	public void SizeFile_SimpleComment() throws IOException, NullParamException {
		_createAndWriteFile("// I'm a comment test \n //I'm another comment test");
		int result = _fileReader.CalculateSize(_fileExample);
		assertEquals(0, result);
		_fileExample.delete();
	}
	
	@Test
	public void SizeFile_BlockComments_SingleLine() throws IOException, NullParamException {
		_createAndWriteFile("/* I'm a comment test */");
		int result = _fileReader.CalculateSize(_fileExample);
		assertEquals(0, result);
		_fileExample.delete();
	}
	
	@Test
	public void SizeFile_BlockComments_MultiLine() throws IOException, NullParamException {
		_createAndWriteFile("/* I'm a comment test \n Here continue the test \n we need three lines*/");
		int result = _fileReader.CalculateSize(_fileExample);
		assertEquals(0, result);
		_fileExample.delete();
	}

	@Test
	public void SizeFile_BlockComments_MultiLineAndText() throws IOException, NullParamException {
		_createAndWriteFile("/* I'm a comment test \n Here continue the test \n we need three lines*/ \n This line must be counted");
		int result = _fileReader.CalculateSize(_fileExample);
		assertEquals(1, result);
		_fileExample.delete();
	}
	
	@Test
	(expected = NullParamException.class)
	public void ReadFile_NullRoute() throws IOException, NullParamException {
	 _fileReader.CalculateSize(null);
	}

	
	@Test
	(expected = FileNotFoundException.class)
	public void ReadFile_NotFound() throws IOException, NullParamException {
	 _fileReader.CalculateSize(new File("test1.txt"));
	}
	
	
	
	private void _createAndWriteFile(String text) throws IOException{
		_fileExample = new File("test.txt");
		_fileExample.createNewFile();
		PrintWriter write  = new PrintWriter(_fileExample.getName());
		write.println(text);
		write.close();
	}
}
