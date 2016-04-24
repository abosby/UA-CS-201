import java.util.Comparator;

public class ComparatorEdge implements Comparator {

	public int compare(Object o1, Object o2) {
		Edge e1 = (Edge) o1;
		Edge e2 = (Edge) o2;

		return Long.compare(e1.getWeight(), e2.getWeight());
	}

}
