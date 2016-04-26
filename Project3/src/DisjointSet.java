import java.util.ArrayList;
import java.util.Collections;

public class DisjointSet {	

	private DSRBT rootList;

	/**
	public static class Node{
		private int rank;
		private Vertex value;
		private Node parent;
		private EdgeRedBlackTree tTree;
		private SinglyLinkedList adjacencyList;
		private int level;
		private Node next;
		private Node prev;
		private Node child;

		public Node(Vertex v){
			value = v;
			level = 0;
			tTree = new EdgeRedBlackTree();
			adjacencyList = new SinglyLinkedList();
		}
	}
	*/

	public DisjointSet(){
		rootList = new DSRBT();
	}

	public DSRBT.RedBlackNode getNode(Vertex v){
		return rootList.findNode(v.getValue());
		/**
		Node temp = front;
		while(temp != null){
			if(temp.value.getValue() == v.getValue()){
				return temp;
			}
			temp = temp.next;
		}
		return null;
		*/
	}

	public void makeSet(Vertex v){
		rootList.insertNode(v);
		DSRBT.RedBlackNode temp = rootList.findNode(v.getValue());
		temp.pParent = temp;
		temp.rank = 0;
		/**
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
		*/
	}

	/** Merge Vertex b into a
	 * 
	 * @param a
	 * @param b
	 */
	public void union(DSRBT.RedBlackNode a, DSRBT.RedBlackNode b){
		DSRBT.RedBlackNode aRoot = findSet(a);
		DSRBT.RedBlackNode bRoot = findSet(b);
		if(aRoot.value.getValue() != bRoot.value.getValue()){
			if(aRoot.rank < bRoot.rank){
				//aRoot.parent  = bRoot;
				//a.pParent = b;
				aRoot.pParent = bRoot;
				a.adjacencyList.addItem(b);
				b.adjacencyList.addItem(a);
			}
			else if(aRoot.rank > bRoot.rank){
				//bRoot.parent = aRoot;
				//b.pParent = a;
				bRoot.pParent = aRoot;
				a.adjacencyList.addItem(b);
				b.adjacencyList.addItem(a);

			}
			else{
				//bRoot.parent = aRoot;
				bRoot.pParent = aRoot;
				//b.pParent = a;
				a.adjacencyList.addItem(b);
				b.adjacencyList.addItem(a);
				aRoot.rank++;
			}
		}
	}

	public DSRBT.RedBlackNode findSet(DSRBT.RedBlackNode a){
		if(a.pParent != a){
			//a.pParent = findSet(a.pParent);
			return findSet(a.pParent);
		}
		return a.pParent;
	}

	public void printDisjointSet() {
		rootList.printBreadthTraversal(rootList.root);
		/**
		Node temp = front;
		while(temp.next != null){
			System.out.printf(temp.value.toString() + "-->");
			temp = temp.next;
		}
		*/
		//System.out.printf(temp.value.toString()+"\n");
	}

