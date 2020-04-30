
public class Execute {
	static boolean zeroFlag;
	static String result = "";
	static String BranchAddress="";

	public String Execute(String AluOp, char ALUSrc, String ReadData1, String ReadData2, String immediate) {
		System.out.println("Starting Excuting ..");
		System.out.println("The input AluOp is "+ AluOp);
		System.out.println("The input ALUSrc is "+ ALUSrc);
		System.out.println("The input ReadData1 is "+ ReadData1);
		System.out.println("The input ReadData2 is "+ ReadData2);
		System.out.println("The input SignExtend is "+ immediate);
		System.out.println("The input PC is "+ InstructionMemory.programCounter);
		
		String funct = InstructionFetch.currentInstruction.substring(26, 32);
		String Op = "";
		int Operand1 =(int) Long.parseLong(ReadData1, 2);
		int Operand2 = -1;

		if (ALUSrc == '1') {
			Operand2 =(int) Long.parseLong(immediate, 2);
		} else {
			Operand2 =(int) Long.parseLong(ReadData2, 2);
		}

		if (AluOp.equals("00")) {
			Op = "0010";
		} else if (AluOp.equals("01")) {
			Op = "0110";
			BranchAddress=ReadData2;
			System.out.println("The output BranchAddress is "+ ReadData2);
		} else if (AluOp.equals("10")) {
			if (funct.equals("100000")) {
				Op = "0010";
			} else if (funct.equals("100010")) {
				Op = "0110";
			} else if (funct.equals("100100")) {
				Op = "0000";
			} else if (funct.equals("100101")) {
				Op = "0001";
			} else if (funct.equals("101010")) {
				Op = "0111";
			}
		}

		if (Op.equals("0000")) {
			System.out.println("The operation name is AND");
			System.out.println("The first operand is " + Operand1);
			System.out.println("The second operand is " + Operand2);

			System.out.println("The output is " + (Operand1 & Operand2));

			result = toBinary(Operand1 & Operand2);

			if ((Operand1 & Operand2) == 0) {
				zeroFlag = true;
				System.out.println("The zero flag is set to true");
			} else {
				zeroFlag = false;
				System.out.println("The zero flag is set to false");

			}

			System.out.println("AND Operation Done!");

		} else if (Op.equals("0001")) {
			System.out.println("The operation name is OR");
			System.out.println("The first operand is " + Operand1);
			System.out.println("The second operand is " + Operand2);
			System.out.println("The output is " + (Operand1 | Operand2));

			result = toBinary(Operand1 | Operand2);

			if ((Operand1 | Operand2) == 0) {
				zeroFlag = true;
				System.out.println("The zero flag is set to true");
			} else {
				zeroFlag = false;
				System.out.println("The zero flag is set to false");

			}
			System.out.println("OR Operation Done!");

		} else if (Op.equals("0010")) {
			System.out.println("The operation name is ADD");
			System.out.println("The first operand is " + Operand1);
			System.out.println("The second operand is " + Operand2);

			System.out.println("The output is " + (Operand1 + Operand2));

			result = toBinary(Operand1 + Operand2);

			if ((Operand1 + Operand2) == 0) {
				zeroFlag = true;
				System.out.println("The zero flag is set to true");
			} else {
				zeroFlag = false;
				System.out.println("The zero flag is set to false");

			}
			System.out.println("ADD Operation Done!");

		} else if (Op.equals("0110")) {
			System.out.println("The operation name is SUB");
			System.out.println("The first operand is " + Operand1);
			System.out.println("The second operand is " + Operand2);
			System.out.println("The output is " + (Operand1 - Operand2));
			result = toBinary(Operand1 - Operand2);
			if ((Operand1 - Operand2) == 0) {
				zeroFlag = true;
				System.out.println("The zero flag is set to true");
				if (AluOp.equals("01")) {
					InstructionFetch.ProgCounter(ReadData2);
					
				}
			} else {
				zeroFlag = false;
				System.out.println("The zero flag is set to false");

			}
			System.out.println("SUB Operation Done!");

		} else if (Op.equals("0111")) {
			System.out.println("The operation name is SLT");
			System.out.println("The first operand is " + Operand1);
			System.out.println("The second operand is " + Operand2);
			System.out.print("The output is ");
			if ((Operand1 < Operand2)) {
				System.out.println("1");
				result = toBinary(1);
			} else {
				System.out.println("0");
				result = toBinary(0);

			}

			if ((Operand1 >= Operand2) == true) {
				zeroFlag = true;
				System.out.println("The zero flag is set to true");
			} else {
				zeroFlag = false;
				System.out.println("The zero flag is set to false");

			}
			System.out.println("SLT Operation Done!");

		} else if (Op.equals("1100")) {
			System.out.println("The operation name is NOR");
			System.out.println("The first operand is " + Operand1);
			System.out.println("The second operand is " + Operand2);
			System.out.println("The output is " + ~(Operand1 | Operand2));

			result = toBinary(~(Operand1 | Operand2));

			if (~(Operand1 | Operand2) == 0) {
				zeroFlag = true;
				System.out.println("The zero flag is set to true");
			} else {
				zeroFlag = false;
				System.out.println("The zero flag is set to false");

			}

			System.out.println("NOR Operation Done!");

		}
		System.out.println("The output ALUresult is " + result);
		System.out.println("The output ReadData2 is "+ ReadData2);
		System.out.println("The output PC is " + InstructionMemory.programCounter);
		System.out.println("End of Executing..");
		for(int i=result.length();i<32;i++){
			result=0+result;
		}
		return result;
	}

	public static String toBinary(int integer) {
		String binaryString = Integer.toBinaryString(integer);
		for (int i = binaryString.length(); i < 32; i++)
			binaryString = "0" + binaryString;
		return binaryString;
	}

}
