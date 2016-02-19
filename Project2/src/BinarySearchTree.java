import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree {

	//Private
	BinaryNode root;
	BinaryNode min;
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
			boolean confirmDeletion = this.root.deleteNode(this,v);
			if(confirmDeletion == true){
				
			}
			else{
				
			}
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
	public void levelOrderTraversal(BinaryNode n){
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
			if(n == temp){
				System.out.printf("%d: %s(%s)%dX", temp.getLevel(),temp.getValue(), temp.getValue(), temp.getFrequency());
			}
			else{
				//If same level
				if(prev.getLevel() == temp.getLevel()){
					//If left
					if(temp.getParent().getLeft() == temp){
						System.out.printf("%s(%s)%dL ",temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
					}
					//If right
					else{
						System.out.printf("%s(%s)%dR ",temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
					}
				}
				//If new level
				else{
					//If left
					if(temp.getParent().getLeft() == temp){
				
						//If next is a new level
						if((queue.isEmpty() == false) && (queue.peek().getLevel() == temp.getLevel())){
							System.out.printf("\n%d: =%s(%s)%dL ", temp.getLevel(),temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
						}
						//If next is the same level
						else{
							System.out.printf("\n%d: %s(%s)%dL", temp.getLevel(),temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
						}
					}
					//If right
					else{
						if((queue.isEmpty() == false) && (queue.peek().getLevel() == temp.getLevel())){
							System.out.printf("\n%d: =%s(%s)%dR ", temp.getLevel(),temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
						}
						else{
							System.out.printf("\n%d: %s(%s)%dR", temp.getLevel(),temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
						}
					}
				}
			}
			if(temp.getLeft() != null){
				temp.getLeft().setLevel(level+1);
				queue.add(temp.getLeft());
			}
			if(temp.getRight() != null){
				temp.getRight().setLevel(level+1);
				queue.add(temp.getRight());
			}
			level++;
		}
	}
	
	public void printStatistics(){
		System.out.println("\n\nStatistics for the Binary Search Tree");
		System.out.println("=============================================");
		System.out.printf("Number of the Nodes in the Tree	|%d\n",this.getNodeCount());
		System.out.printf("Minimum Depth of the Tree	|%d\n",this.getMinHeight());
		System.out.printf("Maximum Depth of the Tree	|%d\n",this.getMaxHeight());
	}

	public BinaryNode getMin() {
		return this.min;
	}
	
	public void setMin(BinaryNode v){
		this.min = v;
	}

}
