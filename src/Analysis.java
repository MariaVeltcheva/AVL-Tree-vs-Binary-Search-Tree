import java.lang.Math;
import java.io.*;
import java.util.*;
/**The Analysis class traverses a given file containing counter values
 * and calculates the minimum, maximum and average of the values in the file. 
 * The minimum, maximum and average values are appended to separate textfiles along with the size
 * of the file that was traversed.**/
public class Analysis{
	
	/**Stores the path to the file containing counter values that will be traversed.**/
	private static String file;
	/**Stores the path to the file that the calculated minimum will be appended to. **/
	private static String bestCaseFile;
	/**Stores the path to the file that the calculated average will be appended to. **/
	private static String avgCaseFile;
	/**Stores the path to the file that the calculated maximum will be appended to. **/
	private static String worstCaseFile;
	/**Stores the number of lines in the file to be traversed.**/
	private static int size;
	
	/**Navigates between various variable states needed by the class's methods according to the command line parameters passed when running the application
	 * from the command line.
	 * <p>
	 * @param args[0] the file containing counter values that will be traversed.
	 * @param args[1] a string (either "AVL" or "BST") that will tell the program which output files to save the calculated values to.
	 * @param args[2] the number of lines in the file to be traversed.
	 * @param args[3] a string that ,if present, instructs the program to append the calculated values to textfiles relating to the experiment 
	 * run using the sorted csv file.**/
	public static void main(String args[]){
		
		file = args[0];
		if (args[1].equals("AVL")){
			if(args.length > 3){
				bestCaseFile = "../textfiles/Part5/Analysis/AVL/Sorted/BestCase.txt";
				avgCaseFile = "../textfiles/Part5/Analysis/AVL/Sorted/AverageCase.txt";
				worstCaseFile = "../textfiles/Part5/Analysis/AVL/Sorted/WorstCase.txt";
			}else{
				bestCaseFile = "../textfiles/Part5/Analysis/AVL/BestCase.txt";
				avgCaseFile = "../textfiles/Part5/Analysis/AVL/AverageCase.txt";
				worstCaseFile = "../textfiles/Part5/Analysis/AVL/WorstCase.txt";
			}
		}else{
			if(args.length > 3){
				bestCaseFile = "../textfiles/Part5/Analysis/BST/Sorted/BestCase.txt";
				avgCaseFile = "../textfiles/Part5/Analysis/BST/Sorted/AverageCase.txt";
				worstCaseFile = "../textfiles/Part5/Analysis/BST/Sorted/WorstCase.txt";
			}else{
				bestCaseFile = "../textfiles/Part5/Analysis/BST/BestCase.txt";
				avgCaseFile = "../textfiles/Part5/Analysis/BST/AverageCase.txt";
				worstCaseFile = "../textfiles/Part5/Analysis/BST/WorstCase.txt";
			}
		}
		size = Integer.parseInt(args[2]);
		
		bestCase();
		worstCase();
		avgCase();
				
	}
	/**Traverses the file passed as a parameter to the main method and determines the minimum value in the file. 
	 * The value is then appended to a file pertaining to a specific data structure (either BST or AVL) 
	 * along with the value of the dataset's size.**/
	public static void bestCase(){
		File f = new File(file);
		Scanner s = null;
		int best = 500;
		//obtain best case value from given file
		try{
		   s = new Scanner(f);
		   while(s.hasNext()){
			   String line = s.nextLine();
			   String[] parts = line.split(";");
			   String val = parts[1].trim();
			   int value = Integer.parseInt(val); 
			   if(value<best)
				   best = value;
		   }
		}
		catch(IOException e){
			  System.out.println(e);
		}
		
		//write best case value to a file
		try{
		   PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter(bestCaseFile, true)));
		   w.println(String.format("%-5s %s", size + "; ", best));
		   w.close();
	    }
	    catch(IOException e){
		   System.out.println(e);
	    }
	}
	
	/**Traverses the file passed as a parameter to the main method and determines the maximum value in the file. 
	 * The value is then appended to a file pertaining to a specific data structure (either BST or AVL) 
	 * along with the value of the dataset's size.**/
	public static void worstCase(){
		File f = new File(file);
		Scanner s = null;
		try{
		   s = new Scanner(f);
		   int worst = 0;
		   while(s.hasNext()){
			   String line = s.nextLine();
			   String[] parts = line.split(";");
			   String val = parts[1].trim();
			   int value = Integer.parseInt(val); 
			   if(value>worst)
				   worst = value;
		   }
		   
		   //write worst case value to a file
		   try{
			   PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter(worstCaseFile, true)));
			   w.println(String.format("%-5s %s", size + "; ", worst));
			   w.close();
		   }
		   catch(IOException e){
			   System.out.println(e);
		   }//end of writing to file  
		}
		catch(IOException e){
			  System.out.println(e);
		}
		
	}
	
	/**Traverses the file passed as a parameter to the main method and determines the average of all of the values in the file. 
	 * This value is then appended to a file pertaining to a specific data structure (either BST or AVL) 
	 * along with the value of the dataset's size.**/
	public static void avgCase(){
		File f = new File(file);
		Scanner s = null;
		try{
		   s = new Scanner(f);
		   int sum = 0;
		   while(s.hasNext()){
			   String line = s.nextLine();
			   String[] parts = line.split(";");
			   String val = parts[1].trim();
			   int value = Integer.parseInt(val); 
			   sum+= value;
		   }
		   
		   int avg = Math.round(sum/size);
		   
		   //write avg case value to a file
		   try{
			   PrintWriter w = new PrintWriter(new BufferedWriter(new FileWriter(avgCaseFile, true)));
			   w.println(String.format("%-5s %s", size + "; ", avg));
			   w.close();
		   }
		   catch(IOException e){
			   System.out.println(e);
		   }//end of writing to file
		   
		}
		catch(IOException e){
			  System.out.println(e);
		}
		
		
	
	}
}
