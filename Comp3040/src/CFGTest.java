import java.util.ArrayList;
import java.util.Arrays;

public class CFGTest {
	
	private static NFAFunctions func = new NFAFunctions();
	
	//Initialize Alphabet(s)-----------------------------------------------------------------------------------------------------
		
		//The binary numbers
		private static ArrayList<Character> biSymbols =  new ArrayList<Character>(Arrays.asList(new Character("0"), new Character("1")));
		
		private static Alphabet biAlphabet = new Alphabet(biSymbols);
		
		//a, b, and c Alphabet
		private static ArrayList<Character> abcSymbols =  new ArrayList<Character>(Arrays.asList(new Character("a"), new Character("b"), new Character("c")));
		
		
		private static Alphabet abcAlphabet = new Alphabet(abcSymbols);
		
		//Lower-case English alphabet
		private static ArrayList<Character> engSymbols =  new ArrayList<Character>(Arrays.asList(new Character("a"), new Character("b"), new Character("c"), new Character("d"), new Character("e"), new Character("f"), new Character("g"),
																								 new Character("h"), new Character("i"), new Character("j"), new Character("k"), new Character("l"), new Character("m"), new Character("n"),
																								 new Character("o"), new Character("p"), new Character("q"), new Character("r"), new Character("s"), new Character("t"),new Character("u"),
																								 new Character("v"), new Character("w"), new Character("x"), new Character("y"), new Character("z")));
		
		private static Alphabet engAlphabet = new Alphabet(engSymbols);
	//---------------------------------------------------------------------------------------------------------------------------
	
	//CFG that produces strings in the form of (0^n)(1^n) (the znonCFG)----------------------------------------------------------
		private static ArrayList<NonTerminal> znonCFGNT = new ArrayList<NonTerminal>(Arrays.asList(new NonTerminal("S0", biAlphabet), new NonTerminal("S1", biAlphabet)));														//NT = {S0, S1}
		
		private static ArrayList<Terminal> znonCFGT = new ArrayList<Terminal>(Arrays.asList(new Terminal(biAlphabet.get(0), biAlphabet), new Terminal(biAlphabet.get(1), biAlphabet), new Terminal(NFAFunctions.epsilon, biAlphabet)));		//T = {'0', '1', [epsilon]}
		
		private static ArrayList<Rule> znonCFGRules = new ArrayList<Rule>(Arrays.asList(new Rule(znonCFGNT.get(0), znonCFGT.get(0), znonCFGNT.get(1), znonCFGT.get(1)),									//R = {(S0 ::= '0' x S1 x '1'),
																					 new Rule(znonCFGNT.get(0), znonCFGT.get(2)),																		//	   (S0 ::= [epsilon]),
																					 new Rule(znonCFGNT.get(1), znonCFGT.get(0), znonCFGNT.get(1), znonCFGT.get(1)),									//     (S1 ::= '0' x S1 x '1'),
																					 new Rule(znonCFGNT.get(1), znonCFGT.get(0), znonCFGT.get(1))														//     (S1 ::= '0' x '1')}
				));
		
		private static NonTerminal znonCFGStart = znonCFGNT.get(0);																																		//S = S0
		
		private static CFG znonCFG = new CFG(znonCFGNT, znonCFGT, znonCFGRules, znonCFGStart, NFAFunctions.epsilon);
		
	//---------------------------------------------------------------------------------------------------------------------------
		
	//CFG that produces strings made of an odd binary number (the oddCFG)--------------------------------------------------------
		private static ArrayList<NonTerminal> oddCFGNT = new ArrayList<NonTerminal>(Arrays.asList(new NonTerminal("S0", biAlphabet), new NonTerminal("S1", biAlphabet)));														//NT = {S0, S1}
		
		private static ArrayList<Terminal> oddCFGT = new ArrayList<Terminal>(Arrays.asList(new Terminal(biAlphabet.get(0), biAlphabet), new Terminal(biAlphabet.get(1), biAlphabet), new Terminal(NFAFunctions.epsilon, biAlphabet)));		//T = {'0', '1', [epsilon]}
		
		private static ArrayList<Rule> oddCFGRules = new ArrayList<Rule>(Arrays.asList(new Rule(oddCFGNT.get(0), oddCFGNT.get(1), oddCFGT.get(1)),														//R = {(S0 ::= S1 x '1'),
																					 new Rule(oddCFGNT.get(1), oddCFGT.get(2)),																			//	   (S1 ::= [epsilon]),
																					 new Rule(oddCFGNT.get(1), oddCFGNT.get(1), oddCFGT.get(0)),														//     (S1 ::= S1 x '0'),
																					 new Rule(oddCFGNT.get(1), oddCFGNT.get(1), oddCFGT.get(1))															//     (S1 ::= S1 x '1')}
				));
		
