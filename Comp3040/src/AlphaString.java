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
	
	public void addChar(int i){				//Add char at alphabet[i] to string
		string.add(alphabet.get(i));
		
	}
	
	public void setChar(int n, int i){		//Sets the nth character of a string to the ith character of an alphabet
		string.set(n, alphabet.get(i));
		
	}
	
	public String displayable(){
		String display = "";
		
		for(int i = 0; i < string.size(); i++){
			display += string.get(i).toString();
			
		}
		
		return display;
		
	}
	
	public void printString(){
		for(int i = 0; i < string.size(); i++){
			string.get(i).printChar();
			
		}
		
	}
	
}