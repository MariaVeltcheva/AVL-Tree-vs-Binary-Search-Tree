import java.util.*;
import java.io.*;

public class PowerAVLApp{
	
	/**AVLTree that will hold the data items obtained from the csv.**/		
	private static AVLTree avl;
	/**Stores the number of data items that will be loaded into the AVLTree.**/
	private static int avlSize;
	/**Stores the path to the file that will store insertion counter values for a tree of a specific size.**/
	private static String insertOutput;
	/**Stores the path to the file that will store search counter values for a tree of a specific size.**/
	private static String searchOutput;
	/**Stores the value which represents the number of items in a query file that have been visited so far so that when 
	 * printDateTime is called on a query within the file, <i>iteration</i> can be appended to an output textfile along with the search count.**/
	private static int iteration;
	/**Stores the path to the file that contains the data that will populate the AVLTree.**/
	private static String dataFile;
	/**Stores the number of arguments passed when PowerAVLApp is run from the command line - for use by printDateTime.**/
	private static int numArgs;
	
	/**Navigates between various method invocations and variable states depending on the 
    * arguments passed when running PoweAVLApp from the command line.
    * <p>
    * @param args[0] dateTime string to be queried by printDateTime() or the path to a textfile containing dateTime strings to be queried 
    * by printDateTime().
    * @param args[1] number from 0 to 500 which will determine the number of data items loaded into the AVLTree.
    * @param args[2] string that denotes the path to the file which will store the insertion counter values for a tree of a specific size.
    * @param args[3] string that denotes the path to the file which will store the search counter values for a tree of a specific size.
    * @param args[4] string that ,if present, instructs the program to load data from cleaned_data_sorted.csv as opposed to cleaned_data.csv.
    * **/
	public static void main( String args[]){
		numArgs = args.length;
		
		/*set number of items to be loaded into avl and files to store
		 *insert and search counters if appropriate paramaters passed*/
		if (args.length > 1){
			avlSize = Integer.parseInt(args[1]);
			insertOutput = args[2];
			searchOutput = args[3];
		}else{
			avlSize = 500;
			insertOutput = "../textfiles/Part2/InsertionCount_Part2.txt";
			searchOutput = "../textfiles/Part2/SearchCount_Part2.txt";
		}
		
		/*set data input file to either sorted or unsorted csv
		 depending on parameters passed*/
		if(args.length < 5)
			dataFile = "../data/cleaned_data.csv";
		else
			dataFile = args[4];
		
		loadData();
		
		//check if dateTime string, query file or no paramaters passed
		//navigate to either printDateTime or printAllDateTimes accordingly
		if (args.length > 0){
			if (isDateTime(args[0]))
				printDateTime(args[0]);
			else{
				iteration = 0;
				File f = new File(args[0]);
			    Scanner s = null;
			    try{
				   s = new Scanner(f);
				   while(s.hasNext()){ 
					   iteration++;
					   String line = s.nextLine();
					   String[] parts = line.split(",");
					   String date = parts[0];
					   printDateTime(date);
				   }
			    }
			    catch(IOException e){
				      System.out.println(e);
			    }   
			}	
		}else{
			printAllDateTimes();
		}
		System.out.println("Insertion Count: " + avl.getTotalInsertCount());
		
		//write insertionCount value to a file for analysis purposes
		try{
		   PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter(insertOutput, true)));
		   if (numArgs > 1)
			   w.println(String.format("%-5s %s", avlSize + "; ", avl.getTotalInsertCount()));
		   else
			   w.println("Insertion count: " + avl.getTotalInsertCount());
           w.close();
	   }
	   catch(IOException e){
		   System.out.println(e);
	   }
	}
	
	/**Populates the AVLtree with data from the csv.
    * The Date/Time, Power and Voltage values of each line in the csv
    * are combined into a single dataItem which is then added as a node in the tree. **/
	public static void loadData(){
		File f = new File(dataFile);   
		Scanner s = null;
		avl = new AVLTree();
		int count = -1;//counter to keep track of array index

		try{
		 s = new Scanner(f);
		 while((s.hasNext()) && (count<=avlSize-1)){
			 String line = s.nextLine();
			 String[] parts = line.split(",");
			 String dataItem = parts[0] + ", " + parts[1] + ", " + parts[3];          
			 if (count >-1)
				avl.insert(dataItem);
			

			 count++;
		 }
		}catch(IOException e){
		 System.out.println(e);
		}
	
	}
	
	/**Prints out the Date/Time, Power and Voltage values for all dateTime records in the tree. 
    * This method is invoked if no arguments are passed when 
    * running PowerAVLApp from the command line.**/
	public static void printAllDateTimes(){
		avl.inOrder();
		
		//write findCount value to a file for analysis purposes
		try{
		   PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter(searchOutput, true)));
		   w.println("No parameters " + avl.getFindCount());
           w.close();
	   }
	   catch(IOException e){
		   System.out.println(e);
	   }
	}
	
	/**Determines whether a string passed as an argument is in the form "DD/MM/YYYY/hh:mm:ss".
	 * @param s String who's format will be checked.
	 * @return True if string is in the correct format, otherwise returns false.**/
	public static boolean isDateTime(String s){
		if (s.length() == 19){
			char[] characters = s.toCharArray();
			
			if ((characters[2] == '/') && (characters[5] == '/') && (characters[10]=='/') 
				&& (characters[13] == ':') && (characters[16] == ':'))
				return true;
				
		}
		return false;
		
	}
		
    /**Prints out the Date/time, Power and Voltage values for
    * the given dateTime record, or "Date/time not found" if the record is not found.
    * The method also keeps count of how many comparisons were made when searching 
    * for the given dateTime record, and writes that value to a text file. 
    * If the dateTime being queried is extracted from a query file, the number of items in 
    * the query file that were visited up to the point of calling printDateTime on the query is also appended to the file.
    * The file it is written to is determined by the command line
    * argument passed when running PowerAVLApp.
    * @param  dateTime  dateTime to be searched for in the AVLTree.**/
	public static void printDateTime(String dateTime){
		AVLNode result = avl.find(dateTime);
		if (result == null){
			System.out.println("Date/time not found");
			System.out.println("Search Count: " + avl.getFindCount());
		}else{
			avl.visit(result);
			System.out.println("Search Count: " + avl.getFindCount());
		}
		
		//write findCount value to a file for analysis purposes
		try{
		   PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter(searchOutput, true)));
		   if (numArgs > 1)
			   w.println(String.format("%-5s %s",iteration + "; ", avl.getFindCount())); //write to analysis file
		   else
			   w.println(dateTime + " " + avl.getFindCount()); //write to SearchCount_Part2.txt
           w.close();
	    }
	    catch(IOException e){
		   System.out.println(e);
	   }
	}
}