		private static NonTerminal oddCFGStart = oddCFGNT.get(0);																																			//S = S0
		
		private static CFG oddCFG = new CFG(oddCFGNT, oddCFGT, oddCFGRules, oddCFGStart, NFAFunctions.epsilon);
		
	//-----------------------------------------------------------------------------------------------------------------------
		
	//CFG that produces strings made of an even binary number (the evenCFG)--------------------------------------------------
		private static ArrayList<NonTerminal> evenCFGNT = new ArrayList<NonTerminal>(Arrays.asList(new NonTerminal("S0", biAlphabet), new NonTerminal("S1", biAlphabet)));														//NT = {S0, S1}
		
		private static ArrayList<Terminal> evenCFGT = new ArrayList<Terminal>(Arrays.asList(new Terminal(biAlphabet.get(0), biAlphabet), new Terminal(biAlphabet.get(1), biAlphabet), new Terminal(NFAFunctions.epsilon, biAlphabet)));		//T = {'0', '1', [epsilon]}
		
		private static ArrayList<Rule> evenCFGRules = new ArrayList<Rule>(Arrays.asList(new Rule(evenCFGNT.get(0), evenCFGNT.get(1), evenCFGT.get(0)),															//R = {(S0 ::= S1 x '0'),
																					 	new Rule(evenCFGNT.get(1), evenCFGT.get(2)),																			//	   (S1 ::= [epsilon]),
																					 	new Rule(evenCFGNT.get(1), evenCFGNT.get(1), evenCFGT.get(0)),															//     (S1 ::= S1 x '0'),
																					 	new Rule(evenCFGNT.get(1), evenCFGNT.get(1), evenCFGT.get(1))															//     (S1 ::= S1 x '1')}
				));
		
		private static NonTerminal evenCFGStart = evenCFGNT.get(0);																																			//S = S0
		
		private static CFG evenCFG = new CFG(evenCFGNT, evenCFGT, evenCFGRules, evenCFGStart, NFAFunctions.epsilon);
		
	//-----------------------------------------------------------------------------------------------------------------------
		
	//CFG that produces a string that contains '11' (the ooCFG)------------------------------------------------------------
		private static ArrayList<NonTerminal> ooCFGNT = new ArrayList<NonTerminal>(Arrays.asList(new NonTerminal("S0", biAlphabet), new NonTerminal("S1", biAlphabet)));														//NT = {S0, S1}
		
		private static ArrayList<Terminal> ooCFGT = new ArrayList<Terminal>(Arrays.asList(new Terminal(biAlphabet.get(0), biAlphabet), new Terminal(biAlphabet.get(1), biAlphabet), new Terminal(NFAFunctions.epsilon, biAlphabet)));		//T = {'0', '1', [epsilon]}
		
		private static ArrayList<Rule> ooCFGRules = new ArrayList<Rule>(Arrays.asList(new Rule(ooCFGNT.get(0), ooCFGNT.get(1), ooCFGT.get(1), ooCFGT.get(1), ooCFGNT.get(1)),											//R = {(S0 ::= S1 x '1' x '1' x S1),
																					  new Rule(ooCFGNT.get(1), ooCFGT.get(2)),																			//	   (S1 ::= [epsilon]),
																					  new Rule(ooCFGNT.get(1), ooCFGNT.get(1), ooCFGT.get(0)),															//     (S1 ::= S1 x '0'),
																					  new Rule(ooCFGNT.get(1), ooCFGNT.get(1), ooCFGT.get(1))															//     (S1 ::= S1 x '1')}
				));
		
		private static NonTerminal ooCFGStart = ooCFGNT.get(0);																																			//S = S0
		
		private static CFG ooCFG = new CFG(ooCFGNT, ooCFGT, ooCFGRules, ooCFGStart, NFAFunctions.epsilon);
		
	//-----------------------------------------------------------------------------------------------------------------------
		
	//CFG that produces a string that contains '00' (the zzCFG)------------------------------------------------------------
		private static ArrayList<NonTerminal> zzCFGNT = new ArrayList<NonTerminal>(Arrays.asList(new NonTerminal("S0", biAlphabet), new NonTerminal("S1", biAlphabet)));														//NT = {S0, S1}
		
		private static ArrayList<Terminal> zzCFGT = new ArrayList<Terminal>(Arrays.asList(new Terminal(biAlphabet.get(0), biAlphabet), new Terminal(biAlphabet.get(1), biAlphabet), new Terminal(NFAFunctions.epsilon, biAlphabet)));		//T = {'0', '1', [epsilon]}
		
