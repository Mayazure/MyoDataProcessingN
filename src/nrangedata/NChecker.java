package nrangedata;

import java.io.File;

import nconfig.NConfig;
import nfileoperator.NDirTool;
import nfileoperator.NFileOperator;

public class NChecker {

	private String path;
	private NFileOperator fileOperator;
	
	public NChecker(String path){
		this.path = path;
		fileOperator = new NFileOperator();
	}
	
	public void check(){
		
		File files[] = NDirTool.getFiles(path);
		
		for(File tempfile : files){
			String tempfilePath = tempfile.getAbsolutePath();
			fileOperator.loadReadFile(tempfilePath);
			
			int lastLine = 0;
			int fileLine = 0;
			String line;
			while((line = fileOperator.nextLine())!=null){
				fileLine ++;
				line = line.trim();
				if(line.equals("")){
					continue;
				}
				String isTest = line.substring(line.length()-1);
				if(isTest.equals("1")){
					System.out.println("In <"+tempfilePath+", line "+fileLine + ">: " + "is test.");
				}
				
				String lineNo = line.substring(0, line.indexOf(","));
				int currentLine = Integer.valueOf(lineNo);
				if(lastLine<currentLine){
					if(currentLine - lastLine <= NConfig.windowLength){
						System.out.println("In <"+tempfilePath+", line "+fileLine + ">: too small window size. " + currentLine);
					}
					lastLine = currentLine;
				}
				else{
					System.out.println("In <"+tempfilePath+", line "+fileLine + ">: disorder. " + currentLine);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		NChecker checker = new NChecker("H:\\Myo\\Data\\Processing\\modified\\");
		checker.check();
		System.out.println("Check done.");
	}
}
