/********************************************************************
* PATHGUI  		                                                    *
*                                                                   *
* Author: Humboldt-Redwood                                          *
*                                                                   *
* Last Modified: 2013 june 30                                       *
*                                                                   *
* Version: 0.1              										*
*                                                                   *
*  TODO:                                                            *
*  cleanButton                                                      *
*  colors                                                           *
*                                                                   *
*********************************************************************/
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.*;


public class PathGUI extends JFrame {
	
	private JPanel contentPanel;
	private JPanel buttonActionPanel;
	private JPanel buttonViewPanel;
	
	PathController pathController;
	JScrollPane scrollPanel;
	JButton buttonAddPath;
	JButton buttonSelectPath;
	JButton buttonShowAll;
	JButton buttonShowClear;
	JButton buttonShowOriginal;
	JButton buttonShowNew;
	JButton buttonShowDelete;
	JList pathList;
	JLabel showLabel;
	JLabel showLabelListState;
	
	String state;
	
	ActionListener buttonListener;

	
	public PathGUI() {
		super("Pathfinder");
		
		// Set size and location of frame , handle close
		setSize(500,550);
        setLocation(700,100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // create new PathController 
     	pathController = new PathController();
        
     	// create new JPannel and set Layout to Boarder
        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
    
        pathList = new JList();
        pathList.setModel(pathController.getAllListModel());
		scrollPanel = new JScrollPane(pathList);
		
		
		// create the Button Listener
		buttonListener = createActionListener();
		
		
		// create Buttons
		buttonAddPath = new JButton("Add Path");	
		buttonSelectPath = new JButton("Select Path");	
		buttonShowAll = new JButton("All");
		buttonShowOriginal = new JButton("Original");	
		buttonShowNew = new JButton("New");
		buttonShowDelete = new JButton("Delete");	
		
		
		// conect buttons with Action Listener
		buttonAddPath.addActionListener(buttonListener);
		buttonSelectPath.addActionListener(buttonListener);
		buttonShowAll.addActionListener(buttonListener);
		buttonShowOriginal.addActionListener(buttonListener);
		buttonShowNew.addActionListener(buttonListener);
		buttonShowDelete.addActionListener(buttonListener);
		
		
		// create Button Action Panel , add search and Add Path Button
		buttonActionPanel = new JPanel();
		buttonActionPanel.add(buttonAddPath);
		buttonActionPanel.add(buttonSelectPath);
		
	
		// create Labels for Button View Pannel 
		showLabelListState = new JLabel("All");		
		showLabel = new JLabel("Show ");
		
		
		// create Button View Pannel , add Label and View Buttons
		buttonViewPanel = new JPanel();
		buttonViewPanel.add(showLabel);
		buttonViewPanel.add(showLabelListState);
		buttonViewPanel.add(buttonShowAll);
		buttonViewPanel.add(buttonShowOriginal);
		buttonViewPanel.add(buttonShowNew);
		buttonViewPanel.add(buttonShowDelete);
		

		// setting Label, Tabbed Pannel and Button to the Borderlayout of Content Panel 
		contentPanel.add(scrollPanel, BorderLayout.CENTER);
		contentPanel.add(buttonActionPanel, BorderLayout.NORTH);
		contentPanel.add(buttonViewPanel, BorderLayout.SOUTH);
		
		
		// set Content Panel to Frame and make it visible
        setContentPane(contentPanel);
        setVisible(true);
        
	}
	
	
	/*
	 * Action Listener for the Buttons
	 */
	public ActionListener createActionListener() {
		
		ActionListener newButtonListener = new ActionListener(){
			  
			@Override
			public void actionPerformed(ActionEvent event) {
				
				if (event.getSource() == buttonAddPath) {
					addPathButton();
				} else if (event.getSource() == buttonSelectPath) {
					pressedSelectPathButton();
				} else if (event.getSource() == buttonShowAll) {
					pressedShowAllButton();
				} else if (event.getSource() == buttonShowOriginal) {
					pressedShowOriginalButton();
				} else if (event.getSource() == buttonShowNew) {
					pressedShowNewButton();
				} else if (event.getSource() == buttonShowDelete) {
					pressedShowDeleteButton();
				} else {
					System.out.println("Pressed: "+event.getSource());
				}
					
			}
		};
		return newButtonListener;
	}
	
	
	/*
	 * Actions for the Buttons
	 */
	public void pressedSelectPathButton() {
		
		String selectedString = null;
	
		try {
			selectedString = pathList.getSelectedValue().toString();
		} catch (Exception e) {	   
			System.out.println("no path selected");
		}	
		
		if (selectedString != null) {
			pathController.updateSinglePath(selectedString);
		}
		
	}
	
	
	public void pressedShowAllButton() {
		
		showLabelListState.setText("All");
		pathList.setModel(pathController.getAllListModel());
		System.out.println("Pressed All Button");
	}
	
	
	public void pressedShowOriginalButton() {
		
		showLabelListState.setText("Original");
		pathList.setModel(pathController.getOriginalListModel());
		System.out.println("Pressed Original Button");
		
	}
	
	
	public void pressedShowNewButton() {

		showLabelListState.setText("New");
		pathList.setModel(pathController.getNewListModel());
		System.out.println("Pressed New Button");
	}
	
	
	public void pressedShowDeleteButton() {
		
		showLabelListState.setText("Delete");
		pathList.setModel(pathController.getDeleteListModel());
		System.out.println("Pressed Delete Button");
	}
	
	
	
	/*
	public DefaultListModel createListModel(List<SinglePath> singlePaths) {
		
		DefaultListModel defaultListModel = new DefaultListModel();
		 
		 for (SinglePath current: singlePaths) {	
			 defaultListModel.addElement(current.getPathVariable());
			}	
		return defaultListModel;
	}
	*/
	
	
	
	
	// define actions of Add Path Button
	public void addPathButton() {
		
		// create JFileChoose
        JFileChooser chooser = new JFileChooser();
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        // open File Open Dialog 

        String newPath = null;
        
        int result = chooser.showOpenDialog(null);
        
        // Abfrage, ob auf "Öffnen" geklickt wurde 
        if(result == JFileChooser.APPROVE_OPTION) {
        	// Ausgabe der ausgewaehlten Datei
        	newPath = chooser.getSelectedFile().getAbsolutePath();
        }
        if (newPath != null) {
        	pathController.addNewToSinglePathList(newPath);
        	System.out.println("Added: "+newPath);
        } 
        pathList.setModel(pathController.getAllListModel());
	}
	
	
	
	
}
