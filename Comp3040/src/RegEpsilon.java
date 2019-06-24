import java.util.ArrayList;

public class RegEpsilon implements RegEx {

	private Alphabet alphabet;
	
	public RegEpsilon(Alphabet alphabet) {
		this.alphabet = alphabet;
	}

	@Override
	public NFA compile() {
		ArrayList<State> states = new ArrayList<State>(func.newList(new State("ep0"), new State("ep1")));
		ArrayList<ArrayList<State>> next = new ArrayList<ArrayList<State>>();
		
		State start = states.get(0);
		
		for(int i = 0; i < states.size(); i++) {
			for(int j = 0; j < this.alphabet.size(); j++) {
				next.add(func.newList(states.get(1)));
			}
			
			next.add(func.newList());
			
		}
		
		ArrayList<State> accepting = new ArrayList<State>(func.newList(states.get(0)));
		
		return new NFA(states, this.alphabet, start, next, accepting, func.epsilon);
		
	}

	@Override
	public AlphaString generate() {
		return new AlphaString(alphabet);
		
	}

	@Override
	public boolean accepts() {
		DFA dfa = func.nfaToDFA(this.compile());
		return (dfa.run(this.generate()));
		
	}
	
	public Alphabet getAlphabet() {
		return this.alphabet;
		
	}

	@Override
	public String displayable() {
		return "[epsilon]";
		
	}
	
}
