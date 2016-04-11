public class DisjointSet {	
	
	private Node front;
	private Node back;
	private int size;
	
	public static class Node{
		private int rank;
		private Vertex value;
		private Node parent;
		private Node next;
		private Node prev;
		
		public Node(Vertex v){
			value = v;
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
		if(aRoot != bRoot){
			if(aRoot.rank < bRoot.rank){
				aRoot.parent  = bRoot;
			}
			else if(aRoot.rank > bRoot.rank){
				bRoot.parent = aRoot;
			}
			else{
				bRoot.parent = aRoot;
				aRoot.rank = aRoot.rank++;
			}
		}
	}
	
	public Node findSet(Node a){
		if(a.value.getParent() == a.value){
			return a;
		}
		else{
			a.parent = findSet(a.parent);
			return a.parent;
		}
	}

	public void printDisjointSet() {
		Node temp = front;
		while(temp.next != null){
			System.out.printf(temp.value.toString() + "-->");
			temp = temp.next;
		}
		System.out.printf(temp.value.toString()+"\n");
	}
	
	public void printDisjointTree(){
		Node temp = front;
		while(temp != null){
			System.out.printf("("+temp.value.toString()+")");
			Vertex tempV = temp.value;
			while(tempV.getParent() != tempV){
				System.out.printf(tempV.getValue() +"-->");
				tempV = tempV.getParent();
			}
			System.out.printf("\n");
			temp = temp.next;
		}
	}
	
	
	
}
