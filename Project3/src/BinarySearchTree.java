import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree {

	//Private class
	private class BinaryNode{

		private BinarySearchTree BST;
		private Integer value;
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
		public BinaryNode(BinarySearchTree BST, Integer v){
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

		public int getValue() {
			return value;
		}

		public void setValue(Integer value) {
			this.value = value;
		}

		/** Inserts a string into the Binary Tree under the current node
		 * 	Adapted insert function from http://vitalflux.com/java-create-binary-search-tree-string-search/
		 * @param binarySearchTree 
		 * @param v The value to be inserted
		 */
		public void insertNode(BinarySearchTree binarySearchTree, Integer v){

			//Check value of Node
			if(this.value == null){
				this.setValue(v);
				this.setLevel(1);
			}

			else{

				// If value is equal , increase frequency
				if(this.getValue() == v){
					this.setFrequency(this.getFrequency() + 1);
				}

				// If value is less than
				else if(this.getValue() > v){
					if(this.getLeft() != null){
						this.getLeft().insertNode(binarySearchTree, v);
					}
					else{
						this.setLeft(new BinaryNode(binarySearchTree,v));
						this.getLeft().setLevel(this.getLevel()+1);
						this.getLeft().setParent(this);
						this.getBST().setNodeCount(this.getBST().getNodeCount() +1);
					}
				}

				// If value is greater than
				else if(this.getValue() < v){
					if (this.getRight() != null){
						this.getRight().insertNode(binarySearchTree, v);
					}
					else{
						this.setRight(new BinaryNode(binarySearchTree,v));
						this.getRight().setLevel(this.getLevel()+1);
						this.getRight().setParent(this);
						this.getBST().setNodeCount(this.getBST().getNodeCount() +1);
					}
				}

			}

		}

		public BinaryNode deleteNode(BinarySearchTree binarySearchTree, Integer v) {
			if(this.value == null){
				return null;
			}
			else{

				//If value is less than
				if(this.getValue() > v){
					if(this.getLeft() != null){
						return this.getLeft().deleteNode(binarySearchTree, v);
					}
					else{
						return null;
					}

				}

				//If value is greater than
				else if(this.getValue() < v){
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
								BinaryNode temp = new BinaryNode(this.getBST(),this.getValue());
								this.setFrequency(this.getLeft().getFrequency());
								this.setValue(this.getLeft().getValue());
								this.setRight(this.getLeft().getRight());
								this.setLeft(this.getLeft().getLeft());
								return temp;
							}
							else{
								BinaryNode temp = new BinaryNode(this.getBST(),this.getValue());
								this.setFrequency(this.getRight().getFrequency());
								this.setValue(this.getRight().getValue());
								this.setLeft(this.getRight().getLeft());
								this.setRight(this.getRight().getRight());
								return temp;

							}
						}

						//If one child
						else if((this.getLeft() != null) && (this.getRight() == null)){

							BinaryNode temp = new BinaryNode(this.getBST(),this.getValue());
							this.setFrequency(this.getLeft().getFrequency());
							this.setValue(this.getLeft().getValue());
							this.setRight(this.getLeft().getRight());
							this.setLeft(this.getLeft().getLeft());
							return temp;						
						}

						else if((this.getLeft() == null) && (this.getRight() != null)){

							BinaryNode temp = new BinaryNode(this.getBST(),this.getValue());
							this.setFrequency(this.getRight().getFrequency());
							this.setValue(this.getRight().getValue());
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
						int temp = this.getFrequency();
						this.setFrequency(temp-1);
						return this;
					}
				}
			}
		}
		//public String determineValue(String v){
		//	if

		public Integer findNode(BinarySearchTree binarySearchTree, Integer v) {
			
			//If less
			if(this.getValue() > v){
				if(this.getLeft() != null){
					return this.getLeft().findNode(binarySearchTree, v);
				}
				else{
					//System.out.printf("\nFind Result: 0\n");
					return null;
				}
			}
			//If greater
			else if(this.getValue() < v){
				if(this.getRight() != null){
					return this.getRight().findNode(binarySearchTree, v);
				}
				else{
					//System.out.printf("\nFind Result: 0\n", v);
					return null;
				}
			}
			//If equal
			else if(this.getValue() == v){
				//System.out.printf("\nFind Result: %d\n",this.getFrequency());
				return this.getValue();
			}
			//Supposed to be here but isn't
			else{
				//System.out.printf("\nFind Result: 0\n");
				return null;
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
		
	//Private
	private BinaryNode root;
	private BinaryNode min;
	private int nodeCount;
	private int minHeight;
	private int maxHeight;


	/** Constructor for Binary Search Tree Class
	 * 
	 */
	public BinarySearchTree(){
		this.root = null;
		this.maxHeight = 0;
		this.minHeight = 0;
		this.nodeCount = 0;
	}

	public int getSize() {
		return nodeCount;
	}

	public int getMaxHeight() {
		return maxHeight;
	}

	public void setMaxHeight(int maxHeight) {
		this.maxHeight = maxHeight;
	}

	public int getMinHeight() {
		return minHeight;
	}

	public void setMinHeight(int minHeight) {
		this.minHeight = minHeight;
	}

	public int getNodeCount() {
		return nodeCount;
	}

	public void setNodeCount(int nodeCount) {
		this.nodeCount = nodeCount;
	}

	public void setMin(BinaryNode v){
		this.min = v;
	}

	public BinaryNode getMin() {
		return this.min;
	}

	public BinaryNode getRoot() {
		return this.root;
	}

	public void setRoot(BinaryNode r) {
		this.root = r;
	}

	/** Inserts a Node into the Binary Search Tree
	 * 
	 * @param strNode String value for the new node
	 */
	public void insertNode(Integer strNode){

		//First node as root
		if(this.root == null){
			BinaryNode n = new BinaryNode(this,strNode);
			n.setLevel(1);
			this.root = n;
			this.min = n;
			this.setNodeCount(1);
		}
		//Insert into tree
		else{
			this.root.insertNode(this,strNode);
		}

	}

	/** Deletes a Node from the Binary Search Tree. Decreases the frequency if it is greater than 1
	 * 
	 * @param strNode String value for the node to be deleted
	 */
	public Integer deleteNode(Integer strNode){

		if(this.root == null){
			//System.out.println("The tree is empty and cannot delete");
			return null;
		}
		else{
			BinaryNode confirmDeletion = this.root.deleteNode(this,strNode);
			if(confirmDeletion != null){
				//System.out.printf("\nDeleted Node: %s\nNew Node frequency: %d\n",confirmDeletion.getValue(),confirmDeletion.getFrequency());
				resetLevels(this.root);
				return confirmDeletion.value;
			}
			else{
				//System.out.printf("\nThe Node: %s was not found. Frequency: 0\n",strNode);
				return null;
			}
		}
	}

	/** Finds a node in the Binary Search Tree and returns the frequency
	 * 
	 * @param strNode String value of the node we are searching for
	 */
	public Integer findNode(Integer strNode){
		if(this.root == null){
			//System.out.println("\nFind Result: 0\n");
			return null;
		}
		else{
			return this.root.findNode(this,strNode);
		}
	}

	/** Helper method to print a Pre-Order Traversal
	 * 
	 * @param bNode Prints the node and traverses to the left and right child
	 */
	public void preOrderTraversal(BinaryNode bNode){
		//Print Node
		System.out.println("|" + bNode.getValue() + "|");
		//Go Left
		if(bNode.getLeft()!= null){
			preOrderTraversal(bNode.getLeft());
		}
		//Go Right
		if(bNode.getRight()!= null){
			preOrderTraversal(bNode.getRight());
		}
	}

	/** Prints the Breadth Traversal for the Binary Search Tree, implemented with Queue
	 * 
	 * @param bNode Node to Traverse
	 */
	public void printBreadthTraversal(BinaryNode bNode){
		BQueue<BinaryNode> queue= new BQueue<BinaryNode>();
		int level = 1;
		bNode.setLevel(level);
		queue.enqueue(bNode);
		BinaryNode temp = null;
		BinaryNode prev = bNode;
		while(queue.getSize() != 0){
			prev = temp;
			temp = queue.dequeue();
			//If root
			if(bNode == temp){
				System.out.printf("\n%d: %s(%s)%dX\n", temp.getLevel(),temp.getValue(), temp.getValue(), temp.getFrequency());
			}
			else{
				//If same level
				if(prev.getLevel() == temp.getLevel()){
					//If left
					if(temp.getParent().getLeft() == temp){
						if((queue.getSize() != 0) && (queue.peek().getLevel() == temp.getLevel())){
							//If leaf
							if((temp.getLeft() == null) && (temp.getRight() == null)){
								System.out.printf("=%s(%s)%dL ",temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
							}
							else{
								System.out.printf("%s(%s)%dL ",temp.getValue(), temp.getParent().getValue(),temp.getFrequency());

							}
						}
						else{
							if((temp.getLeft() == null) && (temp.getRight() == null)){
								System.out.printf("=%s(%s)%dL\n",temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
							}
							else{
								System.out.printf("%s(%s)%dL\n",temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
							}
						}
					}
					//If right
					else{
						//Next is not a new level
						if((queue.getSize()!= 0)  && (queue.peek().getLevel() == temp.getLevel())){
							//If leaf
							if((temp.getLeft() == null) && (temp.getRight() == null)){
								System.out.printf("=%s(%s)%dR ",temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
							}
							else{
								System.out.printf("%s(%s)%dR ",temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
							}
						}
						//Next is new level
						else{
							if((temp.getLeft() == null) && (temp.getRight() == null)){
								System.out.printf("=%s(%s)%dR\n",temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
							}
							else{
								System.out.printf("%s(%s)%dR\n",temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
							}
						}
					}
				}
				//If new level
				else{
					//If left
					if(temp.getParent().getLeft() == temp){

						//If next is not a new level
						if((queue.getSize() != 0) && (queue.peek().getLevel() == temp.getLevel())){
							//If leaf
							if((temp.getLeft() == null) && (temp.getRight() == null)){
								System.out.printf("%d: =%s(%s)%dL ", temp.getLevel(),temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
							}
							else{
								System.out.printf("%d: %s(%s)%dL ", temp.getLevel(),temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
							}
						}
						//If next is a new level
						else{
							//Is leaf
							if((temp.getLeft() == null) && (temp.getRight() ==null)){
								System.out.printf("%d: =%s(%s)%dL\n", temp.getLevel(),temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
							}
							else{
								System.out.printf("%d: %s(%s)%dL\n", temp.getLevel(),temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
							}
						}
					}
					//If right
					else{
						if((queue.getSize() != 0) && (queue.peek().getLevel() == temp.getLevel())){
							//If leaf
							if((temp.getLeft() == null) && (temp.getRight() == null)){
								System.out.printf("%d: =%s(%s)%dR ", temp.getLevel(),temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
							}
							else{
								System.out.printf("%d: %s(%s)%dR ", temp.getLevel(),temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
							}
						}
						else{
							if((temp.getLeft() == null) && (temp.getRight() == null)){
								System.out.printf("%d: =%s(%s)%dR\n", temp.getLevel(),temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
							}
							else{
								System.out.printf("%d: %s(%s)%dR\n", temp.getLevel(),temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
							}
						}
					}
				}
			}
			if(temp.getLeft() != null){
				queue.enqueue(temp.getLeft());
			}
			if(temp.getRight() != null){
				queue.enqueue(temp.getRight());
			}
			level++;
		}
		System.out.println();
	}

	/** Prints the Statistics for the Binary Search Tree by reporting the number of nodes, Min-Height and Max-Height
	 * 
	 */
	public void printStatistics(){
		calculateMinMax(this.root);
		System.out.println("\nStatistics for the Binary Search Tree");
		System.out.println("=============================================");
		System.out.printf("Number of the Nodes in the Tree	|%d\n",this.getNodeCount());
		System.out.printf("Minimum Depth of the Tree	|%d\n",this.getMinHeight());
		System.out.printf("Maximum Depth of the Tree	|%d\n",this.getMaxHeight());
	}

	/** Traverses the Binary Search tree to Calculate the Min and Max Height 
	 * 
	 * @param bNode Node to begin Traversal at
	 */
	private void calculateMinMax(BinaryNode bNode) {
		BQueue<BinaryNode> queue= new BQueue<BinaryNode>();
		int level = 1;
		boolean trigger = false;
		bNode.setLevel(level);
		queue.enqueue(bNode);
		BinaryNode temp = null;
		BinaryNode prev = null;
		while(queue.getSize() != 0){
			prev = temp;
			temp = queue.dequeue();
			//If root
			if((bNode == temp) &&((temp.getRight() != null) || (temp.getLeft() != null))){
				this.setMinHeight(temp.getLevel()+1);
			}
			else{
				//If it is the shortest
				if((temp.getLeft() == null) && (temp.getRight() == null) && (trigger == false)){
					this.setMinHeight(temp.getLevel());
					trigger = true;
				}
				//If it is the longest
				if(temp.getLevel() > this.getMaxHeight()){
					this.setMaxHeight(temp.getLevel());
				}
			}
			if(temp.getLeft() != null){
				queue.enqueue(temp.getLeft());
			}
			if(temp.getRight() != null){
				queue.enqueue(temp.getRight());
			}
			level++;
		}
	}

	/** Helper method to reset the node levels in the Binary Search Tree
	 * 
	 * @param node Node to Reset Level, proceeds to children
	 */
	private void resetLevels(BinaryNode node) {
		if(node.getLeft() != null){
			node.getLeft().setLevel(node.getLevel()+1);
			resetLevels(node.getLeft());
		}
		if(node.getRight() != null){
			node.getRight().setLevel(node.getLevel()+1);
			resetLevels(node.getRight());
		}
		return;
	}

}
