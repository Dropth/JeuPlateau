import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Debut {
	
	public Debut() {
	
		JTextField tfE1 = new JTextField();
		JTextField tfE2 = new JTextField();
		
		int option = JOptionPane.showOptionDialog(null, 
			    new Object[] {"Equipe 1 :", tfE1, "Equipe 2 :", tfE2},
			    "Nom des equipes",
			    JOptionPane.OK_CANCEL_OPTION,
			    JOptionPane.QUESTION_MESSAGE, null,  null, null);
		
		if (option == JOptionPane.OK_OPTION) {
			
			Equipe e1 = new Equipe(tfE1.getText(), 0, new ArrayList<CarteS>());
			Equipe e2 = new Equipe(tfE2.getText(), 0, new ArrayList<CarteS>());
		
			new VueProjet(e1,e2);
		}
	
	}



	public static void main(String[] args) {
		
		new Debut();
	}

}
