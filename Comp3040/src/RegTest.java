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
			
			if(temp.getReg1().isStar() && temp.getReg2().isStar()) {																				//(a)* u (b)* -> (a u b)*				
				RegStar subTemp1 = (RegStar)temp.getReg1();
				RegStar subTemp2 = (RegStar)temp.getReg2();
				return new RegStar(new RegUnion(simplify(subTemp1.getReg()), simplify(subTemp2.getReg())));
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
			
			if(regEqual(temp.getReg(), new RegEpsilon(reg.getAlphabet()))) {
				return new RegEpsilon(reg.getAlphabet());
				
			}
			
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
		
		return null;
		
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
	
}
