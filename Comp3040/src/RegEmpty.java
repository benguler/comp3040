import java.util.ArrayList;

public class RegEmpty implements RegEx {
	
	private Alphabet alphabet;
	
	public RegEmpty(Alphabet alphabet) {
		this.alphabet = alphabet;
		
	}

	@Override
	public NFA compile() {
		ArrayList<State> states = new ArrayList<State>(func.newList(new State("empty0")));
		ArrayList<ArrayList<State>> next = new ArrayList<ArrayList<State>>();
		
		State start = states.get(0);

		for(int j = 0; j < this.alphabet.size(); j++) {
			next.add(func.newList(states.get(0)));
			
		}
		
		next.add(func.newList());
		
		ArrayList<State> accepting = new ArrayList<State>(func.newList());
		
		return new NFA(states, this.alphabet, start, next, accepting, func.epsilon);
		
	}

	@Override
	public AlphaString generate() {
		return null;
		
	}

	@Override
	public boolean accepts(AlphaString string) {
		return false;
		
	}

	@Override
	public Alphabet getAlphabet() {
		return this.alphabet;
		
	}

	@Override
	public String displayable() {
		return "[Theta]";
		
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

	@Override
	public boolean isChar() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEpsilon() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return true;
	}
	
}
