package assembler;

import java.io.*;
import java.util.Scanner;


public class Parser {
	static File fileOut;
	static SymbolTable st;
	
	public static void pMain(String fileName) {
		
		//initialize symbol table
		st = new SymbolTable( );
		
		int memory = 16;
		int counter = 0;
		
		boolean number = false;
		boolean contain = false;
		
		String CommandLine = null;
		File file = new File(fileName);
		Scanner textReader;
		
		
		fileOut = new File( "binary.txt");
		RandomAccessFile rFile = null;
		
		
		try {
			rFile = new RandomAccessFile (fileOut, "rw");
			//empty file if there is stuff already in it
			rFile.setLength(0);
			
		}catch (IOException e) {
			System.out.println("Output file is having issues.")
;			e.printStackTrace();
		}
		
		
		
		try {
			//reads full line to next line
			textReader = new Scanner(file).useDelimiter("\\n");
			while(textReader.hasNextLine()) {
				counter++;
				try {
					CommandLine = textReader.next();
				} catch(Exception e) {
					continue;
				}
		
				
				
				//Get rid of comments
				String [] result = CommandLine.split("//");
				
				//Token is comment-less code
				String token = result[0];
				if(token.equals("")) {
					continue;
				}
				//Just making the world easier to understand
				String line = token;
				
				//Get rid of whitespace
				line = line.trim();
				System.out.println(line);
				char command = commandType( line ); 
				if ( command == 'A' )
				{
					//Get rid of the @ symbol
					String noa = line.replace("@", "");
					char[] cnoa = noa.toCharArray();
					
					if(st.contains(noa)){
						contain = true;
						continue;
					}
					
					for(int i = 0; i < cnoa.length; i++) {
						if(Character.isDigit(cnoa[i])){
							number = true;
							System.out.println("isdigit is true");
						}
			
					}
					if((!number) && !contain) {
						st.addEntry(noa, memory);
						System.out.println(noa + " put in symboltable");
						memory++;	
					}
					contain = false;
					number = false;
				}
				
				if (command == 'L') {
					System.out.println("l thingy executed");
					String nop = line.replace("(", "");
					nop = nop.replace(")", "");
					st.addEntry(nop, counter);
					counter--;
				}
				
				try {
					rFile.writeChars(line);
					rFile.writeChars("\n");
				}catch(IOException a) {
					a.printStackTrace();
				}
				
				
				
				
			}
			
			
			
			
		}catch (FileNotFoundException x) {
			System.out.println("Input file not found.");
			x.printStackTrace();
		}
		
		
		try {
			rFile.close();
		}catch (IOException b) {
			b.printStackTrace();
		}
		
	
		
		
		
	}
	
	
	public static char commandType(String line) {
		
		if(!(line.equals(null))) {

			if(line.charAt(0) == '@') {
				return 'A';
			}else if(line.charAt(0) == '(') {

				return 'L';
			}else{

				return 'C';
			}
		}else {

			return 'N';
		}
	}
			
//		switch( line.charAt(0)){
//		case '@': //call symbol
//			return 'A';
//		case '(': //call symbol
//			return 'L';
//		default: return 'C';
//		}
//		}else{
//			return 'N';
//		}
//	}
//	
	public static File getOutFile()
	{
			return fileOut;
		
	}
	
	public static SymbolTable getSymTab()
	{
		return st;
	}
		
}
