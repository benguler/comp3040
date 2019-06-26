import java.sql.Ref;
import java.util.ArrayList;
import java.util.Arrays;

public class RegTest{
	
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
	
	//NFA that accepts odd binary #s (The "oddNFA")------------------------------------------------------------------------- Used for testing																
		private static ArrayList<State> oddNFAStates = new ArrayList<State>(Arrays.asList(new State("odd0"), new State("odd1")));						//Q = {odd0, odd1}
		
		private static State oddNFAStartState = oddNFAStates.get(0);																			//q0 = odd0
		
		private static ArrayList<ArrayList<State>>oddNFANextStates = new ArrayList<ArrayList<State>>(Arrays.asList(
																									 func.newList( oddNFAStates.get(0) ), func.newList( oddNFAStates.get(0), oddNFAStates.get(1) ), func.newList(),	//Delta = {(odd0, '0', {odd0}), (odd0, '1', {odd0, odd1}), (odd0, func.epsilon, {})
																									 func.newList(), func.newList(), func.newList())																	//        	   (odd1, '0', {}), (odd1, '1', {}), (odd1, func.epsilon, {})}
																									);	
																									
		
		private static ArrayList<State>oddNFAAcceptingStates = new ArrayList<State>(Arrays.asList(oddNFAStates.get(1)));						//F = {odd1}
		
		private static NFA oddNFA = new NFA(oddNFAStates, biAlphabet,oddNFAStartState,oddNFANextStates,oddNFAAcceptingStates, NFAFunctions.epsilon);
		
	//----------------------------------------------------------------------------------------------------------------------
		
	//NFA that accepts even binary #s (The "evenNFA")----------------------------------------------------------------------- Used for testing
		private static ArrayList<State> evenNFAStates = new ArrayList<State>(Arrays.asList(new State("even0"), new State("even1")));						//Q = {evenO, even1}
		
		private static State evenNFAStartState = evenNFAStates.get(0);																				//q0 = evenO
		
		private static ArrayList<ArrayList<State>>evenNFANextStates = new ArrayList<ArrayList<State>>(Arrays.asList(
																									 func.newList( evenNFAStates.get(0), evenNFAStates.get(1)), func.newList( evenNFAStates.get(0)), func.newList(),	//Delta = {(even0, '0', {even0, even1}), (even0, '1', {even0}), (even0, func.epsilon, {})
																									 func.newList(), func.newList(), func.newList())																	//         (even1, '0', {}),     (even1, '1', {}),  (even1, func.epsilon, {})}
																									);	
																									
		
		private static ArrayList<State>evenNFAAcceptingStates = new ArrayList<State>(Arrays.asList(evenNFAStates.get(1)));						//F = {even1}
		
		private static NFA evenNFA = new NFA(evenNFAStates, biAlphabet,evenNFAStartState,evenNFANextStates,evenNFAAcceptingStates, NFAFunctions.epsilon);
		
	//----------------------------------------------------------------------------------------------------------------------
		
	private static RegEx regZOStar = new RegStar(new RegConcat(new RegChar(biAlphabet.get(0), biAlphabet), new RegChar(biAlphabet.get(1), biAlphabet))); 	//('01')*
	
