import java.util.ArrayList;

public class MemoryAccess {
	static ArrayList<String> dataMemory = new ArrayList<String>();

	static String memoryRead;

	public void MemAccess(String ALUresult, String ReadData2, char MemWrite, char MemRead) {
		System.out.println("Starting MemoryAccess..");

		System.out.println("The input ALUresult is " + ALUresult);
		System.out.println("The input Branch is " + InstructionDecode.ControlUnit.charAt(7));
		if (InstructionDecode.ControlUnit.charAt(7) == '1')
			System.out.println("The input BranchAddress is " + Execute.BranchAddress);
		System.out.println("The input zero flag is " + Execute.zeroFlag);
		System.out.println("The input ReadData2 is " + ReadData2);
		System.out.println("The input SignExtend is " + InstructionDecode.signExtend);
		System.out.println("The input MemWrite is " + MemWrite);
		System.out.println("The input MemRead is " + MemRead);

		if (MemWrite == '1') {
			dataMemory.set((int) Long.parseLong(ALUresult, 2), ReadData2);
			if ((int) Long.parseLong(ALUresult, 2) == 0) {
				System.out.println("You can't write in register 0");
			} else
				System.out.println("Writing in the data memory at index " + (int) Long.parseLong(ALUresult, 2)
						+ " with data value " + ReadData2);
		} else if (MemRead == '1') {
			memoryRead = dataMemory.get((int) Long.parseLong(ALUresult, 2));
			System.out.println("Reading in the data memory at index " + (int) Long.parseLong(ALUresult, 2)
					+ " with data value " + memoryRead);
		}

		System.out.println("The output AluResult is " + ALUresult);
		System.out.println("The output ReadData2 is " + ReadData2);

		System.out.println("End MemoryAccess..");
	}

}
