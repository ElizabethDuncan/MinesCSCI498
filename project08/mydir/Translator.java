package mydir;
//Import necessary libraries
import java.io.*;
import java.util.*;
import java.util.regex.Pattern;


public class Translator {
	//Declare output file
	static File fileOut;

	//main class
	public static void main(String[] fileName) throws IOException {
		
		String name = "main";
		
		//Enable main to access the hashmaps in Assembly
		Assembly popc = new Assembly();
		Assembly pushc = new Assembly();
		Assembly arthc = new Assembly();
		Assembly labelc = new Assembly();
		
		//Declare variables
		String type = "";
		String push = "push";
		char commandType;
		
		//Initialize input file
		String CommandLine = null;
		File file = new File(fileName[0]);
		Scanner textReader;
		
		//Create output file
		fileOut = new File( "output.asm");
		RandomAccessFile rFile = null;
		
		//Initializing output file
		try {
			rFile = new RandomAccessFile (fileOut, "rw");
			rFile.setLength(0);
			
		}catch (IOException e) {
			System.out.println("Output file is having issues.");
			e.printStackTrace();
		}
		
		
		
		try {
			//reads full line to next line
			textReader = new Scanner(file).useDelimiter("\\n");
			//While loop through each line of command
			while(textReader.hasNextLine()) {
				CommandLine = textReader.nextLine();
				
				
				
				//Get rid of comments
				String [] result = CommandLine.split("//");
				//Token is comment-less code
				String token = result[0];
				
				//If there is an empty line, move on to the next line
				if(token.equals("")) {
					continue;
				}
				
				//Just making the world easier to understand
				String line = token;
				
				//Get rid of whitespace
				line = line.trim();

				
				//Split the command
				String[] code = line.split(" ");
				//This is the type. A result will either be push, pull, or an arithmetic command
				type = code[0];

				
				
				if(type.length() == 5 && type.charAt(2) == 'b') {
					String label = code[1];
					String calllab = name + '$' + label;
					
					//put in symbol table??
				}else if(type.length() == 4 && type.charAt(2) == 't') {
					String label = code[1];
					String calllab = name + '$' + label;
					
					//Look up the assembly language translation in labels
					String labelhack = (String)(labelc.labelget(type));
					//Replace <number> with the index, or number being passed
					String termToReplace = "<label>";
		            String replacedString = calllab;
		            //output results
		            System.out.println("//" + type + " " + calllab);
		            System.out.println( Pattern.compile( termToReplace ).matcher( labelhack ).replaceAll( replacedString ) );
		            //Output results to output file
		            try {
		            	rFile.writeChars("//" + type + " " + calllab);
						rFile.writeChars(Pattern.compile( termToReplace ).matcher( labelhack ).replaceAll( replacedString ));
						rFile.writeChars("\n");
					}catch(IOException a) {
						a.printStackTrace();
					}
				}else if(type.length() == 7 && type.charAt(2) == '-') {
					String label = code[1];
					String calllab = name + '$' + label;
					
					//Look up the assembly language translation in labels
					String labelhack = (String)(labelc.labelget(type));
					//Replace <number> with the index, or number being passed
					String termToReplace = "<label>";
		            String replacedString = calllab;
		            //output results
		            System.out.println("//" + type + " " + calllab);
		            System.out.println( Pattern.compile( termToReplace ).matcher( labelhack ).replaceAll( replacedString ) );
		            //Output results to output file
		            try {
		            	rFile.writeChars("//" + type + " " + calllab);
						rFile.writeChars(Pattern.compile( termToReplace ).matcher( labelhack ).replaceAll( replacedString ));
						rFile.writeChars("\n");
					}catch(IOException a) {
						a.printStackTrace();
					}
				}else if(type.length() == 4 && type.charAt(2) == 's'){
					//Assign the next word in that line to the segment
					String segment = code[1]; 
					//And the word after that the the index, or number being passed
					String index = code[2];
					
					//Look up the assembly language translation in Assembly
					String pushhack = (String)(pushc.pushget(segment));
					//Replace <number> with the index, or number being passed
					String termToReplace = "<number>";
		            String replacedString = index;
		            //output results
		            System.out.println("//" + type + " " + segment + " " + index);
		            System.out.println( Pattern.compile( termToReplace ).matcher( pushhack ).replaceAll( replacedString ) );
		            //Output results to output file
		            try {
		            	rFile.writeChars("//" + type + " " + segment + " " + index + "\n");
						rFile.writeChars(Pattern.compile( termToReplace ).matcher( pushhack ).replaceAll( replacedString ));
						rFile.writeChars("\n");
					}catch(IOException a) {
						a.printStackTrace();
					}

				}else if(type.length() == 3 &&type.charAt(2) == 'p') {
					//Assign the next word in that line to the segment
					String segment = code[1]; 
					//And the word after that the the index, or number being passed
					String index = code[2];
					
					//Look up the assembly translation in Assembly
					String pophack = (String)(popc.popget(segment));					
					//Replace all <number> with index, the number being passed
					String termToReplace = " <number> ";
		            String replacedString = index;
		            //Output results
		            System.out.println("//" + type + " " + segment + " " + index);
		            System.out.println( Pattern.compile( termToReplace ).matcher( pophack ).replaceAll( replacedString ) );
		            //Output results to the output file
		            try {
		            	rFile.writeChars("//" + type + " " + segment + " " + index);
						rFile.writeChars(Pattern.compile( termToReplace ).matcher( pophack ).replaceAll( replacedString ));
						rFile.writeChars("\n");
					}catch(IOException a) {
						a.printStackTrace();
					}
					//If not push or pull, must be an arithmetic command
				}else{
					//Look up the assembly translation in Assembly
					String arthhack = (String)(arthc.arthget(type));
					//output results
					System.out.println("//" + type);
					System.out.println(arthhack);
					//Output results to output file
					try {
						rFile.writeChars("//" + type);
						rFile.writeChars(arthhack);
						rFile.writeChars("\n");
					}catch(IOException a) {
						a.printStackTrace();
					}
				}
				
				
			}
			
			
			
			
		}catch (FileNotFoundException x) {
			System.out.println("Input file not found.");
			x.printStackTrace();
		}
		
		//Close output file
		try {
			rFile.close();
		}catch (IOException b) {
			b.printStackTrace();
		}
		
	
		
		
		
	}
	
}