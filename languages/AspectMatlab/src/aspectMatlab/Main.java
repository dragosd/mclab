
/*
Main entry point
Author: Toheed Aslam
Date: May 01, 2010
*/

package aspectMatlab;

import ast.*;
import natlab.CommentBuffer;
import beaver.Parser;
import java.io.*;
import java.util.*;
import natlab.toolkits.*;
import natlab.toolkits.rewrite.clearresolution.*;
import natlab.toolkits.rewrite.endresolution.*;

public class Main
{
	public static void main(String[] args)
	{
		System.err.println("--------------------------");
		System.err.println("AspectMatlab");
		System.err.println("--------------------------");

		//StringBuffer errors = new StringBuffer();
		ArrayList errors = new ArrayList();
		
		//parse each file and put them in a list of Programs
		//aspects are kept separate, as we dont weave into them
		LinkedList<Program> programs = new LinkedList<Program>();
		LinkedList<Program> aspects = new LinkedList<Program>();
		CompilationUnits cu = new CompilationUnits();

		String outPath = "weaved";
		boolean isEntryPoint = false;
		boolean isMatlabConvert = false;

		if(args.length == 0){
			displayUsage();
			System.exit(1);
		}

		for(int i=0; i<args.length; i++){
			String file = args[i];

			//Flags
			if(file.compareTo("-version") == 0){ //version
				System.out.println("Version 1.0.1");
				continue;
			} else if(file.compareTo("-h") == 0 || file.compareTo("-help") == 0){ //usage
				displayUsage();
				continue;
			} else if(file.compareTo("-out") == 0){ //out directory
				outPath = args[i+1];
				System.out.println("Output directory set: " + outPath);
				i++; //to skip the directory name
				continue;
			} else if(file.compareTo("-main") == 0){ //entry point
				isEntryPoint = true;
				continue;
			} else if(file.compareTo("-m") == 0){ //matlab translation
				isMatlabConvert = true;
				continue;
			}

			Reader fileReader = new StringReader("");

			if(isMatlabConvert) {
				System.err.println("Translating "+file+" to Natlab");
				fileReader = natlab.Parse.translateFile( file, errors );

				if( errors.size() > 0 ){
					System.err.print( errors.toString() );
					errors.clear();
				}
				if( fileReader == null ){
					System.err.println("\nSkipping " + file);
					break;
				}
				
				isMatlabConvert = false;
				
			} else {
				//treat as a natlab input, set fileReader to a new 
				//FileReader instance pointing to the current file
				try{
					fileReader = new FileReader( file );
				}catch(FileNotFoundException e){
					System.err.println("File "+file+" not found!\nAborting");
					System.exit(1);
				}
			}
			
			//parse the file
			System.err.println("Parsing: " + file);
			Program prog = null;
			prog = parseFile( file,  fileReader, errors );

			if( prog == null ){
				//report errors
				if( errors.size() > 0 ) {
					System.err.print( errors.toString() );
					errors.clear();
				}
				System.err.println("Skipping " + file);
				continue;
			}

			//Keep the file name, mainly required in case of scripts
			String fname = file.substring(file.lastIndexOf("/")+1);
			prog.setFileName(fname);
			if(isEntryPoint){
				prog.setEntryPoint(true);
				cu.setEntryPoint(true);
				System.out.println("Entry point set: " + fname);
				isEntryPoint = false;
			}

			if(prog instanceof Aspect) {
				System.err.println("Fetching aspect info: " + file);

				//Weeding checks on Aspect file
				if(!((Aspect)prog).weeding()){
					System.err.println("Skipping " + file);
					continue;
				}

				AspectsEngine.fetchAspectInfo(prog);
				prog = AspectsEngine.convertToClass(prog);
				prog.setFileName(fname);
				aspects.add(prog);
			} else {
				programs.add(prog);
			}
		}

		if(programs.size() > 0){
			//Take all resulting Program nodes and place them in a
			//CompilationUnits instance
			for( Program p : programs ){
				cu.addProgram( p );
			}

			System.err.println("--------------------------");

			//Perform different kinds of transformations
			//including statement simplification, loop transformation...
			System.err.println("Transforming...");
			for( Program p : cu.getPrograms() ){
				p.aspectsCorrespondingFunctions();
			}

			//Perform the flow analysis to determine name resolution
			System.err.println("Analysing...");
			//AspectsEngine.analysis(cu);
			AspectsEngine.flowAnalysis(cu);

			//Matching and weaving aspects
			System.err.println("Matching and Weaving...");
			for( Program p : cu.getPrograms() ){
				p.aspectsWeave();
			}
			
			
			ast.List<Program> allPrograms = cu.getPrograms();

			for(int i = 0;i<allPrograms.getNumChild();i++){
				Program p = allPrograms.getChild(i);
				if(p instanceof Aspect)continue;
				
				//Clear rewrite analysis to protect aspect structures
				for(int j = 0;j<p.getNumChild();j++){
					//System.err.println();
					//System.out.println("Program"+i+"node"+j);
					ASTNode oldNode = p.getChild(j);
					
						//System.err.println(oldNode.toString());
						ClearResolutionRewrite rewriteClear = new ClearResolutionRewrite(oldNode);
	
						ASTNode newNode = new ASTNode();
						boolean rewriten = true;			
						
						newNode = rewriteClear.transform();
						
						if(rewriten){
							p.setChild(newNode,j);
						}
					
				}
				
				//Replace End with call to builtin End, before 3adressrewrite.
				for(int j = 0;j<p.getNumChild();j++){
					//System.err.println();
					//System.err.println("Program"+i+"node"+j);
					ASTNode oldNode = p.getChild(j);
					
						//System.err.println(oldNode.toString());
						EndResolutionRewrite rewriteEnd = new EndResolutionRewrite(oldNode);
	
						ASTNode newNode = new ASTNode();
						boolean rewriten = true;	
					
						newNode = rewriteEnd.transform();
				
						if(rewriten){
							p.setChild(newNode,j);
						}
					
				}
				
			}
 
			//Post-processing: adding aspect global structure
			AspectsEngine.weaveGlobalStructure(cu);
			for( Program a : aspects ){
				cu.addProgram( a );
			}

			
		
			
			
			//System.err.println("Pretty Printing...");
			//System.out.println(cu.getPrettyPrinted());

			System.err.println("Generating output files...");
			FileWriter writer;

			File dir = new File(outPath);
			dir.mkdirs();

			for( Program p : cu.getPrograms() ){
				try{
					File f = new File(outPath+"/"+p.getFileName());
					f.createNewFile();
					writer = new FileWriter(f);
					writer.write(p.getPrettyPrinted());
					writer.flush();
					writer.close();
				}catch(IOException e){
					System.err.println("File "+p.getFileName()+" can not be opened!\nAborting");
					
				}
			}

			System.err.println("Done!");
			System.err.println("--------------------------");
		}
		
		
	}

