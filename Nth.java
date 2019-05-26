import java.util.ArrayList;
import java.util.Arrays;

public class Nth {
	ArrayList<Character> symbols =  new ArrayList<Character>(Arrays.asList(new Character("a"), new Character("b"), new Character("c")));

	Alphabet alphabet = new Alphabet(symbols);
	
	public static void main(String[] args) {
		
	}
	
	public static AlphaString nthString(Alphabet alphabet){
		AlphaString nthString = new AlphaString(alphabet);
		
		return nthString;
		
	}
}
