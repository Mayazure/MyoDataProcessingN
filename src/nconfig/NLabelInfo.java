package nconfig;

import java.io.File;

import javax.swing.filechooser.FileSystemView;

public class NLabelInfo {
	
	private static NLabelInfo labelInfoInstance = new NLabelInfo();
	
	private String workingDir = "";
	private String workingFile = "new.txt";

	private NLabelInfo(){
		File desktopDir = FileSystemView.getFileSystemView().getHomeDirectory();
		String desktopPath = desktopDir.getAbsolutePath();
		workingDir = desktopPath + "\\MyoProcessingLabel\\";
		File file = new File(workingDir);
		if(!file.exists()){
			file.mkdirs();
		}
	}
	
	public static NLabelInfo getNLabelInfoInstance(){
		return labelInfoInstance;
	}
	
	public String getWorkingDir() {
		return workingDir;
	}

	public void setWorkingDir(String workingDir) {
		this.workingDir = workingDir;
	}
	
	public String getWorkingFile() {
		return workingFile;
	}

	public void setWorkingFile(String workingFile) {
		this.workingFile = workingFile + ".txt";
	}
}
