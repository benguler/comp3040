import java.util.ArrayList;

public class ParseTree {

	private ArrayList<ArrayList<ParseNode>> nodes;
	private int depth;
	
	public ParseTree() {
		this.nodes = new ArrayList<ArrayList<ParseNode>>();
		this.nodes.add(new ArrayList<ParseNode>());
		this.depth = 0;
		
	}
	
	public void addNode(ParseNode node) {
		this.nodes.get(depth).add(node);
		
	}
	
	public void incrimentDepth(){
		this.nodes.add(new ArrayList<ParseNode>());
		this.depth++;
		
	}
	
	public int getDepth(){
		return this.depth;
		
	}
	
	public void reset() {
		this.nodes = new ArrayList<ArrayList<ParseNode>>();
		this.nodes.add(new ArrayList<ParseNode>());
		this.depth = 0;
		
	}
}
