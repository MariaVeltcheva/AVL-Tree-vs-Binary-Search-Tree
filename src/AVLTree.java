import java.lang.Math;

//implemented as per code in the slides
/**The AVLTree class serves as the implementation of a balanced 
 * Binary Search Tree data structure where nodes that make up the tree contain a concatenation of 
 * String values and are orderd lexically.**/
public class AVLTree{

	private AVLNode rootNode;
	private int findCount;
	private int totalInsertCount;
	
	/**Creates an empty AVL tree.**/
	public void AVLTree(){
		rootNode = null;
		totalInsertCount =0;
	}
	
	/**Non-recursive version of the insert method - 
	 * This method invokes the recursive version 
	 * which inserts a node into its tree.
	 * @param d Date/Time, Power and Voltage instance to be stored by the node created by insert. **/
	public void insert ( String d){
		rootNode = insert(d, rootNode);
	}
	
	/**Recursive version of the insert method - this method recurses down
     * the tree to find the position of insertion and thereafter creates a new
     * node in that position. The method also keeps track of how many comparisons 
     * were made when inserting the given node in the tree and updates the totalInsertCount 
     * accordingly. After each insertion the balance method is called in order to ensure that 
     * the balance properties of the AVL tree are maintained.
     * @param d Date/Time, Power and Voltage instance to be stored by the node created by insert.
     * @param node the node being compared to the given String to find the position of insertion.
     * @return the root of the subtree formed by inserting a node.**/ 
	public AVLNode insert (String d, AVLNode node ){
		if (node == null)
			return new AVLNode (d,0, null, null);
		if (d.compareTo (node.getData()) <= 0){
			totalInsertCount++;
			node.setLeft(insert (d, node.getLeft()));
		}else{
			totalInsertCount++;
			node.setRight(insert (d, node.getRight()));
		}return balance (node);
   }
	
	/**Returns the height of a node.
	 * @param node the node who's height will be calculated.
	 * @return the node's height.*/
	public int height(AVLNode node){
		if (node != null)
			return node.getHeight();
		else
			return -1;
	}
	
	/**Returns the height of  an AVLTree's root node.
	 * @return the height of the root node.**/
	public int getRootHeight(){
		return height(rootNode);
	}
	
	/**Maintains the balance properties of the tree by invoking the appropriate roatation methods, if necessary.
	 * @param p potentially imbalanced node where balance properties will be checked.
	 * @return the root node of the rebalanced subtree or the original node if no rotations performed.**/
	public AVLNode balance(AVLNode p){
		fixHeight(p);
		if (balanceFactor(p) == 2){ //right tree longer
			if (balanceFactor(p.getRight()) < 0)
				p.setRight(rotateRight(p.getRight()));
			
			return rotateLeft(p);
			
		}
		if (balanceFactor(p) == -2){ //left tree longer
			if (balanceFactor(p.getLeft()) > 0)
				p.setLeft(rotateLeft(p.getLeft()));
		
			return rotateRight(p);
			
		}
		return p;
	 }
	
	/**Determines the balance factor at a node which gives an indication of whether or not a tree is balanced.
	 * @param node the node who's balance factor will be calculated.
	 * @return the balance factor at a node.**/
	public int balanceFactor(AVLNode node){
		return height(node.getRight()) - height(node.getLeft());
	}
	
	/**Calculates and sets the height of a node according to the height of its left and right subtrees.
	 * @param node the node who's height will be set.**/
	public void fixHeight(AVLNode node){
		node.setHeight(Math.max(height(node.getLeft()), height(node.getRight())) + 1);
	}
	
	/**Performs a rotation on a node in a clockwise direction in order to aid in rebalancing the tree.
	 * @param p the node which serves as the pivot of rotation.
	 * @return the root of the rebalanced subtree.**/
	public AVLNode rotateRight(AVLNode p ){
		AVLNode q = p.getLeft();
		p.setLeft(q.getRight());
		q.setRight(p);
		fixHeight(p);
		fixHeight(q);
		
		return q;
	}
	
	/**Performs a rotation on a node in an anti-clockwise direction in order to aid in rebalancing the tree.
	 * @param q the node which serves as the pivot of rotation.
	 * @return p the root of the rebalanced subtree. **/
	public AVLNode rotateLeft(AVLNode q){
		AVLNode p = q.getRight();
		q.setRight(p.getLeft());
		p.setLeft(q);
		fixHeight(q);
		fixHeight(p);
		
		return p;
	}
	
	/**Prints the value of the data stored in a node.
	 * @param node the node who's data will be printed by the visit method.**/
	public void visit (AVLNode node){
		String temp = node.getData();
		String parts[] = temp.split(", ");
		System.out.println(String.format("%-19s %-7s %-7s",parts[0] + " ", parts[1] + " ", parts[2]));
	}
	
	/**Non-recursive version of the inOrder method - this method invokes 
	 * the recursive version which prints out the value of each node.**/
	public void inOrder(){
		inOrder(rootNode);
	}
	
     /**Recursive version of the inOrder method which recurses down the tree and invokes 
	 * the visit method to print out the value of each node - the nodes are printed in ascending order.
	 * @param node the node that will be passed to the visit function for its data to be printed.**/
	 public void inOrder(AVLNode node){
		if (node != null){
			
			inOrder(node.getLeft());
			visit(node);              /*test*/
			inOrder(node.getRight());
		}   
	 }
	 
	 /**Non-recursive version of the find method - if the tree is not empty,
	 * this method invokes the recursive version and searches for a given dateTime
	 * in the tree.
	 * @param s the dateTime record that will be searched for in the tree.
	 * @return the node if found, null if not found.**/
	 public AVLNode find(String s){
		if (rootNode == null){
			findCount=0;
			return null;
		}else{
			findCount=0;
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
	 public AVLNode find(String s, AVLNode node){
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
