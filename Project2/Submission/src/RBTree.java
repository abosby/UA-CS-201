import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.Queue;

public class RBTree {

	//Private
	private class RBNode {

		//Private
		private RBTree RBT;
		private String value;
		private String color;
		private int frequency;
		private int level;
		private RBNode grandparent;
		private RBNode parent;
		private RBNode left;
		private RBNode right;

		public RBTree getRBT() {
			return RBT;
		}
		public void setRBT(RBTree rBT) {
			RBT = rBT;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
		public String getColor() {
			return color;
		}
		public void setColor(String color) {
			this.color = color;
		}
		public int getLevel() {
			return level;
		}
		public void setLevel(int level) {
			this.level = level;
		}
		public RBNode getGrandparent() {
			return grandparent;
		}
		public void setGrandparent(RBNode grandparent) {
			this.grandparent = grandparent;
		}
		public RBNode getParent() {
			return parent;
		}
		public void setParent(RBNode parent) {
			this.parent = parent;
		}
		public RBNode getLeft() {
			return left;
		}
		public void setLeft(RBNode left) {
			this.left = left;
		}
		public RBNode getRight() {
			return right;
		}
		public void setRight(RBNode right) {
			this.right = right;
		}
		public int getFrequency() {
			return frequency;
		}
		public void setFrequency(int frequency) {
			this.frequency = frequency;
		}

		/** Constructor for the Red Black Node
		 * 
		 * @param RBT The tree that the Node is a part of
		 * @param v The value of the new node
		 */
		public RBNode(RBTree RBT, String v){
			this.setRBT(RBT);
			this.setValue(v);
			this.setFrequency(1);
			this.setColor("red");
			this.setLevel(0);
			this.setParent(null);
			this.setLeft(null);
			this.setRight(null);
		}
		public void insertNode(RBNode node, String v) {

			while(true){
				if((node.value == null) && (node.getRBT().getRoot() == null)){
					node.setValue(v);
					node.setLevel(1);
					node.RBT.setNodeCount(node.RBT.getNodeCount()+1);
				}
				else{
					if(node.getValue().equals(v)){
						node.setFrequency(node.getFrequency()+1);
						break;
					}
					else if(node.getValue().compareTo(v)>0){
						if(node.getLeft() != null){
							node = node.left;
						}
						else{
							node.setLeft(new RBNode(node.getRBT(),v));
							node.getLeft().setLevel(node.getLevel()+1);
							node.getLeft().setParent(node);
							node.getLeft().setGrandparent(node.getParent());
							node.insertionFixUp(node.getLeft());
							node.RBT.setNodeCount(node.RBT.getNodeCount()+1);
							break;
						}
					}
					else if(node.getValue().compareTo(v)<0){
						if(node.getRight() != null){
							node = node.right;
						}
						else{
							node.setRight(new RBNode(node.getRBT(),v));
							node.getRight().setLevel(node.getLevel()+1);
							node.getRight().setParent(node);
							node.getRight().setGrandparent(node.getParent());
							node.insertionFixUp(node.getRight());
							node.RBT.setNodeCount(node.RBT.getNodeCount()+1);
							break;
						}
					}
				}
			}
		}

		private void insertionFixUp(RBNode node) {
			while(true){
				if(node == this.getRBT().getRoot()){
					break;
				}
				if((node.getParent().getColor().equals("black"))){ 
					break;
				}
				if(uncle(node) != null){
					if(uncle(node).getColor().equals("red")){
						node.getParent().setColor("black");
						uncle(node).setColor("black");
						node.getGrandparent().setColor("red");
						node = node.getGrandparent();
					}
					else{
						//uncle is black 
						//if not linear
						if(needsToRotateLinear(node)){
							RBNode temp = null;
							temp = node.getParent();
							rotateToLinear(node);
							node = temp;
						}
						node.getParent().setColor("black");
						node.getGrandparent().setColor("red");
						rotate(node.getParent());
						//rotate(x);
					}
				}
				//uncle is black 
				else{
					//if not linear
					if(needsToRotateLinear(node)){
						RBNode temp = null;
						temp = node.getParent();
						rotateToLinear(node);
						node = temp;
					}
					node.getParent().setColor("black");
					node.getGrandparent().setColor("red");
					rotate(node.getParent());
					//rotate(x);
				}

			}
			this.getRBT().resetRoot(this.getRBT().getRoot());
			this.getRBT().getRoot().setColor("black");
		}

		public RBNode deleteNode(RBTree rbTree, String v) {
			if(this.value == null){
				return null;
			}
			else{
				//If value is less than
				if(this.getValue().compareTo(v)>0){
					if(this.getLeft() != null){
						return this.getLeft().deleteNode(rbTree, v);
					}
					else{
						return null;
					}
				}

				//If value is greater than
				else if(this.getValue().compareTo(v)<0){
					if(this.getRight() != null){
						return this.getRight().deleteNode(rbTree, v);
					}
					else{
						return null;
					}
				}

				//If value is equal
				else{
					//If there is only one frequency left
					if(this.getFrequency() == 1){
						//If two children
						if((this.getRight() != null) && (this.getLeft() != null)){
							RBNode temp = this;
							this.swapToLeaf(this);
							deletionFixUp(this);
							temp.setFrequency(temp.getFrequency()-1);
							this.RBT.setNodeCount(this.RBT.getNodeCount()-1);
							return temp;
						}

						//If one child
						else if((this.getRight() != null) && (this.getLeft() == null)){
							RBNode temp = this;
							this.swapToLeaf(this);
							deletionFixUp(this);
							temp.setFrequency(temp.getFrequency()-1);
							this.RBT.setNodeCount(this.RBT.getNodeCount()-1);
							return temp;
						}

						else if((this.getRight() == null) && (this.getLeft() != null)){
							RBNode temp = this;
							this.swapToLeaf(this);
							deletionFixUp(this);
							temp.setFrequency(temp.getFrequency()-1);
							this.RBT.setNodeCount(this.RBT.getNodeCount()-1);
							return temp;
						}
						//If no child
						else{
							//Is left child
							if(this.getParent().getLeft() == this){
								this.getParent().setLeft(null);
							}
							//Is right child
							else{
								this.getParent().setRight(null);
							}
							this.setFrequency(this.getFrequency()-1);
							this.RBT.setNodeCount(this.RBT.getNodeCount()-1);
							return this;
						}
					}
					//Reduce frequency
					else{
						this.setFrequency(this.getFrequency()-1);
						return this;
					}
				}
			}
		}
		void deletionFixUp(RBNode x){
			while(true){
				if(x == x.getRBT().getRoot()){
					break;
				}
				if(x.getColor().compareTo("red") == 1){
					break;
				}
				if(x.getSibling() != null){
					if(x.getSibling().getColor().compareTo("red") == 1){
						x.getParent().setColor("red");
						x.getSibling().setColor("black");
						x.getSibling().rotate(x.getParent());
						// should have black sibling now
					}
				}
				else if(x.getNephew() != null){
					if(x.getNephew().getColor().compareTo("red") == 1){
						x.getSibling().setColor(x.getParent().getColor());
						x.getParent().setColor("black");
						x.getNephew().setColor("black");
						x.getSibling().rotate(getParent());
						x = this.getRBT().getRoot();
						//subtree and tree are BH Balanced
					}
				}
				//nephew must be black
				else if(x.getNiece() != null){
					if(x.getNiece().getColor().compareTo("red") == 1){
						x.getNiece().setColor("black");
						x.getSibling().setColor("black");
						x.rotate(x.getSibling());
						//should have red nephew now
					}
				}
				//sibling, niece and nephew are black
				else{
					x.getSibling().setColor("red");
					x = x.getParent();
					//this subtree is BH balanced, but tree is not
				}
			}
			this.getRBT().getRoot().setColor("black");
		}
		public void findNode(String v) {

			//If less
			if(this.getValue().compareTo(v)>0){
				if(this.getLeft() != null){
					this.getLeft().findNode(v);
					return;
				}
				else{
					System.out.printf("\n Find Result: 0\n");
					return;
				}
			}
			//If greater
			else if(this.getValue().compareTo(v)<0){
				if(this.getRight() != null){
					this.getRight().findNode(v);
					return;
				}
				else{
					System.out.printf("\n Find Result: 0\n");
					return;
				}
			}
			//If equal
			else if(this.getValue().equals(v)){
				System.out.printf("\nFind Result: %d \n",this.getFrequency());
				return;
			}
			//Supposed to be here but isn't
			else{
				System.out.printf("\n Find Result: 0\n");
				return;
			}
		}

		private void rotateToLinear(RBNode node) {
			RBNode temp = node.getParent();
			if(temp.getLeft() == node){
				temp.getParent().setRight(node);
				node.setGrandparent(temp.getGrandparent());
				node.setParent(temp.getParent());
				temp.setLeft(node.getRight());
				if(temp.getLeft()!=null){
					if(temp.getLeft().getLeft()!=null){
						temp.getLeft().getLeft().setGrandparent(temp);
					}
					if(temp.getLeft().getRight()!=null){
						temp.getLeft().getRight().setGrandparent(temp);
					}
					temp.getLeft().setParent(temp);
					temp.getLeft().setGrandparent(node);
				}
				temp.setGrandparent(node.getParent());
				temp.setParent(node);
				if(temp.getRight()!=null){
					temp.getRight().setGrandparent(node);
				}
				node.setRight(temp);
				if(node.getLeft()!=null){
					node.getLeft().setGrandparent(node.getGrandparent());
				}
			}
			else{
				temp.getParent().setLeft(node);
				node.setGrandparent(temp.getGrandparent());
				node.setParent(temp.getParent());
				temp.setRight(node.getLeft());
				if(temp.getRight()!= null){
					if(temp.getRight().getLeft()!=null){
						temp.getRight().getLeft().setGrandparent(temp);
					}
					if(temp.getRight().getRight()!=null){
						temp.getRight().getRight().setGrandparent(temp);
					}
					temp.getRight().setParent(temp);
					temp.getRight().setGrandparent(node);
				}
				temp.setGrandparent(node.getParent());
				temp.setParent(node);
				if(temp.getLeft()!=null){
					temp.getLeft().setGrandparent(node);
				}
				node.setLeft(temp);
				if(node.getRight()!= null){
					node.getRight().setGrandparent(node.getParent());
				}
			}
		}
		private void rotate(RBNode node){
			RBNode temp = node.getParent();
			//right rotate
			if(temp.getLeft() == node){
				//Grandparent A
				if(node.getLeft() != null){
					node.getLeft().setGrandparent(temp.getParent());
				}
				//Grandparent B
				if(node.getRight() != null){
					if(node.getRight().getRight()!= null){
						node.getRight().getRight().setGrandparent(temp);
					}
					if(node.getRight().getLeft()!= null){
						node.getRight().getLeft().setGrandparent(temp);
					}
					node.getRight().setGrandparent(node);
					node.getRight().setParent(temp);
				}
				//Grandparent C
				if(temp.getRight() != null){
					temp.getRight().setGrandparent(node);
				}
				node.setGrandparent(temp.getGrandparent());
				node.setParent(temp.getParent());
				temp.setLeft(node.getRight());
				temp.setParent(node);
				temp.setGrandparent(node.getParent());
				node.setRight(temp);
				if(node.getParent() != null){
					if(node.getParent().getLeft() == temp){
						node.getParent().setLeft(node);
					}
					else{
						node.getParent().setRight(node);
					}
					node.getRBT().resetRoot(node.getRBT().getRoot());
				}
			}
			//left rotate
			else{
				//C
				if(node.getRight() != null){
					node.getRight().setGrandparent(temp.getParent());
				}
				//B
				if(node.getLeft() != null){
					if(node.getLeft().getLeft()!= null){
						node.getLeft().getLeft().setGrandparent(temp);
					}
					if(node.getLeft().getRight()!= null){
						node.getLeft().getRight().setGrandparent(temp);
					}
					node.getLeft().setGrandparent(node);
					node.getLeft().setParent(temp);
				}
				//A
				if(temp.getLeft() != null){
					temp.getLeft().setGrandparent(node);
				}
				node.setGrandparent(temp.getGrandparent());
				node.setParent(temp.getParent());
				temp.setRight(node.getLeft());
				temp.setParent(node);
				temp.setGrandparent(node.getParent());
				node.setLeft(temp);
				if(node.getParent() != null){
					if(node.getParent().getLeft() == temp){
						node.getParent().setLeft(node);
					}
					else{
						node.getParent().setRight(node);
					}
				}
				node.getRBT().resetRoot(node.getRBT().getRoot());
			}
			return;
		}

		private boolean isLinear(RBNode x) {
			if(x.getParent() == null){
				return false;
			}
			if(x.getGrandparent() == null){
				return false;
			}

			if(x.getGrandparent().getLeft() == x.getParent()){
				if(x.getParent().getLeft() == x){
					return true;
				}
				else{
					return false;
				}
			}
			else if(x.getGrandparent().getRight() == x.getParent()){
				if(x.getParent().getRight() == x){
					return true;
				}
				else{
					return false;
				}
			}
			else{
				return false;
			}
		}

		private boolean needsToRotateLinear(RBNode x){
			if(x.getParent().getLeft() == x){
				if(x.getGrandparent().getRight() == x.getParent()){
					return true;
				}
				else{
					return false;
				}
			}
			else{
				if(x.getGrandparent().getLeft() == x.getParent()){
					return true;
				}
				else{
					return false;
				}
			}
		}

		private RBNode uncle(RBNode x){
			if(x.getGrandparent() != null){
				if(x.getGrandparent().getLeft() == x.getParent()){
					return x.getGrandparent().getRight();
				}
				else if(x.getGrandparent().getRight() == x.getParent()){
					return x.getGrandparent().getLeft();
				}
				else{
					return null;
				}
			}
			return null;
		}

		void swapToLeaf(RBNode x){
			if(x == this){
				//If x is left child
				if(this.getParent().getLeft()==this){
					//Swap with smallest on x's right side
					this.swapToLeaf(x.getLeft());
				}
				//If x is right child
				else{
					//Swap with largest on x's left side
					this.swapToLeaf(x.getRight());
				}
			}
			else{
				//Go as far Right as possible now
				if(this.getParent().getLeft()==this){
					if(x.getRight() != null){
						this.swapToLeaf(x.getRight());
					}
					//Swap Values and Delete Leaf Node
					else{
						Integer tempInt = x.getFrequency();
						String tempVal = x.getValue();
						if(x.getParent().getLeft() == x){
							x.getParent().setLeft(null);
						}
						else{
							x.getParent().setRight(null);
						}
						//x.setFrequency(this.getFrequency());
						//x.setValue(this.getValue());
						this.setValue(tempVal);
						this.setFrequency(tempInt);
					}

				}
				//Go as far Left as possible now
				else{
					if(x.getLeft() != null){
						this.swapToLeaf(x.getLeft());
					}
					//Swap values
					else{
						Integer tempInt = x.getFrequency();
						String tempVal = x.getValue();
						if(x.getParent().getLeft() == x){
							x.getParent().setLeft(null);
						}
						else{
							x.getParent().setRight(null);
						}
						//x.setFrequency(this.getFrequency());
						//x.setValue(this.getValue());
						this.setValue(tempVal);
						this.setFrequency(tempInt);
					}
				}

			}
		}

		RBNode getSibling(){
			if(this.getParent().getLeft() != null){
				//if left child of parent, return right
				if(this.getParent().getLeft() == this){
					if(this.getParent().getRight() != null){
						return this.getParent().getRight();
					}
					else{
						return null;
					}
				}
				//the right child of parent is 'this'
				else{
					return this.getParent().getLeft();
				}
			}
			//There are not two children of the parent
			else{
				return null;
			}
		}

		RBNode getNephew(){
			if(this.getParent().getLeft()!= null){
				//If left child of parent return right child of sibling if its there
				if(this.getParent().getLeft() == this){
					if(this.getSibling() != null){
						return this.getSibling().getRight();
					}
					else{
						return null;
					}
				}
				//If right child of parent return the left child of sibling if its there
				else{
					if(this.getSibling() != null){
						return this.getSibling().getLeft();
					}
					else{
						return null;
					}
				}
			}
			else{
				return null;
			}
		}

		RBNode getNiece(){
			if(this.getParent().getLeft()!= null){
				//If left child of parent return the left child of sibling if its there
				if(this.getParent().getLeft() == this){
					if(this.getSibling() != null){
						return this.getSibling().getLeft();
					}
					else{
						return null;
					}
				}
				//If right child of parent return right child of sibling if its there
				else{
					if(this.getSibling() != null){
						return this.getSibling().getRight();
					}
					else{
						return null;
					}
				}
			}
			else{
				return null;
			}
		}
	}
	//End RBNode Class

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
			this.setNodeCount(1);
			this.root = n;
			this.min = n;
		}
		else{
			this.root.insertNode(this.root,v);
			this.resetRoot(this.root);
			//System.out.println();
			//this.printBreadthTraversal(this.root);
			//this.preOrderTraversal(this.root);
		}

	}

	public void deleteNode(String v){
		if(this.root == null){

		}
		else{
			RBNode confirmDeletion = this.root.deleteNode(this,v);
			if(confirmDeletion != null){
				//System.out.printf("Deleted Node: %s\nNew Node frequency: %d\n\n", confirmDeletion.getValue(),confirmDeletion.getFrequency());
			}
			else{
				//System.err.printf("\nThe Node: '%s' was not found to delete\n\n", v);
			}
		}
	}

	public void findNode(String v){
		if(this.root == null){
			System.out.printf("\n Find Result: 0\n");
			return;
		}
		else{
			this.root.findNode(v);
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
		BQueue<RBNode> queue= new BQueue<RBNode>();
		int level = 1;
		n.setLevel(level);
		queue.enqueue(n);
		RBNode temp = null;
		RBNode prev = n;
		while(queue.getSize() != 0){
			prev = temp;
			temp = queue.dequeue();
			//If root
			if(n == temp){
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
						if((queue.getSize() != 0) && (queue.peek().getLevel() == temp.getLevel())){
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
						if((queue.getSize() != 0) && (queue.peek().getLevel() == temp.getLevel())){
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
						if((queue.getSize() != 0) && (queue.peek().getLevel() == temp.getLevel())){
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
				queue.enqueue(temp.getLeft());
			}
			if(temp.getRight() != null){
				temp.getRight().setLevel(temp.getLevel()+1);
				queue.enqueue(temp.getRight());
			}
			level++;
		}
		System.out.println();
	}

	public void printStatistics(){
		calculateMinMax(this.root);
		System.out.println("\n\nStatistics for the Red-Black Tree");
		System.out.println("=============================================");
		System.out.printf("Number of the Nodes in the Tree	|%d\n",this.getNodeCount());
		System.out.printf("Minimum Depth of the Tree	|%d\n",this.getMinHeight());
		System.out.printf("Maximum Depth of the Tree	|%d\n\n",this.getMaxHeight());
	}

	private void calculateMinMax(RBNode n) {
		BQueue<RBNode> queue= new BQueue<RBNode>();
		int level = 1;
		boolean trigger = false;
		n.setLevel(level);
		queue.enqueue(n);
		RBNode temp = null;
		RBNode prev = null;
		while(queue.getSize() != 0){
			prev = temp;
			temp = queue.dequeue();
			//If root
			if((n == temp) &&((temp.getRight() != null) || (temp.getLeft() != null))){
				//this.setMinHeight(temp.getLevel()+1);
			}
			else{
				//If it is the shortest
				if(((temp.getLeft() == null) && (temp.getRight() == null)) && (trigger == false)){
					this.setMinHeight(temp.getLevel());
					trigger = true;
				}
				//If it is the longest
				if(temp.getLevel() > this.getMaxHeight()){
					this.setMaxHeight(temp.getLevel());
				}
			}
			if(temp.getLeft() != null){
				temp.getLeft().setLevel(temp.getLevel()+1);
				queue.enqueue(temp.getLeft());
			}
			if(temp.getRight() != null){
				temp.getRight().setLevel(temp.getLevel()+1);
				queue.enqueue(temp.getRight());
			}
			level++;
		}
	}

	public void resetRoot(RBNode n) {
		while(true){
			if(n.getParent() != null){
				n.getRBT().setRoot(n.getParent());
				n = n.getParent();
			}
			else{
				n.getRBT().setRoot(n);
				break;
			}
		}
	}
	public void outputBreadthTraversal(RBNode n){

		PrintWriter writer;
		String curDir = System.getProperty("user.dir");
		try {
			writer = new PrintWriter(curDir + "/src/rbtchecker/output.txt","UTF-8");


			BQueue<RBNode> queue= new BQueue<RBNode>();
			int level = 1;
			n.setLevel(level);
			queue.enqueue(n);
			RBNode temp = null;
			RBNode prev = n;
			while(queue.getSize() != 0){
				prev = temp;
				temp = queue.dequeue();
				//If root
				if(n == temp){
					writer.printf("%d: %s(%s)%dX\n", temp.getLevel(),temp.getValue(), temp.getValue(), temp.getFrequency());
				}
				else{
					//If same level
					if(prev.getLevel() == temp.getLevel()){
						//If left
						if(temp.getParent().getLeft() == temp){
							if((queue.getSize() != 0) && (queue.peek().getLevel() == temp.getLevel())){
								//If leaf
								if((temp.getLeft() == null) && (temp.getRight() == null)){
									if(temp.getColor().equals("red")&&(temp.getParent().getColor().equals("black"))){
										writer.printf("=%s*(%s*)%dL ",temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
									}
									else if(temp.getColor().equals("red")&&(temp.getParent().getColor().equals("black"))){
										writer.printf("=%s*(%s)%dL ",temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
									}
									else if(temp.getColor().equals("black")&&temp.getParent().getColor().equals("red")){
										writer.printf("=%s(%s*)%dL ",temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
									}
									else{
										writer.printf("=%s(%s)%dL ",temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
									}
								}
								else{
									if(temp.getColor().equals("red")&&(temp.getParent().getColor().equals("red"))){
										writer.printf("%s*(%s*)%dL ",temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
									}
									else if(temp.getColor().equals("red")&&(temp.getParent().getColor().equals("black"))){
										writer.printf("%s*(%s)%dL ",temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
									}
									else if(temp.getColor().equals("black")&&(temp.getParent().getColor().equals("red"))){
										writer.printf("%s(%s*)%dL ",temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
									}
									else{
										writer.printf("%s(%s)%dL ",temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
									}
								}
							}
							else{
								if((temp.getLeft() == null) && (temp.getRight() == null)){
									if(temp.getColor().equals("red")&&(temp.getParent().getColor().equals("red"))){
										writer.printf("=%s*(%s*)%dL\n",temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
									}
									else if(temp.getColor().equals("red")&&(temp.getParent().getColor().equals("black"))){
										writer.printf("=%s*(%s)%dL\n",temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
									}
									else if(temp.getColor().equals("black")&&(temp.getParent().getColor().equals("red"))){
										writer.printf("=%s(%s*)%dL\n",temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
									}
									else{
										writer.printf("=%s(%s)%dL\n",temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
									}
								}
								else{
									if(temp.getColor().equals("red")&&(temp.getParent().getColor().equals("red"))){
										writer.printf("%s*(%s*)%dL\n",temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
									}
									else if(temp.getColor().equals("red")&&(temp.getParent().getColor().equals("black"))){
										writer.printf("%s*(%s)%dL\n",temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
									}
									else if(temp.getColor().equals("black")&&(temp.getParent().getColor().equals("red"))){
										writer.printf("%s(%s*)%dL\n",temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
									}
									else{
										writer.printf("%s(%s)%dL\n",temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
									}
								}
							}
						}
						//If right
						else{
							//Next is not a new level
							if((queue.getSize() != 0) && (queue.peek().getLevel() == temp.getLevel())){
								//If leaf
								if((temp.getLeft() == null) && (temp.getRight() == null)){
									if(temp.getColor().equals("red")&&(temp.getParent().getColor().equals("red"))){
										writer.printf("=%s*(%s*)%dR ",temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
									}
									else if(temp.getColor().equals("red")&&(temp.getParent().getColor().equals("black"))){
										writer.printf("=%s*(%s)%dR ",temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
									}
									else if(temp.getColor().equals("black")&&(temp.getParent().getColor().equals("red"))){
										writer.printf("=%s(%s*)%dR ",temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
									}
									else{
										writer.printf("=%s(%s)%dR ",temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
									}
								}
								else{
									if(temp.getColor().equals("red")&&(temp.getParent().getColor().equals("red"))){
										writer.printf("%s*(%s*)%dR ",temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
									}
									else if(temp.getColor().equals("red")&&(temp.getParent().getColor().equals("black"))){
										writer.printf("%s*(%s)%dR ",temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
									}
									else if(temp.getColor().equals("black")&&(temp.getParent().getColor().equals("red"))){
										writer.printf("%s(%s*)%dR ",temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
									}
									else{
										writer.printf("%s(%s)%dR ",temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
									}
								}
							}
							//Next is new level
							else{
								if((temp.getLeft() == null) && (temp.getRight() == null)){
									if(temp.getColor().equals("red")&&(temp.getParent().getColor().equals("red"))){
										writer.printf("=%s*(%s*)%dR\n",temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
									}
									else if(temp.getColor().equals("red")&&(temp.getParent().getColor().equals("black"))){
										writer.printf("=%s*(%s)%dR\n",temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
									}
									else if(temp.getColor().equals("black")&&(temp.getParent().getColor().equals("red"))){
										writer.printf("=%s(%s*)%dR\n",temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
									}
									else{
										writer.printf("=%s(%s)%dR\n",temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
									}
								}
								else{
									if(temp.getColor().equals("red")&&(temp.getParent().getColor().equals("red"))){
										writer.printf("%s*(%s*)%dR\n",temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
									}
									else if(temp.getColor().equals("red")&&(temp.getParent().getColor().equals("black"))){
										writer.printf("%s*(%s)%dR\n",temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
									}
									else if(temp.getColor().equals("black")&&(temp.getParent().getColor().equals("red"))){
										writer.printf("%s(%s*)%dR\n",temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
									}
									else{
										writer.printf("%s(%s)%dR\n",temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
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
							if((queue.getSize() != 0) && (queue.peek().getLevel() == temp.getLevel())){
								//If leaf
								if((temp.getLeft() == null) && (temp.getRight() == null)){
									if(temp.getColor().equals("red")&&(temp.getParent().getColor().equals("red"))){
										writer.printf("%d: =%s*(%s*)%dL ", temp.getLevel(),temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
									}
									else if(temp.getColor().equals("red")&&(temp.getParent().getColor().equals("black"))){
										writer.printf("%d: =%s*(%s)%dL ", temp.getLevel(),temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
									}
									else if(temp.getColor().equals("black")&&(temp.getParent().getColor().equals("red"))){
										writer.printf("%d: =%s(%s*)%dL ", temp.getLevel(),temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
									}
									else{
										writer.printf("%d: =%s(%s)%dL ", temp.getLevel(),temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
									}
								}
								else{
									if(temp.getColor().equals("red")&&(temp.getParent().getColor().equals("red"))){
										writer.printf("%d: %s*(%s*)%dL ", temp.getLevel(),temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
									}
									else if(temp.getColor().equals("red")&&(temp.getParent().getColor().equals("black"))){
										writer.printf("%d: %s*(%s)%dL ", temp.getLevel(),temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
									}
									else if(temp.getColor().equals("black")&&(temp.getParent().getColor().equals("red"))){
										writer.printf("%d: %s(%s*)%dL ", temp.getLevel(),temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
									}
									else{
										writer.printf("%d: %s(%s)%dL ", temp.getLevel(),temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
									}
								}
							}
							//If next is a new level
							else{
								//Is leaf
								if((temp.getLeft() == null) && (temp.getRight() ==null)){
									if(temp.getColor().equals("red")&&(temp.getParent().getColor().equals("red"))){
										writer.printf("%d: =%s*(%s*)%dL\n", temp.getLevel(),temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
									}
									else if(temp.getColor().equals("red")&&(temp.getParent().getColor().equals("black"))){
										writer.printf("%d: =%s*(%s)%dL\n", temp.getLevel(),temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
									}
									else if(temp.getColor().equals("black")&&(temp.getParent().getColor().equals("red"))){
										writer.printf("%d: =%s(%s*)%dL\n", temp.getLevel(),temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
									}
									else{
										writer.printf("%d: =%s(%s)%dL\n", temp.getLevel(),temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
									}
								}
								else{
									if(temp.getColor().equals("red")&&(temp.getParent().getColor().equals("red"))){
										writer.printf("%d: %s*(%s*)%dL\n", temp.getLevel(),temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
									}
									else if(temp.getColor().equals("red")&&(temp.getParent().getColor().equals("black"))){
										writer.printf("%d: %s*(%s)%dL\n", temp.getLevel(),temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
									}
									else if(temp.getColor().equals("black")&&(temp.getParent().getColor().equals("red"))){
										writer.printf("%d: %s(%s*)%dL\n", temp.getLevel(),temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
									}
									else{
										writer.printf("%d: %s(%s)%dL\n", temp.getLevel(),temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
									}
								}
							}
						}
						//If right
						else{
							if((queue.getSize() != 0) && (queue.peek().getLevel() == temp.getLevel())){
								//If leaf
								if((temp.getLeft() == null) && (temp.getRight() == null)){
									if(temp.getColor().equals("red")&&(temp.getParent().getColor().equals("red"))){
										writer.printf("%d: =%s*(%s*)%dR ", temp.getLevel(),temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
									}
									else if(temp.getColor().equals("red")&&(temp.getParent().getColor().equals("black"))){
										writer.printf("%d: =%s*(%s)%dR ", temp.getLevel(),temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
									}
									else if(temp.getColor().equals("black")&&(temp.getParent().getColor().equals("red"))){
										writer.printf("%d: =%s(%s*)%dR ", temp.getLevel(),temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
									}
									else{
										writer.printf("%d: =%s(%s)%dR ", temp.getLevel(),temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
									}
								}
								else{
									if(temp.getColor().equals("red")&&(temp.getParent().getColor().equals("red"))){
										writer.printf("%d: %s*(%s*)%dR ", temp.getLevel(),temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
									}
									else if(temp.getColor().equals("red")&&(temp.getParent().getColor().equals("black"))){
										writer.printf("%d: %s*(%s)%dR ", temp.getLevel(),temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
									}
									else if(temp.getColor().equals("black")&&(temp.getParent().getColor().equals("red"))){
										writer.printf("%d: %s(%s*)%dR ", temp.getLevel(),temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
									}
									else{
										writer.printf("%d: %s(%s)%dR ", temp.getLevel(),temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
									}
								}
							}
							else{
								if((temp.getLeft() == null) && (temp.getRight() == null)){
									if(temp.getColor().equals("red")&&(temp.getParent().getColor().equals("red"))){
										writer.printf("%d: =%s*(%s*)%dR\n", temp.getLevel(),temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
									}
									else if(temp.getColor().equals("red")&&(temp.getParent().getColor().equals("black"))){
										writer.printf("%d: =%s*(%s)%dR\n", temp.getLevel(),temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
									}
									else if(temp.getColor().equals("black")&&(temp.getParent().getColor().equals("red"))){
										writer.printf("%d: =%s(%s*)%dR\n", temp.getLevel(),temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
									}
									else{
										writer.printf("%d: =%s(%s)%dR\n", temp.getLevel(),temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
									}
								}
								else{
									if(temp.getColor().equals("red")&&(temp.getParent().getColor().equals("red"))){
										writer.printf("%d: %s*(%s*)%dR\n", temp.getLevel(),temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
									}
									else if(temp.getColor().equals("red")&&(temp.getParent().getColor().equals("black"))){
										writer.printf("%d: %s*(%s)%dR\n", temp.getLevel(),temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
									}
									else if(temp.getColor().equals("black")&&(temp.getParent().getColor().equals("red"))){
										writer.printf("%d: %s(%s*)%dR\n", temp.getLevel(),temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
									}
									else{
										writer.printf("%d: %s(%s)%dR\n", temp.getLevel(),temp.getValue(), temp.getParent().getValue(),temp.getFrequency());
									}
								}
							}
						}
					}
				}
				if(temp.getLeft() != null){
					temp.getLeft().setLevel(temp.getLevel()+1);
					queue.enqueue(temp.getLeft());
				}
				if(temp.getRight() != null){
					temp.getRight().setLevel(temp.getLevel()+1);
					queue.enqueue(temp.getRight());
				}
				level++;
			}	
			writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
