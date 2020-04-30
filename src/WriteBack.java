
public class WriteBack {
	
	public void WriteBack(String AluResult) {
		System.out.println("Starting WriteBack..");
		String controlUnit=InstructionDecode.ControlUnit;

		char regWrite=controlUnit.charAt(4);
		System.out.println("The regWrite is "+ regWrite);

		if(regWrite=='1') {

		System.out.println("The input AluResult is "+ AluResult);
		System.out.println("The input ReadData [from data memory] is "+ MemoryAccess.memoryRead);
		String readDataMemory=MemoryAccess.memoryRead;
		char regDst=controlUnit.charAt(2);
		char memtoreg=controlUnit.charAt(8);
		int writeRegisterIndex=-1;
		System.out.println("The input MemToReg is "+memtoreg );
		System.out.println("The input RegDst is "+regDst );
		if(regDst=='1') {
			writeRegisterIndex=InstructionDecode.rd;
		}else {
			writeRegisterIndex=InstructionDecode.rt;
		}
		
		if(memtoreg=='1') {
			RegisterFile.setRegisterData(writeRegisterIndex, readDataMemory);
			System.out.println("THe output WriteRegisterData is "+ readDataMemory+" at index(int) "+ writeRegisterIndex);
		}
		else {
			RegisterFile.setRegisterData(writeRegisterIndex, AluResult);
			System.out.println("THe output WriteRegisterData is "+ AluResult+" at index(int) "+ writeRegisterIndex);

		}}
		
		System.out.println("Ending WriteBack..");
	}
}
