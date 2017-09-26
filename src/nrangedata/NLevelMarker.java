package nrangedata;

import java.io.File;

import nconfig.NConfig;
import nfileoperator.NDirTool;
import nfileoperator.NFileOperator;

public class NLevelMarker {

	private String path;
	private NFileOperator fileOperator;
	
	public NLevelMarker(String path){
		this.path = path;
		fileOperator = new NFileOperator();
	}
	
	public void mark(){
		
		File files[] = NDirTool.getFiles(path);
		
		for(File tempfile : files){
			String tempfilePath = tempfile.getAbsolutePath();
			fileOperator.loadReadFile(tempfilePath);
			fileOperator.loadWriteFile(path+"mark\\"+tempfile.getName());

			String line;
			while((line = fileOperator.nextLine())!=null){
				line = line.trim();
				if(line.equals("")){
					continue;
				}
				String[] data = line.split(",");
				int level = Integer.valueOf(data[4]);
				String mark;
				if(level<45){
					mark = "< ";
				}
				else{
					mark = "> ";
				}
				fileOperator.writeToFile(mark+line);
				
			}
			
			fileOperator.closeOut();
		}
	}
	
	public static void main(String[] args) {
		NLevelMarker marker = new NLevelMarker("H:\\Myo\\Data\\Processing\\modified_removed\\");
		marker.mark();
		System.out.println("Mark done.");
	}
}
