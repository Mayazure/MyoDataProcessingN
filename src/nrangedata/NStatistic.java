package nrangedata;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import nfileoperator.NDirTool;
import nfileoperator.NFileOperator;

public class NStatistic {
	
	private String path;
	private ArrayList<NRangeInfoWithLine> rangeLevel;
	
	public NStatistic(String path){
		this.path = path;
		rangeLevel = new ArrayList<NRangeInfoWithLine>();
	}
	
	public ArrayList<NRangeInfoWithLine> getRangeLevelList(){
		File files[] = NDirTool.getFiles(path);
		for(File tempfile : files){
			String tempfilePath = tempfile.getAbsolutePath();
			rangeLevel.addAll(new NRangeList(tempfilePath, NRangeList.RANGE_MODIFIED_NEW).getRangeInfoWithLines());
		}
		return rangeLevel;
	}
	
	public void saveToFile(String path){
		NFileOperator fileOperator = new NFileOperator();
		fileOperator.loadWriteFile(path+"range.csv");
		
		Iterator<NRangeInfoWithLine> iterator = rangeLevel.iterator();
		while(iterator.hasNext()){
			NRangeInfoWithLine rf = iterator.next();
			fileOperator.writeToFile(rf.getAnxiousLevel() + "," + rf.getAngerLevel());
		}
	}
	
	public static void main(String[] args) {
		NStatistic ns = new NStatistic("H:\\Myo\\Data\\Processing\\modified\\");
		ns.getRangeLevelList();
		ns.saveToFile("H:\\Myo\\Data\\Processing\\modified\\");
	}
}
