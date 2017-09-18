package test;

import nrangedata.NRangeInfo;
import nrangedata.NRangeParser;

public class TestRangeParser {
	
	public static void main(String[] args) {
		NRangeParser rangeParser = new NRangeParser();
		
		NRangeInfo rangeInfo = rangeParser.NParseRange("2017-08-29 14:52:30:100> $R1=15$R2=14<x");
		System.out.println(rangeInfo.getTimestamp()+";"+rangeInfo.getRange1()+";"+rangeInfo.getRange2()+";"+rangeInfo.getLevel());
		
	}

}