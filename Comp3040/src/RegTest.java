import java.sql.Ref;
import java.util.ArrayList;
import java.util.Arrays;

public class RegTest extends NFAFunctions {
	
//Initialize Alphabet(s)-----------------------------------------------------------------------------------------------
		private static final Character epsilon = new Character(""); 

		//The binary numbers
		private static ArrayList<Character> biSymbols =  new ArrayList<Character>(Arrays.asList(new Character("0"), new Character("1")));
		
		private static Alphabet biAlphabet = new Alphabet(biSymbols);
		
		//a, b, and c Alphabet
		private static ArrayList<Character> abcSymbols =  new ArrayList<Character>(Arrays.asList(new Character("a"), new Character("b"), new Character("c")));
		
		
		private static Alphabet abcAlphabet = new Alphabet(abcSymbols);
		
		//Lower-case English alphabet
		private static ArrayList<Character> engSymbols =  new ArrayList<Character>(Arrays.asList(new Character("a"), new Character("b"), new Character("c"), new Character("d"), new Character("e"), new Character("f"), new Character("g"),
																								 new Character("h"), new Character("i"), new Character("j"), new Character("k"), new Character("l"), new Character("m"), new Character("n"),
																								 new Character("o"), new Character("p"), new Character("q"), new Character("r"), new Character("s"), new Character("t"),new Character("u"),
																								 new Character("v"), new Character("w"), new Character("x"), new Character("y"), new Character("z")));
		
		private static Alphabet engAlphabet = new Alphabet(engSymbols);
	//---------------------------------------------------------------------------------------------------------------------
	
	private static RegEx regZOStar = new RegStar(new RegConcat(new RegChar(biAlphabet.get(0), biAlphabet), new RegChar(biAlphabet.get(1), biAlphabet))); 	//('01')*
	
	private static RegEx regABCUnion = new RegUnion(new RegUnion(new RegChar(engAlphabet.get(0), engAlphabet), new RegChar(engAlphabet.get(1), engAlphabet)), new RegChar(engAlphabet.get(2), engAlphabet));	//'a' u 'b' u  'c'
	
	private static RegEx regEpsilon = new RegEpsilon(engAlphabet); // '[epsilon]'
	
	private static RegEx regEOunion = new RegUnion(new RegEpsilon(biAlphabet), new RegChar(biAlphabet.get(1), biAlphabet));	//[epsilon] u '1'
	
	private static RegEx regAll = new RegStar(new RegUnion(new RegChar(biAlphabet.get(0), biAlphabet), new RegChar(biAlphabet.get(1), biAlphabet)));	//('0' u '1')*
	
	private static RegEx regNone = new RegEmpty(biAlphabet);
	
	private static RegEx regOdd = new RegConcat( regAll, new RegChar(biAlphabet.get(1), biAlphabet));	//('0' u '1')* o '1'
	
	private static RegEx regEven = new RegConcat( regAll, new RegChar(biAlphabet.get(0), biAlphabet));	//('0' u '1')* o '0'
	
	private static RegEx regNotEp = new RegUnion( new RegConcat( regAll, new RegChar(biAlphabet.get(1), biAlphabet)), new RegConcat( regAll, new RegChar(biAlphabet.get(0), biAlphabet)));		//(('0' u '1')* o '0') u (('0' u '1')* o '1')
	
	private static RegEx regOddEven = new RegConcat(new RegConcat( regAll, new RegChar(biAlphabet.get(1), biAlphabet)), new RegConcat( regAll, new RegChar(biAlphabet.get(0), biAlphabet)));	//(('0' u '1')* o '0') o (('0' u '1')* o '1')
	
	private static RegEx regZStarOStar = new RegConcat(new RegStar(new RegChar(biAlphabet.get(0), biAlphabet)), new RegStar(new RegChar(biAlphabet.get(1), biAlphabet)));	//('0')* o ('1')*
	
	private static RegEx regBen = new RegConcat(new RegConcat(new RegChar(engAlphabet.get(1), engAlphabet), new RegChar(engAlphabet.get(4), engAlphabet)), new RegChar(engAlphabet.get(13), engAlphabet));	//'b' o 'e' o 'n'
	
	public static void main(String[] args) { 
		String accepts = (testReg(regBen, new AlphaString(engAlphabet, new ArrayList<Character>(Arrays.asList(engAlphabet.get(1), engAlphabet.get(4), engAlphabet.get(13))))))? "String Accepted":"String Rejected";	
		System.out.println(accepts);
		
	}
	
	public static void printReg(RegEx reg, String string){
		
	}
	
	public static boolean regEquals(RegEx reg1, RegEx reg2){
		return nfaEqual(reg1.compile(), reg2.compile());
	
	}
	
	public static boolean testReg(RegEx reg, AlphaString string) {
		NFA nfa = reg.compile();
		
		return stringTest(string, nfa, nfa.getStartState(), 0);
		
	}
	
}
