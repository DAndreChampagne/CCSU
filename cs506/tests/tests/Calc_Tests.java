package tests;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.stream.Stream;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import cs506.Calc;




public class Calc_Tests {
	
	@BeforeAll
	public static void BeforeAll() {
		
	}
	
	@AfterAll
	public static void AfterAll() {
		
	}
	
	@BeforeEach
	public void BeforeEach() {
		
	}
	
	@AfterEach
	public void AfterEach() {
		
	}
	
	@Test
	public void CalculateRadius() {
		double actual = Calc.AreaOfCircle(10);
		
		assertEquals(314.1592653589793, actual);
	}
	
}
