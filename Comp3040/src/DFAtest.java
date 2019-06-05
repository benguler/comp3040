import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

//Create and test various DFAs and DFA-related functions

public class DFAtest {
	
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

	//DFA that accepts no strings (The "noDFA")----------------------------------------------------------------------------
		private static ArrayList<Character> noTestCharacters =  new ArrayList<Character>(Arrays.asList(biAlphabet.get(0), biAlphabet.get(1)));
		private static AlphaString noTestString = new AlphaString(biAlphabet, noTestCharacters);												//Input = "01"
		
		private static ArrayList<State> noDFAStates = new ArrayList<State>(Arrays.asList(new State("A")));										//Q = {A}
		
		private static State noDFAStartState = noDFAStates.get(0);																				//q0 = A
		
		private static ArrayList<State> noDFANextStates = new ArrayList<State>(Arrays.asList(noDFAStates.get(0), noDFAStates.get(0)));			//Delta = {(A, '0', A), (A, '1', A)
		
		private static ArrayList<State> noDFAAcceptingStates = new ArrayList<State>();															//F = {}
		
		private static DFA noDFA = new DFA(noDFAStates, biAlphabet, noDFAStartState, noDFANextStates, noDFAAcceptingStates);
	//----------------------------------------------------------------------------------------------------------------------
	
	//DFA that accepts only the empty string (The "emptyDFA")---------------------------------------------------------------
		private static ArrayList<Character> emptyTestCharacters = new ArrayList<Character>();
		private static AlphaString emptyTestString =  new AlphaString(biAlphabet, emptyTestCharacters);											//input = ""
		
		private static ArrayList<State> emptyDFAStates = new ArrayList<State>(Arrays.asList(new State("A"), new State("B")));					//Q = {A, B}
		
		private static State emptyDFAStartState = emptyDFAStates.get(0);																		//qo = A
		
		private static ArrayList<State> emptyDFANextStates = new ArrayList<State>(Arrays.asList(emptyDFAStates.get(1), emptyDFAStates.get(1),	//Delta = {(A, '0', B), (A, '1', B)
																								emptyDFAStates.get(1), emptyDFAStates.get(1)));			 //(B, '0', B), (B, '1', B)}
		
		private static ArrayList<State> emptyDFAAcceptingStates = new ArrayList<State>(Arrays.asList(emptyDFAStates.get(0)));					//F = {A}
		
		private static DFA emptyDFA = new DFA(emptyDFAStates, biAlphabet, emptyDFAStartState, emptyDFANextStates, emptyDFAAcceptingStates);
	//----------------------------------------------------------------------------------------------------------------------
	
	//DFA that accepts only the character "n" in an alphabet (The "nDFA")---------------------------------------------------
		private static Character abcTestCharacter = abcAlphabet.get(2);																			//Input is "c"
		
		private static DFA nDFA = nDFA(abcAlphabet, abcAlphabet.get(2));																		//Generate a DFA that will only accept the string "c"
	//----------------------------------------------------------------------------------------------------------------------
		
