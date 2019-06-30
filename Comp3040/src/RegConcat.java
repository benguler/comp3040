import java.util.ArrayList;

public class RegConcat implements RegEx{

	private RegEx reg1;
	private RegEx reg2;
	
	public RegConcat(RegEx reg1, RegEx reg2) {
		this.reg1 = reg1;
		this.reg2 = reg2;
		
	}

	@Override
	public NFA compile() {
		return func.concatenation(this.reg1.compile(), this.reg2.compile());
		
	}

	@Override
	public AlphaString generate() {
		Alphabet alphabet = this.reg1.getAlphabet();
		
		if(!reg1.isRecur() && !reg2.isRecur()) {
			if(this.reg1.isEmpty() || this.reg2.isEmpty() ) {
				return null;
				
			}
		
		}else if(!reg1.isRecur() && reg2.isRecur()) {
			if(this.reg1.isEmpty()) {
				return null;
				
			}
			
			
		}else if(reg1.isRecur() && !reg2.isRecur()) {
			System.out.println(reg2.displayable());
			if(this.reg2.isEmpty()) {
				return null;
				
			}
			
		}
		
		ArrayList<Character> chars1 = this.reg1.generate().getChars();
		ArrayList<Character> chars2 = this.reg2.generate().getChars();
		
		AlphaString string = new AlphaString(alphabet);
		
		for(Character c : chars1) {
			string.addChar(c);
			
		}
		
		for(Character c : chars2) {
			string.addChar(c);
			
		}
		
		return string;
		
	}

	@Override
	public boolean accepts(AlphaString string) {
		DFA dfa = func.nfaToDFA(this.compile());
		return (dfa.run(string));
		
	}
	
	public Alphabet getAlphabet() {
		return this.reg1.getAlphabet();
		
	}
	
	public String displayable() {
		return ("(" + reg1.displayable() + " o " + reg2.displayable() + ")");
		
	}

	public RegEx getReg1() {
		return reg1;
	}

	public void setReg1(RegEx reg1) {
		this.reg1 = reg1;
	}

	public RegEx getReg2() {
		return reg2;
	}

	public void setReg2(RegEx reg2) {
		this.reg2 = reg2;
	}

	@Override
	public boolean isUnion() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isConcat() {
		// TODO Auto-generated method stub
		return true;
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
		return (this.reg1.isEmpty() || this.reg2.isEmpty());
	}

	@Override
	public boolean isRecur() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
