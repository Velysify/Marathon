import java.util.*;
public class NamnSort implements Comparator<L�pare> {
	public int compare(L�pare o1, L�pare o2){
		return o1.getNamn().compareTo(o2.getNamn());
	}
}
