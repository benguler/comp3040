
public class NonTerminal implements Symbol{
	
	private String identifier;
	private Alphabet alphabet;
	private RegEx reg;
	
	public NonTerminal(String identifier, Alphabet alphabet) {
		this.identifier = identifier;
		this.alphabet = alphabet;
		this.reg = new RegEmpty(alphabet);
		
	}

	public RegEx getReg() {
		return this.reg;
		
	}
	
	public void setReg(RegEx reg) {
		this.reg = reg;
		
	}
	
}
