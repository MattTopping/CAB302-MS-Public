package GUI;

/**
 * This is the GUI class. It contains that code responsible for constructing the GUI.
 * 
 * @author Matthew Topping
 * @version 5.1
 */

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Delivery.Manifest;
import Stock.Item;
import Stock.Stock;
import specExceptions.CSVFormatException;
import specExceptions.CSVReadFileException;
import specExceptions.DeliveryException;
import specExceptions.ItemDoesNotExistException;
import specExceptions.NoItemDuplicatesException;

@SuppressWarnings("serial")
public class GUILibrary extends JFrame implements ActionListener{
	
	private Store store;
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private JPanel initialWindowStructure = new JPanel(new GridLayout(4, 1, 5, 5));
	private JLabel nameMessage = new JLabel("You are opening this software for the first time \n"
			+ "Please enter your store name.", JLabel.CENTER);
	private JTextField name = new JTextField();
	private JLabel itemMessage = new JLabel("Would you like to load in your items now? \n"
			+ "This can be completed later via the main GUI screen", JLabel.CENTER);
	
	private JPanel internalButtonStructure = new JPanel(new GridLayout(1, 2, 20, 20));
	private JButton load = new JButton("Load in Items");
	private JButton skip = new JButton("Skip loading in Items");
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	private boolean itemsInstalled = false;
	private boolean initialiseWindow = true;
	private final CountDownLatch latch = new CountDownLatch(1);

	private JPanel split = new JPanel(new GridBagLayout());
	private JPanel buttonFrame = new JPanel(new GridLayout(5, 1, 50, 50));
	private JPanel tableFrame = new JPanel(new FlowLayout());
	
	private JLabel capital;
	private JLabel itemTablePlaceHolderLabel = new JLabel("No Items Imported. Please use the Load Items button to get started.", JLabel.CENTER);
	
	private JButton loadItems = new JButton("Load Items");
	private JButton exportMan = new JButton("Export Manifest");
	private JButton loadMan = new JButton("Load Manifest");
	private JButton loadSales = new JButton("Load Sales Log");
	
	private JTable itemJTable;

