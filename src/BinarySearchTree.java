//implemented as per code in the slides
/**The BinarySearchTree class serves as the implementation of an orderd 
 * Binary Tree data structure where nodes that make up the tree contain a concatenation of 
 * String values and are orderd lexically.**/
public class BinarySearchTree{

	private BinaryTreeNode rootNode;
	private int findCount;
	private int totalInsertCount;
	
	/**Creates an empty BinarySearchTree**/
	public BinarySearchTree(){
		rootNode = null;
		findCount = 0;
		totalInsertCount =0;
	}
	
	/**Non-recursive version of the insert method - 
	 * if the tree is not empty, this method invokes the recursive version 
	 * which inserts a node into its tree.
	 * @param s Date/Time, Power and Voltage instance to be stored by the node created by insert. **/
	public void insert (String s){
      if (rootNode == null)
         rootNode = new BinaryTreeNode(s, null, null);
      else{
         insert(s,rootNode);
	 }
    }
    
     /**Recursive version of the insert method - this method recurses down
     * the tree to find the position of insertion and thereafter creates a new
     * node in that position. The method also keeps track of how many comparisons 
     * were made when inserting the given node in the tree and updates the totalInsertCount accordingly.
     * @param s Date/Time, Power and Voltage instance to be stored by the node created by insert.
     * @param node the node being compared to the given String to find the position of insertion.**/ 
    public void insert(String s, BinaryTreeNode node){
		if (s.compareTo(node.getData()) < 0){
			if (node.getLeft() == null){
				totalInsertCount++;
				node.setLeft(s);
			}else{
				totalInsertCount++;
				insert(s, node.getLeft());
			}
		}else{
			if (node.getRight() == null){
				totalInsertCount++;
				node.setRight(s);
			}else{
				totalInsertCount++;
				insert(s, node.getRight());
			}
		}
	}
	
	/**Prints the value of the data stored in a node.
	 * @param node the node who's data will be printed by the visit method.**/
	public void visit (BinaryTreeNode node){
		String temp = node.getData();
		String parts[] = temp.split(", ");
		System.out.println(String.format("%-19s %-7s %-7s",parts[0] + " ", parts[1] + " ", parts[2]));
	}
	/**Non-recursive version of the inOrder method - this method invokes 
	 * the recursive version which prints out the value of each node.**/
	public void inOrder(){
		inOrder (rootNode);
	}
	
	/**Recursive version of the inOrder method which recurses down the tree and invokes 
	 * the visit method to print out the value of each node - the nodes are printed in ascending order.
	 * @param node the node that will be passed to the visit function for its data to be printed.**/
	public void inOrder(BinaryTreeNode node){
		if (node != null){
			inOrder(node.getLeft());
			visit(node);
			inOrder(node.getRight());
		}   
	}
	
	/**Non-recursive version of the find method - if the tree is not empty,
	 * this method invokes the recursive version and searches for a given dateTime
	 * in the tree.
	 * @param s the dateTime record that will be searched for in the tree.
	 * @return the node if found, null if not found.**/
	public BinaryTreeNode find(String s){
		if (rootNode == null){
			findCount =0;
			return null;
		}else{
			findCount= 0;
			return find(s, rootNode);
		}
	}
	
	/**Recursive version of the find method - this method recurses down the tree
	 * and compares the given dateTime with the data stored in each node. If found, 
	 * the node is returned, if not then null is returned. The method also keeps track 
	 * of how many comparisons were made when searching for a given node(ie. findCount).
	 * @param s the dateTime record that will be searched for in the tree.
	 * @param node the node who's data will be compared to the given dateTime record.
	 * @return the node if found, null if not found.**/
	public BinaryTreeNode find(String s, BinaryTreeNode node){
		String[] parts = node.getData().split(",");
		String dateTime = parts[0];
		if (s.compareTo(dateTime) == 0){
			findCount++;
			return node;
		}else if (s.compareTo(dateTime) < 0){
			findCount+=2;
			return (node.getLeft() == null) ? null : find(s, node.getLeft());
		}else{
			findCount+=2;
			return (node.getRight() == null) ? null : find(s, node.getRight());
		}
	}
	
	/**Returns the value of findCount which is the number of comparisons 
	 * that were made when searching for a node in its tree.
	 * The value of findCount is reset whenever a new node is searched
	 * for, so the findCount attribute will always correspond to the last node searched.
	 * @return the number of comparisons made when searching for a node.**/
	public int getFindCount(){
		return this.findCount;
	}
	
	/**Returns the value of totalInsertCount which is the number of comparisons 
	 * that were made when populating the tree with data.
	 * The value of totalInsertCount is reset whenever the tree is initialised,  
	 * and incremented whenever the insert method is invoked.
	 * @return the number of comparisons made when building the tree.**/
	public int getTotalInsertCount(){
		return this.totalInsertCount;
	}
    
}
