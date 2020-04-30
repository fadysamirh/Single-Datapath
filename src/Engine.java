import java.util.ArrayList;

public class Engine {
	public static void main(String[] args) {
		ArrayList<String> dummyData=new ArrayList<String>();
		RegisterFile registerfile=new RegisterFile();

		for(int i=0;i<32;i++) {
			String x=Integer.toBinaryString(i);
			for(int j=x.length();j<32;j++)
				x="0"+x;
			dummyData.add(x);
			registerfile.arrRegister[i]=x;
			
			
			
			}
		
		
		InstructionMemory instMemory=new InstructionMemory();
		ArrayList<String>instructions=new ArrayList<String>();
		//sub
		
		instructions.add("00000001100010010101100000100000"); // add $11,$12,$9
		instructions.add("00000001100010010101100000100101"); // OR $11,$12,$9
		instructions.add("00000001100011010101100000100010"); // SUB $11,$12,$9
		instructions.add("00000001100010010101100000100100"); // AND $11,$12,$9
		instructions.add("00000001100010010101100000101010"); // Set-on-less-than $11,$12,$9
		instructions.add("10101100001000100000000000000101"); // store
 		instructions.add("10001101010010000000000000000010");//lw		
		instructions.add("00010001000010000000000000000101"); //beq

		
		instMemory.setInstructions(instructions);
		
		while(Integer.parseInt(InstructionMemory.programCounter,2)<InstructionMemory.instructions.size()) {
		InstructionFetch instFetch = new InstructionFetch();
		instFetch.InstFetch(null);
		
		InstructionDecode instDecode = new InstructionDecode();
		instDecode.InstDecode(instFetch.currentInstruction, instMemory.programCounter, registerfile);
		
		Execute alu = new Execute();
		String AluOp=InstructionDecode.ControlUnit.substring(0,2);
		char AluSrc=InstructionDecode.ControlUnit.charAt(3);
		
		String ALUresult=alu.Execute(AluOp, AluSrc, instDecode.readData1, instDecode.readData2, instDecode.signExtend);
		
		MemoryAccess memAccess = new MemoryAccess();
		memAccess.dataMemory=dummyData;
		
		memAccess.MemAccess(ALUresult, instDecode.readData2, InstructionDecode.ControlUnit.charAt(6), InstructionDecode.ControlUnit.charAt(5));
		WriteBack writeBack = new WriteBack();
		writeBack.WriteBack(ALUresult);}
		
		
	}
}
