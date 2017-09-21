package test;

import nrangedata.NLineInfo;
import nrangedata.NRangeInfo;
import nrangedata.NRangeParser;

public class TestRangeParser {
	
	private static NRangeParser rangeParser = new NRangeParser();
	
	public static void main(String[] args) {
		testParseOldRange();
	}
	
	private static void testParseRange(){
		NRangeInfo rangeInfo = rangeParser.parseRange("2017-08-29 14:52:30:100> $R1=15$R2=14<x");
		System.out.println(rangeInfo.getTimestamp()+";"+rangeInfo.getRange1()+";"+rangeInfo.getRange2()+";"+rangeInfo.getLevel());
	}
	
	private static void testParseOldRange(){
		String line = "2017-08-29 14:52:11> $T1=1503982327357$T2=1503982331287$R1=12$R2=20"; 
		String line1 = "2017-08-29 14:43:27> TEST:$T1=1503981796898$T2=1503981807141$R1=17$R2=30";
		NLineInfo lineInfo = rangeParser.parseOldRange(line);
		NLineInfo lineInfo1 = rangeParser.parseOldRange(line1);
		System.out.println(lineInfo.toText());
		System.out.println(lineInfo1.toText());
	}

}