import java.util.ArrayList;

public class TraceTree {
	
	private ArrayList<ArrayList<TTNode>> nodes;
	private int depth;
	
	public TraceTree() {
		this.nodes = new ArrayList<ArrayList<TTNode>>();
		this.nodes.add(new ArrayList<TTNode>());
		this.depth = 0;
	}
	
	
	public void addNode(TTNode node){
		this.nodes.get(this.depth).add(node);
	}
	
	public void incrimentDepth(){
		this.nodes.add(new ArrayList<TTNode>());
		this.depth++;
		
	}
	
	public int getDepth(){
		return this.depth;
		
	}
	
	public TTNode getNode(int d, int i) {
		return nodes.get(d).get(i);
				
	}
	
}
