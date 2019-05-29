import java.util.ArrayList;
import java.util.Arrays;

import java.util.Scanner;

//Create and test various DFAs and DFA-related functions

public class DFAtest {
	
	//Initialize Alphabet(s)-----------------------------------------------------------------------------------------------
		//The binary numbers
		private static ArrayList<Character> biSymbols =  new ArrayList<Character>(Arrays.asList(new Character("0"), new Character("1")));
		
		private static Alphabet biAlphabet = new Alphabet(biSymbols);
		
		//a, b, and c
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
		
		private static ArrayList<State> noDFAStates = new ArrayList<State>(Arrays.asList(new State("A")));										//States = {A}
		
		private static State noDFAStartState = noDFAStates.get(0);																				//StartStae = A
		
		private static ArrayList<State> noDFANextStates = new ArrayList<State>(Arrays.asList(noDFAStates.get(0), noDFAStates.get(0)));			//(A, '0', A), (A, '1', A)
		
		private static ArrayList<State> noDFAAcceptingStates = new ArrayList<State>();															//AcceptingStates = {}
		
		private static DFA noDFA = new DFA(noDFAStates, biAlphabet, noDFAStartState, noDFANextStates, noDFAAcceptingStates);
	//----------------------------------------------------------------------------------------------------------------------
	
	//DFA that accepts only the empty string (The "emptyDFA")---------------------------------------------------------------
		private static ArrayList<Character> emptyTestCharacters = new ArrayList<Character>();
		private static AlphaString emptyTestString =  new AlphaString(biAlphabet, emptyTestCharacters);											//input = ""
		
		private static ArrayList<State> emptyDFAStates = new ArrayList<State>(Arrays.asList(new State("A"), new State("B")));					//States = {A, B}
		
		private static State emptyDFAStartState = emptyDFAStates.get(0);																		//StartState = A
		
		private static ArrayList<State> emptyDFANextStates = new ArrayList<State>(Arrays.asList(emptyDFAStates.get(1), emptyDFAStates.get(1),	//(A, '0', B), (A, '1', B)
																								emptyDFAStates.get(1), emptyDFAStates.get(1)));	//(B, '0', B), (B, '1', B)
		
		private static ArrayList<State> emptyDFAAcceptingStates = new ArrayList<State>(Arrays.asList(emptyDFAStates.get(0)));					//Accepting states = {A}
		
		private static DFA emptyDFA = new DFA(emptyDFAStates, biAlphabet, emptyDFAStartState, emptyDFANextStates, emptyDFAAcceptingStates);
	//----------------------------------------------------------------------------------------------------------------------
	
	//DFA that accepts only the character "n" in an alphabet (The "nDFA")---------------------------------------------------
		private static Character abcTestCharacter = abcAlphabet.get(2);																			//Input is "c"
		
		private static DFA nDFA = nDFA(abcAlphabet, abcAlphabet.get(2));																		//Generate a DFA that will only accept the string "c"
	//----------------------------------------------------------------------------------------------------------------------
		
	//DFA that accepts odd binary #s (The "oddDFA")-------------------------------------------------------------------------
		private static ArrayList<Character> oddTestCharacters =  new ArrayList<Character>(Arrays.asList(biAlphabet.get(0), biAlphabet.get(1), biAlphabet.get(1)));
		private static AlphaString oddTestString = new AlphaString(biAlphabet, oddTestCharacters);												//Input = "011"
		
		private static ArrayList<State> oddDFAStates = new ArrayList<State>(Arrays.asList(new State("A"), new State("B")));						//States = {A}
		
		private static State oddDFAStartState =oddDFAStates.get(0);																				//StartStae = A
		
		private static ArrayList<State>oddDFANextStates = new ArrayList<State>(Arrays.asList(oddDFAStates.get(0), oddDFAStates.get(1),			//(A, '0', A), (A, '1', B)
																		                     oddDFAStates.get(0), oddDFAStates.get(1)));		//(B, '0', A), (B, '1', B)
		
