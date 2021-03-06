import java.sql.Ref;
import java.util.ArrayList;
import java.util.Arrays;

public class RegTest implements Cloneable{
	
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
	
	private static RegEx regABC = new RegStar(new RegUnion(new RegUnion(new RegChar(engAlphabet.get(0), engAlphabet), new RegChar(engAlphabet.get(1), engAlphabet)), new RegChar(engAlphabet.get(2), engAlphabet)));	//('a' u 'b' u  'c')*
	
	private static AlphaString abcUnionTestStrings[] = new AlphaString[]{
			new AlphaString(engAlphabet, new ArrayList<Character>(Arrays.asList(engAlphabet.get(0)))),																	//a
		    new AlphaString(engAlphabet, new ArrayList<Character>(Arrays.asList(engAlphabet.get(0), engAlphabet.get(2)))),												//ac
		    new AlphaString(engAlphabet, new ArrayList<Character>(Arrays.asList(engAlphabet.get(0), engAlphabet.get(1), engAlphabet.get(2)))),							//abc
		    new AlphaString(engAlphabet, new ArrayList<Character>(Arrays.asList(engAlphabet.get(1)))),																	//b
		    new AlphaString(engAlphabet, new ArrayList<Character>(Arrays.asList(engAlphabet.get(5)))),																	//f
	};
	
	private static RegEx regEpsilon = new RegEpsilon(engAlphabet); // '[func.epsilon]'
	
	private static AlphaString epsilonTestStrings[] = new AlphaString[]{
			new AlphaString(biAlphabet),																																//epsilon
		    new AlphaString(engAlphabet, new ArrayList<Character>(Arrays.asList(engAlphabet.get(7), engAlphabet.get(2), engAlphabet.get(5)))),							//hcf
		    new AlphaString(engAlphabet, new ArrayList<Character>(Arrays.asList(engAlphabet.get(20), engAlphabet.get(15), engAlphabet.get(12)))),						//upm
		    new AlphaString(engAlphabet, new ArrayList<Character>(Arrays.asList(engAlphabet.get(0), engAlphabet.get(25), engAlphabet.get(11), engAlphabet.get(25)))),	//azkz
		    new AlphaString(engAlphabet, new ArrayList<Character>(Arrays.asList(engAlphabet.get(11), engAlphabet.get(5), engAlphabet.get(4), engAlphabet.get(14)))),	//lfeo
	};
	
	private static RegEx regEOunion = new RegUnion(new RegEpsilon(biAlphabet), new RegChar(biAlphabet.get(1), biAlphabet));																						//[epsilon] u '1'
	
