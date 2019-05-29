import java.util.ArrayList;

public class DFA {

	private ArrayList<State> states;
	private Alphabet alphabet; 
	private State startState; 
	private StateTable stateTable;
	private ArrayList<State> acceptingStates;
	
	private State currentState;
	
	private Trace trace;
	
	public DFA(){
		
	}
	
	public DFA(ArrayList<State> states, Alphabet alphabet, State startState, ArrayList<State> nextStates, ArrayList<State> acceptingStates){
		this.states = states;
		this.alphabet = alphabet;
		this.startState = startState;
		this.stateTable = new StateTable(states, alphabet, nextStates);
		this.acceptingStates = acceptingStates;
		
		this.currentState = startState;
		
		this.trace = new Trace();
		
	}
	
	private void findNextState(Character character){
		this.currentState = this.stateTable.findNextState(currentState, character);
		
	}
	
	public boolean acceptReject(){
		boolean accepts = false;
		
		if(acceptingStates.contains(currentState)){
			accepts = true;
			
		}
			
		
		return accepts;
		
	}
	
	public boolean run(AlphaString string){
		resetDFA();
		
		this.trace.addState(returnCurrentState());
		
		for(int i = 0; i <  string.length(); i++){
			findNextState(string.getChar(i));
			
			this.trace.addState(returnCurrentState());
			
		}
		
		return acceptReject();
		
	}
	
	public State returnCurrentState(){
		return currentState;
		
	}
	
	public void resetDFA(){
		this.currentState = this.startState;
		
	}
	
	public Trace getTrace(){
		return this.trace;
		
	}
	
}
