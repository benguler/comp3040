import java.util.ArrayList;
import java.util.Arrays;

public class NFATest {
	
//Initialize Alphabet(s)-----------------------------------------------------------------------------------------------
		private static final Character epsilon = new Character(""); //ϵ

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
	
	//NFA that accepts odd binary #s (The "oddDFA")-------------------------------------------------------------------------
		private static ArrayList<State> oddNFAStates = new ArrayList<State>(Arrays.asList(new State("odd0"), new State("odd1")));						//Q = {A, B}
		
		private static State oddNFAStartState = oddNFAStates.get(0);																			//q0 = A
		
		private static ArrayList<ArrayList<State>>oddNFANextStates = new ArrayList<ArrayList<State>>(Arrays.asList(
																									 newList( oddNFAStates.get(0) ), newList( oddNFAStates.get(0), oddNFAStates.get(1) ), newList(),	//Delta = {(A, '0', {A}), (A, '1', {A, B}), (A, epsilon, {})
																									 newList(), newList(), newList())																//        {(B, '0', {}), (B, '1', {}), (B, epsilon, {})
																									);	
																									
		
		private static ArrayList<State>oddNFAAcceptingStates = new ArrayList<State>(Arrays.asList(oddNFAStates.get(1)));						//F = {B}
		
		private static NFA oddNFA = new NFA(oddNFAStates, biAlphabet,oddNFAStartState,oddNFANextStates,oddNFAAcceptingStates, epsilon);
	//----------------------------------------------------------------------------------------------------------------------
		
	//NFA that accepts even binary #s (The "evenDFA")-----------------------------------------------------------------------
		private static ArrayList<State> evenNFAStates = new ArrayList<State>(Arrays.asList(new State("even0"), new State("even1")));						//Q = {A, B}
		
		private static State evenNFAStartState = evenNFAStates.get(0);																				//q0 = A
		
		private static ArrayList<ArrayList<State>>evenNFANextStates = new ArrayList<ArrayList<State>>(Arrays.asList(
																									 newList( evenNFAStates.get(0), evenNFAStates.get(1)), newList( evenNFAStates.get(0)), newList(),	//Delta = {(A, '0', {A, B}), (A, '1', {A}), (A, epsilon, {})
																									 newList(), newList(), newList())																	//        {(B, '0', {}),     (B, '1', {}),  (B, epsilon, {})
																									);	
																									
		
		private static ArrayList<State>evenNFAAcceptingStates = new ArrayList<State>(Arrays.asList(evenNFAStates.get(1)));						//F = {B}
		
		private static NFA evenNFA = new NFA(evenNFAStates, biAlphabet,evenNFAStartState,evenNFANextStates,evenNFAAcceptingStates, epsilon);
	//----------------------------------------------------------------------------------------------------------------------

	//NFA that accepts a binary number with a 1 at the 2nd or 3rd position from the end in the string (The "stDFA")-------					//I.E. 1000 or 0100, but not 0010 or 0001
		private static ArrayList<State> stNFAStates = new ArrayList<State>(Arrays.asList(new State("st0"), new State("st1"), new State("st2"), new State("st3")));						//Q = {A, B, C, D}
		
		private static State stNFAStartState = stNFAStates.get(0);																				//q0 = A
		
		private static ArrayList<ArrayList<State>>stNFANextStates = new ArrayList<ArrayList<State>>(Arrays.asList(
				 																					 newList(stNFAStates.get(0)), newList(stNFAStates.get(0), stNFAStates.get(1)), newList(),	//Delta = {(A, '0', {A}),(A, 1, {A, B}).(A, epsilon, {})
				 																					 newList(stNFAStates.get(2)), newList(stNFAStates.get(2)), newList(stNFAStates.get(2)),		//		   (B, '0', {C}),(B, '1', {C}), (B, epsilon, {C})
																									 newList(stNFAStates.get(3)), newList(stNFAStates.get(3)), newList(),						//		   (C, '0', {D}),(C, '1', {D}), (C, epsilon, {})
																									 newList(), newList(), newList())															//		   (D, '0', {}), (D, '1', {}), (D, epsilon, {})				
																									);	
																									
		
		private static ArrayList<State>stNFAAcceptingStates = new ArrayList<State>(Arrays.asList(stNFAStates.get(3)));						//F = {D}
		
