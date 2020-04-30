import java.util.ArrayList;

public class InstructionFetch {
	  

	static String currentInstruction="";
	
	public String InstFetch( String branchPC) {
		System.out.println("Starting Fetching..");
		System.out.println("PC Address input is " +InstructionMemory.programCounter);
		
		int intAddress= Integer.parseInt(InstructionMemory.programCounter,2);
		
		String returnedInstruction= InstructionMemory.getInstructions().get(intAddress);
		currentInstruction=returnedInstruction;
		ProgCounter(branchPC);
		
		System.out.println("The fetched instruction is "+ returnedInstruction);
		System.out.println("PC Address output is " +InstructionMemory.programCounter);
		System.out.println("End of fetching..");
		return returnedInstruction;
	}
	
	public static void ProgCounter(String branchPC) {
		String controlUnit=InstructionDecode.ControlUnit;
		if(controlUnit.charAt(0)=='0'&&controlUnit.charAt(1)=='1'&&controlUnit.charAt(7)=='1') {
			
			Long pc=Long.parseLong(InstructionMemory.programCounter,2);
			Long branch=Long.parseLong(branchPC,2);
			pc+=branch;
			InstructionMemory.programCounter=Long.toBinaryString(pc);
			for(int i= InstructionMemory.programCounter.length();i<32;i++) {
				InstructionMemory.programCounter="0"+InstructionMemory.programCounter;
			}
			
		}
		else {
			int pc=Integer.parseInt(InstructionMemory.programCounter,2);
			pc+=1;
			InstructionMemory.programCounter=Integer.toBinaryString(pc);
			
			for(int i= InstructionMemory.programCounter.length();i<32;i++) {
				InstructionMemory.programCounter="0"+InstructionMemory.programCounter;
			}
		}
	}

	
}
