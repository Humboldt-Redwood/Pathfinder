
public class SinglePath {
	
	String pathVariable;
	boolean variableOriginal;
	boolean variableDelete;
	
	// Constructor
	public SinglePath() {
		
	}
	
	// Constructor with String Argument to set pathVariable
	public SinglePath(String pathVariable, boolean variableSet) {
		this.pathVariable = pathVariable;
		this.variableOriginal = variableSet; 
		this.variableDelete = false;
	}

	
	// SETTER AND GETTER
	
	// get pathVariable
	public String getPathVariable() {
		return pathVariable;
	}

	
	// set pathVariable
	public void setPathVariable(String pathVariable) {
		this.pathVariable = pathVariable;
	}

	
	// is variable set ?
	// returns true if path is from the Set path
	public boolean isVariableOriginal() {
		return variableOriginal;
	}

	
	// set set variable 
	// true for set variable , false if variable is new
	public void setVariableOriginal(boolean variableSet) {
		this.variableOriginal = variableSet;
	}

	
	// returns if path should be deleted
	public boolean isVariableDelete() {
		return variableDelete;
	}

	
	// sets path to get deleted
	public void setVariableDelete(boolean variableDelete) {
		this.variableDelete = variableDelete;
	}

}