	private static AlphaString EOUnionTestStrings[] = new AlphaString[]{
			new AlphaString(biAlphabet),																														//epsilon
			new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1)))),															//1
		    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(0)))),															//0
		    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(1), biAlphabet.get(1), biAlphabet.get(1)))),	//1111
	};
	
	private static RegEx regAll = new RegStar(new RegUnion(new RegChar(biAlphabet.get(0), biAlphabet), new RegChar(biAlphabet.get(1), biAlphabet)));															//('0' u '1')*
	
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
	
	private static RegEx regZStarOStar = new RegConcat(new RegStar(new RegChar(biAlphabet.get(0), biAlphabet)), new RegStar(new RegChar(biAlphabet.get(1), biAlphabet)));									//('0')* o ('1')*
	
	private static AlphaString zStarOStarTestStrings[] = new AlphaString[]{
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
	
	//DFA's and NFA's used for testing
	
	//DFA that accepts a string of my name "ben" (The benDFA")---------------------------------------------------------------
		private static ArrayList<Character> benTestCharacters =  new ArrayList<Character>(Arrays.asList(engAlphabet.get(1), engAlphabet.get(4), engAlphabet.get(13)));
		private static AlphaString benTestString = new AlphaString(engAlphabet, benTestCharacters);
		
		private static ArrayList<State> benDFAStates = new ArrayList<State>(Arrays.asList(new State("A"), new State("B"), new State("C"), 								//Q = {A, B, C, D, E}
				 																		  new State("D"), new State("E")));
		
		private static State benDFAStartState = benDFAStates.get(0);																									//StartState = A
		
		private static ArrayList<State> benDFANextStates = new ArrayList<State>();
		
		private static ArrayList<State> benDFAAcceptingStates = new ArrayList<State>(Arrays.asList(benDFAStates.get(3)));												//F = {D}
		
		private static DFA benDFA;
		
	//----------------------------------------------------------------------------------------------------------------------
	
	//NFA that accepts odd binary #s (The "oddNFA")------------------------------------------------------------------------- 																
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
	
		//NFA that accepts all binary #s (The "allNFA")-------------------------------------------------------------------------
			private static ArrayList<State> allNFAStates = new ArrayList<State>(Arrays.asList(new State("all0"), new State("all1")));						//Q = {A, B}
			
			private static State allNFAStartState = allNFAStates.get(0);																			//q0 = A
			
			private static ArrayList<ArrayList<State>>allNFANextStates = new ArrayList<ArrayList<State>>(Arrays.asList(
																										 func.newList( allNFAStates.get(0) ), func.newList( allNFAStates.get(0)), func.newList( allNFAStates.get(1) ),	//Delta = {(all0, '0', {all0}), (all0, '1', {all0}), (all0, epsilon, {all1})
																										 func.newList(), func.newList(), func.newList())																//        {(all1, '0', {}), (all1, '1', {}), (all1, epsilon, {})
																										);	
																										
			
			private static ArrayList<State>allNFAAcceptingStates = new ArrayList<State>(Arrays.asList(allNFAStates.get(1)));						//F = {all1}
			
			private static NFA allNFA = new NFA(allNFAStates, biAlphabet,allNFAStartState,allNFANextStates,allNFAAcceptingStates, NFAFunctions.epsilon);
			
		//----------------------------------------------------------------------------------------------------------------------
			
		//DFA that accepts odd binary #s (The "oddDFA")-------------------------------------------------------------------------
			private static ArrayList<State> oddDFAStates = new ArrayList<State>(Arrays.asList(new State("A"), new State("B")));						//Q = {A, B}
			
			private static State oddDFAStartState =oddDFAStates.get(0);																				//q0 = A
			
			private static ArrayList<State>oddDFANextStates = new ArrayList<State>(Arrays.asList(oddDFAStates.get(0), oddDFAStates.get(1),			//Delta = {(A, '0', A), (A, '1', B)
																			                     oddDFAStates.get(0), oddDFAStates.get(1)));		//(B, '0', A), (B, '1', B)
			
			private static ArrayList<State>oddDFAAcceptingStates = new ArrayList<State>(Arrays.asList(oddDFAStates.get(1)));						//F = {B}
			
			private static DFA oddDFA = new DFA(oddDFAStates, biAlphabet,oddDFAStartState,oddDFANextStates,oddDFAAcceptingStates);
		//----------------------------------------------------------------------------------------------------------------------
			
		//DFA that accepts a string that contains "ab" somewhere in it (The "abDFA")---------------------------------------------
			private static ArrayList<State> abDFAStates = new ArrayList<State>(Arrays.asList(new State("A"), new State("B"), new State("C")));								//Q = {A, B, C}
				
			private static State abDFAStartState = abDFAStates.get(0);																										//q0 = A
			
			private static ArrayList<State> abDFANextStates = new ArrayList<State>(Arrays.asList(abDFAStates.get(1), abDFAStates.get(0), abDFAStates.get(0),				//Delta = {(A, 'a', B), (A, 'b', A), (A, 'c', A)
																								 abDFAStates.get(1), abDFAStates.get(2), abDFAStates.get(0),                //(B, 'a', B), (B, 'b', C), (B, 'c', A)
																			                     abDFAStates.get(2), abDFAStates.get(2), abDFAStates.get(2)));				//(C, 'a', C), (C, 'b', C), (C, 'c', C)
																			                   
			
			private static ArrayList<State> abDFAAcceptingStates = new ArrayList<State>(Arrays.asList( abDFAStates.get(2)));												//F = {C}
			
			private static DFA abDFA = new DFA(abDFAStates, abcAlphabet, abDFAStartState, abDFANextStates, abDFAAcceptingStates);
	//----------------------------------------------------------------------------------------------------------------------
			
	@SuppressWarnings("static-access")
	public static void main(String[] args) { 
		
		//Define benDFA--------------------------------------------------------------------------------------------------------
		func.initializeStates(engAlphabet, benDFAStates, benDFANextStates, benDFAStates.get(4)); //Delta = {([ALL States], [ALL Characters], E])}
																							//Except for																		
		
		
		benDFANextStates.set((26*0 + 1), benDFAStates.get(1));								//{(A, 'b', B)
		benDFANextStates.set((26*1 + 4), benDFAStates.get(2));								 //(B, 'e', C)
		benDFANextStates.set((26*2 + 13), benDFAStates.get(3));							     //(C, 'n', D)}
		
		benDFA = new DFA(benDFAStates, engAlphabet, benDFAStartState, benDFANextStates, benDFAAcceptingStates);
	//------------------------------------------------------------------------------------------------------------------------
		
		String accepts;
		
		System.out.println(dfaToGNFA(benDFA).run(benTestStrings[0]));
		
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
		
		accepts = (regEqual(regAll, new RegUnion(new RegUnion(regOdd, regEven), new RegEpsilon(biAlphabet))))? " == ":" != ";	
		eqPrint(regAll, accepts, new RegUnion(new RegUnion(regOdd, regEven), new RegEpsilon(biAlphabet)));
		
		accepts = (regEqual(regNotEp, new RegUnion(regOdd, regEven)))? " == ":" != ";	
		eqPrint(regNotEp, accepts, new RegUnion(regOdd, regEven));
		
		accepts = (regEqual(regNotEp, new RegUnion(regOdd, regEven)))? " == ":" != ";	
		eqPrint(regNotEp, accepts, new RegUnion(regOdd, regEven));
		
		accepts = (regEqual(regOdd, regEven))? " == ":" != ";	
		eqPrint(regOdd, accepts, regEven);
		
		accepts = (regEqual(regNone, new RegConcat(regAll, new RegEmpty(biAlphabet))))? " == ":" != ";	
		eqPrint(regNone, accepts, new RegConcat(regAll, new RegEmpty(biAlphabet)));
		
		accepts = (regEqual(regBen, regABC))? " == ":" != ";	
		eqPrint(regBen, accepts, regABC);
		
		accepts = (regEqual(new RegUnion(regOdd, regEven), new RegUnion(regOdd, regEven)))? " == ":" != ";	
		eqPrint(new RegUnion(regOdd, regEven), accepts, new RegUnion(regOdd, regEven));
		
		accepts = (regEqual(new RegUnion(regOdd, regEven), new RegUnion(regEven, regOdd)))? " == ":" != ";	
		eqPrint(new RegUnion(regOdd, regEven), accepts, new RegUnion(regOdd, regEven));

		accepts = (regEqual(new RegUnion(regBen, regABC), new RegUnion(regBen, regABC)))? " == ":" != ";	
		eqPrint(new RegUnion(regBen, regABC), accepts, new RegUnion(regBen, regABC));
		
		accepts = (regEqual(new RegUnion(regBen, regABC), new RegUnion(regABC, regBen)))? " == ":" != ";	
		eqPrint(new RegUnion(regBen, regABC), accepts, new RegUnion(regABC, regBen));
		
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
		
		//Verify that the ripper works by checking if a DFA accepts a string generated by DFA->Reg
		System.out.println("Do DFA's accept strings generated by RegEx's converted from those DFA's:\n");
		
		AlphaString gen;
		NFA nfa;
		DFA dfa;
		
		dfa = func.nfaToDFA(oddNFA); 
		gen = dfaToReg(dfa).generate();
		
		accepts = (dfa.run(gen))? " accpets ":" does not accept ";	
		System.out.println("    oddDFA " + accepts + gen.displayable() + "\n");
		
		dfa = func.nfaToDFA(evenNFA); 
		gen = dfaToReg(dfa).generate();
		
		accepts = (dfa.run(gen))? " accpets ":" does not accept ";		
		System.out.println("    evenDFA" + accepts + gen.displayable() + "\n");
		
		dfa = func.nfaToDFA(allNFA);
		gen = dfaToReg(dfa).generate();
		
		accepts = (dfa.run(gen))? " accpets ":" does not accept ";		
		System.out.println("    allDFA" + accepts + gen.displayable() + "\n");
		
		//Takes an inordinate amount of time to compile. possibly because of alphabet size?
		//dfa = benDFA; 
		//gen = dfaToReg(dfa).generate();
		
		//accepts = (dfa.run(gen))? " accpets ":" does not accept ";	
		//System.out.println("    benDFA" + accepts + gen.displayable() + "\n");
		
		//Verify that the ripper works by checking if a DFA is equal to to (DFA -> Reg -> NFA -> DFA):
		System.out.println("Is DFA == nfaToDFA(dfaToReg(dfa).compile()):\n");
		
		dfa = func.nfaToDFA(oddNFA);  
		
		accepts = (func.equal(dfa, func.nfaToDFA(dfaToReg(dfa).compile())))? " == ":" != ";	
		System.out.println("    oddDFA " + accepts + " (Reg)oddDFA \n");
		
		dfa = func.nfaToDFA(evenNFA);  
		
		accepts = (func.equal(dfa, func.nfaToDFA(dfaToReg(dfa).compile())))? " == ":" != ";	
		System.out.println("    evenDFA " + accepts + " (Reg)evenDFA \n");
		
		dfa = func.nfaToDFA(allNFA);  
		
		accepts = (func.equal(dfa, func.nfaToDFA(dfaToReg(dfa).compile())))? " == ":" != ";	
		System.out.println("    allDFA " + accepts + " (Reg)allDFA \n");
		
		//dfa = benDFA;  
		
		//accepts = (func.equal(dfa, func.nfaToDFA(dfaToReg(dfa).compile())))? " == ":" != ";	
		//System.out.println("    benDFA " + accepts + " (Reg)benDFA \n");
		
		//Regular Pumping examples
		System.out.println("Regular pumping examples:\n");
		
		AlphaString x;
		AlphaString y;
		AlphaString z;
		
		AlphaString origY;
		
		AlphaString xyz;																				//xyz = x(y^i)z, where |y| >= 1, |xyz| >= p, |xy| <=p, and reg.acceppts(x(y^i)z) for i >=0 
		ArrayList<Character> xyzChars;
		
		RegEx reg;
		Alphabet alphabet;
		
		reg = regOdd;
		alphabet = reg.getAlphabet();
		
		x = new AlphaString(alphabet, new ArrayList<Character>(Arrays.asList(alphabet.get(0))));
		y = new AlphaString(alphabet, new ArrayList<Character>(Arrays.asList(alphabet.get(0))));
		z = new AlphaString(alphabet, new ArrayList<Character>(Arrays.asList(alphabet.get(1))));
		
		System.out.println("\n    regOdd:");
		System.out.println("	x = '" + x.displayable()+"'");
		System.out.println("	y = '" + y.displayable()+"'");
		System.out.println("	z = '" + z.displayable()+"'");
		
		origY = new AlphaString(y.getAlphabet(), new ArrayList<Character>(y.getChars()));
		
		for (int i = 0; i<20; i++) {
			xyzChars = new ArrayList<Character>();
			xyzChars.addAll(x.getChars());
			xyzChars.addAll(y.getChars());
			xyzChars.addAll(z.getChars());
			
			xyz = new AlphaString(alphabet, xyzChars);
			
			System.out.println("		"+reg.displayable()+ " accepts("+xyz.displayable()+") == " + reg.accepts(xyz));
			
			for(Character c : origY.getChars()){
				y.addChar(c);
				
			}
			
		}
		
		x = new AlphaString(alphabet, new ArrayList<Character>(Arrays.asList(alphabet.get(0))));
		y = new AlphaString(alphabet, new ArrayList<Character>(Arrays.asList(alphabet.get(0), alphabet.get(1))));
		z = new AlphaString(alphabet, new ArrayList<Character>(Arrays.asList(alphabet.get(1))));
		
		System.out.println("\n    regOdd:");
		System.out.println("	x = '" + x.displayable()+"'");
		System.out.println("	y = '" + y.displayable()+"'");
		System.out.println("	z = '" + z.displayable()+"'");
		
		origY = new AlphaString(y.getAlphabet(), new ArrayList<Character>(y.getChars()));
		
		for (int i = 0; i<20; i++) {
			xyzChars = new ArrayList<Character>();
			xyzChars.addAll(x.getChars());
			xyzChars.addAll(y.getChars());
			xyzChars.addAll(z.getChars());
			
			xyz = new AlphaString(alphabet, xyzChars);
			
			System.out.println("		"+reg.displayable()+ " accepts("+xyz.displayable()+") == " + reg.accepts(xyz));
			
			for(Character c : origY.getChars()){
				y.addChar(c);
				
			}
			
		}
		
		reg = regEven;
		alphabet = reg.getAlphabet();
		
		x = new AlphaString(alphabet, new ArrayList<Character>(Arrays.asList(alphabet.get(1))));
		y = new AlphaString(alphabet, new ArrayList<Character>(Arrays.asList(alphabet.get(0), alphabet.get(1))));
		z = new AlphaString(alphabet, new ArrayList<Character>(Arrays.asList(alphabet.get(0))));
		
		System.out.println("\n    regEven:");
		System.out.println("	x = '" + x.displayable()+"'");
		System.out.println("	y = '" + y.displayable()+"'");
		System.out.println("	z = '" + z.displayable()+"'");
		
		origY = new AlphaString(y.getAlphabet(), new ArrayList<Character>(y.getChars()));
		
		for (int i = 0; i<20; i++) {
			xyzChars = new ArrayList<Character>();
			xyzChars.addAll(x.getChars());
			xyzChars.addAll(y.getChars());
			xyzChars.addAll(z.getChars());
			
			xyz = new AlphaString(alphabet, xyzChars);
			
			System.out.println("		"+reg.displayable()+ " accepts("+xyz.displayable()+") == " + reg.accepts(xyz));
			
			for(Character c : origY.getChars()){
				y.addChar(c);
				
			}
			
		}
		
		x = new AlphaString(alphabet, new ArrayList<Character>(Arrays.asList(alphabet.get(0), alphabet.get(1))));
		y = new AlphaString(alphabet, new ArrayList<Character>(Arrays.asList(alphabet.get(1), alphabet.get(1))));
		z = new AlphaString(alphabet, new ArrayList<Character>(Arrays.asList(alphabet.get(0))));
		
		System.out.println("	reEven:");
		System.out.println("	x = '" + x.displayable()+"'");
		System.out.println("	y = '" + y.displayable()+"'");
		System.out.println("	z = '" + z.displayable()+"'");
		
		origY = new AlphaString(y.getAlphabet(), new ArrayList<Character>(y.getChars()));
		
		for (int i = 0; i<20; i++) {
			xyzChars = new ArrayList<Character>();
			xyzChars.addAll(x.getChars());
			xyzChars.addAll(y.getChars());
			xyzChars.addAll(z.getChars());
			
			xyz = new AlphaString(alphabet, xyzChars);
			
			System.out.println("		"+reg.displayable()+ " accepts("+xyz.displayable()+") == " + reg.accepts(xyz));
			
			for(Character c : origY.getChars()){
				y.addChar(c);
				
			}
			
		}
		
		reg = regABC;
		alphabet = reg.getAlphabet();
		
		x = new AlphaString(alphabet, new ArrayList<Character>(Arrays.asList(alphabet.get(1))));
		y = new AlphaString(alphabet, new ArrayList<Character>(Arrays.asList(alphabet.get(0), alphabet.get(2))));
		z = new AlphaString(alphabet, new ArrayList<Character>(Arrays.asList(alphabet.get(1))));
		
		System.out.println("\n    regABC:");
		System.out.println("	x = '" + x.displayable()+"'");
		System.out.println("	y = '" + y.displayable()+"'");
		System.out.println("	z = '" + z.displayable()+"'");
		
		origY = new AlphaString(y.getAlphabet(), new ArrayList<Character>(y.getChars()));
		
		for (int i = 0; i<20; i++) {
			xyzChars = new ArrayList<Character>();
			xyzChars.addAll(x.getChars());
			xyzChars.addAll(y.getChars());
			xyzChars.addAll(z.getChars());
			
			xyz = new AlphaString(alphabet, xyzChars);
			
			System.out.println("		"+reg.displayable()+ " accepts("+xyz.displayable()+") == " + reg.accepts(xyz));
			
			for(Character c : origY.getChars()){
				y.addChar(c);
				
			}
			
		}
		
		x = new AlphaString(alphabet, new ArrayList<Character>(Arrays.asList(alphabet.get(2), alphabet.get(1))));
		y = new AlphaString(alphabet, new ArrayList<Character>(Arrays.asList(alphabet.get(1), alphabet.get(1))));
		z = new AlphaString(alphabet, new ArrayList<Character>(Arrays.asList(alphabet.get(0), alphabet.get(0), alphabet.get(2))));
		
		System.out.println("	reABC:");
		System.out.println("	x = '" + x.displayable()+"'");
		System.out.println("	y = '" + y.displayable()+"'");
		System.out.println("	z = '" + z.displayable()+"'");
		
		origY = new AlphaString(y.getAlphabet(), new ArrayList<Character>(y.getChars()));
		
		for (int i = 0; i<20; i++) {
			xyzChars = new ArrayList<Character>();
			xyzChars.addAll(x.getChars());
			xyzChars.addAll(y.getChars());
			xyzChars.addAll(z.getChars());
			
			xyz = new AlphaString(alphabet, xyzChars);
			
			System.out.println("		"+reg.displayable()+ " accepts("+xyz.displayable()+") == " + reg.accepts(xyz));
			
			for(Character c : origY.getChars()){
				y.addChar(c);
				
			}
			
		}
		
		reg = regOddEven;
		alphabet = reg.getAlphabet();
		
		x = new AlphaString(alphabet, new ArrayList<Character>(Arrays.asList(alphabet.get(1))));
		y = new AlphaString(alphabet, new ArrayList<Character>(Arrays.asList(alphabet.get(1), alphabet.get(1), alphabet.get(0))));
		z = new AlphaString(alphabet, new ArrayList<Character>(Arrays.asList(alphabet.get(0))));
		
		System.out.println("\n    regOddEven:");
		System.out.println("	x = '" + x.displayable()+"'");
		System.out.println("	y = '" + y.displayable()+"'");
		System.out.println("	z = '" + z.displayable()+"'");
		
		origY = new AlphaString(y.getAlphabet(), new ArrayList<Character>(y.getChars()));
		
		for (int i = 0; i<20; i++) {
			xyzChars = new ArrayList<Character>();
			xyzChars.addAll(x.getChars());
			xyzChars.addAll(y.getChars());
			xyzChars.addAll(z.getChars());
			
			xyz = new AlphaString(alphabet, xyzChars);
			
			System.out.println("		"+reg.displayable()+ " accepts("+xyz.displayable()+") == " + reg.accepts(xyz));
			
			for(Character c : origY.getChars()){
				y.addChar(c);
				
			}
			
		}
		
		x = new AlphaString(alphabet, new ArrayList<Character>(Arrays.asList(alphabet.get(0), alphabet.get(0))));
		y = new AlphaString(alphabet, new ArrayList<Character>(Arrays.asList(alphabet.get(1), alphabet.get(1))));
		z = new AlphaString(alphabet, new ArrayList<Character>(Arrays.asList(alphabet.get(0))));
		
		System.out.println("\n    regOddEven:");
		System.out.println("	x = '" + x.displayable()+"'");
		System.out.println("	y = '" + y.displayable()+"'");
		System.out.println("	z = '" + z.displayable()+"'");
		
		origY = new AlphaString(y.getAlphabet(), new ArrayList<Character>(y.getChars()));
		
		for (int i = 0; i<20; i++) {
			xyzChars = new ArrayList<Character>();
			xyzChars.addAll(x.getChars());
			xyzChars.addAll(y.getChars());
			xyzChars.addAll(z.getChars());
			
			xyz = new AlphaString(alphabet, xyzChars);
			
			System.out.println("		"+reg.displayable()+ " accepts("+xyz.displayable()+") == " + reg.accepts(xyz));
			
			for(Character c : origY.getChars()){
				y.addChar(c);
				
			}
			
		}
		
		reg = regZStarOStar;
		alphabet = reg.getAlphabet();
		
		x = new AlphaString(alphabet, new ArrayList<Character>(Arrays.asList(alphabet.get(0))));
		y = new AlphaString(alphabet, new ArrayList<Character>(Arrays.asList(alphabet.get(0), alphabet.get(0), alphabet.get(0))));
		z = new AlphaString(alphabet, new ArrayList<Character>(Arrays.asList(alphabet.get(1))));
		
		System.out.println("\n    regZStarOStar:");
		System.out.println("	x = '" + x.displayable()+"'");
		System.out.println("	y = '" + y.displayable()+"'");
		System.out.println("	z = '" + z.displayable()+"'");
		
		origY = new AlphaString(y.getAlphabet(), new ArrayList<Character>(y.getChars()));
		
		for (int i = 0; i<20; i++) {
			xyzChars = new ArrayList<Character>();
			xyzChars.addAll(x.getChars());
			xyzChars.addAll(y.getChars());
			xyzChars.addAll(z.getChars());
			
			xyz = new AlphaString(alphabet, xyzChars);
			
			System.out.println("		"+reg.displayable()+ " accepts("+xyz.displayable()+") == " + reg.accepts(xyz));
			
			for(Character c : origY.getChars()){
				y.addChar(c);
				
			}
			
		}
		
		x = new AlphaString(alphabet, new ArrayList<Character>(Arrays.asList(alphabet.get(0), alphabet.get(0))));
		y = new AlphaString(alphabet, new ArrayList<Character>(Arrays.asList(alphabet.get(1))));
		z = new AlphaString(alphabet, new ArrayList<Character>(Arrays.asList(alphabet.get(1))));
		
		System.out.println("\n    regZStarOStar:");
		System.out.println("	x = '" + x.displayable()+"'");
		System.out.println("	y = '" + y.displayable()+"'");
		System.out.println("	z = '" + z.displayable()+"'");
		
		origY = new AlphaString(y.getAlphabet(), new ArrayList<Character>(y.getChars()));
		
		for (int i = 0; i<20; i++) {
			xyzChars = new ArrayList<Character>();
			xyzChars.addAll(x.getChars());
			xyzChars.addAll(y.getChars());
			xyzChars.addAll(z.getChars());
			
			xyz = new AlphaString(alphabet, xyzChars);
			
			System.out.println("		"+reg.displayable()+ " accepts("+xyz.displayable()+") == " + reg.accepts(xyz));
			
			for(Character c : origY.getChars()){
				y.addChar(c);
				
			}
			
		}
		
		reg = regAll;
		alphabet = reg.getAlphabet();
		
		x = new AlphaString(alphabet, new ArrayList<Character>(Arrays.asList(alphabet.get(0))));
		y = new AlphaString(alphabet, new ArrayList<Character>(Arrays.asList(alphabet.get(0), alphabet.get(0))));
		z = new AlphaString(alphabet, new ArrayList<Character>(Arrays.asList(alphabet.get(0))));
		
		System.out.println("\n    regAll:");
		System.out.println("	x = '" + x.displayable()+"'");
		System.out.println("	y = '" + y.displayable()+"'");
		System.out.println("	z = '" + z.displayable()+"'");
		
		origY = new AlphaString(y.getAlphabet(), new ArrayList<Character>(y.getChars()));
		
		for (int i = 0; i<20; i++) {
			xyzChars = new ArrayList<Character>();
			xyzChars.addAll(x.getChars());
			xyzChars.addAll(y.getChars());
			xyzChars.addAll(z.getChars());
			
			xyz = new AlphaString(alphabet, xyzChars);
			
			System.out.println("		"+reg.displayable()+ " accepts("+xyz.displayable()+") == " + reg.accepts(xyz));
			
			for(Character c : origY.getChars()){
				y.addChar(c);
				
			}
			
		}
		
		x = new AlphaString(alphabet, new ArrayList<Character>(Arrays.asList(alphabet.get(0))));
		y = new AlphaString(alphabet, new ArrayList<Character>(Arrays.asList(alphabet.get(1))));
		z = new AlphaString(alphabet, new ArrayList<Character>(Arrays.asList(alphabet.get(1))));
		
		System.out.println("\n    regAll:");
		System.out.println("	x = '" + x.displayable()+"'");
		System.out.println("	y = '" + y.displayable()+"'");
		System.out.println("	z = '" + z.displayable()+"'");
		
		origY = new AlphaString(y.getAlphabet(), new ArrayList<Character>(y.getChars()));
		
		for (int i = 0; i<20; i++) {
			xyzChars = new ArrayList<Character>();
			xyzChars.addAll(x.getChars());
			xyzChars.addAll(y.getChars());
			xyzChars.addAll(z.getChars());
			
			xyz = new AlphaString(alphabet, xyzChars);
			
			System.out.println("		"+reg.displayable()+ " accepts("+xyz.displayable()+") == " + reg.accepts(xyz));
			
			for(Character c : origY.getChars()){
				y.addChar(c);
				
			}
			
		}
		
		//Pumper and Repumper tests
		System.out.println("\nPumper and Repumper function tests:");
		
		AlphaString string;
		Trace trace;
		AlphaString[] pumped;
		DFA subDFA;
		
		//+++++++++++
		dfa = oddDFA;
		alphabet = dfa.getAlphabet();
		
		string = new AlphaString(alphabet, new ArrayList<Character>(Arrays.asList(alphabet.get(0), alphabet.get(1), alphabet.get(1), alphabet.get(1))));
		dfa.run(string);
		
		trace = new Trace(dfa.getTrace().getStates());
		
		pumped = pumper(dfa, string, trace);
		
		System.out.println("\n    oddDFA and string '" + string.displayable() +"': '" + pumped[0].displayable() + "' + ('" + pumped[1].displayable()+ "'^i) + '" + pumped[2].displayable()+"' ");
		
		subDFA = rePumper(pumped[0], pumped[1], pumped[2]);
		System.out.println("    Resulting DFA is subset of original DFA?: " + func.subset(subDFA, dfa));
		
		//---
		
		string = new AlphaString(alphabet, new ArrayList<Character>(Arrays.asList(alphabet.get(0), alphabet.get(0), alphabet.get(0), alphabet.get(1))));
		dfa.run(string);
		
		trace = new Trace(dfa.getTrace().getStates());
		
		pumped = pumper(dfa, string, trace);
		
		System.out.println("\n    oddDFA and string '" + string.displayable() +"': '" + pumped[0].displayable() + "' + ('" + pumped[1].displayable()+ "'^i) + '" + pumped[2].displayable()+"' ");
		
		subDFA = rePumper(pumped[0], pumped[1], pumped[2]);
		System.out.println("    Resulting DFA is subset of original DFA?: " + func.subset(subDFA, dfa));
		
		//+++++++++++
		dfa = abDFA;
		alphabet = dfa.getAlphabet();
		
		string = new AlphaString(alphabet, new ArrayList<Character>(Arrays.asList(alphabet.get(2), alphabet.get(2), alphabet.get(0), alphabet.get(1))));
		dfa.run(string);
		
		trace = new Trace(dfa.getTrace().getStates());
		
		pumped = pumper(dfa, string, trace);
		
		System.out.println("\n    abDFA and string '" + string.displayable() +"': '" + pumped[0].displayable() + "' + ('" + pumped[1].displayable()+ "'^i) + '" + pumped[2].displayable()+"' ");
		
		subDFA = rePumper(pumped[0], pumped[1], pumped[2]);
		System.out.println("    Resulting DFA is subset of original DFA?: " + func.subset(subDFA, dfa));
		
		//---
		
		string = new AlphaString(alphabet, new ArrayList<Character>(Arrays.asList(alphabet.get(2), alphabet.get(0), alphabet.get(1), alphabet.get(2))));
		dfa.run(string);
		
		trace = new Trace(dfa.getTrace().getStates());
		
		pumped = pumper(dfa, string, trace);
		
		System.out.println("\n    abdDFA and string '" + string.displayable() +"': '" + pumped[0].displayable() + "' + ('" + pumped[1].displayable()+ "'^i) + '" + pumped[2].displayable()+"' ");
		
		subDFA = rePumper(pumped[0], pumped[1], pumped[2]);
		System.out.println("    Resulting DFA is subset of original DFA?: " + func.subset(subDFA, dfa));
		
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
			
			if(temp.getReg1().isConcat() && temp.getReg2().isConcat()) {
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
			
			if(regEqual(temp.getReg2(), new RegEpsilon(reg.getAlphabet()))) {																	 //a o [epsilon] -> a
				return simplify(temp.getReg1());
				
			}else if(regEqual(temp.getReg1(), new RegEpsilon(reg.getAlphabet())) && !regEqual(temp.getReg2(), new RegEpsilon(reg.getAlphabet()))) {	//[epsilon] o b -> b
				return simplify(temp.getReg2());
				
			}
			
			return new RegConcat(simplify(temp.getReg1()), simplify(temp.getReg2()));
			
		//If the reg is *, simplify its reg or epsilon if reg is equal to epsilon
		}else if(reg.isStar()) {
			RegStar temp = (RegStar) reg;
			
			return new RegStar(simplify(temp.getReg()));
			
		}
		
		//If the reg is empty, char, or epsilon, cannot simplify any further
		return reg;
		
	}
	
	public static RegEx dfaToReg(DFA dfa) {
		GNFA gnfa = dfaToGNFA(dfa);
	
		GNFA ripped = rip(gnfa);
		
		return simplify(ripped.findReg(ripped.gatStartState(), ripped.getAcceptingState()));
		
	}
	
	//Reduce a GNFA with k>2 states to an equivalent one with k-1 states until k == 2
	//The GNFA will be non-functional, but it does not have to be for this purpose
	public static GNFA rip(GNFA gnfa) {
		
		if(gnfa.getStates().size() == 2) {
			return gnfa;
			
		}
		
		ArrayList<State> ripStates = new ArrayList<State>();
		
		Alphabet alphabet = gnfa.getAlphabet();
		
		ArrayList<RegEx> ripNextRegs = new ArrayList<RegEx>();
		
		ArrayList<RegEx> tempRegs = new ArrayList<RegEx>();
		
		ripStates.add(new State(gnfa.getStates().get(0).getIdentifier()));
		
		//Remove the second state
		for(int i = 2; i < gnfa.getStates().size(); i++) {
			ripStates.add(new State(gnfa.getStates().get(i).getIdentifier()));
			
		}
		
		//Because the gnfa is converted from a trivial nfa converted from a dfa, , we know
		State ripStart = ripStates.get(0);											//The first state is the starting state
		
		State ripAccept = ripStates.get(ripStates.size()-1);						//and the last state is the accepting state
				
		
		
		//Find the concatenations in the form of reg(qi, [q removed]) o reg([q removed], [q removed])* o reg([q removed]. qj)

		tempRegs.add(regCopy(new RegConcat(new RegConcat(gnfa.findReg(gnfa.getStates().get(0), gnfa.getStates().get(1)), new RegStar(gnfa.findReg(gnfa.getStates().get(1), gnfa.getStates().get(1)))), gnfa.findReg(gnfa.getStates().get(1), gnfa.getStates().get(0)))));

		for(int j = 2; j < gnfa.getStates().size(); j++) {
			tempRegs.add(regCopy(new RegConcat(new RegConcat(gnfa.findReg(gnfa.getStates().get(0), gnfa.getStates().get(1)), new RegStar(gnfa.findReg(gnfa.getStates().get(1), gnfa.getStates().get(1)))), gnfa.findReg(gnfa.getStates().get(1), gnfa.getStates().get(j)))));

		}
		
		for(int i = 2; i < gnfa.getStates().size(); i++) {
			tempRegs.add(regCopy(new RegConcat(new RegConcat(gnfa.findReg(gnfa.getStates().get(i), gnfa.getStates().get(1)), new RegStar(gnfa.findReg(gnfa.getStates().get(1), gnfa.getStates().get(1)))), gnfa.findReg(gnfa.getStates().get(1), gnfa.getStates().get(0)))));

			for(int j = 2; j < gnfa.getStates().size(); j++) {
				tempRegs.add(regCopy(new RegConcat(new RegConcat(gnfa.findReg(gnfa.getStates().get(i), gnfa.getStates().get(1)), new RegStar(gnfa.findReg(gnfa.getStates().get(1), gnfa.getStates().get(1)))), gnfa.findReg(gnfa.getStates().get(1), gnfa.getStates().get(j)))));

			}
		}
		

		
		//Add those connections
		
		ripNextRegs.add(regCopy(gnfa.findReg(gnfa.getStates().get(0), gnfa.getStates().get(0))));													//Add regs that mirror the functionality of the removed reg
		ripNextRegs.set(ripNextRegs.size()-1, simplify(new RegUnion(ripNextRegs.get(ripNextRegs.size()-1), tempRegs.get(ripNextRegs.size()-1))));	//reg(qi, qj) --> reg(reg(qi, qj) u (reg(qi, [q removed]) o reg([q removed], qj)))
																																					
		for(int j = 2; j < gnfa.getStates().size(); j++) {
			ripNextRegs.add(regCopy(gnfa.findReg(gnfa.getStates().get(0), gnfa.getStates().get(j))));
			ripNextRegs.set(ripNextRegs.size()-1, simplify(new RegUnion(ripNextRegs.get(ripNextRegs.size()-1), tempRegs.get(ripNextRegs.size()-1))));	
			
		}
		
		for(int i = 2; i < gnfa.getStates().size(); i++) {
			ripNextRegs.add(regCopy(gnfa.findReg(gnfa.getStates().get(i), gnfa.getStates().get(0))));
			ripNextRegs.set(ripNextRegs.size()-1, simplify(new RegUnion(ripNextRegs.get(ripNextRegs.size()-1), tempRegs.get(ripNextRegs.size()-1))));	
			
			for(int j = 2; j < gnfa.getStates().size(); j++) {	
				ripNextRegs.add(regCopy(gnfa.findReg(gnfa.getStates().get(i), gnfa.getStates().get(j))));
				ripNextRegs.set(ripNextRegs.size()-1, simplify(new RegUnion(ripNextRegs .get(ripNextRegs.size()-1), tempRegs.get(ripNextRegs.size()-1))));
				
			}
			
		}
		
		return rip(new GNFA(ripStates, alphabet, ripStart, ripNextRegs, ripAccept, NFAFunctions.epsilon));
		
	}
	
	//Return an equivalent but separate reg
	public static RegEx regCopy(RegEx reg) {
		if(reg.isChar()) {
			RegChar tempChar;
			tempChar = (RegChar)reg;
			
			return new RegChar(tempChar.getCharacter(), reg.getAlphabet());
			
		}else if(reg.isEpsilon()) {
			
			
			return new RegEpsilon(reg.getAlphabet());
			
		}else if(reg.isEmpty()) {
			return new RegEmpty(reg.getAlphabet());
			
		}else if(reg.isStar()) {
			RegStar tempStar;
			tempStar = (RegStar)reg;
			
			return new RegStar(regCopy(tempStar.getReg()));
			
		}else if(reg.isUnion()) {
			RegUnion tempUnion;
			tempUnion = (RegUnion)reg;
			
			return new RegUnion(regCopy(tempUnion.getReg1()), regCopy(tempUnion.getReg2()));
			
		}else if(reg.isConcat()) {
			RegConcat tempConcat;
			tempConcat = (RegConcat)reg;
			
			return new RegConcat(regCopy(tempConcat.getReg1()), (regCopy(tempConcat.getReg2())));
			
		}
		
		return reg;
		
	}

	//Convert a dfa to a GNFA
	public static GNFA dfaToGNFA(DFA dfa) {
		NFA nfa = func.dfaToNFA(dfa);
		
		StateTable stateTable = nfa.getStateTable();
		
		ArrayList<State> gnfaStates = new ArrayList<State>();
		
		gnfaStates.addAll(nfa.getStates());
		
		Alphabet alphabet = nfa.getAlphabet();
		
		State gnfaStart = gnfaStates.get(0);
		State gnfaAccept = gnfaStates.get(gnfaStates.size()-1);
		
		ArrayList<RegEx> nextRegs = new ArrayList<RegEx>();
		
		for(int i = 0; i < gnfaStates.size(); i++) {
			for(int j = 0; j < gnfaStates.size(); j++) {
				nextRegs.add(new RegEmpty(alphabet));
				
			}
		}
		
		Character c;
		int index; 
		
		for(int i = 0; i < gnfaStates.size(); i++) {
			for(int j = 0; j < gnfaStates.size(); j++) {
				for(int k = 0; k < alphabet.size() + 1; k++) {
					if(k < alphabet.size()) {
						c = alphabet.get(k);
						
						if(nfa.findNextStates(nfa.getStates().get(i), alphabet.get(k)).contains(nfa.getStates().get(j))) {
							//System.out.println(c.displayable());
							index = i*gnfaStates.size() + j;
							nextRegs.set(index, new RegUnion(new RegChar(c, alphabet), nextRegs.get(index)));
							
						}
							
					}else {
						if(nfa.findNextStates(nfa.getStates().get(i), NFAFunctions.epsilon).contains(nfa.getStates().get(j))) {
							index = i*gnfaStates.size() + j;
							nextRegs.set(index, new RegUnion(new RegEpsilon(alphabet), nextRegs.get(index)));
							
						}
						
					}
					
				}
				
			}
			
		}
		
		return new GNFA (gnfaStates, alphabet, gnfaStart, nextRegs, gnfaAccept, NFAFunctions.epsilon);
		
	}
	
	public static void eqPrint(RegEx reg1, String acc, RegEx reg2) {
		System.out.println("    " + reg1.displayable() + acc + reg2.displayable() + "\n");
		
	}
	
	//Given a DFA, a string, and an accepting trace, return xxyz
	public static AlphaString[] pumper(DFA dfa, AlphaString string, Trace acceptingTrace) {
		if(dfa.getAcceptingStates().contains(acceptingTrace.get(acceptingTrace.size()-1)) && string.getAlphabet() == dfa.getAlphabet() && string.length() == acceptingTrace.size()-1) {
			AlphaString[] xyz = new AlphaString[3];
			
			ArrayList<ArrayList<AlphaString>> groupings = func.genGroupings(string);
			
			ArrayList<ArrayList<AlphaString>> tpGroupings = new ArrayList<ArrayList<AlphaString>>();
			
			ArrayList<AlphaString> tempGrouping;
			
			State curState;
			
			// fimd possible xyz's where |x| >= 0, |y| >= 1, |z| >= 0 and |xyz| == |string|
			for(ArrayList<AlphaString> grouping : groupings) {
				if(grouping.size() == 3) {
					tpGroupings.add(grouping);
					
				}else if(grouping.size() == 2) {
					tempGrouping = new ArrayList<AlphaString>(Arrays.asList(new AlphaString(string.getAlphabet())));
					tempGrouping.add(grouping.get(0));
					tempGrouping.add(grouping.get(1));
					
					tpGroupings.add(new ArrayList<AlphaString>(tempGrouping));
					
					tempGrouping = new ArrayList<AlphaString>(Arrays.asList(grouping.get(1)));
					tempGrouping.add(grouping.get(1));
					tempGrouping.add(new AlphaString(string.getAlphabet()));
					
					tpGroupings.add(new ArrayList<AlphaString>(tempGrouping));
					
				}else if(grouping.size() == 1) {
					tempGrouping = new ArrayList<AlphaString>(Arrays.asList(new AlphaString(string.getAlphabet())));
					tempGrouping.add(grouping.get(0));
					tempGrouping.add(new AlphaString(string.getAlphabet()));
					
					tpGroupings.add(new ArrayList<AlphaString>(tempGrouping));
					
				}
				
			}
			

			AlphaString x;
			AlphaString y;
			AlphaString z;
			
			boolean loop;
			int traceIndex;
			boolean stop;
			
			for(int i = tpGroupings.size() - 1 ; i >= 0 ; i--) {
				x = tpGroupings.get(i).get(0);
				y = tpGroupings.get(i).get(1);
				z = tpGroupings.get(i).get(2);
				
				loop = false;
				stop = false;
				
				traceIndex = 0;
				
				curState = dfa.getStartState();
				
				for(Character character : x.getChars()){					//See if string x matches trace
					curState = dfa.findNextState(curState, character);
					traceIndex++;
					
					if(curState != acceptingTrace.get(traceIndex)) {
						stop = true;
						break;
						
					}
					
				}
				
				if(stop) {
					break;
					
				}
				
				for(Character character : y.getChars()){					//See if string y matches trace
					curState = dfa.findNextState(curState, character);
					traceIndex++;
					
					if(curState != acceptingTrace.get(traceIndex)) {
						stop = true;
						break;
						
					}
					
				}
				
				if(stop) {
					break;
					
				}
				
				State loopState = curState;
				
				for(Character character : y.getChars()){					//See if y's segment of the trace loops (dfa accepts xyz -> dfa accepts x(y^i)x
					loopState = dfa.findNextState(loopState, character);
					
				}
				
				if(loopState != curState) {
					break;
					
				}
				
				for(Character character : z.getChars()){					//see if string z matches string
					curState = dfa.findNextState(curState, character);
					traceIndex++;
					
					if(curState != acceptingTrace.get(traceIndex)) {
						stop = true;
						break;
						
					}
					
				}
				
				if(stop) {
					break;
					
				}
				
				xyz[0] = x;
				xyz[1] = y;
				xyz[2] = z;
				
				return xyz;													//return x, y, z where xyz == string
				
			}
			
		}
		
		return null;
		
	}
	
	public static DFA rePumper(AlphaString x, AlphaString y, AlphaString z) {
		return func.nfaToDFA((new RegConcat(regString(x), new RegConcat(new RegConcat(new RegStar(regString(y)), regString(y)), regString(z))).compile()));
		
	}
	
	public static RegEx regString(AlphaString string) {
		Alphabet alphabet = string.getAlphabet();
		
		switch(string.length()) {
			case 0:
				return new RegEpsilon(alphabet);
				
			case 1:
				return new RegChar(string.getChar(0), alphabet);
				
			case 2:
				return new RegConcat(new RegChar(string.getChar(0), alphabet), new RegChar(string.getChar(1), alphabet));
			default:
				RegEx regString = new RegConcat(new RegChar(string.getChar(0), alphabet), new RegChar(string.getChar(1), alphabet));
				
				for(int i = 2; i < string.length(); i++) {
					regString = new RegConcat(regCopy(regString), new RegChar(string.getChar(i), alphabet));
				}
				
				return regString;
				
		}
		
	}
	
}
