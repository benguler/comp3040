import java.util.ArrayList;

public class AlphaString {
	
	private ArrayList<Character> string;
	private Alphabet alphabet;
	
	public AlphaString(Alphabet alphabet){
		this.alphabet = alphabet;
		
	}
	
	public AlphaString(Alphabet alphabet, ArrayList<Character> string){
		this.alphabet = alphabet;
	
		this.string = string;
		
	}
	
}