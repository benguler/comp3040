import java.util.ArrayList;

public class CFG {
	
	ArrayList<Symbol> symbols;
	ArrayList<Terminal> terminals;
	ArrayList<Rule> rules;
	Symbol startSymbol;
	
	public CFG(ArrayList<Symbol> symbols, ArrayList<Terminal> terminals, ArrayList<Rule> rules, Symbol startSymbol){
		this.symbols = symbols;
		this.terminals = terminals;
		this.rules = rules;
		this.startSymbol = startSymbol;
		
	}
	
}