		private static ArrayList<State>oddDFAAcceptingStates = new ArrayList<State>(Arrays.asList(oddDFAStates.get(1)));						//AcceptingStates = {B}
		
		private static DFA oddDFA = new DFA(oddDFAStates, biAlphabet,oddDFAStartState,oddDFANextStates,oddDFAAcceptingStates);
	//----------------------------------------------------------------------------------------------------------------------
		
	//DFA that accepts even binary #s (The "evenDFA")-------------------------------------------------------------------------
		private static ArrayList<Character> evenTestCharacters =  new ArrayList<Character>(Arrays.asList(biAlphabet.get(0), biAlphabet.get(1), biAlphabet.get(1)));
		private static AlphaString evenTestString = new AlphaString(biAlphabet, evenTestCharacters);												//Input = "011"
				
		private static ArrayList<State> evenDFAStates = new ArrayList<State>(Arrays.asList(new State("A"), new State("B")));						//States = {A, B}
				
		private static State evenDFAStartState =evenDFAStates.get(0);																				//StartStae = A
				
		private static ArrayList<State>evenDFANextStates = new ArrayList<State>(Arrays.asList(evenDFAStates.get(1), evenDFAStates.get(0),			//(A, '0', B), (A, '1', A)
																				              evenDFAStates.get(1), evenDFAStates.get(0)));			//(B, '0', B), (B, '1', A)
				
		private static ArrayList<State>evenDFAAcceptingStates = new ArrayList<State>(Arrays.asList(evenDFAStates.get(1)));							//AcceptingStates = {B}
				
		private static DFA evenDFA = new DFA(evenDFAStates, biAlphabet,evenDFAStartState,evenDFANextStates,evenDFAAcceptingStates);
	//----------------------------------------------------------------------------------------------------------------------
		
	//DFA that accepts 3 bit binary #s (The "tbDFA")------------------------------------------------------------------------
			private static ArrayList<Character> tbTestCharacters =  new ArrayList<Character>(Arrays.asList(biAlphabet.get(0), biAlphabet.get(1), biAlphabet.get(1)));
			private static AlphaString tbTestString = new AlphaString(biAlphabet, tbTestCharacters);												//Input = "011"
			
			private static ArrayList<State> tbDFAStates = new ArrayList<State>(Arrays.asList(new State("A"), new State("B"), new State("C"), 
																											 new State("D"), new State("E")));		//States = {A, B, C, D, E}
			
			private static State tbDFAStartState =tbDFAStates.get(0);																				//StartStae = A
			
			private static ArrayList<State>tbDFANextStates = new ArrayList<State>(Arrays.asList(tbDFAStates.get(1),tbDFAStates.get(1),				//(A, '0', B), (A, '1', B)
																			                    tbDFAStates.get(2),tbDFAStates.get(2),				//(B, '0', C), (B, '1', C)
																			                    tbDFAStates.get(3),tbDFAStates.get(3),				//(C, '0', D), (C, '1', D)
																			                    tbDFAStates.get(4),tbDFAStates.get(4),				//(D, '0', E), (D, '1', E)
			 																					tbDFAStates.get(4),tbDFAStates.get(4)));			//(E, '0', E), (E, '1', E)
			
			private static ArrayList<State>tbDFAAcceptingStates = new ArrayList<State>(Arrays.asList( tbDFAStates.get(3)));							//AcceptingStates = {D}
			
			private static DFA tbDFA = new DFA(tbDFAStates, biAlphabet,tbDFAStartState,tbDFANextStates,tbDFAAcceptingStates);
	//----------------------------------------------------------------------------------------------------------------------
			
