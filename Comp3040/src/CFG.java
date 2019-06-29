import java.util.ArrayList;

public class CFG {
	
	ArrayList<NonTerminal> nonTerminals;
	ArrayList<Terminal> terminals;
	ArrayList<Rule> rules;
	NonTerminal startSymbol;
	
	public CFG(ArrayList<NonTerminal> nonTerminals, ArrayList<Terminal> terminals, ArrayList<Rule> rules, NonTerminal startSymbol){
		this.nonTerminals = nonTerminals;
		this.terminals = terminals;
		this.rules = rules;
		this.startSymbol = startSymbol;
		
	}
	
}
