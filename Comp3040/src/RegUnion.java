public class RegUnion implements RegEx {
	
	private RegEx reg1;
	private RegEx reg2;
	
	public RegUnion(RegEx reg1, RegEx reg2) {
		this.reg1 = reg1;
		this.reg2 = reg2;
	}

	@Override
	public NFA compile() {
		return func.union(this.reg1.compile(), this.reg2.compile());
		
	}

	@Override
	public AlphaString generate() {
		if(Math.random() < 0.5) {
			return this.reg1.generate();
			
		}else {
			return this.reg2.generate();
			
		}
		
	}

	@Override
	public boolean accepts() {
		DFA dfa = func.nfaToDFA(this.compile());
		return (dfa.run(this.generate()));
		
	}
	
	public Alphabet getAlphabet() {
		return this.reg1.getAlphabet();
		
	}
	
	public String displayable() {
		return ("(" + reg1.displayable() + " u " + reg2.displayable() + ")");
		
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
		return true;
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
