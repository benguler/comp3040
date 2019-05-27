import java.util.ArrayList;

public class Character {
	
	private String character;
	
	public Character(String character){
		this.character = character;
		
	}

	public void printChar() {
		System.out.print(character);
		
	}
	
	public String toString(){
		return character;
		
	}
	
}
