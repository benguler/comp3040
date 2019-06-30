import java.util.ArrayList;

public class Rule {
	
	private NonTerminal nt;
	private ArrayList<Symbol> symbols;
	private RegEx reg;
	
	public Rule(NonTerminal nt, Symbol... symbols) {
		this.nt = nt;
		this.symbols = new ArrayList<Symbol>();
		this.reg = new RegEpsilon(nt.getAlphabet());
		
		for(Symbol symbol : symbols) {
			this.symbols.add(symbol);
			this.reg = new RegConcat(reg, symbol.getReg());
			
		}
		
	}
	
	public NonTerminal getNonTerminal() {
		return this.nt;
		
	}
	
	public RegEx getReg() {
		return this.reg;
		
	}
	
}