		private static ArrayList<Rule> zzCFGRules = new ArrayList<Rule>(Arrays.asList(new Rule(zzCFGNT.get(0), zzCFGNT.get(1), zzCFGT.get(0), zzCFGT.get(0), zzCFGNT.get(1)),							//R = {(S0 ::= S1 x '0' x '0' x S1),
																					  new Rule(zzCFGNT.get(1), zzCFGT.get(2)),																			//	   (S1 ::= [epsilon]),
																					  new Rule(zzCFGNT.get(1), zzCFGNT.get(1), zzCFGT.get(0)),															//     (S1 ::= S1 x '0'),
																					  new Rule(zzCFGNT.get(1), zzCFGNT.get(1), zzCFGT.get(1))															//     (S1 ::= S1 x '1')}
				));
		
		private static NonTerminal zzCFGStart = zzCFGNT.get(0);																																			//S = S0
		
		private static CFG zzCFG = new CFG(zzCFGNT, zzCFGT, zzCFGRules, zzCFGStart, NFAFunctions.epsilon);
		
	//-----------------------------------------------------------------------------------------------------------------------
	
	//CFG that produces a string in the form ('1')* (the oStarCFG)------------------------------------------------------------
		private static ArrayList<NonTerminal> oStarCFGNT = new ArrayList<NonTerminal>(Arrays.asList(new NonTerminal("S0", biAlphabet)));														//NT = {S0}
		
		private static ArrayList<Terminal> oStarCFGT = new ArrayList<Terminal>(Arrays.asList(new Terminal(biAlphabet.get(0), biAlphabet), new Terminal(biAlphabet.get(1), biAlphabet), new Terminal(NFAFunctions.epsilon, biAlphabet)));		//T = {'0', '1', [epsilon]}
		
		private static ArrayList<Rule> oStarCFGRules = new ArrayList<Rule>(Arrays.asList(new Rule(oStarCFGNT.get(0), oStarCFGNT.get(0), oStarCFGT.get(1)),													//R = {(S0 ::= S0 x '1'),
																					     new Rule(oStarCFGNT.get(0), oStarCFGT.get(2))																		//     (S0 ::= [epsilon])}
				));
		
		private static NonTerminal oStarCFGStart = oStarCFGNT.get(0);																																			//S = S0
		
		private static CFG oStarCFG = new CFG(oStarCFGNT, oStarCFGT, oStarCFGRules, oStarCFGStart, NFAFunctions.epsilon);
		
	//-----------------------------------------------------------------------------------------------------------------------

		
	//CFG that produces a string in the form ('0')* (the zStarCFG)------------------------------------------------------------
			private static ArrayList<NonTerminal> zStarCFGNT = new ArrayList<NonTerminal>(Arrays.asList(new NonTerminal("S0", biAlphabet)));														//NT = {S0}
			
			private static ArrayList<Terminal> zStarCFGT = new ArrayList<Terminal>(Arrays.asList(new Terminal(biAlphabet.get(0), biAlphabet), new Terminal(biAlphabet.get(1), biAlphabet), new Terminal(NFAFunctions.epsilon, biAlphabet)));		//T = {'0', '1', [epsilon]}
			
			private static ArrayList<Rule> zStarCFGRules = new ArrayList<Rule>(Arrays.asList(new Rule(zStarCFGNT.get(0), zStarCFGNT.get(0), zStarCFGT.get(0)),													//R = {(S0 ::= S0 x '0'),
																						     new Rule(zStarCFGNT.get(0), zStarCFGT.get(2))																		//     (S0 ::= [epsilon])}
					));
			
			private static NonTerminal zStarCFGStart = zStarCFGNT.get(0);																																			//S = S0
			
			private static CFG zStarCFG = new CFG(zStarCFGNT, zStarCFGT, zStarCFGRules, zStarCFGStart, NFAFunctions.epsilon);
			
	//-----------------------------------------------------------------------------------------------------------------------
			
	//CFG that produces all binary strings, and epsilon (the allCFG)-------------------------------------------------------------
			private static ArrayList<NonTerminal> allCFGNT = new ArrayList<NonTerminal>(Arrays.asList(new NonTerminal("S0", biAlphabet)));																//NT = {S0, S1}
			
			private static ArrayList<Terminal> allCFGT = new ArrayList<Terminal>(Arrays.asList(new Terminal(biAlphabet.get(0), biAlphabet), new Terminal(biAlphabet.get(1), biAlphabet), new Terminal(NFAFunctions.epsilon, biAlphabet)));		//T = {'0', '1', [epsilon]}
			
