public class CarteS {
	
	private String titre;
	private String effet;
	private String image;
	
	public CarteS(String titre, String effet, String image) {
		super();
		this.titre = titre;
		this.effet = effet;
		this.image = image;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getEffet() {
		return effet;
	}

	public void setEffet(String effet) {
		this.effet = effet;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "CarteS [titre=" + titre + ", effet=" + effet + ", image="
				+ image + "]";
	}
	
	
	

}
