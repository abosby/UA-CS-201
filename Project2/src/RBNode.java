
public class RBNode {

	
	//Private
	private RBTree RBT;
	private String value;
	private String color;
	private int frequency;
	private int level;
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
		this.setLevel(0);
		this.setParent(null);
		this.setLeft(null);
		this.setRight(null);
	}
	
}
