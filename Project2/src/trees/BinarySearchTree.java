package trees;
import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree {

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
	public void insertNode(String strNode){

		//First node as root
		if(this.root == null){
			BinaryNode n = new BinaryNode(this,strNode);
			n.setLevel(1);
			this.root = n;
			this.min = n;
		}
		//Insert into tree
		else{
			this.root.insertNode(this,strNode);
			this.setNodeCount(this.getNodeCount() + 1);
		}

	}

	/** Deletes a Node from the Binary Search Tree. Decreases the frequency if it is greater than 1
	 * 
	 * @param strNode String value for the node to be deleted
	 */
	public void deleteNode(String strNode){

		if(this.root == null){
			System.out.println("The tree is empty and cannot delete");
		}
		else{
			BinaryNode confirmDeletion = this.root.deleteNode(this,strNode);
			if(confirmDeletion != null){
				System.out.printf("\nDeleted Node: %s\nNew Node frequency: %d\n",confirmDeletion.getValue(),confirmDeletion.getFrequency());
				resetLevels(this.root);
			}
			else{
				System.out.printf("\nThe Node: %s was not found\n",strNode);
			}
		}
	}

	/** Finds a node in the Binary Search Tree and returns the frequency
	 * 
	 * @param strNode String value of the node we are searching for
	 */
	public void findNode(String strNode){
		if(this.root == null){
			System.out.println("\nThere is no tree to find frequencies in.\n");
			return;
		}
		else{
			this.root.findNode(this,strNode);
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
				System.out.printf("%d: %s(%s)%dX\n", temp.getLevel(),temp.getValue(), temp.getValue(), temp.getFrequency());
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
	}

	/** Prints the Statistics for the Binary Search Tree by reporting the number of nodes, Min-Height and Max-Height
	 * 
	 */
	public void printStatistics(){
		calculateMinMax(this.root);
		System.out.println("\n\nStatistics for the Binary Search Tree");
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
				if((temp.getLeft() == null) && (temp.getRight() == null) && (temp.getLevel() < this.getMinHeight())){
					this.setMinHeight(temp.getLevel());
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
