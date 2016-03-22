import java.util.LinkedList;
import java.util.Queue;

public class RBTree {

	//Private
	private RBNode root;
	private RBNode min;
	private int nodeCount;
	private int minHeight;
	private int maxHeight;
	public RBNode getRoot() {
		return root;
	}
	public void setRoot(RBNode root) {
		this.root = root;
	}
	public RBNode getMin() {
		return min;
	}
	public void setMin(RBNode min) {
		this.min = min;
	}
	public int getNodeCount() {
		return nodeCount;
	}
	public void setNodeCount(int nodeCount) {
		this.nodeCount = nodeCount;
	}
	public int getMinHeight() {
		return minHeight;
	}
	public void setMinHeight(int minHeight) {
		this.minHeight = minHeight;
	}
	public int getMaxHeight() {
		return maxHeight;
	}
	public void setMaxHeight(int maxHeight) {
		this.maxHeight = maxHeight;
	}

	/** Constructor for the Red Black Tree
	 * 
	 */
	public RBTree(){
		this.setRoot(root);
		this.setMaxHeight(0);
		this.setMinHeight(0);
		this.setNodeCount(0);
	}

	public void insertNode(String v){
		if(this.root == null){
			RBNode n = new RBNode(this,v);
			n.setLevel(1);
			n.setColor("black");
			n.setGrandparent(null);
			n.setParent(null);
			this.root = n;
			this.min = n;
		}
		else{
			this.root.insertNode(this,v);
			this.resetRoot(this.root);
			System.out.println();
			this.preOrderTraversal(this.root);
		}

	}

	public void deleteNode(String v){
		if(this.root == null){

		}
		else{
			RBNode confirmDeletion = this.root.deleteNode(this,v);
			if(confirmDeletion != null){
				System.out.printf("Deleted Node: %s\nNew Node frequency: %d\n\n", confirmDeletion.getValue(),confirmDeletion.getFrequency());
			}
			else{
				System.out.printf("The Node: %s was not found\n\n", v);
			}
		}
	}

	public void preOrderTraversal(RBNode n){
		//Print Node
		System.out.println("|" + n.getValue() + "|");
		//Go Left
		if(n.getLeft()!= null){
			preOrderTraversal(n.getLeft());
		}
		if(n.getRight()!= null){
			preOrderTraversal(n.getRight());
		}
	}

