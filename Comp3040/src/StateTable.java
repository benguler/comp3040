import java.util.ArrayList;

public class StateTable {
	private ArrayList<State> currentStates;
	private Alphabet alphabet;
	private ArrayList<State> nextStates;		//For DFA
	ArrayList<ArrayList<State>> branchStates;	//For NFA
	
	ArrayList<ArrayList<State>> dfaStateTable;
	ArrayList<ArrayList<ArrayList<State>>> nfaStateTable;
	
	public StateTable(){
		
	}
	
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
	
	public StateTable(ArrayList<State> currentStates, Alphabet alphabet, ArrayList<ArrayList<State>> branchStates, Character epsilon){
		this.currentStates = currentStates; 
		this.alphabet = alphabet;
		this.branchStates = branchStates;
		
		this.nfaStateTable = new ArrayList<ArrayList<ArrayList<State>>>();
		
		for(int i = 0; i < currentStates.size(); i++){
			this.nfaStateTable.add(new ArrayList<ArrayList<State>>());
			
			for(int j = 0; j < alphabet.size(); j++){
				(this.nfaStateTable.get(i)).add(this.branchStates.get(j + (i*alphabet.size())));
				
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
		return (nfaStateTable.get(getStateIndex(state))).get(getCharIndex(character));
		
	}
	
	public State nfaGet(int i, int j, int k){
		return (nfaStateTable.get(i)).get(j).get(k);
		
	}
	
}
