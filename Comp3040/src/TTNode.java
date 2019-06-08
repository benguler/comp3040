import java.util.ArrayList;

public class TTNode {
	
	private TTNode parentNode;
	private State state;
	private Character edge;
	
	private ArrayList<TTNode> childNodes  = new ArrayList<TTNode>();;
	
	public TTNode() {
		
	}
	
	public TTNode(State state) {
		this.state = state;
	}
	
	public TTNode(TTNode parentNode, State state, Character edge){
		this.parentNode = parentNode;
		this.state = state;
		this.edge = edge;
		
	}
	
	public TTNode getParentNode() {
		return this.parentNode;
		
	}
	
	public void addChildNode(TTNode node) {
		childNodes.add(node);
		
	}
	
}
