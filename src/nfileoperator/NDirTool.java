package nfileoperator;

import java.io.File;

public class NDirTool {

	public static File[] getFiles(String path){
		
		File dir = new File(path);
		File files[] = dir.listFiles();
		return files;
	}
}
