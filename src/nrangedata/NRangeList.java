package nrangedata;

import java.util.ArrayList;

import nfileoperator.NFileOperator;


public class NRangeList {

	private NFileOperator fileOperator;
	private ArrayList<NRangeInfo> rangeInfos;
	private String filePath;
	private NRangeParser rangeParser;

	public NRangeList(String filePath){
		this.filePath = filePath;
		fileOperator = new NFileOperator();
		rangeParser = new NRangeParser();
		generateRangeList();
	}

	private void generateRangeList(){
		rangeInfos = new ArrayList<NRangeInfo>();
		fileOperator.loadReadFile(filePath);
		String line;

		while((line=fileOperator.nextLine())!=null){
			rangeInfos.add(rangeParser.NParseRange(line));
		}
	}

	public ArrayList<NRangeInfo> getRangeList(){
		return rangeInfos;
	}

}
