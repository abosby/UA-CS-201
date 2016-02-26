import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree {

	//Private
	private BinaryNode root;
	private BinaryNode min;
	private int nodeCount;
	private int minHeight;
	private int maxHeight;


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

	/** Constructor for the Binary Search Tree
	 * 
	 */
	public BinarySearchTree(){
		this.root = null;
		this.maxHeight = 0;
		this.minHeight = 0;
		this.nodeCount = 0;
	}

	public void insertNode(String v){

		if(this.root == null){
			BinaryNode n = new BinaryNode(this,v);
			n.setLevel(1);
			this.root = n;
			this.min = n;
		}
		else{
			this.root.insertNode(this,v);
			this.setNodeCount(this.getNodeCount() + 1);
		}

	}

	public void deleteNode(String v){

		if(this.root == null){
			System.out.println("The tree is empty and cannot delete");
		}
		else{
			BinaryNode confirmDeletion = this.root.deleteNode(this,v);
			if(confirmDeletion != null){
				System.out.printf("\nDeleted Node: %s\nNew Node frequency: %d\n",confirmDeletion.getValue(),confirmDeletion.getFrequency());
				resetLevels(this.root);
			}
			else{
				System.out.printf("\nThe Node: %s was not found\n",v);
			}
		}
	}

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

	public void findNode(String v){
		if(this.root == null){
			System.out.println("\nThere is no tree to find frequencies in.\n");
			return;
		}
		else{
			this.root.findNode(this,v);
		}
	}

	public void preOrderTraversal(BinaryNode n){
		//Print Node
		System.out.println("|" + n.getValue() + "|");
		//Go Left
		if(n.getLeft()!= null){
			preOrderTraversal(n.getLeft());
		}
		//Go Right
		if(n.getRight()!= null){
			preOrderTraversal(n.getRight());
		}
	}

	//Need to implement Queue class
	public void printBreadthTraversal(BinaryNode n){
		Queue<BinaryNode> queue= new LinkedList<BinaryNode>();
		int level = 1;
		n.setLevel(level);
		queue.add(n);
		BinaryNode temp = null;
		BinaryNode prev = n;
		while(!queue.isEmpty()){
			prev = temp;
			temp = queue.poll();
			//If root
			if(n == temp){
				System.out.printf("%d: %s(%s)%dX\n", temp.getLevel(),temp.getValue(), temp.getValue(), temp.getFrequency());
			}
			else{
				//If same level
				if(prev.getLevel() == temp.getLevel()){
					//If left
					if(temp.getParent().getLeft() == temp){
						if((queue.isEmpty() == false) && (queue.peek().getLevel() == temp.getLevel())){
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
						if((queue.isEmpty() == false) && (queue.peek().getLevel() == temp.getLevel())){
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
						if((queue.isEmpty() == false) && (queue.peek().getLevel() == temp.getLevel())){
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
						if((queue.isEmpty() == false) && (queue.peek().getLevel() == temp.getLevel())){
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
				queue.add(temp.getLeft());
			}
			if(temp.getRight() != null){
				queue.add(temp.getRight());
			}
			level++;
		}
	}

	public void printStatistics(){
		calculateMinMax(this.root);
		System.out.println("\n\nStatistics for the Binary Search Tree");
		System.out.println("=============================================");
		System.out.printf("Number of the Nodes in the Tree	|%d\n",this.getNodeCount());
		System.out.printf("Minimum Depth of the Tree	|%d\n",this.getMinHeight());
		System.out.printf("Maximum Depth of the Tree	|%d\n",this.getMaxHeight());
	}

	private void calculateMinMax(BinaryNode n) {
		Queue<BinaryNode> queue= new LinkedList<BinaryNode>();
		int level = 1;
		n.setLevel(level);
		queue.add(n);
		BinaryNode temp = null;
		BinaryNode prev = null;
		while(!queue.isEmpty()){
			prev = temp;
			temp = queue.poll();
			//If root
			if((n == temp) &&((temp.getRight() != null) || (temp.getLeft() != null))){
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
				queue.add(temp.getLeft());
			}
			if(temp.getRight() != null){
				queue.add(temp.getRight());
			}
			level++;
		}
	}

	public BinaryNode getMin() {
		return this.min;
	}

	public void setMin(BinaryNode v){
		this.min = v;
	}

	public BinaryNode getRoot() {
		return this.root;
	}
	
	public void setRoot(BinaryNode r) {
		this.root = r;
	}

}
