
public class WriteBack {
	
	public void WriteBack(String AluResult) {
		String readDataMemory=MemoryAccess.memoryRead;
		String controlUnit=InstructionDecode.ControlUnit;
		char regDst=controlUnit.charAt(2);
		char memtoreg=controlUnit.charAt(8);
		int writeRegisterIndex=-1;
		if(regDst=='1') {
			writeRegisterIndex=InstructionDecode.rd;
		}else {
			writeRegisterIndex=InstructionDecode.rt;
		}
		if(memtoreg=='1') {
			RegisterFile.setRegisterData(writeRegisterIndex, readDataMemory);
		}
		else {
			RegisterFile.setRegisterData(writeRegisterIndex, AluResult);
		}
		
	}
}
