import java.util.ArrayList;

public class Alphabet {
	
	private ArrayList<Character> alphabet = new ArrayList<Character>(); 
	
	public Alphabet(ArrayList<Character> alphabet){
		this.alphabet = alphabet;
		
	}
	
	public int size(){
		return alphabet.size();
		
	}
	
	public void set(int n, Character character){
		alphabet.set(n, character);
		
	}
	
	public Character get(int n){
		return alphabet.get(n);
		
	}
	
}
