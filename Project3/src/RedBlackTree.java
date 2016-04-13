//RedBlackTree for Edges

public class RedBlackTree {

	RedBlackNode root;
	RedBlackNode min;
	int size;

	private class RedBlackNode{
		RedBlackNode parent;
		RedBlackNode left;
		RedBlackNode right;
		RedBlackTree RBT;
		Edge value;
		String color;
		int level;

		private RedBlackNode getGrandparent() {
			if(this.parent.parent != null){
				return this.parent.parent;
			}
			else{
				return null;
			}
		}

		private void setGrandparent(RedBlackNode node) {
			if(this.parent.parent != null){
				this.parent.parent = node;
			}
		}

		private RedBlackNode(RedBlackTree rbt, Edge e){
			parent = null;
			left = null;
			right = null;
			value = e;
			RBT = rbt;
			color = "red";
		}

		private void insertNode(RedBlackNode node, Edge e){
			while(true){
				if( (node.value == null) && (node.RBT.root == null)){
					node.value = e;
					node.level = 1;
					node.RBT.size++;
				}
				else{
					//Vertex 1 equal
					if(node.value.getVertex1().getValue() == e.getVertex1().getValue()){
						//Vertex 2 equal
						if(node.value.getVertex2().getValue() == e.getVertex2().getValue()){
							System.out.println("InsertNode: Value should not be the same");
						}
						//Go Left
						else if(node.value.getVertex2().getValue() > e.getVertex2().getValue()){
							if(node.left != null){
								node = node.left;
							}
							else{
								node.left = new RedBlackNode(node.RBT,e);
								node.left.level = node.level + 1;
								node.left.parent = node;
								node.insertionFixUp(node.left);
								node.RBT.size++;
								break;
							}
						}
						else if(node.value.getVertex2().getValue() < e.getVertex2().getValue()){
							if(node.right != null){
								node = node.right;
							}
							else{
								node.right = new RedBlackNode(node.RBT,e);
								node.right.level = node.level +1;
								node.right.parent = node;
								node.insertionFixUp(node.right);
								node.RBT.size++;
								break;
							}

						}

					}
					//Go Left
					else if(node.value.getVertex1().getValue() > e.getVertex1().getValue()){
						if(node.left != null){
							node = node.left;
						}
						else{
							node.left = new RedBlackNode(node.RBT,e);
							node.left.level = node.level + 1;
							node.left.parent = node;
							node.insertionFixUp(node.left);
							node.RBT.size++;
							break;
						}
					}
					//Go Right
					else if(node.value.getVertex1().getValue() > e.getVertex1().getValue()){
						if(node.right != null){
							node = node.right;
						}
						else{
							node.right = new RedBlackNode(node.RBT,e);
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

		private void insertionFixUp(RedBlackNode node) {
			while(true){
				if(node == this.RBT.root){
					break;
				}
				if(node.parent.color.equals("black")){
					break;
				}
				if(uncle(node) != null){
					if(uncle(node).color.equals("red")){
						node.parent.color.equals("black");
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
					node.getGrandparent().color = "red";
					rotate(node.parent);
				}

			}
		}

		private RedBlackNode deleteNode(RedBlackNode node, Edge e) {
			while(true){
				if(node.value == null){
					return null;
				}
				else{
					//Equals
					if(node.value.getVertex1().getValue() == e.getVertex1().getValue()){
						//Equals
						if(node.value.getVertex2().getValue() == e.getVertex2().getValue()){
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
						else if(node.value.getVertex2().getValue() > e.getVertex2().getValue()){

						}
						else if(node.value.getVertex2().getValue() < e.getVertex2().getValue()){

						}

					}
					//Go Left
					else if(node.value.getVertex1().getValue() > e.getVertex1().getValue()){


					}
					//Go Right
					else if(node.value.getVertex1().getValue() < e.getVertex1().getValue()){

					}
				}
			}
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
				}
				else if(node.getNephew() != null){
					if(node.getNephew().color.equals("red")){
						node.getSibling().color = node.parent.color;
						node.parent.color = "black";
						node.getNephew().color = "black";
						node.getSibling().rotate(node.parent);
						node = this.RBT.root;	
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

		private void swapToLeaf(RedBlackNode node) {
			// TODO Auto-generated method stub

		}

		private void rotate(RedBlackNode node) {
			RedBlackNode temp = node.parent;
			//right rotate
			if(temp.left == node){
				//Grandparent A
				if(node.left != null){
					node.left.setGrandparent(temp.parent);
				}
				//Grandparent B
				if(node.right != null){
					if(node.right.right != null){
						node.right.right.setGrandparent(temp);
					}
					if(node.right.left != null){
						node.right.left.setGrandparent(temp);
					}
					node.right.setGrandparent(node);
					node.right.parent = temp;
				}
				//Grandparent C
				if(temp.right != null){
					temp.right.setGrandparent(node);
				}
				node.setGrandparent(temp.getGrandparent());
				node.parent = temp.parent;
				temp.left = node.right;
				temp.parent = node;
				temp.setGrandparent(node.parent);
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
				//C
				if(node.right != null){
					node.right.setGrandparent(temp.parent);
				}
				//B
				if(node.left != null){
					if(node.left.left != null){
						node.left.left.setGrandparent(temp);
					}
					if(node.left.right != null){
						node.left.right.setGrandparent(temp);
					}
					node.left.setGrandparent(node);
					node.left.parent = temp;
				}
				//A
				if(temp.left != null){
					temp.left.setGrandparent(node);
				}
				node.setGrandparent(temp.getGrandparent());
				node.parent = temp.parent;
				temp.right = node.left;
				temp.parent = node;
				temp.setGrandparent(node.parent);
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
				node.setGrandparent(temp.getGrandparent());
				node.parent = temp.parent;
				temp.left = node.right;
				if(temp.left != null){
					if(temp.left.left != null){
						temp.left.left.setGrandparent(temp);
					}
					if(temp.left.right != null){
						temp.left.right.setGrandparent(temp);
					}
					temp.left.parent = temp;
					temp.left.setGrandparent(node);
				}
				temp.setGrandparent(node.parent);
				temp.parent = node;
				if(temp.right != null){
					temp.right.setGrandparent(node);
				}
				node.right = temp;
				if(node.left != null){
					node.left.setGrandparent(node.getGrandparent());
				}
			}
			else{
				temp.parent.left = node;
				node.setGrandparent(temp.getGrandparent());
				node.parent = temp.parent;
				temp.right = node.left;
				if(temp.right != null){
					if(temp.right.left != null){
						temp.right.left.setGrandparent(temp);
					}
					if(temp.right.right != null){
						temp.right.right.setGrandparent(temp);
					}
					temp.right.parent = temp;
					temp.right.setGrandparent(node);
				}
				temp.setGrandparent(node.parent);
				temp.parent = node;
				if(temp.left != null){
					temp.left.setGrandparent(node);
				}
				node.left = temp;
				if(node.right != null){
					node.right.setGrandparent(node.parent);
				}
			}
		}

		private boolean needsToRotateLinear(RedBlackNode node) {
			if(node.parent.left == node){
				if(node.getGrandparent().right == node.parent){
					return true;
				}
				else{
					return false;
				}
			}
			else{
				if(node.getGrandparent().left == node.parent){
					return true;
				}
				else{
					return false;
				}

			}
		}

		private RedBlackNode uncle(RedBlackNode node) {
			if(node.getGrandparent() != null){
				if(node.getGrandparent().left == node.parent){
					return node.getGrandparent().right;
				}
				else if(node.getGrandparent().right == node.parent){
					return node.getGrandparent().left;
				}
				else{
					return null;
				}
			}
			return null;
		}	

		RedBlackNode getNiece(){
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

		RedBlackNode getNephew() {
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

		RedBlackNode getSibling() {
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
	}



	public RedBlackTree(){
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

	public void insertNode(Edge e){
		if(this.root == null){
			RedBlackNode n = new RedBlackNode(this,e);
			n.level = 1;
			n.color = "black";
			n.parent = null;
			this.size = 1;
			this.root = n;
			this.min = n;
		}
		else{

		}
	}

	public void deleteNode(Edge e){
		if(this.root == null){

		}
		else{
			RedBlackNode confirmDeletion = this.root.deleteNode(this.root,e);
			this.resetRoot(this.root);
			if(confirmDeletion != null){
				//System.out.printf("Deleted Node: %s\nNew Node frequency: %d\n\n", confirmDeletion.getValue(),confirmDeletion.getFrequency());
			}
			else{
				//System.err.printf("\nThe Node: '%s' was not found to delete\n\n", v);
			}
		}
	}
}
