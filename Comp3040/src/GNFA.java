import java.util.ArrayList;
import java.util.Arrays;

public class GNFA {
	
	private ArrayList<State> states;
	private ArrayList<RegEx> regs;
	private Alphabet alphabet;
	private State startState; 
	private StateTable regTable;
	private State acceptingState;
	private Character epsilon;
	
	private ArrayList<State> currentStates;
	
	public GNFA(ArrayList<State> states, Alphabet alphabet, State startState, ArrayList<RegEx> regs, State acceptingState, Character epsilon){
		this.states = states;
		this.regs = regs;
		this.alphabet = alphabet;
		this.startState = startState;
		this.regTable = new StateTable(states, regs, alphabet, epsilon);
		this.acceptingState = acceptingState;
		this.epsilon = epsilon;
		
	}
	
	public RegEx findReg(State state1, State state2) {
		return regTable.findReg(state1, state2);
		
	}
	
	public boolean run(AlphaString string) {
		
		ArrayList<ArrayList<AlphaString>> groupings = genGroupings(string);
		
		ArrayList<AlphaString> tempGrouping;
		
		ArrayList<State> tempStates;
		
		for(ArrayList<AlphaString> grouping : groupings) {													//For all possible parses of the input string
			tempGrouping = new ArrayList<AlphaString>();
			
			tempGrouping.add(new AlphaString(this.alphabet));
			
			tempGrouping.addAll(grouping);
			
			tempGrouping.add(new AlphaString(this.alphabet));
			
			this.currentStates = new ArrayList<State>(Arrays.asList(this.startState));
			
			for(AlphaString s : tempGrouping) {																
				tempStates = new ArrayList<State>();
					for(State currentState : this.currentStates) {
						for(State state : this.states) {
						if(this.findReg(currentState, state).accepts(s)) {
							tempStates.add(state);	
							
						}
						
					}
						
				}
				
				this.currentStates = new ArrayList<State>();
				this.currentStates.addAll(tempStates);
				
			}
	
			if(this.currentStates.contains(this.acceptingState)) {
				return true;
				
			}
			
		}
		
		return false;
		
	}
	
	//Generate all possible ways to parse a string 
	public ArrayList<ArrayList<AlphaString>> genGroupings(AlphaString string){

		ArrayList<ArrayList<AlphaString>> groupings = new ArrayList<ArrayList<AlphaString>>();
		
		ArrayList<ArrayList<AlphaString>> prev = new ArrayList<ArrayList<AlphaString>>();
		
		ArrayList<AlphaString> grouping1 = new ArrayList<AlphaString>();
		ArrayList<AlphaString> grouping2 = new ArrayList<AlphaString>();
		
		ArrayList<Character> newChars;
		
		if(string.length() <= 1) {
			
			grouping1.add(string);
			groupings.add(grouping1);
			
			return groupings;
			
		}
		
		prev = genGroupings(string.subString(1, string.length()-1));
		
		for(ArrayList<AlphaString> part : prev) {
			grouping1 = new ArrayList<AlphaString>();
			
			for(int i = 0; i < part.size(); i++) {
				newChars = new ArrayList<Character>();
				newChars.addAll(part.get(i).getChars());
				
				grouping1.add(new AlphaString(string.getAlphabet(), newChars));
				
			}
			
			newChars = new ArrayList<Character>();
			newChars.add(string.getChar(0));
			newChars.addAll(part.get(0).getChars());	
			
			grouping1.set(0, new AlphaString(string.getAlphabet(), newChars));

			groupings.add(grouping1);
			
			grouping2 = new ArrayList<AlphaString>();
			
			grouping2.add(new AlphaString(string.getAlphabet(), string.getChar(0)));
			
			for(AlphaString s : part) {
				grouping2.add(new AlphaString(s.getAlphabet(), s.getChars()));
				
			}
			
			groupings.add(grouping2);
			
			
		}
		
		return groupings;
		
	}
	
}
