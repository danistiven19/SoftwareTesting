package co.edu.udea.program1.Common.Tests.Utils;

import static org.junit.Assert.*;

import java.io.IOException;
import java.security.InvalidParameterException;

import org.junit.BeforeClass;
import org.junit.Test;

import co.edu.udea.program1.Common.Exceptions.NullParamException;
import co.edu.udea.program1.Common.Utils.Utils;

public class UtilsTest {

	private static Utils _utils;
	
	@BeforeClass
	public static void onceExecutedBeforeAll()  {
		_utils = new Utils();
	}
	
	@Test
	(expected = NullParamException.class)
	public void SplitByComma_Null() throws NullParamException {
		_utils.SplitByComma(null);
	}
	
	@Test
	(expected = InvalidParameterException.class)
	public void SplitByComma_EmptyParam() throws NullParamException {
		_utils.SplitByComma("");
	}
	
	@Test
	(expected = InvalidParameterException.class)
	public void SplitByComma_InvalidFormat() throws NullParamException {
		_utils.SplitByComma("1;2");
	}
	
	@Test
	public void SplitByComma_SuccessDoubleValue() throws NullParamException {
		String[] values = _utils.SplitByComma("1.2,2.1");
		assertEquals("1.2", values[0]);
	}	

}
