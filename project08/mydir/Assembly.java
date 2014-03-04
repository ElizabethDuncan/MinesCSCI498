package mydir;
// import necessary libraries
import java.util.*;


public class Assembly{
	//Declare three hashmaps - one for each command type
	private static HashMap pushc;
	private static HashMap popc;
	private static HashMap arthc;
	private static HashMap labelc;
	
	public Assembly()
	{
		//Initialize arthimetic command hashtable with assembly translation
		arthc = new HashMap<String, String>();
		arthc.put("add", "\n@SP\nA=M-1\nD=M\n@R13\nM=D\n@SP\nM=M-1\n@SP\nA=M-1\nD=M\n@R13\nA=M\nD=D+A\n@SP\nA=M-1\nM=D\n");
		arthc.put("sub", "\n@SP\nA=M-1\nD=M\n@R13\nM=D\n@SP\nM=M-1\n@SP\nA=M-1\nD=M\n@R13\nA=M\nD=D+A\n@SP\nA=M-1\nM=M-1\n");
		arthc.put("neg", "\n@SP\nA=M-1\nM=-M\n");
		arthc.put("eq", "\n@SP\nA=M-1\nD=M\n@R13\nM=D\n@SP\nM=M-1\n@SP\nA=M-1\nD=M\n@R13\nA=M\nD=D-A\n@LBL1\nD;JEQ\nD=0\n@LBL2\n0;JMP\n(LBL1)\nD=-1\n(LBL2)\n@SP\nA=M-1\nM=D\n");
		arthc.put("and", "\n@SP\nM=M-1\nA=M\nD=M\n@SP\nA=m-1\nM=A&M\n");
		arthc.put("gt", "\n@SP\nA=M-1\nD=M\n@R13\nM=D\n@SP\nM=M-1\n@SP\nA=M-1\nD=M\n@R13\nA=M\nD=D-A\n@LBL1\nD;JGT\nD=0\n@LBL2\n0;JMP\n(LBL1)\nD=-1\n(LBL2)\n@SP\nA=M-1\nM=D\n");
		arthc.put("lt", "\n@SP\nA=M-1\nD=M\n@R13\nM=D\n@SP\nM=M-1\n@SP\nA=M-1\nD=M\n@R13\nA=M\nD=D-A\n@LBL1\nD;JLT\nD=0\n@LBL2\n0;JMP\n(LBL1)\nD=-1\n(LBL2)\n@SP\nA=M-1\nM=D\n");
		arthc.put("or", "\n@SP\nM=M-1\nA=M\nD=M\n@SP\nA=m-1\nM=A|M\n");
		arthc.put("not", "\n@SP\nA=M-1\nA+!M\n");
		
		//Initialize pull command hashtable with assembly translation
		popc = new HashMap<String, String>();
		popc.put("constant", "@<number>\nD=A\n@SP\nM=M+1\n@SP\nA=M-1\nM=D\n");
		popc.put("argument", "@ <number> \nD=A\n@ARG\nM=M+1\n@ARG\nA=M-1\nM=D\n");
		popc.put("local", "@ <number> \nD=A\n@LCL\nM=M+1\n@LCL\nA=M-1\nM=D\n");
		popc.put("temp", "@<number>\nD=A\n@SP\nM=M+5\n@SP\nA=M-1\nM=D\n");  
		popc.put("this", "@<number>\nD=A\n@THIS\nM=M+1\n@THIS\nA=M-1\nM=D\n");
		popc.put("that", "@<number>\nD=A\n@THAT\nM=M+1\n@THAT\nA=M-1\nM=D\n");
	
		//Initialize push command hashtable with assembly translation
		pushc = new HashMap<String, String>();
		pushc.put("constant", "@<number>\nD=A\n@SP\nM=M+1\n@SP\nA=M-1\nM=D\n");
		pushc.put("argument", "@<number>\nD=A\n@ARG\nM=M+1\n@ARG\nA=M-1\nM=D\n");
		pushc.put("local", "@<number>\nD=A\n@LCL\nM=M+1\n@LCL\nA=M-1\nM=D\n");
		pushc.put("temp", "@<number>\nD=A\n@SP\nM=M+5\n@SP\nA=M-1\nM=D\n"); 
		pushc.put("this", "@<number>\nD=A\n@THIS\nM=M+1\n@THIS\nA=M-1\nM=D\n");
		pushc.put("that", "@<number>\nD=A\n@THAT\nM=M+1\n@THAT\nA=M-1\nM=D\n");
		
		//Initialize label command hastable with assembly translation
		labelc = new HashMap<String, String>();
		labelc.put("goto", "@<label>\n0;JEQ\n");
		labelc.put("if-goto", "//something");
		labelc.put("label", "//something");
		
	}
	
			
			//Enable other classes to access the pop values	
			public Object popget(String symbol) {
					
				  return popc.get(symbol);
		  }
			//Enable other classes to access the push values	
			public Object pushget(String symbol) {
				  return pushc.get(symbol);
		  }
			//Enable other classes to access the arithmetic values	
			public Object arthget(String symbol) {
				  return arthc.get(symbol);
		  }
			//Enable other calsses to access the label values
			public Object labelget(String symbol) {
				return labelc.get(symbol);
			}
			
}