import java.lang.reflect.Array;
import java.util.ArrayList;

public class InstructionDecode {
	static String readData1;
	static String readData2;
	static String ControlUnit = "XXXXXXXXX";
	static String signExtend = "";
	static int read2Index = -1;
	static int rs;
	static int rd;
	static int rt;
	
	public void InstDecode(String instruction, String PC, RegisterFile registerFile) {
		System.out.println("Starting decoding now..");
		System.out.println("The input instruction is "+instruction);
		System.out.println("The input PC is " +PC);
		String opCode = instruction.substring(0, 6);

		if (opCode.equals("000000")) {
			System.out.println("The instruction type is R-type");
			String function = instruction.substring(26, 32);
			ContUnit(opCode);

			if (function.equals("100000")) {
				System.out.println("This is an add instruction");

			} else if (function.equals("100010")) {
				System.out.println("This is a sub instruction");
			}

		} else if (opCode.equals("100011")) {
			System.out.println("This is a load word instruction");

			ContUnit(opCode);

		} else if (opCode.equals("101011")) {
			System.out.println("This is a store word instruction");

			ContUnit(opCode);

		} else if (opCode.equals("000100")) {
			System.out.println("The instruction type is I-type");
			System.out.println("This is a beq instruction");

			ContUnit(opCode);

		} else if (opCode.equals("000010")) {

			ContUnit(opCode);

		}

		 rs = (int) Long.parseLong(instruction.substring(6, 11), 2);
		 rt = (int) Long.parseLong(instruction.substring(11, 16), 2);
		 rd = (int) Long.parseLong(instruction.substring(16, 21), 2);

		readData1 = registerFile.getRegisterData(rs);

		if (ControlUnit.charAt(3) == '0') {
			readData2 = registerFile.getRegisterData(rt);
			read2Index = rt;
		} else if (ControlUnit.charAt(3) == '1') {
			readData2 = registerFile.getRegisterData(rd);
			read2Index = rd;

		}
		System.out.println("Reading from the registerRead1 at index " + rs + " with the data value "
				+ (int) Long.parseLong(readData1, 2));

		if (read2Index != -1)
			System.out.println("Reading from the registerRead2 at index " + read2Index + " with the data value "
					+ (int) Long.parseLong(readData2, 2));

		String immediate = instruction.substring(16, 32);

		signExtend = SignExtend(immediate);
		System.out.println("The PC is " + InstructionMemory.programCounter);
		System.out.println("End of decoding");
	}

	public void ContUnit(String opCode) {
		if (opCode.equals("000000")) {
			// System.out.println("The instruction type is R-type");
			// ALU OP(2) RegDst AluSrc RegWrite MemRead MemWrite Branch Mem-to-Reg
			ControlUnit = "101010000";

		} else if (opCode.equals("100011")) {
			// System.out.println("This is a load word instruction");
			// ALU OP(2) RegDst AluSrc RegWrite MemRead MemWrite Branch Mem-to-Reg
			ControlUnit = "000111001";

		} else if (opCode.equals("101011")) {
			// System.out.println("This is a store word instruction");
			// ALU OP(2) RegDst AluSrc RegWrite MemRead MemWrite Branch Mem-to-Reg
			ControlUnit = "00X10010X";

		} else if (opCode.equals("000100")) {
			// System.out.println("The instruction type is I-type");
			// ALU OP(2) RegDst AluSrc RegWrite MemRead MemWrite Branch Mem-to-Reg
			ControlUnit = "01X00001X";

		} else {
			// ALU OP(2) RegDst AluSrc RegWrite MemRead MemWrite Branch Mem-to-Reg
			ControlUnit = "XXXXXXXX";
		}
		System.out.println("The output Control Signal");
		System.out.print("AluOp: " + ControlUnit.charAt(0) + ControlUnit.charAt(1));
		System.out.print(" | ");
		System.out.print("RegDst: " + ControlUnit.charAt(2));
		System.out.print(" | ");
		System.out.print("AluSrc: " + ControlUnit.charAt(3));
		System.out.print(" | ");
		System.out.print("RegWrite: " + ControlUnit.charAt(4));
		System.out.print(" | ");
		System.out.print("MemRead: " + ControlUnit.charAt(5));
		System.out.print(" | ");
		System.out.print("MemWrite: " + ControlUnit.charAt(6));
		System.out.print(" | ");
		System.out.print("Branch: " + ControlUnit.charAt(7));
		System.out.print(" | ");
		System.out.print("Mem-To-Reg: " + ControlUnit.charAt(8));

	}

	public String SignExtend(String immediate) {
		System.out.println("Immediate before sign extend: " + immediate);
		if (immediate.charAt(0) == '0') {
			System.out.println("Immediate after sign extend: " + "0000000000000000" + immediate);

			return "0000000000000000" + immediate;
		} else {
			System.out.println("Immediate after sign extend: " + "1111111111111111" + immediate);
			return "1111111111111111" + immediate;
		}
	}

}
