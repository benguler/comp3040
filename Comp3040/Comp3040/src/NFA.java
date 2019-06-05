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
	private StateTable stateTable;							//Delta : Q X Sigma or Epsilon --> Q 
	private ArrayList<State> acceptingStates;				//F
	
	private ArrayList<State> currentStates;
	
	private TraceTree traceTree;
	
	Trace trace;
	
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
		
		this.currentStates = new ArrayList<State>(Arrays.asList(startState));
		
		ArrayList<State> epStart = new ArrayList<State>();
		
		this.traceTree = new TraceTree();
		
		for(State curState : this.currentStates) {
			for(State epState : this.stateTable.nfaFindNextState(curState, epsilon)){
				epStart.add(epState);
				
			}
			
		}
		
		for(State epState : epStart) {
			this.currentStates.add(epState);
			
		}
		
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
		
	}
	
	private boolean acceptReject() {
		
		for(State state : currentStates){
			if(this.acceptingStates.contains(state)){
				return true;
				
			}
			
		}
		
		return false;
		
	}
	
	public boolean run(AlphaString string){
		resetNFA();
		
		ArrayList<ArrayList<State>> newStates = new ArrayList<ArrayList<State>>();
		
		ArrayList<State> branch = new ArrayList<State>();		
		ArrayList<State> epsilonBranch = new ArrayList<State>();
		ArrayList<State> temp = new ArrayList<State>();			
		
		for(int i = 0; i <  string.length(); i++){
			newStates.clear();
			
			branch.clear();
			epsilonBranch.clear();
			temp.clear();
			
			trace = new Trace(currentStates);
			System.out.print(trace.displayable() + "- " + string.getChar(i).displayable() + " - ");
			
			for(int j = 0; j < currentStates.size(); j++) {
				branch = this.stateTable.nfaFindNextState(currentStates.get(j), string.getChar(i));		//Eventally begins returning an empty set regardless of the given character or state
				
				for (State branchling : branch){
					epsilonBranch = this.stateTable.nfaFindNextState(branchling, this.epsilon);			//Find any states resulting from the epsilon transitions, if the exist
					temp.addAll(epsilonBranch);	
					
				}
				
				branch.addAll(temp);																	//Append those states to the branch
				
				newStates.add(branch);
				
				for (State branchling : branch){
					TTNode node = new TTNode( traceTree.getNode(traceTree.getDepth()-1, j), branchling, string.getChar(i));
					this.traceTree.addNode(node);																			//Add node to trace tree containing new state
					//this.traceTree.getNode(traceTree.getDepth()-1, j).addChildNode(node); 								//Add node to set of child nodes for the corresponding state. Parent node gets null pointer exception, but worked previously
				}
				
			}
			
			this.traceTree.incrimentDepth();
			
			this.currentStates.clear();
			
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