	@SuppressWarnings("unchecked")
	public void printDisjointTree(int root, EdgeRedBlackTree eTree,ArrayList<Integer> verticesList, EdgeDoublyLinkedList eList){
		BQueue<DSRBT.RedBlackNode> queue = new BQueue<DSRBT.RedBlackNode>();
		BQueue<DSRBT.RedBlackNode> eQueue = new BQueue<DSRBT.RedBlackNode>();
		ArrayList <Edge> sEdge = new ArrayList<Edge>();
		//EdgeRedBlackTree vTree = new EdgeRedBlackTree();
		BinarySearchTreeEdge vTree = new BinarySearchTreeEdge();

		DSRBT.RedBlackNode nRoot = rootList.findNode(root);

		queue.enqueue(nRoot);
		System.out.printf("0: %d;\n",root);
		int v = 1;
		long graphWeight = 0;
		DSRBT.RedBlackNode temp = null;
		DSRBT.RedBlackNode adjNode = null;
		DSRBT.RedBlackNode temp2;
		Edge fEdge;
		Edge fEdge2;
		Edge check1;
		Edge check2;
		if(queue.getSize()!= 0){
			while(queue.getSize() != 0){
				while(queue.getSize() != 0){
					//Each node in queue
					temp = queue.dequeue();
					if(temp.adjacencyList.getSize() != 0){
						//add temp to used parent nodes
						
						while(temp.adjacencyList.getSize() != 0){
							adjNode = temp.adjacencyList.removeItem();
							if(adjNode.value == nRoot.value){
								continue;
							}
							if(vTree.findNode(temp.value,adjNode.value) != null){
								continue;
							}
							if(temp.value != nRoot.value){
								fEdge2 = null;
								fEdge = eTree.deleteNode(temp.value, adjNode.value);
								if(fEdge == null){
									fEdge2 = eTree.deleteNode(adjNode.value, temp.value);
								}
								//fEdge = eList.removeEdge(temp.value, adjNode.value);
								//fEdge2 = eList.removeEdge(adjNode.value, temp.value);
								if(((fEdge != null) || (fEdge2 != null))){
								//if((eTree.findNode(fEdge) != null) || (eTree.findNode(fEdge2) != null)){
									if(fEdge != null){
										//check to see if removed
										//check1 = eList.removeEdge(temp.value, adjNode.value);
										eQueue.enqueue(adjNode);
										sEdge.add(fEdge);
										vTree.insertNode(fEdge);
										//eTree.deleteNode(fEdge);
									}

									else if(fEdge2 != null){
										//check2 = eList.removeEdge(adjNode.value, temp.value);
										fEdge2.reverse();
										eQueue.enqueue(adjNode);
										sEdge.add(fEdge2);
										vTree.insertNode(fEdge2);
										//eTree.deleteNode(fEdge2);
									}
								}
							}
							else{
								//if(vTree.findNode(adjNode.value.getValue()) == null){
									fEdge2 = null;
									fEdge = eTree.deleteNode(temp.value, adjNode.value);
									if(fEdge == null){
										fEdge2 = eTree.deleteNode(adjNode.value, temp.value);
									}
									if(((fEdge != null) || (fEdge2 != null))){
										//fEdge = eList.removeEdge(temp.value, adjNode.value);
										if((fEdge != null)){
										//if(eTree.findNode(fEdge) != null){
											eQueue.enqueue(adjNode);
											sEdge.add(fEdge);
											vTree.insertNode(fEdge);
											//eTree.deleteNode(fEdge);
										}
										else if(fEdge2 != null){
											fEdge2.reverse();
											eQueue.enqueue(adjNode);
											sEdge.add(fEdge2);
											vTree.insertNode(fEdge2);
										}
									}
								//}
							}
							//if((eList.getEdge(temp.value, adjNode.value) != null) || (eList.getEdge(adjNode.value, temp.value)!= null)){
						}
						//vTree.insertNode(temp.value.getValue());
					}
				}
				if(eQueue.getSize() != 0){
					System.out.printf("%d: ",v);
					while(eQueue.getSize() != 0){
						temp2 = eQueue.dequeue();
						queue.enqueue(temp2);
					}
					Collections.sort(sEdge, new ComparatorEdge());
					//sEdge.mergeSort(sEdge.getFront());
					Edge tempPointer;
					while(sEdge.size() != 0){
						tempPointer = sEdge.remove(0);
						graphWeight = graphWeight + tempPointer.getWeight();
						System.out.printf("%d(%d)%d; ",tempPointer.getVertex2().getValue(),tempPointer.getVertex1().getValue(),tempPointer.getWeight());
					}
					System.out.println("");
					v++;
				}
			}
			System.out.printf("weight: %d\n", graphWeight);
			System.out.printf("unreachable: %d\n", verticesList.size() - (vTree.getSize()+1));
		}
		//The Tree is empty
		else{
			System.out.printf("The Graph could not find a BFS from the selected Node");
		}

	}
}
