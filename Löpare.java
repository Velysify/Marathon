
public class Löpare {
	private int startnr;
	static private int iD;
	private String namn;
	private int ålder;
	private double tid = 900000;
	private String land;
	
	public Löpare(int startnr, String namn, String land, int ålder){
		this.startnr = startnr;
		this.namn = namn;
		this.land = land;
		this.ålder = ålder;
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
	public int getÅlder(){
		return ålder;
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
			return startnr + " " + namn + " " + land + " " + ålder +" " + " -- \n";
		else
			return startnr + " " + namn + " " + land + " " + ålder +" " + tid + "\n";
	}
}