import java.util.*;
public class NamnSort implements Comparator<Löpare> {
	public int compare(Löpare o1, Löpare o2){
		return o1.getNamn().compareTo(o2.getNamn());
	}
}
