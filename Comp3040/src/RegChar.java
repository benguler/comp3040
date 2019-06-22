import java.util.ArrayList;

public class RegChar extends NFAFunctions implements RegEx {
	
	private Character c;
	private Alphabet alphabet;
	
	public RegChar(Character c, Alphabet alphabet) {
		this.c = c;
		this.alphabet = alphabet;
	}

	@Override
	public NFA compile() {
		ArrayList<State> states = new ArrayList<State>(newList(new State("char0"), new State("char11"), new State("char2")));
		ArrayList<ArrayList<State>> next = new ArrayList<ArrayList<State>>();
		
		State start = states.get(0);
		
		for(int i = 0; i < states.size(); i++) {
			for(int j = 0; j < this.alphabet.size(); j++) {
				next.add(newList(states.get(1)));
			}
			
			next.add(newList());
			
		}
		
		next.set(this.alphabet.findIndex(c), newList(states.get(2)));
		
		ArrayList<State> accepting = new ArrayList<State>(newList(states.get(2)));
		
		return new NFA(states, this.alphabet, start, next, accepting, epsilon);
		
	}

	@Override
	public AlphaString generate() {
		return new AlphaString(this.alphabet, this.alphabet.get(this.alphabet.findIndex(c)));
		
	}

	@Override
	public boolean accepts() {
		DFA dfa = nfaToDFA(this.compile());
		return (dfa.run(this.generate()));
		
	}
	
	public Alphabet getAlphabet() {
		return this.alphabet;
		
	}
	
}
