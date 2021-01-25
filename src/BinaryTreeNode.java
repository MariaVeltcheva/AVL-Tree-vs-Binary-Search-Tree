//implemented as per code in the slides
/**The BinaryTreeNode class serves as the implementation of the objects that
 * that are linked together to form a BinarySearchTree. Each node in this case stores
 * a concatenation of Date/Time, Power and Voltage Strings as well as a link to a left and right child.**/
public class BinaryTreeNode{
	
	private String data;
	private BinaryTreeNode left;
	private BinaryTreeNode right;
	
	/**Constructor that initialises the nodes attributes.
	 * @param val the data that will be stored by the node.
	 * @param l the left child of the node.
	 * @param r the right child of the node.**/
	public BinaryTreeNode(String val, BinaryTreeNode l, BinaryTreeNode r){
		data = val;
		left = l;
		right = r;
	}
	
	/**Returns the node's data.
	 * @return the data stored by the node(String).**/
	public String getData(){
		return this.data;
	}
	/**Returns the node's left child.
	 * @return the node's left child(BinaryTreeNode).**/
	public BinaryTreeNode getLeft(){ 
		return this.left;
	}
	/**Returns the node's right child.
	 * @return the node's right child(BinaryTreeNode).**/
	public BinaryTreeNode getRight(){
		return this.right;
	}

	/**Sets the left child of a node to one with the given data.
	 * @param val the data stored by the new BinaryTreeNode object**/
	public void setLeft(String val){
		this.left = new BinaryTreeNode(val, null, null);
	}
	/**Sets the right child of a node to one with the given data.
	 * @param val the data stored by the new BinaryTreeNode object**/
	public void setRight(String val){
		this.right = new BinaryTreeNode(val, null, null);
	}

}
