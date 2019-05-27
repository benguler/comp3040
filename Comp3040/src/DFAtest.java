import java.util.ArrayList;
import java.util.Arrays;

import java.util.Scanner;

//Create and test various DFAs

public class DFAtest {
	
	//Initialize Alphabet(s)-----------------------------------------------------------------------------------------------
	private static ArrayList<Character> biSymbols =  new ArrayList<Character>(Arrays.asList(new Character("0"), new Character("1")));
	private static Alphabet biAlphabet = new Alphabet(biSymbols);
	//---------------------------------------------------------------------------------------------------------------------

	//DFA that accepts no strings (The "noDFA")----------------------------------------------------------------------------
	private static ArrayList<Character> noTestCharacters =  new ArrayList<Character>(Arrays.asList(biAlphabet.get(0), biAlphabet.get(1)));
	private static AlphaString noTestString = new AlphaString(biAlphabet, noTestCharacters);										//Input = "01"
	
	private static ArrayList<State> noDFAStates = new ArrayList<State>(Arrays.asList(new State("A")));								//States = {A}
	
	private static State noDFAStartState = noDFAStates.get(0);																		//StartStae = A
	
	private static ArrayList<State> noDFANextStates = new ArrayList<State>(Arrays.asList(noDFAStates.get(0), noDFAStates.get(0)));	//NextState = {(A, 0, A), (A, 1, A)}
	
	private static ArrayList<State> noDFAAcceptingStates = new ArrayList<State>();													//AcceptingStates = {}
	
	private static DFA noDFA = new DFA(noDFAStates, biAlphabet, noDFAStartState, noDFANextStates, noDFAAcceptingStates);
	//----------------------------------------------------------------------------------------------------------------------
	
	//DFA that accepts only the empty (The "emptyDFA")----------------------------------------------------------------------
	private static ArrayList<Character> emptyTestCharacters =  new ArrayList<Character>();
	private static AlphaString emptyTestString = new AlphaString(biAlphabet, emptyTestCharacters);												//input = ""
	
	private static ArrayList<State> emptyDFAStates = new ArrayList<State>(Arrays.asList(new State("A"), new State("B")));						//States = {A, B}
	
	private static State emptyDFAStartState = emptyDFAStates.get(0);																			//StartState = A
	
	private static ArrayList<State> emptyDFANextStates = new ArrayList<State>(Arrays.asList(emptyDFAStates.get(1), emptyDFAStates.get(1),		//NextState = {(A, 0, B), (A, 1, B)
																							emptyDFAStates.get(1), emptyDFAStates.get(1)));		//			   (B, 0, B), (B, 1, B)}
	
	private static ArrayList<State> emptyDFAAcceptingStates = new ArrayList<State>(Arrays.asList(emptyDFAStates.get(0)));						//Accepting states = {A}
	
	private static DFA emptyDFA = new DFA(emptyDFAStates, biAlphabet, emptyDFAStartState, emptyDFANextStates, emptyDFAAcceptingStates);
	//----------------------------------------------------------------------------------------------------------------------
	
	public static void main(String[] args) { 
		boolean cont = true;
		
		Scanner scanner = new Scanner(System.in);
		
		int input = 0;
		
		AlphaString testString = new AlphaString();
		
		DFA currentDFA = new DFA();
		
		while(cont){
			System.out.println("0 - Quit ");
			System.out.println("1 - DFA that accepts no strings");
			System.out.println("2 - DFA that only accepts the empty string");
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
					
				default:
					System.out.println("Please enter a valid input!");
					break;
			
			}
			
			if(cont){
				System.out.println("\nString to be tested: '" + testString.displayable() + "'");
				
				for(int i = 0; i < testString.length(); i++){
					currentDFA.findNextState(testString.getChar(i));
					
				}
				
				String accepts = (currentDFA.acceptReject())? "String Accepted":"String Rejected"; 
				
				System.out.println("\n" + accepts + "\n");
				
			}
			
		}
		
	}

}
