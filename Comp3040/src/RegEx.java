
public interface RegEx {
	
	public NFA compile();
	
	public AlphaString generate();
	
	public boolean accepts();
	
	public Alphabet getAlphabet();
	
}