	//DFA that accepts even length binary #s (The "elDFA")------------------------------------------------------------------
			private static ArrayList<Character> elTestCharacters =  new ArrayList<Character>(Arrays.asList(biAlphabet.get(0), biAlphabet.get(1), biAlphabet.get(1)));
			private static AlphaString elTestString = new AlphaString(biAlphabet, elTestCharacters);												//Input = "011"
			
			private static ArrayList<State> elDFAStates = new ArrayList<State>(Arrays.asList(new State("A"), new State("B")));						//States = {A, B}
			
			private static State elDFAStartState =elDFAStates.get(0);																				//StartStae = A
			
			private static ArrayList<State>elDFANextStates = new ArrayList<State>(Arrays.asList(elDFAStates.get(1),elDFAStates.get(1),				//(A, '0', B), (A, '1', B)
																			                    elDFAStates.get(0),elDFAStates.get(0)));			//(B, '0', A), (B, '1', A)
																			                   
			
			private static ArrayList<State>elDFAAcceptingStates = new ArrayList<State>(Arrays.asList( elDFAStates.get(0)));							//AcceptingStates = {A}
			
			private static DFA elDFA = new DFA(elDFAStates, biAlphabet,elDFAStartState,elDFANextStates,elDFAAcceptingStates);
	//----------------------------------------------------------------------------------------------------------------------
			
	//DFA that accepts odd length binary #s (The olDFA")-------------------------------------------------------------------
			private static ArrayList<Character> olTestCharacters =  new ArrayList<Character>(Arrays.asList(biAlphabet.get(0), biAlphabet.get(1), biAlphabet.get(1)));
			private static AlphaString olTestString = new AlphaString(biAlphabet, olTestCharacters);												//Input = "011"
			
			private static ArrayList<State> olDFAStates = new ArrayList<State>(Arrays.asList(new State("A"), new State("B")));						//States = {A, B}
			
			private static State olDFAStartState =olDFAStates.get(0);																				//StartStae = A
			
			private static ArrayList<State> olDFANextStates = new ArrayList<State>(Arrays.asList(olDFAStates.get(1),olDFAStates.get(1),				//(A, '0', B), (A, '1', B)
																			                    olDFAStates.get(0),olDFAStates.get(0)));			//(B, '0', A), (B, '1', A)
																			                   
			
			private static ArrayList<State> olDFAAcceptingStates = new ArrayList<State>(Arrays.asList( olDFAStates.get(1)));						//AcceptingStates = {B}
			
			private static DFA olDFA = new DFA(olDFAStates, biAlphabet,olDFAStartState,olDFANextStates,olDFAAcceptingStates);
	//----------------------------------------------------------------------------------------------------------------------
			
	//DFA that accepts a string of my name "ben" (The benDFA")---------------------------------------------------------------
			private static ArrayList<Character> benTestCharacters =  new ArrayList<Character>(Arrays.asList(engAlphabet.get(1), engAlphabet.get(4), engAlphabet.get(13)));
			private static AlphaString benTestString = new AlphaString(engAlphabet, benTestCharacters);
			
			private static ArrayList<State> benDFAStates = new ArrayList<State>(Arrays.asList(new State("A"), new State("B"), new State("C"), 								//States = {A, B, C, D, E}
					 																		  new State("D"), new State("E")));
			
			private static State benDFAStartState = benDFAStates.get(0);																									//StartState = A
			
			private static ArrayList<State> benDFANextStates = new ArrayList<State>();
			
			private static ArrayList<State> benDFAAcceptingStates = new ArrayList<State>(Arrays.asList(benDFAStates.get(3)));												//AcceptingStates = {D}
	//----------------------------------------------------------------------------------------------------------------------
			
	//DFA that accepts a string that starts with "aa" (The aaDFA")---------------------------------------------------------------
			private static ArrayList<Character> aaTestCharacters =  new ArrayList<Character>(Arrays.asList(engAlphabet.get(0), engAlphabet.get(25)));
			private static AlphaString aaTestString = new AlphaString(engAlphabet, aaTestCharacters);
			
