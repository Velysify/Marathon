import java.util.*;

public class TidSort implements Comparator<L�pare> {
		public int compare(L�pare o1, L�pare o2){
			Double d1 = o1.getTid();
			Double d2 = o2.getTid();
			return d1.compareTo(d2);
		}

}