	public void printBreadthTraversal(RBNode n){
		Queue<RBNode> queue= new LinkedList<RBNode>();
		int level = 1;
		n.setLevel(level);
		queue.add(n);
		RBNode temp = null;
		RBNode prev = n;
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
								if(temp.getColor().equals("red")&&(temp.getParent().getColor().equals("black"))){
									System.out.printf("=%s*(%s*)%dL ",temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
								}
								else if(temp.getColor().equals("red")&&(temp.getParent().getColor().equals("black"))){
									System.out.printf("=%s*(%s)%dL ",temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
								}
								else if(temp.getColor().equals("black")&&temp.getParent().getColor().equals("red")){
									System.out.printf("=%s(%s*)%dL ",temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
								}
								else{
									System.out.printf("=%s(%s)%dL ",temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
								}
							}
							else{
								if(temp.getColor().equals("red")&&(temp.getParent().getColor().equals("red"))){
									System.out.printf("%s*(%s*)%dL ",temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
								}
								else if(temp.getColor().equals("red")&&(temp.getParent().getColor().equals("black"))){
									System.out.printf("%s*(%s)%dL ",temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
								}
								else if(temp.getColor().equals("black")&&(temp.getParent().getColor().equals("red"))){
									System.out.printf("%s(%s*)%dL ",temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
								}
								else{
									System.out.printf("%s(%s)%dL ",temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
								}
							}
						}
						else{
							if((temp.getLeft() == null) && (temp.getRight() == null)){
								if(temp.getColor().equals("red")&&(temp.getParent().getColor().equals("red"))){
									System.out.printf("=%s*(%s*)%dL\n",temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
								}
								else if(temp.getColor().equals("red")&&(temp.getParent().getColor().equals("black"))){
									System.out.printf("=%s*(%s)%dL\n",temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
								}
								else if(temp.getColor().equals("black")&&(temp.getParent().getColor().equals("red"))){
									System.out.printf("=%s(%s*)%dL\n",temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
								}
								else{
									System.out.printf("=%s(%s)%dL\n",temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
								}
							}
							else{
								if(temp.getColor().equals("red")&&(temp.getParent().getColor().equals("red"))){
									System.out.printf("%s*(%s*)%dL\n",temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
								}
								else if(temp.getColor().equals("red")&&(temp.getParent().getColor().equals("black"))){
									System.out.printf("%s*(%s)%dL\n",temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
								}
								else if(temp.getColor().equals("black")&&(temp.getParent().getColor().equals("red"))){
									System.out.printf("%s(%s*)%dL\n",temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
								}
								else{
									System.out.printf("%s(%s)%dL\n",temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
								}
							}
						}
					}
					//If right
					else{
						//Next is not a new level
						if((queue.isEmpty() == false) && (queue.peek().getLevel() == temp.getLevel())){
							//If leaf
							if((temp.getLeft() == null) && (temp.getRight() == null)){
								if(temp.getColor().equals("red")&&(temp.getParent().getColor().equals("red"))){
									System.out.printf("=%s*(%s*)%dR ",temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
								}
								else if(temp.getColor().equals("red")&&(temp.getParent().getColor().equals("black"))){
									System.out.printf("=%s*(%s)%dR ",temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
								}
								else if(temp.getColor().equals("black")&&(temp.getParent().getColor().equals("red"))){
									System.out.printf("=%s(%s*)%dR ",temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
								}
								else{
									System.out.printf("=%s(%s)%dR ",temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
								}
							}
							else{
								if(temp.getColor().equals("red")&&(temp.getParent().getColor().equals("red"))){
									System.out.printf("%s*(%s*)%dR ",temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
								}
								else if(temp.getColor().equals("red")&&(temp.getParent().getColor().equals("black"))){
									System.out.printf("%s*(%s)%dR ",temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
								}
								else if(temp.getColor().equals("black")&&(temp.getParent().getColor().equals("red"))){
									System.out.printf("%s(%s*)%dR ",temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
								}
								else{
									System.out.printf("%s(%s)%dR ",temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
								}
							}
						}
						//Next is new level
						else{
							if((temp.getLeft() == null) && (temp.getRight() == null)){
								if(temp.getColor().equals("red")&&(temp.getParent().getColor().equals("red"))){
									System.out.printf("=%s*(%s*)%dR\n",temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
								}
								else if(temp.getColor().equals("red")&&(temp.getParent().getColor().equals("black"))){
									System.out.printf("=%s*(%s)%dR\n",temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
								}
								else if(temp.getColor().equals("black")&&(temp.getParent().getColor().equals("red"))){
									System.out.printf("=%s(%s*)%dR\n",temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
								}
								else{
									System.out.printf("=%s(%s)%dR\n",temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
								}
							}
							else{
								if(temp.getColor().equals("red")&&(temp.getParent().getColor().equals("red"))){
									System.out.printf("%s*(%s*)%dR\n",temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
								}
								else if(temp.getColor().equals("red")&&(temp.getParent().getColor().equals("black"))){
									System.out.printf("%s*(%s)%dR\n",temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
								}
								else if(temp.getColor().equals("black")&&(temp.getParent().getColor().equals("red"))){
									System.out.printf("%s(%s*)%dR\n",temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
								}
								else{
									System.out.printf("%s(%s)%dR\n",temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
								}
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
								if(temp.getColor().equals("red")&&(temp.getParent().getColor().equals("red"))){
									System.out.printf("%d: =%s*(%s*)%dL ", temp.getLevel(),temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
								}
								else if(temp.getColor().equals("red")&&(temp.getParent().getColor().equals("black"))){
									System.out.printf("%d: =%s*(%s)%dL ", temp.getLevel(),temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
								}
								else if(temp.getColor().equals("black")&&(temp.getParent().getColor().equals("red"))){
									System.out.printf("%d: =%s(%s*)%dL ", temp.getLevel(),temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
								}
								else{
									System.out.printf("%d: =%s(%s)%dL ", temp.getLevel(),temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
								}
							}
							else{
								if(temp.getColor().equals("red")&&(temp.getParent().getColor().equals("red"))){
									System.out.printf("%d: %s*(%s*)%dL ", temp.getLevel(),temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
								}
								else if(temp.getColor().equals("red")&&(temp.getParent().getColor().equals("black"))){
									System.out.printf("%d: %s*(%s)%dL ", temp.getLevel(),temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
								}
								else if(temp.getColor().equals("black")&&(temp.getParent().getColor().equals("red"))){
									System.out.printf("%d: %s(%s*)%dL ", temp.getLevel(),temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
								}
								else{
									System.out.printf("%d: %s(%s)%dL ", temp.getLevel(),temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
								}
							}
						}
						//If next is a new level
						else{
							//Is leaf
							if((temp.getLeft() == null) && (temp.getRight() ==null)){
								if(temp.getColor().equals("red")&&(temp.getParent().getColor().equals("red"))){
									System.out.printf("%d: =%s*(%s*)%dL\n", temp.getLevel(),temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
								}
								else if(temp.getColor().equals("red")&&(temp.getParent().getColor().equals("black"))){
									System.out.printf("%d: =%s*(%s)%dL\n", temp.getLevel(),temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
								}
								else if(temp.getColor().equals("black")&&(temp.getParent().getColor().equals("red"))){
									System.out.printf("%d: =%s(%s*)%dL\n", temp.getLevel(),temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
								}
								else{
									System.out.printf("%d: =%s(%s)%dL\n", temp.getLevel(),temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
								}
							}
							else{
								if(temp.getColor().equals("red")&&(temp.getParent().getColor().equals("red"))){
									System.out.printf("%d: %s*(%s*)%dL\n", temp.getLevel(),temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
								}
								else if(temp.getColor().equals("red")&&(temp.getParent().getColor().equals("black"))){
									System.out.printf("%d: %s*(%s)%dL\n", temp.getLevel(),temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
								}
								else if(temp.getColor().equals("black")&&(temp.getParent().getColor().equals("red"))){
									System.out.printf("%d: %s(%s*)%dL\n", temp.getLevel(),temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
								}
								else{
									System.out.printf("%d: %s(%s)%dL\n", temp.getLevel(),temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
								}
							}
						}
					}
					//If right
					else{
						if((queue.isEmpty() == false) && (queue.peek().getLevel() == temp.getLevel())){
							//If leaf
							if((temp.getLeft() == null) && (temp.getRight() == null)){
								if(temp.getColor().equals("red")&&(temp.getParent().getColor().equals("red"))){
									System.out.printf("%d: =%s*(%s*)%dR ", temp.getLevel(),temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
								}
								else if(temp.getColor().equals("red")&&(temp.getParent().getColor().equals("black"))){
									System.out.printf("%d: =%s*(%s)%dR ", temp.getLevel(),temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
								}
								else if(temp.getColor().equals("black")&&(temp.getParent().getColor().equals("red"))){
									System.out.printf("%d: =%s(%s*)%dR ", temp.getLevel(),temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
								}
								else{
									System.out.printf("%d: =%s(%s)%dR ", temp.getLevel(),temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
								}
							}
							else{
								if(temp.getColor().equals("red")&&(temp.getParent().getColor().equals("red"))){
									System.out.printf("%d: %s*(%s*)%dR ", temp.getLevel(),temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
								}
								else if(temp.getColor().equals("red")&&(temp.getParent().getColor().equals("black"))){
									System.out.printf("%d: %s*(%s)%dR ", temp.getLevel(),temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
								}
								else if(temp.getColor().equals("black")&&(temp.getParent().getColor().equals("red"))){
									System.out.printf("%d: %s(%s*)%dR ", temp.getLevel(),temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
								}
								else{
									System.out.printf("%d: %s(%s)%dR ", temp.getLevel(),temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
								}
							}
						}
						else{
							if((temp.getLeft() == null) && (temp.getRight() == null)){
								if(temp.getColor().equals("red")&&(temp.getParent().getColor().equals("red"))){
									System.out.printf("%d: =%s*(%s*)%dR\n", temp.getLevel(),temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
								}
								else if(temp.getColor().equals("red")&&(temp.getParent().getColor().equals("black"))){
									System.out.printf("%d: =%s*(%s)%dR\n", temp.getLevel(),temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
								}
								else if(temp.getColor().equals("black")&&(temp.getParent().getColor().equals("red"))){
									System.out.printf("%d: =%s(%s*)%dR\n", temp.getLevel(),temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
								}
								else{
									System.out.printf("%d: =%s(%s)%dR\n", temp.getLevel(),temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
								}
							}
							else{
								if(temp.getColor().equals("red")&&(temp.getParent().getColor().equals("red"))){
									System.out.printf("%d: %s*(%s*)%dR\n", temp.getLevel(),temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
								}
								else if(temp.getColor().equals("red")&&(temp.getParent().getColor().equals("black"))){
									System.out.printf("%d: %s*(%s)%dR\n", temp.getLevel(),temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
								}
								else if(temp.getColor().equals("black")&&(temp.getParent().getColor().equals("red"))){
									System.out.printf("%d: %s(%s*)%dR\n", temp.getLevel(),temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
								}
								else{
									System.out.printf("%d: %s(%s)%dR\n", temp.getLevel(),temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
								}
							}
						}
					}
				}
			}
			if(temp.getLeft() != null){
				temp.getLeft().setLevel(temp.getLevel()+1);
				queue.add(temp.getLeft());
			}
			if(temp.getRight() != null){
				temp.getRight().setLevel(temp.getLevel()+1);
				queue.add(temp.getRight());
			}
			level++;
		}
	}

	public void resetRoot(RBNode n) {
		if(n.getParent() != null){
			n.getRBT().setRoot(n.getParent());
			resetRoot(n.getParent());
		}
		else{
			n.getRBT().setRoot(n);
			return;
		}
	}
}
