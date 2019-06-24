import java.util.ArrayList;

public class RegChar implements RegEx {
	
	private Character c;
	private Alphabet alphabet;
	
	public RegChar(Character c, Alphabet alphabet) {
		this.c = c;
		this.alphabet = alphabet;
	}

	@Override
	public NFA compile() {
		ArrayList<State> states = new ArrayList<State>(func.newList(new State("char0"), new State("char1"), new State("char2")));
		ArrayList<ArrayList<State>> next = new ArrayList<ArrayList<State>>();
		
		State start = states.get(0);
		
		for(int i = 0; i < states.size(); i++) {
			for(int j = 0; j < this.alphabet.size(); j++) {
				next.add(func.newList(states.get(1)));
			}
			
			next.add(func.newList());
			
		}
		
		next.set(this.alphabet.findIndex(c), func.newList(states.get(2)));
		
		ArrayList<State> accepting = new ArrayList<State>(func.newList(states.get(2)));
		
		return new NFA(states, this.alphabet, start, next, accepting, func.epsilon);
		
	}

	@Override
	public AlphaString generate() {
		return new AlphaString(this.alphabet, this.alphabet.get(this.alphabet.findIndex(c)));
		
	}

	@Override
	public boolean accepts() {
		DFA dfa = func.nfaToDFA(this.compile());
		return (dfa.run(this.generate()));
		
	}
	
	public Alphabet getAlphabet() {
		return this.alphabet;
		
	}
	
	public String displayable() {
		return ("'"+c.displayable()+"'") ;
		
	}

	@Override
	public boolean isUnion() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isConcat() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isStar() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
