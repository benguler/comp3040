import java.util.ArrayList;
import java.util.Random; 

public class RegStar implements RegEx {
	
	private RegEx reg;
	
	private Random rand = new Random(); 
	
	public RegStar(RegEx reg) {
		this.reg = reg;
	}

	@Override
	public NFA compile() {
		return func.kleene(reg.compile());
		
	}

	@Override
	public AlphaString generate() {
		Alphabet alphabet = this.reg.getAlphabet();
		
		if(reg.isEmpty()) {
			if(Math.random() < 0.5) {
				return null;
				
			}else {
				return new AlphaString(alphabet);
				
			}
			
		}
		
		ArrayList<Character> chars = this.reg.generate().getChars();
		
		AlphaString string = new AlphaString(alphabet);
		
		int randInt = this.rand.nextInt(21);		
		
		for(int i = 0; i < randInt; i++) {								//[epsilon] - [string]^20 
			for(Character c : chars) {
				string.addChar(c);
				
			}
			
		}
		
		return string;
		
	}

	@Override
	public boolean accepts(AlphaString string) {
		DFA dfa = func.nfaToDFA(this.compile());
		return (dfa.run(string));
		
	}
	
	public Alphabet getAlphabet() {
		return this.reg.getAlphabet();
		
	}
	
	public String displayable() {
		return ("(" + reg.displayable() + ")*");
		
	}

	public RegEx getReg() {
		return reg;
	}

	public void setReg(RegEx reg) {
		this.reg = reg;
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
		return true;
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
		return false;
	}

	@Override
	public boolean isRecur() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
