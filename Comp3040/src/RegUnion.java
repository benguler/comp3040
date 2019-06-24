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
	
}
