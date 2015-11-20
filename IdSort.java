import java.util.*;

public class IdSort implements Comparator<Löpare> {
		public int compare(Löpare o1, Löpare o2){
			return o1.getStartnr() - o2.getStartnr();
		}

}
