import java.util.*;

public class TidSort implements Comparator<Löpare> {
		public int compare(Löpare o1, Löpare o2){
			Double d1 = o1.getTid();
			Double d2 = o2.getTid();
			return d1.compareTo(d2);
		}

}
