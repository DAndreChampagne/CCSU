package cs506.project.SyntaxTests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.regex.*;


class T2_MutantSyntaxTests {
	
	
	//original:   ["0"-"9"]((["0"-"9","_"])*["0"-"9"])?
	private final String DECIMAL_LITERAL = "[0-9](([0-9,_])*)?"; //private final String DECIMAL_LITERAL = "[0-9](([0-9,_])*[0-9])?";

	
	//original:   ["e","E"] (["+","-"])? (<DECIMAL_LITERAL>)+
	private final String DECIMAL_EXPONENT = "[eE]([+-])*(" + DECIMAL_LITERAL + ")+"; //private final String DECIMAL_EXPONENT = "[e,E]([+-])?(" + DECIMAL_LITERAL + ")+";
	
	
	//original:   <DECIMAL_LITERAL> "." (<DECIMAL_LITERAL>)? (<DECIMAL_EXPONENT>)? (["f","F","d","D"])?
    //				| "." <DECIMAL_LITERAL> (<DECIMAL_EXPONENT>)? (["f","F","d","D"])?
    //				| <DECIMAL_LITERAL> <DECIMAL_EXPONENT> (["f","F","d","D"])?
    //				| <DECIMAL_LITERAL> (<DECIMAL_EXPONENT>)? ["f","F","d","D"]>
	private final String DECIMAL_FLOATING_POINT_LITERAL =
			DECIMAL_LITERAL + ".*("+DECIMAL_LITERAL+")?("+DECIMAL_EXPONENT+")?([f,F,d,D])?" + // DECIMAL_LITERAL + ".("+DECIMAL_LITERAL+")?("+DECIMAL_EXPONENT+")?([f,F,d,D])?" +
			"|.*"+DECIMAL_LITERAL+"("+DECIMAL_EXPONENT+")?([f,F,d,D])?" + // "|."+DECIMAL_LITERAL+"("+DECIMAL_EXPONENT+")?([f,F,d,D])?" +
			"|"+DECIMAL_LITERAL+""+DECIMAL_EXPONENT+"([f,F,d,D])?" +
			"|"+DECIMAL_LITERAL+"("+DECIMAL_EXPONENT+")?[f,F,d,D]>";

	
	@ParameterizedTest(name = "Testing literal \"{0}\"")
	@ValueSource(strings = { "1,,,", })
	void DecimalLiteralTests(String s) {
		Pattern p = Pattern.compile(DECIMAL_LITERAL);
		Matcher m = p.matcher(s);
		
		boolean expected = false;
		boolean actual = m.matches();
		
		assertEquals(expected, actual);
	}
	
	@ParameterizedTest(name = "Testing literal \"{0}\"")
	@ValueSource(strings = { "e++++++++20",  })
	void DecimalExponentTests(String s) {
		Pattern p = Pattern.compile(DECIMAL_EXPONENT);
		Matcher m = p.matcher(s);
		
		boolean expected = false;
		boolean actual = m.matches();
		
		assertEquals(expected, actual);
	}

	@ParameterizedTest(name = "Testing literal \"{0}\"")
	@ValueSource(strings = { 
			//DECIMAL_LITERAL + ".("+DECIMAL_LITERAL+")?("+DECIMAL_EXPONENT+")?([f,F,d,D])?" +
			"1..0", "20..2", "3..e", "4..4e4", "5..5E5f"
			
			//"|."+DECIMAL_LITERAL+"("+DECIMAL_EXPONENT+")?([f,F,d,D])?" +
			,"...1", ".2e.2", "..3e3d"
			
			//"|"+DECIMAL_LITERAL+""+DECIMAL_EXPONENT+"([f,F,d,D])?" +
			,"1e1..", "2..e2d"
			
			//"|"+DECIMAL_LITERAL+"("+DECIMAL_EXPONENT+")?[f,F,d,D]>";
			,"1e+1..d", "2..e-2D"
			
	})
	void DecimalFloatingPointTests(String s) {
		Pattern p = Pattern.compile(DECIMAL_FLOATING_POINT_LITERAL);
		Matcher m = p.matcher(s);
		
		boolean expected = false;
		boolean actual = m.matches();
		
		assertEquals(expected, actual);
	}
	
	
}
