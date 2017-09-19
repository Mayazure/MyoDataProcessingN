package nrangedata;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

import controller.MainWindow;
import nconfig.NConfig;
import nconfig.NDataInfo;
import nfileoperator.NFileOperator;

public class NDataExtractor {

	private NLineSeeker lineSeeker;
//	private ArrayList<Line> lines;
//	private ArrayList<NRangeInfo> rangeInfos;
//	private NRangeList rangeList;
	private NFileOperator fileOperator;
	private NDataInfo dataInfo = NDataInfo.getDataInfoInstance();
	private MainWindow mainWindow;
	private int[] count;
	private int distribution = NConfig.distribution;
	
	public NDataExtractor(MainWindow mainWindow){
		this.mainWindow = mainWindow;
		lineSeeker = new NLineSeeker();
//		lines = new ArrayList<Line>();
//		rangeList = new NRangeList(dataInfo.getDataFilePath()+dataInfo.getRangeFileName());
//		rangeInfos = rangeList.getRangeList();
		fileOperator = new NFileOperator();
//		readFileOperator = new FileOperator();
		count = new int[distribution];
		
		for(int i=0;i<count.length;i++){
			count[i]=0;
		}
	}
	
	public void extractData(){
		for(int i=0;i<distribution;i++){
			fileOperator.createFileDir(dataInfo.getParsedDirPath()+i);
		}
		
		for(int i=0;i<=NConfig.totalExp;i++){
			
			String[] path = {
					dataInfo.getDataDirPath()+"n_"+String.valueOf(i)+".csv",
					dataInfo.getDataDirPath()+String.valueOf(i)+".txt"
					};
			
			File workingFile = new File(path[0]);
			if(!workingFile.exists()){
				mainWindow.updateSimpleConsole("Skipped."+path[0]);
				continue;
			}
			dataInfo.setWorkingFilePath(path);
			
			NRangeList rangeList = new NRangeList(dataInfo.getWorkingFilePath(dataInfo.RANGEFILE));
			ArrayList<NRangeInfo> rangeInfos = rangeList.getRangeList();
			Iterator<NRangeInfo> iterator = rangeInfos.iterator();
			ArrayList<Line> lines = new ArrayList<Line>();
			
			while(iterator.hasNext()){
				NRangeInfo rangeInfo = iterator.next();
				Line tempLine = new Line(lineSeeker.seekLine(rangeInfo.getTimestamp()),rangeInfo.getRange1(),rangeInfo.getRange2());
				lines.add(tempLine);
			}
			
			Iterator<Line> lineIterator = lines.iterator();
//			int curcount = 0;
			while(lineIterator.hasNext()){
//				curcount++;
				Line line = lineIterator.next();
				int angerLevel = NConfig.level[Integer.parseInt(line.getRange2())];
				fileOperator.loadReadFile(dataInfo.getWorkingFilePath(dataInfo.EMGFILE));
				fileOperator.loadWriteFile(dataInfo.getParsedDirPath()+angerLevel+"\\"+dataInfo.getClassCount(angerLevel)+".csv");
				dataInfo.addClassCount(angerLevel);
//				fileOperator.writeToFile(line.getRange1()+";"+line.getRange2()+";");
				
				int end = line.getLine();
				int start = 0;
				if(end<=NConfig.windowLength){
					start = 0;
				}
				else{
					start = end - NConfig.windowLength + 1;
				}
				for(int n=0;n<=end;n++){
					if(n<start){
						fileOperator.nextLine();
//						continue;
					}
					else{
						String str = fileOperator.nextLine();
						fileOperator.writeToFile(str);
					}
				}
//				mainWindow.updateSimpleConsole("Extract:"+curcount+"/"+lines.size());
				fileOperator.closeIn();
				fileOperator.closeOut();
			}
			mainWindow.updateSimpleConsole("Extract finished."+dataInfo.getWorkingFilePath(dataInfo.EMGFILE));
		}

	}
	
	private class Line{
		private int line;
		private String range1;
		private String range2;
		public Line(int line, String range1, String range2) {
			super();
			this.line = line;
			this.range1 = range1;
			this.range2 = range2;
		}
		public int getLine() {
			return line;
		}
		public void setLine(int line) {
			this.line = line;
		}
		public String getRange1() {
			return range1;
		}
		public void setRange1(String range1) {
			this.range1 = range1;
		}
		public String getRange2() {
			return range2;
		}
		public void setRange2(String range2) {
			this.range2 = range2;
		}
		
	}
}