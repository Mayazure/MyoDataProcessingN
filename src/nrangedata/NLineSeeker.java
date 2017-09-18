package nrangedata;

import nconfig.NDataInfo;
import nfileoperator.NFileOperator;

public class NLineSeeker {
	
	private NFileOperator fileOperator;
	private NDataInfo dataInfo = NDataInfo.getDataInfoInstance();
	private int start = 0;
	
	public NLineSeeker(){
		this(0);
	}
	
	public NLineSeeker(int start){
		this.start = start;
		fileOperator = new NFileOperator();
	}
	
	public int seekLine(String timestamp){
		
		fileOperator.loadReadFile(dataInfo.getWorkingFilePath(dataInfo.EMGFILE));
		
		String str = null;
		int count = 0;
		int result = 0;
		long timestampBase = Long.parseLong(timestamp);
		while((str=fileOperator.nextLine())!=null){
			if(count==0||count<start){
				count++;
				continue;
			}
			else{
				String timestampString = str.substring(0,13);
				long timestampLong = Long.parseLong(timestampString);
				if(timestampLong<=timestampBase){
					result = count;
					count++;
				}
				else{
					break;
				}
			}
		}
		return result;
	}
}