	//Parse a given aspect file and return a Program ast node
	//if file does not exist or other problems, exit program
	private static Program parseFile(String fName, Reader file, ArrayList errBuf )
	{
		AspectsParser parser = new AspectsParser();
		AspectsScanner scanner = null;
		CommentBuffer cb = new CommentBuffer();

		parser.setCommentBuffer(cb);

		try{
			scanner = new AspectsScanner( file );
			scanner.setCommentBuffer( cb );
			try{

				Program prog = (Program)parser.parse(scanner);
				if( parser.hasError() ){
					for( String error : parser.getErrors())
						errBuf.add(error + "\n");
					prog = null;
				}
				return prog;

			}catch(Parser.Exception e){
				errBuf.add(e.getMessage());
				for(String error : parser.getErrors()) {
					errBuf.add(error + "\n");
				}
				return null;
			} 
		}catch(FileNotFoundException e){
			errBuf.add( "File "+fName+" not found!\n" );
			return null;
		}
		catch(IOException e){
			errBuf.add( "Problem parsing "+fName + "\n");
			if( e.getMessage() != null )
				errBuf.add( e.getMessage() + "\n");
			return null;
		}
		finally{
			if(scanner != null) {
				//scanner.stop();
			}
		}
	}

	//Prints out the usage
	private static void displayUsage()
	{
		System.out.println("Usage: amc.jar myFunc.m myAspect.m");
		System.out.println("Flags:");
		System.out.println("\t-main: to specify the program entry point");
		System.out.println("\t\tamc.jar myFunc1.m -main myFunc2.m myAspect.m");
		System.out.println("\t-m: to translate standard MATLAB to Natlab");
		System.out.println("\t\tamc.jar -m myFunc.m myAspect.m");
		System.out.println("\t-out: to specify the output directory");
		System.out.println("\t\tamc.jar -out output myFunc.m myAspect.m");
		System.out.println("\t-version: to check the version number");
		System.out.println("\t\tamc.jar -version");
		System.out.println("\t-help(-h): to check the usage");
		System.out.println("\t\tamc.jar -help");
	}
}