	private static AlphaString zoTestStrings[] = new AlphaString[]{
			new AlphaString(biAlphabet),																														//epsilon
			new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(0), biAlphabet.get(1)))),											//01
		    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(0), biAlphabet.get(0), biAlphabet.get(1)))),	//1001
		    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(0), biAlphabet.get(1), biAlphabet.get(0), biAlphabet.get(0)))),	//0101
		    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(0), biAlphabet.get(1), biAlphabet.get(1)))),	//1011
		    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(0), biAlphabet.get(1), biAlphabet.get(0)))),	//1000
		    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(1), biAlphabet.get(0), biAlphabet.get(1)))),	//1101
		    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(0), biAlphabet.get(0), biAlphabet.get(1)))),	//1001
		    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(0), biAlphabet.get(0), biAlphabet.get(0)))),	//1000
		    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(0), biAlphabet.get(1), biAlphabet.get(1)))),	//1011
		    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(0), biAlphabet.get(1), biAlphabet.get(0)))),	//1000
		    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(1), biAlphabet.get(0), biAlphabet.get(1))))	//1101
	};
	
	private static RegEx regABCUnion = new RegUnion(new RegUnion(new RegChar(engAlphabet.get(0), engAlphabet), new RegChar(engAlphabet.get(1), engAlphabet)), new RegChar(engAlphabet.get(2), engAlphabet));	//'a' u 'b' u  'c'
	
	private static AlphaString abcUnionTestStrings[] = new AlphaString[]{
			new AlphaString(engAlphabet, new ArrayList<Character>(Arrays.asList(engAlphabet.get(0)))),																	//a
		    new AlphaString(engAlphabet, new ArrayList<Character>(Arrays.asList(engAlphabet.get(0), engAlphabet.get(2)))),												//ac
		    new AlphaString(engAlphabet, new ArrayList<Character>(Arrays.asList(engAlphabet.get(0), engAlphabet.get(1), engAlphabet.get(2)))),							//abc
		    new AlphaString(engAlphabet, new ArrayList<Character>(Arrays.asList(engAlphabet.get(1)))),																	//b
		    new AlphaString(engAlphabet, new ArrayList<Character>(Arrays.asList(engAlphabet.get(2)))),																	//c
	};
	
	private static RegEx regEpsilon = new RegEpsilon(engAlphabet); // '[func.epsilon]'
	
	private static AlphaString epsilonTestStrings[] = new AlphaString[]{
			new AlphaString(biAlphabet),																																//epsilon
		    new AlphaString(engAlphabet, new ArrayList<Character>(Arrays.asList(engAlphabet.get(7), engAlphabet.get(2), engAlphabet.get(5)))),							//hcf
		    new AlphaString(engAlphabet, new ArrayList<Character>(Arrays.asList(engAlphabet.get(20), engAlphabet.get(15), engAlphabet.get(12)))),						//upm
		    new AlphaString(engAlphabet, new ArrayList<Character>(Arrays.asList(engAlphabet.get(0), engAlphabet.get(25), engAlphabet.get(11), engAlphabet.get(25)))),	//azkz
		    new AlphaString(engAlphabet, new ArrayList<Character>(Arrays.asList(engAlphabet.get(11), engAlphabet.get(5), engAlphabet.get(4), engAlphabet.get(14)))),	//lfeo
	};
	
	private static RegEx regEOunion = new RegUnion(new RegEpsilon(biAlphabet), new RegChar(biAlphabet.get(1), biAlphabet));	//[func.epsilon] u '1'
	
	private static AlphaString EOUnionTestStrings[] = new AlphaString[]{
			new AlphaString(biAlphabet),																														//epsilon
			new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1)))),															//1
		    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(0)))),															//0
		    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(1), biAlphabet.get(1), biAlphabet.get(1)))),	//1111
	};
	
	private static RegEx regAll = new RegStar(new RegUnion(new RegChar(biAlphabet.get(0), biAlphabet), new RegChar(biAlphabet.get(1), biAlphabet)));	//('0' u '1')*
	
	private static AlphaString allTestStrings[] = new AlphaString[]{
			new AlphaString(biAlphabet),																														//epsilon
		    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(0), biAlphabet.get(0), biAlphabet.get(1)))),						//001
		    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(0), biAlphabet.get(1), biAlphabet.get(0)))),						//010
		    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(0), biAlphabet.get(1)))),						//011
		    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(0), biAlphabet.get(0)))),						//100
		    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(1), biAlphabet.get(1)))),						//111
		    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(1), biAlphabet.get(0)))),						//110
		    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(0), biAlphabet.get(0), biAlphabet.get(1)))),	//1001
	};
	
	private static RegEx regNone = new RegEmpty(biAlphabet);
	
	private static AlphaString noneTestStrings[] = new AlphaString[]{
			new AlphaString(biAlphabet),																														//epsilon
		    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(0), biAlphabet.get(0), biAlphabet.get(1)))),						//001
		    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(0), biAlphabet.get(1), biAlphabet.get(0)))),						//010
		    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(0), biAlphabet.get(1)))),						//011
		    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(0), biAlphabet.get(0)))),						//100
		    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(1), biAlphabet.get(1)))),						//111
		    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(1), biAlphabet.get(0)))),						//110
		    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(0), biAlphabet.get(0), biAlphabet.get(1)))),	//1001
	};
	
	private static RegEx regOdd = new RegConcat( new RegStar(new RegUnion(new RegChar(biAlphabet.get(0), biAlphabet), new RegChar(biAlphabet.get(1), biAlphabet))), new RegChar(biAlphabet.get(1), biAlphabet));	//('0' u '1')* o '1'
	
	private static AlphaString oddTestStrings[] = new AlphaString[]{
			new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(0), biAlphabet.get(0), biAlphabet.get(0)))),						//000
		    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(0), biAlphabet.get(0), biAlphabet.get(1)))),						//001
		    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(0), biAlphabet.get(1), biAlphabet.get(0)))),						//010
		    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(0), biAlphabet.get(1)))),						//011
		    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(0), biAlphabet.get(0)))),						//100
		    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(1), biAlphabet.get(1)))),						//111
		    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(1), biAlphabet.get(0)))),						//110
		    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(0), biAlphabet.get(0), biAlphabet.get(1)))),	//1001
	};
	
	private static RegEx regEven = new RegConcat( new RegStar(new RegUnion(new RegChar(biAlphabet.get(0), biAlphabet), new RegChar(biAlphabet.get(1), biAlphabet))), new RegChar(biAlphabet.get(0), biAlphabet));	//('0' u '1')* o '0'
	
	private static AlphaString evenTestStrings[] = new AlphaString[]{
			new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(0), biAlphabet.get(0), biAlphabet.get(0)))),							//000
		    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(0), biAlphabet.get(0), biAlphabet.get(1)))),							//001
		    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(0), biAlphabet.get(1), biAlphabet.get(0)))),							//010
		    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(0), biAlphabet.get(1)))),							//011
		    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(0), biAlphabet.get(0)))),							//100
		    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(1), biAlphabet.get(1)))),							//111
		    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(1), biAlphabet.get(0)))),							//110
		    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(0), biAlphabet.get(0), biAlphabet.get(1))))		//1001
	};
	
	private static RegEx regNotEp = new RegUnion( new RegConcat( new RegStar(new RegUnion(new RegChar(biAlphabet.get(0), biAlphabet), new RegChar(biAlphabet.get(1), biAlphabet))), new RegChar(biAlphabet.get(1), biAlphabet)), new RegConcat( regAll, new RegChar(biAlphabet.get(0), biAlphabet)));		//(('0' u '1')* o '0') u (('0' u '1')* o '1')
	
	private static AlphaString notEpTestStrings[] = new AlphaString[]{
			new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(0), biAlphabet.get(0), biAlphabet.get(0)))),						//000
		    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(0), biAlphabet.get(0), biAlphabet.get(1)))),						//001
		    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(0), biAlphabet.get(1), biAlphabet.get(0)))),						//010
		    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(0), biAlphabet.get(1)))),						//011
		    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(0), biAlphabet.get(0)))),						//100
		    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(1), biAlphabet.get(1)))),						//111
		    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(1), biAlphabet.get(0)))),						//110
		    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(0), biAlphabet.get(0), biAlphabet.get(1)))),	//1001
		    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(0), biAlphabet.get(0), biAlphabet.get(0)))),	//1000
		    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(0), biAlphabet.get(1), biAlphabet.get(1)))),	//1011
		    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(0), biAlphabet.get(1), biAlphabet.get(0)))),	//1000
		    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(1), biAlphabet.get(0), biAlphabet.get(1))))	//1101
	};
	
	private static RegEx regOddEven = new RegConcat(new RegConcat( new RegStar(new RegUnion(new RegChar(biAlphabet.get(0), biAlphabet), new RegChar(biAlphabet.get(1), biAlphabet))), new RegChar(biAlphabet.get(1), biAlphabet)), new RegConcat( regAll, new RegChar(biAlphabet.get(0), biAlphabet)));	//(('0' u '1')* o '0') o (('0' u '1')* o '1')
	
	private static AlphaString oddEvenTestStrings[] = new AlphaString[]{
			new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(0), biAlphabet.get(0), biAlphabet.get(0)))),						//000
		    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(0), biAlphabet.get(0), biAlphabet.get(1)))),						//001
		    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(0), biAlphabet.get(1), biAlphabet.get(0)))),						//010
		    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(0), biAlphabet.get(1)))),						//011
		    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(0), biAlphabet.get(0)))),						//100
		    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(1), biAlphabet.get(1)))),						//111
		    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(1), biAlphabet.get(0)))),						//110
		    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(0), biAlphabet.get(0), biAlphabet.get(1)))),	//1001
		    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(0), biAlphabet.get(0), biAlphabet.get(0)))),	//1000
		    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(0), biAlphabet.get(1), biAlphabet.get(1)))),	//1011
		    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(0), biAlphabet.get(1), biAlphabet.get(0)))),	//1000
		    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(1), biAlphabet.get(0), biAlphabet.get(1))))	//1101
	};
	
	private static RegEx regZStarOStar = new RegConcat(new RegStar(new RegChar(biAlphabet.get(0), biAlphabet)), new RegStar(new RegChar(biAlphabet.get(1), biAlphabet)));	//('0')* o ('1')*
	
	private static AlphaString epTestStrings[] = new AlphaString[]{
			new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(0), biAlphabet.get(0), biAlphabet.get(0)))),						//000
		    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(0), biAlphabet.get(0), biAlphabet.get(1)))),						//001
		    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(0), biAlphabet.get(1), biAlphabet.get(0)))),						//010
		    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(0), biAlphabet.get(1)))),						//011
		    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(0), biAlphabet.get(0)))),						//100
		    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(1), biAlphabet.get(1)))),						//111
		    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(1), biAlphabet.get(0)))),						//110
		    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(0), biAlphabet.get(0), biAlphabet.get(1), biAlphabet.get(1)))),	//0011
		    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(0), biAlphabet.get(0), biAlphabet.get(0)))),	//1000
		    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(0), biAlphabet.get(1), biAlphabet.get(1)))),	//1011
		    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(0), biAlphabet.get(1), biAlphabet.get(0)))),	//1000
		    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(1), biAlphabet.get(0), biAlphabet.get(0))))	//1100
	};
	
	private static RegEx regBen = new RegConcat(new RegConcat(new RegChar(engAlphabet.get(1), engAlphabet), new RegChar(engAlphabet.get(4), engAlphabet)), new RegChar(engAlphabet.get(13), engAlphabet));	//'b' o 'e' o 'n'
	
	private static AlphaString benTestStrings[] = new AlphaString[]{
			new AlphaString(engAlphabet, new ArrayList<Character>(Arrays.asList(engAlphabet.get(1), engAlphabet.get(4), engAlphabet.get(13)))),							//ben
		    new AlphaString(engAlphabet, new ArrayList<Character>(Arrays.asList(engAlphabet.get(7), engAlphabet.get(2), engAlphabet.get(5)))),							//hcf
		    new AlphaString(engAlphabet, new ArrayList<Character>(Arrays.asList(engAlphabet.get(20), engAlphabet.get(15), engAlphabet.get(12)))),						//upm
		    new AlphaString(engAlphabet, new ArrayList<Character>(Arrays.asList(engAlphabet.get(21), engAlphabet.get(18), engAlphabet.get(11), engAlphabet.get(5)))),	//vslf
		    new AlphaString(engAlphabet, new ArrayList<Character>(Arrays.asList(engAlphabet.get(11), engAlphabet.get(5), engAlphabet.get(4), engAlphabet.get(14)))),	//lfeo
		    new AlphaString(engAlphabet, new ArrayList<Character>(Arrays.asList(engAlphabet.get(1), engAlphabet.get(4), engAlphabet.get(13), engAlphabet.get(0)))),		//bena
		    new AlphaString(engAlphabet, new ArrayList<Character>(Arrays.asList(engAlphabet.get(11), engAlphabet.get(1), engAlphabet.get(4), engAlphabet.get(13))))		//lben
	};
			
	@SuppressWarnings("static-access")
	public static void main(String[] args) { 
		String accepts;
		
		//Test if RegEx compiler works
		System.out.println("Compiler Tests:\n");
		
		accepts = (func.equal(oddNFA, regOdd.compile()))? " == ":" != ";	
		System.out.println("    oddNFA " + accepts + regOdd.displayable()+ "\n");
		
		accepts = (func.equal(evenNFA, regEven.compile()))? " == ":" != ";	
		System.out.println("    evenNFA " + accepts + regEven.displayable()+ "\n");
		
		accepts = (func.equal(oddNFA, regEven.compile()))? " == ":" != ";	
		System.out.println("    oddNFA " + accepts + regEven.displayable()+ "\n");
		
		accepts = (func.equal(func.concatenation(oddNFA, evenNFA), regOddEven.compile()))? " == ":" != ";	
		System.out.println("    oddNFA o evenNFA " + accepts + regOddEven.displayable()+ "\n");
		
		//RegEx equality tests
		System.out.println("Equality Tests:\n");
		
		accepts = (regEqual(regAll, new RegUnion(new RegUnion(regOdd, regEven), new RegEpsilon(biAlphabet))))? " == 4":" != 4";	
		eqPrint(regAll, accepts, new RegUnion(new RegUnion(regOdd, regEven), new RegEpsilon(biAlphabet)));
		
		accepts = (regEqual(regNotEp, new RegUnion(regOdd, regEven)))? " == ":" != ";	
		eqPrint(regNotEp, accepts, new RegUnion(regOdd, regEven));
		
		accepts = (regEqual(regNotEp, new RegUnion(regOdd, regEven)))? " == ":" != ";	
		eqPrint(regNotEp, accepts, new RegUnion(regOdd, regEven));
		
		accepts = (regEqual(regOdd, regEven))? " == ":" != ";	
		eqPrint(regOdd, accepts, regEven);
		
		accepts = (regEqual(regNone, new RegConcat(regAll, new RegEmpty(biAlphabet))))? " == ":" != ";	
		eqPrint(regNone, accepts, new RegConcat(regAll, new RegEmpty(biAlphabet)));
		
		accepts = (regEqual(regBen, regABCUnion))? " == ":" != ";	
		eqPrint(regBen, accepts, regABCUnion);
		
		accepts = (regEqual(new RegUnion(regOdd, regEven), new RegUnion(regOdd, regEven)))? " == ":" != ";	
		eqPrint(new RegUnion(regOdd, regEven), accepts, new RegUnion(regOdd, regEven));
		
		accepts = (regEqual(new RegUnion(regOdd, regEven), new RegUnion(regEven, regOdd)))? " == ":" != ";	
		eqPrint(new RegUnion(regOdd, regEven), accepts, new RegUnion(regOdd, regEven));

		accepts = (regEqual(new RegUnion(regBen, regABCUnion), new RegUnion(regBen, regABCUnion)))? " == ":" != ";	
		eqPrint(new RegUnion(regBen, regABCUnion), accepts, new RegUnion(regBen, regABCUnion));
		
		accepts = (regEqual(new RegUnion(regBen, regABCUnion), new RegUnion(regABCUnion, regBen)))? " == ":" != ";	
		eqPrint(new RegUnion(regBen, regABCUnion), accepts, new RegUnion(regABCUnion, regBen));
		
		accepts = (regEqual(regNone, regNone))? " == ":" != ";	
		eqPrint(regNone, accepts, regNone);
		
		accepts = (regEqual(new RegStar(regNone), regNone))? " == ":" != ";											//(theta)* accepts epsilon, where theta accepts nothing
		eqPrint(new RegStar(regNone), accepts, regNone);
		
		accepts = (regEqual(new RegStar(regEpsilon), regEpsilon))? " == ":" != ";	
		eqPrint(new RegStar(regEpsilon), accepts, regEpsilon);
		
		//RegEx simplifier tests
		
		System.out.println("Simplifier Tests:\n");
		
		RegEx temp; 
		
		temp = new RegUnion(new RegChar(abcAlphabet.get(0), abcAlphabet), new RegEmpty(abcAlphabet));
		
		accepts = (regEqual(temp, simplify(temp)))? " == ":" != ";	
		eqPrint(temp, accepts, simplify(temp));
		
		temp = new RegConcat(new RegChar(abcAlphabet.get(0), abcAlphabet), new RegEpsilon(abcAlphabet));
		
		accepts = (regEqual(temp, simplify(temp)))? " == ":" != ";	
		eqPrint(temp, accepts, simplify(temp));
		
		temp = new RegConcat(new RegChar(abcAlphabet.get(0), abcAlphabet), new RegEmpty(abcAlphabet));
		
		accepts = (regEqual(temp, simplify(temp)))? " == ":" != ";	
		eqPrint(temp, accepts, simplify(temp));
		
		temp = new RegUnion(new RegConcat(new RegChar(abcAlphabet.get(0), abcAlphabet), new RegChar(abcAlphabet.get(1), abcAlphabet)), new RegConcat(new RegChar(abcAlphabet.get(0), abcAlphabet), new RegChar(abcAlphabet.get(2), abcAlphabet)));
		
		accepts = (regEqual(temp, simplify(temp)))? " == ":" != ";	
		eqPrint(temp, accepts, simplify(temp));
		
		temp = new RegStar(new RegStar(new RegUnion(new RegConcat(new RegChar(abcAlphabet.get(0), abcAlphabet), new RegChar(abcAlphabet.get(1), abcAlphabet)), new RegConcat(new RegChar(abcAlphabet.get(0), abcAlphabet), new RegChar(abcAlphabet.get(2), abcAlphabet)))));

		accepts = (regEqual(temp, simplify(temp)))? " == ":" != ";	
		eqPrint(temp, accepts, simplify(temp));
		
	}
	
	public static void printReg(RegEx reg, String string){
		
	}
	
	public static boolean testReg(RegEx reg, AlphaString string) {
		NFA nfa = reg.compile();
		
		return func.stringTest(string, nfa, nfa.getStartState(), 0);
		
	}
	
	public static boolean regEqual(RegEx reg1, RegEx reg2){
		NFA nfa1 = reg1.compile();
		NFA nfa2 = reg2.compile();
		
		return func.equal(nfa1, nfa2);
	
	}
	
	public static RegEx simplify(RegEx reg) {
		if(reg.isUnion()) {
			RegUnion temp = (RegUnion) reg;
			
			if(regEqual(temp.getReg1(), temp.getReg2())) {																							//a u a -> a
				return simplify(temp.getReg1());
				
			}
			
			if(!regEqual(temp.getReg1(), new RegEmpty(reg.getAlphabet())) && regEqual(temp.getReg2(), new RegEmpty(reg.getAlphabet()))) {			//a u [theta] -> a
				return simplify(temp.getReg1());
				
			}else if(regEqual(temp.getReg1(), new RegEmpty(reg.getAlphabet())) && !regEqual(temp.getReg2(), new RegEmpty(reg.getAlphabet()))) {		//[theta] u b -> b
				return simplify(temp.getReg2());
				
			}
			
			if(temp.getReg1().isConcat() && temp.getReg1().isConcat()) {
				RegConcat subTemp1 = (RegConcat)temp.getReg1();
				RegConcat subTemp2 = (RegConcat)temp.getReg2();
				
				if(regEqual(subTemp1.getReg1(), subTemp2.getReg1())) {																				//(a o b) u (a o c)  -> a o (b u c)
					return new RegConcat(simplify(subTemp1.getReg1()), simplify(new RegUnion(subTemp1.getReg2(), subTemp2.getReg2())));				
					
				}else if(regEqual(subTemp1.getReg2(), subTemp2.getReg2())) {																		//(a o c) u (b o c)  -> (a u b) o c
					return new RegConcat(simplify(new RegUnion(subTemp1.getReg1(), subTemp2.getReg1())), simplify(subTemp1.getReg2()));				
					
				}
				
			}
			
			return new RegUnion(simplify(temp.getReg1()), simplify(temp.getReg2()));
			
		}else if(reg.isConcat()) {
			RegConcat temp = (RegConcat) reg;
			
			if(regEqual(temp.getReg1(), new RegEmpty(reg.getAlphabet())) || regEqual(temp.getReg2(), new RegEmpty(reg.getAlphabet()))) {			//a o [theta] -> [theta], [theta] o a -> [theta]
				return new RegEmpty(reg.getAlphabet());
				
			}
			
			if(regEqual(temp.getReg2(), new RegEpsilon(reg.getAlphabet()))) {																	 //a o [epsilon] -> a, [epsilon] o [epsilon] -> [epsilon]
				return simplify(temp.getReg1());
				
			}else if(regEqual(temp.getReg1(), new RegEpsilon(reg.getAlphabet())) && !regEqual(temp.getReg2(), new RegEpsilon(reg.getAlphabet()))) {	//[epsilon] o b -> b
				return simplify(temp.getReg2());
				
			}
			
			return new RegConcat(simplify(temp.getReg1()), simplify(temp.getReg2()));
			
		//If the reg is *, simplify its reg
		}else if(reg.isStar()) {
			RegStar temp = (RegStar) reg;
			
			return new RegStar(simplify(temp.getReg()));
			
		}
		
		//If the reg is empty, char, or epsilon, cannot simplify any further
		return reg;
		
	}
	
	/*public static RegEx dfaToReg(DFA dfa) {
		NFA nfa = func.dfaToNFA(dfa);
		
		for(State state : nfa.getStates()) {
			
		}
		
	}*/
	
	public static void eqPrint(RegEx reg1, String acc, RegEx reg2) {
		System.out.println("    " + reg1.displayable() + acc + reg2.displayable() + "\n");
		
	}
	
}
