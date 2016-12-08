import java.util.ArrayList;

public class Equipe {
	
	private String nom;
	private int points;
	private ArrayList<CarteS> alC;
	
	public Equipe(String nom, int points, ArrayList<CarteS> alC) {
		super();
		this.nom = nom;
		this.points = points;
		this.alC = alC;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
		
		if (points < 0)
			this.points = 0;
	}

	public ArrayList<CarteS> getAlC() {
		return alC;
	}

	public void setAlC(ArrayList<CarteS> alC) {
		this.alC = alC;
	}
	
	public void addC(CarteS c) {
		this.alC.add(c);
	}

	@Override
	public String toString() {
		return "Equipe [nom=" + nom + ", points=" + points + ", alC=" + alC
				+ "]";
	}
	
}
