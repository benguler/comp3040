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
	
	public int length(){
		return string.size();
		
	}
	
	public Character getChar(int i){
		return string.get(i);
		
	}
	
	public int getCharPosition(Character character){
		return string.indexOf(character);
		
	}
	
	public void addChar(Character character){				//Add char at alphabet[i] to string
		int i = alphabet.findIndex(character);
		
		string.add(alphabet.get(i));
		
	}
	
	public void setChar(int n, Character character){		//Sets the nth character of a string to the new character 
		string.set(n, character);
		
	}
	
	public String displayable(){
		String display = "";
		
		for(int i = 0; i < string.size(); i++){
			display += string.get(i).displayable();
			
		}
		
		return display;
		
	}
	
	
}