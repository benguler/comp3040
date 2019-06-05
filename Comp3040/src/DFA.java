import java.util.ArrayList;

public class DFA {

	private ArrayList<State> states; 			//Q [Also serves as the y-axis of the state map]
	private Alphabet alphabet; 					//Sigma	[Also serves as the x-axis of the state map]
	private State startState; 					//q0
	private ArrayList<State> nextStates;		//What fills the cells in the state map[The "--> Q" in Delta]
	private StateTable stateTable;				//Delta : Q X Sigma --> Q	
	private ArrayList<State> acceptingStates;	//F
	
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
	
	private boolean acceptReject(){
		boolean accepts = acceptingStates.contains(currentState);
		
		return accepts;
		
	}
	
	public boolean run(AlphaString string){
		this.trace.clear();
		
		resetDFA();
		
		this.trace.addState(getCurrentState());
		
		for(int i = 0; i <  string.length(); i++){
			currentState = findNextState(string.getChar(i));
			
			this.trace.addState(getCurrentState());
			
		}
		
		return acceptReject();
		
	}
	
	public State getCurrentState(){
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
