import java.util.ArrayList;

public class RegEmpty extends NFAFunctions implements RegEx {
	
	private Alphabet alphabet;
	
	public RegEmpty(Alphabet alphabet) {
		this.alphabet = alphabet;
		
	}

	@Override
	public NFA compile() {
		ArrayList<State> states = new ArrayList<State>(newList(new State("empty0")));
		ArrayList<ArrayList<State>> next = new ArrayList<ArrayList<State>>();
		
		State start = states.get(0);

		for(int j = 0; j < this.alphabet.size(); j++) {
			next.add(newList(states.get(0)));
			
		}
		
		next.add(newList());
		
		ArrayList<State> accepting = new ArrayList<State>(newList(states.get(2)));
		
		return new NFA(states, this.alphabet, start, next, accepting, epsilon);
		
	}

	@Override
	public AlphaString generate() {
		return null;
		
	}

	@Override
	public boolean accepts() {
		DFA dfa = nfaToDFA(this.compile());
		return (dfa.run(this.generate()));
		
	}

	@Override
	public Alphabet getAlphabet() {
		return this.alphabet;
		
	}
	
}
