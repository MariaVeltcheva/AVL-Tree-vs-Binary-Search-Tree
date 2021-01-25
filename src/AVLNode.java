/**The AVLNode class serves as the implementation of the objects that
 * that are linked together to form an AVLTree. Each node in this case stores
 * a concatenation of Date/Time, Power and Voltage Strings as well as a link to a 
 * left and right child as well as the height of each node.**/
public class AVLNode{
	
	private String data;
	private int	height;
	private AVLNode left;
    private AVLNode right;
	
	/**Constructor that initialises the nodes attributes.
	 * @param d the data that will be stored by the node.
	 * @param h the height of the node.
	 * @param l the left child of the node.
	 * @param r the right child of the node.**/
	public AVLNode(String d,int h, AVLNode l, AVLNode r){
      data = d;
      height = h;
      left = l;
      right = r;
   }
   
   /**Returns the node's data.
	 * @return the data stored by the node(String).**/
   public String getData(){
      return this.data;
   }
   /**Returns the node's height.
    * @return the height of the node.**/
   public int getHeight(){
      return this.height;
   }
   /**Returns the node's left child.
	 * @return the node's left child(AVLNode).**/
   public AVLNode getLeft(){
      return this.left;
   }
   /**Returns the node's right child.
	 * @return the node's right child(AVLNode).**/
   public AVLNode getRight(){
      return this.right;
   }
   /**Sets the data of the node to the given String.
    * @param d the string to be stored by the node.**/
   public void setData(String d){
      this.data = d;
   }
   /**Sets the height of the node to the given Integer.
    * @param h the inetger that will be stored as the node's height.**/
   public void setHeight(int h){
      this.height = h;
   }
   /**Sets the left child of a node to the node given.
	 * @param node the node to be set as the left child.**/
   public void setLeft(AVLNode node){
      this.left = node;
   }
   /**Sets the right child of a node to the node given.
	 * @param node the node to be set as the right child.**/
   public void setRight(AVLNode node){
      this.right = node;
   }

}
