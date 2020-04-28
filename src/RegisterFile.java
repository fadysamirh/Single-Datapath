
public class RegisterFile {
	static String [] arrRegister;

	
	static boolean  writeFlag;
	
	public RegisterFile() {
		arrRegister=new String[32];
		arrRegister[0]= new String("00000000000000000000000000000000");
		
	}
	
	public void setWriteFlag(boolean value) {
		writeFlag=value;
	}
	
	public String getRegisterData(int address) {
		return arrRegister[address];
	}

}
