package csvproject;

import edu.duke.*;
import org.apache.commons.csv.*;

/*
 * Description: Simple java program that utilizes the duke library
 * and apache csv library to parse through a csv file and obtain 
 * particular information using tester() method. Program created during
 * a Duke Coursera course. 
 * 
 * 
 *     	
 *
 * tester method documentation
 * 
 * tester(arg0, arg1, arg2, arg3, arg4);
 * arg0 -is country name, will return all exports for that country
 * arg1 and arg2 -are item names and will return all countries that export both items
 * arg3 -is an item name and returns the number of countries that export that item
 * arg4 -is an amount in the form of a string and returns all countries with a value longer than that input string
 * 
 * 
 * @author Zev
 * 
 * @version 25/03/2018
 *
*/

public class DataParser {

    public void tester(String countryOfInterest, String item1, String item2, String exportItem, String amount) {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        
        countryInfo(parser, countryOfInterest);
        
        //Must reset parser!
        parser = fr.getCSVParser();
        
        listExportersTwoProducts(parser, item1, item2);
        
        //Parser reset
        parser = fr.getCSVParser();
        numberOfExporter(parser, exportItem);
    
        parser = fr.getCSVParser();
        bigExporters(parser, amount);
    }
    
    public void countryInfo(CSVParser parser, String countryOfInterest) {
    	
        String info = "";
        
        for(CSVRecord record : parser) {
        	
        	info = record.get("Country") + ": " + record.get("Exports");
        	
        	if(info.indexOf(countryOfInterest) != -1) {
        		break;
        	}
        }
        if(info != "") System.out.println(info);
        else System.out.println("NOT FOUND");
    }
    
    public void listExportersTwoProducts(CSVParser parser, String item1, String item2) {
    	
    	String info =  "";
    	
    	System.out.println(); //Spacing
        System.out.println("Countries that export: " + item1 + " and " + item2);

    	for(CSVRecord record : parser) {
    		info = record.get("Exports");
    		
    		if(info.contains(item1) && info.contains(item2)) {
    			System.out.println(record.get("Country"));
    		}
    	}
    }
    
    public void numberOfExporter(CSVParser parser, String exportItem) {
    	
    	int numberOf = 0;
    	String info = "";
    	
    	for(CSVRecord record : parser) {
    		info = record.get("Exports");
    		
    		if(info.contains(exportItem)) {
    			numberOf++;
    		}
    	}
    	System.out.println(); //Spacing
    	System.out.println("Number of countries that export " + exportItem + " are: ");
    	System.out.println(numberOf);
    }
    
    public void bigExporters(CSVParser parser, String amount) {
    	
    	System.out.println(); //Line spacing
    	System.out.println("Countries with export value longer than string: $" + amount);
    	
    	for(CSVRecord record : parser) {
    		
    		String country = record.get("Country");
    		String exportValue = record.get("Value (dollars)");
    		
    		if(exportValue.length() > amount.length() + 1) { //add one value to amount to commensate for dollar sign symbol in Export values
    			System.out.println(country + " " + exportValue);
    		}
    	}
    }
    
    public static void main(String args[]) {
    	DataParser ob = new DataParser();
    	
    	ob.tester("Nauru", "cotton", "flowers", "cocoa", "999,999,999,999");
    }
	

}
