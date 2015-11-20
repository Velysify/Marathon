import java.util.*;

public class ÅlderSort implements Comparator<Löpare> {
		public int compare(Löpare o1, Löpare o2){
			return o1.getÅlder()-o2.getÅlder();
		}

}

