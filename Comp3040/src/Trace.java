import java.util.ArrayList;

public class Trace {

	ArrayList<State> trace;
	
	public Trace(){
		this.trace = new ArrayList<State>();
		
	}
	
	public Trace(ArrayList<State> trace){
		this.trace = trace;
		
	}
	
	public ArrayList<State> getStates(){
		return this.trace;
		
	}
	
	public void addState(State state){
		this.trace.add(state);
		
	}
	
	public String displayable(){
		String display = "";
		
		for(int i = 0; i < this.trace.size(); i++){
			display += trace.get(i).getIdentifier();
			display += " ";
			
		}
		
		return display;
		
	}
	
	public void clear(){
		this.trace.clear();
		
	}
	
	public State get(int i) {
		return trace.get(i);
		
	}
	
	public int size(){
		return trace.size();
		
	}
	
}
