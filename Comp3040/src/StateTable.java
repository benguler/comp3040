import java.util.ArrayList;

public class StateTable {
	private ArrayList<State> currentStates;
	private Alphabet alphabet;
	private ArrayList<State> nextStates;
	
	ArrayList<ArrayList<State>> stateTable;
	
	public StateTable(){
		
	}
	
	public StateTable(ArrayList<State> currentStates, Alphabet alphabet, ArrayList<State> nextStates){
		this.currentStates = currentStates; 
		this.alphabet = alphabet;
		this.nextStates = nextStates;
		
		this.stateTable = new ArrayList<ArrayList<State>>();
		
		for(int i = 0; i < currentStates.size(); i++){
			this.stateTable.add(new ArrayList<State>());
			
			for(int j = 0; j < alphabet.size(); j++){
				(this.stateTable.get(i)).add(this.nextStates.get(j + (i*alphabet.size())));
				
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
		return (stateTable.get(getStateIndex(state))).get(getCharIndex(character));
		
	}
	
}
