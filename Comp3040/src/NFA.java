import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.text.html.HTMLDocument.Iterator;

public class NFA {
	
	private Character epsilon;
	
	private ArrayList<State> states; 						//Q [Also serves as the y-axis of the state map]
	private Alphabet alphabet; 								//Sigma	[Also serves as the x-axis of the state map]
	private State startState; 								//q0
	private ArrayList<ArrayList<State>> nextStates;			//What fills the cells in the state map[The "--> Q" in Delta]
	private StateTable stateTable;							//Delta : Q X Sigma or Epsilon --> Q 
	private ArrayList<State> acceptingStates;				//F
	
	private ArrayList<State> currentStates = new ArrayList<State>();
	
	public NFA(){
		
	}
	
	public NFA(ArrayList<State> states, Alphabet alphabet, State startState, ArrayList<ArrayList<State>> nextStates, ArrayList<State> acceptingStates, Character epsilon){
		this.acceptingStates = states;
		this.alphabet = alphabet;
		this.startState = startState;
		this.nextStates = nextStates;
		this.stateTable = new StateTable(states, alphabet, nextStates, epsilon);
		this.acceptingStates = acceptingStates;
		
		this.epsilon = epsilon;
		
		this.currentStates.add(startState);
		
	}
	
	private ArrayList<State> findNextStates(Character character, int index){
		return this.stateTable.nfaFindNextState(currentStates.get(index), character);
		
	}
	
	public void resetNFA(){
		this.currentStates = new ArrayList<State>(Arrays.asList(this.startState));
		
	}
	
	private boolean acceptReject() {
		
		for(State state : currentStates){
			if(acceptingStates.contains(state)){
				return true;
				
			}
			
		}
		
		return false;
		
	}
	
	public boolean run(AlphaString string){
		resetNFA();
		
		ArrayList<ArrayList<State>> newStates = new ArrayList<ArrayList<State>>();
		
		for(int i = 0; i <  string.length(); i++){
			
				newStates.clear();
				
				for(State state : this.currentStates) {
					newStates.add(this.stateTable.nfaFindNextState(state, string.getChar(i)));
					
				}
				
				this.currentStates.clear();
				
				for(ArrayList<State> states : newStates) {
					for(State state : states) {
						this.currentStates.add(state);
						
					}
					
				}
			
		}
		
		return acceptReject();
		
	}

}
