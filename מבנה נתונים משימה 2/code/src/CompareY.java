import java.util.Comparator;

public class CompareY implements Comparator {

	public int compare(Object o1, Object o2) {
		return ((Point)o1).getY()-((Point)o2).getY();
	}
}