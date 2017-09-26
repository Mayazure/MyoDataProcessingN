package test;

import nconfig.NConfig;

public class GeneralTest {

	public static void main(String[] args) {
		//test1();
		test2();
	}
	
	private static void test1(){
		for(int i = 0;i<NConfig.level.length;i++){
			System.out.println(i+": "+NConfig.level[i]);
		}
	}
	
	private static void test2() {
		String in = "441879,2017-07-18 12:35:35,1500345330558,1500345335997,82,29,0";
		String out = in.substring(0,in.indexOf(","));
		String last = in.substring(in.length()-1);
		System.out.println(out);
		System.out.println(last);
	}
}
