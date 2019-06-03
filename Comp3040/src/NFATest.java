import java.util.ArrayList;
import java.util.Arrays;

public class NFATest {
	
	public static void main(String[] args) { 
		
	}
	
	public static NFA dfaToNfa(DFA dfa) {
		ArrayList<ArrayList<State>> nfaNextStates = new ArrayList<ArrayList<State>>();
		
		for(State state : dfa.getNextStates()) {
			nfaNextStates.add(new ArrayList<State>(Arrays.asList(state)));
			
		}
		
		NFA nfa = new NFA(dfa.getStates(), dfa.getAlphabet(), dfa.getStartState(), nfaNextStates, dfa.getAcceptingStates());
		
		return nfa;
		
	}
}
