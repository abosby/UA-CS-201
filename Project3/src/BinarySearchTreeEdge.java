import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTreeEdge {

	//Private class
	private class BinaryNode{

		private BinarySearchTreeEdge BST;
		private Edge value;
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
		public BinaryNode(BinarySearchTreeEdge BST, Edge v){
			this.setBST(BST);
			this.setValue(v);
			this.setFrequency(1);
			this.setLevel(0);
			this.setParent(null);
			this.setLeft(null);
			this.setRight(null);
		}

		public BinarySearchTreeEdge getBST() {
			return BST;
		}

		public void setBST(BinarySearchTreeEdge bST) {
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

		public Edge getValue() {
			return value;
		}

		public void setValue(Edge value) {
			this.value = value;
		}

		/** Inserts a string into the Binary Tree under the current node
		 * 	Adapted insert function from http://vitalflux.com/java-create-binary-search-tree-string-search/
		 * @param binarySearchTree 
		 * @param v The value to be inserted
		 */
		public void insertNode(BinaryNode node,Edge v){

			while(true){
				//Check value of Node
				if(node.value == null){
					node.setValue(v);
					node.setLevel(1);

				}

				else{

					// If value is equal , increase frequency
					if(node.getValue().getVertex1().getValue() == v.getVertex1().getValue()){
						if(node.getValue().getVertex2().getValue() == v.getVertex2().getValue()){
							System.out.println("Same Node here");
							break;
						}
						// If value is less than
						else if(node.getValue().getVertex2().getValue() > v.getVertex2().getValue()){
							if(node.getLeft() != null){
								node = node.left;
							}
							else{
								node.setLeft(new BinaryNode(node.BST,v));
								node.getLeft().setLevel(node.getLevel()+1);
								node.getLeft().setParent(node);
								node.getBST().setNodeCount(node.getBST().getNodeCount() +1);
								break;
							}
						}

						// If value is greater than
						else if(node.getValue().getVertex2().getValue() < v.getVertex2().getValue()){
							if (node.getRight() != null){
								node = node.right;
							}
							else{
								node.setRight(new BinaryNode(node.BST,v));
								node.getRight().setLevel(node.getLevel()+1);
								node.getRight().setParent(node);
								node.getBST().setNodeCount(node.getBST().getNodeCount() +1);
								break;
							}
						}
					}

					// If value is less than
					else if(node.getValue().getVertex1().getValue() > v.getVertex1().getValue()){
						if(node.getLeft() != null){
							node = node.left;
						}
						else{
							node.setLeft(new BinaryNode(node.BST,v));
							node.getLeft().setLevel(node.getLevel()+1);
							node.getLeft().setParent(node);
							node.getBST().setNodeCount(node.getBST().getNodeCount() +1);
							break;
						}
					}

					// If value is greater than
					else if(node.getValue().getVertex1().getValue() < v.getVertex1().getValue()){
						if (node.getRight() != null){
							node = node.right;
						}
						else{
							node.setRight(new BinaryNode(node.BST,v));
							node.getRight().setLevel(node.getLevel()+1);
							node.getRight().setParent(node);
							node.getBST().setNodeCount(node.getBST().getNodeCount() +1);
							break;
						}
					}
				}

			}

		}

		public BinaryNode deleteNode(BinaryNode node, Vertex v1, Vertex v2) {
			while(true){
				if(node.value == null){
					return null;
				}
				else{

					//If value is less than
					if(node.getValue().getVertex1().getValue() > v1.getValue()){
						if(node.getLeft() != null){
							node = node.left;
						}
						else{
							return null;
						}

					}

					//If value is greater than
					else if(node.getValue().getVertex1().getValue() < v1.getValue()){
						if(node.getRight() != null){
							node = node.right;
						}
						else{
							return null;
						}
					}

					//If value is equal 
					else{
						if(node.getValue().getVertex2().getValue() > v2.getValue()){
							if(node.getLeft() != null){
								node = node.left;
							}
							else{
								return null;
							}
						}
						else if(node.getValue().getVertex2().getValue() < v2.getValue()){
							if(node.getRight() != null){
								node = node.right;
							}
							else{
								return null;
							}
						}
						else{
							//If no child
							if((node.getLeft() == null) && (node.getRight() == null)){
								if(node.getParent().getLeft() != null){
									if(node.getParent().left.value == node.value){
										node.getParent().setLeft(null);
										node.resetRoot();
										return node;
									}
									else if(node.getParent().getRight() != null){
										if(node.getParent().right.value == node.value){
											node.getParent().setRight(null);
											node.resetRoot();
											return node;
										}
									}
								}
								else{
									if(node.getParent().getRight() != null){
										if(node.getParent().right.value == node.value){
											node.getParent().setRight(null);
											node.resetRoot();
											return node;
										}
									}
									else{
										return null;
									}
								}
							}
							//One Child
							else if(node.left != null){
								BinaryNode temp = new BinaryNode(node.BST,node.value);
								node.setValue(node.left.value);
								node.setRight(node.left.right);
								node.setLeft(node.left.left);
								//node.resetRoot();
								return temp;
							}
							else if(node.right != null){
								BinaryNode temp = new BinaryNode(node.BST,node.value);
								node.setValue(node.right.value);
								node.setLeft(node.right.getLeft());
								node.setRight(node.right.right);
								//node.resetRoot();
								return temp;
							}
							//Two children
							else{
								BinaryNode temp2 = new BinaryNode(node.BST,node.value);
								BinaryNode temp = node.right.getMin();
								node.setValue(temp.getValue());
								this.BST.root.deleteNode(node.right, temp.getValue().getVertex1(), temp.getValue().getVertex2());
								//node.resetRoot();
								return temp2;

							}
						}
					}
				}
			}
		}

		private void resetRoot() {
			BinaryNode temp = this;
			while(temp.parent != null){
				temp = temp.parent;
			}
			this.BST.root = temp;
		}

		//public String determineValue(String v){
		//	if

		public Edge findNode(BinaryNode node, Vertex v1, Vertex v2) {

			while(true){
				//If less
				if(node.getValue().getVertex1().getValue() > v1.getValue()){
					if(node.getLeft() != null){
						node = node.left;
					}
					else{
						//System.out.printf("\nFind Result: 0\n");
						return null;
					}
				}
				//If greater
				else if(node.getValue().getVertex1().getValue() < v1.getValue()){
					if(node.getRight() != null){
						node = node.right;
					}
					else{
						//System.out.printf("\nFind Result: 0\n", v);
						return null;
					}
				}
				//If equal
				else if(node.getValue().getVertex1().getValue() == v1.getValue()){
					if(node.getValue().getVertex2().getValue() == v2.getValue()){
						return this.getValue();
					}
					else if(node.getValue().getVertex2().getValue() > v2.getValue()){
						if(node.getLeft() != null){
							node = node.left;
						}
						else{
							return null;
						}
					}
					else if(node.getValue().getVertex2().getValue() < v2.getValue()){
						if(node.getRight() != null){
							node = node.right;
						}
						else{
							return null;
						}
					}
				}
				//Supposed to be here but isn't
				else{
					//System.out.printf("\nFind Result: 0\n");
					return null;
				}
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

		public BinaryNode getMin(){
			BinaryNode temp = this;
			while(temp.left != null){
				temp = temp.left;
			}
			return temp;
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
	public BinarySearchTreeEdge(){
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
	public void insertNode(Edge strNode){

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
			this.root.insertNode(this.root,strNode);
		}

	}

	/** Deletes a Node from the Binary Search Tree. Decreases the frequency if it is greater than 1
	 * 
	 * @param strNode String value for the node to be deleted
	 */
	public Edge deleteNode(Vertex v1, Vertex v2){

		if(this.root == null){
			//System.out.println("The tree is empty and cannot delete");
			return null;
		}
		else{
			BinaryNode confirmDeletion = this.root.deleteNode(this.root,v1, v2);
			if(confirmDeletion != null){
				//System.out.printf("\nDeleted Node: %s\nNew Node frequency: %d\n",confirmDeletion.getValue(),confirmDeletion.getFrequency());
				resetLevels(this.root);
				//System.out.println("Removing :" + v1 + "-" + v2 );
				//this.printBreadthTraversal(getRoot());
				BinaryNode confirmDeletion2 = this.root.deleteNode(this.root, v1, v2);
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
	public Edge findNode(Vertex v1, Vertex v2){
		if(this.root == null){
			//System.out.println("\nFind Result: 0\n");
			return null;
		}
		else{
			return this.root.findNode(this.root,v1, v2);
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
	/**
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
	 */

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
