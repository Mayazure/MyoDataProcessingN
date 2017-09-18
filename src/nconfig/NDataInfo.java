package nconfig;

public class NDataInfo {

	private static NDataInfo dataInfoInstance = new NDataInfo();
	
	public static NDataInfo getDataInfoInstance(){
		return dataInfoInstance;
	}
	
	public static final int EMGFILE = 0;
	public static final int RANGEFILE = 1;
	
	private int index[];
	
	private NDataInfo(){
		index = new int[NConfig.distribution];
		for(int i:index){
			index[i] = 0;
		}
	}
	
	//The directory where all the organized emg.csv files and range.txt files stored
	private String dataDirPath;
	
	//The directory where all parsed emg.csv files stored
	private String parsedDirPath;

	//The path of the file that is being processed
	private String[] workingFilePath;
	
	public void setDirPath(String dataFilePath) {
		this.dataDirPath = dataFilePath;
		this.parsedDirPath = dataFilePath+"\\ParsedData_"+NConfig.distribution+"\\";
	}
	
	public String getDataDirPath() {
		return dataDirPath;
	}

	public String getParsedDirPath() {
		return parsedDirPath;
	}

	public String getWorkingFilePath(int index) {
		return workingFilePath[index];
	}

	public void setWorkingFilePath(String[] workingFilePath) {
		this.workingFilePath = workingFilePath;
	}
	
	public void addClassCount(int ind){
		this.index[ind]++;
	}
	
	public int getClassCount(int ind){
		return index[ind];
	}
	
}