			private static ArrayList<State> aaDFAStates = new ArrayList<State>(Arrays.asList(new State("A"), new State("B"), new State("C"), 								//States = {A, B, C, D}
					 																		  new State("D")));
			
			private static State aaDFAStartState = aaDFAStates.get(0);																										//StartState = A
			
			private static ArrayList<State> aaDFANextStates = new ArrayList<State>();																						
			
			private static ArrayList<State> aaDFAAcceptingStates = new ArrayList<State>(Arrays.asList(aaDFAStates.get(3)));													//AcceptingStates = {D}
	//----------------------------------------------------------------------------------------------------------------------
	
	//DFA that accepts a string that contains "ab" somewhere in it (The "abDFA")---------------------------------------------
			private static ArrayList<Character> abTestCharacters =  new ArrayList<Character>(Arrays.asList(abcAlphabet.get(0), abcAlphabet.get(1), abcAlphabet.get(1)));
			private static AlphaString abTestString = new AlphaString(abcAlphabet, abTestCharacters);																		//Input = "abb"
			
			private static ArrayList<State> abDFAStates = new ArrayList<State>(Arrays.asList(new State("A"), new State("B"), new State("C")));								//States = {A, B, C}
				
			private static State abDFAStartState = abDFAStates.get(0);																										//StartStae = A
			
			private static ArrayList<State> abDFANextStates = new ArrayList<State>(Arrays.asList(abDFAStates.get(1), abDFAStates.get(0), abDFAStates.get(0),				//(A, 'a', B), (A, 'b', A), (A, 'c', A)
																								 abDFAStates.get(1), abDFAStates.get(2), abDFAStates.get(0),                //(B, 'a', B), (B, 'b', C), (B, 'c', A)
																			                     abDFAStates.get(2), abDFAStates.get(2), abDFAStates.get(2)));				//(C, 'a', C), (C, 'b', C), (C, 'c', C)
																			                   
			
			private static ArrayList<State> abDFAAcceptingStates = new ArrayList<State>(Arrays.asList( abDFAStates.get(2)));												//AcceptingStates = {C}
			
			private static DFA abDFA = new DFA(abDFAStates, abcAlphabet, abDFAStartState, abDFANextStates, abDFAAcceptingStates);
	//----------------------------------------------------------------------------------------------------------------------
			
	//DFA that accepts all strings of bits (The "allDFA")-------------------------------------------------------------------
			private static ArrayList<Character> allTestCharacters =  new ArrayList<Character>(Arrays.asList(biAlphabet.get(0), biAlphabet.get(1), biAlphabet.get(1)));
			private static AlphaString allTestString = new AlphaString(biAlphabet, allTestCharacters);																		//Input = "011"
			
			private static ArrayList<State> allDFAStates = new ArrayList<State>(Arrays.asList(new State("A")));																//States = {A}
			
			private static State allDFAStartState =allDFAStates.get(0);																										//StartStae = A
			
			private static ArrayList<State> allDFANextStates = new ArrayList<State>(Arrays.asList(allDFAStates.get(0),allDFAStates.get(0)));								//(A, '0', A), (A, '1', A)
																			                   
			
			private static ArrayList<State> allDFAAcceptingStates = new ArrayList<State>(Arrays.asList(allDFAStates.get(0)));												//AcceptingStates = {A}
			
			private static DFA allDFA = new DFA(allDFAStates, biAlphabet,allDFAStartState,allDFANextStates,allDFAAcceptingStates);
	//----------------------------------------------------------------------------------------------------------------------
	
	//DFA that accepts a string that starts with 'a' and ends with 'c' (The acDFA")-----------------------------------------
			private static ArrayList<Character> acTestCharacters =  new ArrayList<Character>(Arrays.asList(abcAlphabet.get(0), abcAlphabet.get(1), abcAlphabet.get(2)));
			private static AlphaString acTestString = new AlphaString(abcAlphabet, acTestCharacters);																		//Input = "abc"
			
