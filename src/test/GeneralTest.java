package test;

import nconfig.NConfig;

public class GeneralTest {

	public static void main(String[] args) {
		
		for(int i = 0;i<NConfig.level.length;i++){
			System.out.println(i+": "+NConfig.level[i]);
		}
	}
}
