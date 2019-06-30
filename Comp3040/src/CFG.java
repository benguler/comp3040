import java.util.ArrayList;

public class CFG {
	
	private NFAFunctions func = new NFAFunctions();
	
	private ArrayList<NonTerminal> nonTerminals;
	private ArrayList<Terminal> terminals;
	private ArrayList<Rule> rules;
	private NonTerminal startSymbol;
	
	private ParseTree parseTree;
	
	public CFG(ArrayList<NonTerminal> nonTerminals, ArrayList<Terminal> terminals, ArrayList<Rule> rules, NonTerminal startSymbol, Character epsilon){
		this.nonTerminals = nonTerminals;
		this.terminals = terminals;
		this.rules = rules;
		this.startSymbol = startSymbol;
		
		parseTree = new ParseTree();
		
		ArrayList<RegEx> regs;
		RegEx ruleUnion;
		
		for(NonTerminal nonTerminal : this.nonTerminals) {
			
			regs = new ArrayList<RegEx>();
			
			for(Rule rule : this.rules) {
				if(rule.getNonTerminal() == nonTerminal) {
					regs.add(rule.getReg());
					
				}
	
			}
			
			ruleUnion = new RegEmpty(nonTerminal.getAlphabet());
			
			for(RegEx reg : regs) {
				ruleUnion = new RegUnion(ruleUnion, reg);
				
			}
			
			nonTerminal.setReg(ruleUnion);
			
		}
		
	}
	
	public boolean run(AlphaString string) {
		parseTree = new ParseTree();
		
		return false;
		
	}
	
	//Generate a random string produced by the CFG
	public AlphaString generate() {
		return this.startSymbol.getReg().generate();
		
	}
	
	public ParseTree getPareTree() {
		return this.parseTree;
		
	}
	
}