		private static NFA stNFA = new NFA(stNFAStates, biAlphabet, stNFAStartState,stNFANextStates,stNFAAcceptingStates, epsilon);
	//------------------------------------------------------------------------------------------------------------------
		
	public static void main(String[] args) { 
		
		String accepts;
		
		AlphaString string = new AlphaString(biAlphabet,  new ArrayList<Character>(Arrays.asList(biAlphabet.get(0), biAlphabet.get(0), biAlphabet.get(0), biAlphabet.get(1))));	//0111
		
		Trace trace = new Trace(new ArrayList<State>(Arrays.asList(oddNFAStates.get(0), oddNFAStates.get(0), oddNFAStates.get(0), oddNFAStates.get(0), oddNFAStates.get(1))));	
		
		System.out.println(string.displayable());
		System.out.println(trace.displayable());
		
		NFA union = union(stNFA, oddNFA);
		
		accepts = (stringTest(string, union, union.getStartState(), 0))? "String Accepted":"String Rejected";					//Tests string through an NFA. Breaks the transition functions. Was working before. Might reimplement previous method 
		//accepts = (stringTest(string, oddNFA, oddNFA.getStartState(), 0))? "String Accepted":"String Rejected";	
		
		System.out.println("\n" + accepts);											
		
		accepts = (traceTest(oddNFA, string, trace, true))? "Trace Accepted":"Trace Rejected";	//Tests trace given an NFA, string, and 
		
		System.out.println("\n" + accepts);
		
	}
	
	public static ArrayList<State> newList(State...states){
		
		ArrayList<State> newList = new ArrayList<State>();
		
		for(State state : states) {
			newList.add(state);
			
		}
		
		return newList;
		
	}
	
	public static NFA dfaToNfa(DFA dfa) {
		ArrayList<ArrayList<State>> nfaNextStates = new ArrayList<ArrayList<State>>();
		State nfaStartState = dfa.getStartState();
		
		for(State state : dfa.getNextStates()) {
			nfaNextStates.add(new ArrayList<State>(Arrays.asList(state)));
			
		}
		
		for(int i = 0; i < dfa.getStates().size(); i++) {
			nfaNextStates.add( i*dfa.getAlphabet().size() + i, new ArrayList<State>( Arrays.asList( dfa.getStates().get(i) ) ) );	//Delta += ([A State], epsilon, [That State])
			
		}
		
		NFA nfa = new NFA(dfa.getStates(), dfa.getAlphabet(), nfaStartState, nfaNextStates, dfa.getAcceptingStates(), epsilon);
		
		return nfa;
		
	}
	
	public static boolean traceTest(NFA nfa, AlphaString string, Trace trace, Boolean result) {
		
		if(trace.size() != string.length()+1) {														//If trace.size() is not equal to the number of characters + 1
			return false;
			
		}
		
		if(trace.get(0) != nfa.getStartState()){													//If trace[0] is equal to q0
			return false;
			
		}
		
		for(int i = string.length(); i > 0; i--){
			if(!nfa.findNextStates(trace.get(i-1), string.getChar(i-1)).contains(trace.get(i)) && !nfa.findNextStates(trace.get(i-1), epsilon).contains(trace.get(i))){   //If stateTable[trace[i-1][string[i-1] or epsilon].contains(trace[i]) == false 
				return false;
				
			}
			
		}
		
	
		if(nfa.getAcceptingStates().contains(trace.get(string.length())) == result ) {				//If acceptingStates.contains(trace[string.length]) == result
			return true;
			
		}
																									//Otherwise
		return false;
		
	}
	
	public static boolean stringTest(AlphaString string, NFA nfa, State curState, int index) {		
		boolean accept = false;
		
		ArrayList<State> branch = new ArrayList<State>();												
		if(string.length() != 0){																		//If testing the empty string, check if startState is an accepting state			
			branch = nfa.findNextStates(curState, string.getChar(index));								//Get (curState, string[index])
			
			for(State epState : nfa.findNextStates(curState, epsilon)) {
				branch.addAll(nfa.findNextStates(epState, string.getChar(index)));
				
			}																		
			
			for(State branchling : branch) {															//Search through branching states
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
	
}
