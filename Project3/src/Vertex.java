public class Vertex{

	//Variables
	private String value;
	private Vertex parent;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	//Constructor
	public Vertex(String v){
		this.setValue(v);
	}

	@Override
	public String toString(){
		return this.getValue();
	}

	public Vertex getParent() {
		return parent;
	}

	public void setParent(Vertex parent) {
		this.parent = parent;
	}

}
