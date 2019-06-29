import java.util.ArrayList;

public class ParseNode {
	private ParseNode parentNode;
	private Symbol symbol;
	private ArrayList<ParseNode> childNodes;
	
	public ParseNode() {
		
	}
	
	public ParseNode(Symbol symbol) {
		this.symbol = symbol;
		this.childNodes = new ArrayList<ParseNode>();
		
	}
	
	public ParseNode(ParseNode parentNode, Symbol symbol) {
		this.parentNode = parentNode;
		this.symbol = symbol;
		this.childNodes = new ArrayList<ParseNode>();
		
	}

	public ParseNode getParentNode() {
		return this.parentNode;
		
	}

	public void setParentNode(ParseNode parentNode) {
		this.parentNode = parentNode;
		
	}

	public Symbol getSymbol() {
		return this.symbol;
		
	}

	public void setSymbol(Symbol symbol) {
		this.symbol = symbol;
		
	}

	public ArrayList<ParseNode> getChildNodes() {
		return this.childNodes;
		
	}

	public void setChildNodes(ArrayList<ParseNode> childNodes) {
		this.childNodes = childNodes;
		
	}
	
	public void addChildNode(ParseNode childNode) {
		this.childNodes.add(childNode);
		
	}
	
	
	
}