	//DFA that accepts odd binary #s (The "oddDFA")-------------------------------------------------------------------------
		private static AlphaString oddTestStrings[] = new AlphaString[]{
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
		
		private static ArrayList<State> oddDFAStates = new ArrayList<State>(Arrays.asList(new State("A"), new State("B")));						//Q = {A, B}
		
		private static State oddDFAStartState =oddDFAStates.get(0);																				//q0 = A
		
		private static ArrayList<State>oddDFANextStates = new ArrayList<State>(Arrays.asList(oddDFAStates.get(0), oddDFAStates.get(1),			//Delta = {(A, '0', A), (A, '1', B)
																		                     oddDFAStates.get(0), oddDFAStates.get(1)));		//(B, '0', A), (B, '1', B)
		
		private static ArrayList<State>oddDFAAcceptingStates = new ArrayList<State>(Arrays.asList(oddDFAStates.get(1)));						//F = {B}
		
		private static DFA oddDFA = new DFA(oddDFAStates, biAlphabet,oddDFAStartState,oddDFANextStates,oddDFAAcceptingStates);
	//----------------------------------------------------------------------------------------------------------------------
		
	//DFA that accepts even binary #s (The "evenDFA")-------------------------------------------------------------------------
		private static AlphaString evenTestStrings[] = new AlphaString[]{
				new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(0), biAlphabet.get(0), biAlphabet.get(0)))),							//000
			    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(0), biAlphabet.get(0), biAlphabet.get(1)))),							//001
			    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(0), biAlphabet.get(1), biAlphabet.get(0)))),							//010
			    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(0), biAlphabet.get(1)))),							//011
			    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(0), biAlphabet.get(0)))),							//100
			    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(1), biAlphabet.get(1)))),							//111
			    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(1), biAlphabet.get(0)))),							//110
			    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(0), biAlphabet.get(0), biAlphabet.get(1)))),		//1001
			    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(0), biAlphabet.get(0), biAlphabet.get(0)))),		//1000
			    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(0), biAlphabet.get(1), biAlphabet.get(1)))),		//1011
			    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(0), biAlphabet.get(1), biAlphabet.get(0)))),		//1000
			    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(1), biAlphabet.get(0), biAlphabet.get(1))))		//1101
		};
				
		private static ArrayList<State> evenDFAStates = new ArrayList<State>(Arrays.asList(new State("A"), new State("B")));						//Q = {A, B}
				
		private static State evenDFAStartState =evenDFAStates.get(0);																				//q0 = A
				
		private static ArrayList<State>evenDFANextStates = new ArrayList<State>(Arrays.asList(evenDFAStates.get(1), evenDFAStates.get(0),			//Delta = {(A, '0', B), (A, '1', A)
																				              evenDFAStates.get(1), evenDFAStates.get(0)));			//(B, '0', B), (B, '1', A)
				
		private static ArrayList<State>evenDFAAcceptingStates = new ArrayList<State>(Arrays.asList(evenDFAStates.get(1)));							//F = {B}
				
		private static DFA evenDFA = new DFA(evenDFAStates, biAlphabet,evenDFAStartState,evenDFANextStates,evenDFAAcceptingStates);
	//----------------------------------------------------------------------------------------------------------------------
		
	//DFA that accepts 3 bit binary #s (The "tbDFA")------------------------------------------------------------------------
			private static AlphaString tbTestStrings[] = new AlphaString[]{
					new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(0), biAlphabet.get(0), biAlphabet.get(0)))),						//000	
				    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(0), biAlphabet.get(0), biAlphabet.get(1)))),						//001
				    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(0), biAlphabet.get(1), biAlphabet.get(0)))),						//010
				    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(1), biAlphabet.get(0), biAlphabet.get(0)))),	//1100
				    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(0), biAlphabet.get(0), biAlphabet.get(1)))),	//1001
				    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(0), biAlphabet.get(0), biAlphabet.get(0)))),	//1000
				    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(0), biAlphabet.get(1)))),						//101
				    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(0), biAlphabet.get(0)))),						//100
				    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(1), biAlphabet.get(1)))),						//111
				    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(0), biAlphabet.get(1), biAlphabet.get(1)))),	//1011
				    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(0), biAlphabet.get(1), biAlphabet.get(0)))),	//1010
				    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(1), biAlphabet.get(0), biAlphabet.get(1))))	//1101
			};
			
			private static ArrayList<State> tbDFAStates = new ArrayList<State>(Arrays.asList(new State("A"), new State("B"), new State("C"), 
																											 new State("D"), new State("E")));		//Q = {A, B, C, D, E}
			
			private static State tbDFAStartState =tbDFAStates.get(0);																				//q0 = A
			
			private static ArrayList<State>tbDFANextStates = new ArrayList<State>(Arrays.asList(tbDFAStates.get(1),tbDFAStates.get(1),				//Delta = {(A, '0', B), (A, '1', B)
																			                    tbDFAStates.get(2),tbDFAStates.get(2),				//(B, '0', C), (B, '1', C)
																			                    tbDFAStates.get(3),tbDFAStates.get(3),				//(C, '0', D), (C, '1', D)
																			                    tbDFAStates.get(4),tbDFAStates.get(4),				//(D, '0', E), (D, '1', E)
			 																					tbDFAStates.get(4),tbDFAStates.get(4)));			//(E, '0', E), (E, '1', E)
			
			private static ArrayList<State>tbDFAAcceptingStates = new ArrayList<State>(Arrays.asList( tbDFAStates.get(3)));							//F = {D}
			
			private static DFA tbDFA = new DFA(tbDFAStates, biAlphabet,tbDFAStartState,tbDFANextStates,tbDFAAcceptingStates) ;
	//----------------------------------------------------------------------------------------------------------------------
			
	//DFA that accepts even length binary #s (The "elDFA")------------------------------------------------------------------
			private static AlphaString elTestStrings[] = new AlphaString[]{
					new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(0), biAlphabet.get(0), biAlphabet.get(0)))),						//000	
				    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(0), biAlphabet.get(0), biAlphabet.get(1)))),						//001
				    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(0), biAlphabet.get(1), biAlphabet.get(0)))),						//010
				    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(1), biAlphabet.get(0), biAlphabet.get(0)))),	//1100
				    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(0), biAlphabet.get(0), biAlphabet.get(1)))),	//1001
				    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(0), biAlphabet.get(0), biAlphabet.get(0)))),	//1000
				    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(0), biAlphabet.get(1)))),						//101
				    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(0), biAlphabet.get(0)))),						//100
				    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(1), biAlphabet.get(1)))),						//111
				    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(0), biAlphabet.get(1), biAlphabet.get(1)))),	//1011
				    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(0), biAlphabet.get(1), biAlphabet.get(0)))),	//1010
				    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(1), biAlphabet.get(0), biAlphabet.get(1))))	//1101
			};
			
			private static ArrayList<State> elDFAStates = new ArrayList<State>(Arrays.asList(new State("A"), new State("B")));						//Q = {A, B}
			
			private static State elDFAStartState =elDFAStates.get(0);																				//q0 = A
			
			private static ArrayList<State>elDFANextStates = new ArrayList<State>(Arrays.asList(elDFAStates.get(1),elDFAStates.get(1),				//Delta = {(A, '0', B), (A, '1', B)
																			                    elDFAStates.get(0),elDFAStates.get(0)));			//(B, '0', A), (B, '1', A)
																			                   
			
			private static ArrayList<State>elDFAAcceptingStates = new ArrayList<State>(Arrays.asList( elDFAStates.get(0)));							//F = {A}
			
			private static DFA elDFA = new DFA(elDFAStates, biAlphabet,elDFAStartState,elDFANextStates,elDFAAcceptingStates);
	//----------------------------------------------------------------------------------------------------------------------
			
	//DFA that accepts odd length binary #s (The olDFA")-------------------------------------------------------------------
			private static AlphaString olTestStrings[] = new AlphaString[]{
					new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(0), biAlphabet.get(0), biAlphabet.get(0)))),						//000	
				    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(0), biAlphabet.get(0), biAlphabet.get(1)))),						//001
				    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(0), biAlphabet.get(1), biAlphabet.get(0)))),						//010
				    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(1), biAlphabet.get(0), biAlphabet.get(0)))),	//1100
				    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(0), biAlphabet.get(0), biAlphabet.get(1)))),	//1001
				    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(0), biAlphabet.get(0), biAlphabet.get(0)))),	//1000
				    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(0), biAlphabet.get(1)))),						//101
				    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(0), biAlphabet.get(0)))),						//100
				    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(1), biAlphabet.get(1)))),						//111
				    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(0), biAlphabet.get(1), biAlphabet.get(1)))),	//1011
				    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(0), biAlphabet.get(1), biAlphabet.get(0)))),	//1010
				    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(1), biAlphabet.get(0), biAlphabet.get(1))))	//1101
			};
			
			private static ArrayList<State> olDFAStates = new ArrayList<State>(Arrays.asList(new State("A"), new State("B")));						//Q = {A, B}
			
			private static State olDFAStartState =olDFAStates.get(0);																				//q0 = A
			
			private static ArrayList<State> olDFANextStates = new ArrayList<State>(Arrays.asList(olDFAStates.get(1),olDFAStates.get(1),				//Delta = {(A, '0', B), (A, '1', B)
																			                    olDFAStates.get(0),olDFAStates.get(0)));			//(B, '0', A), (B, '1', A)
																			                   
			
			private static ArrayList<State> olDFAAcceptingStates = new ArrayList<State>(Arrays.asList( olDFAStates.get(1)));						//F = {B}
			
			private static DFA olDFA = new DFA(olDFAStates, biAlphabet,olDFAStartState,olDFANextStates,olDFAAcceptingStates);
	//----------------------------------------------------------------------------------------------------------------------
			
	//DFA that accepts a string of my name "ben" (The benDFA")---------------------------------------------------------------
			private static ArrayList<Character> benTestCharacters =  new ArrayList<Character>(Arrays.asList(engAlphabet.get(1), engAlphabet.get(4), engAlphabet.get(13)));
			private static AlphaString benTestString = new AlphaString(engAlphabet, benTestCharacters);
			
			private static AlphaString benTestStrings[] = new AlphaString[]{
					new AlphaString(engAlphabet, new ArrayList<Character>(Arrays.asList(engAlphabet.get(1), engAlphabet.get(4), engAlphabet.get(13)))),							//ben
				    new AlphaString(engAlphabet, new ArrayList<Character>(Arrays.asList(engAlphabet.get(7), engAlphabet.get(2), engAlphabet.get(5)))),							//hcf
				    new AlphaString(engAlphabet, new ArrayList<Character>(Arrays.asList(engAlphabet.get(20), engAlphabet.get(15), engAlphabet.get(12)))),						//upm
				    new AlphaString(engAlphabet, new ArrayList<Character>(Arrays.asList(engAlphabet.get(21), engAlphabet.get(18), engAlphabet.get(11), engAlphabet.get(5)))),	//vslf
				    new AlphaString(engAlphabet, new ArrayList<Character>(Arrays.asList(engAlphabet.get(11), engAlphabet.get(5), engAlphabet.get(4), engAlphabet.get(14)))),	//lfeo
				    new AlphaString(engAlphabet, new ArrayList<Character>(Arrays.asList(engAlphabet.get(1), engAlphabet.get(4), engAlphabet.get(13), engAlphabet.get(0)))),		//bena
				    new AlphaString(engAlphabet, new ArrayList<Character>(Arrays.asList(engAlphabet.get(17), engAlphabet.get(7), engAlphabet.get(5)))),							//rhf
				    new AlphaString(engAlphabet, new ArrayList<Character>(Arrays.asList(engAlphabet.get(17), engAlphabet.get(6), engAlphabet.get(0)))),							//rga
				    new AlphaString(engAlphabet, new ArrayList<Character>(Arrays.asList(engAlphabet.get(13), engAlphabet.get(4), engAlphabet.get(1)))),							//neb
				    new AlphaString(engAlphabet, new ArrayList<Character>(Arrays.asList(engAlphabet.get(25), engAlphabet.get(25), engAlphabet.get(25), engAlphabet.get(25)))),	//zzzz
				    new AlphaString(engAlphabet, new ArrayList<Character>(Arrays.asList(engAlphabet.get(9), engAlphabet.get(0), engAlphabet.get(2), engAlphabet.get(10)))),		//jack
				    new AlphaString(engAlphabet, new ArrayList<Character>(Arrays.asList(engAlphabet.get(11), engAlphabet.get(1), engAlphabet.get(4), engAlphabet.get(13))))		//lben
			};
			
			private static ArrayList<State> benDFAStates = new ArrayList<State>(Arrays.asList(new State("A"), new State("B"), new State("C"), 								//Q = {A, B, C, D, E}
					 																		  new State("D"), new State("E")));
			
			private static State benDFAStartState = benDFAStates.get(0);																									//StartState = A
			
			private static ArrayList<State> benDFANextStates = new ArrayList<State>();
			
			private static ArrayList<State> benDFAAcceptingStates = new ArrayList<State>(Arrays.asList(benDFAStates.get(3)));												//F = {D}
			
			private static DFA benDFA;
	//----------------------------------------------------------------------------------------------------------------------
			
	//DFA that accepts a string that starts with "az" (The azDFA")---------------------------------------------------------------
			private static AlphaString azTestStrings[] = new AlphaString[]{
					new AlphaString(engAlphabet, new ArrayList<Character>(Arrays.asList(engAlphabet.get(0), engAlphabet.get(25), engAlphabet.get(13)))),						//azn
				    new AlphaString(engAlphabet, new ArrayList<Character>(Arrays.asList(engAlphabet.get(7), engAlphabet.get(2), engAlphabet.get(5)))),							//hcf
				    new AlphaString(engAlphabet, new ArrayList<Character>(Arrays.asList(engAlphabet.get(20), engAlphabet.get(15), engAlphabet.get(12)))),						//upm
				    new AlphaString(engAlphabet, new ArrayList<Character>(Arrays.asList(engAlphabet.get(0), engAlphabet.get(25), engAlphabet.get(11), engAlphabet.get(25)))),	//azkz
				    new AlphaString(engAlphabet, new ArrayList<Character>(Arrays.asList(engAlphabet.get(11), engAlphabet.get(5), engAlphabet.get(4), engAlphabet.get(14)))),	//lfeo
				    new AlphaString(engAlphabet, new ArrayList<Character>(Arrays.asList(engAlphabet.get(1), engAlphabet.get(4), engAlphabet.get(13), engAlphabet.get(0)))),		//bena
				    new AlphaString(engAlphabet, new ArrayList<Character>(Arrays.asList(engAlphabet.get(0), engAlphabet.get(25), engAlphabet.get(0)))),							//aza
				    new AlphaString(engAlphabet, new ArrayList<Character>(Arrays.asList(engAlphabet.get(17), engAlphabet.get(6), engAlphabet.get(0)))),							//rga
				    new AlphaString(engAlphabet, new ArrayList<Character>(Arrays.asList(engAlphabet.get(0), engAlphabet.get(25), engAlphabet.get(9)))),							//azj
				    new AlphaString(engAlphabet, new ArrayList<Character>(Arrays.asList(engAlphabet.get(0), engAlphabet.get(25), engAlphabet.get(0), engAlphabet.get(25)))),	//azaz
				    new AlphaString(engAlphabet, new ArrayList<Character>(Arrays.asList(engAlphabet.get(4), engAlphabet.get(10), engAlphabet.get(24), engAlphabet.get(14)))),	//elyo
				    new AlphaString(engAlphabet, new ArrayList<Character>(Arrays.asList(engAlphabet.get(0), engAlphabet.get(25), engAlphabet.get(6), engAlphabet.get(13))))		//azgn
			};
			
			private static ArrayList<State> azDFAStates = new ArrayList<State>(Arrays.asList(new State("A"), new State("B"), new State("C"), 								//Q = {A, B, C, D}
					 																		  new State("D")));
			
			private static State azDFAStartState = azDFAStates.get(0);																										//StartState = A
			
			private static ArrayList<State> azDFANextStates = new ArrayList<State>();																						
			
			private static ArrayList<State> azDFAAcceptingStates = new ArrayList<State>(Arrays.asList(azDFAStates.get(3)));													//F = {D}
			
			private static DFA azDFA;
	//----------------------------------------------------------------------------------------------------------------------
	
	//DFA that accepts a string that contains "ab" somewhere in it (The "abDFA")---------------------------------------------
			private static AlphaString abTestStrings[] = new AlphaString[]{
					new AlphaString(abcAlphabet, new ArrayList<Character>(Arrays.asList(abcAlphabet.get(0), abcAlphabet.get(1), abcAlphabet.get(1)))),						//abb
				    new AlphaString(abcAlphabet, new ArrayList<Character>(Arrays.asList(abcAlphabet.get(1), abcAlphabet.get(1), abcAlphabet.get(0)))),						//bba
				    new AlphaString(abcAlphabet, new ArrayList<Character>(Arrays.asList(abcAlphabet.get(0), abcAlphabet.get(0), abcAlphabet.get(2)))),						//aac
				    new AlphaString(abcAlphabet, new ArrayList<Character>(Arrays.asList(abcAlphabet.get(0), abcAlphabet.get(1), abcAlphabet.get(1), abcAlphabet.get(2)))),	//abbc
				    new AlphaString(abcAlphabet, new ArrayList<Character>(Arrays.asList(abcAlphabet.get(0), abcAlphabet.get(1), abcAlphabet.get(0), abcAlphabet.get(0)))),	//abaa
				    new AlphaString(abcAlphabet, new ArrayList<Character>(Arrays.asList(abcAlphabet.get(1), abcAlphabet.get(1), abcAlphabet.get(2), abcAlphabet.get(0)))),	//bbca
				    new AlphaString(abcAlphabet, new ArrayList<Character>(Arrays.asList(abcAlphabet.get(0), abcAlphabet.get(1), abcAlphabet.get(0)))),						//aba
				    new AlphaString(abcAlphabet, new ArrayList<Character>(Arrays.asList(abcAlphabet.get(1), abcAlphabet.get(0), abcAlphabet.get(1)))),						//bab
				    new AlphaString(abcAlphabet, new ArrayList<Character>(Arrays.asList(abcAlphabet.get(0), abcAlphabet.get(1), abcAlphabet.get(2)))),						//abc
				    new AlphaString(abcAlphabet, new ArrayList<Character>(Arrays.asList(abcAlphabet.get(2), abcAlphabet.get(2), abcAlphabet.get(2), abcAlphabet.get(2)))),	//cccc
				    new AlphaString(abcAlphabet, new ArrayList<Character>(Arrays.asList(abcAlphabet.get(1), abcAlphabet.get(1), abcAlphabet.get(1), abcAlphabet.get(1)))),	//bbbb
				    new AlphaString(abcAlphabet, new ArrayList<Character>(Arrays.asList(abcAlphabet.get(0), abcAlphabet.get(2), abcAlphabet.get(1), abcAlphabet.get(2))))	//acbc
			};
			
			private static ArrayList<State> abDFAStates = new ArrayList<State>(Arrays.asList(new State("A"), new State("B"), new State("C")));								//Q = {A, B, C}
				
			private static State abDFAStartState = abDFAStates.get(0);																										//q0 = A
			
			private static ArrayList<State> abDFANextStates = new ArrayList<State>(Arrays.asList(abDFAStates.get(1), abDFAStates.get(0), abDFAStates.get(0),				//Delta = {(A, 'a', B), (A, 'b', A), (A, 'c', A)
																								 abDFAStates.get(1), abDFAStates.get(2), abDFAStates.get(0),                //(B, 'a', B), (B, 'b', C), (B, 'c', A)
																			                     abDFAStates.get(2), abDFAStates.get(2), abDFAStates.get(2)));				//(C, 'a', C), (C, 'b', C), (C, 'c', C)
																			                   
			
			private static ArrayList<State> abDFAAcceptingStates = new ArrayList<State>(Arrays.asList( abDFAStates.get(2)));												//F = {C}
			
			private static DFA abDFA = new DFA(abDFAStates, abcAlphabet, abDFAStartState, abDFANextStates, abDFAAcceptingStates);
	//----------------------------------------------------------------------------------------------------------------------
			
	//DFA that accepts all strings of bits (The "allDFA")-------------------------------------------------------------------															
			private static AlphaString allTestStrings[] = new AlphaString[]{
					new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(0), biAlphabet.get(0), biAlphabet.get(0)))),							//aaa
				    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(0), biAlphabet.get(0), biAlphabet.get(1)))),							//aab
				    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(0), biAlphabet.get(1), biAlphabet.get(0)))),							//aba
				    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(1), biAlphabet.get(0), biAlphabet.get(0)))),		//bbaa
				    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(0), biAlphabet.get(0), biAlphabet.get(1)))),		//baab
				    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(0), biAlphabet.get(0), biAlphabet.get(0)))),		//baaa
				    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(0), biAlphabet.get(1)))),							//bab
				    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(0), biAlphabet.get(0)))),							//baa
				    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(1), biAlphabet.get(1)))),							//bbb
				    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(0), biAlphabet.get(1), biAlphabet.get(1)))),		//babb
				    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(0), biAlphabet.get(1), biAlphabet.get(0)))),		//baba
				    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(1), biAlphabet.get(0), biAlphabet.get(1))))		//bbab
			};
			
			private static ArrayList<State> allDFAStates = new ArrayList<State>(Arrays.asList(new State("A")));																//Q = {A}
			
			private static State allDFAStartState =allDFAStates.get(0);																										//q0 = A
			
			private static ArrayList<State> allDFANextStates = new ArrayList<State>(Arrays.asList(allDFAStates.get(0),allDFAStates.get(0)));								//Delta = {(A, '0', A), (A, '1', A)
																			                   
			
			private static ArrayList<State> allDFAAcceptingStates = new ArrayList<State>(Arrays.asList(allDFAStates.get(0)));												//F = {A}
			
			private static DFA allDFA = new DFA(allDFAStates, biAlphabet,allDFAStartState,allDFANextStates,allDFAAcceptingStates);
	//----------------------------------------------------------------------------------------------------------------------
	
	//DFA that accepts a string that starts with 'a' and ends with 'c' (The acDFA")-----------------------------------------
			private static AlphaString acTestStrings[] = new AlphaString[]{
					new AlphaString(abcAlphabet, new ArrayList<Character>(Arrays.asList(abcAlphabet.get(0), abcAlphabet.get(1), abcAlphabet.get(2)))),						//abc
				    new AlphaString(abcAlphabet, new ArrayList<Character>(Arrays.asList(abcAlphabet.get(1), abcAlphabet.get(1), abcAlphabet.get(0)))),						//bba
				    new AlphaString(abcAlphabet, new ArrayList<Character>(Arrays.asList(abcAlphabet.get(0), abcAlphabet.get(0), abcAlphabet.get(2)))),						//aac
				    new AlphaString(abcAlphabet, new ArrayList<Character>(Arrays.asList(abcAlphabet.get(0), abcAlphabet.get(1), abcAlphabet.get(1), abcAlphabet.get(2)))),	//abbc
				    new AlphaString(abcAlphabet, new ArrayList<Character>(Arrays.asList(abcAlphabet.get(0), abcAlphabet.get(1), abcAlphabet.get(0), abcAlphabet.get(0)))),	//0100
				    new AlphaString(abcAlphabet, new ArrayList<Character>(Arrays.asList(abcAlphabet.get(1), abcAlphabet.get(1), abcAlphabet.get(2), abcAlphabet.get(0)))),	//bbca
				    new AlphaString(abcAlphabet, new ArrayList<Character>(Arrays.asList(abcAlphabet.get(0), abcAlphabet.get(1), abcAlphabet.get(2)))),						//abc
				    new AlphaString(abcAlphabet, new ArrayList<Character>(Arrays.asList(abcAlphabet.get(1), abcAlphabet.get(0), abcAlphabet.get(1)))),						//bab
				    new AlphaString(abcAlphabet, new ArrayList<Character>(Arrays.asList(abcAlphabet.get(0), abcAlphabet.get(1), abcAlphabet.get(2)))),						//abc
				    new AlphaString(abcAlphabet, new ArrayList<Character>(Arrays.asList(abcAlphabet.get(2), abcAlphabet.get(2), abcAlphabet.get(2), abcAlphabet.get(2)))),	//bbbb
				    new AlphaString(abcAlphabet, new ArrayList<Character>(Arrays.asList(abcAlphabet.get(1), abcAlphabet.get(1), abcAlphabet.get(1), abcAlphabet.get(1)))),	//bbbb
				    new AlphaString(abcAlphabet, new ArrayList<Character>(Arrays.asList(abcAlphabet.get(0), abcAlphabet.get(2), abcAlphabet.get(1), abcAlphabet.get(2))))	//acbc
			};											//Input = "abc"
			
			private static ArrayList<State> acDFAStates = new ArrayList<State>(Arrays.asList(new State("A"), new State("B"), new State("C"), new State("D")));				//Q = {A, B, C, D}
			
			private static State acDFAStartState = acDFAStates.get(0);																										//StartingState = A
			
			private static ArrayList<State> acDFANextStates =  new ArrayList<State>(Arrays.asList(acDFAStates.get(2), acDFAStates.get(1), acDFAStates.get(1),				//Delta = {(A, 'a', C), (A, 'b', B), (A, 'c', B) 
					 																			  acDFAStates.get(1), acDFAStates.get(1), acDFAStates.get(1),				//(B, 'a', B), (B, 'b', B), (B, 'c', B)
					 																			  acDFAStates.get(2), acDFAStates.get(2), acDFAStates.get(3),				//(C, 'a', C), (C, 'b', C), (C, 'c', D)
					 																			  acDFAStates.get(2), acDFAStates.get(2), acDFAStates.get(3)));				//(D, 'a', C), (D, 'b', C), (D, 'c', D)
			
			private static ArrayList<State> acDFAAcceptingStates = new ArrayList<State>(Arrays.asList(acDFAStates.get(3)));													//F = {D}
			
			private static DFA acDFA = new DFA(acDFAStates, abcAlphabet, acDFAStartState, acDFANextStates, acDFAAcceptingStates);
	//----------------------------------------------------------------------------------------------------------------------
			
	//DFA that accepts a string that starts with 'a' or ends with 'c' (The acOrDFA")----------------------------------------
			private static AlphaString acOrTestStrings[] = new AlphaString[]{
					new AlphaString(abcAlphabet, new ArrayList<Character>(Arrays.asList(abcAlphabet.get(0), abcAlphabet.get(0), abcAlphabet.get(2)))),						//aac
				    new AlphaString(abcAlphabet, new ArrayList<Character>(Arrays.asList(abcAlphabet.get(1), abcAlphabet.get(0), abcAlphabet.get(1)))),						//bab
				    new AlphaString(abcAlphabet, new ArrayList<Character>(Arrays.asList(abcAlphabet.get(1), abcAlphabet.get(2), abcAlphabet.get(1)))),						//bcb
				    new AlphaString(abcAlphabet, new ArrayList<Character>(Arrays.asList(abcAlphabet.get(1), abcAlphabet.get(1), abcAlphabet.get(0), abcAlphabet.get(1)))),	//bbab
				    new AlphaString(abcAlphabet, new ArrayList<Character>(Arrays.asList(abcAlphabet.get(0), abcAlphabet.get(1), abcAlphabet.get(1), abcAlphabet.get(2)))),	//abbc
				    new AlphaString(abcAlphabet, new ArrayList<Character>(Arrays.asList(abcAlphabet.get(0), abcAlphabet.get(0), abcAlphabet.get(0), abcAlphabet.get(0)))),	//aaaa
				    new AlphaString(abcAlphabet, new ArrayList<Character>(Arrays.asList(abcAlphabet.get(1), abcAlphabet.get(1), abcAlphabet.get(2)))),						//bbc
				    new AlphaString(abcAlphabet, new ArrayList<Character>(Arrays.asList(abcAlphabet.get(0), abcAlphabet.get(0), abcAlphabet.get(2)))),						//aab
				    new AlphaString(abcAlphabet, new ArrayList<Character>(Arrays.asList(abcAlphabet.get(1), abcAlphabet.get(2), abcAlphabet.get(1)))),						//bcb
				    new AlphaString(abcAlphabet, new ArrayList<Character>(Arrays.asList(abcAlphabet.get(1), abcAlphabet.get(2), abcAlphabet.get(0), abcAlphabet.get(1)))),	//bcab
				    new AlphaString(abcAlphabet, new ArrayList<Character>(Arrays.asList(abcAlphabet.get(1), abcAlphabet.get(2), abcAlphabet.get(1), abcAlphabet.get(1)))),	//bcaa
				    new AlphaString(abcAlphabet, new ArrayList<Character>(Arrays.asList(abcAlphabet.get(1), abcAlphabet.get(1), abcAlphabet.get(0), abcAlphabet.get(2))))	//bbac
			};																//Input = "abc"
			
			private static ArrayList<State> acOrDFAStates = new ArrayList<State>(Arrays.asList(new State("A"), new State("B"), new State("C"), new State("D")));				//Q = {A, B, C, D}
			
			private static State acOrDFAStartState = acOrDFAStates.get(0);																										//StartingState = A
			
			private static ArrayList<State> acOrDFANextStates =  new ArrayList<State>(Arrays.asList(acOrDFAStates.get(1), acOrDFAStates.get(3), acOrDFAStates.get(2),			//Delta = {(A, 'a', B), (A, 'b', D), (A, 'c', C) 
					 																			    acOrDFAStates.get(1), acOrDFAStates.get(1), acOrDFAStates.get(1),			//(B, 'a', B), (B, 'b', B), (B, 'c', B)
					 																			    acOrDFAStates.get(3), acOrDFAStates.get(3), acOrDFAStates.get(2),			//(C, 'a', D), (C, 'b', D), (C, 'c', C)
					 																			    acOrDFAStates.get(3), acOrDFAStates.get(3), acOrDFAStates.get(2)));			//(D, 'a', D), (D, 'b', D), (D, 'c', C)
			
			private static ArrayList<State> acOrDFAAcceptingStates = new ArrayList<State>(Arrays.asList(acOrDFAStates.get(1), acOrDFAStates.get(2)));							//F = {B, C}
			
			private static DFA acOrDFA = new DFA(acOrDFAStates, abcAlphabet, acOrDFAStartState, acOrDFANextStates, acOrDFAAcceptingStates);
	//----------------------------------------------------------------------------------------------------------------------
			
	//DFA that accepts a sting containing the binary # "01", or "10" (The "ooDFA")------------------------------------------------------------------------
			private static AlphaString ooTestStrings[] = new AlphaString[]{
					new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(0)))),															//0
				    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1)))),															//1
				    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(0), biAlphabet.get(0)))),											//00
				    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(0), biAlphabet.get(1)))),											//01
				    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(0)))),											//10
				    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(1)))),											//11
				    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(0), biAlphabet.get(0), biAlphabet.get(0)))),						//000
				    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(0), biAlphabet.get(0), biAlphabet.get(1)))),						//001
				    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(1), biAlphabet.get(1)))),						//111
				    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(0), biAlphabet.get(1), biAlphabet.get(1)))),	//1011
				    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(1), biAlphabet.get(1), biAlphabet.get(1)))),   //1111
				    new AlphaString(biAlphabet, new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(1), biAlphabet.get(0), biAlphabet.get(1))))	//1101
			};
			
			private static ArrayList<State> ooDFAStates = new ArrayList<State>(Arrays.asList(new State("A"), new State("B"), new State("C"), 
																											 new State("D")));						//Q = {A, B, C, D}
			
			private static State ooDFAStartState =ooDFAStates.get(0);																				//q0 = A
			
			private static ArrayList<State>ooDFANextStates = new ArrayList<State>(Arrays.asList(ooDFAStates.get(1),ooDFAStates.get(2),				//Delta = {(A, '0', B), (A, '1', C)
																			                    ooDFAStates.get(1),ooDFAStates.get(3),						 //(B, '0', B), (B, '1', D)
																			                    ooDFAStates.get(3),ooDFAStates.get(2),						 //(C, '0', D), (C, '1', C)
			 																					ooDFAStates.get(3),ooDFAStates.get(3)));					 //(D, '0', D), (D, '1', D)}
			
			private static ArrayList<State>ooDFAAcceptingStates = new ArrayList<State>(Arrays.asList(ooDFAStates.get(3)));							//F = {D}
			
			private static DFA ooDFA = new DFA(ooDFAStates, biAlphabet,ooDFAStartState,ooDFANextStates,ooDFAAcceptingStates);
	//----------------------------------------------------------------------------------------------------------------------
			
	public static void main(String[] args) { 
		
		//Define benDFA--------------------------------------------------------------------------------------------------------
			initializeStates(engAlphabet, benDFAStates, benDFANextStates, benDFAStates.get(4)); //Delta = {([ALL States], [ALL Characters], E])}
																								//Except for																		
			
			
			benDFANextStates.set((26*0 + 1), benDFAStates.get(1));								//{(A, 'b', B)
			benDFANextStates.set((26*1 + 4), benDFAStates.get(2));								 //(B, 'e', C)
			benDFANextStates.set((26*2 + 13), benDFAStates.get(3));							     //(C, 'n', D)}
			
			benDFA = new DFA(benDFAStates, engAlphabet, benDFAStartState, benDFANextStates, benDFAAcceptingStates);
		//------------------------------------------------------------------------------------------------------------------------
		
		//Define azDFA---------------------------------------------------------------------------------------------------------
			initializeStates(engAlphabet, azDFAStates, azDFANextStates, azDFAStates.get(2)); 	//Delta = {([ALL States], [ALL Characters], C])}
																							 	//Except for
			azDFANextStates.set((26*0 + 0), azDFAStates.get(1));								//{(A, 'a', B)
			azDFANextStates.set((26*1 + 25), azDFAStates.get(3));							     //(B, 'z', D)
																								 //(D, [All Characters], D)}
			for(int i = 0; i < engAlphabet.size(); i++){											 
			azDFANextStates.set((26*3 + i), azDFAStates.get(3));						             
			
			}
			
			azDFA = new DFA(azDFAStates, engAlphabet, azDFAStartState, azDFANextStates, azDFAAcceptingStates);
		//------------------------------------------------------------------------------------------------------------------------
		
		boolean cont = true;
		boolean dfaToTest = true;
		
		boolean isSubset;
		boolean isEqual;
		
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		
		String input = "";
		
		String accepts;
		
		int testStringIndex = 0;
		
		AlphaString testString = new AlphaString();
		
		DFA currentDFA = new DFA();
		
		while(cont){
			dfaToTest = true;
			
			System.out.println("0  - Quit ");
			System.out.println("1  - Initial DFA tests");
			System.out.println("2  - Expanded DFA tests");
			System.out.println("3  - Compliment DFA tests");
			System.out.println("4  - Union of DFAs tests");
			System.out.println("5  - Intersection of DFAs tests");
			System.out.println("6  - Subset of DFAs tests");
			System.out.println("7  - Equality of DFAs tests");
			System.out.println("8  - Test union, intersection, and complement functions");
			
			System.out.println("\nWhat kind of test do you wish to run?: ");
			
			input = scanner.next();
			
			switch(input){
				case "0":
					System.out.println("\nBye...");
					cont = false;
					break;
				
				case "1":
					System.out.println("1  - DFA that accepts no strings of bits");
					System.out.println("2  - DFA that only accepts the empty string");
					System.out.println("3  - DFA that only accepts a string of a single character 'c'");
					
					System.out.println("\nWhich DFA would you like to test?: ");
					
					input = scanner.next();
					switch(input) {
						case "0":
							System.out.println("\nBye...");
							cont = false;
							break;
					
						case "1":
							testString = noTestString;
							currentDFA = noDFA;
							break;
							
						case "2":
							testString = emptyTestString;
							currentDFA = emptyDFA;
							break;
							
						case "3":
							testString = new AlphaString(abcAlphabet, new ArrayList<Character>(Arrays.asList(abcTestCharacter)));
							currentDFA = nDFA;
							break;
							
						default:
							dfaToTest = false;
							System.out.println("\nPlease enter a valid input!\n");
							break;
							
					}
					
					break;
					
				case "2":
					System.out.println("0  - Quit ");
					System.out.println("1  - DFA that only accepts a string of an odd binary number");
					System.out.println("2  - DFA that only accepts a string of an even binary number");
					System.out.println("3  - DFA that only accepts a string of a binary number of length 3");
					System.out.println("4  - DFA that only accepts a string made of an even number of bits");
					System.out.println("5  - DFA that only accepts a string made of an odd number of bits");
					System.out.println("6  - DFA that only accepts a string of my first name, 'ben' in all lower case letters");
					System.out.println("7  - DFA that only accepts a string that starts with 'az'");
					System.out.println("8  - DFA that only accepts a string that contains 'ab'");
					System.out.println("9  - DFA that accepts all strings of bits");
					System.out.println("10 - DFA that only accepts a string that begins with 'a' and ends with 'c'");
					System.out.println("11 - DFA that only accepts a string that begins with 'a' or ends with 'c'");
					System.out.println("12 - DFA that only accepts a string that contains the binary number '01' or '10'");
					
					System.out.println("\nWhich DFA would you like to test?: ");
					
					input = scanner.next();
					
					switch(input) {	
						case "0":
							System.out.println("\nBye...");
							break;
					
						case "1":
							testString = oddTestStrings[testStringIndex%12];
							currentDFA = oddDFA;
							
							break;
							
						case "2":
							testString = evenTestStrings[testStringIndex%12];
							currentDFA = evenDFA;
							break;
							
						case "3":
							testString = tbTestStrings[testStringIndex%12];
							currentDFA = tbDFA;
							break;
							
						case "4":
							testString = elTestStrings[testStringIndex%12];
							currentDFA = elDFA;
							break;
							
						case "5":
							testString = olTestStrings[testStringIndex%12];
							currentDFA = olDFA;
							break;
							
						case "6":
							testString = benTestStrings[testStringIndex%12];;
							currentDFA = benDFA;
							
							break;
							
						case "7":
							testString = azTestStrings[testStringIndex%12];		
							currentDFA = azDFA;
							
							break;
							
						case "8":
							testString = abTestStrings[testStringIndex%12];;
							currentDFA = abDFA;
							break;
							
						case "9":
							testString = allTestStrings[testStringIndex%12];
							currentDFA = allDFA;
							break;
							
						case "10":
							testString = acTestStrings[testStringIndex%12];;
							currentDFA = acDFA;
							break;
							
						case "11":
							testString = acOrTestStrings[testStringIndex%12];;;
							currentDFA = acOrDFA;
							break;
							
						case "12":
							testString = ooTestStrings[testStringIndex%12];
							currentDFA = ooDFA;
							break;
							
						default:
							dfaToTest = false;
							System.out.println("\nPlease enter a valid input!\n");
							break;
							
					}
					
					break;
					
				case "3":
					System.out.println("0  - Quit ");
					System.out.println("1  - DFA that accepts every string but one made up of my first name, 'ben'");
					
					System.out.println("\nWhich DFA would you like to test?: ");
					
					input = scanner.next();
					switch(input) {	
						case "0":
							System.out.println("\nBye...");;
							break;
					
						case "1":
							testString = benTestStrings[testStringIndex%12];
							currentDFA = complement(benDFA);
							break;
							
						default:
							dfaToTest = false;
							System.out.println("\nPlease enter a valid input!\n");
							break;
							
					}
					
					break;
				
				case "4":
					System.out.println("0  - Quit ");
					System.out.println("1  - DFA that accepts a string made up of an even binary number or of an even number of bits");
					System.out.println("2  - DFA that accepts a string made up of an even binary number or of an odd number of bits");
					System.out.println("3  - DFA that accepts a string made up of an even binary number or of three bits");
					System.out.println("4  - DFA that accepts a string made up of an even binary number or one that contains '01' or '10' ");
					System.out.println("5  - DFA that accepts a string made up of an odd binary number or of an even number of bits");
					System.out.println("6  - DFA that accepts a string made up of an odd binary number or of an odd number of bits");
					System.out.println("7  - DFA that accepts a string made up of an odd binary number or of three bits");
					System.out.println("8  - DFA that accepts a string made up of an odd binary number or one that contains '01' or '10' ");
					System.out.println("9  - DFA that accepts a string that contains '01' or '10' or is made of an even number of bits");
					System.out.println("10  - DFA that accepts a string that contains '01' or '10' or is made of of an odd number of bits");
					System.out.println("11 - DFA that accepts a string that contains 'ab' or that begins with an 'a' or ends with a 'c'");
					System.out.println("12 - DFA that accepts a string made up of an even binary number or one made up of an odd binary number");
					
					System.out.println("\nWhich DFA would you like to test?: ");
					
					input = scanner.next();
					
					switch(input) {	
						case "0":
							System.out.println("\nBye...");
							break;
					
						case "1":
							testString = evenTestStrings[testStringIndex%12];
							currentDFA = union(evenDFA, elDFA);
							break;
						
						case "2":
							testString = evenTestStrings[testStringIndex%12];
							currentDFA = union(evenDFA, olDFA);
							break;
							
						case "3":
							testString = evenTestStrings[testStringIndex%12];
							currentDFA = union(evenDFA, tbDFA);
							break;
							
						case "4":
							testString = evenTestStrings[testStringIndex%12];
							currentDFA = union(evenDFA, ooDFA);
							break;
							
						case "5":
							testString = oddTestStrings[testStringIndex%12];
							currentDFA = union(oddDFA, elDFA);
							break;
							
						case "6":
							testString = oddTestStrings[testStringIndex%12];
							currentDFA = union(oddDFA, olDFA);
							break;
							
						case "7":
							testString = oddTestStrings[testStringIndex%12];
							currentDFA = union(oddDFA, tbDFA);
							break;
							
						case "8":
							testString = oddTestStrings[testStringIndex%12];
							currentDFA = union(oddDFA, ooDFA);
							break;
							
						case "9":
							testString = ooTestStrings[testStringIndex%12];
							currentDFA = union(ooDFA, elDFA);
							break;
							
						case "10":
							testString = ooTestStrings[testStringIndex%12];
							currentDFA = union(ooDFA, olDFA);
							break;
							
						case "11":
							testString = abTestStrings[testStringIndex%12];
							currentDFA = union(abDFA, acOrDFA);
							break;
							
						case "12":
							testString = evenTestStrings[testStringIndex%12];
							currentDFA = union(evenDFA, oddDFA);
							break;
							
						default:
							dfaToTest = false;
							System.out.println("\nPlease enter a valid input!\n");
							break;
					}
					
					break;
					
				case "5":
					System.out.println("0  - Quit ");
					System.out.println("1  - DFA that accepts a string made up of an even binary number and of an even number of bits");
					System.out.println("2  - DFA that accepts a string made up of an even binary number and of an odd number of bits");
					System.out.println("3  - DFA that accepts a string made up of an even binary number and of three bits");
					System.out.println("4  - DFA that accepts a string made up of an even binary number and one that contains '01' or '10' ");
					System.out.println("5  - DFA that accepts a string made up of an odd binary number and of an even number of bits");
					System.out.println("6  - DFA that accepts a string made up of an odd binary number and of an odd number of bits");
					System.out.println("7  - DFA that accepts a string made up of an odd binary number and of three bits");
					System.out.println("8  - DFA that accepts a string made up of an odd binary number and one that contains '01' or '10' ");
					System.out.println("9  - DFA that accepts a string that contains '01' or '10' and is made of an even number of bits");
					System.out.println("10 - DFA that accepts a string that contains '01' or '10' and is made of of an odd number of bits");
					System.out.println("11 - DFA that accepts a string that contains 'ab' and that begins with an 'a' or ends with a 'c'");
					System.out.println("12 - DFA that accepts a string made up of an even binary number and made up of an odd binary number");
					
					System.out.println("\nWhich DFA would you like to test?: ");
					
					input = scanner.next();
					
					switch(input) {	
						case "0":
							System.out.println("\nBye...");
							cont = false;
							break;
					
						case "1":
							testString = evenTestStrings[testStringIndex%12];
							currentDFA = intersection(evenDFA, elDFA);
							break;
						
						case "2":
							testString = evenTestStrings[testStringIndex%12];
							currentDFA = intersection(evenDFA, olDFA);
							break;
							
						case "3":
							testString = evenTestStrings[testStringIndex%12];
							currentDFA = intersection(evenDFA, tbDFA);
							break;
							
						case "4":
							testString = evenTestStrings[testStringIndex%12];
							currentDFA = intersection(evenDFA, ooDFA);
							break;
							
						case "5":
							testString = oddTestStrings[testStringIndex%12];
							currentDFA = intersection(oddDFA, elDFA);
							break;
							
						case "6":
							testString = oddTestStrings[testStringIndex%12];
							currentDFA = intersection(oddDFA, olDFA);
							break;
							
						case "7":
							testString = oddTestStrings[testStringIndex%12];
							currentDFA = intersection(oddDFA, tbDFA);
							break;
							
						case "8":
							testString = oddTestStrings[testStringIndex%12];
							currentDFA = intersection(oddDFA, ooDFA);
							break;
							
						case "9":
							testString = ooTestStrings[testStringIndex%12];
							currentDFA = intersection(ooDFA, elDFA);
							break;
							
						case "10":
							testString = ooTestStrings[testStringIndex%12];
							currentDFA = intersection(ooDFA, olDFA);
							break;
							
						case "11":
							testString = abTestStrings[testStringIndex%12];
							currentDFA = intersection(abDFA, acOrDFA);
							break;
							
						case "12":
							testString = evenTestStrings[testStringIndex%12];
							currentDFA = intersection(evenDFA, oddDFA);
							break;
						
						default:
							dfaToTest = false;
							System.out.println("\nPlease enter a valid input!\n");
							break;
							
					}
					
					break;
					
				case "6":
					System.out.println("0  - Quit ");
					System.out.println("1  - Is oddDFA a subset of ooDFA?");
					System.out.println("2  - Is evenDFA a subset of ooDFA?");
					System.out.println("3  - Is acDFA a subset of acorDFA?");
					System.out.println("4  - Is evenDFA a subset of oddDFA?");
					System.out.println("5  - Is evenDFA a subset of evenDFA?");
					System.out.println("6  - Is noDFA a subset of evenDFA?");
					System.out.println("7  - Is allDFA a subset of oddDFA?");
					System.out.println("8  - Is benDFA a subset of azDFA?");
					System.out.println("9  - Is noDFA a subset of noDFA?");
					System.out.println("10 - Is allDFA a subset of noDFA?");
					System.out.println("11 - Is tbDFA a subset of olDFA?");
					System.out.println("12 - Is tbDFA a subset of elDFA?");
					System.out.println("\nWhich statement would you like to test?: ");
					
					input = scanner.next();
					
					switch(input) {	
						case "0":
							System.out.println("\nBye...");
							cont = false;
							break;
							
						case "1":
							isSubset = subset(oddDFA, ooDFA);
							accepts = (isSubset)?"oddDFA is a subset of ooDFA":"oddDFA is not a subset of ooDFA";
							System.out.println(accepts);
							
							dfaToTest = false;
							break;
						
						case "2":
							isSubset = subset(evenDFA, ooDFA);
							accepts = (isSubset)?"evenDFA is a subset of ooDFA":"evenDFA is not a subset of ooDFA";
							System.out.println(accepts);
							
							dfaToTest = false;
							break;
						
						case "3":
							isSubset = subset(acDFA, acOrDFA);
							accepts = (isSubset)?"acDFA is a subset of acOrDFA":"acDFA is not a subset of acOrDFA";
							System.out.println(accepts);
							
							dfaToTest = false;
							break;
						
						case "4":
							isSubset = subset(evenDFA, oddDFA);
							accepts = (isSubset)?"evenDFA is a subset of oddDFA":"evenDFA is not a subset of oddDFA";
							System.out.println(accepts);
							
							dfaToTest = false;
							break;
						
						case "5":
							isSubset = subset(evenDFA, evenDFA);
							accepts = (isSubset)?"evenDFA is a subset of evenDFA":"evenDFA is not a subset of evenDFA";
							System.out.println(accepts);
							
							dfaToTest = false;
							break;
						
						case "6":
							isSubset = subset(noDFA, evenDFA);
							accepts = (isSubset)?"noDFA is a subset of evenDFA":"noDFA is not a subset of evenDFA";
							System.out.println(accepts);
							
							dfaToTest = false;
							break;
						
						case "7":
							isSubset = subset(allDFA, oddDFA);
							accepts = (isSubset)?"noDFA is a subset of evenDFA":"noDFA is not a subset of evenDFA";
							System.out.println(accepts);
							
							dfaToTest = false;
							break;
						
						case "8":
							isSubset = subset(benDFA, azDFA);
							accepts = (isSubset)?"benDFA is a subset of azDFA":"benDFA is not a subset of azDFA";
							System.out.println(accepts);
							
							dfaToTest = false;
							break;
						
						case "9":
							isSubset = subset(noDFA, noDFA);
							accepts = (isSubset)?"noDFA is a subset of noDFA":"noDFA is not a subset of noDFA";
							System.out.println(accepts);
							
							dfaToTest = false;
							break;
						
						case "10":
							isSubset = subset(allDFA, noDFA);
							accepts = (isSubset)?"allDFA is a subset of noDFA":"allDFA is not a subset of noDFA";
							System.out.println(accepts);
							
							dfaToTest = false;
							break;
						
						case "11":
							isSubset = subset(tbDFA, olDFA);
							accepts = (isSubset)?"tbDFA is a subset of olDFA":"tbDFA is not a subset of olDFA";
							System.out.println(accepts);
							
							dfaToTest = false;
							break;
						
						case "12":
							isSubset = subset(tbDFA, elDFA);
							accepts = (isSubset)?"tbDFA is a subset of elDFA":"tbDFA is not a subset of elDFA";
							System.out.println(accepts);
							
							dfaToTest = false;
							break;
						
						default:
							dfaToTest = false;
							System.out.println("\nPlease enter a valid input!\n");
							break;
					
					}
					
					break;
					
				case "7":
					System.out.println("0  - Quit ");
					System.out.println("1  - Is oddDFA equal to evenDFA?");
					System.out.println("2  - Is oddDFA equal to oddFA?");
					System.out.println("3  - Is benDFA equal to benDFA?");
					System.out.println("4  - Is benDFA equal to azDFA?");
					System.out.println("5  - Is acDFA equal to acOrDFA?");
					System.out.println("6  - Is allDFA equal to noDFA?");
					System.out.println("7  - Is allDFA equal to allDFA?");
					System.out.println("8  - Is (evenDFA n oddDFA) equal to noDFA?");
					System.out.println("9  - Is ~(allDFA) equal to noDFA?");
					System.out.println("10 - Is (allDFA n oddDFA) equal to oddDFA?");
					System.out.println("11 - Is ~(oddDFA) equal to evenDFA?");
					System.out.println("12 - Is (~(oddDFA) n ~(emptyDFA)) equal to evenDFA?");
					System.out.println("\nWhich Statement would you like to test?: ");
					
					input = scanner.next();
					
					switch(input) {	
						case "0":
							System.out.println("\nBye...");
							cont = false;
							break;
							
						case "1":
							isEqual = equal(oddDFA, evenDFA);
							accepts = (isEqual)?"oddDFA is equal to evenDFA":"oddDFA is not equal to evenDFA";
							System.out.println(accepts);
							
							dfaToTest = false;
							break;
						
						case "2":
							isEqual = equal(oddDFA, oddDFA);
							accepts = (isEqual)?"oddDFA is equal to oddDFA":"oddDFA is not equal to oddDFA";
							System.out.println(accepts);
							
							dfaToTest = false;
							break;
						
						case "3":
							isEqual = equal(benDFA, benDFA);
							accepts = (isEqual)?"benDFA is equal to benDFA":"benDFA is not equal to benDFA";
							System.out.println(accepts);
							
							dfaToTest = false;
							break;
						
						case "4":
							isEqual = equal(benDFA, azDFA);
							accepts = (isEqual)?"benDFA is equal to ooDFA":"oddDFA is not equal to ooDFA";
							System.out.println(accepts);
							
							dfaToTest = false;
							break;
						
						case "5":
							isEqual = equal(acDFA, acOrDFA);
							accepts = (isEqual)?"acDFA is equal to acOrDFA":"acDFA is not equal to acOrDFA";
							System.out.println(accepts);
							
							dfaToTest = false;
							break;
						
						case "6":
							isEqual = equal(allDFA, noDFA);
							accepts = (isEqual)?"allDFA is equal to noDFA":"allDFA is not equal to noDFA";
							System.out.println(accepts);
							
							dfaToTest = false;
							break;
						
						case "7":
							isEqual = equal(allDFA, allDFA);
							accepts = (isEqual)?"allDFA is equal to allDFA":"allDFA is not equal to allDFA";
							System.out.println(accepts);
							
							dfaToTest = false;
							break;
						
						case "8":
							isEqual = equal(intersection(evenDFA, oddDFA), noDFA);
							accepts = (isEqual)?"(evenDFA n oddDFA) is equal to noDFA":"(evenDFA n oddDFA) is not equal to noDFA";
							System.out.println(accepts);
							
							dfaToTest = false;
							break;
						
						case "9":
							isEqual = equal(complement(allDFA), noDFA);
							accepts = (isEqual)?"~(allDFA) is equal to noDFA":"~(allDFA) is not equal to noDFA";
							System.out.println(accepts);
							
							dfaToTest = false;
							break;
						
						case "10":
							isEqual = equal(intersection(allDFA, oddDFA), oddDFA);
							accepts = (isEqual)?"(allDFA n oddDFA) is equal to oddDFA":"(allDFA n oddDFA) is not equal to oddDFA";
							System.out.println(accepts);
							
							dfaToTest = false;
							break;
						
						case "11":
							isEqual = equal(complement(oddDFA), evenDFA);																//Counterintuitively untrue because the empty string is in ~(oddDFA) and not in evenDFA
							accepts = (isEqual)?"~(oddDFA) is equal to evenDFA":"~(oddDFA) is not equal to evenDFA";
							System.out.println(accepts);
							
							dfaToTest = false;
							break;
							
						case "12":
							isEqual = equal(intersection(complement(oddDFA), complement(emptyDFA)), evenDFA);							//Always consider the empty string
							accepts = (isEqual)?"(~(oddDFA) n ~(emptyDFA)) is equal to evenDFA":"(~(oddDFA) n ~(emptyDFA)) is not equal to evenDFA";
							System.out.println(accepts);
							
							dfaToTest = false;
							break;
						
						default:
							dfaToTest = false;
							System.out.println("\nPlease enter a valid input!\n");
							break;
					
					}
					
					break;
					
				case "8":
					isEqual = equal( union( intersection( complement(evenDFA), complement(oddDFA) ), oddDFA), complement(evenDFA));		//(~(evenDFA) n ~(oddDFA)) u oddDFA = ~(evenDFA)					
					accepts = (isEqual)?"It all works":"It doesn't all work";
					System.out.println(accepts + "\n");
					
					dfaToTest = false;
					break;
					
				default:
					dfaToTest = false;
					System.out.println("\nPlease enter a valid input!\n");
					break;
			
			}
			
			if(cont && dfaToTest){
				testStringIndex++;
				
				System.out.println("\nString to be tested: '" + testString.displayable() + "'");
				
				accepts = (currentDFA.run(testString))? "String Accepted":"String Rejected";	//Run DFA and return whether the string was accepted or not
				
				System.out.println("\n" + accepts + "\n");
				
				System.out.println("Trace: " + currentDFA.getTrace().displayable() + "\n");		//Print the trace of states visited
				
			}
			
		}
		
	}
	
	//Intialize destination states to a single state for a state map
	public static void initializeStates(Alphabet alphabet, ArrayList<State> states, ArrayList<State> nextStates, State state){
		for(int i = 0; i < alphabet.size() * states.size(); i++){
			nextStates.add(state);
			
		}
		
	}
	
	//Function that returns a DFA with a language consisting of a single character
	public static DFA nDFA(Alphabet alphabet, Character character){
		ArrayList<State> states = new ArrayList<State>(Arrays.asList(new State("A"), new State("B"), new State("C")));	//Q = {A, B, C}
		
		ArrayList<State> nextStates = new ArrayList<State>();
		
		for(int i = 0; i < (states.size() * alphabet.size()); i++){
			nextStates.add(states.get(2));																				//([All States], [All Characters], C)
			
		}
		
		nextStates.set(alphabet.findIndex(character), states.get(1));													//Except for (A, [accepting character], B)
		
		State startState = states.get(0);																				//q0 = A
		
		ArrayList<State> acceptingStates = new ArrayList<State>(Arrays.asList(states.get(1)));							//F = {B}
		
		DFA nDFA = new DFA(states, alphabet, startState, nextStates, acceptingStates);
		
		return nDFA;
		
	}
	
	//Function that given a DFA, returns a string that is accepted by that DFA
	public static AlphaString acceptingString(DFA dfa){
		
		if(dfa.getAcceptingStates().size() < 1){	//If there are no accepting states
			return null;							//Return null
		}
		
		ArrayList<State> states = dfa.getStates(); 
		State curState = dfa.getAcceptingStates().get(0);	//Current state = acceptingStates[0] - i.e. the first accepting state
		State startState = dfa.getStartState();
		
		StateTable stateTable = dfa.getStateTable();
		
		Alphabet alphabet = dfa.getAlphabet();
		
		AlphaString acceptingString = new AlphaString(alphabet);
		
		boolean newState;
				
		while(curState != startState){				//Until we arrive at the starting state
			newState = false;
			
			for(int i = 0; i <= states.size(); i++){
				if(newState){						//Current state has been updated
					break;							//Stop searching
					
				}
				
				if(i == states.size()) {			//No states point to c
					return null;
					
				}
				
				for(int j = 0; j < alphabet.size(); j++){
					if(stateTable.get(i, j) == curState && states.get(i) != curState){	//StateTable[Previous State][Character] == Current State
						acceptingString.pushChar(alphabet.get(j));							//Push Character into the String to be returned
						curState = states.get(i);										//Current State = Previous State
						newState = true;												
						break;															//Stop searching
						
					}
					
				}	
				
			}
			
		}
		
		return acceptingString;
		
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
	public static boolean equal(DFA dfa1, DFA dfa2) {
		return(subset(dfa1, dfa2) && subset(dfa2, dfa1)); 			//(dfa1 c dfa2) and (dfa2 c dfa1) -> dfa1 == dfa2
		
	}

}
