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
		
	//NFA that accepts odd binary #s (The "oddNFA")-------------------------------------------------------------------------
		private static ArrayList<State> oddNFAStates = new ArrayList<State>(Arrays.asList(new State("odd0"), new State("odd1")));						//Q = {A, B}
		
		private static State oddNFAStartState = oddNFAStates.get(0);																			//q0 = A
		
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
	//------------------------------------------------------------------------------------------------------------------
			
	//NFA that accepts a binary number that only contains 1's or where the last character is zero (The "ozLastNFA")---------				
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
	//------------------------------------------------------------------------------------------------------------------
			
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
	public static void main(String[] args) {  	
		String accepts;
		
		AlphaString string1 = new AlphaString(biAlphabet,  new ArrayList<Character>(Arrays.asList(biAlphabet.get(0), biAlphabet.get(1), biAlphabet.get(0), biAlphabet.get(1))));
		AlphaString string2 = new AlphaString(biAlphabet,  new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(1), biAlphabet.get(1))));
		AlphaString string3 = new AlphaString(biAlphabet,  new ArrayList<Character>(Arrays.asList(biAlphabet.get(0), biAlphabet.get(0), biAlphabet.get(0))));	
		
		System.out.print("Generate Trace Trees: \n");
		
		System.out.print("	oddNFA:\n");
		for(int i = 0; i < testStrings.length; i++) {
			oddNFA.run(testStrings[i]);
			System.out.print("	Created trace tree for oddNFA and string '" + testStrings[i].displayable() + "'\n");
			
		}
		
		System.out.print("\n");
		
		System.out.print("	evenNFA:\n");
		for(int i = 0; i < testStrings.length; i++) {
			evenNFA.run(testStrings[i]);
			System.out.print("	Created trace tree for evenNFA and string '" + testStrings[i].displayable() + "'\n");
			
		}
		
		System.out.print("\n");
		
		System.out.print("	oddLNFA:\n");
		for(int i = 0; i < testStrings.length; i++) {
			oddLNFA.run(testStrings[i]);
			System.out.print("	Created trace tree for oddLNFA and string '" + testStrings[i].displayable() + "'\n");
			
		}
		
		System.out.print("\n");
		
		System.out.print("	evenLNFA:\n");
		for(int i = 0; i < testStrings.length; i++) {
			evenLNFA.run(testStrings[i]);
			System.out.print("	Created trace tree for evenLNFA and string '" + testStrings[i].displayable() + "'\n");
			
		}
		
		System.out.print("\n");
		
		System.out.print("	stNFA:\n");
		for(int i = 0; i < testStrings.length; i++) {
			stNFA.run(testStrings[i]);
			System.out.print("	Created trace tree for stNFA and string '" + testStrings[i].displayable() + "'\n");
			
		}
		
		System.out.print("\n");
		
		System.out.print("	zzNFA:\n");
		for(int i = 0; i < testStrings.length; i++) {
			zzNFA.run(testStrings[i]);
			System.out.print("	Created trace tree for zzNFA and string '" + testStrings[i].displayable() + "'\n");
			
		}
		
		System.out.print("\n");
		
		System.out.print("	ooNFA:\n");
		for(int i = 0; i < testStrings.length; i++) {
			ooNFA.run(testStrings[i]);
			System.out.print("	Created trace tree for ooNFA and string '" + testStrings[i].displayable() + "'\n");
			
		}
		
		System.out.print("\n");
		
		System.out.print("	zOroNFA:\n");
		for(int i = 0; i < testStrings.length; i++) {
			zOroNFA.run(testStrings[i]);
			System.out.print("	Created trace tree for zOroNFA and string '" + testStrings[i].displayable() + "'\n");
			
		}
		
		System.out.print("\n");
		
		System.out.print("	zOroEndNFA:\n");
		for(int i = 0; i < testStrings.length; i++) {
			zOroEndNFA.run(testStrings[i]);
			System.out.print("	Created trace tree for zOroEndNFA and string '" + testStrings[i].displayable() + "'\n");
			
		}
		
		System.out.print("\n");
		
		System.out.print("	zoNFA:\n");
		for(int i = 0; i < testStrings.length; i++) {
			zoNFA.run(testStrings[i]);
			System.out.print("	Created trace tree for zoNFA and string '" + testStrings[i].displayable() + "'\n");
			
		}
		
		System.out.print("\n");
		
		System.out.print("	ozLastNFA:\n");
		for(int i = 0; i < testStrings.length; i++) {
			ozLastNFA.run(testStrings[i]);
			System.out.print("	Created trace tree for ozLastNFA and string '" + testStrings[i].displayable() + "'\n");
			
		}
		
		System.out.print("\n");
		
		System.out.print("	allNFA:\n");
		for(int i = 0; i < testStrings.length; i++) {
			allNFA.run(testStrings[i]);
			System.out.print("	Created trace tree for allNFA and string '" + testStrings[i].displayable() + "'\n");
			
		}
		
		System.out.print("\n");
		
		System.out.print("Test NFA's: \n");
		
		System.out.print("	oddNFA:\n");
		for(int i = 0; i < testStrings.length; i++) {
			System.out.print("	oddNFA ('" + testStrings[i].displayable() + "') = " + oddNFA.run(testStrings[i]) + " \n");
			
		}
		
		System.out.print("\n");
		
		System.out.print("	evenNFA:\n");
		for(int i = 0; i < testStrings.length; i++) {
			System.out.print("	evenNFA ('" + testStrings[i].displayable() + "') = " + evenNFA.run(testStrings[i]) + " \n");
			
		}
		
		System.out.print("\n");
		
		System.out.print("	oddLNFA:\n");
		for(int i = 0; i < testStrings.length; i++) {
			System.out.print("	oddLNFA ('" + testStrings[i].displayable() + "') = " + oddLNFA.run(testStrings[i]) + " \n");
			
		}
		
		System.out.print("\n");
		
		System.out.print("	evenLNFA:\n");
		for(int i = 0; i < testStrings.length; i++) {
			System.out.print("	evenLNFA ('" + testStrings[i].displayable() + "') = " + evenLNFA.run(testStrings[i]) + " \n");
			
		}
		
		System.out.print("\n");
		
		System.out.print("	stNFA:\n");
		for(int i = 0; i < testStrings.length; i++) {
			System.out.print("	stNFA ('" + testStrings[i].displayable() + "') = " + stNFA.run(testStrings[i]) + " \n");
			
		}
		
		System.out.print("\n");
		
		System.out.print("	zzNFA:\n");
		for(int i = 0; i < testStrings.length; i++) {
			System.out.print("	zzNFA ('" + testStrings[i].displayable() + "') = " + zzNFA.run(testStrings[i]) + " \n");
			
		}
		
		System.out.print("\n");
		
		System.out.print("	ooNFA:\n");
		for(int i = 0; i < testStrings.length; i++) {
			System.out.print("	ooNFA ('" + testStrings[i].displayable() + "') = " + ooNFA.run(testStrings[i]) + " \n");
			
		}
		
		System.out.print("\n");
		
		System.out.print("	zOroNFA:\n");
		for(int i = 0; i < testStrings.length; i++) {
			System.out.print("	zOroNFA ('" + testStrings[i].displayable() + "') = " + zOroNFA.run(testStrings[i]) + " \n");
			
		}
		
		System.out.print("\n");
		
		System.out.print("	zOroEndNFA:\n");
		for(int i = 0; i < testStrings.length; i++) {
			System.out.print("	zOroEndNFA ('" + testStrings[i].displayable() + "') = " + zOroEndNFA.run(testStrings[i]) + " \n");
			
		}
		
		System.out.print("\n");
		
		System.out.print("	zoNFA:\n");
		for(int i = 0; i < testStrings.length; i++) {
			System.out.print("	zoNFA ('" + testStrings[i].displayable() + "') = " + zoNFA.run(testStrings[i]) + " \n");
			
		}
		
		System.out.print("\n");
		
		System.out.print("	ozLastNFA:\n");
		for(int i = 0; i < testStrings.length; i++) {
			System.out.print("	ozLastNFA ('" + testStrings[i].displayable() + "') = " + ozLastNFA.run(testStrings[i]) + " \n");
			
		}
		
		System.out.print("\n");
		
		System.out.print("	allNFA:\n");
		for(int i = 0; i < testStrings.length; i++) {
			System.out.print("	allNFA ('" + testStrings[i].displayable() + "') = " + allNFA.run(testStrings[i]) + " \n");
			
		}
		
		System.out.print("\n");
	
	
		/*accepts = (oddNFA.run(string2))? "String Accepted":"String Rejected";	
		System.out.println("\n" + accepts);
		
		accepts = (dfaToNFA(oddDFA).run(string2))? "String Accepted":"String Rejected";	
		System.out.println("\n" + accepts);
		
		accepts = (nfaToDFA(evenNFA).run(string2))? "String Accepted":"String Rejected";	
		System.out.println("\n" + accepts);
		
		accepts = (nfaToDFA(oddNFA).run(string2))? "String Accepted":"String Rejected";	
		System.out.println("\n" + accepts);
		
		NFA test = kleene(oddNFA);
		
		accepts = (stringTest(string2,test, test.getStartState(), 0))? "String Accepted":"String Rejected";	
		System.out.println("\n" + accepts);
		
		test = kleene(allNFA);
		
		accepts = (stringTest(string2,test, test.getStartState(), 0))? "String Accepted":"String Rejected";	
		System.out.println("\n" + accepts);
		
		accepts = (stringTest(string1, zzoNFA, zzoNFA.getStartState(), 0))? "String Accepted":"String Rejected";	
		System.out.println("\n" + accepts);*/
		
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
		
		nfaStates.add(new State("start"));
		nfaStates.addAll(dfa.getStates());
		nfaStates.add(new State("accept"));
		
		ArrayList<ArrayList<State>> nfaNextStates = new ArrayList<ArrayList<State>>();
		ArrayList<State> nfaAcceptingStates = new ArrayList<State>();
		
		State nfaStartState = nfaStates.get(0);
		nfaAcceptingStates.add(nfaStates.get(nfaStates.size()-1));
		
		for(Character c : dfa.getAlphabet().getList()) {
			nfaNextStates.add(newList());
		}
		
		nfaNextStates.add(newList(dfa.getStartState()));
		
		for(State state : dfa.getStates()) {

			for(Character c : dfa.getAlphabet().getList()) {
				nfaNextStates.add(newList(dfa.findNextState(state, c)));
			}
			
			if(dfa.getAcceptingStates().contains(state)) {
				nfaNextStates.add(nfaAcceptingStates);
				
			}else {
				nfaNextStates.add(newList());
				
			}
			
		}
		
		for(int i = 0; i < dfa.getAlphabet().size()+1; i++) {
			nfaNextStates.add(newList());
			
		}
		
		NFA nfa = new NFA(nfaStates, dfa.getAlphabet(), nfaStartState, nfaNextStates, nfaAcceptingStates, epsilon);
		
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
			if(!nfa.findNextStates(trace.get(i-1), string.getChar(i-1)).contains(trace.get(i))) {//If stateTable[trace[i-1][string[i-1]].contains(trace[i]) == false 
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
		
		if(string.length() != 0){																		//If testing the empty string, just check if startState is an accepting state	
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
			return (nfa.getAcceptingStates().contains(curState) && index == string.length()-1);
			
		}
		
	}
	
	//Union of 2 NFA's
	public static NFA union(NFA nfa1, NFA nfa2) {
		ArrayList<State> uStates = new ArrayList<State>();
		Alphabet alphabet = nfa1.getAlphabet();
		State uStartState = new State("uStart");
		ArrayList<ArrayList<State>> uNextStates = new ArrayList<ArrayList<State>>();
		ArrayList<State> uAcceptingStates = new ArrayList<State>();
		
		uStates.add(uStartState);												//uStates = {uStart
		uStates.addAll(nfa1.getStates());										//			 + [nfa1 States] 
		uStates.addAll(nfa2.getStates());										//			 + [nfa2 States]}
		
		for(int i = 0; i < nfa1.getAlphabet().size(); i++){
				uNextStates.add(newList());										//(uStartState, C e Sigma, {} )
				
		}
	
		uNextStates.add(newList(nfa1.getStartState(), nfa2.getStartState()));	//(uStartState, epsilon, {Start1, Start2} )
		
		uNextStates.addAll(nfa1.getNextStates());								//Preserve the transitions of the first NFA
		uNextStates.addAll(nfa2.getNextStates());								//As well as the second
		
		uAcceptingStates.addAll(nfa1.getAcceptingStates());						//uAcceptingStates = {[nfa1 Accepting States]
		uAcceptingStates.addAll(nfa2.getAcceptingStates());						//+[nfa2 Accepting States]}
		
		return new NFA(uStates, alphabet, uStartState, uNextStates, uAcceptingStates, epsilon);
		
	}
	
	//Concatenation of 2 NFA's
	public static NFA concatenation(NFA nfa1, NFA nfa2) {
		ArrayList<State> concatStates = new ArrayList<State>();
		Alphabet alphabet = nfa1.getAlphabet();
		State concatStartState = nfa1.getStartState();
		ArrayList<ArrayList<State>> concatNextStates = new ArrayList<ArrayList<State>>();
		ArrayList<State> concatAcceptingStates = new ArrayList<State>();
		
		concatStates.addAll(nfa1.getStates());
		concatStates.addAll(nfa2.getStates());
		
		concatNextStates.addAll(nfa1.getNextStates());
		concatNextStates.addAll(nfa2.getNextStates());													//Qu = Q1 + Q2
		
		int epsilonIndex;
		ArrayList<State> epsilonTran;
		
		for(State accept : nfa1.getAcceptingStates()) {
			epsilonIndex = (nfa1.getStates().indexOf(accept))*(alphabet.size()+1) + alphabet.size();
			
			epsilonTran = nfa1.getNextStates().get(epsilonIndex);
			epsilonTran.add(nfa2.getStartState());
			
			concatNextStates.set(epsilonIndex, epsilonTran);											//([nfa1 Accept], epsilon, [nfa2 start])
			
		}
		
		concatAcceptingStates.addAll(nfa2.getAcceptingStates());										//Fu = F2
		
		return new NFA(concatStates, alphabet, concatStartState, concatNextStates, concatAcceptingStates, epsilon);
		
	}
	
	//Given an NFA, return a new NFA that accepts a string that can be broken into N parts that are accepted by the given NFA
	public static NFA kleene(NFA nfa) {
		ArrayList<State> kleeneStates = new ArrayList<State>();
		Alphabet alphabet = nfa.getAlphabet();
		State kleeneStartState = new State("kleene 0");
		ArrayList<ArrayList<State>> kleeneNextStates = new ArrayList<ArrayList<State>>();
		ArrayList<State> kleeneAcceptingStates = new ArrayList<State>();
		
		kleeneStates.add(kleeneStartState);																//Qk = Q u {qk0}
		kleeneStates.addAll(nfa.getStates());
		
		for(int i = 0; i < alphabet.size(); i++){
			kleeneNextStates.add(newList());
			
		}
		
		kleeneNextStates.add(newList(nfa.getStartState()));
		
		kleeneNextStates.addAll(nfa.getNextStates());
		
		int epsilonIndex;
		ArrayList<State> epsilonTran;
		
		for(int i = 1; i < kleeneStates.size(); i++) {														//([all states except q0], epsilon, q0)
			epsilonIndex = i*(alphabet.size() + 1) + alphabet.size();
			epsilonTran = kleeneNextStates.get(epsilonIndex);
			
			if(!epsilonTran.contains(nfa.getStartState()) && kleeneStates.get(i) != nfa.getStartState()){
				epsilonTran.add(nfa.getStartState());
				kleeneNextStates.set(epsilonIndex, epsilonTran);
				
			}
			
		}
		
		kleeneAcceptingStates.add(kleeneStartState);														//Fk = F u {qk0}
		kleeneAcceptingStates.addAll(nfa.getAcceptingStates());
		
		return new NFA(kleeneStates, alphabet, kleeneStartState, kleeneNextStates, kleeneAcceptingStates, epsilon);
		
	}
	
	//NFA -> DFA
	public static DFA nfaToDFA(NFA nfa) {
		NFA snfa = nfa;
		
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
	
}
