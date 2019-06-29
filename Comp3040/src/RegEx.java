
public interface RegEx {
	
	static final NFAFunctions func = new NFAFunctions();
	
	public NFA compile();
	
	public AlphaString generate();
	
	public boolean accepts(AlphaString string);
	
	public Alphabet getAlphabet();
	
	public String displayable();
	
	public boolean isUnion();
	
	public boolean isConcat();
	
	public boolean isStar();
	
	public boolean isChar();
	
	public boolean isEpsilon();
	
	public boolean isEmpty();
	
}
