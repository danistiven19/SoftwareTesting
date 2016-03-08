package co.edu.udea.program1.Common.Tests.BAL;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.InvalidParameterException;
import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import co.edu.udea.program1.BAL.Managers.ProgramManager;
import co.edu.udea.program1.BAL.Managers.Interfaces.ProgramManagerInterface;
import co.edu.udea.program1.Common.Exceptions.IsNotProgramException;
import co.edu.udea.program1.Common.Exceptions.NullParamException;
import co.edu.udea.program1.Common.Models.Part;
import co.edu.udea.program1.Common.Models.Program;

public class ProgramManagerTest {
	
	private static ProgramManagerInterface _programManager;
	private File _directory;
	private static final String DIRECTORY_NAME = "directoryTest";
	private static final String DIRECTORY_NAME2 = "directoryTest2";
	private static final String DIRECTORY_NAME3 = "directoryTest3";
	
	@BeforeClass
	public static void onceExecutedBeforeAll() throws IOException {
		_programManager = new ProgramManager();
	}
	
	@Test
	(expected= FileNotFoundException.class)
	public void Program_CreateProgram_DontExist() throws IOException, NullParamException, IsNotProgramException{
		_programManager.CreateProgram("Anything");		
	}
	
	@Test
	public void Program_CreateProgram_IsNotProgram() throws IOException, NullParamException{
		File directory = new File("directoryTest");
		
		boolean response = _programManager.IsjavaProgram(directory,false);
		directory.delete();
		assertFalse(response);
		
	}
	
	@Test
	public void Program_CreateProgram_IsProgram() throws IOException, NullParamException{
		_createJavaDirectory("I'm a program file");
		boolean response = _programManager.IsjavaProgram(_directory.getParentFile(),false);
		_directory.delete();
		assertTrue(response);
	}
	
	@Test
	(expected = InvalidParameterException.class)
	public void Program_CreateProgram_ProgramIsNull() throws IOException, NullParamException{
		_programManager.IsjavaProgram(null,false);
	}
	
	@Test
	(expected = IsNotProgramException.class)
	public void Program_CreateProgram_IsNotjavaProgram() throws IOException, NullParamException, IsNotProgramException{
		File directory = new File(DIRECTORY_NAME2+"\\file.txt");
		directory.getParentFile().mkdir();
		 _programManager.CreateProgram(directory.getParentFile().getName());
		directory.delete();
		
	}
	
	@Test
	public void Program_CreateProgram_IsjavaProgram() throws IOException, NullParamException, IsNotProgramException{
		_createJavaDirectory("I'm a program file");
		Program response = _programManager.CreateProgram(_directory.getParent());
		_directory.delete();
		assertNotNull(response);
	}
	
	@Test
	public void Program_ProcessProgram_OnePart() throws IOException, NullParamException, IsNotProgramException{
		Program p = _generateProgramTest();
		p = _programManager.ProcessProgram(p);
		p.getDirectory().delete();
		assertEquals(1,p.Parts.size());
		assertEquals(1,p.TotalSize);
	}
	
	
	@Test
	public void Program_ProcessProgram_TwoParts() throws IOException, NullParamException, IsNotProgramException{
		Program p = _generateProgramTestTwo();
		p = _programManager.ProcessProgram(p);
		p.getDirectory().delete();
		assertEquals(2,p.Parts.size());
		assertEquals(2,p.TotalSize);
	}
	
	@Test
	public void Program_ProcessProgram_OnePartTwoItems() throws IOException, NullParamException, IsNotProgramException{
		Program p = _generateProgramTestOnePartTwoItems();
		p = _programManager.ProcessProgram(p);
		p.getDirectory().delete();
		assertEquals("Number of parts",1,p.Parts.size());
		assertEquals("Total Size",2,p.TotalSize);
		assertEquals("Number of items",2, p.getParts().get(0).getNumberOfItems());
	}

	private void  _createJavaDirectory(String text) throws IOException {
		_directory = new File(DIRECTORY_NAME+"\\file.java");
		_directory.getParentFile().mkdir();
		_directory.createNewFile();
		PrintWriter write  = new PrintWriter(_directory);
		write.println(text);
		write.close();
	}
	

	private Program _generateProgramTest() throws IOException, NullParamException, IsNotProgramException{
		_createJavaDirectory("Sample");
		Program program = new Program();
		program.setDirectory(_directory.getParentFile());
		program.setName(_directory.getName());
		program.setTotalSize(0);
		program.setParts(new ArrayList<Part>());
		return program;
		
	}
	
	private Program _generateProgramTestTwo() throws IOException, NullParamException, IsNotProgramException{
		File directory = new File(DIRECTORY_NAME2);
		directory.mkdir();
		
		File directory2 = new File(DIRECTORY_NAME2+"\\part1\\file.java");
		directory2.getParentFile().mkdir();
		directory2.createNewFile();
		PrintWriter write  = new PrintWriter(directory2);
		write.println("sample");
		write.close();

		directory2 = new File(DIRECTORY_NAME2+"\\part2\\file.java");
		directory2.getParentFile().mkdir();
		directory2.createNewFile();
		write  = new PrintWriter(directory2);
		write.println("sample");
		write.close();
		
		Program program = new Program();
		program.setDirectory(directory);
		program.setName(directory.getName());
		program.setTotalSize(0);
		program.setParts(new ArrayList<Part>());
		return program;
		
	}
	
	private Program _generateProgramTestOnePartTwoItems() throws IOException, NullParamException, IsNotProgramException{
		File directory = new File(DIRECTORY_NAME3);
		directory.mkdir();
		
		File directory2 = new File(DIRECTORY_NAME3+"\\part1\\file.java");
		directory2.getParentFile().mkdir();
		directory2.createNewFile();
		PrintWriter write  = new PrintWriter(directory2);
		write.println("sample");
		write.close();

		directory2 = new File(DIRECTORY_NAME3+"\\part1\\file2.java");
		directory2.getParentFile().mkdir();
		directory2.createNewFile();
		write  = new PrintWriter(directory2);
		write.println("sample");
		write.close();
		
		Program program = new Program();
		program.setDirectory(directory);
		program.setName(directory.getName());
		program.setTotalSize(0);
		program.setParts(new ArrayList<Part>());
		return program;
		
	}
	
}
