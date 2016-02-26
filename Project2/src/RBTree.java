
public class RBTree {

	//Private
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
			this.root = n;
			this.min = n;
		}
		this.root.insertNode(this,v);
		
	}
}
