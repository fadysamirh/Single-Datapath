import java.util.ArrayList;

public class MemoryAccess {
	static ArrayList<String> dataMemory= new ArrayList<String>();
	
	static String memoryRead;
	 	
	public void MemAccess(String ALUresult, String ReadData2, char MemWrite,char MemRead ) {
		if(MemWrite=='1') {
			dataMemory.set(Integer.parseInt(ALUresult,2), ReadData2);
		}
		else if (MemRead=='1') {
			memoryRead=dataMemory.get(Integer.parseInt(ALUresult,2));
			System.out.println(memoryRead);
		}
	}
	
}
