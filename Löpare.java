
public class L�pare {
	private int startnr;
	static private int iD;
	private String namn;
	private int �lder;
	private double tid = 900000;
	private String land;
	
	public L�pare(int startnr, String namn, String land, int �lder){
		this.startnr = startnr;
		this.namn = namn;
		this.land = land;
		this.�lder = �lder;
	}
		
	public int getStartnr(){
		return startnr;
	}
	public String getNamn(){
		return namn;
	}
	public String getLand(){
		return land;
	}
	public int get�lder(){
		return �lder;
	}
	public double getTid(){
		return tid;
	}
	public static int getID(){
		return iD;
	}
	public void setTid(double input){
		tid = input;
	}
	public static void setID(int change){
		iD = change;
	}
	public String toString (){
		if (tid == 900000)
			return startnr + " " + namn + " " + land + " " + �lder +" " + " -- \n";
		else
			return startnr + " " + namn + " " + land + " " + �lder +" " + tid + "\n";
	}
}