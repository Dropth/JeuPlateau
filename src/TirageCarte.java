import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;


public class TirageCarte extends JFrame implements ActionListener{
	
	private VueProjet parent;
	private Equipe e1,e2;
	private ArrayList<CarteS> alC;
	private Font fontEntered;
	private Font fontEntered2;
	private CarteS carte;
	private JButton buttonE1;
	private JButton buttonE2;
	private JButton bCancel;
	private int random;
	
	public TirageCarte (VueProjet parent, Equipe eq1, Equipe eq2, ArrayList<CarteS> alCarte)
	{
		super();
		
		this.parent = parent;
		e1 = eq1;
		e2 = eq2;
		alC = alCarte;
		
		
		setSize(550, 505);
		
		fontEntered = new Font(Font.DIALOG, Font.LAYOUT_LEFT_TO_RIGHT, 18);
		fontEntered2 = new Font(Font.DIALOG, Font.LAYOUT_LEFT_TO_RIGHT, 28);
		
		this.setTitle("Tirage de carte");
		setLocationRelativeTo(null);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setLayout(new BorderLayout());
		initComponent();
	}
	
	/**
	 * 
	 * Cette méthode insère dans la frame tous les élément nécessaire.
	 * 
	 */
	
	private void initComponent()
	{
		int max = alC.size()-1;
		int min = 0;
		
		random = (int) (Math.random() * ( max - min ));
		
		carte = alC.get(random);
		
		setBackground(Color.white);
		
		/******************/
		
		//PANEL CENTRE
		
		JPanel pCentre = new JPanel(new BorderLayout());
		
		pCentre.setBackground(Color.white);
		
		String chemin = carte.getImage();
		
		JLabel image = new JLabel(new ImageIcon(chemin));
		
		JLabel l = new JLabel(carte.getTitre(), JLabel.CENTER);
		l.setFont(fontEntered2);
		pCentre.add(l, BorderLayout.NORTH);
		
		JPanel pImg = new JPanel(new GridLayout(2,1,5,5));
		
		JTextPane l4 = new JTextPane();
		l4.setEditable(false);
		StyledDocument doc = l4.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		l4.setText(carte.getEffet());
		l4.setFont(fontEntered2);
		
		pCentre.add(l4, BorderLayout.SOUTH);
		pCentre.add(image);
		
		add(pCentre, BorderLayout.CENTER);
		
		
		/***********/
		
		//PANEL SUD
		
		JPanel pSud = new JPanel(new GridLayout(1,2,10,10));
		
		buttonE1 = new JButton(e1.getNom());
		buttonE2 = new JButton(e2.getNom());
		
		buttonE1.setFont(fontEntered);
		buttonE2.setFont(fontEntered);
		
		buttonE1.addActionListener(this);
		buttonE2.addActionListener(this);
		
		pSud.add(buttonE1);
		pSud.add(buttonE2);
		
		add(pSud, BorderLayout.SOUTH);
		
		/***********/
		
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource()==buttonE1) {
			
			e1.addC(carte);
			
			alC.remove(random);
			
			parent.maj(e1, e2, alC);
			
			this.dispose();
		}
		else {
			
			e2.addC(carte);
			
			alC.remove(random);
			
			parent.maj(e1, e2, alC);
			
			this.dispose();
			
		}
		
	}

}
