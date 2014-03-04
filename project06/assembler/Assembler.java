package assembler;

import java.io.*;
import java.util.*;
import java.lang.*;


//TODO get a-commands to parse alpha numeric
//TODO look up error on 4th line of add.asm

public class Assembler {


	public static void main(String[] args){
	
	File binaryout = new File("Prog.txt");
	RandomAccessFile bFile = null;
	
	try {
		bFile = new RandomAccessFile (binaryout, "rw");
	}catch(IOException e) {
		e.printStackTrace();
	}
		
//		//initialize symbol table
		Code dest_func = new Code();
		Code comp_func = new Code();
		Code jump_func = new Code();
		
		String linecomp = null;
		String linedest = null;
		String linejump = null;
		String comp = null;
		String dest = null;
		String jump = null; 
		String scomp = "";
		String baint = "";
		
		boolean number = false;
		
		char command = 'N';
		int aint = 0;
	
		//Call parser to edit code and build symbol table
		Parser parse = new Parser();
		Code dest_table = new Code();
		Code comp_table = new Code();
		Code jump_table = new Code();
		
		Parser.pMain(args[0]);

		SymbolTable st = Parser.getSymTab();

		File file = Parser.getOutFile();
		RandomAccessFile rFile = null;
		
		try {
			rFile = new RandomAccessFile (file, "rw");
			
		}catch (IOException e) {
			System.out.println("Output file is having issues.")
;			e.printStackTrace();
		}
		
		
		
		try {
			//reads full line to next line
			Scanner textReader = new Scanner(file);
			while(textReader.hasNextLine()) {
				
				String line = textReader.nextLine();
				
				if(!(line.equals(null))) {
					if(line.charAt(1) == '@') {
						command = 'A';
					}else if(line.charAt(1) == '(') {
						command = 'L';
					}else{
						command = 'C';
					}
				}
				

				if ( command == 'A' )
				{
					
					//Get rid of the @ symbol
					String noa = line.replace("@", "");
					String newline = (String)(st.get(noa));
					
//					//if predefined, print out binary
//					if(st.contains(noa)) {
//						System.out.println("@statment already in symboltable")
//						System.out.println(st.get(noa));
//						try {
//							bFile.writeChars((String)(st.get(noa)));
//							bFile.writeChars("\n");
//						}catch(IOException x) {
//							x.printStackTrace();
//						}
//					}
					
					char[] cnoa = noa.toCharArray();
					
					if(st.contains(noa)){
						String already = (String)(st.get(noa));
						aint = Integer.parseInt(already);
						baint = Integer.toBinaryString(aint);
					}
					
					for(int i = 0; i < cnoa.length; i++) {
						if(Character.isDigit(cnoa[i])){
							number = true;
						}
					}
					
					if(number) {
						//what if their is more than one digit???!!!
						char yay = noa.charAt(2);
						aint = Integer.parseInt(Character.toString(yay));
						baint = Integer.toBinaryString(aint);
					}
					
					int zeroes = 15 - baint.length();
					String extrazeroes = "";
					
					while(!(extrazeroes.length() == zeroes)) {
						extrazeroes = extrazeroes + "0";
					}
					
					number = false;
					
					System.out.println("0" + extrazeroes + baint); //ONLY 15 DIGITS OF AINT
					try {
						bFile.writeChars("0" + extrazeroes + baint);
						bFile.writeChars("\n");
					}catch(IOException x) {
						x.printStackTrace();
					}
//					
				}
				
				if (command == 'L') {
					//go back to the beginning of loop
					continue;
				}
				
				if( command == 'C') {
					
					if(line.indexOf(";") != -1 &&  line.indexOf("=") !=-1){
						String [] instjump = line.split(";");
						String inst = instjump[0];
						jump = instjump[1];
						String [] destcomp = inst.split("=");
						 dest = destcomp[0];
						 comp = destcomp[1];
					}else if(line.indexOf("=") != -1) {
						String [] result2 = line.split("=");

						 dest = result2[0];
						 comp = result2[1];
						 jump = "null";


					}else if(line.indexOf("=") == -1) {
						 jump = "null";
						 dest = "null";
						 comp = line;
					}
//					System.out.println(dest);
					
					if(comp.length() == 7) {

					}
//					System.out.println(jump);
					
					comp = comp.trim();
					dest = dest.trim();
					jump = jump.trim();
////i've added this to try to edit the length of comp but it's not working.
////although this system technically works, the length required to get D+A is too long (ex 1 to 6)
////then when I try to access the string using at character, there is an error if I try to get any character except the first

					if(comp.length() == 1) {
						char ccomp = comp.charAt(0);
						 scomp = Character.toString(ccomp);
					}else if(comp.length() == 5) {
						scomp = ((Character)(comp.charAt(0))).toString() + ((Character)(comp.charAt(2))).toString() + ((Character)(comp.charAt(4))).toString();
					}else if(comp.length() == 3) {
						 scomp = ((Character)(comp.charAt(0))).toString() + ((Character)(comp.charAt(2))).toString();
					}

//					char cdest = dest.charAt(1);
//					String sdest = Character.toString(cdest);
//					
////					char cjump = jump.charAt(1);
////					String sjump = Character.toString(cjump);
					
					
//					
//					System.out.println("scomp:" + scomp);
//					System.out.println("scomp length:" +scomp.length());
//this is why i try to access at character
//					System.out.println("scomp char 1:" +scomp.charAt(1));
					//ERROR HERE!!!!
					//look up binary representation of each part
					linedest = (String)(dest_table.dget(dest));
					linecomp = (String)(comp_table.cget(scomp));
					linejump = (String)(jump_table.jget(jump));
					
//					System.out.println(linedest);
//					System.out.println(linecomp);
//					System.out.println(linejump);
					
					
					//output comp dest and jump
					//TODO actually output this!
					System.out.println("111" + linecomp + linedest + linejump); 
					try {
						bFile.writeChars("111" + linecomp + linedest + linejump);
						bFile.writeChars("\n");
					}catch(IOException x) {
						x.printStackTrace();
					}
				}
		
		
		
			}
		}catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
	}
}
	
	


	
