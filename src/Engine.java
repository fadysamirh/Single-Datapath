import java.util.ArrayList;

public class Engine {
	public static void main(String[] args) {
		ArrayList<String> dummyData=new ArrayList<String>();
		for(int i=0;i<30;i++)
			dummyData.add("StringData"+i);
		
		
		InstructionMemory instMemory=new InstructionMemory();
		ArrayList<String>instructions=new ArrayList<String>();
		//sub
		
		//instructions.add("00000000011000100001100000100010");
		instructions.add("10001100001000000000000000000100");
		RegisterFile registerfile=new RegisterFile();
		registerfile.arrRegister[1]=Integer.toBinaryString(0);
		registerfile.arrRegister[3]=Integer.toBinaryString(2);
		registerfile.arrRegister[2]=Integer.toBinaryString(10);
		instMemory.setInstructions(instructions);
		
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
		writeBack.WriteBack(ALUresult);
		
		
	}
}
