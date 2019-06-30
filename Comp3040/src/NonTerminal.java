
public class NonTerminal implements Symbol{
	
	private String identifier;
	private Alphabet alphabet;
	private RegRecur reg;
	
	public NonTerminal(String identifier, Alphabet alphabet) {
		this.identifier = identifier;
		this.alphabet = alphabet;
		this.reg = new RegRecur(this);
		
	}

	public RegEx getReg() {
		return this.reg;
		
	}
	
	public Alphabet getAlphabet() {
		return this.alphabet;
		
	}

	public String getIdentiier() {
		return this.identifier;
		
	}
	
	public void setReg(RegEx reg) {
		this.reg.setReg(reg);
		
	}
	
}
