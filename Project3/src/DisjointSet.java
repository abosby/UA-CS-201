
public class DisjointSet {	

	private Node front;
	private Node back;
	private int size;

	public static class Node{
		private int rank;
		private Vertex value;
		private Node parent;
		private SinglyLinkedList adjacencyList;
		private int level;
		private Node next;
		private Node prev;
		private Node child;

		public Node(Vertex v){
			value = v;
			level = 0;
			adjacencyList = new SinglyLinkedList();
		}
	}

	public DisjointSet(){
		front = null;
		back = null;
	}

	public Node getNode(Vertex v){
		Node temp = front;
		while(temp != null){
			if(temp.value.getValue() == v.getValue()){
				return temp;
			}
			temp = temp.next;
		}
		return null;
	}

	public void makeSet(Vertex v){
		Node temp = new Node(v);
		temp.value.setParent(temp.value);
		temp.parent = temp; 
		temp.rank = 0;
		if(front == null){
			front = temp;
			front.next = temp;
			back = temp;
			back.prev = temp;
		}
		else{
			temp.prev = back;
			back.next =temp;
			back = temp;
		}
	}

	/** Merge Vertex b into a
	 * 
	 * @param a
	 * @param b
	 */
	public void union(Node a, Node b){
		Node aRoot = findSet(a);
		Node bRoot = findSet(b);
		if(aRoot.value != bRoot.value){
			if(aRoot.rank < bRoot.rank){
				//aRoot.parent  = bRoot;
				a.parent = b;
				a.adjacencyList.addItem(b);
				b.adjacencyList.addItem(a);
			}
			else if(aRoot.rank > bRoot.rank){
				//bRoot.parent = aRoot;
				b.parent = a;
				a.adjacencyList.addItem(b);
				b.adjacencyList.addItem(a);

			}
			else{
				//bRoot.parent = aRoot;
				b.parent = a;
				a.adjacencyList.addItem(b);
				b.adjacencyList.addItem(a);
				aRoot.rank = aRoot.rank++;
			}
		}
	}

	public Node findSet(Node a){
		if(a.parent != a){
			return findSet(a.parent);
		}
		return a.parent;
	}

	public void printDisjointSet() {
		Node temp = front;
		while(temp.next != null){
			System.out.printf(temp.value.toString() + "-->");
			temp = temp.next;
		}
		System.out.printf(temp.value.toString()+"\n");
	}

