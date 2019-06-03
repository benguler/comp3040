import java.util.ArrayList;
import java.util.Arrays;

public class NFATest {
	
	//Initialize Alphabet(s)-----------------------------------------------------------------------------------------------
			private static final Character epsilon = new Character("ϵ");
	
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
			private static ArrayList<State> oddNFAStates = new ArrayList<State>(Arrays.asList(new State("A"), new State("B")));						//Q = {A, B}
			
			private static State oddNFAStartState =oddNFAStates.get(0);																				//q0 = A
			
			private static ArrayList<ArrayList<State>>oddNFANextStates = new ArrayList<ArrayList<State>>(Arrays.asList(
																										 newList( oddNFAStates.get(0) ), newList( oddNFAStates.get(0), oddNFAStates.get(1)), newList(),	//Delta = {(A, '0', {A}), (A, '1', {A, B}), (A, epsilon, {})
																										 newList(), newList(), newList())																//        {(B, '0', {}), (B, '1', {}), (B, epsilon, {})
																										);	
																										
			
			private static ArrayList<State>oddNFAAcceptingStates = new ArrayList<State>(Arrays.asList(oddNFAStates.get(1)));						//F = {B}
			
			private static NFA oddNFA = new NFA(oddNFAStates, biAlphabet,oddNFAStartState,oddNFANextStates,oddNFAAcceptingStates, epsilon);
		//----------------------------------------------------------------------------------------------------------------------
			
		//NFA that accepts even binary #s (The "evenDFA")-------------------------------------------------------------------------
			private static ArrayList<State> evenNFAStates = new ArrayList<State>(Arrays.asList(new State("A"), new State("B")));						//Q = {A, B}
			
			private static State evenNFAStartState =evenNFAStates.get(0);																				//q0 = A
			
			private static ArrayList<ArrayList<State>>evenNFANextStates = new ArrayList<ArrayList<State>>(Arrays.asList(
																										 newList( evenNFAStates.get(0), evenNFAStates.get(1)), newList( evenNFAStates.get(0)), newList(evenNFAStates.get(0)),	//Delta = {(A, '0', {A, B}), (A, '1', {B}), (A, epsilon, {A})
																										 newList(), newList(), newList(evenNFAStates.get(0)))																	//        {(B, '0', {}),     (B, '1', {}),  (B, epsilon, {B})
																										);	
																										
			
			private static ArrayList<State>evenNFAAcceptingStates = new ArrayList<State>(Arrays.asList(evenNFAStates.get(1)));						//F = {B}
			
			private static NFA evenNFA = new NFA(evenNFAStates, biAlphabet,evenNFAStartState,evenNFANextStates,evenNFAAcceptingStates, epsilon);
		//----------------------------------------------------------------------------------------------------------------------
			
	public static void main(String[] args) { 
		
		AlphaString string = new AlphaString(biAlphabet,  new ArrayList<Character>(Arrays.asList()));
		
		System.out.println(string.displayable());
		
		String accepts = (oddNFA.run(string))? "String Accepted":"String Rejected";	//Run DFA and return whether the string was accepted or not
		
		System.out.println("\n" + accepts + "\n");
		
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
		
		for(State state : dfa.getNextStates()) {
			nfaNextStates.add(new ArrayList<State>(Arrays.asList(state)));
			
		}
		
		NFA nfa = new NFA(dfa.getStates(), dfa.getAlphabet(), dfa.getStartState(), nfaNextStates, dfa.getAcceptingStates(), epsilon);
		
		return nfa;
		
	}
	
	public static boolean traceAccepted(NFA nfa, AlphaString string, Trace trace) {
		return false;
		
	}
	
}
