import java.util.ArrayList;
import java.util.Arrays;

public class NFATest {
	
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
	
	//Strings used to test NFA's--------------------------------------------------------------------------------------------
		private static AlphaString testStrings[] = new AlphaString[]{
				new AlphaString(biAlphabet),																														//[epsilon]
				new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(0)))),															//0
			    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(0), biAlphabet.get(1)))),											//01
			    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(0), biAlphabet.get(1), biAlphabet.get(0)))),						//010
			    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(0), biAlphabet.get(1)))),						//011
			    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(0), biAlphabet.get(0)))),						//100
			    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(1), biAlphabet.get(1)))),						//111
			    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(1), biAlphabet.get(0)))),						//110
			    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(0), biAlphabet.get(0), biAlphabet.get(1)))),	//1001
			    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(0), biAlphabet.get(0), biAlphabet.get(0)))),	//1000
			    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(0), biAlphabet.get(1), biAlphabet.get(1)))),	//1011
			    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(0), biAlphabet.get(1), biAlphabet.get(0), biAlphabet.get(1)))),	//0101
			    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(0), biAlphabet.get(0), biAlphabet.get(0), biAlphabet.get(0)))),	//0000
			    
		};
	//----------------------------------------------------------------------------------------------------------------------
		
	//NFA that accepts odd binary #s (The "oddNFA")-------------------------------------------------------------------------
		private static ArrayList<State> oddNFAStates = new ArrayList<State>(Arrays.asList(new State("odd0"), new State("odd1")));						//Q = {odd0, odd1}
		
		private static State oddNFAStartState = oddNFAStates.get(0);																			//q0 = odd0
		
		private static ArrayList<ArrayList<State>>oddNFANextStates = new ArrayList<ArrayList<State>>(Arrays.asList(
																									 newList( oddNFAStates.get(0) ), newList( oddNFAStates.get(0), oddNFAStates.get(1) ), newList(),	//Delta = {(odd0, '0', {odd0}), (odd0, '1', {odd0, odd1}), (odd0, epsilon, {})
																									 newList(), newList(), newList())																//        	   (odd1, '0', {}), (odd1, '1', {}), (odd1, epsilon, {})}
																									);	
																									
		
		private static ArrayList<State>oddNFAAcceptingStates = new ArrayList<State>(Arrays.asList(oddNFAStates.get(1)));						//F = {odd1}
		
		private static NFA oddNFA = new NFA(oddNFAStates, biAlphabet,oddNFAStartState,oddNFANextStates,oddNFAAcceptingStates, epsilon);
		
		private static Trace oddTestTraces[] = new Trace[] {
				new Trace(new ArrayList<State>(Arrays.asList(oddNFAStates.get(0)))),
				new Trace(new ArrayList<State>(Arrays.asList(oddNFAStates.get(1)))),
				new Trace(new ArrayList<State>(Arrays.asList(oddNFAStates.get(0), oddNFAStates.get(0)))),
				new Trace(new ArrayList<State>(Arrays.asList(oddNFAStates.get(0), oddNFAStates.get(1)))),
				new Trace(new ArrayList<State>(Arrays.asList(oddNFAStates.get(0), oddNFAStates.get(0), oddNFAStates.get(0)))),
				new Trace(new ArrayList<State>(Arrays.asList(oddNFAStates.get(0), oddNFAStates.get(0), oddNFAStates.get(1)))),
				new Trace(new ArrayList<State>(Arrays.asList(oddNFAStates.get(0), oddNFAStates.get(0), oddNFAStates.get(0), oddNFAStates.get(0)))),
				new Trace(new ArrayList<State>(Arrays.asList(oddNFAStates.get(0), oddNFAStates.get(0), oddNFAStates.get(0), oddNFAStates.get(1)))),
				new Trace(new ArrayList<State>(Arrays.asList(oddNFAStates.get(0), oddNFAStates.get(0), oddNFAStates.get(0), oddNFAStates.get(0), oddNFAStates.get(0)))),
				new Trace(new ArrayList<State>(Arrays.asList(oddNFAStates.get(0), oddNFAStates.get(0), oddNFAStates.get(0), oddNFAStates.get(0), oddNFAStates.get(1)))),
				new Trace(new ArrayList<State>(Arrays.asList(oddNFAStates.get(0), oddNFAStates.get(0), oddNFAStates.get(0), oddNFAStates.get(0), oddNFAStates.get(0), oddNFAStates.get(0)))),
				new Trace(new ArrayList<State>(Arrays.asList(oddNFAStates.get(0), oddNFAStates.get(0), oddNFAStates.get(0), oddNFAStates.get(0), oddNFAStates.get(0), oddNFAStates.get(1))))
				
		};
	//----------------------------------------------------------------------------------------------------------------------
		
	//NFA that accepts even binary #s (The "evenNFA")-----------------------------------------------------------------------
		private static ArrayList<State> evenNFAStates = new ArrayList<State>(Arrays.asList(new State("even0"), new State("even1")));						//Q = {evenO, even1}
		
		private static State evenNFAStartState = evenNFAStates.get(0);																				//q0 = evenO
		
		private static ArrayList<ArrayList<State>>evenNFANextStates = new ArrayList<ArrayList<State>>(Arrays.asList(
																									 newList( evenNFAStates.get(0), evenNFAStates.get(1)), newList( evenNFAStates.get(0)), newList(),	//Delta = {(even0, '0', {even0, even1}), (even0, '1', {even0}), (even0, epsilon, {})
																									 newList(), newList(), newList())																	//         (even1, '0', {}),     (even1, '1', {}),  (even1, epsilon, {})}
																									);	
																									
		
		private static ArrayList<State>evenNFAAcceptingStates = new ArrayList<State>(Arrays.asList(evenNFAStates.get(1)));						//F = {even1}
		
		private static NFA evenNFA = new NFA(evenNFAStates, biAlphabet,evenNFAStartState,evenNFANextStates,evenNFAAcceptingStates, epsilon);
		
		private static Trace evenTestTraces[] = new Trace[] {
				new Trace(new ArrayList<State>(Arrays.asList(evenNFAStates.get(0)))),
				new Trace(new ArrayList<State>(Arrays.asList(evenNFAStates.get(1)))),
				new Trace(new ArrayList<State>(Arrays.asList(evenNFAStates.get(0), evenNFAStates.get(0)))),
				new Trace(new ArrayList<State>(Arrays.asList(evenNFAStates.get(0), evenNFAStates.get(1)))),
				new Trace(new ArrayList<State>(Arrays.asList(evenNFAStates.get(0), evenNFAStates.get(0), evenNFAStates.get(0)))),
				new Trace(new ArrayList<State>(Arrays.asList(evenNFAStates.get(0), evenNFAStates.get(0), evenNFAStates.get(1)))),
				new Trace(new ArrayList<State>(Arrays.asList(evenNFAStates.get(0), evenNFAStates.get(0), evenNFAStates.get(0), evenNFAStates.get(0)))),
				new Trace(new ArrayList<State>(Arrays.asList(evenNFAStates.get(0), evenNFAStates.get(0), evenNFAStates.get(0), evenNFAStates.get(1)))),
				new Trace(new ArrayList<State>(Arrays.asList(evenNFAStates.get(0), evenNFAStates.get(0), evenNFAStates.get(0), evenNFAStates.get(0), evenNFAStates.get(0)))),
				new Trace(new ArrayList<State>(Arrays.asList(evenNFAStates.get(0), evenNFAStates.get(0), evenNFAStates.get(0), evenNFAStates.get(0), evenNFAStates.get(1)))),
				new Trace(new ArrayList<State>(Arrays.asList(evenNFAStates.get(0), evenNFAStates.get(0), evenNFAStates.get(0), evenNFAStates.get(0), evenNFAStates.get(0), evenNFAStates.get(0)))),
				new Trace(new ArrayList<State>(Arrays.asList(evenNFAStates.get(0), evenNFAStates.get(0), evenNFAStates.get(0), evenNFAStates.get(0), evenNFAStates.get(0), evenNFAStates.get(1))))
				
		};
	//----------------------------------------------------------------------------------------------------------------------
	
	//NFA that accepts binary #s of odd length (The "oddLNFA")--------------------------------------------------------------
			private static ArrayList<State> oddLNFAStates = new ArrayList<State>(Arrays.asList(new State("oddL0"), new State("oddL1")));						//Q = {A, B}
			
			private static State oddLNFAStartState = oddLNFAStates.get(0);																			//q0 = A
			
			private static ArrayList<ArrayList<State>>oddLNFANextStates = new ArrayList<ArrayList<State>>(Arrays.asList(
																										 newList(oddLNFAStates.get(1)), newList(oddLNFAStates.get(1)), newList(),						//Delta	= {(oddL0, '0', {oddL1}), (oddL0, '1', {odd1}), (oddL0, epsilon, {})
																										 newList(oddLNFAStates.get(0)), newList(oddLNFAStates.get(0)), newList())						//		   (oddL1, '0', {oddL0}), (oddL1, '1', {oddL0}), (oddL1, epsilon, {})}
																										);	
																										
			
			private static ArrayList<State>oddLNFAAcceptingStates = new ArrayList<State>(Arrays.asList(oddLNFAStates.get(1)));						//F = {oddL1}
			
			private static NFA oddLNFA = new NFA(oddLNFAStates, biAlphabet,oddLNFAStartState,oddLNFANextStates,oddLNFAAcceptingStates, epsilon);
			
			private static Trace oddLTestTraces[] = new Trace[] {
					new Trace(new ArrayList<State>(Arrays.asList(oddNFAStates.get(0)))),
					new Trace(new ArrayList<State>(Arrays.asList(oddNFAStates.get(0), oddNFAStates.get(1)))),
					new Trace(new ArrayList<State>(Arrays.asList(oddNFAStates.get(0), oddNFAStates.get(1), oddNFAStates.get(0)))),
					new Trace(new ArrayList<State>(Arrays.asList(oddNFAStates.get(0), oddNFAStates.get(1), oddNFAStates.get(0), oddNFAStates.get(1)))),
					new Trace(new ArrayList<State>(Arrays.asList(oddNFAStates.get(0), oddNFAStates.get(1), oddNFAStates.get(0), oddNFAStates.get(1), oddNFAStates.get(0)))),
					new Trace(new ArrayList<State>(Arrays.asList(oddNFAStates.get(0), oddNFAStates.get(1), oddNFAStates.get(0), oddNFAStates.get(1), oddNFAStates.get(0), oddNFAStates.get(1)))),
					new Trace(new ArrayList<State>(Arrays.asList(oddNFAStates.get(0), oddNFAStates.get(1), oddNFAStates.get(0), oddNFAStates.get(1), oddNFAStates.get(0), oddNFAStates.get(1), oddNFAStates.get(0)))),
					new Trace(new ArrayList<State>(Arrays.asList(oddNFAStates.get(0), oddNFAStates.get(1), oddNFAStates.get(0), oddNFAStates.get(1), oddNFAStates.get(0), oddNFAStates.get(1), oddNFAStates.get(0), oddNFAStates.get(1)))),
					new Trace(new ArrayList<State>(Arrays.asList(oddNFAStates.get(0), oddNFAStates.get(1), oddNFAStates.get(0), oddNFAStates.get(1), oddNFAStates.get(0), oddNFAStates.get(1), oddNFAStates.get(0), oddNFAStates.get(1), oddNFAStates.get(0)))),
					new Trace(new ArrayList<State>(Arrays.asList(oddNFAStates.get(0), oddNFAStates.get(1), oddNFAStates.get(0), oddNFAStates.get(1), oddNFAStates.get(0), oddNFAStates.get(1), oddNFAStates.get(0), oddNFAStates.get(1), oddNFAStates.get(0), oddNFAStates.get(1)))),
					new Trace(new ArrayList<State>(Arrays.asList(oddNFAStates.get(0), oddNFAStates.get(1), oddNFAStates.get(0), oddNFAStates.get(1), oddNFAStates.get(0), oddNFAStates.get(1), oddNFAStates.get(0), oddNFAStates.get(1), oddNFAStates.get(0), oddNFAStates.get(1), oddNFAStates.get(0)))),
					new Trace(new ArrayList<State>(Arrays.asList(oddNFAStates.get(0), oddNFAStates.get(1), oddNFAStates.get(0), oddNFAStates.get(1), oddNFAStates.get(0), oddNFAStates.get(1), oddNFAStates.get(0), oddNFAStates.get(1), oddNFAStates.get(0), oddNFAStates.get(1), oddNFAStates.get(0), oddNFAStates.get(1)))),
					
			};
	//----------------------------------------------------------------------------------------------------------------------
			
	//NFA that accepts binary #s of even length (The "evenLNFA")-------------------------------------------------------------
			private static ArrayList<State> evenLNFAStates = new ArrayList<State>(Arrays.asList(new State("evenL0"), new State("evenL1")));						//Q = {A, B}
			
			private static State evenLNFAStartState = evenLNFAStates.get(0);																			//q0 = A
			
			private static ArrayList<ArrayList<State>>evenLNFANextStates = new ArrayList<ArrayList<State>>(Arrays.asList(
																										 newList(evenLNFAStates.get(1)), newList(evenLNFAStates.get(1)), newList(),					//Delta	= {(evenL0, '0', {evenL1}), (evenL0, '1', {evenL1}), (evenL0, epsilon, {})
																										 newList(evenLNFAStates.get(0)), newList(evenLNFAStates.get(0)), newList())					//Delta	= {(evenL0, '0', {evenL1}), (evenL0, '1', {evenL1}), (evenL0, epsilon, {})
																										);	
																										
			
			private static ArrayList<State>evenLNFAAcceptingStates = new ArrayList<State>(Arrays.asList(evenLNFAStates.get(0)));						//F = {evenL0}
			
			private static NFA evenLNFA = new NFA(evenLNFAStates, biAlphabet,evenLNFAStartState,evenLNFANextStates,evenLNFAAcceptingStates, epsilon);
			
			private static Trace evenLTestTraces[] = new Trace[] {
					new Trace(new ArrayList<State>(Arrays.asList(evenNFAStates.get(0)))),
					new Trace(new ArrayList<State>(Arrays.asList(evenNFAStates.get(0), evenNFAStates.get(1)))),
					new Trace(new ArrayList<State>(Arrays.asList(evenNFAStates.get(0), evenNFAStates.get(1), evenNFAStates.get(0)))),
					new Trace(new ArrayList<State>(Arrays.asList(evenNFAStates.get(0), evenNFAStates.get(1), evenNFAStates.get(0), evenNFAStates.get(1)))),
					new Trace(new ArrayList<State>(Arrays.asList(evenNFAStates.get(0), evenNFAStates.get(1), evenNFAStates.get(0), evenNFAStates.get(1), evenNFAStates.get(0)))),
					new Trace(new ArrayList<State>(Arrays.asList(evenNFAStates.get(0), evenNFAStates.get(1), evenNFAStates.get(0), evenNFAStates.get(1), evenNFAStates.get(0), evenNFAStates.get(1)))),
					new Trace(new ArrayList<State>(Arrays.asList(evenNFAStates.get(0), evenNFAStates.get(1), evenNFAStates.get(0), evenNFAStates.get(1), evenNFAStates.get(0), evenNFAStates.get(1), evenNFAStates.get(0)))),
					new Trace(new ArrayList<State>(Arrays.asList(evenNFAStates.get(0), evenNFAStates.get(1), evenNFAStates.get(0), evenNFAStates.get(1), evenNFAStates.get(0), evenNFAStates.get(1), evenNFAStates.get(0), evenNFAStates.get(1)))),
					new Trace(new ArrayList<State>(Arrays.asList(evenNFAStates.get(0), evenNFAStates.get(1), evenNFAStates.get(0), evenNFAStates.get(1), evenNFAStates.get(0), evenNFAStates.get(1), evenNFAStates.get(0), evenNFAStates.get(1), evenNFAStates.get(0)))),
					new Trace(new ArrayList<State>(Arrays.asList(evenNFAStates.get(0), evenNFAStates.get(1), evenNFAStates.get(0), evenNFAStates.get(1), evenNFAStates.get(0), evenNFAStates.get(1), evenNFAStates.get(0), evenNFAStates.get(1), evenNFAStates.get(0), evenNFAStates.get(1)))),
					new Trace(new ArrayList<State>(Arrays.asList(evenNFAStates.get(0), evenNFAStates.get(1), evenNFAStates.get(0), evenNFAStates.get(1), evenNFAStates.get(0), evenNFAStates.get(1), evenNFAStates.get(0), evenNFAStates.get(1), evenNFAStates.get(0), evenNFAStates.get(1), evenNFAStates.get(0)))),
					new Trace(new ArrayList<State>(Arrays.asList(evenNFAStates.get(0), evenNFAStates.get(1), evenNFAStates.get(0), evenNFAStates.get(1), evenNFAStates.get(0), evenNFAStates.get(1), evenNFAStates.get(0), evenNFAStates.get(1), evenNFAStates.get(0), evenNFAStates.get(1), evenNFAStates.get(0), evenNFAStates.get(1)))),
					
			};
	//----------------------------------------------------------------------------------------------------------------------


	//NFA that accepts a binary number with a 1 at the 2nd or 3rd position from the end in the string (The "stNFA")-------					//I.E. 1000 or 0100, but not 0010 or 0001
		private static ArrayList<State> stNFAStates = new ArrayList<State>(Arrays.asList(new State("st0"), new State("st1"), new State("st2"), new State("st3")));						//Q = {st0, st1, st2, st3}
		
		private static State stNFAStartState = stNFAStates.get(0);																				//q0 = st0
		
		private static ArrayList<ArrayList<State>>stNFANextStates = new ArrayList<ArrayList<State>>(Arrays.asList(
				 																					 newList(stNFAStates.get(0)), newList(stNFAStates.get(0), stNFAStates.get(1)), newList(),	//Delta = {(st0, '0', {st0}),(st0, 1, {st0, st1}).(st0, epsilon, {})
				 																					 newList(stNFAStates.get(2)), newList(stNFAStates.get(2)), newList(stNFAStates.get(2)),		//		   (st1, '0', {st2}),(st1, '1', {st2}), (st1, epsilon, {st2})
																									 newList(stNFAStates.get(3)), newList(stNFAStates.get(3)), newList(),						//		   (st2, '0', {st3}),(st2, '1', {st3}), (st2, epsilon, {})
																									 newList(), newList(), newList())															//		   (st3, '0', {}), (st3, '1', {}), (st3, epsilon, {})				
																									);	
																									
		
		private static ArrayList<State>stNFAAcceptingStates = new ArrayList<State>(Arrays.asList(stNFAStates.get(3)));						//F = {st3}
		
		private static NFA stNFA = new NFA(stNFAStates, biAlphabet, stNFAStartState,stNFANextStates,stNFAAcceptingStates, epsilon);
		
		private static Trace stTestTraces[] = new Trace[] {
				new Trace(new ArrayList<State>(Arrays.asList(stNFAStates.get(0), stNFAStates.get(1), stNFAStates.get(2), stNFAStates.get(3)))),
				new Trace(new ArrayList<State>(Arrays.asList(stNFAStates.get(0)))),
				new Trace(new ArrayList<State>(Arrays.asList(stNFAStates.get(0), stNFAStates.get(0)))),
				new Trace(new ArrayList<State>(Arrays.asList(stNFAStates.get(0), stNFAStates.get(0), stNFAStates.get(2), stNFAStates.get(3)))),
				new Trace(new ArrayList<State>(Arrays.asList(stNFAStates.get(0), stNFAStates.get(1), stNFAStates.get(2)))),
				new Trace(new ArrayList<State>(Arrays.asList(stNFAStates.get(0), stNFAStates.get(0), stNFAStates.get(0), stNFAStates.get(0)))),
				new Trace(new ArrayList<State>(Arrays.asList(stNFAStates.get(0), stNFAStates.get(2)))),
				new Trace(new ArrayList<State>(Arrays.asList(stNFAStates.get(0), stNFAStates.get(2), stNFAStates.get(3)))),
				new Trace(new ArrayList<State>(Arrays.asList(stNFAStates.get(0), stNFAStates.get(0), stNFAStates.get(2)))),
				new Trace(new ArrayList<State>(Arrays.asList(stNFAStates.get(0), stNFAStates.get(0), stNFAStates.get(0)))),
				new Trace(new ArrayList<State>(Arrays.asList(stNFAStates.get(0), stNFAStates.get(0), stNFAStates.get(0), stNFAStates.get(1), stNFAStates.get(2)))),
				new Trace(new ArrayList<State>(Arrays.asList(stNFAStates.get(0), stNFAStates.get(0), stNFAStates.get(0), stNFAStates.get(1), stNFAStates.get(2), stNFAStates.get(3))))
				
		};
	//----------------------------------------------------------------------------------------------------------------------
	
	//NFA that accepts a binary number that contains 00 (The "zzNFA")-------------------------------------------------------
		private static ArrayList<State> zzNFAStates = new ArrayList<State>(Arrays.asList(new State("zz0"), new State("zz1"), new State("zz2")));						//Q = {zz0, zz1, zz2}
		
		private static State zzNFAStartState = zzNFAStates.get(0);																				//q0 = zz0
		
		private static ArrayList<ArrayList<State>> zzNFANextStates = new ArrayList<ArrayList<State>>(Arrays.asList(
																													newList(zzNFAStates.get(0), zzNFAStates.get(1)), newList(zzNFAStates.get(0)), newList(), 	//Delta = {(zz0, 0 {zz0, zz1}), (zz0, l {zz0}), (zz0, epsilon {})
																													newList(zzNFAStates.get(2)), newList(), newList(),  										//		   (zz1, 0 {zz2}), (zz1, l {}), (zz1, epsilon {})
																													newList(zzNFAStates.get(2)), newList(zzNFAStates.get(2)), newList()							//		   (zz2, 0 {zz2}), (zz2, l {zz2}), (zz2, epsilon {})
																													
																													)
																									);	
																									
		
		private static ArrayList<State> zzNFAAcceptingStates = new ArrayList<State>(Arrays.asList(zzNFAStates.get(2)));						//F = {zz2}
		
		private static NFA zzNFA = new NFA(zzNFAStates, biAlphabet, zzNFAStartState,zzNFANextStates,zzNFAAcceptingStates, epsilon);
		
		private static Trace zzTestTraces[] = new Trace[] {
				new Trace(new ArrayList<State>(Arrays.asList(zzNFAStates.get(0)))),
				new Trace(new ArrayList<State>(Arrays.asList(zzNFAStates.get(0), zzNFAStates.get(0)))),
				new Trace(new ArrayList<State>(Arrays.asList(zzNFAStates.get(0), zzNFAStates.get(1), zzNFAStates.get(2), zzNFAStates.get(2)))),
				new Trace(new ArrayList<State>(Arrays.asList(zzNFAStates.get(0), zzNFAStates.get(0), zzNFAStates.get(0), zzNFAStates.get(1)))),
				new Trace(new ArrayList<State>(Arrays.asList(zzNFAStates.get(0), zzNFAStates.get(0), zzNFAStates.get(0), zzNFAStates.get(0)))),
				new Trace(new ArrayList<State>(Arrays.asList(zzNFAStates.get(0), zzNFAStates.get(0), zzNFAStates.get(1), zzNFAStates.get(2)))),
				new Trace(new ArrayList<State>(Arrays.asList(zzNFAStates.get(0), zzNFAStates.get(0), zzNFAStates.get(1), zzNFAStates.get(2), zzNFAStates.get(2)))),
				new Trace(new ArrayList<State>(Arrays.asList(zzNFAStates.get(0), zzNFAStates.get(0), zzNFAStates.get(0), zzNFAStates.get(1), zzNFAStates.get(2)))),
				new Trace(new ArrayList<State>(Arrays.asList(zzNFAStates.get(0), zzNFAStates.get(0), zzNFAStates.get(0), zzNFAStates.get(0), zzNFAStates.get(0)))),
				new Trace(new ArrayList<State>(Arrays.asList(zzNFAStates.get(0), zzNFAStates.get(0), zzNFAStates.get(0), zzNFAStates.get(0), zzNFAStates.get(0), zzNFAStates.get(0)))),
				new Trace(new ArrayList<State>(Arrays.asList(zzNFAStates.get(0), zzNFAStates.get(0), zzNFAStates.get(0), zzNFAStates.get(0), zzNFAStates.get(0), zzNFAStates.get(0), zzNFAStates.get(0)))),
				new Trace(new ArrayList<State>(Arrays.asList(zzNFAStates.get(0), zzNFAStates.get(0), zzNFAStates.get(0), zzNFAStates.get(0), zzNFAStates.get(0), zzNFAStates.get(0), zzNFAStates.get(0)))),
				
				
		};
	//------------------------------------------------------------------------------------------------------------------
		
	//NFA that accepts a binary number that contains 11 (The "ooNFA")-------------------------------------------------------
			private static ArrayList<State> ooNFAStates = new ArrayList<State>(Arrays.asList(new State("oo0"), new State("oo1"), new State("oo2")));						//Q = {oo0, oo1, oo2}
			
			private static State ooNFAStartState = ooNFAStates.get(0);																				//q0 = oo0
			
			private static ArrayList<ArrayList<State>> ooNFANextStates = new ArrayList<ArrayList<State>>(Arrays.asList(
																														newList(ooNFAStates.get(0)), newList(ooNFAStates.get(0), ooNFAStates.get(1)), newList(), 
																														newList(), newList(ooNFAStates.get(2)), newList(),  
																														newList(ooNFAStates.get(2)), newList(ooNFAStates.get(2)), newList()
					 																					)				
																										);	
																										
			
			private static ArrayList<State> ooNFAAcceptingStates = new ArrayList<State>(Arrays.asList(ooNFAStates.get(2)));						//F = {oo2}
			
			private static NFA ooNFA = new NFA(ooNFAStates, biAlphabet, ooNFAStartState,ooNFANextStates,ooNFAAcceptingStates, epsilon);
			
			private static Trace ooTestTraces[] = new Trace[] {
					new Trace(new ArrayList<State>(Arrays.asList(ooNFAStates.get(0)))),
					new Trace(new ArrayList<State>(Arrays.asList(ooNFAStates.get(0), ooNFAStates.get(0)))),
					new Trace(new ArrayList<State>(Arrays.asList(ooNFAStates.get(0), ooNFAStates.get(1), ooNFAStates.get(2), ooNFAStates.get(2)))),
					new Trace(new ArrayList<State>(Arrays.asList(ooNFAStates.get(0), ooNFAStates.get(0), ooNFAStates.get(0), ooNFAStates.get(1)))),
					new Trace(new ArrayList<State>(Arrays.asList(ooNFAStates.get(0), ooNFAStates.get(0), ooNFAStates.get(0), ooNFAStates.get(0)))),
					new Trace(new ArrayList<State>(Arrays.asList(ooNFAStates.get(0), ooNFAStates.get(0), ooNFAStates.get(1), ooNFAStates.get(2)))),
					new Trace(new ArrayList<State>(Arrays.asList(ooNFAStates.get(0), ooNFAStates.get(0), ooNFAStates.get(1), ooNFAStates.get(2), ooNFAStates.get(2)))),
					new Trace(new ArrayList<State>(Arrays.asList(ooNFAStates.get(0), ooNFAStates.get(0), ooNFAStates.get(0), ooNFAStates.get(1), ooNFAStates.get(2)))),
					new Trace(new ArrayList<State>(Arrays.asList(ooNFAStates.get(0), ooNFAStates.get(0), ooNFAStates.get(0), ooNFAStates.get(0), ooNFAStates.get(0)))),
					new Trace(new ArrayList<State>(Arrays.asList(ooNFAStates.get(0), ooNFAStates.get(0), ooNFAStates.get(0), ooNFAStates.get(0), ooNFAStates.get(0), ooNFAStates.get(0)))),
					new Trace(new ArrayList<State>(Arrays.asList(ooNFAStates.get(0), ooNFAStates.get(0), ooNFAStates.get(0), ooNFAStates.get(0), ooNFAStates.get(0), ooNFAStates.get(0), ooNFAStates.get(0)))),
					new Trace(new ArrayList<State>(Arrays.asList(ooNFAStates.get(0), ooNFAStates.get(0), ooNFAStates.get(0), ooNFAStates.get(0), ooNFAStates.get(0), ooNFAStates.get(0), ooNFAStates.get(0)))),
					
			};
	//------------------------------------------------------------------------------------------------------------------
	
	//NFA that accepts a binary number that contains 11 or 00 (The "zOroNFA")-----------------------------------------------					//I.E. 1011 or 0100, but not 0010 or 0001
			private static ArrayList<State> zOroNFAStates = new ArrayList<State>(Arrays.asList(new State("zOro0"), new State("zOro1"), new State("zOro2"), new State("zOro3")));						//Q = {zOro0, zOro1, zOro2, zOro3}
			
			private static State zOroNFAStartState = zOroNFAStates.get(0);																				//q0 = zOro0
			
			private static ArrayList<ArrayList<State>> zOroNFANextStates = new ArrayList<ArrayList<State>>(Arrays.asList(
																														newList(zOroNFAStates.get(0), zOroNFAStates.get(1)), newList(zOroNFAStates.get(0), zOroNFAStates.get(2)), newList(), 
																														newList(zOroNFAStates.get(3)), newList(), newList(), 
																														newList(), newList(zOroNFAStates.get(3)), newList(), 
																														newList(zOroNFAStates.get(3)), newList(zOroNFAStates.get(3)), newList()
					 																					)				
																										);	
																										
			
			private static ArrayList<State> zOroNFAAcceptingStates = new ArrayList<State>(Arrays.asList(zOroNFAStates.get(3)));						//F = {zOro3}
			
			private static NFA zOroNFA = new NFA(zOroNFAStates, biAlphabet, zOroNFAStartState,zOroNFANextStates,zOroNFAAcceptingStates, epsilon);
			
			private static Trace zOroTestTraces[] = new Trace[] {
					new Trace(new ArrayList<State>(Arrays.asList(zOroNFAStates.get(0), zOroNFAStates.get(1)))),
					new Trace(new ArrayList<State>(Arrays.asList(zOroNFAStates.get(0), zOroNFAStates.get(0)))),
					new Trace(new ArrayList<State>(Arrays.asList(zOroNFAStates.get(0), zOroNFAStates.get(0), zOroNFAStates.get(2)))),
					new Trace(new ArrayList<State>(Arrays.asList(zOroNFAStates.get(0), zOroNFAStates.get(0), zOroNFAStates.get(1)))),
					new Trace(new ArrayList<State>(Arrays.asList(zOroNFAStates.get(0), zOroNFAStates.get(1), zOroNFAStates.get(2)))),
					new Trace(new ArrayList<State>(Arrays.asList(zOroNFAStates.get(0), zOroNFAStates.get(0), zOroNFAStates.get(1), zOroNFAStates.get(3)))),
					new Trace(new ArrayList<State>(Arrays.asList(zOroNFAStates.get(0), zOroNFAStates.get(0), zOroNFAStates.get(0), zOroNFAStates.get(0)))),
					new Trace(new ArrayList<State>(Arrays.asList(zOroNFAStates.get(0), zOroNFAStates.get(2), zOroNFAStates.get(3), zOroNFAStates.get(3)))),
					new Trace(new ArrayList<State>(Arrays.asList(zOroNFAStates.get(0), zOroNFAStates.get(0), zOroNFAStates.get(2), zOroNFAStates.get(3)))),
					new Trace(new ArrayList<State>(Arrays.asList(zOroNFAStates.get(0), zOroNFAStates.get(0), zOroNFAStates.get(2), zOroNFAStates.get(3), zOroNFAStates.get(3)))),
					new Trace(new ArrayList<State>(Arrays.asList(zOroNFAStates.get(0), zOroNFAStates.get(0), zOroNFAStates.get(0), zOroNFAStates.get(0), zOroNFAStates.get(0)))),
					new Trace(new ArrayList<State>(Arrays.asList(zOroNFAStates.get(0), zOroNFAStates.get(0), zOroNFAStates.get(0), zOroNFAStates.get(0), zOroNFAStates.get(2)))),
					
			};
	//------------------------------------------------------------------------------------------------------------------
			
	//NFA that accepts a binary number that ends in 11 or 00 (The "zOroEndNFA")--------------------------------------------------					//I.E. 0010 or 0100, but not 1000 or 0001
			private static ArrayList<State> zOroEndNFAStates = new ArrayList<State>(Arrays.asList(new State("zOroEnd0"), new State("zOroEnd1"), new State("zOroEnd2"), new State("zOroEnd3")));						//Q = {zOroEnd0, zOroEnd1, zOroEnd2, zOroEnd3}
			
			private static State zOroEndNFAStartState = zOroEndNFAStates.get(0);																				//q0 = zOroEnd0
			
			private static ArrayList<ArrayList<State>> zOroEndNFANextStates = new ArrayList<ArrayList<State>>(Arrays.asList(
																														newList(zOroEndNFAStates.get(0), zOroEndNFAStates.get(1)), newList(zOroEndNFAStates.get(0), zOroEndNFAStates.get(2)), newList(), 
																														newList(zOroEndNFAStates.get(3)), newList(), newList(), 
																														newList(), newList(zOroEndNFAStates.get(3)), newList(), 
																														newList(), newList(), newList()
					 																					)				
																										);	
																										
			
			private static ArrayList<State> zOroEndNFAAcceptingStates = new ArrayList<State>(Arrays.asList(zOroEndNFAStates.get(3)));						//F = {zOroEnd3}
			
			private static NFA zOroEndNFA = new NFA(zOroEndNFAStates, biAlphabet, zOroEndNFAStartState,zOroEndNFANextStates,zOroEndNFAAcceptingStates, epsilon);
			
			private static Trace zOroEndTestTraces[] = new Trace[] {
					new Trace(new ArrayList<State>(Arrays.asList(zOroEndNFAStates.get(0), zOroEndNFAStates.get(1)))),
					new Trace(new ArrayList<State>(Arrays.asList(zOroEndNFAStates.get(0), zOroEndNFAStates.get(0)))),
					new Trace(new ArrayList<State>(Arrays.asList(zOroEndNFAStates.get(0), zOroEndNFAStates.get(0), zOroEndNFAStates.get(2)))),
					new Trace(new ArrayList<State>(Arrays.asList(zOroEndNFAStates.get(0), zOroEndNFAStates.get(0), zOroEndNFAStates.get(1)))),
					new Trace(new ArrayList<State>(Arrays.asList(zOroEndNFAStates.get(0), zOroEndNFAStates.get(1), zOroEndNFAStates.get(2)))),
					new Trace(new ArrayList<State>(Arrays.asList(zOroEndNFAStates.get(0), zOroEndNFAStates.get(0), zOroEndNFAStates.get(1), zOroEndNFAStates.get(3)))),
					new Trace(new ArrayList<State>(Arrays.asList(zOroEndNFAStates.get(0), zOroEndNFAStates.get(0), zOroEndNFAStates.get(0), zOroEndNFAStates.get(0)))),
					new Trace(new ArrayList<State>(Arrays.asList(zOroEndNFAStates.get(0), zOroEndNFAStates.get(2), zOroEndNFAStates.get(3), zOroEndNFAStates.get(3)))),
					new Trace(new ArrayList<State>(Arrays.asList(zOroEndNFAStates.get(0), zOroEndNFAStates.get(0), zOroEndNFAStates.get(2), zOroEndNFAStates.get(3)))),
					new Trace(new ArrayList<State>(Arrays.asList(zOroEndNFAStates.get(0), zOroEndNFAStates.get(0), zOroEndNFAStates.get(0), zOroEndNFAStates.get(1), zOroEndNFAStates.get(3)))),
					new Trace(new ArrayList<State>(Arrays.asList(zOroEndNFAStates.get(0), zOroEndNFAStates.get(0), zOroEndNFAStates.get(0), zOroEndNFAStates.get(0), zOroEndNFAStates.get(0)))),
					new Trace(new ArrayList<State>(Arrays.asList(zOroEndNFAStates.get(0), zOroEndNFAStates.get(0), zOroEndNFAStates.get(0), zOroEndNFAStates.get(0), zOroEndNFAStates.get(2)))),
					
			};
	//------------------------------------------------------------------------------------------------------------------
		
	//NFA that accepts a binary number in the form of (01)* (The "zoNFA")--------------------------------------------------		
			private static ArrayList<State> zoNFAStates = new ArrayList<State>(Arrays.asList(new State("zo0"), new State("zo1"), new State("zo2")));						//Q = {zo0, zo1, zo2}
			
			private static State zoNFAStartState = zoNFAStates.get(0);																				//q0 = zo0
			
			private static ArrayList<ArrayList<State>> zoNFANextStates = new ArrayList<ArrayList<State>>(Arrays.asList(
																														newList(zoNFAStates.get(1)), newList(zoNFAStates.get(2)), newList(), 
																														newList(zoNFAStates.get(2)), newList(zoNFAStates.get(0)), newList(), 
																														newList(zoNFAStates.get(2)), newList(zoNFAStates.get(2)), newList() 
					 																					)				
																										);	
																										
			
			private static ArrayList<State> zoNFAAcceptingStates = new ArrayList<State>(Arrays.asList(zoNFAStates.get(0)));						//F = {zo0}
			
			private static NFA zoNFA = new NFA(zoNFAStates, biAlphabet, zoNFAStartState, zoNFANextStates, zoNFAAcceptingStates, epsilon);
			
			private static Trace zoTestTraces[] = new Trace[] {
					new Trace(new ArrayList<State>(Arrays.asList(zoNFAStates.get(0)))),
					new Trace(new ArrayList<State>(Arrays.asList(zoNFAStates.get(0), zoNFAStates.get(1)))),
					new Trace(new ArrayList<State>(Arrays.asList(zoNFAStates.get(0), zoNFAStates.get(1), zoNFAStates.get(0)))),
					new Trace(new ArrayList<State>(Arrays.asList(zoNFAStates.get(0), zoNFAStates.get(1), zoNFAStates.get(0), zoNFAStates.get(1)))),
					new Trace(new ArrayList<State>(Arrays.asList(zoNFAStates.get(0), zoNFAStates.get(2), zoNFAStates.get(2), zoNFAStates.get(2)))),
					new Trace(new ArrayList<State>(Arrays.asList(zoNFAStates.get(0), zoNFAStates.get(2), zoNFAStates.get(2), zoNFAStates.get(2), zoNFAStates.get(2)))),
					new Trace(new ArrayList<State>(Arrays.asList(zoNFAStates.get(0), zoNFAStates.get(1), zoNFAStates.get(0), zoNFAStates.get(1), zoNFAStates.get(2)))),
					new Trace(new ArrayList<State>(Arrays.asList(zoNFAStates.get(0), zoNFAStates.get(1), zoNFAStates.get(0), zoNFAStates.get(1), zoNFAStates.get(2), zoNFAStates.get(2), zoNFAStates.get(2)))),
					new Trace(new ArrayList<State>(Arrays.asList(zoNFAStates.get(0), zoNFAStates.get(1), zoNFAStates.get(0), zoNFAStates.get(1), zoNFAStates.get(0)))),
					new Trace(new ArrayList<State>(Arrays.asList(zoNFAStates.get(0), zoNFAStates.get(2), zoNFAStates.get(2), zoNFAStates.get(2), zoNFAStates.get(2)))),
					new Trace(new ArrayList<State>(Arrays.asList(zoNFAStates.get(0), zoNFAStates.get(1), zoNFAStates.get(0), zoNFAStates.get(1), zoNFAStates.get(2), zoNFAStates.get(2)))),
					new Trace(new ArrayList<State>(Arrays.asList(zoNFAStates.get(0), zoNFAStates.get(1), zoNFAStates.get(0), zoNFAStates.get(1), zoNFAStates.get(2), zoNFAStates.get(2)))),
					new Trace(new ArrayList<State>(Arrays.asList(zoNFAStates.get(0), zoNFAStates.get(1), zoNFAStates.get(0), zoNFAStates.get(1), zoNFAStates.get(0), zoNFAStates.get(2)))),
					
			};
	//--------------------------------------------------------------------------------------------------------------------------
			
	//NFA that accepts a binary number that only contains 1's or where the last character is zero or the string is empty (The "ozLastNFA")--			
			private static ArrayList<State> ozLastNFAStates = new ArrayList<State>(Arrays.asList(new State("ozLast0"), new State("ozLast1"), new State("ozLast2"), new State("ozLast3")));						//Q = {ozLast0, ozLast1, ozLast2, ozLast3}
			
			private static State ozLastNFAStartState = ozLastNFAStates.get(1);																				//q0 = ozLast1
			
			private static ArrayList<ArrayList<State>> ozLastNFANextStates = new ArrayList<ArrayList<State>>(Arrays.asList(
																														newList(), newList(ozLastNFAStates.get(0)), newList(), 
																														newList(), newList(), newList(ozLastNFAStates.get(0), ozLastNFAStates.get(2)), 
																														newList(ozLastNFAStates.get(2), ozLastNFAStates.get(3)), newList(ozLastNFAStates.get(2)), newList(), 
																														newList(), newList(), newList()
					 																					)				
																										);	
																										
			
			private static ArrayList<State> ozLastNFAAcceptingStates = new ArrayList<State>(Arrays.asList(ozLastNFAStates.get(0), ozLastNFAStates.get(3)));						//F = {ozLast0, ozLast3}
			
			private static NFA ozLastNFA = new NFA(ozLastNFAStates, biAlphabet, ozLastNFAStartState,ozLastNFANextStates,ozLastNFAAcceptingStates, epsilon);
			
			private static Trace ozLastTestTraces[] = new Trace[] {
					new Trace(new ArrayList<State>(Arrays.asList(ozLastNFAStates.get(0)))),
					new Trace(new ArrayList<State>(Arrays.asList(ozLastNFAStates.get(1)))),
					new Trace(new ArrayList<State>(Arrays.asList(ozLastNFAStates.get(2)))),
					new Trace(new ArrayList<State>(Arrays.asList(ozLastNFAStates.get(0), ozLastNFAStates.get(0)))),
					new Trace(new ArrayList<State>(Arrays.asList(ozLastNFAStates.get(0), ozLastNFAStates.get(0), ozLastNFAStates.get(0)))),
					new Trace(new ArrayList<State>(Arrays.asList(ozLastNFAStates.get(0), ozLastNFAStates.get(0), ozLastNFAStates.get(0), ozLastNFAStates.get(0)))),
					new Trace(new ArrayList<State>(Arrays.asList(ozLastNFAStates.get(0), ozLastNFAStates.get(0), ozLastNFAStates.get(0), ozLastNFAStates.get(0), ozLastNFAStates.get(0)))),
					new Trace(new ArrayList<State>(Arrays.asList(ozLastNFAStates.get(0), ozLastNFAStates.get(0), ozLastNFAStates.get(0), ozLastNFAStates.get(0), ozLastNFAStates.get(0), ozLastNFAStates.get(0)))),
					new Trace(new ArrayList<State>(Arrays.asList(ozLastNFAStates.get(2), ozLastNFAStates.get(2), ozLastNFAStates.get(2), ozLastNFAStates.get(2), ozLastNFAStates.get(2), ozLastNFAStates.get(3)))),
					new Trace(new ArrayList<State>(Arrays.asList(ozLastNFAStates.get(2), ozLastNFAStates.get(2), ozLastNFAStates.get(2), ozLastNFAStates.get(2), ozLastNFAStates.get(2), ozLastNFAStates.get(2)))),
					new Trace(new ArrayList<State>(Arrays.asList(ozLastNFAStates.get(2), ozLastNFAStates.get(2), ozLastNFAStates.get(2), ozLastNFAStates.get(2), ozLastNFAStates.get(3)))),
					new Trace(new ArrayList<State>(Arrays.asList(ozLastNFAStates.get(2), ozLastNFAStates.get(2), ozLastNFAStates.get(2), ozLastNFAStates.get(2), ozLastNFAStates.get(2)))),
					
			};
	//----------------------------------------------------------------------------------------------------------------------
			
	//NFA that accepts all binary #s (The "allNFA")-------------------------------------------------------------------------
			private static ArrayList<State> allNFAStates = new ArrayList<State>(Arrays.asList(new State("all0"), new State("all1")));						//Q = {A, B}
			
			private static State allNFAStartState = allNFAStates.get(0);																			//q0 = A
			
			private static ArrayList<ArrayList<State>>allNFANextStates = new ArrayList<ArrayList<State>>(Arrays.asList(
																										 newList( allNFAStates.get(0) ), newList( allNFAStates.get(0)), newList( allNFAStates.get(1) ),	//Delta = {(all0, '0', {all0}), (all0, '1', {all0}), (all0, epsilon, {all1})
																										 newList(), newList(), newList())																//        {(all1, '0', {}), (all1, '1', {}), (all1, epsilon, {})
																										);	
																										
			
			private static ArrayList<State>allNFAAcceptingStates = new ArrayList<State>(Arrays.asList(allNFAStates.get(1)));						//F = {all1}
			
			private static NFA allNFA = new NFA(allNFAStates, biAlphabet,allNFAStartState,allNFANextStates,allNFAAcceptingStates, epsilon);
			
			private static Trace allTestTraces[] = new Trace[] {
					new Trace(new ArrayList<State>(Arrays.asList(allNFAStates.get(0)))),
					new Trace(new ArrayList<State>(Arrays.asList(allNFAStates.get(1)))),
					new Trace(new ArrayList<State>(Arrays.asList(allNFAStates.get(0), allNFAStates.get(0)))),
					new Trace(new ArrayList<State>(Arrays.asList(allNFAStates.get(0), allNFAStates.get(1)))),
					new Trace(new ArrayList<State>(Arrays.asList(allNFAStates.get(0), allNFAStates.get(0), allNFAStates.get(0)))),
					new Trace(new ArrayList<State>(Arrays.asList(allNFAStates.get(0), allNFAStates.get(0), allNFAStates.get(1)))),
					new Trace(new ArrayList<State>(Arrays.asList(allNFAStates.get(0), allNFAStates.get(0), allNFAStates.get(0), allNFAStates.get(0)))),
					new Trace(new ArrayList<State>(Arrays.asList(allNFAStates.get(0), allNFAStates.get(0), allNFAStates.get(0), allNFAStates.get(1)))),
					new Trace(new ArrayList<State>(Arrays.asList(allNFAStates.get(0), allNFAStates.get(0), allNFAStates.get(0), allNFAStates.get(0), allNFAStates.get(0)))),
					new Trace(new ArrayList<State>(Arrays.asList(allNFAStates.get(0), allNFAStates.get(0), allNFAStates.get(0), allNFAStates.get(0), allNFAStates.get(1)))),
					new Trace(new ArrayList<State>(Arrays.asList(allNFAStates.get(0), allNFAStates.get(0), allNFAStates.get(0), allNFAStates.get(0), allNFAStates.get(0), allNFAStates.get(0)))),
					new Trace(new ArrayList<State>(Arrays.asList(allNFAStates.get(0), allNFAStates.get(0), allNFAStates.get(0), allNFAStates.get(0), allNFAStates.get(0), allNFAStates.get(1)))),
					new Trace(new ArrayList<State>(Arrays.asList(allNFAStates.get(0), allNFAStates.get(0), allNFAStates.get(0), allNFAStates.get(0), allNFAStates.get(0), allNFAStates.get(0), allNFAStates.get(0)))),
					new Trace(new ArrayList<State>(Arrays.asList(allNFAStates.get(0), allNFAStates.get(0), allNFAStates.get(0), allNFAStates.get(0), allNFAStates.get(0), allNFAStates.get(0), allNFAStates.get(1)))),
					
			};
	//----------------------------------------------------------------------------------------------------------------------
	
	//DFA that accepts odd binary #s (The "oddDFA")-------------------------------------------------------------------------
		private static ArrayList<State> oddDFAStates = new ArrayList<State>(Arrays.asList(new State("A"), new State("B")));						//Q = {A, B}
		
		private static State oddDFAStartState =oddDFAStates.get(0);																				//q0 = A
		
		private static ArrayList<State>oddDFANextStates = new ArrayList<State>(Arrays.asList(oddDFAStates.get(0), oddDFAStates.get(1),			//Delta = {(A, '0', A), (A, '1', B)
																		                     oddDFAStates.get(0), oddDFAStates.get(1)));		//(B, '0', A), (B, '1', B)
		
		private static ArrayList<State>oddDFAAcceptingStates = new ArrayList<State>(Arrays.asList(oddDFAStates.get(1)));						//F = {B}
		
		private static DFA oddDFA = new DFA(oddDFAStates, biAlphabet,oddDFAStartState,oddDFANextStates,oddDFAAcceptingStates);
	//----------------------------------------------------------------------------------------------------------------------	
			
	//DFA that accepts even binary #s (The "evenDFA")-------------------------------------------------------------------------		
		private static ArrayList<State> evenDFAStates = new ArrayList<State>(Arrays.asList(new State("A"), new State("B")));						//Q = {A, B}
				
		private static State evenDFAStartState =evenDFAStates.get(0);																				//q0 = A
				
		private static ArrayList<State>evenDFANextStates = new ArrayList<State>(Arrays.asList(evenDFAStates.get(1), evenDFAStates.get(0),			//Delta = {(A, '0', B), (A, '1', A)
																				              evenDFAStates.get(1), evenDFAStates.get(0)));			//(B, '0', B), (B, '1', A)
				
		private static ArrayList<State>evenDFAAcceptingStates = new ArrayList<State>(Arrays.asList(evenDFAStates.get(1)));							//F = {B}
				
		private static DFA evenDFA = new DFA(evenDFAStates, biAlphabet,evenDFAStartState,evenDFANextStates,evenDFAAcceptingStates);
	//----------------------------------------------------------------------------------------------------------------------
		
	//DFA that accepts even length binary #s (The "evenLDFA")------------------------------------------------------------------
		private static ArrayList<State> evenLDFAStates = new ArrayList<State>(Arrays.asList(new State("A"), new State("B")));						//Q = {A, B}
		
		private static State evenLDFAStartState =evenLDFAStates.get(0);																				//q0 = A
		
		private static ArrayList<State>evenLDFANextStates = new ArrayList<State>(Arrays.asList(evenLDFAStates.get(1),evenLDFAStates.get(1),				//Delta = {(A, '0', B), (A, '1', B)
																		                    evenLDFAStates.get(0),evenLDFAStates.get(0)));			//(B, '0', A), (B, '1', A)
																		                   
		
		private static ArrayList<State>evenLDFAAcceptingStates = new ArrayList<State>(Arrays.asList( evenLDFAStates.get(0)));							//F = {A}
		
		private static DFA evenLDFA = new DFA(evenLDFAStates, biAlphabet,evenLDFAStartState,evenLDFANextStates,evenLDFAAcceptingStates);
	//----------------------------------------------------------------------------------------------------------------------
		
	//DFA that accepts odd length binary #s (The oddLDFA")-------------------------------------------------------------------
		private static ArrayList<State> oddLDFAStates = new ArrayList<State>(Arrays.asList(new State("A"), new State("B")));						//Q = {A, B}
		
		private static State oddLDFAStartState =oddLDFAStates.get(0);																				//q0 = A
		
		private static ArrayList<State> oddLDFANextStates = new ArrayList<State>(Arrays.asList(oddLDFAStates.get(1),oddLDFAStates.get(1),				//Delta = {(A, '0', B), (A, '1', B)
																		                    oddLDFAStates.get(0),oddLDFAStates.get(0)));			//(B, '0', A), (B, '1', A)
																		                   
		
		private static ArrayList<State> oddLDFAAcceptingStates = new ArrayList<State>(Arrays.asList( oddLDFAStates.get(1)));						//F = {B}
		
		private static DFA oddLDFA = new DFA(oddLDFAStates, biAlphabet,oddLDFAStartState,oddLDFANextStates,oddLDFAAcceptingStates);
	//----------------------------------------------------------------------------------------------------------------------
		
	//DFA that accepts a sting containing the binary # "11" (The "ooDFA")------------------------------------------------------------------------
		private static ArrayList<State> ooDFAStates = new ArrayList<State>(Arrays.asList(new State("A"), new State("B"), new State("C")));						//Q = {A, B, C}
		
		private static State ooDFAStartState =ooDFAStates.get(0);																				//q0 = A
		
		private static ArrayList<State>ooDFANextStates = new ArrayList<State>(Arrays.asList(ooDFAStates.get(0),ooDFAStates.get(1),				//Delta = {(A, '0', A), (A, '1', B)
																		                    ooDFAStates.get(0),ooDFAStates.get(2),						 //(B, '0', A), (B, '1', C)
		 																					ooDFAStates.get(2),ooDFAStates.get(2)));					 //(C, '0', C), (C, '1', C)}
		
		private static ArrayList<State>ooDFAAcceptingStates = new ArrayList<State>(Arrays.asList(ooDFAStates.get(2)));							//F = {C}
		
		private static DFA ooDFA = new DFA(ooDFAStates, biAlphabet,ooDFAStartState,ooDFANextStates,ooDFAAcceptingStates);
	//----------------------------------------------------------------------------------------------------------------------
				
		
	//DFA that accepts all strings of bits (The "allDFA")-------------------------------------------------------------------															
		private static ArrayList<State> allDFAStates = new ArrayList<State>(Arrays.asList(new State("A")));																//Q = {A}
		
		private static State allDFAStartState =allDFAStates.get(0);																										//q0 = A
		
		private static ArrayList<State> allDFANextStates = new ArrayList<State>(Arrays.asList(allDFAStates.get(0),allDFAStates.get(0)));								//Delta = {(A, '0', A), (A, '1', A)
																		                   
		
		private static ArrayList<State> allDFAAcceptingStates = new ArrayList<State>(Arrays.asList(allDFAStates.get(0)));												//F = {A}
		
		private static DFA allDFA = new DFA(allDFAStates, biAlphabet,allDFAStartState,allDFANextStates,allDFAAcceptingStates);
	//----------------------------------------------------------------------------------------------------------------------
		
	public static void main(String[] args) {  	
		
		/*
		 * 
		 * Gen Trace Trees
		 * 
		 */
		System.out.print("Generate Trace Trees: \n");
		
		System.out.print("	oddNFA:\n");
		for(int i = 0; i < testStrings.length; i++) {
			oddNFA.run(testStrings[i]);
			System.out.print("		Created trace tree for oddNFA and string '" + testStrings[i].displayable() + "'\n");
			
		}
		
		System.out.print("\n");
		
		System.out.print("	evenNFA:\n");
		for(int i = 0; i < testStrings.length; i++) {
			evenNFA.run(testStrings[i]);
			System.out.print("		Created trace tree for evenNFA and string '" + testStrings[i].displayable() + "'\n");
			
		}
		
		System.out.print("\n");
		
		System.out.print("	oddLNFA:\n");
		for(int i = 0; i < testStrings.length; i++) {
			oddLNFA.run(testStrings[i]);
			System.out.print("		Created trace tree for oddLNFA and string '" + testStrings[i].displayable() + "'\n");
			
		}
		
		System.out.print("\n");
		
		System.out.print("	evenLNFA:\n");
		for(int i = 0; i < testStrings.length; i++) {
			evenLNFA.run(testStrings[i]);
			System.out.print("		Created trace tree for evenLNFA and string '" + testStrings[i].displayable() + "'\n");
			
		}
		
		System.out.print("\n");
		
		System.out.print("	stNFA:\n");
		for(int i = 0; i < testStrings.length; i++) {
			stNFA.run(testStrings[i]);
			System.out.print("		Created trace tree for stNFA and string '" + testStrings[i].displayable() + "'\n");
			
		}
		
		System.out.print("\n");
		
		System.out.print("	zzNFA:\n");
		for(int i = 0; i < testStrings.length; i++) {
			zzNFA.run(testStrings[i]);
			System.out.print("		Created trace tree for zzNFA and string '" + testStrings[i].displayable() + "'\n");
			
		}
		
		System.out.print("\n");
		
		System.out.print("	ooNFA:\n");
		for(int i = 0; i < testStrings.length; i++) {
			ooNFA.run(testStrings[i]);
			System.out.print("		Created trace tree for ooNFA and string '" + testStrings[i].displayable() + "'\n");
			
		}
		
		System.out.print("\n");
		
		System.out.print("	zOroNFA:\n");
		for(int i = 0; i < testStrings.length; i++) {
			zOroNFA.run(testStrings[i]);
			System.out.print("		Created trace tree for zOroNFA and string '" + testStrings[i].displayable() + "'\n");
			
		}
		
		System.out.print("\n");
		
		System.out.print("	zOroEndNFA:\n");
		for(int i = 0; i < testStrings.length; i++) {
			zOroEndNFA.run(testStrings[i]);
			System.out.print("		Created trace tree for zOroEndNFA and string '" + testStrings[i].displayable() + "'\n");
			
		}
		
		System.out.print("\n");
		
		System.out.print("	zoNFA:\n");
		for(int i = 0; i < testStrings.length; i++) {
			zoNFA.run(testStrings[i]);
			System.out.print("		Created trace tree for zoNFA and string '" + testStrings[i].displayable() + "'\n");
			
		}
		
		System.out.print("\n");
		
		System.out.print("	ozLastNFA:\n");
		for(int i = 0; i < testStrings.length; i++) {
			ozLastNFA.run(testStrings[i]);
			System.out.print("		Created trace tree for ozLastNFA and string '" + testStrings[i].displayable() + "'\n");
			
		}
		
		System.out.print("\n");
		
		System.out.print("	allNFA:\n");
		for(int i = 0; i < testStrings.length; i++) {
			allNFA.run(testStrings[i]);
			System.out.print("		Created trace tree for allNFA and string '" + testStrings[i].displayable() + "'\n");
			
		}
		
		System.out.print("\n");
		
		/*
		 * 
		 * Test NFA's
		 * 
		 */
		
		System.out.print("Test NFA's: \n");
		
		System.out.print("	oddNFA:\n");
		for(int i = 0; i < testStrings.length; i++) {
			System.out.print("		oddNFA ('" + testStrings[i].displayable() + "') = " + oddNFA.run(testStrings[i]) + " \n");
			
		}
		
		System.out.print("\n");
		
		System.out.print("	evenNFA:\n");
		for(int i = 0; i < testStrings.length; i++) {
			System.out.print("		evenNFA ('" + testStrings[i].displayable() + "') = " + evenNFA.run(testStrings[i]) + " \n");
			
		}
		
		System.out.print("\n");
		
		System.out.print("	oddLNFA:\n");
		for(int i = 0; i < testStrings.length; i++) {
			System.out.print("		oddLNFA ('" + testStrings[i].displayable() + "') = " + oddLNFA.run(testStrings[i]) + " \n");
			
		}
		
		System.out.print("\n");
		
		System.out.print("	evenLNFA:\n");
		for(int i = 0; i < testStrings.length; i++) {
			System.out.print("		evenLNFA ('" + testStrings[i].displayable() + "') = " + evenLNFA.run(testStrings[i]) + " \n");
			
		}
		
		System.out.print("\n");
		
		System.out.print("	stNFA:\n");
		for(int i = 0; i < testStrings.length; i++) {
			System.out.print("		stNFA ('" + testStrings[i].displayable() + "') = " + stringTest(testStrings[i], stNFA, stNFA.getStartState(), 0) + " \n");
			
		}
		
		System.out.print("\n");
		
		System.out.print("	zzNFA:\n");
		for(int i = 0; i < testStrings.length; i++) {
			System.out.print("		zzNFA ('" + testStrings[i].displayable() + "') = " + zzNFA.run(testStrings[i]) + " \n");
			
		}
		
		System.out.print("\n");
		
		System.out.print("	ooNFA:\n");
		for(int i = 0; i < testStrings.length; i++) {
			System.out.print("		ooNFA ('" + testStrings[i].displayable() + "') = " + ooNFA.run(testStrings[i]) + " \n");
			
		}
		
		System.out.print("\n");
		
		System.out.print("	zOroNFA:\n");
		for(int i = 0; i < testStrings.length; i++) {
			System.out.print("		zOroNFA ('" + testStrings[i].displayable() + "') = " + zOroNFA.run(testStrings[i]) + " \n");
			
		}
		
		System.out.print("\n");
		
		System.out.print("	zOroEndNFA:\n");
		for(int i = 0; i < testStrings.length; i++) {
			System.out.print("		zOroEndNFA ('" + testStrings[i].displayable() + "') = " + zOroEndNFA.run(testStrings[i]) + " \n");
			
		}
		
		System.out.print("\n");
		
		System.out.print("	zoNFA:\n");
		for(int i = 0; i < testStrings.length; i++) {
			System.out.print("		zoNFA ('" + testStrings[i].displayable() + "') = " + zoNFA.run(testStrings[i]) + " \n");
			
		}
		
		System.out.print("\n");
		
		System.out.print("	ozLastNFA:\n");
		for(int i = 0; i < testStrings.length; i++) {
			System.out.print("		ozLastNFA ('" + testStrings[i].displayable() + "') = " + stringTest(testStrings[i], ozLastNFA, ozLastNFA.getStartState(), 0) + " \n");
			
		}
		
		System.out.print("\n");
		
		System.out.print("	allNFA:\n");
		for(int i = 0; i < testStrings.length; i++) {
			System.out.print("		allNFA ('" + testStrings[i].displayable() + "') = " + allNFA.run(testStrings[i]) + " \n");
			
		}
		
		System.out.print("\n");
		
		
		/*
		 * 
		 * Concat Tests
		 * 
		 */
		
		System.out.print("Concatenation Tests: \n");
		
		NFA test;
		int index;
		
		test = concatenation(evenNFA, oddNFA);
		index = 4;
		
		System.out.print("		evenNFA o oddNFA('" + testStrings[index].displayable() + "') = " +stringTest(testStrings[index], test, test.getStartState(), 0) + " \n\n");
		
		test = concatenation(evenNFA, zoNFA);
		index = 8;
		
		System.out.print("		evenNFA o zoNFA('" + testStrings[index].displayable() + "') = " +stringTest(testStrings[index], test, test.getStartState(), 0) + " \n\n");
		
		test = concatenation(evenNFA, oddLNFA);
		index = 4;
		
		System.out.print("		evenNFA o oddLNFA('" + testStrings[index].displayable() + "') = " +stringTest(testStrings[index], test, test.getStartState(), 0) + " \n\n");
		
		test = concatenation(evenNFA, evenLNFA);
		index = 9;
		
		System.out.print("		evenNFA o evenLNFA('" + testStrings[index].displayable() + "') = " +stringTest(testStrings[index], test, test.getStartState(), 0) + " \n\n");
		
		test = concatenation(stNFA, oddNFA);
		index = 8;
		
		System.out.print("		stNFA o oddNFA('" + testStrings[index].displayable() + "') = " +stringTest(testStrings[index], test, test.getStartState(), 0) + " \n\n");
		
		test = concatenation(evenNFA, zOroEndNFA);
		index = 10;
		
		System.out.print("		evenNFA o zOroEndNFA('" + testStrings[index].displayable() + "') = " +stringTest(testStrings[index], test, test.getStartState(), 0) + " \n\n");
		
		test = concatenation(stNFA, evenNFA);
		index = 9;
		
		System.out.print("		stNFA o evenNFA('" + testStrings[index].displayable() + "') = " +stringTest(testStrings[index], test, test.getStartState(), 0) + " \n\n");
		
		test = concatenation(oddNFA, ozLastNFA);
		index = 9;
		
		System.out.print("		oddNFA o ozLastNFA('" + testStrings[index].displayable() + "') = " +stringTest(testStrings[index], test, test.getStartState(), 0) + " \n\n");
		
		test = concatenation(oddNFA, zzNFA);
		index = 5;
		
		System.out.print("		oddNFA o zzNFA('" + testStrings[index].displayable() + "') = " +stringTest(testStrings[index], test, test.getStartState(), 0) + " \n\n");
		
		test = concatenation(ozLastNFA, stNFA);
		index = 10;
		
		System.out.print("		ozLastNFA o stNFA('" + testStrings[index].displayable() + "') = " +stringTest(testStrings[index], test, test.getStartState(), 0) + " \n\n");
		
		test = concatenation(ozLastNFA, ozLastNFA);
		index = 3;
		
		System.out.print("		ozLastNFA o ozLastNFA('" + testStrings[index].displayable() + "') = " +stringTest(testStrings[index], test, test.getStartState(), 0) + " \n\n");
		
		test = concatenation(oddLNFA, evenLNFA);
		index = 7;
		
		System.out.print("		oddLNFA o evenNFA('" + testStrings[index].displayable() + "') = " +stringTest(testStrings[index], test, test.getStartState(), 0) + " \n\n");
		
		
		/*
		 * 
		 * Kleene Tests
		 * 
		 */
		
		System.out.print("Kleene Star Tests: \n");
		
		test = kleene(oddNFA);
		index = 11;
		
		System.out.print("		oddNFA*('" + testStrings[index].displayable() + "') = " +stringTest(testStrings[index], test, test.getStartState(), 0) + " \n\n");
		
		test = kleene(evenNFA);
		index = 1;
		
		System.out.print("		evenNFA*('" + testStrings[index].displayable() + "') = " +stringTest(testStrings[index], test, test.getStartState(), 0) + " \n\n");
		
		test = kleene(oddLNFA);
		index = 8;
		
		System.out.print("		oddLNFA*('" + testStrings[index].displayable() + "') = " +stringTest(testStrings[index], test, test.getStartState(), 0) + " \n\n");
		
		test = kleene(evenLNFA);
		index = 10;
		
		System.out.print("		evenLNFA*('" + testStrings[index].displayable() + "') = " +stringTest(testStrings[index], test, test.getStartState(), 0) + " \n\n");
		
		test = kleene(stNFA);
		index = 4;
		
		System.out.print("		stNFA*('" + testStrings[index].displayable() + "') = " +stringTest(testStrings[index], test, test.getStartState(), 0) + " \n\n");
		
		test = kleene(zzNFA);
		index = 9;
		
		System.out.print("		zzNFA*('" + testStrings[index].displayable() + "') = " +stringTest(testStrings[index], test, test.getStartState(), 0) + " \n\n");
		
		test = kleene(ooNFA);
		index = 10;
		
		System.out.print("		ooNFA*('" + testStrings[index].displayable() + "') = " +stringTest(testStrings[index], test, test.getStartState(), 0) + " \n\n");
		
		test = kleene(zOroNFA);
		index = 10;
		
		System.out.print("		zOroNFA*('" + testStrings[index].displayable() + "') = " +stringTest(testStrings[index], test, test.getStartState(), 0) + " \n\n");
		
		test = kleene(zOroEndNFA);
		index = 12;
		
		System.out.print("		zOroEndNFA*('" + testStrings[index].displayable() + "') = " +stringTest(testStrings[index], test, test.getStartState(), 0) + " \n\n");
		
		test = kleene(zoNFA);
		index = 11;
		
		System.out.print("		zoNFA*('" + testStrings[index].displayable() + "') = " +stringTest(testStrings[index], test, test.getStartState(), 0) + " \n\n");
		
		test = kleene(ozLastNFA);
		index = 2;
		
		System.out.print("		ozLastNFA*('" + testStrings[index].displayable() + "') = " +stringTest(testStrings[index], test, test.getStartState(), 0) + " \n\n");
		
		test = kleene(allNFA);
		index = 0;
		
		System.out.print("		allNFA*('" + testStrings[index].displayable() + "') = " +stringTest(testStrings[index], test, test.getStartState(), 0) + " \n\n");
		
		/*
		 * 
		 * DFA Conversion Tests
		 * 
		 */
		
		System.out.print("Test NFA to DFA conversion: \n");
		
		System.out.print("	oddNFA:\n");
		for(int i = 0; i < testStrings.length; i++) {
			System.out.print("		(DFA)oddNFA ('" + testStrings[i].displayable() + "') = " + nfaToDFA(oddNFA).run(testStrings[i]) + " \n");
			
		}
		
		System.out.print("\n");
		
		System.out.print("	evenNFA:\n");
		for(int i = 0; i < testStrings.length; i++) {
			System.out.print("		(DFA)evenNFA ('" + testStrings[i].displayable() + "') = " + nfaToDFA(evenNFA).run(testStrings[i]) + " \n");
			
		}
		
		System.out.print("\n");
		
		System.out.print("	oddLNFA:\n");
		for(int i = 0; i < testStrings.length; i++) {
			System.out.print("		(DFA)oddLNFA ('" + testStrings[i].displayable() + "') = " + nfaToDFA(oddLNFA).run(testStrings[i]) + " \n");
			
		}
		
		System.out.print("\n");
		
		System.out.print("	evenLNFA:\n");
		for(int i = 0; i < testStrings.length; i++) {
			System.out.print("		(DFA)evenLNFA ('" + testStrings[i].displayable() + "') = " + nfaToDFA(evenLNFA).run(testStrings[i]) + " \n");
			
		}
		
		System.out.print("\n");
		
		System.out.print("	stNFA:\n");
		for(int i = 0; i < testStrings.length; i++) {
			System.out.print("		(DFA)stNFA ('" + testStrings[i].displayable() + "') = " + nfaToDFA(stNFA).run(testStrings[i]) + " \n");
			
		}
		
		System.out.print("\n");
		
		System.out.print("	zzNFA:\n");
		for(int i = 0; i < testStrings.length; i++) {
			System.out.print("		(DFA)zzNFA ('" + testStrings[i].displayable() + "') = " + nfaToDFA(zzNFA).run(testStrings[i]) + " \n");
			
		}
		
		System.out.print("\n");
		
		System.out.print("	ooNFA:\n");
		for(int i = 0; i < testStrings.length; i++) {
			System.out.print("		(DFA)ooNFA ('" + testStrings[i].displayable() + "') = " + nfaToDFA(ooNFA).run(testStrings[i]) + " \n");
			
		}
		
		System.out.print("\n");
		
		System.out.print("	zOroNFA:\n");
		for(int i = 0; i < testStrings.length; i++) {
			System.out.print("		(DFA)zOroNFA ('" + testStrings[i].displayable() + "') = " + nfaToDFA(zOroNFA).run(testStrings[i]) + " \n");
			
		}
		
		System.out.print("\n");
		
		System.out.print("	zOroEndNFA:\n");
		for(int i = 0; i < testStrings.length; i++) {
			System.out.print("		(DFA)zOroEndNFA ('" + testStrings[i].displayable() + "') = " + nfaToDFA(zOroEndNFA).run(testStrings[i]) + " \n");
			
		}
		
		System.out.print("\n");
		
		System.out.print("	zoNFA:\n");
		for(int i = 0; i < testStrings.length; i++) {
			System.out.print("		(DFA)zoNFA ('" + testStrings[i].displayable() + "') = " + nfaToDFA(zoNFA).run(testStrings[i]) + " \n");
			
		}
		
		System.out.print("\n");
		
		System.out.print("	ozLastNFA:\n");
		for(int i = 0; i < testStrings.length; i++) {
			System.out.print("		(DFA)ozLastLNFA ('" + testStrings[i].displayable() + "') = " + nfaToDFA(ozLastNFA).run(testStrings[i]) + " \n");
			
		}
		
		System.out.print("\n");
		
		System.out.print("	allNFA:\n");
		for(int i = 0; i < testStrings.length; i++) {
			System.out.print("		(DFA)allNFA ('" + testStrings[i].displayable() + "') = " + nfaToDFA(allNFA).run(testStrings[i]) + " \n\n");
			
		}
		
		/*
		 * 
		 * DFA Conversion Equivalence Tests
		 * 
		 */
		
		System.out.print("Test NFA to DFA conversion by comparing converted NFA's to equivalent DFA's: \n\n");
		
		System.out.print("	oddNFA:\n");
		System.out.print("		(DFA)oddNFA == oddDFA? " + dfaEqual(nfaToDFA(oddNFA), oddDFA) + " \n\n");
		
		System.out.print("	evenNFA:\n");
		System.out.print("		(DFA)evenNFA == evenDFA? " + dfaEqual(nfaToDFA(evenNFA), evenDFA) + " \n\n");
		
		System.out.print("	oddLNFA:\n");
		System.out.print("		(DFA)oddLNFA == oddLDFA? " + dfaEqual(oddLDFA, oddLDFA) + " \n\n");
		
		System.out.print("	evenLNFA:\n");
		System.out.print("		(DFA)evenLNFA == evenLDFA? " + dfaEqual(nfaToDFA(evenLNFA), evenLDFA) + " \n\n");
		
		System.out.print("	ooNFA:\n");
		System.out.print("		(DFA)ooNFA == ooDFA? " + dfaEqual(nfaToDFA(ooNFA), ooDFA) + " \n\n");
		
		System.out.print("	allNFA:\n");
		System.out.print("		(DFA)allNFA == allDFA? " + dfaEqual(nfaToDFA(allNFA), allDFA) + " \n\n");
			
	}
	
	public static ArrayList<State> newList(State...states){
		
		ArrayList<State> newList = new ArrayList<State>();
		
		for(State state : states) {
			newList.add(state);
			
		}
		
		return newList;
		
	}
	
	//DFA -> NFA
	public static NFA dfaToNFA(DFA dfa) {
		ArrayList<State> nfaStates = new ArrayList<State>();
		Alphabet alphabet = dfa.getAlphabet();
		
		nfaStates.add(new State("start"));
		nfaStates.addAll(dfa.getStates());
		nfaStates.add(new State("accept"));
		
		ArrayList<ArrayList<State>> nfaNextStates = new ArrayList<ArrayList<State>>();
		ArrayList<State> nfaAcceptingStates = new ArrayList<State>();
		
		State nfaStartState = nfaStates.get(0);
		nfaAcceptingStates.add(nfaStates.get(nfaStates.size()-1));
		
		for(int i = 0; i < alphabet.size(); i++) {
			nfaNextStates.add(newList());
		}
		
		nfaNextStates.add(newList(dfa.getStartState()));
		
		for(State state : dfa.getStates()) {

			for(Character c : alphabet.getList()) {
				nfaNextStates.add(newList(dfa.findNextState(state, c)));
			}
			
			if(dfa.getAcceptingStates().contains(state)) {
				nfaNextStates.add(nfaAcceptingStates);
				
			}else {
				nfaNextStates.add(newList());
				
			}
			
		}
		
		for(int i = 0; i < alphabet.size()+1; i++) {
			nfaNextStates.add(newList());
			
		}
		
		NFA nfa = new NFA(nfaStates, alphabet, nfaStartState, nfaNextStates, nfaAcceptingStates, epsilon);
		
		return nfa;
		
	}
	
	//Given an NFA, string, trace, and result, return whether when the string is run the the DFA, the Trace exists within the resulting tree and ends in the result
	public static boolean traceTest(NFA nfa, AlphaString string, Trace trace, Boolean result) {
		
		if(trace.size() != string.length()+1) {														//If trace.size() is not equal to the number of characters + 1
			return false;
			
		}
		
		if(trace.get(0) != nfa.getStartState()){													//If trace[0] is equal to q0
			return false;
			
		}
		
		for(int i = string.length(); i > 0; i--){
			if(!nfa.findNextStates(trace.get(i-1), string.getChar(i-1)).contains(trace.get(i))) {//If stateTable[trace[i-1][string[i-1]].contains(trace[index]) == false 
				return false;
				
			}
			
		}
		
	
		if(nfa.getAcceptingStates().contains(trace.get(string.length())) == result ) {				//If acceptingStates.contains(trace[string.length]) == result
			return true;
			
		}
																									//Otherwise
		return false;
		
	}
	
	//Given a string and an NFA, return whether that string is accepted by that NFA (Assuming the string and NFA share an alphabet)
	public static boolean stringTest(AlphaString string, NFA nfa, State curState, int index) {		
		boolean accept = false;
		
		ArrayList<State> branch = new ArrayList<State>();	
		ArrayList<State> temp = new ArrayList<State>();	
		
		if(string.length() > 0){																		//If testing the empty string, just check if startState is an accepting state	
			temp.clear();
			branch = nfa.findNextStates(curState, string.getChar(index));								//Get (curState, string[index])
			
			if(curState == nfa.getStartState() || nfa.findNextStates(nfa.getStartState(), epsilon).contains(curState)) {
				for(State epState : nfa.findNextStates(curState, epsilon)) {
					branch.addAll(nfa.findNextStates(epState, string.getChar(index)));
					
				}
			}
			
			temp.addAll(branch);
			
			for(State branchling : temp) {															//Search through branching states
				if(index < string.length()-1){
					accept = stringTest(string, nfa, branchling, index+1);								//Keep searching
					 
				}
				
				if(accept) {																			//If string is accepted											
					return true;																		//Return true and stop searching
					
				}
				
				if(nfa.getAcceptingStates().contains(branchling) && index == string.length()-1) {		//If curState is an accepting state and curState is at the bottom of the tree
					return true;																		//Return true and stop searching
					
				}
				
			}
																										//Otherwise
			return false;	
			
		}else{
			//return (nfa.getAcceptingStates().contains(curState));
			
			if(nfa.getAcceptingStates().contains(curState)) {
				return true;
				
			}else {
				for(State state : nfa.findNextStates(curState, epsilon)) {
					if(nfa.getAcceptingStates().contains(state)) {
						return true;
						
					}
					
				}
				
			}
			
			return false;
			
		}
		
	}
	
	//Union of 2 NFA's
	public static NFA union(NFA nfa1, NFA nfa2) {
		NFA snfa1 = new NFA(nfa1.getStates(), nfa1.getAlphabet(), nfa1.getStartState(), nfa1.getNextStates(), nfa1.getAcceptingStates(), epsilon);
		NFA snfa2 = new NFA(nfa2.getStates(), nfa2.getAlphabet(), nfa2.getStartState(), nfa2.getNextStates(), nfa2.getAcceptingStates(), epsilon);
		
		ArrayList<State> uStates = new ArrayList<State>();
		Alphabet alphabet = snfa1.getAlphabet();
		State uStartState = new State("uStart");
		ArrayList<ArrayList<State>> uNextStates = new ArrayList<ArrayList<State>>();
		ArrayList<State> uAcceptingStates = new ArrayList<State>();
		
		uStates.add(uStartState);												//uStates = {uStart
		uStates.addAll(snfa1.getStates());										//			 + [nfa1 States] 
		uStates.addAll(snfa2.getStates());										//			 + [nfa2 States]}
		
		for(int i = 0; i < snfa1.getAlphabet().size(); i++){
				uNextStates.add(newList());										//(uStartState, C e Sigma, {} )
				
		}
	
		uNextStates.add(newList(snfa1.getStartState(), snfa2.getStartState()));	//(uStartState, epsilon, {Start1, Start2} )
		
		uNextStates.addAll(snfa1.getNextStates());								//Preserve the transitions of the first NFA
		uNextStates.addAll(snfa2.getNextStates());								//As well as the second
		
		uAcceptingStates.addAll(snfa1.getAcceptingStates());						//uAcceptingStates = {[snfa1 Accepting States]
		uAcceptingStates.addAll(snfa2.getAcceptingStates());						//+[nfa2 Accepting States]}
		
		return new NFA(uStates, alphabet, uStartState, uNextStates, uAcceptingStates, epsilon);
		
	}
	
	//Concatenation of 2 NFA's
	public static NFA concatenation(NFA nfa1, NFA nfa2) {
		NFA snfa1 = new NFA(nfa1.getStates(), nfa1.getAlphabet(), nfa1.getStartState(), nfa1.getNextStates(), nfa1.getAcceptingStates(), epsilon);
		NFA snfa2 = new NFA(nfa2.getStates(), nfa2.getAlphabet(), nfa2.getStartState(), nfa2.getNextStates(), nfa2.getAcceptingStates(), epsilon);
		
		ArrayList<State> concatStates = new ArrayList<State>();
		Alphabet alphabet = snfa1.getAlphabet();
		State concatStartState = snfa1.getStartState();
		ArrayList<ArrayList<State>> concatNextStates = new ArrayList<ArrayList<State>>();
		ArrayList<State> concatAcceptingStates = new ArrayList<State>();
		
		for(State state : snfa1.getStates()) {																														//Qc = Q1 + Q2
			concatStates.add(new State(state.getIdentifier()));
			
		}
		
		for(State state : snfa2.getStates()) {
			concatStates.add(new State(state.getIdentifier()));
			
		}
		
		concatStartState = concatStates.get(snfa1.getStates().indexOf(snfa1.getStartState()));
		
		ArrayList<State> tran;
		
		for(int i = 0; i < snfa1.getNextStates().size(); i++) {
			tran = new ArrayList<State>();
			
			for(State state :  snfa1.getNextStates().get(i)) {
				tran.add(concatStates.get(snfa1.getStates().indexOf(state)));
				
			}
			
			for(State accepting : snfa1.getAcceptingStates()) {
				if((i) % (alphabet.size() + 1) == alphabet.size() && i == (snfa1.getStates().indexOf(accepting)) * (alphabet.size() + 1) + alphabet.size()) {
					tran.add(concatStates.get(snfa2.getStates().indexOf(snfa2.getStartState()) + snfa1.getStates().size()));											//([nfa1 Accept], epsilon, [nfa2 start])
					break;
					
				}
			
			}
			
			concatNextStates.add(tran);
			
		}
		
		for(int i = 0; i < snfa2.getNextStates().size(); i++) {
			tran = new ArrayList<State>();
			
			for(State state :  snfa2.getNextStates().get(i)) {
				tran.add(concatStates.get(snfa2.getStates().indexOf(state)  + snfa1.getStates().size()));
				
			}
			
			concatNextStates.add(tran);
			
		}
		
					
		for(State state : snfa2.getAcceptingStates()) {
			concatAcceptingStates.add(concatStates.get(snfa2.getStates().indexOf(state)  + snfa1.getStates().size()));												//Fc = F2
		
		}
		
		return new NFA(concatStates, alphabet, concatStartState, concatNextStates, concatAcceptingStates, epsilon);
		
	}
	
	//Given an NFA, return a new NFA that accepts a string that can be broken into N parts that are accepted by the given NFA
	public static NFA kleene(NFA nfa) {
		NFA snfa = new NFA(nfa.getStates(), nfa.getAlphabet(), nfa.getStartState(), nfa.getNextStates(), nfa.getAcceptingStates(), epsilon);
		
		ArrayList<State> kleeneStates = new ArrayList<State>();
		Alphabet alphabet = snfa.getAlphabet();
		State kleeneStartState = new State("kleene start");
		ArrayList<ArrayList<State>> kleeneNextStates = new ArrayList<ArrayList<State>>();
		ArrayList<State> kleeneAcceptingStates = new ArrayList<State>();
		
		kleeneStates.add(kleeneStartState);																//Qk = Q u {qk0}
		
		String id;
		
		for(State state : snfa.getStates()) {
			kleeneStates.add(new State(state.getIdentifier()));
			
		}
		
		for(int i = 0; i < alphabet.size(); i++){
			kleeneNextStates.add(newList());
			
		}
		
		kleeneNextStates.add(newList(kleeneStates.get(snfa.getStates().indexOf(snfa.getStartState()) + 1)));
		
		ArrayList<State> tran;
		
		for(int i = 0; i < snfa.getNextStates().size(); i++) {													//{[All states, besides kleene0, epsilon, kleene0]}
			tran = new ArrayList<State>();
			
			for(State state : snfa.getNextStates().get(i)) {
				tran.add(kleeneStates.get(snfa.getStates().indexOf(state) + 1));
				
			}
			
			if((i) % (alphabet.size() + 1) == alphabet.size() && i!= (snfa.getStates().indexOf(snfa.getStartState())) * (alphabet.size() + 1) + alphabet.size()) {
				tran.add(kleeneStates.get(snfa.getStates().indexOf(snfa.getStartState()) + 1));
				
			}
			
			kleeneNextStates.add(tran);
			
		}
		
		
		kleeneAcceptingStates.add(kleeneStartState);														//Fk = F u {qk0}
		
		for(State state : snfa.getAcceptingStates()) {
			kleeneAcceptingStates.add(kleeneStates.get(snfa.getStates().indexOf(state) + 1));
			
		}
		
		return new NFA(kleeneStates, alphabet, kleeneStartState, kleeneNextStates, kleeneAcceptingStates, epsilon);
		
	}
	
	//NFA -> DFA
	public static DFA nfaToDFA(NFA nfa) {
		NFA snfa = new NFA(nfa.getStates(), nfa.getAlphabet(), nfa.getStartState(), nfa.getNextStates(), nfa.getAcceptingStates(), epsilon);
		
		ArrayList<State> dfaStates = new ArrayList<State>();
		Alphabet alphabet = snfa.getAlphabet();
		State dfaStartState;
		ArrayList<State> dfaNextStates = new ArrayList<State>();
		ArrayList<State> dfaAcceptingStates = new ArrayList<State>();
		
		ArrayList<State> start = new ArrayList<State>(Arrays.asList(snfa.getStartState()));
		start.addAll(snfa.findNextStates(snfa.getStartState(), epsilon));
		
		ArrayList<ArrayList<State>> states = new ArrayList<ArrayList<State>>();
		
		ArrayList<ArrayList<State>> nextStates = new ArrayList<ArrayList<State>>();
		
		states.add(start);
		
		ArrayList<State> newState = new ArrayList<State>();
		
		int index = 0;
		
		String id = "";
		
		while(index < states.size()) {														
			for(Character c : alphabet.getList()) {
				newState  = new ArrayList<State>();
			
				for(State state : states.get(index)){
					for(State tran : snfa.findNextStates(state, c)) {
						if(!newState.contains(tran)){
							newState.add(tran);								//Essentially combine states based on the transitions. 
	
						}
						
					}
					
				}
				
				nextStates.add(newState);									//New transition (and state) based on the transition function of the given NFA
				
				if(!states.contains(newState)) {							//If state transitioned to is not already in Qdfa
					states.add(newState);									//Add it		

				}
				
			}
			
			index++;
			
		}
		
		for(ArrayList<State> element : states){
			id = "";
			
			for(State state : element){
				id += state.getIdentifier() ;
				
			}
			
			dfaStates.add(new State(id));
			
		}
		
		dfaStartState = dfaStates.get(0);
		
		for(ArrayList<State> next : nextStates) {
			dfaNextStates.add(dfaStates.get(states.indexOf(next)));
			
		}
		
		for(ArrayList<State> element : states) {
			for(State state : element) {
				if(snfa.getAcceptingStates().contains(state)) {
					dfaAcceptingStates.add(dfaStates.get(states.indexOf(element)));	//Accepting states are any states that contain one of the accepting states of the NFA
					break;
				}
				
			}
			
		}
		
		return new DFA(dfaStates, alphabet, dfaStartState, dfaNextStates, dfaAcceptingStates);
	
	}
	
	//Function that given a DFA, returns a string that is accepted by that DFA
	public static AlphaString acceptingString(DFA dfa){
		if(dfa.getAcceptingStates().isEmpty()) {
			return null;
			
		}
		
		AlphaString string = new AlphaString(dfa.getAlphabet());
		
		State  curState = null;
		boolean foundAccepting = false;
		boolean foundNew = false;
		int depth = 0;
		
		ArrayList<ArrayList<State>> states = new ArrayList<ArrayList<State>>();
		ArrayList<ArrayList<Character>> chars = new ArrayList<ArrayList<Character>>();
		states.add(new ArrayList<State>(Arrays.asList(dfa.getStartState())));
		
		for(int i = 0 ; i < dfa.getStates().size(); i++) {
			states.add(new ArrayList<State>());
			chars.add(new ArrayList<Character>());
			
			for(State state : states.get(states.size()-2)) {
				for(Character c : dfa.getAlphabet().getList()) {
					states.get(states.size()-1).add(dfa.findNextState(state, c));
					chars.get(chars.size()-1).add(c);
					
				}
				
			}
			
		}
		
		for(int i = states.size() -1 ; i >= 0 ; i--) {
			for(State state : states.get(i)) {
				if(dfa.getAcceptingStates().contains(state)) {
					curState = state;
					foundAccepting = true;
					depth = i;
				}
				
				if(foundAccepting){
					break;
					
				}
				
			}
			
		}
		
		if(!foundAccepting) {
			return null;
			
		}
		
		while(curState != dfa.getStartState()) {
			
			foundNew = false;
			
			for(State state : states.get(depth-1)) {
				for(Character character : dfa.getAlphabet().getList()) {
					if(dfa.findNextState(state, character) == curState && state != curState){
						curState = state;
						foundNew = true;
						string.pushChar(character);
						break;
						
					}
				
				}
				
				if(foundNew) {
					depth--;
					break;
					
				}
				
			}
			
		}
		
		return string;
		
	}
	
	//~(dfa1)
	public static DFA complement(DFA dfa){
		ArrayList<State> complementAcceptingStates = new ArrayList<State>();
		
		for(int i = 0; i < dfa.getStates().size(); i++){
			if(!(dfa.getAcceptingStates().contains(dfa.getStates().get(i)))){
				complementAcceptingStates.add(dfa.getStates().get(i));			//complementAcceptingStates = dfaStates - dfaAcceptingStates
				
			}
			
		}
		
		DFA complement = new DFA(dfa.getStates(), dfa.getAlphabet(), dfa.getStartState(), dfa.getNextStates(), complementAcceptingStates);
		
		return complement;
		
	}
	
	//(dfa1 u dfa2)
	public static DFA union(DFA dfa1, DFA dfa2){
		ArrayList<State> unionStates = new ArrayList<State>();
		Alphabet alphabet = dfa1.getAlphabet();
		State unionStartState;
		ArrayList<State> unionNextStates = new ArrayList<State>();
		ArrayList<State> unionAcceptingStates = new ArrayList<State>();
		
		for(int i = 0; i < dfa1.getStates().size(); i++){
			for(int j = 0; j < dfa2.getStates().size(); j++){
				unionStates.add( new State( dfa1.getStates().get(i).getIdentifier() + dfa2.getStates().get(j).getIdentifier() ) );	//|Q| = |Qa| * |Qb|
				
			}
			
		}
		
		unionStartState = unionStates.get(dfa2.getStates().size() * dfa1.getStates().indexOf( dfa1.getStartState() ) +  dfa2.getStates().indexOf( dfa2.getStartState() ) );	//q0 = qa0 + qb0
		
		State dfa1NextState;
		State dfa2NextState;
		
		for(int i = 0; i < dfa1.getStates().size(); i++){
			for(int j = 0; j < dfa2.getStates().size(); j++){
				for(int k = 0; k < alphabet.size(); k++){
					dfa1NextState = dfa1.getNextStates().get(alphabet.size() * i + k);
					dfa2NextState = dfa2.getNextStates().get(alphabet.size() * j + k);
				    
					int nextIndex = (dfa2.getStates().size() * dfa1.getStates().indexOf(dfa1NextState) + dfa2.getStates().indexOf(dfa2NextState) );	//Delta = {( qi+j, c, ((qai, c)+(qaj, c)) )}
					
					unionNextStates.add(unionStates.get(nextIndex));
				
				}
				
			}
			
		}
		
		State dfa1CurState;
		State dfa2CurState;
		
		for(int i = 0; i < dfa1.getStates().size(); i++){
			dfa1CurState = dfa1.getStates().get(i);
			
			for(int j = 0; j < dfa2.getStates().size(); j++){
				dfa2CurState = dfa2.getStates().get(j);
				
				if(dfa1.getAcceptingStates().contains(dfa1CurState) || dfa2.getAcceptingStates().contains(dfa2CurState)){
					unionAcceptingStates.add(unionStates.get(dfa2.getStates().size() * i + j));	//F = {qa e Qa + qb e Qb | qa e Fa or qb e Fb}
					
				}
				
			}
			
		}
		
		DFA union = new DFA(unionStates, alphabet, unionStartState, unionNextStates, unionAcceptingStates);
		
		return union;
	}
	//(dfa1 n dfa2)
	public static DFA intersection(DFA dfa1, DFA dfa2){
		ArrayList<State> intersectionStates = new ArrayList<State>();
		Alphabet alphabet = dfa1.getAlphabet();
		State intersectionStartState;
		ArrayList<State> intersectionNextStates = new ArrayList<State>();
		ArrayList<State> intersectionAcceptingStates = new ArrayList<State>();
		
		for(int i = 0; i < dfa1.getStates().size(); i++){
			for(int j = 0; j < dfa2.getStates().size(); j++){
				intersectionStates.add( new State( dfa1.getStates().get(i).getIdentifier() + dfa2.getStates().get(j).getIdentifier() ) );	//|Q| = |Qa| * |Qb|
				
			}
			
		}
		
		intersectionStartState = intersectionStates.get(alphabet.size() * dfa1.getStates().indexOf( dfa1.getStartState() ) +  dfa2.getStates().indexOf( dfa2.getStartState() ) );	//q0 = qa0 + qb0
		
		State dfa1NextState;
		State dfa2NextState;
		
		for(int i = 0; i < dfa1.getStates().size(); i++){
			for(int j = 0; j < dfa2.getStates().size(); j++){
				for(int k = 0; k < alphabet.size(); k++){
					dfa1NextState = dfa1.getNextStates().get(alphabet.size() * i + k);
					dfa2NextState = dfa2.getNextStates().get(alphabet.size() * j + k);
				    
					int nextIndex = (dfa2.getStates().size() * dfa1.getStates().indexOf(dfa1NextState) + dfa2.getStates().indexOf(dfa2NextState) );	//( qi+j, c, ((qai, c)+(qaj, c)) )
					
					intersectionNextStates.add(intersectionStates.get(nextIndex));
				
				}
				
			}
			
		}
		
		
		State dfa1CurState;
		State dfa2CurState;
		int q = 0;
		for(int i = 0; i < dfa1.getStates().size(); i++){
			dfa1CurState = dfa1.getStates().get(i);
			
			for(int j = 0; j < dfa2.getStates().size(); j++){
				dfa2CurState = dfa2.getStates().get(j);
				
				if(dfa1.getAcceptingStates().contains(dfa1CurState) && dfa2.getAcceptingStates().contains(dfa2CurState)){
					intersectionAcceptingStates.add( intersectionStates.get( (dfa2.getStates().size() * i) + j) );	//F = {qa e Qa + qb e Qb | qa e Fa and qb e Fb}
				
				}
				
			}
			
		}
		
		DFA intersection = new DFA(intersectionStates, alphabet, intersectionStartState, intersectionNextStates, intersectionAcceptingStates);
		
		return intersection;
		
	}
	
	//(dfa1 c dfa2)?
	public static boolean subset(DFA dfa1, DFA dfa2){
		DFA dfa2Compliment = complement(dfa2);				   		//~(dfa2)
		DFA dfaIntersection = intersection(dfa1, dfa2Compliment);	//dfa1 n ~(dfa2)
		
		
		return (acceptingString(dfaIntersection) == null);					   
	}
	
	//(dfa1 == dfa2)?
	public static boolean dfaEqual(DFA dfa1, DFA dfa2) {
		return(subset(dfa1, dfa2) && subset(dfa2, dfa1)); 			//(dfa1 c dfa2) and (dfa2 c dfa1) -> dfa1 == dfa2
		
	}

	
}
