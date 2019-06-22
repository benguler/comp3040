import java.util.ArrayList;

public class AlphaString {
	
	private ArrayList<Character> string = new ArrayList<Character>();
	private Alphabet alphabet;
	
	public AlphaString(){
		
	}
	
	public AlphaString(Alphabet alphabet){
		this.alphabet = alphabet;
		
	}
	
	public AlphaString(Alphabet alphabet, ArrayList<Character> string){
		this.alphabet = alphabet;
	
		this.string = string;
		
	}
	
	public AlphaString(Alphabet alphabet, Character character){
		this.alphabet = alphabet;
	
		this.string.add(character);
		
	}
	
	public int length(){
		return this.string.size();
		
	}
	
	public Character getChar(int i){
		return this.string.get(i);
		
	}
	
	public int getCharPosition(Character character){
		return this.string.indexOf(character);
		
	}
	
	public void addChar(Character character){				//Add char at alphabet[i] to string
		this.string.add(character);
		
	}
	
	public void pushChar(Character character){
		this.string.add(0, character);
	}
	
	public void setChar(int n, Character character){		//Sets the nth character of a string to the new character 
		this.string.set(n, character);
		
	}
	
	public String displayable(){
		String display = "";
		
		for(int i = 0; i < this.string.size(); i++){
			display += this.string.get(i).displayable();
			
		}
		
		return display;
		
	}
	
	public ArrayList<Character> getChars(){
		return this.string;
		
	}
	
}