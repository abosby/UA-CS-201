package trees;


public class BinaryNode {

	//Private
	private BinarySearchTree BST;
	private String value;
	private int frequency;
	private int level;
	private BinaryNode parent;
	private BinaryNode left;
	private BinaryNode right;
	/** Constructor for the Binary Node
	 * 
	 * @param BST The Tree that the Node is a part of
	 * @param v The value of the new node
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

	/** Inserts a string into the Binary Tree under the current node
	 * 	Adapted insert function from http://vitalflux.com/java-create-binary-search-tree-string-search/
	 * @param binarySearchTree 
	 * @param v The value to be inserted
	 */
	public void insertNode(BinarySearchTree binarySearchTree, String v){

		//Check value of Node
		if(this.value == null){
			this.setValue(v);
			this.setLevel(1);
		}

		else{

			// If value is equal , increase frequency
			if(this.getValue().equals(v)){
				this.setFrequency(this.getFrequency() + 1);
			}

			// If value is less than
			else if(this.getValue().compareTo(v)>0){
				if(this.getLeft() != null){
					this.getLeft().insertNode(binarySearchTree, v);
				}
				else{
					this.setLeft(new BinaryNode(binarySearchTree,v));
					this.getLeft().setLevel(this.getLevel()+1);
					this.getLeft().setParent(this);
				}
			}

			// If value is greater than
			else if(this.getValue().compareTo(v)<0){
				if (this.getRight() != null){
					this.getRight().insertNode(binarySearchTree, v);
				}
				else{
					this.setRight(new BinaryNode(binarySearchTree,v));
					this.getRight().setLevel(this.getLevel()+1);
					this.getRight().setParent(this);
				}
			}

		}

	}

	public BinaryNode deleteNode(BinarySearchTree binarySearchTree, String v) {
		if(this.value == null){
			return null;
		}
		else{

			//If value is less than
			if(this.getValue().compareTo(v)>0){
				if(this.getLeft() != null){
					return this.getLeft().deleteNode(binarySearchTree, v);
				}
				else{
					return null;
				}

			}

			//If value is greater than
			else if(this.getValue().compareTo(v)<0){
				if(this.getRight() != null){
					return this.getRight().deleteNode(binarySearchTree, v);
				}
				else{
					return null;
				}
			}

			//If value is equal 
			else{

				if(this.getFrequency() == 1){
					//If two children
					if((this.getRight() != null ) && (this.getLeft() != null)){

						//replace with inorder predecessor then remove the predecessor node
						if(this.getParent().getRight() == this){
							BinaryNode temp = new BinaryNode(this.getBST(),this.getValue().toString());
							this.setFrequency(this.getLeft().getFrequency());
							this.setValue(this.getLeft().getValue().toString());
							this.setRight(this.getLeft().getRight());
							this.setLeft(this.getLeft().getLeft());
							return temp;
						}
						else{
							BinaryNode temp = new BinaryNode(this.getBST(),this.getValue().toString());
							this.setFrequency(this.getRight().getFrequency());
							this.setValue(this.getRight().getValue());
							this.setLeft(this.getRight().getLeft());
							this.setRight(this.getRight().getRight());
							return temp;

						}
					}

					//If one child
					else if((this.getLeft() != null) && (this.getRight() == null)){

						BinaryNode temp = new BinaryNode(this.getBST(),this.getValue().toString());
						this.setFrequency(this.getLeft().getFrequency());
						this.setValue(this.getLeft().getValue().toString());
						this.setRight(this.getLeft().getRight());
						this.setLeft(this.getLeft().getLeft());
						return temp;						
					}

					else if((this.getLeft() == null) && (this.getRight() != null)){

						BinaryNode temp = new BinaryNode(this.getBST(),this.getValue().toString());
						this.setFrequency(this.getRight().getFrequency());
						this.setValue(this.getRight().getValue().toString());
						this.setLeft(this.getRight().getLeft());
						this.setRight(this.getRight().getRight());
						return temp;
					}
					//If no children
					else{
						if(this.getParent().getLeft() == this){
							this.getParent().setLeft(null);
						}
						else{
							this.getParent().setRight(null);
						}
						return this;
					}
				}

				//Reduce node frequency
				else {
					this.setFrequency(this.getFrequency()-1);
					return this;
				}
			}
		}
	}
	//public String determineValue(String v){
	//	if

	public void findNode(BinarySearchTree binarySearchTree, String v) {
		
		//If less
		if(this.getValue().compareTo(v)>0){
			if(this.getLeft() != null){
				this.getLeft().findNode(binarySearchTree, v);
				return;
			}
			else{
				System.out.printf("\nThe Node '%s' is not found in the tree\n", v);
				return;
			}
		}
		//If greater
		else if(this.getValue().compareTo(v)<0){
			if(this.getRight() != null){
				this.getRight().findNode(binarySearchTree, v);
				return;
			}
			else{
				System.out.printf("\nThe Node '%s' is not found in the tree\n", v);
				return;
			}
		}
		//If equal
		else if(this.getValue().equals(v)){
			System.out.printf("\nFound '%s'\nFrequency:%d\n",this.getValue(),this.getFrequency());
			return;
		}
		//Supposed to be here but isn't
		else{
			System.out.printf("\nThe Node '%s' is not found in the tree", v);
			return;
		}
	}

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



}