	public void printDisjointTree(int root, RedBlackTree eTree,DoublyLinkedList eList){
		BQueue<Node> queue = new BQueue<Node>();
		BQueue<Node> eQueue = new BQueue<Node>();
		DoublyLinkedList sEdge = new DoublyLinkedList();
		BinarySearchTree vTree = new BinarySearchTree();
		Node nRoot = front;
		while(nRoot.value.getValue() != root){
			nRoot = nRoot.next;
		}
		queue.enqueue(nRoot);
		System.out.printf("0: %d;\n",root);
		int v = 1;
		Node temp = null;
		Node adjNode = null;
		Node temp2;
		Edge fEdge;
		Edge fEdge2;
		Edge check1;
		Edge check2;
		while(queue.getSize() != 0){
			System.out.printf("%d: ",v);
			while(queue.getSize() != 0){
				//Each node in queue
				temp = queue.dequeue();
				if(temp.adjacencyList.getSize() != 0){
					//add temp to used parent nodes
					
					while(temp.adjacencyList.getSize() != 0){
						adjNode = temp.adjacencyList.removeItem();
						if(temp.value != nRoot.value){
							fEdge = eList.removeEdge(temp.value, adjNode.value);
							fEdge2 = eList.removeEdge(adjNode.value, temp.value);
							if(((fEdge != null) || (fEdge2 != null)) && (vTree.findNode(temp.value.getValue()) == null)){
							//if((eTree.findNode(fEdge) != null) || (eTree.findNode(fEdge2) != null)){
								if(fEdge != null){
									//check to see if removed
									check1 = eList.removeEdge(temp.value, adjNode.value);
									eQueue.enqueue(adjNode);
									sEdge.addItem(fEdge);
									//eTree.deleteNode(fEdge);
								}

								else if(fEdge2 != null){
									check2 = eList.removeEdge(adjNode.value, temp.value);
									eQueue.enqueue(adjNode);
									sEdge.addItem(fEdge2);
									//eTree.deleteNode(fEdge2);
								}
							}
						}
						else{
							if(vTree.findNode(adjNode.value.getValue()) == null){
								fEdge = eList.removeEdge(temp.value, adjNode.value);
								if((fEdge != null) && (vTree.findNode(temp.value.getValue()) == null)){
								//if(eTree.findNode(fEdge) != null){
									if(fEdge != null){
										eQueue.enqueue(adjNode);
										sEdge.addItem(fEdge);
										//eTree.deleteNode(fEdge);
									}
								}
							}
						}
						//if((eList.getEdge(temp.value, adjNode.value) != null) || (eList.getEdge(adjNode.value, temp.value)!= null)){
					}
					vTree.insertNode(temp.value.getValue());
				}
			}
			//Pass next level edges from eQueue to the queue
			/**
			while(eQueue.getSize() != 0){
				temp2 = eQueue.dequeue();
				fEdge = eList.removeEdge(temp2.value, temp2.parent.value);
				fEdge2 = eList.removeEdge(temp2.parent.value, temp2.value);
				if(fEdge != null){
					queue.enqueue(temp2);
					sEdge.addItem(fEdge);
				}
				else if(fEdge2 != null){
					queue.enqueue(temp2.parent);
					sEdge.addItem(fEdge2);
				}
			}
			*/
			while(eQueue.getSize() != 0){
				temp2 = eQueue.dequeue();
				queue.enqueue(temp2);
			}
			sEdge.mergeSort(sEdge.getFront());
			Edge tempPointer;
			while(sEdge.getSize() != 0){
				tempPointer = sEdge.removeItem();
				System.out.printf("%d(%d)%d; ",tempPointer.getVertex2().getValue(),tempPointer.getVertex1().getValue(),tempPointer.getWeight());
			}
			System.out.println("");
			v++;
		}

	}
		/**
		BQueue<Node> queue = new BQueue<Node>();
		BQueue<Node> eQueue = new BQueue<Node>();
		DoublyLinkedList sEdge = new DoublyLinkedList();
		Node nRoot = front;
		//Find Root
		while(nRoot.value.getValue() != root){
			nRoot = nRoot.next;
		}
		Node temp = null;
		Node prev = nRoot;
		queue.enqueue(nRoot);
		System.out.printf("0: %d;\n",root);
		int v = 1;
		while(true){
			System.out.printf("%d: ",v);
			while(queue.getSize() != 0){
				prev = temp;
				temp = queue.dequeue();
				Node tNode = front;
				while(tNode != null){
					//If temp is the parent
					if(tNode.parent.value == temp.value){
						tNode.level = temp.level++;
						//queue.enqueue(tNode);
						eQueue.enqueue(tNode);
					}
					//If temp is the child

					else if(temp.parent.value == tNode.value){
						//if((temp.parent.value != nRoot.value) && (tNode.value != nRoot.value)){
						//if(eList.getEdge(tNode.value,temp.parent.value) != null){
						tNode.level = temp.level++;
						tNode.child = temp;
						//Reset children
						Node tTNode = tNode;
						Node tempNode = temp;
						Node advanceNode = tNode;
						while(tTNode.parent != advanceNode){
							advanceNode = tTNode.parent;
							tTNode.parent = tempNode;
							tempNode = tTNode;
							tTNode = advanceNode;
						}
						//queue.enqueue(tNode);
						eQueue.enqueue(tNode);
					}
					tNode = tNode.next;
				}
			}
			//Pass next level edges from eQueue to the queue
			Node temp2;
			Edge fEdge;
			while(eQueue.getSize() != 0){
				temp2 = eQueue.dequeue();
				fEdge = eList.removeEdge(temp2.value,temp2.parent.value);
				//fEdge = eList.getEdge(temp2.value, temp.value);
				if(fEdge != null){
					queue.enqueue(temp2);
					sEdge.addItem(fEdge);
				}
			}
			sEdge.mergeSort(sEdge.getFront());
			Edge tempPointer;
			while(sEdge.getSize() != 0){
				tempPointer = sEdge.removeItem();
				System.out.printf("%d(%d)%d; ",tempPointer.getVertex2().getValue(),tempPointer.getVertex1().getValue(),tempPointer.getWeight());
			}
			System.out.println("");
			v++;
		}
	}
	*/
}
