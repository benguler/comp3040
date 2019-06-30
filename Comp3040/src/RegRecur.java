import java.util.ArrayList;

public class RegRecur implements RegEx {
	
	private NonTerminal nt;
	private Alphabet alphabet;
	private RegEx reg; 
	
	private ArrayList<RegEx> regs;
	
	public RegRecur(NonTerminal nt) {
		this.nt = nt;
		this.alphabet = nt.getAlphabet();
		this.regs = new ArrayList<RegEx>();
		
	}
	
	public void addReg(RegEx reg) {
		regs.add(reg);
		
	}
	
	public void setReg(RegEx reg) {
		this.reg = reg;
		
	}
	
	@Override
	public NFA compile() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AlphaString generate() {
		return this.reg.generate();
	}
	
	@Override
	public AlphaString generate(ParseTree parseTree) {
		return this.reg.generate();
	}

	@Override
	public boolean accepts(AlphaString string) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Alphabet getAlphabet() {
		// TODO Auto-generated method stub
		return this.alphabet;
	}

	@Override
	public String displayable() {
		return nt.getIdentiier();
	}

	@Override
	public boolean isUnion() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isConcat() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isStar() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isChar() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEpsilon() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isRecur() {
		// TODO Auto-generated method stub
		return true;
	}

}
