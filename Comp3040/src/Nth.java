//Defines and test the function that generates the nth string in a lexicographic ordering

import java.util.ArrayList;
import java.util.Arrays;

public class Nth {
	private static ArrayList<Character> symbols =  new ArrayList<Character>(Arrays.asList(new Character("0"), new Character("1"), new Character("2"), new Character("3"), new Character("4"),
																						  new Character("5"), new Character("6"), new Character("7"), new Character("8"), new Character("9"),
												   										  new Character("a"), new Character("b"), new Character("c"), new Character("d"),  new Character("e"),
												   										  new Character("f")));
	
	private static Alphabet alphabet = new Alphabet(symbols);
	
	private static int n = 16+16;
	
	public static void main(String[] args) {
		AlphaString nthString = nthString(alphabet, n);
		
		System.out.println(nthString.displayable());
		
	}
																	//Returns the nth string in a lexicographical ordering of a given alphabet
	public static AlphaString nthString(Alphabet alphabet, int n){	
		AlphaString nthString = new AlphaString(alphabet);	
		int alphabetSize = alphabet.size();
		int m = n;
		
		int exp = 0;
		int stringLength = 0;										//Length of the nth string
		
		if(m < Math.pow(alphabetSize, exp)){						//If n = 0, return empty string
			return nthString;
			
		}
																	//Find the length of the nth string, and the indices for the new characters in the alphabet
		while (m >= Math.pow(alphabetSize, exp)){					
			m -= Math.pow(alphabetSize, exp);						
			
			exp++;													
			stringLength++;											
			
		}
		
																	//Initialize nthString with stringLength many first characters
		for(int i = 0; i < stringLength; i++){
			nthString.addChar(alphabet.get(0));
			
		}
		
		String newIndices = Integer.toString(Integer.parseInt(Integer.toString(m), 10), alphabetSize);				//Indices of the character to be added in base alphabetSize
		
		for(int i = 0; i < newIndices.length(); i++){
			String newStrIndex = String.valueOf(newIndices.charAt(newIndices.length()-i-1));						//Index of the character to be added in base Alphabetsize
			
			int newIntIndex = Integer.parseInt(Integer.toString(Integer.parseInt(newStrIndex, alphabetSize), 10)); //Index of the character to be added in base 10
			
			Character newCharacter = alphabet.get(newIntIndex);
			
			nthString.setChar((nthString.length()-i-1), newCharacter);
			
		}
		
		return nthString;
		
	}
	
}