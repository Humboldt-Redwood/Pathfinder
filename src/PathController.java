import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;


public class PathController {
	
	private final String pathVariable;
	List<SinglePath> singlePathList; 
	
	// Constructor
	public PathController() {
		
		pathVariable = System.getenv("PATH");  
		singlePathList = createSingleOriginalPathsList();
		System.out.println("Original PATH at start: "+pathVariable);
	}

	
	// get the Original Path variable String
	public String getPathVariable() {
		return pathVariable;
	}

	
	// get the Single Path List
	public List<SinglePath> getSinglePathList() {
		return singlePathList;
	}

	
	// Add a new path to the Single Path List
	public void addNewToSinglePathList(String path) {
		SinglePath singlePathNew = new SinglePath(path, false);
		singlePathList.add(singlePathNew);
	}
	
	
	/*
	 * * create a SinglePath object list with original PATH string
	 * * cut string at every ";"
	 */
	public List<SinglePath> createSingleOriginalPathsList() {
		
		List<SinglePath> singlePathList = new ArrayList<SinglePath>(); 
		
		String[] splitResult = pathVariable.split(";");
		
		for (int i=0; i<splitResult.length; i++) {
			
			SinglePath singlePath = new SinglePath(splitResult[i],true);
			singlePathList.add(singlePath);			
		}	
		return singlePathList;
	}

	
	
	public DefaultListModel getAllListModel() {
		
		DefaultListModel defaultListModel = new DefaultListModel();
		 
		 for (SinglePath current: singlePathList) {	
			
				 defaultListModel.addElement(current.getPathVariable());
		 }	
		return defaultListModel;
	}
	
	
	public DefaultListModel getOriginalListModel() {
		
		DefaultListModel defaultListModel = new DefaultListModel();
		 
		 for (SinglePath current: singlePathList) {	
			 
			 if (current.isVariableOriginal() == true) {
				 defaultListModel.addElement(current.getPathVariable());
			 }
		 }	
		return defaultListModel;
	}
	
	
	public DefaultListModel getNewListModel() {
		
		DefaultListModel defaultListModel = new DefaultListModel();
		 
		 for (SinglePath current: singlePathList) {	
			 
			 if (current.isVariableOriginal() == false) {
				 defaultListModel.addElement(current.getPathVariable());
			 }
		 }	
		return defaultListModel;
	}
	
	
	public DefaultListModel getDeleteListModel() {
		
		DefaultListModel defaultListModel = new DefaultListModel();
		 
		 for (SinglePath current: singlePathList) {	
			 
			 if (current.isVariableDelete() == true) {
				 defaultListModel.addElement(current.getPathVariable());
			 }
		 }	
		return defaultListModel;
	}
	
	
	
	public void updateSinglePath(String pathName) {
		
		for (SinglePath current: singlePathList) {	
			 
			 if (current.getPathVariable() == pathName) {
				 current.setVariableDelete(true);
				 System.out.println("Set -> "+current.getPathVariable()+" <- ready to delete");
			 }
		 }	
		
		
		
	}
	
	
	
	
	
}
