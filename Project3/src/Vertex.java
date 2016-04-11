public class Vertex{

	//Variables
	private int value;
	private Vertex parent;

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	//Constructor
	public Vertex(int v){
		this.setValue(v);
	}

	@Override
	public String toString(){
		return Integer.toString(this.getValue());
	}

	public Vertex getParent() {
		return parent;
	}

	public void setParent(Vertex parent) {
		this.parent = parent;
	}

}
