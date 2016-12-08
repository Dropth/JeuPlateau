import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
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


public class VueCarte extends JFrame implements ActionListener,WindowListener{
	
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
	private JButton bSwitch;
	private ArrayList<JButton> alB;
	private boolean isE1;
	
	public VueCarte (VueProjet parent, Equipe eq1, Equipe eq2, boolean isOk)
	{
		super();
		
		this.parent = parent;
		e1 = eq1;
		e2 = eq2;
		isE1 = isOk;
		
		if(isE1)
			alC = e1.getAlC();
		else
			alC = e2.getAlC();
		
		
		setSize(550, 505);
		
		fontEntered = new Font(Font.DIALOG, Font.LAYOUT_LEFT_TO_RIGHT, 18);
		fontEntered2 = new Font(Font.DIALOG, Font.LAYOUT_LEFT_TO_RIGHT, 28);
		
		this.setTitle("Consultation des Cartes Saugrenues");
		setLocationRelativeTo(null);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setLayout(new BorderLayout());
		this.addWindowListener(this);
		
		initComponent();
	}
	
	/**
	 * 
	 * Cette méthode insère dans la frame tous les élément nécessaire.
	 * 
	 */
	
	private void initComponent()
	{
		
		setBackground(Color.white);
		
		/******************/
		
		alB = new ArrayList<JButton> ();
		
		//PANEL CENTRE
		
		JPanel pCentre = new JPanel(new GridLayout(alC.size(),1));
		
		for(CarteS c : alC) {
			
			JPanel p = new JPanel(new FlowLayout());
			
			JLabel l = new JLabel(c.getTitre());
			l.setFont(fontEntered);
			l.setToolTipText(c.getEffet());
			
			p.add(l);
			
			JButton b = new JButton("X");
			b.setFont(fontEntered);
			b.addActionListener(this);
			alB.add(b);
			
			p.add(b);
			
			pCentre.add(p);
		}
		
		add(pCentre, BorderLayout.CENTER);
		
		
		/***********/
		
		//PANEL SUD
		
		JPanel pSud = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		bSwitch = new JButton("Switch");
		
		bSwitch.setFont(fontEntered);
		
		bSwitch.addActionListener(this);
		
		pSud.add(bSwitch);
		
		add(pSud, BorderLayout.SOUTH);
		
		/***********/
		
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource() == bSwitch) {
			
			ArrayList<CarteS> alTemp;
			
			alTemp = e1.getAlC();
			e1.setAlC(e2.getAlC());
			e2.setAlC(alTemp);
			
			new VueCarte(parent, e1, e2, isE1);
			this.dispose();
		}
		
		for(int i = 0; i < alB.size(); i++) {
			
			if (e.getSource() == alB.get(i)) {
				
				alC.remove(i);
				
				if(isE1)
					e1.setAlC(alC);
				else
					e2.setAlC(alC);
				
				new VueCarte(parent, e1, e2, isE1);
				this.dispose();
			}
			
		}
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		parent.maj2(e1, e2);
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}
