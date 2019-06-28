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
	
	public void reset() {
		this.nodes = new ArrayList<ArrayList<TTNode>>();
		this.nodes.add(new ArrayList<TTNode>());
		this.depth = 0;
		
	}
	
	public String displayable() {
		String display = "";
		
		for(ArrayList<TTNode> row : nodes){
			for(TTNode node : row) {
				display += " Parent Node: " + node.getState().getIdentifier() + " Child Nodes : ";
				
				if(node.getChildNodes().isEmpty()) {
					display += "[none] ";
					
				}else {
					for(TTNode child : node.getChildNodes()) {
						display += child.getState().getIdentifier() + " ";
								
					}
				
				}
				
				display += "\n \n";
				
			}
			
		}
		
		return display;
		
	}
	
}
