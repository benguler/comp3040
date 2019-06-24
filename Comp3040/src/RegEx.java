
public interface RegEx {
	
	static final NFAFunctions func = new NFAFunctions();
	
	public NFA compile();
	
	public AlphaString generate();
	
	public boolean accepts();
	
	public Alphabet getAlphabet();
	
}