	/**
	 * This constructor will create the GUI and run it. This will allow the object to just be created and it will run the GUI.
	 */
	public GUILibrary(){
		//Start window and change closing state
		super("SuperMart Inventory System");
		JFrame.setDefaultLookAndFeelDecorated(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// Generate new store
		store = Store.getInstance();
		
		generateInitialStructure();
		generateMainStructure();
		generateMainButtons();
	}
	
	/**
	 * This function is called by the constructor and will create the initial window of the GUI. This
	 * window will ask for the branch name and give the user the option to load in items for initialsation
	 */
	public void generateInitialStructure() {
		initialWindowStructure.add(nameMessage);
		initialWindowStructure.add(name);
		initialWindowStructure.add(itemMessage);
		initialWindowStructure.add(internalButtonStructure);
		
		initialWindowStructure.setBorder(new EmptyBorder(20, 20, 20, 20));

		load.addActionListener(this);
		internalButtonStructure.add(load);
		
		skip.addActionListener(this);
		internalButtonStructure.add(skip);
		
		getContentPane().add(initialWindowStructure);
		setPreferredSize(new Dimension(400, 400));
		setLocation(new Point(100, 100));
		pack();
		setVisible(true);

		try {
			latch.await();
		} catch (InterruptedException e) {
			JOptionPane.showMessageDialog(null, "Waiting thread was interupted!");
			e.printStackTrace();
		}
	}
	
	/**
	 * This method will create the GUI object that are consistent between the 2 outcomes of the starting screen.
	 * These items are the frames that contain the content as well as the capital label.
	 */
	
	public void generateMainStructure() {
		// Create the container for the left and right
		split.setBorder(new EmptyBorder(10, 20, 10, 10));
				
		// Create for way divider to hold buttons and capital
		buttonFrame.setBorder(new EmptyBorder(10, 20, 10, 10));

		split.add(buttonFrame);
				
		// Create capital label
		String capitalString = String.format("%.2f", store.getCapital());
		String labelString = "Current Capital: $" + capitalString;
		capital = new JLabel(labelString, JLabel.CENTER);
		
		buttonFrame.add(capital);
	}
	
	
	/**
	 * 
	 */
	
	public void generateMainButtons() {
		Stock stock;
		stock = store.getCurrInventory();
		
		if(stock.keySet().size() == 0) {
			itemsInstalled = false;
		} else {
			itemsInstalled = true;
		}
		
		if (itemsInstalled == true) {
			
			//Generate Table and format 
			itemJTable = generateItemJTable();
			itemJTable.setFillsViewportHeight(true);
			itemJTable.setEnabled(false);
			itemJTable.getTableHeader().setReorderingAllowed(false);
			
			GridBagConstraints itemTableFrameConstraints = new GridBagConstraints();
			itemTableFrameConstraints.weightx = 0.6;
			
			split.add(tableFrame, itemTableFrameConstraints);
			
			GridBagConstraints itemTableConstraints = new GridBagConstraints();
			itemTableConstraints.weightx = 1;
			itemTableConstraints.weighty = 1;
			
			tableFrame.add(new JScrollPane(itemJTable), itemTableConstraints);
		} else {
			// Create label to display in place of the item table
			GridBagConstraints itemTableConstraints = new GridBagConstraints();
			itemTableConstraints.weightx= 0.60;
					
			split.add(itemTablePlaceHolderLabel, itemTableConstraints);
			
			// disable function that cannot be used without without loading in items
			exportMan.setEnabled(false);
			loadMan.setEnabled(false);
			loadSales.setEnabled(false);
		}
		
		// Create the 4 buttons
		loadItems.addActionListener(this);
		buttonFrame.add(loadItems);

		exportMan.addActionListener(this);
		buttonFrame.add(exportMan);

		loadMan.addActionListener(this);
		buttonFrame.add(loadMan);

		loadSales.addActionListener(this);
		buttonFrame.add(loadSales);
				
		// Display
		getContentPane().add(split);
		setPreferredSize(new Dimension(1366, 768));
		setLocation(new Point(200, 200));
		pack();
		setVisible(true);
	}
	
	/**
	 * This method is called to generate the JTable of all the current items to be displayed in the GUI
	 * 
	 * @return the JTable of current items 
	 */
	public JTable generateItemJTable () {
		String [] colNames = {"Name", "Quantity", "Manufacturing Cost", "Sell Price", 
		                                 "Reorder Point", "ReorderAmount", "Temperature"};
		int index = 0;
		final int ALL_TABLE_ELEMENTS = 7;
		Stock stock;
		stock = store.getCurrInventory();
		
		Set<Item> currItems = new HashSet<Item>();	
		currItems = stock.keySet();
		
		String [][] tableContents = new String[currItems.size()][ALL_TABLE_ELEMENTS];

		ArrayList<String> rowPlaceholder = new ArrayList<String>();
		
		for(Item item: currItems) {
			rowPlaceholder.clear();
			
			try {
				tableContents [index][0] = item.getName();
				tableContents [index][1] = Integer.toString(stock.currentQuantity(item.toString()));
				tableContents [index][2] = "$" + String.format("%.2f", item.getManCost());
				tableContents [index][3] = "$" + String.format("%.2f", item.getStoreCost());
				tableContents [index][4] = Integer.toString(item.getReorderPoint());
				tableContents [index][5] = Integer.toString(item.getReorderAmount());
				if(item.getTemp() != null){
					tableContents [index][6] = Double.toString(item.getTemp()) + " \u00b0 C";
				}
				
			}
			catch(ItemDoesNotExistException e) {
				JOptionPane.showMessageDialog(null, "There was an error loading items: Item cannot be found");
				break;
			}
			index++;
		}
		return new JTable(tableContents, colNames);
	}

	/**
	 * This is the function that runs the actions that are associated with the GUI's buttons
	 */
	
	public void actionPerformed(ActionEvent e) {
		if(!name.getText().equals("") && initialiseWindow == true) {
			setTitle("SuperMart Inventory System: " + name.getText());
		}
		
		String buttonString = e.getActionCommand();
		
		if(buttonString.equals("Load in Items")) {
			
			final JFileChooser loadItem = new JFileChooser();
			int returnVal = loadItem.showOpenDialog(this);
			if(returnVal==JFileChooser.APPROVE_OPTION) {
				File file = loadItem.getSelectedFile();
				Path path = file.toPath();
				try {
					store.initItems(path);
				} catch (CSVFormatException | NoItemDuplicatesException | CSVReadFileException e1) {
					JOptionPane.showMessageDialog(null, e1);
					e1.printStackTrace();
				}
				
				// release pause on initial GUI screen
				latch.countDown();
				itemsInstalled = true;
				initialiseWindow = false;
				getContentPane().remove(initialWindowStructure);
				
				JOptionPane.showMessageDialog(null, "Load complete!");
				
			} else if(returnVal==JFileChooser.CANCEL_OPTION) {}
			
		} else if(buttonString.equals("Skip loading in Items")) {
			// release pause on initial GUI screen
			latch.countDown();
			getContentPane().remove(initialWindowStructure);
			initialiseWindow = false;
			JOptionPane.showMessageDialog(null, "Name changed! No items loaded.");
			
		} else if (buttonString.equals("Load Items")){
			final JFileChooser loadItem = new JFileChooser();
			int returnVal = loadItem.showOpenDialog(this);
			if(returnVal==JFileChooser.APPROVE_OPTION) {
				File file = loadItem.getSelectedFile();
				Path path = file.toPath();
				try {
					store.initItems(path);
				} catch (CSVFormatException | NoItemDuplicatesException | CSVReadFileException e1) {
					JOptionPane.showMessageDialog(null, e1);
					e1.printStackTrace();
				}
				
				// as items are now loaded enable other buttons
				itemsInstalled = true;
				
				exportMan.setEnabled(true);
				loadMan.setEnabled(true);
				loadSales.setEnabled(true);
				
				//refresh display to show table
				split.remove(itemTablePlaceHolderLabel);
				split.revalidate();
				split.repaint();
				
				itemJTable = generateItemJTable();
				itemJTable.setFillsViewportHeight(true);
				itemJTable.getTableHeader().setReorderingAllowed(false);
				
				GridBagConstraints itemTableFrameConstraints = new GridBagConstraints();
				itemTableFrameConstraints.weightx = 0.6;
				
				split.add(tableFrame, itemTableFrameConstraints);
				tableFrame.add(new JScrollPane(itemJTable));
				
				JOptionPane.showMessageDialog(null, "Load complete!");
				
			} else if(returnVal==JFileChooser.CANCEL_OPTION) {}

			//TODO - Commenting from here
		} else if (buttonString.equals("Export Manifest")) {
			Manifest manifest = null;
			try {
				manifest = new Manifest();
				manifest.writeManifest();
			} catch (FileNotFoundException | DeliveryException | ItemDoesNotExistException | IndexOutOfBoundsException e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, e1);
				return;
			} 
			JOptionPane.showMessageDialog(null, "Export complete!");
			
		} else if (buttonString.equals("Load Manifest")) {
			final JFileChooser loadItem = new JFileChooser();
			int returnVal = loadItem.showOpenDialog(this);
			if(returnVal==JFileChooser.APPROVE_OPTION) {
				File file = loadItem.getSelectedFile();
				Path path = file.toPath();
				
				try {					
					store.loadManifest(path);					
				} catch (CSVFormatException | ItemDoesNotExistException | CSVReadFileException | DeliveryException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, e1);
					return;
				} 
	
				String capitalString = String.format("%.2f", store.getCapital());
				String labelString = "Current Capital: $" + capitalString;
				capital.setText(labelString);
				
				capital.revalidate();
				capital.repaint();
				
				updateJTableAmount();
				JOptionPane.showMessageDialog(null, "Load complete!");
				
			} else if(returnVal==JFileChooser.CANCEL_OPTION) {}
			
		} else if(buttonString.equals("Load Sales Log")) {
			final JFileChooser loadItem = new JFileChooser();
			int returnVal = loadItem.showOpenDialog(this);
			if(returnVal==JFileChooser.APPROVE_OPTION) {
				File file = loadItem.getSelectedFile();
				Path path = file.toPath();
				
				try {
					store.loadSalesLog(path);
				} catch (CSVFormatException | ItemDoesNotExistException | CSVReadFileException | DeliveryException e1) {
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, e1);
					return;
				} 
				
				String capitalString = String.format("%.2f", store.getCapital());
				String labelString = "Current Capital: $" + capitalString;
				capital.setText(labelString);
				
				capital.revalidate();
				capital.repaint();
				
				updateJTableAmount();
				JOptionPane.showMessageDialog(null, "Load complete!");
			} else if(returnVal==JFileChooser.CANCEL_OPTION) {}
		} else {}
	}

	public void updateJTableAmount(){
		
		Set<Item> currItems = new HashSet<Item>();
		Stock stock = store.getCurrInventory();
		currItems = stock.keySet();
		int rowCount = itemJTable.getRowCount();
		
		try {
			for(Item item : currItems) {
				for(int i = 0; i <rowCount; i++) {
					String string = (String) itemJTable.getValueAt(i, 0);
					if(string.equals(item.toString())){
						int quant = stock.currentQuantity(item.toString());
						String input = Integer.toString(quant);
						itemJTable.setValueAt(input,i,1);
					}
				}
			}
		} catch(ItemDoesNotExistException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, e);
			return;
		}
		itemJTable.revalidate();
		itemJTable.repaint();
	}
	
	/**
	 * Calls and runs the GUI
	 */
	
	public static void main(String [ ] args) {
		new GUILibrary();
	}
}
