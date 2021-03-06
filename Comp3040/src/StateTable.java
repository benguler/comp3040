import java.util.ArrayList;

public class StateTable {
	private ArrayList<State> currentStates;
	private Alphabet alphabet;
	private ArrayList<State> nextStates;				//For DFA
	private ArrayList<ArrayList<State>> branchStates;	//For NFA
	private ArrayList<RegEx> currentRegs;						
	
	private Character epsilon;
	
	private ArrayList<ArrayList<State>> dfaStateTable;
	private ArrayList<ArrayList<ArrayList<State>>> nfaStateTable;
	private ArrayList<ArrayList<RegEx>> gnfaRegTable;
	
	public StateTable(){
		
	}
	
	//Constructor for DFA's
	public StateTable(ArrayList<State> currentStates, Alphabet alphabet, ArrayList<State> nextStates){
		this.currentStates = currentStates; 
		this.alphabet = alphabet;
		this.nextStates = nextStates;
		
		this.dfaStateTable = new ArrayList<ArrayList<State>>();
		
		for(int i = 0; i < currentStates.size(); i++){
			this.dfaStateTable.add(new ArrayList<State>());
			
			for(int j = 0; j < alphabet.size(); j++){
				(this.dfaStateTable.get(i)).add(this.nextStates.get(j + (i*alphabet.size())));
				
			}
			
		}
		
	}
	
	//Constructor for NFA's
	public StateTable(ArrayList<State> currentStates, Alphabet alphabet, ArrayList<ArrayList<State>> branchStates, Character epsilon){
		this.currentStates = currentStates; 
		this.alphabet = alphabet;
		this.branchStates = branchStates;
		this.epsilon = epsilon;
		
		this.nfaStateTable = new ArrayList<ArrayList<ArrayList<State>>>();
		
		for(int i = 0; i < currentStates.size(); i++){
			this.nfaStateTable.add(new ArrayList<ArrayList<State>>());
			
			for(int j = 0; j < alphabet.size()+1; j++){												//+1 is for epsilon, essentially appended on the end of the alphabet
				(this.nfaStateTable.get(i)).add(this.branchStates.get(j + (i*(alphabet.size()+1))));
				
			}
			
		}
		
	}
	
	//Constructor for GNFA's. Not really a state table, but a RegEx table
	
	public StateTable(ArrayList<State> currentStates, ArrayList<RegEx> currentRegs, Alphabet alphabet, Character epsilon){
		this.currentStates = currentStates;
		this.currentRegs = currentRegs; 
		this.alphabet = alphabet;
		this.epsilon = epsilon;
		
		this.gnfaRegTable = new ArrayList<ArrayList<RegEx>>();
		
		for(int i = 0; i < currentStates.size(); i++){
			this.gnfaRegTable.add(new ArrayList<RegEx>());
			
			for(int j = 0; j < currentStates.size(); j++){
				(this.gnfaRegTable.get(i)).add(this.currentRegs.get(j + (i*currentStates.size())));
				
			}
			
		}
		
	}
	
	private int getStateIndex(State state){
		return currentStates.indexOf(state);
		
	}
	
	private int getCharIndex(Character character){
		return alphabet.findIndex(character);
		
	}
	
	public State findNextState(State state, Character character){
		return (dfaStateTable.get(getStateIndex(state))).get(getCharIndex(character));
		
	}
	
	public State get(int i, int j){
		return (dfaStateTable.get(i)).get(j);
		
	}
	
	public ArrayList<State> nfaFindNextState(State state, Character character){
		ArrayList<State> next  = new ArrayList<State>();;
		ArrayList<State> temp1 = new ArrayList<State>();
		ArrayList<State> temp2 = new ArrayList<State>();
		
		if(character == this.epsilon) {															//Find just the epsilon transition, and any resulting epsilon transitions resulting from that
			if(currentStates.contains(state)) {													//Workaround for bug where nfa comes to contain state of other nfa
				next = ( nfaStateTable.get( getStateIndex(state)) ).get( alphabet.size() );
				 
				temp1.addAll((nfaStateTable.get(getStateIndex(state))).get(alphabet.size()));
			
			}
			
			do{
				temp2 = new ArrayList<State>();
				
				for(State nState :  temp1) {													//Keep looking for epsilon transitions
					if(currentStates.contains(nState)) {										//Workaround for bug where nfa comes to contain state of other nfa
						temp2.addAll(( nfaStateTable.get( getStateIndex(nState)) ).get( alphabet.size() ));
						
					}
						
				}
				
				ArrayList<State> nextTemp = next;
				
				if(!nextTemp.containsAll(temp2)) {
					for(State temp : temp2) {
						if(!next.contains(temp)) {
							next.add(temp);
						}
						
					}
					
				}else {
					break;
					
				}
			
				temp1 = temp2;
				
			}while(!temp2.isEmpty());															//There are still states being transitioned to
			
			return next;
																						
		}else {																					//Find a transistion based on a character, any resulting epsilon transitions, and any resulting epsilon transitions
			
			if(currentStates.contains(state)) {													//Workaround for bug where nfa comes to contain state of other nfa
				next = ( nfaStateTable.get( getStateIndex(state)) ).get( getCharIndex(character) );
	
				temp1.addAll(( nfaStateTable.get( getStateIndex(state)) ).get( getCharIndex(character) ));
			
			}
			
			do{																					//Keep looking for epsilon transitions
				temp2 = new ArrayList<State>();
				
				for(State nState :  temp1) {
					if(currentStates.contains(nState)) {										
						temp2.addAll(( nfaStateTable.get( getStateIndex(nState)) ).get( alphabet.size() ));
					}
						
				}
				
				ArrayList<State> nextTemp = next;
				
				if(!nextTemp.containsAll(temp2)) {
					for(State temp : temp2) {
						if(!next.contains(temp)) {
							next.add(temp);
						}
						
					}
					
				}else {
					break;
					
				}
				
				temp1 = temp2;
				
			}while(!temp2.isEmpty());															//There are still states being transitioned to
			
			return next;
			
		}
		
	}
	
	public ArrayList<State> nfaGet(int i, int j){
		return (nfaStateTable.get(i)).get(j);
		
	}
	
	public RegEx findReg(State state1, State state2){
		return (gnfaRegTable.get(getStateIndex(state1))).get(getStateIndex(state2));
		
	}
	
	
}
