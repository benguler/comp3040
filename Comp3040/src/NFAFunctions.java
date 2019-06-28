//Would work better as a library, but this is quick

import java.util.ArrayList;
import java.util.Arrays;

public class NFAFunctions {
	
	public NFAFunctions() {
		
	}
	
	public static final Character epsilon = new Character(""); 
	
	public ArrayList<State> newList(State...states){
		
		ArrayList<State> newList = new ArrayList<State>();
		
		for(State state : states) {
			newList.add(state);
			
		}
		
		return newList;
		
	}
	
	public ArrayList<RegEx> newList(boolean foo, RegEx...regs){
		
		ArrayList<RegEx> newList = new ArrayList<RegEx>();
		
		for(RegEx reg : regs) {
			newList.add(reg);
			
		}
		
		return newList;
		
	}
	
	public NFA dfaToNFA(DFA dfa) {
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
		
		nfaNextStates.add(newList(dfa.getStartState()));					//([new start state], [epsilon, [old start state]])
		
		for(State state : dfa.getStates()) {

			for(Character c : dfa.getAlphabet().getList()) {
				nfaNextStates.add(newList(dfa.findNextState(state, c))); 
			}
			
			if(dfa.getAcceptingStates().contains(state)) {
				nfaNextStates.add(nfaAcceptingStates);						//([old accepting state], [epsilon], [new accepting state]
				
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
	
	//Given an NFA, string, trace, and result, return whether when the string is run the the DFA, the Trace existes within the resulting tree and ends in the result
	public boolean traceTest(NFA nfa, AlphaString string, Trace trace, Boolean result) {
		
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
	public boolean stringTest(AlphaString string, NFA nfa, State curState, int index) {		
		boolean accept = false;
		
		ArrayList<State> branch = new ArrayList<State>();	
		ArrayList<State> temp = new ArrayList<State>();	
		
		if(string.length() != 0){																		//If testing the empty string, just check if startState is an accepting state	
			temp.clear();
			branch = nfa.findNextStates(curState, string.getChar(index));								//Get (curState, string[index])
			
			if(curState == nfa.getStartState()) {
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
	public NFA union(NFA nfa1, NFA nfa2) {
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
	public NFA concatenation(NFA nfa1, NFA nfa2) {
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
	public NFA kleene(NFA nfa) {
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
	public DFA nfaToDFA(NFA nfa) {
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
				
				ArrayList<State> temp;
				
				for(State state : states.get(index)){
					temp =  snfa.findNextStates(state, c);
					for(State tran : temp) {
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
	
	public boolean equal(NFA nfa1, NFA nfa2) {
		DFA dfa1 = nfaToDFA(nfa1);
		DFA dfa2 = nfaToDFA(nfa2);
		
		return equal(dfa1, dfa2);
		
	}
	
public static AlphaString acceptingString(DFA dfa){
		
		if(dfa.getAcceptingStates().size() < 1){	//If there are no accepting states
			return null;							//Return null
		}
		
		ArrayList<State> states = dfa.getStates(); 
		State startState = dfa.getStartState();
		
		StateTable stateTable = dfa.getStateTable();
		
		Alphabet alphabet = dfa.getAlphabet();
		
		AlphaString acceptingString = new AlphaString(alphabet);
		
		boolean newState;
		boolean nextAccept = false;
		
		State curState;
		State orig;
		
		for(State acc : dfa.getAcceptingStates()) {
			
			curState = acc;
			orig = acc;
			
			while(curState != startState ){				//Until we arrive at the starting state
				newState = false;
				
				for(int i = 0; i <= states.size(); i++){
					if(newState){						//Current state has been updated
						break;							//Stop searching
						
					}
					
					if(i == states.size()) {			//No states point to curState through c
						if(curState == dfa.getAcceptingStates().get(dfa.getAcceptingStates().size()-1)) {
							return null;
							
						}
						
						acceptingString = new AlphaString(alphabet);
						nextAccept = true;
						break;
						
					}
					
					for(int j = 0; j < alphabet.size(); j++){
						if(stateTable.get(i, j) == curState && states.get(i) != curState){	//StateTable[Previous State][Character] == Current State
							acceptingString.pushChar(alphabet.get(j));						//Push Character into the String to be returned
							curState = states.get(i);										//Current State = Previous State
							newState = true;
							
							if(orig == curState) {											//Loop protection
								nextAccept = true;
								
							}
							
							break;															//Stop searching
							
						}
						
					}	
					
				}
				
				if(nextAccept) {
					break;
					
				}
				
			}
			
			if(dfa.run(acceptingString) && !nextAccept) {
				return acceptingString;
				
			}
			
			nextAccept = false;
			
		}
		
		return null;
		
	}
	
	
	
	//~(dfa1)
	public DFA complement(DFA dfa){
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
	public DFA union(DFA dfa1, DFA dfa2){
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
	public DFA intersection(DFA dfa1, DFA dfa2){
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
	public boolean subset(DFA dfa1, DFA dfa2){
		DFA dfa2Compliment = complement(dfa2);				   		//~(dfa2)
		DFA dfaIntersection = intersection(dfa1, dfa2Compliment);	//dfa1 n ~(dfa2)
		
		return (acceptingString(dfaIntersection) == null);				   
		
	}
	
	//(dfa1 == dfa2)?
	public boolean equal(DFA dfa1, DFA dfa2) {
		
		return(subset(dfa1, dfa2) && subset(dfa2, dfa1)); 			//(dfa1 c dfa2) and (dfa2 c dfa1) -> dfa1 == dfa2
		
	}
	
	public void initializeStates(Alphabet alphabet, ArrayList<State> states, ArrayList<State> nextStates, State state){
		for(int i = 0; i < alphabet.size() * states.size(); i++){
			nextStates.add(state);
			
		}
		
	}
	
}
