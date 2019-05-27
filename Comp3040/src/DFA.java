import java.util.ArrayList;

public class DFA {

	private ArrayList<State> states;
	private Alphabet alphabet; 
	private State startState; 
	private StateTable stateTable;
	private ArrayList<State> acceptingStates;
	
	private State currentState;
	
	public DFA(){
		
	}
	
	public DFA(ArrayList<State> states, Alphabet alphabet, State startState, ArrayList<State> nextStates, ArrayList<State> acceptingStates){
		this.states = states;
		this.alphabet = alphabet;
		this.startState = startState;
		this.stateTable = new StateTable(states, alphabet, nextStates);
		this.acceptingStates = acceptingStates;
		
		this.currentState = startState;
		
	}
	
	public void findNextState(Character character){
		this.currentState = this.stateTable.findNextState(currentState, character);
		
	}
	
	public Boolean acceptReject(){
		boolean accepts = false;
		
		for(int i = 0; i < this.acceptingStates.size(); i++){
			if(acceptingStates.contains(currentState)){
				accepts = true;
				
			}
			
		}
		
		return accepts;
		
		
	}
	
}
