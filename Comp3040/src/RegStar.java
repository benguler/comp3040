import java.util.ArrayList;
import java.util.Random; 

public class RegStar extends NFAFunctions implements RegEx {
	
	private RegEx reg;
	
	private Random rand = new Random(); 
	
	public RegStar(RegEx reg) {
		this.reg = reg;
	}

	@Override
	public NFA compile() {
		return kleene(reg.compile());
		
	}

	@Override
	public AlphaString generate() {
		
		Alphabet alphabet = reg.getAlphabet();
		
		ArrayList<Character> chars = this.reg.generate().getChars();
		
		AlphaString string = new AlphaString(alphabet);
		
		int randInt = rand.nextInt(21);		
		
		for(int i = 0; i < randInt; i++) {								//[epsilon] - [string]^20 
			for(Character c : chars) {
				string.addChar(c);
				
			}
			
		}
		
		return string;
		
	}

	@Override
	public boolean accepts() {
		DFA dfa = nfaToDFA(this.compile());
		return (dfa.run(this.generate()));
		
	}
	
	public Alphabet getAlphabet() {
		return this.reg.getAlphabet();
		
	}
	
}
