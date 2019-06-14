import java.util.ArrayList;

public class Alphabet {
	
	private ArrayList<Character> alphabet = new ArrayList<Character>(); 
	
	public Alphabet(){
		
	}
	
	public Alphabet(ArrayList<Character> alphabet){
		this.alphabet = alphabet;
		
	}
	
	public int size(){
		return alphabet.size();
		
	}
	
	public void set(int i, Character character){
		alphabet.set(i, character);
		
	}
	
	public Character get(int i){
		return alphabet.get(i);
		
	}
	
	public int findIndex(Character character){
		return alphabet.indexOf(character);
	
	}
	
	public ArrayList<Character> getList(){
		return alphabet;
		
	}
	
}
