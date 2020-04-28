import java.util.ArrayList;

public class InstructionMemory {
	static String programCounter;
	private static ArrayList<String> instructions;
	
	public InstructionMemory() {
		programCounter="00000000000000000000000000000000";
		instructions=new ArrayList<String>();
	}
	public void setInstructions(ArrayList<String> instructions) {
		for(int i=0;i<instructions.size();i++)
			this.instructions.add(instructions.get(i));
	}
	
	public static ArrayList<String> getInstructions(){
		return instructions;
	}

}
