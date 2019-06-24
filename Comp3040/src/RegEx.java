
public interface RegEx {
	
	static final NFAFunctions func = new NFAFunctions();
	
	public NFA compile();
	
	public AlphaString generate();
	
	public boolean accepts();
	
	public Alphabet getAlphabet();
	
	public String displayable();
	
	public boolean isUnion();
	
	public boolean isConcat();
	
	public boolean isStar();
	
}
