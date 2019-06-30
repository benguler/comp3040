import java.util.ArrayList;

public class CFG {
	
	private ArrayList<NonTerminal> nonTerminals;
	private ArrayList<Terminal> terminals;
	private ArrayList<Rule> rules;
	private NonTerminal startSymbol;
	
	public CFG(ArrayList<NonTerminal> nonTerminals, ArrayList<Terminal> terminals, ArrayList<Rule> rules, NonTerminal startSymbol, Character epsilon){
		this.nonTerminals = nonTerminals;
		this.terminals = terminals;
		this.rules = rules;
		this.startSymbol = startSymbol;
		
	}
	
}
