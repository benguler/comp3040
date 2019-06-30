
public class Terminal implements Symbol{
	
	private Character character; 
	private Alphabet alphabet;
	
	public Terminal(Character character, Alphabet alphabet) {;
		this.character = character;
		this.alphabet = alphabet;
		
	}

	@Override
	public RegEx getReg() {
		if(this.alphabet.getList().contains(character)) {
			return new RegChar(this.character, this.alphabet);
			
		}else {
			return new RegEpsilon(this.alphabet);
			
		}
	}
	
}