			private static ArrayList<State> acDFAStates = new ArrayList<State>(Arrays.asList(new State("A"), new State("B"), new State("C"), new State("D")));				//States = {A, B, C, D}
			
			private static State acDFAStartState = acDFAStates.get(0);																										//StartingState = A
			
			private static ArrayList<State> acDFANextStates =  new ArrayList<State>(Arrays.asList(acDFAStates.get(2), acDFAStates.get(1), acDFAStates.get(1),				//(A, 'a', C), (A, 'b', B), (A, 'c', B) 
					 																			  acDFAStates.get(1), acDFAStates.get(1), acDFAStates.get(1),				//(B, 'a', B), (B, 'b', B), (B, 'c', B)
					 																			  acDFAStates.get(2), acDFAStates.get(2), acDFAStates.get(3),				//(C, 'a', C), (C, 'b', C), (C, 'c', D)
					 																			  acDFAStates.get(2), acDFAStates.get(2), acDFAStates.get(3)));				//(D, 'a', C), (D, 'b', C), (D, 'c', D)
			
			private static ArrayList<State> acDFAAcceptingStates = new ArrayList<State>(Arrays.asList(acDFAStates.get(3)));													//AcceptingStates = {D}
			
			private static DFA acDFA = new DFA(acDFAStates, abcAlphabet, acDFAStartState, acDFANextStates, acDFAAcceptingStates);
	//----------------------------------------------------------------------------------------------------------------------
			
	//DFA that accepts a string that starts with 'a' or ends with 'c' (The acOrDFA")----------------------------------------
			private static ArrayList<Character> acOrTestCharacters =  new ArrayList<Character>(Arrays.asList(abcAlphabet.get(0), abcAlphabet.get(1), abcAlphabet.get(2)));
			private static AlphaString acOrTestString = new AlphaString(abcAlphabet, acOrTestCharacters);																		//Input = "abc"
			
			private static ArrayList<State> acOrDFAStates = new ArrayList<State>(Arrays.asList(new State("A"), new State("B"), new State("C"), new State("D")));				//States = {A, B, C, D}
			
			private static State acOrDFAStartState = acOrDFAStates.get(0);																										//StartingState = A
			
			private static ArrayList<State> acOrDFANextStates =  new ArrayList<State>(Arrays.asList(acOrDFAStates.get(1), acOrDFAStates.get(3), acOrDFAStates.get(2),			//(A, 'a', B), (A, 'b', D), (A, 'c', C) 
					 																			    acOrDFAStates.get(1), acOrDFAStates.get(1), acOrDFAStates.get(1),			//(B, 'a', B), (B, 'b', B), (B, 'c', B)
					 																			    acOrDFAStates.get(3), acOrDFAStates.get(3), acOrDFAStates.get(2),			//(C, 'a', D), (C, 'b', D), (C, 'c', C)
					 																			    acOrDFAStates.get(3), acOrDFAStates.get(3), acOrDFAStates.get(2)));			//(D, 'a', D), (D, 'b', D), (D, 'c', C)
			
			private static ArrayList<State> acOrDFAAcceptingStates = new ArrayList<State>(Arrays.asList(acOrDFAStates.get(1), acOrDFAStates.get(2)));							//AcceptingStates = {B, C}
			
			private static DFA acOrDFA = new DFA(acOrDFAStates, abcAlphabet, acOrDFAStartState, acOrDFANextStates, acOrDFAAcceptingStates);
	//----------------------------------------------------------------------------------------------------------------------
			
