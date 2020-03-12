package Delivery;

import GUI.Store;
import specExceptions.CSVFormatException;
import specExceptions.CSVReadFileException;
import specExceptions.DeliveryException;
import specExceptions.ItemDoesNotExistException;
import specExceptions.NoItemDuplicatesException;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;

import org.junit.*;

/**
 * 
 * @author Matt
 *
 *This test section outlines the testing for the Manifest Object
 *
 **/

public class ManifestTests {
	
	/* Test 0 : Declaring object for testing 
     */
	Manifest manifest;
	Store store = Store.getInstance();
	
	File itemProperties = new File("C:/Users/Matt/git/CAB302-Group-Assignment/Sample files/item_properties.csv");
	Path itemPath = itemProperties.toPath();
	
	File manifestFile = new File("C:/Users/Matt/git/CAB302-Group-Assignment/manifest.csv");
	Path manifestPath = manifestFile.toPath();
	
	/* Using @Before Ensure that the object declared has no value before starting
	 */
	@Before
	public void setUpTestManifest(){
		manifest = null;
		try {
			store.initItems(itemPath);
		} catch (CSVFormatException | NoItemDuplicatesException | CSVReadFileException e) {
			e.printStackTrace();
		}
	}
	
	/* Test 1 : Constructing a Manifest Object
	 */
	@Test
	public void testManifestInit() throws ItemDoesNotExistException, DeliveryException {
		manifest = new Manifest();		
	}	
	
	@Test
	public void firstRun() throws ItemDoesNotExistException, DeliveryException, FileNotFoundException, CSVFormatException, CSVReadFileException {
		store.loadManifest(manifestPath);
		assertEquals(42717.88, Double.valueOf(String.format("%.2f", store.getCapital())), 0.01);
	}
}