			private static ArrayList<Rule> allCFGRules = new ArrayList<Rule>(Arrays.asList(new Rule(allCFGNT.get(0), allCFGNT.get(0), allCFGT.get(0)),													//R = {(S0 ::= S0 x '0'),
																						   new Rule(allCFGNT.get(0), allCFGNT.get(0), allCFGT.get(1)),												    //	   (S0 ::= S0 x '1'),
																						   new Rule(allCFGNT.get(0), allCFGT.get(2))																	//     (S0 ::= [epsilon])}
					));
			
			private static NonTerminal allCFGStart = allCFGNT.get(0);																																			//S = S0
			
			private static CFG allCFG = new CFG(allCFGNT, allCFGT, allCFGRules, allCFGStart, NFAFunctions.epsilon);
			
	//---------------------------------------------------------------------------------------------------------------------------
			
	//CFG that produces strings in the form ('1')* | ('0' x '1')* (the oStarzoStarCFG)--------------------------------------------
			private static ArrayList<NonTerminal> oStarzoStarCFGNT = new ArrayList<NonTerminal>(Arrays.asList(new NonTerminal("S0", biAlphabet), new NonTerminal("S1", biAlphabet), new NonTerminal("S2", biAlphabet)));	//NT = {S0, S1, S2}
			
			private static ArrayList<Terminal> oStarzoStarCFGT = new ArrayList<Terminal>(Arrays.asList(new Terminal(biAlphabet.get(0), biAlphabet), new Terminal(biAlphabet.get(1), biAlphabet), new Terminal(NFAFunctions.epsilon, biAlphabet)));			//T = {'0', '1', [epsilon]}
			
			private static ArrayList<Rule> oStarzoStarCFGRules = new ArrayList<Rule>(Arrays.asList(new Rule(oStarzoStarCFGNT.get(0), oStarzoStarCFGNT.get(1)),																									//R ={{s0, s1)			
																								new Rule(oStarzoStarCFGNT.get(0), oStarzoStarCFGNT.get(2)),												   													//    (s0, s2)		
																								new Rule(oStarzoStarCFGNT.get(1), oStarzoStarCFGNT.get(1), oStarzoStarCFGT.get(1)),																			//    (s1, s1 x '1')
																								new Rule(oStarzoStarCFGNT.get(1), oStarzoStarCFGT.get(2)),																									//    (s1, {epsilon})
																								new Rule(oStarzoStarCFGNT.get(2), oStarzoStarCFGT.get(0), oStarzoStarCFGT.get(1), oStarzoStarCFGNT.get(2)),													//    (s2. '0' x '1' x s2)
																								new Rule(oStarzoStarCFGNT.get(2), oStarzoStarCFGT.get(2))																									//    (s2, [epsilon])
					));
			
			private static NonTerminal oStarzoStarCFGStart = oStarzoStarCFGNT.get(0);																																											//S = s0
			
			private static CFG oStarzoStarCFG = new CFG(oStarzoStarCFGNT, oStarzoStarCFGT, oStarzoStarCFGRules, oStarzoStarCFGStart, NFAFunctions.epsilon);
			
	//---------------------------------------------------------------------------------------------------------------------------
			
	//CFG that accepts epsilon (the epsilonCFG)----------------------------------------------------------------------------------
			private static ArrayList<NonTerminal> epsilonCFGNT = new ArrayList<NonTerminal>(Arrays.asList(new NonTerminal("S0", biAlphabet)));																											//NT = {S0}
			
			private static ArrayList<Terminal> epsilonCFGT = new ArrayList<Terminal>(Arrays.asList(new Terminal(biAlphabet.get(0), biAlphabet), new Terminal(biAlphabet.get(1), biAlphabet), new Terminal(NFAFunctions.epsilon, biAlphabet)));			//T = {'0', '1', [epsilon]}
			
			private static ArrayList<Rule> epsilonCFGRules = new ArrayList<Rule>(Arrays.asList(new Rule(epsilonCFGNT.get(0), epsilonCFGT.get(0))																								//R ={{s0, [epsilon])			
																								
					));
			
			private static NonTerminal epsilonCFGStart = epsilonCFGNT.get(0);																																											//S = s0
			
			private static CFG epsilonCFG = new CFG(epsilonCFGNT, epsilonCFGT, epsilonCFGRules, epsilonCFGStart, NFAFunctions.epsilon);
	//---------------------------------------------------------------------------------------------------------------------------
	
	public static void main(String[] args) {
		

	}

}