	//DFA that accepts a sting containing the binary # "01", or "10" (The "ooDFA")------------------------------------------------------------------------
			private static ArrayList<Character> ooTestCharacters =  new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(1), biAlphabet.get(0)));
			private static AlphaString ooTestString = new AlphaString(biAlphabet, ooTestCharacters);												//Input = "110"
			
			private static ArrayList<State> ooDFAStates = new ArrayList<State>(Arrays.asList(new State("A"), new State("B"), new State("C"), 
																											 new State("D")));						//States = {A, B, C, D}
			
			private static State ooDFAStartState =ooDFAStates.get(0);																				//StartStae = A
			
			private static ArrayList<State>ooDFANextStates = new ArrayList<State>(Arrays.asList(ooDFAStates.get(1),ooDFAStates.get(2),				//(A, '0', B), (A, '1', C)
																			                    ooDFAStates.get(1),ooDFAStates.get(3),				//(B, '0', B), (B, '1', D)
																			                    ooDFAStates.get(3),ooDFAStates.get(2),				//(C, '0', D), (C, '1', C)
			 																					ooDFAStates.get(3),ooDFAStates.get(3)));			//(D, '0', D), (D, '1', D)
			
			private static ArrayList<State>ooDFAAcceptingStates = new ArrayList<State>(Arrays.asList(ooDFAStates.get(3)));							//AcceptingStates = {D}
			
			private static DFA ooDFA = new DFA(ooDFAStates, biAlphabet,ooDFAStartState,ooDFANextStates,ooDFAAcceptingStates);
	//----------------------------------------------------------------------------------------------------------------------
			
	public static void main(String[] args) { 
		boolean cont = true;
		
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		
		int input = 0;
		
		AlphaString testString = new AlphaString();
		
		DFA currentDFA = new DFA();
		
		while(cont){
			System.out.println("0  - Quit ");
			System.out.println("1  - DFA that accepts no strings of bits");
			System.out.println("2  - DFA that only accepts the empty string");
			System.out.println("3  - DFA that only accepts a string of a single character 'c'");
			System.out.println("4  - DFA that only accepts a string of an odd binary number");
			System.out.println("5  - DFA that only accepts a string of an even binary number");
			System.out.println("6  - DFA that only accepts a string of a binary number of length 3");
			System.out.println("7  - DFA that only accepts a string made of an even number of bits");
			System.out.println("8  - DFA that only accepts a string made of an odd number of bits");
			System.out.println("9  - DFA that only accepts a string of my first name, 'ben' in all lower case letters");
			System.out.println("10 - DFA that only accepts a string made up of 'az'");
			System.out.println("11 - DFA that only accepts a string that contains 'ab'");
			System.out.println("12 - DFA that accepts all strings of bits");
			System.out.println("13 - DFA that only accepts a string that begins with 'a' and ends with 'c'");
			System.out.println("14 - DFA that only accepts a string that begins with 'a' or ends with 'c'");
			System.out.println("15 - DFA that only accepts a string that contains the binary number '01' or '10'");
			
			System.out.println("\nWhich DFA would you like to test?: ");
			
			input = scanner.nextInt();
			
			switch(input){
				case 0:
					System.out.println("\nBye...");
					cont = false;
					break;
					
				case 1:
					testString = noTestString;
					currentDFA = noDFA;
					break;
					
				case 2:
					testString = emptyTestString;
					currentDFA = emptyDFA;
					break;
					
				case 3:
					testString = new AlphaString(abcAlphabet, new ArrayList<Character>(Arrays.asList(abcTestCharacter)));
					currentDFA = nDFA;
					break;
					
				case 4:
					testString = oddTestString;
					currentDFA = oddDFA;
					break;
					
				case 5:
					testString = evenTestString;
					currentDFA = evenDFA;
					break;
					
				case 6:
					testString = tbTestString;
					currentDFA = tbDFA;
					break;
					
				case 7:
					testString = elTestString;
					currentDFA = elDFA;
					break;
					
				case 8:
					testString = olTestString;
					currentDFA = olDFA;
					break;
				case 9:
					testString = benTestString;
					
					initializeStates(engAlphabet, benDFAStates, benDFANextStates, benDFAStates.get(4)); //([ALL States], [ALL Characters], E])
																										//Except for
					benDFANextStates.set((26*0 + 1), benDFAStates.get(1));								//(A, 'b', B)
					benDFANextStates.set((26*1 + 4), benDFAStates.get(2));								//(B, 'e', C)
					benDFANextStates.set((26*2 + 13), benDFAStates.get(3));								//(C, 'n', D)
					
					DFA benDFA = new DFA(benDFAStates, engAlphabet, benDFAStartState, benDFANextStates, benDFAAcceptingStates);
					currentDFA = benDFA;
					
					break;
					
				case 10:
					testString = aaTestString;		
					
					initializeStates(engAlphabet, aaDFAStates, aaDFANextStates, aaDFAStates.get(2));//([ALL States], [ALL Characters], C])
																									//Except for
					aaDFANextStates.set((26*0 + 0), aaDFAStates.get(1));							//(A, 'a', B)
					aaDFANextStates.set((26*1 + 25), aaDFAStates.get(3));							//(B, 'z', D)
					
					for(int i = 0; i < engAlphabet.size(); i++){
						aaDFANextStates.set((26*3 + i), aaDFAStates.get(3));						//(D, [All Characters], D)
						
					}
					
					DFA aaDFA = new DFA(aaDFAStates, engAlphabet, aaDFAStartState, aaDFANextStates, aaDFAAcceptingStates);
					currentDFA = aaDFA;
					
					break;
					
				case 11:
					testString = abTestString;
					currentDFA = abDFA;
					break;
					
				case 12:
					testString = allTestString;
					currentDFA = allDFA;
					break;
					
				case 13:
					testString = acTestString;
					currentDFA = acDFA;
					break;
					
				case 14:
					testString = acOrTestString;
					currentDFA = acOrDFA;
					break;
					
				case 15:
					testString = ooTestString;
					currentDFA = ooDFA;
					break;
					
				default:
					System.out.println("Please enter a valid input!");
					break;
			
			}
			
			if(cont){
				
				System.out.println("\nString to be tested: '" + testString.displayable() + "'");
				
				String accepts = (currentDFA.run(testString))? "String Accepted":"String Rejected";	//Run DFA and return whether 
				
				currentDFA.resetDFA();
				
				System.out.println("\n" + accepts + "\n");
				
				System.out.println("Trace: " + currentDFA.getTrace().displayable() + "\n");
				
				currentDFA.resetDFA();
				
			}
			
		}
		
	}
	
	//Function that returns a DFA that only accepts a string made up of a single, specific character
	public static DFA nDFA(Alphabet alphabet, Character character){
		ArrayList<State> states = new ArrayList<State>(Arrays.asList(new State("A"), new State("B"), new State("C")));	//States = {A, B, C}
		
		ArrayList<State> nextStates = new ArrayList<State>();
		
		for(int i = 0; i < (states.size() * alphabet.size()); i++){
			nextStates.add(states.get(2));																				//([All States], [All Characters], C)
			
		}
		
		nextStates.set(alphabet.findIndex(character), states.get(1));													//Except for (A, [accepting character], B)
		
		State startState = states.get(0);																				//StartState = A
		
		ArrayList<State> acceptingStates = new ArrayList<State>(Arrays.asList(states.get(1)));							//AcceptingStates = {B}
		
		DFA nDFA = new DFA(states, alphabet, startState, nextStates, acceptingStates);
		
		return nDFA;
		
	}
	
	public static void initializeStates(Alphabet alphabet, ArrayList<State> states, ArrayList<State> nextStates, State state){
		for(int i = 0; i < alphabet.size() * states.size(); i++){
			nextStates.add(state);
			
		}
		
	}
	
	public static AlphaString acceptString(DFA dfa){
		AlphaString acceptString = new AlphaString();
		
		return acceptString;
		
	}

}
