package nrangedata;

import java.util.ArrayList;

import nfileoperator.NFileOperator;


public class NRangeList {

	private NFileOperator fileOperator;
	private ArrayList<NRangeInfo> rangeInfos;
	private ArrayList<NLineInfo> lineInfos;
	private ArrayList<NRangeInfoWithLine> rangeInfoWithLines;
	private String filePath;
	private NRangeParser rangeParser;
	
	public static int RANGE_ORIGINAL = 0;
	public static int RANGE_MODIFIED = 1;
	public static int RANGE_MODIFIED_NEW = 2;

	public NRangeList(String filePath, int type){
		this.filePath = filePath;
		fileOperator = new NFileOperator();
		rangeParser = new NRangeParser();
		switch (type) {
		case 0:
			generateLineInfoList();
			break;
		case 1:
			generateRangeList();
			break;
		case 2:
			generateRangeListWithLine();
			break;
		default:
			break;
		}
	}

	private void generateRangeList(){
		rangeInfos = new ArrayList<NRangeInfo>();
		fileOperator.loadReadFile(filePath);
		String line;

		while((line=fileOperator.nextLine())!=null){
			rangeInfos.add(rangeParser.parseRange(line));
		}
	}
	
	private void generateRangeListWithLine(){
		rangeInfoWithLines = new ArrayList<NRangeInfoWithLine>();
		fileOperator.loadReadFile(filePath);
		String line;
		
		while((line=fileOperator.nextLine())!=null){
			if(line.equals(""))continue;
			rangeInfoWithLines.add(rangeParser.parseRangeWithLine(line));
		}
	}
	
	private void generateLineInfoList(){
		lineInfos = new ArrayList<NLineInfo>();
		fileOperator.loadReadFile(filePath);
		String line;

		while((line=fileOperator.nextLine())!=null){
			if(line.contains("start")||line.contains("Total")){
				continue;
			}
			else if(!line.equals("")){
				lineInfos.add(rangeParser.parseOldRange(line));
			}
		}
	}

	public ArrayList<NRangeInfo> getRangeList(){
		return rangeInfos;
	}
	
	public ArrayList<NLineInfo> getLineInfoList(){
		return lineInfos;
	}
	
	public ArrayList<NRangeInfoWithLine> getRangeInfoWithLines() {
		return rangeInfoWithLines;
	}
}
