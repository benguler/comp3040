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
																									 newList( oddNFAStates.get(0) ), newList( oddNFAStates.get(0), oddNFAStates.get(1) ), newList(),	//Delta = {(odd0, '0', {odd0}), (odd0, '1', {odd0, B}), (odd0, epsilon, {})
																									 newList(), newList(), newList())																//        {(odd1, '0', {}), (odd1, '1', {}), (odd1, epsilon, {})
																									);	
																									
		
		private static ArrayList<State>oddNFAAcceptingStates = new ArrayList<State>(Arrays.asList(oddNFAStates.get(1)));						//F = {odd1}
		
		private static NFA oddNFA = new NFA(oddNFAStates, biAlphabet,oddNFAStartState,oddNFANextStates,oddNFAAcceptingStates, epsilon);
	//----------------------------------------------------------------------------------------------------------------------
		
	//NFA that accepts even binary #s (The "evenDFA")-----------------------------------------------------------------------
		private static ArrayList<State> evenNFAStates = new ArrayList<State>(Arrays.asList(new State("even0"), new State("even1")));						//Q = {evenO, even1}
		
		private static State evenNFAStartState = evenNFAStates.get(0);																				//q0 = evenO
		
		private static ArrayList<ArrayList<State>>evenNFANextStates = new ArrayList<ArrayList<State>>(Arrays.asList(
																									 newList( evenNFAStates.get(0), evenNFAStates.get(1)), newList( evenNFAStates.get(0)), newList(),	//Delta = {(even0, '0', {even0, even1}), (even0, '1', {even0}), (even0, epsilon, {})
																									 newList(), newList(), newList())																	//        {(even1, '0', {}),     (even1, '1', {}),  (even1, epsilon, {})
																									);	
																									
		
		private static ArrayList<State>evenNFAAcceptingStates = new ArrayList<State>(Arrays.asList(evenNFAStates.get(1)));						//F = {even1}
		
		private static NFA evenNFA = new NFA(evenNFAStates, biAlphabet,evenNFAStartState,evenNFANextStates,evenNFAAcceptingStates, epsilon);
	//----------------------------------------------------------------------------------------------------------------------

	//NFA that accepts a binary number with a 1 at the 2nd or 3rd position from the end in the string (The "stDFA")-------					//I.E. 1000 or 0100, but not 0010 or 0001
		private static ArrayList<State> stNFAStates = new ArrayList<State>(Arrays.asList(new State("st0"), new State("st1"), new State("st2"), new State("st3")));						//Q = {st0, st1, st2, st3}
		
		private static State stNFAStartState = stNFAStates.get(0);																				//q0 = st0
		
		private static ArrayList<ArrayList<State>>stNFANextStates = new ArrayList<ArrayList<State>>(Arrays.asList(
				 																					 newList(stNFAStates.get(0)), newList(stNFAStates.get(0), stNFAStates.get(1)), newList(),	//Delta = {(st0, '0', {st0}),(st0, 1, {st0, st1}).(st0, epsilon, {})
				 																					 newList(stNFAStates.get(2)), newList(stNFAStates.get(2)), newList(stNFAStates.get(2)),		//		   (st1, '0', {st2}),(st1, '1', {st2}), (st1, epsilon, {st2})
																									 newList(stNFAStates.get(3)), newList(stNFAStates.get(3)), newList(),						//		   (st2, '0', {D}),(st2, '1', {st3}), (st2, epsilon, {})
																									 newList(), newList(), newList())															//		   (st3, '0', {}), (st3, '1', {}), (st3, epsilon, {})				
																									);	
																									
		
		private static ArrayList<State>stNFAAcceptingStates = new ArrayList<State>(Arrays.asList(stNFAStates.get(3)));						//F = {st3}
		
		private static NFA stNFA = new NFA(stNFAStates, biAlphabet, stNFAStartState,stNFANextStates,stNFAAcceptingStates, epsilon);
	//------------------------------------------------------------------------------------------------------------------
		
	public static void main(String[] args) {  	
		String accepts;
		
		AlphaString string = new AlphaString(biAlphabet,  new ArrayList<Character>(Arrays.asList(biAlphabet.get(1), biAlphabet.get(0), biAlphabet.get(1), biAlphabet.get(1))));	
		
	
		accepts = (stNFA.run(string))? "String Accepted":"String Rejected";	
		System.out.println("\n" + accepts);
		
		accepts = (nfaToDFA(stNFA).run(string))? "String Accepted":"String Rejected";	
		System.out.println("\n" + accepts);
		
		accepts = (stringTest(string, stNFA, stNFA.getStartState(), 0))? "String Accepted":"String Rejected";	
		System.out.println("\n" + accepts);
		
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
	
	//Given an NFA, string, trace, and result, return whether when the string is run the the DFA, the Trace existes within the resulting tree and ends in the result
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
			temp.addAll(branch);
			
			if(curState == nfa.getStartState()) {
				for(State epState : nfa.findNextStates(curState, epsilon)) {
					branch.addAll(nfa.findNextStates(epState, string.getChar(index)));
					
				}
			}
			
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
