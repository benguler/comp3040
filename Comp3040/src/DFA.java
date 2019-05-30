import java.util.ArrayList;

public class DFA {

	private ArrayList<State> states;
	private Alphabet alphabet; 
	private State startState; 
	private ArrayList<State> nextStates;
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
		this.nextStates = nextStates;
		this.stateTable = new StateTable(states, alphabet, nextStates);
		this.acceptingStates = acceptingStates;
		
		this.currentState = startState;
		
		this.trace = new Trace();
		
	}
	
	private State findNextState(Character character){
		return this.stateTable.findNextState(currentState, character);
		
	}
	
	public boolean acceptReject(){
		boolean accepts = false;
		
		if(acceptingStates.contains(currentState)){
			accepts = true;
			
		}
			
		
		return accepts;
		
	}
	
	public boolean run(AlphaString string){
		this.trace.clear();
		
		resetDFA();
		
		this.trace.addState(returnCurrentState());
		
		for(int i = 0; i <  string.length(); i++){
			currentState = findNextState(string.getChar(i));
			
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
	
	public ArrayList<State> getStates(){
		return this.states;
		
	}
	
	public Alphabet getAlphabet(){
		return this.alphabet;
		
	}
	
	public State getStartState(){
		return this.startState;
		
	}
	
	public  ArrayList<State> getNextStates(){
		return this.nextStates;
		
	}
	
	public  ArrayList<State> getAcceptingStates(){
		return this.acceptingStates;
		
	}
	
	public StateTable getStateTable(){
		return this.stateTable;
		
	}
	
	public Trace getTrace(){
		return this.trace;
		
	}
	
}
