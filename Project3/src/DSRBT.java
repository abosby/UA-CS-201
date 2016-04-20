
public class DSRBT{

	RedBlackNode root;
	RedBlackNode min;
	int size;

	public class RedBlackNode{
		RedBlackNode pParent;
		int rank;
		SinglyLinkedList adjacencyList;

		Vertex value;
		RedBlackNode parent;
		RedBlackNode left;
		RedBlackNode right;
		DSRBT RBT;

		String color;
		int level;

		private RedBlackNode getNiece(){
			if(this.parent.left != null){
				//If left child of parent return the left child of sibling
				if(this.parent.left == this){
					if(this.getSibling() != null){
						return this.getSibling().left;
					}
					else{
						return null;
					}
				}
				//If right child of parent return the right child of sibling
				else{
					if(this.getSibling() != null){
						return this.getSibling().right;
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

		private RedBlackNode getNephew() {
			if(this.parent.left != null){
				//If left child of parent return the right child of sibling
				if(this.parent.left == this){
					if(this.getSibling() != null){
						return this.getSibling().right;
					}
					else{
						return null;
					}
				}
				//If right child of parent return the left child of sibling
				else{
					if(this.getSibling() != null){
						return this.getSibling().left;
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

		private RedBlackNode getSibling() {
			if(this.parent.left != null){
				//if left child of parent, return right
				if(this.parent.left == this){
					if(this.parent.right != null){
						return this.parent.right;
					}
					else{
						return null;
					}
				}
				//return the left child
				else{
					return this.parent.left;
				}
			}
			//There are not two children of the parent;
			else{
				return null;
			}
		}

		private int getLevel() {
			return this.level;
		}

		private RedBlackNode getParent() {
			return this.parent;
		}

		private RedBlackNode getLeft() {
			return this.left;
		}

		private RedBlackNode getRight() {
			return this.right;
		}

		private String getColor() {
			return this.color;
		}

		public Vertex getValue() {
			return this.value;
		}

		private RedBlackNode getGrandparent() {
			if(this.parent.parent != null){
				return this.parent.parent;
			}
			else{
				return null;
			}
		}

		/**
			private void setGrandparent(RedBlackNode node) {
				if(this.parent.parent != null){
					this.parent.parent = node;
				}
			}
		 */

		private RedBlackNode(DSRBT rbt, Vertex e){
			parent = null;
			left = null;
			right = null;
			value = e;
			RBT = rbt;
			color = "red";
		}

		private void insertNode(RedBlackNode node, Vertex v){
			while(true){
				if( (node.value == null) && (node.RBT.root == null)){
					node.value = v;
					node.level = 1;
					node.RBT.size++;
				}
				else{
					//Vertex 1 equal
					if(node.value.getValue() == v.getValue()){
						//Vertex 2 equal
						if(node.value.getValue() == v.getValue()){
							System.out.println("InsertNode: Value should not be the same");
						}

					}
					//Go Left
					else if(node.value.getValue() > v.getValue()){
						if(node.left != null){
							node = node.left;
						}
						else{
							node.left = new RedBlackNode(node.RBT,v);
							node.left.level = node.level + 1;
							node.left.parent = node;
							node.insertionFixUp(node.left);
							node.RBT.size++;
							break;
						}
					}
					//Go Right
					else if(node.value.getValue() < v.getValue()){
						if(node.right != null){
							node = node.right;
						}
						else{
							node.right = new RedBlackNode(node.RBT,v);
							node.right.level = node.level + 1;
							node.right.parent = node;
							node.insertionFixUp(node.right);
							node.RBT.size++;
							break;
						}
					}
				}
			}
		}

		private void insertionFixUp(DSRBT.RedBlackNode node) {
			while(true){
				if(node == this.RBT.root){
					break;
				}
				if(node.getParent().getColor().equals("black")){
					break;
				}
				if(uncle(node) != null){
					if(uncle(node).getColor().equals("red")){
						node.getParent().getColor().equals("black");
						uncle(node).color = "black";
						node.getGrandparent().color = "red";
						node = node.getGrandparent();
					}
					else{
						//uncle is black
						//if not linear
						if(needsToRotateLinear(node)){
							RedBlackNode temp = null;
							temp = node.parent;
							rotateToLinear(node);
							node = temp;
						}
						node.getParent().color = "black";
						node.getGrandparent().color = "red";
						rotate(node.getParent());
					}
				}
				//uncle is black
				else{
					//if not linear
					if(needsToRotateLinear(node)){
						RedBlackNode temp = null;
						temp = node.parent;
						rotateToLinear(node);
						node = temp;
					}
					node.parent.color = "black";
					if(node.getGrandparent() != null){
						node.getGrandparent().color = "red";
					}
					rotate(node.parent);
				}

			}
			this.RBT.resetRoot(this.RBT.root);
			this.RBT.root.color = "black";
		}

		private void deletionFixUp(RedBlackNode node) {
			while(true){
				if(node == node.RBT.root){
					break;
				}
				if(node.color.compareTo("red") == 1){
					break;
				}
				if(node.getSibling() != null){
					if(node.getSibling().color.compareTo("red") == 1){
						node.parent.color = "red";
						node.getSibling().color = "black";
						node.getSibling().rotate(node.parent);
						//should have black sibling now
					}
					else if(node.getNephew() != null){
						if(node.getNephew().color.equals("red")){
							node.getSibling().color = node.parent.color;
							node.parent.color = "black";
							node.getNephew().color = "black";
							node.getSibling().rotate(node.parent);
							node = this.RBT.root;	
						}

						//nephew must be black
						else if(node.getNiece() != null){
							if(node.getNiece().color.equals("red")){
								node.getNiece().color = "black";
								node.getSibling().color = "black";
								node.rotate(node.getSibling());
								//should have red nephew now
							}
						}
					}
					//nephew must be black
					else if(node.getNiece() != null){
						if(node.getNiece().color.equals("red")){
							node.getNiece().color = "black";
							node.getSibling().color = "black";
							node.rotate(node.getSibling());
							//should have red nephew now
						}

						//sibling niece and nephew are black
						else{
							node.getSibling().color = "red";
							node = node.parent;
						}
					}
					//sibling niece and nephew are black
					else{
						node.getSibling().color = "red";
						node = node.parent;
					}
				}
				else if(node.getNephew() != null){
					if(node.getNephew().color.equals("red")){
						node.getSibling().color = node.parent.color;
						node.parent.color = "black";
						node.getNephew().color = "black";
						node.getSibling().rotate(node.parent);
						node = this.RBT.root;	
					}
					//nephew must be black
					else if(node.getNiece() != null){
						if(node.getNiece().color.equals("red")){
							node.getNiece().color = "black";
							node.getSibling().color = "black";
							node.rotate(node.getSibling());
							//should have red nephew now
						}
					}
				}
				//nephew must be black
				else if(node.getNiece() != null){
					if(node.getNiece().color.equals("red")){
						node.getNiece().color = "black";
						node.getSibling().color = "black";
						node.rotate(node.getSibling());
						//should have red nephew now
					}
				}
				//sibling niece and nephew are black
				else{
					node.getSibling().color = "red";
					node = node.parent;
				}
			}
			this.RBT.root.color = "black";
		}

		private RedBlackNode findNode(Vertex v) {
			RedBlackNode node = root;
			while(true){
				if( (node.value == null) && (node.RBT.root == null)){
					return null;
				}
				else{
					//Vertex 1 equal
					if(node.value.getValue() == v.getValue()){
						//Vertex 2 equal
						return node;
					}
					//Go Left
					else if(node.value.getValue() > v.getValue()){
						if(node.left != null){
							node = node.left;
						}
						else{
							return null;
						}
					}
					//Go Right
					else if(node.value.getValue() > v.getValue()){
						if(node.right != null){
							node = node.right;
						}
						else{
							return null;
						}
					}
				}
			}
		}

		private void swapToLeaf(RedBlackNode node) {
			RedBlackNode temp;
			if(isLeaf(node)){
				return;
			}
			else if(node.left != null){
				//predecessor
				temp = predecessor(node);
			}
			else{
				//successor
				temp = successor(node);
			}
			//swap and recur
			swapValues(node,temp);
			swapToLeaf(temp);
		}

		private RedBlackNode predecessor(RedBlackNode node) {
			RedBlackNode temp = node.left;
			while(!isLeaf(temp)){
				temp = temp.right;
			}
			return temp;
		}

		private RedBlackNode successor(RedBlackNode node){
			RedBlackNode temp = node.right;
			while(!isLeaf(temp)){
				temp = temp.left;
			}
			return temp;
		}

		private void swapValues(RedBlackNode node1, RedBlackNode node2){
			RedBlackNode temp = node1;
			node1.value = node2.value;
			node2.value = temp.value;
		}

		private boolean isLeaf(RedBlackNode node) {
			if((node.left == null) && (node.right == null)){
				return true;
			}
			else{
				return false;
			}
		}

		private void rotate(RedBlackNode node) {
			RedBlackNode temp = node.parent;
			//right rotate
			if(temp.getLeft() == node){
				//Grandchild A
				//if(node.left != null){
				//	node.left.setGrandparent(temp.parent);
				//}
				//Grandchild B
				if(node.getRight() != null){
					//if(node.right.right != null){
					//	node.right.right.setGrandparent(temp);
					//}
					//if(node.right.left != null){
					//	node.right.left.setGrandparent(temp);
					//}
					//node.right.setGrandparent(node);
					node.right.parent = temp;
				}
				//Grandchild C
				//if(temp.right != null){
				//	temp.right.setGrandparent(node);
				//}
				//node.setGrandparent(temp.getGrandparent());
				node.parent = temp.parent;
				temp.left = node.right;
				temp.parent = node;
				//temp.setGrandparent(node.parent);
				node.right = temp;
				if(node.parent != null){
					if(node.parent.left == temp){
						node.parent.left = node;
					}
					else{
						node.parent.right = node;
					}
					node.RBT.resetRoot(node.RBT.root);
				}
			}
			//left rotate
			else{
				//Grandchild C
				//if(node.right != null){
				//	node.right.setGrandparent(temp.parent);
				//}
				//Grandchild B
				if(node.getLeft() != null){
					//if(node.left.left != null){
					//	node.left.left.setGrandparent(temp);
					//}
					//if(node.left.right != null){
					//	node.left.right.setGrandparent(temp);
					//}
					//node.left.setGrandparent(node);
					node.left.parent = temp;
				}
				//Grandchild A
				//if(temp.left != null){
				//	temp.left.setGrandparent(node);
				//}
				//node.setGrandparent(temp.getGrandparent());
				node.parent = temp.parent;
				temp.right = node.left;
				temp.parent = node;
				//temp.setGrandparent(node.parent);
				node.left = temp;
				if(node.parent != null){
					if(node.parent.left == temp){
						node.parent.left = node;
					}
					else{
						node.parent.right = node;
					}
				}
				node.RBT.resetRoot(node.RBT.root);
			}
			return;
		}

		private void rotateToLinear(RedBlackNode node) {
			RedBlackNode temp = node.parent;
			if(temp.left == node){
				temp.parent.right = node;
				//node.setGrandparent(temp.getGrandparent());
				node.parent = temp.parent;
				temp.left = node.right;
				if(temp.left != null){
					//if(temp.left.left != null){
					//	temp.left.left.setGrandparent(temp);
					//}
					//if(temp.left.right != null){
					//	temp.left.right.setGrandparent(temp);
					//}
					temp.left.parent = temp;
					//temp.left.setGrandparent(node);
				}
				//temp.setGrandparent(node.parent);
				temp.parent = node;
				//if(temp.right != null){
				//	temp.right.setGrandparent(node);
				//}
				node.right = temp;
				//if(node.left != null){
				//	node.left.setGrandparent(node.getGrandparent());
				//}
			}
			else{
				temp.parent.left = node;
				//node.setGrandparent(temp.getGrandparent());
				node.parent = temp.parent;
				temp.right = node.left;
				if(temp.right != null){
					//if(temp.right.left != null){
					//	temp.right.left.setGrandparent(temp);
					//}
					//if(temp.right.right != null){
					//	temp.right.right.setGrandparent(temp);
					//}
					temp.right.parent = temp;
					//temp.right.setGrandparent(node);
				}
				//temp.setGrandparent(node.parent);
				temp.parent = node;
				//if(temp.left != null){
				//	temp.left.setGrandparent(node);
				//}
				node.left = temp;
				//if(node.right != null){
				//	node.right.setGrandparent(node.parent);
				//}
			}
		}

		private boolean needsToRotateLinear(RedBlackNode node) {
			//If node is left child
			if(node.parent.left == node){
				if(node.getGrandparent() != null){
					if(node.getGrandparent().right == node.parent){
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
			//If node is right child
			else{
				if(node.getGrandparent() != null){
					if(node.getGrandparent().left == node.parent){
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
		}

		private RedBlackNode uncle(RedBlackNode node) {
			if(node.getGrandparent() != null){
				if(node.getGrandparent().getLeft() == node.getParent()){
					return node.getGrandparent().getRight();
				}
				else if(node.getGrandparent().getRight() == node.getParent()){
					return node.getGrandparent().getLeft();
				}
				else{
					return null;
				}
			}
			return null;
		}

		private RedBlackNode deleteNode(RedBlackNode node, Vertex v) {
			while(true){
				if(node.value == null){
					return null;
				}
				else{
					//Equals
					if(node.value.getValue() == v.getValue()){
						//If two children
						if( (node.right != null) && (node.left != null)){
							RedBlackNode temp = node;
							node.swapToLeaf(node);
							deletionFixUp(node);
							node.RBT.size--;
							return temp;
						}
						//If one child
						else if((node.right != null) && (node.left == null)){
							RedBlackNode temp = node;
							node.swapToLeaf(node);
							deletionFixUp(node);
							node.RBT.size--;
							return temp;
						}
						else if((node.right == null) && (node.left != null)){
							RedBlackNode temp = node;
							node.swapToLeaf(node);
							deletionFixUp(node);
							node.RBT.size--;
							return temp;
						}
						//If no child
						else{
							if(node.parent.left == node){
								node.parent.left = null;
							}
							else{
								node.parent.right = null;
							}
							node.RBT.size--;
							return node;
						}
					}

					//Go Left
					else if(node.value.getValue() > v.getValue()){
						if(node.left != null){
							node = node.left;
						}
						else{
							return null;
						}
					}
					//Go Right
					else if(node.value.getValue() < v.getValue()){
						if(node.right != null){
							node = node.right;
						}
						else{
							return null;
						}
					}
				}
			}
		}

	}

	public DSRBT(){
		root = null;
		size = 0;
	}

	public void resetRoot(RedBlackNode node) {
		while(true){
			if(node.parent != null){
				node.RBT.root = node.parent;
				node = node.parent;
			}
			else{
				node.RBT.root = node;
				break;
			}
		}
	}

	public void insertNode(Vertex v){
		if(this.root == null){
			RedBlackNode n = new RedBlackNode(this,v);
			n.level = 1;
			n.color = "black";
			n.parent = null;
			this.size = 1;
			this.root = n;
			this.min = n;
		}
		else{
			this.root.insertNode(this.root, v);
			this.resetRoot(this.root);
		}
	}

	public Vertex deleteNode(Vertex v){
		if(this.root == null){
			return null;
		}
		else{
			RedBlackNode confirmDeletion = this.root.deleteNode(this.root,v);
			this.resetRoot(this.root);
			if(confirmDeletion != null){
				//System.out.printf("Deleted Node: %s\nNew Node frequency: %d\n\n", confirmDeletion.getValue(),confirmDeletion.getFrequency());
				return v;
			}
			else{
				//System.err.printf("\nThe Node: '%s' was not found to delete\n\n", v);
				return null;

			}
		}
	}

	public RedBlackNode findNode(Vertex v){
		if(this.root == null){
			System.out.printf("\n Find Result: 0\n");
			return null;
		}
		else{
			return this.root.findNode(v);
		}
	}

	public void printBreadthTraversal(RedBlackNode n){
		BQueue<RedBlackNode> queue= new BQueue<RedBlackNode>();
		int level = 1;
		n.level = level;
		queue.enqueue(n);
		RedBlackNode temp = null;
		RedBlackNode prev = n;
		while(queue.getSize() != 0){
			prev = temp;
			temp = queue.dequeue();
			//If root
			if(n == temp){
				System.out.printf("\n%d: %s(%s)X\n", temp.level,temp.value, temp.value);
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
									System.out.printf("=%s*(%s*)L ",temp.getValue(), temp.getParent().getValue());
								}
								else if(temp.getColor().equals("red")&&(temp.getParent().getColor().equals("black"))){
									System.out.printf("=%s*(%s)L ",temp.getValue(), temp.getParent().getValue());
								}
								else if(temp.getColor().equals("black")&&temp.getParent().getColor().equals("red")){
									System.out.printf("=%s(%s*)L ",temp.getValue(), temp.getParent().getValue());
								}
								else{
									System.out.printf("=%s(%s)L ",temp.getValue(), temp.getParent().getValue());
								}
							}
							else{
								if(temp.getColor().equals("red")&&(temp.getParent().getColor().equals("red"))){
									System.out.printf("%s*(%s*)L ",temp.getValue(), temp.getParent().getValue());
								}
								else if(temp.getColor().equals("red")&&(temp.getParent().getColor().equals("black"))){
									System.out.printf("%s*(%s)L ",temp.getValue(), temp.getParent().getValue());
								}
								else if(temp.getColor().equals("black")&&(temp.getParent().getColor().equals("red"))){
									System.out.printf("%s(%s*)L ",temp.getValue(), temp.getParent().getValue());
								}
								else{
									System.out.printf("%s(%s)L ",temp.getValue(), temp.getParent().getValue());
								}
							}
						}
						else{
							if((temp.getLeft() == null) && (temp.getRight() == null)){
								if(temp.getColor().equals("red")&&(temp.getParent().getColor().equals("red"))){
									System.out.printf("=%s*(%s*)L\n",temp.getValue(), temp.getParent().getValue());
								}
								else if(temp.getColor().equals("red")&&(temp.getParent().getColor().equals("black"))){
									System.out.printf("=%s*(%s)L\n",temp.getValue(), temp.getParent().getValue());
								}
								else if(temp.getColor().equals("black")&&(temp.getParent().getColor().equals("red"))){
									System.out.printf("=%s(%s*)L\n",temp.getValue(), temp.getParent().getValue());
								}
								else{
									System.out.printf("=%s(%s)L\n",temp.getValue(), temp.getParent().getValue());
								}
							}
							else{
								if(temp.getColor().equals("red")&&(temp.getParent().getColor().equals("red"))){
									System.out.printf("%s*(%s*)L\n",temp.getValue(), temp.getParent().getValue());
								}
								else if(temp.getColor().equals("red")&&(temp.getParent().getColor().equals("black"))){
									System.out.printf("%s*(%s)L\n",temp.getValue(), temp.getParent().getValue());
								}
								else if(temp.getColor().equals("black")&&(temp.getParent().getColor().equals("red"))){
									System.out.printf("%s(%s*)L\n",temp.getValue(), temp.getParent().getValue());
								}
								else{
									System.out.printf("%s(%s)L\n",temp.getValue(), temp.getParent().getValue());
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
									System.out.printf("=%s*(%s*)R ",temp.getValue(), temp.getParent().getValue());
								}
								else if(temp.getColor().equals("red")&&(temp.getParent().getColor().equals("black"))){
									System.out.printf("=%s*(%s)R ",temp.getValue(), temp.getParent().getValue());
								}
								else if(temp.getColor().equals("black")&&(temp.getParent().getColor().equals("red"))){
									System.out.printf("=%s(%s*)R ",temp.getValue(), temp.getParent().getValue());
								}
								else{
									System.out.printf("=%s(%s)R ",temp.getValue(), temp.getParent().getValue());
								}
							}
							else{
								if(temp.getColor().equals("red")&&(temp.getParent().getColor().equals("red"))){
									System.out.printf("%s*(%s*)R ",temp.getValue(), temp.getParent().getValue());
								}
								else if(temp.getColor().equals("red")&&(temp.getParent().getColor().equals("black"))){
									System.out.printf("%s*(%s)R ",temp.getValue(), temp.getParent().getValue());
								}
								else if(temp.getColor().equals("black")&&(temp.getParent().getColor().equals("red"))){
									System.out.printf("%s(%s*)R ",temp.getValue(), temp.getParent().getValue());
								}
								else{
									System.out.printf("%s(%s)R ",temp.getValue(), temp.getParent().getValue());
								}
							}
						}
						//Next is new level
						else{
							if((temp.getLeft() == null) && (temp.getRight() == null)){
								if(temp.getColor().equals("red")&&(temp.getParent().getColor().equals("red"))){
									System.out.printf("=%s*(%s*)R\n",temp.getValue(), temp.getParent().getValue());
								}
								else if(temp.getColor().equals("red")&&(temp.getParent().getColor().equals("black"))){
									System.out.printf("=%s*(%s)R\n",temp.getValue(), temp.getParent().getValue());
								}
								else if(temp.getColor().equals("black")&&(temp.getParent().getColor().equals("red"))){
									System.out.printf("=%s(%s*)R\n",temp.getValue(), temp.getParent().getValue());
								}
								else{
									System.out.printf("=%s(%s)R\n",temp.getValue(), temp.getParent().getValue());
								}
							}
							else{
								if(temp.getColor().equals("red")&&(temp.getParent().getColor().equals("red"))){
									System.out.printf("%s*(%s*)R\n",temp.getValue(), temp.getParent().getValue());
								}
								else if(temp.getColor().equals("red")&&(temp.getParent().getColor().equals("black"))){
									System.out.printf("%s*(%s)R\n",temp.getValue(), temp.getParent().getValue());
								}
								else if(temp.getColor().equals("black")&&(temp.getParent().getColor().equals("red"))){
									System.out.printf("%s(%s*)R\n",temp.getValue(), temp.getParent().getValue());
								}
								else{
									System.out.printf("%s(%s)R\n",temp.getValue(), temp.getParent().getValue());
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
									System.out.printf("%d: =%s*(%s*)L ", temp.getLevel(),temp.getValue(), temp.getParent().getValue());
								}
								else if(temp.getColor().equals("red")&&(temp.getParent().getColor().equals("black"))){
									System.out.printf("%d: =%s*(%s)L ", temp.getLevel(),temp.getValue(), temp.getParent().getValue());
								}
								else if(temp.getColor().equals("black")&&(temp.getParent().getColor().equals("red"))){
									System.out.printf("%d: =%s(%s*)L ", temp.getLevel(),temp.getValue(), temp.getParent().getValue());
								}
								else{
									System.out.printf("%d: =%s(%s)L ", temp.getLevel(),temp.getValue(), temp.getParent().getValue());
								}
							}
							else{
								if(temp.getColor().equals("red")&&(temp.getParent().getColor().equals("red"))){
									System.out.printf("%d: %s*(%s*)L ", temp.getLevel(),temp.getValue(), temp.getParent().getValue());
								}
								else if(temp.getColor().equals("red")&&(temp.getParent().getColor().equals("black"))){
									System.out.printf("%d: %s*(%s)L ", temp.getLevel(),temp.getValue(), temp.getParent().getValue());
								}
								else if(temp.getColor().equals("black")&&(temp.getParent().getColor().equals("red"))){
									System.out.printf("%d: %s(%s*)L ", temp.getLevel(),temp.getValue(), temp.getParent().getValue());
								}
								else{
									System.out.printf("%d: %s(%s)L ", temp.getLevel(),temp.getValue(), temp.getParent().getValue());
								}
							}
						}
						//If next is a new level
						else{
							//Is leaf
							if((temp.getLeft() == null) && (temp.getRight() ==null)){
								if(temp.getColor().equals("red")&&(temp.getParent().getColor().equals("red"))){
									System.out.printf("%d: =%s*(%s*)L\n", temp.getLevel(),temp.getValue(), temp.getParent().getValue());
								}
								else if(temp.getColor().equals("red")&&(temp.getParent().getColor().equals("black"))){
									System.out.printf("%d: =%s*(%s)L\n", temp.getLevel(),temp.getValue(), temp.getParent().getValue());
								}
								else if(temp.getColor().equals("black")&&(temp.getParent().getColor().equals("red"))){
									System.out.printf("%d: =%s(%s*)L\n", temp.getLevel(),temp.getValue(), temp.getParent().getValue());
								}
								else{
									System.out.printf("%d: =%s(%s)L\n", temp.getLevel(),temp.getValue(), temp.getParent().getValue());
								}
							}
							else{
								if(temp.getColor().equals("red")&&(temp.getParent().getColor().equals("red"))){
									System.out.printf("%d: %s*(%s*)L\n", temp.getLevel(),temp.getValue(), temp.getParent().getValue());
								}
								else if(temp.getColor().equals("red")&&(temp.getParent().getColor().equals("black"))){
									System.out.printf("%d: %s*(%s)L\n", temp.getLevel(),temp.getValue(), temp.getParent().getValue());
								}
								else if(temp.getColor().equals("black")&&(temp.getParent().getColor().equals("red"))){
									System.out.printf("%d: %s(%s*)L\n", temp.getLevel(),temp.getValue(), temp.getParent().getValue());
								}
								else{
									System.out.printf("%d: %s(%s)L\n", temp.getLevel(),temp.getValue(), temp.getParent().getValue());
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
									System.out.printf("%d: =%s*(%s*)R ", temp.getLevel(),temp.getValue(), temp.getParent().getValue());
								}
								else if(temp.getColor().equals("red")&&(temp.getParent().getColor().equals("black"))){
									System.out.printf("%d: =%s*(%s)R ", temp.getLevel(),temp.getValue(), temp.getParent().getValue());
								}
								else if(temp.getColor().equals("black")&&(temp.getParent().getColor().equals("red"))){
									System.out.printf("%d: =%s(%s*)R ", temp.getLevel(),temp.getValue(), temp.getParent().getValue());
								}
								else{
									System.out.printf("%d: =%s(%s)R ", temp.getLevel(),temp.getValue(), temp.getParent().getValue());
								}
							}
							else{
								if(temp.getColor().equals("red")&&(temp.getParent().getColor().equals("red"))){
									System.out.printf("%d: %s*(%s*)R ", temp.getLevel(),temp.getValue(), temp.getParent().getValue());
								}
								else if(temp.getColor().equals("red")&&(temp.getParent().getColor().equals("black"))){
									System.out.printf("%d: %s*(%s)R ", temp.getLevel(),temp.getValue(), temp.getParent().getValue());
								}
								else if(temp.getColor().equals("black")&&(temp.getParent().getColor().equals("red"))){
									System.out.printf("%d: %s(%s*)R ", temp.getLevel(),temp.getValue(), temp.getParent().getValue());
								}
								else{
									System.out.printf("%d: %s(%s)R ", temp.getLevel(),temp.getValue(), temp.getParent().getValue());
								}
							}
						}
						else{
							if((temp.getLeft() == null) && (temp.getRight() == null)){
								if(temp.getColor().equals("red")&&(temp.getParent().getColor().equals("red"))){
									System.out.printf("%d: =%s*(%s*)R\n", temp.getLevel(),temp.getValue(), temp.getParent().getValue());
								}
								else if(temp.getColor().equals("red")&&(temp.getParent().getColor().equals("black"))){
									System.out.printf("%d: =%s*(%s)R\n", temp.getLevel(),temp.getValue(), temp.getParent().getValue());
								}
								else if(temp.getColor().equals("black")&&(temp.getParent().getColor().equals("red"))){
									System.out.printf("%d: =%s(%s*)R\n", temp.getLevel(),temp.getValue(), temp.getParent().getValue());
								}
								else{
									System.out.printf("%d: =%s(%s)R\n", temp.getLevel(),temp.getValue(), temp.getParent().getValue());
								}
							}
							else{
								if(temp.getColor().equals("red")&&(temp.getParent().getColor().equals("red"))){
									System.out.printf("%d: %s*(%s*)R\n", temp.getLevel(),temp.getValue(), temp.getParent().getValue());
								}
								else if(temp.getColor().equals("red")&&(temp.getParent().getColor().equals("black"))){
									System.out.printf("%d: %s*(%s)R\n", temp.getLevel(),temp.getValue(), temp.getParent().getValue());
								}
								else if(temp.getColor().equals("black")&&(temp.getParent().getColor().equals("red"))){
									System.out.printf("%d: %s(%s*)R\n", temp.getLevel(),temp.getValue(), temp.getParent().getValue());
								}
								else{
									System.out.printf("%d: %s(%s)R\n", temp.getLevel(),temp.getValue(), temp.getParent().getValue());
								}
							}
						}
					}
				}
			}
			if(temp.getLeft() != null){
				temp.getLeft().level = temp.getLevel()+1;
				queue.enqueue(temp.getLeft());
			}
			if(temp.getRight() != null){
				temp.getRight().level = temp.getLevel()+1;
				queue.enqueue(temp.getRight());
			}
			level++;
		}
		System.out.println();
	}
}
