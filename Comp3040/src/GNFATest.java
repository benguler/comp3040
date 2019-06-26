import java.util.ArrayList;
import java.util.Arrays;

public class GNFATest {
	
	private static NFAFunctions func = new NFAFunctions();

	//Initialize Alphabet(s)-----------------------------------------------------------------------------------------------
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
	
	private static ArrayList<State> testStates = new ArrayList<State>(func.newList(new State("q0"), new State("q1"), new State("q2"), new State("q3")));
	
	private static State testStart = testStates.get(0); 
	
	private static RegEx reg0 = new RegUnion(new RegChar(biAlphabet.get(0), biAlphabet), new RegChar(biAlphabet.get(1), biAlphabet));
	private static RegEx reg1 = new RegConcat(new RegConcat(new RegChar(biAlphabet.get(1), biAlphabet), new RegChar(biAlphabet.get(1), biAlphabet)), new RegConcat(new RegChar(biAlphabet.get(0), biAlphabet), new RegChar(biAlphabet.get(1), biAlphabet)));
	
	private static ArrayList<RegEx> testRegs = new ArrayList<RegEx>(Arrays.asList(
															new RegEmpty(biAlphabet), new RegEpsilon(biAlphabet), new RegEmpty(biAlphabet), new RegEmpty(biAlphabet),		//No other state connecting to the start state is represented by empty RegEx's
															new RegEmpty(biAlphabet), reg0, reg1, new RegEmpty(biAlphabet),
															new RegEmpty(biAlphabet), new RegEmpty(biAlphabet), reg0, new RegEpsilon(biAlphabet),
															new RegEmpty(biAlphabet), new RegEmpty(biAlphabet), new RegEmpty(biAlphabet), new RegEmpty(biAlphabet)			//The accepting state connecting to no other states is represented with empty RegEx's
															));
	
	private static State testAccept = testStates.get(3); 
	
	private static GNFA testGNFA = new GNFA(testStates, biAlphabet, testStart, testRegs, testAccept, NFAFunctions.epsilon);
	
	public static void main(String[] args) {
		AlphaString testString1 = new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(0), biAlphabet.get(1), biAlphabet.get(1), biAlphabet.get(0), biAlphabet.get(1) , biAlphabet.get(1))));
		AlphaString testString2 = new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(1), biAlphabet.get(0), biAlphabet.get(1))));
		AlphaString testString3 = new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(1), biAlphabet.get(1))));
		
		if(testGNFA.run(testString2)) {
			System.out.print("Yes");
			
		}else {
			System.out.print("No");
		}

	}

}
