

public class BinaryNode {
	
	//Private
	private BinarySearchTree BST;
	private String value;
	private int frequency;
	private int level;
	private BinaryNode parent;
	private BinaryNode left;
	private BinaryNode right;

	public BinarySearchTree getBST() {
		return BST;
	}

	public void setBST(BinarySearchTree bST) {
		BST = bST;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	public BinaryNode getParent() {
		return parent;
	}

	public void setParent(BinaryNode parent) {
		this.parent = parent;
	}

	public void setLevel(int i){
		this.level = i;
		if (this.isMax()){
			this.getBST().setMaxHeight(i);
		}
		if(this.isMin()){
			this.getBST().setMinHeight(i);
		}
	}
	
	public int getLevel(){
		return this.level;
	}

	public BinaryNode getRight() {
		return right;
	}

	public void setRight(BinaryNode right) {
		this.right = right;
	}

	public BinaryNode getLeft() {
		return left;
	}

	public void setLeft(BinaryNode left) {
		this.left = left;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	/** Constructor for the Binary Node
	 * 
	 * @param v The value of the node;
	 */
	public BinaryNode(BinarySearchTree BST, String v){
		this.setBST(BST);
		this.setValue(v);
		this.setFrequency(1);
		this.setLevel(0);
		this.setParent(null);
		this.setLeft(null);
		this.setRight(null);
	}
	
	/** Inserts a string into the Binary Tree under the current node
	 * 	Adapted insert function from http://vitalflux.com/java-create-binary-search-tree-string-search/
	 * @param binarySearchTree 
	 * @param v The value to be inserted
	 */
	public void insertNode(BinarySearchTree binarySearchTree, String v){

		//Check value of Node
		if(this.value == null){
			this.setValue(v);
		}

		else{

			// If value is equal , increase frequency
			if(this.getValue() == v){
				this.setFrequency(this.getFrequency() + 1);
			}

			// If value is less than
			else if(this.getValue().compareTo(v)<0){
				if(this.getLeft() != null){
					this.getLeft().insertNode(binarySearchTree, v);
				}
				else{
					this.setLeft(new BinaryNode(binarySearchTree,v));
					this.getLeft().setParent(this);
					this.getLeft().setLevel(this.getLeft().getParent().getLevel()+1);
					//If this node is now the max height
					if(this.getLeft().getLevel() >= this.getBST().getMaxHeight()){
						this.getBST().setMaxHeight(this.getLeft().getLevel());
					}
					//If this node is now the min height
					if(this.getLeft().getParent() == this.getBST().getMin()){
						this.getBST().setMinHeight(this.getLeft().getLevel());
						this.getBST().setMin(this.getLeft());
					}
				}
			}

			// If value is greater than
			else if(this.getValue().compareTo(v)>0){
				if (this.getRight() != null){
					this.getRight().insertNode(binarySearchTree, v);
				}
				else{
					this.setRight(new BinaryNode(binarySearchTree,v));
					this.getRight().setParent(this);
					this.getRight().setLevel(this.getRight().getParent().getLevel()+1);
					//If this node is now the max height
					if(this.getRight().getLevel() >= this.getBST().getMaxHeight()){
						this.getBST().setMaxHeight(this.getRight().getLevel());
					}
					//If this node is now the min height
					if(this.getRight().getParent() == this.getBST().getMin()){
						this.getBST().setMinHeight(this.getRight().getLevel());
						this.getBST().setMin(this.getRight());
					}
				}
			}
			
		}

	}

	//public String determineValue(String v){
	//	if
	
	public boolean isMax(){
		if(this.getLevel() == this.getBST().getMaxHeight()){
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean isMin(){
		if(this.getLevel() == this.getBST().getMinHeight()){
			return true;
		}
		else{
			return false;
		}
	}

	public boolean deleteNode(BinarySearchTree binarySearchTree, String v) {
		// TODO Auto-generated method stub
		return false;
	}

}