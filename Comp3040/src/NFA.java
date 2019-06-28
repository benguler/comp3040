import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.text.html.HTMLDocument.Iterator;

public class NFA {
	Trace testTrace;
	private Character epsilon;
	
	private ArrayList<State> states; 						//Q [Also serves as the y-axis of the state map]
	private Alphabet alphabet; 								//Sigma	[Also serves as the x-axis of the state map]
	private State startState; 								//q0
	private ArrayList<ArrayList<State>> nextStates;			//What fills the cells in the state map[The "--> Q" in Delta]
	private StateTable stateTable;							//Delta : Q X Sigma or Epsilon --> P(Q) 
	private ArrayList<State> acceptingStates;				//F
	
	private ArrayList<State> currentStates = new ArrayList<State>();
	
	private TraceTree traceTree;
	
	public NFA(){
		
	}
	
	public NFA(ArrayList<State> states, Alphabet alphabet, State startState, ArrayList<ArrayList<State>> nextStates, ArrayList<State> acceptingStates, Character epsilon){
		this.states = states;
		this.alphabet = alphabet;
		this.startState = startState;
		this.nextStates = nextStates;
		this.stateTable = new StateTable(states, alphabet, nextStates, epsilon);
		this.acceptingStates = acceptingStates;
		this.epsilon = epsilon;
		
		this.currentStates.add(startState);
		
		this.currentStates.addAll(this.stateTable.nfaFindNextState(startState, epsilon));
		
		this.traceTree = new TraceTree();
		
		for(State curState : this.currentStates) {
			this.traceTree.addNode(new TTNode(curState));
			
		}
		
		this.traceTree.incrimentDepth();
		
	}
	
	public ArrayList<State> findNextStates(State state, Character character){
		return this.stateTable.nfaFindNextState(state, character);
		
	}
	
	public void resetNFA(){
		this.currentStates = new ArrayList<State>(Arrays.asList(startState));
		
		this.currentStates.addAll(this.stateTable.nfaFindNextState(startState, epsilon));
		
		this.traceTree.reset();
		
		for(State curState : this.currentStates) {
			this.traceTree.addNode(new TTNode(curState));
			
		}
		
		this.traceTree.incrimentDepth();
		
	}
	
	private boolean acceptReject() {
		
		for(State state : currentStates){
			if(this.acceptingStates.contains(state)){
				return true;
				
			}
			
		}
		
		return false;
		
	}
	
	//See if NFA accepts string and generate trace tree resulting from that string
	public boolean run(AlphaString string){
		resetNFA();
		
		ArrayList<ArrayList<State>> newStates = new ArrayList<ArrayList<State>>();
		
		ArrayList<State> branch = new ArrayList<State>();	
		
		for(int i = 0; i <  string.length(); i++){
			newStates  = new ArrayList<ArrayList<State>>();
			
			branch = new ArrayList<State>();
			for(int j = 0; j < this.currentStates.size(); j++) {																		//Find next set of states that are transitioned to
				branch = this.stateTable.nfaFindNextState(this.currentStates.get(j), string.getChar(i));																			
				
				newStates.add(branch);
				
				for (State branchling : branch){
					TTNode node = new TTNode( this.traceTree.getNode(this.traceTree.getDepth()-1, j), branchling, string.getChar(i));
					this.traceTree.addNode(node);																						//Add node to trace tree containing new state
					this.traceTree.getNode(this.traceTree.getDepth()-1, j).addChildNode(node); 											//Add node to set of child nodes for the node containing the corresponding state. 
					
				}
				
			}
			
			this.traceTree.incrimentDepth();																							
			
			this.currentStates = new ArrayList<State>();
			
			for(ArrayList<State> states : newStates) {
				this.currentStates.addAll(states);
					
			}
			
		}
		
		return acceptReject();
		
	}
	
	public ArrayList<State> getCurrentStates() {
		return this.currentStates;
		
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
	
	public  ArrayList<ArrayList<State>> getNextStates(){
		return this.nextStates;
		
	}
	
	public  ArrayList<State> getAcceptingStates(){
		return this.acceptingStates;
		
	}
	
	public StateTable getStateTable(){
		return this.stateTable;
		
	}
	
	public TraceTree getTraceTree() {
		return this.traceTree;
		
	}
	
	

}
