package assembler;
import java.util.*;


public class Code{
	private static HashMap dest_table;
	private static HashMap comp_table;
	private static HashMap jump_table;
	
	public Code()
	{
		dest_table = new HashMap<String, String>();
		dest_table.put("null", "000");
		dest_table.put("M", "001");
		dest_table.put("D", "010");
		dest_table.put("MD", "011");
		dest_table.put("A", "100");
		dest_table.put("AM", "101");
		dest_table.put("AD", "110");
		dest_table.put("AMD", "111");
		
		comp_table = new HashMap<String, String>();
		comp_table.put("0", "0101010");
		comp_table.put("1", "0111111");
		comp_table.put("-1", "0111010");
		comp_table.put("D", "0001100");
		comp_table.put("A", "0110000");
		comp_table.put("!D", "0001101");
		comp_table.put("!A", "0110001"); 
		comp_table.put("-D", "0001111");
		comp_table.put("-A", "0110011");
		comp_table.put("D+1", "0011111");
		comp_table.put("A+1", "0110111");
		comp_table.put("D-1", "0001110");
		comp_table.put("A-1", "0110010");
		comp_table.put("D+A", "0000010");
		comp_table.put("D-A", "0010011");
		comp_table.put("A-D", "0000111");
		comp_table.put("D&A", "0000000");
		comp_table.put("D|A", "0010101");
		comp_table.put("M", "1110000");
		comp_table.put("!M", "1110001");
		comp_table.put("-M", "1110011");
		comp_table.put("M+1", "1110111");
		comp_table.put("D+M", "1000010");
		comp_table.put("D-M", "1010011");
		comp_table.put("M-D", "1000111");
		comp_table.put("D&M", "1000000");
		comp_table.put("D|M", "1010101");
		
		jump_table = new HashMap<String, String>();
		jump_table.put("null", "000");
		jump_table.put("JGT", "001");
		jump_table.put("JEQ", "010");
		jump_table.put("JGE", "011");
		jump_table.put("JLT", "100");
		jump_table.put("JNE", "101");
		jump_table.put("JLE", "110");
		jump_table.put("JMP", "111");
	}
	
		public static void dest_func(String args1) {
					
          
				}
				public static void comp_func(String args2) {
					
				}

				public static void jump_func(String args3) {

				}
			
				
			public Object dget(String symbol) {
					
				  return dest_table.get(symbol);
		  }
			public Object cget(String symbol) {
				  return comp_table.get(symbol);
		  }
			public Object jget(String symbol) {
				  return jump_table.get(symbol);
		  }
			
}