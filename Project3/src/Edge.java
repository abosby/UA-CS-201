import java.util.Comparator;

public class Edge {

	private Vertex vertex1;
	private Vertex vertex2;
	private int weight;
	private Edge parent;

	public Edge getParent() {
		return parent;
	}

	public void setParent(Edge parent) {
		this.parent = parent;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public Vertex getVertex2() {
		return vertex2;
	}

	public void setVertex2(Vertex vertex2) {
		this.vertex2 = vertex2;
	}

	public Vertex getVertex1() {
		return vertex1;
	}

	public void setVertex1(Vertex vertex1) {
		this.vertex1 = vertex1;
	}
	
	public Edge(String v1, String v2){
		Vertex vertex1 = new Vertex(Integer.parseInt(v1));
		Vertex vertex2 = new Vertex(Integer.parseInt(v2));
		this.setVertex1(vertex1);
		this.setVertex2(vertex2);
		this.weight = 0;
	}

	public Edge(String v1, String v2, int weight){
		Vertex vertex1 = new Vertex(Integer.parseInt(v1));
		Vertex vertex2 = new Vertex(Integer.parseInt(v2));
		this.setVertex1(vertex1);
		this.setVertex2(vertex2);
		this.setWeight(weight);
	}
	
	@Override
	public String toString(){
		String s = "[("+this.getWeight()+") "+this.getVertex1() + "-" + this.getVertex2() + " ]";
		return s;
	}

	//Reverses the two vertices so that that is it an undirected graph
	public void reverse() {
		Vertex temp = this.getVertex1();
		this.setVertex1(getVertex2());
		this.setVertex2(temp);
	}
	

	
}
