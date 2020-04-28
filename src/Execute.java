
public class Execute {
	static boolean zeroFlag;
	static String  result="";
	public String Execute(String AluOp, char ALUSrc, String ReadData1, String ReadData2, String immediate) {
		String funct=InstructionFetch.currentInstruction.substring(26, 32);
		String Op = null;
		int Operand1=Integer.parseInt(ReadData1,2);
		int Operand2=-1;
		
		if(ALUSrc=='1') {
			Operand2=Integer.parseInt(immediate,2);
		}else  {
			Operand2=Integer.parseInt(ReadData2,2);
		}
		
		if(AluOp.equals("00")) {
			Op="0010";
		}
		else if(AluOp.equals("01")) {
			Op="0110";
		}
		else if(AluOp.equals("10")){
			if(funct.equals("100000")) {
				Op="0010";
			}
			else if(funct.equals("100010")) {
				Op="0110";
			}else if(funct.equals("100100")) {
				Op="0000";
			}else if(funct.equals("100101")) {
				Op="0001";
			}else if(funct.equals("101010")) {
				Op="0111";
			}
		}
		
		
		
		if (Op.equals("0000")) {
			System.out.println("The operation name is AND");
			System.out.println("The first operand is " + Operand1);
			System.out.println("The second operand is " + Operand2);

			System.out.println("The output is " + (Operand1 & Operand2));
			
			result=toBinary(Operand1 & Operand2);
			
			if ((Operand1 & Operand2) == 0) {
				zeroFlag=true;
				System.out.println("The zero flag is set to true");
			}else {
				zeroFlag=false;
				System.out.println("The zero flag is set to false");

			}
			
			System.out.println("AND Operation Done!");

		} else if (Op.equals("0001")) {
			System.out.println("The operation name is OR");
			System.out.println("The first operand is " + Operand1);
			System.out.println("The second operand is " + Operand2);
			System.out.println("The output is " + (Operand1 | Operand2));
			
			result=toBinary(Operand1 | Operand2);
			
			if ((Operand1 | Operand2) == 0) {
				zeroFlag=true;
				System.out.println("The zero flag is set to true");
			}else {
				zeroFlag=false;
				System.out.println("The zero flag is set to false");

			}
			System.out.println("OR Operation Done!");

		} else if (Op.equals("0010")) {
			System.out.println("The operation name is ADD");
			System.out.println("The first operand is " + Operand1);
			System.out.println("The second operand is " + Operand2);

			System.out.println("The output is " + (Operand1 + Operand2));
			
			result=toBinary(Operand1 + Operand2);
			
			if ((Operand1 + Operand2) == 0) {
				zeroFlag=true;
				System.out.println("The zero flag is set to true");
			}else {
				zeroFlag=false;
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
				zeroFlag=true;
				System.out.println("The zero flag is set to true");
			}else {
				zeroFlag=false;
				System.out.println("The zero flag is set to false");

			}
			System.out.println("SUB Operation Done!");

		} else if (Op.equals("0111")) {
			System.out.println("The operation name is SLT");
			System.out.println("The first operand is " + Operand1);
			System.out.println("The second operand is " + Operand2);
			System.out.print("The output is ");
			if((Operand1 < Operand2)) {
				System.out.println("1");
				result=toBinary(1);
			}
			else {
				System.out.println("0");
				result=toBinary(0);

			}

			if ((Operand1 >= Operand2) == true) {
				zeroFlag=true;
				System.out.println("The zero flag is set to true");
			}else {
				zeroFlag=false;
				System.out.println("The zero flag is set to false");

			}
			System.out.println("SLT Operation Done!");

		} else if (Op.equals("1100")) {
			System.out.println("The operation name is NOR");
			System.out.println("The first operand is " + Operand1);
			System.out.println("The second operand is " + Operand2);
			System.out.println("The output is " + ~(Operand1 | Operand2));
			
			result=toBinary(~(Operand1 | Operand2));

			if (~(Operand1 | Operand2) == 0) {
				zeroFlag=true;
				System.out.println("The zero flag is set to true");
			}else {
				zeroFlag=false;
				System.out.println("The zero flag is set to false");

			}
			
			System.out.println("NOR Operation Done!");

		}
		return result;
	}
	public static String toBinary(int integer) {
		String binaryString=Integer.toBinaryString(integer);
		for(int i=binaryString.length();i<32;i++)
			binaryString="0"+binaryString;
		return binaryString;
	}

}