import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;


public class VueProjet extends JFrame implements ActionListener,KeyListener,WindowListener, MouseListener {
	
	private Equipe e1,e2;
	private ArrayList<CarteS> alC;
	
	private ArrayList<Button> alB;
	private Font fontEntered;
	private JPanel ouest;
	private JPanel est;
	private JPanel centre;
	private JSplitPane splitOuest;
	private JTextField tfEquipe1;

	private JTextField tfEquipe2;

	private JButton butPoint;

	private Font fontEntered2;

	private Button bCarte;
	
	private VueProjet vp;

	private JLabel lP1;

	private JLabel lP3;

	private JPanel pCentre;
	private JPanel centreHaut;

	public VueProjet(Equipe eq1, Equipe eq2)
	{
		
		String chaine="";
		String fichier ="./BDCarte.txt";
		
		alC = new ArrayList<CarteS>();
		
		
		//lecture du fichier texte	
		try{
			InputStream ips=new FileInputStream(fichier); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String ligne;
			while ((ligne=br.readLine())!=null){
				String[] s = ligne.split(":");
				
				System.out.println(s[0]);
				
				alC.add(new CarteS(s[0], s[1], s[2]));
				
				
			}
			br.close(); 
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
		
		e1 = eq1;
		e2 = eq2;
		
		vp = this;
		
		alB = new ArrayList<Button> ();
		
		fontEntered = new Font(Font.DIALOG, Font.LAYOUT_LEFT_TO_RIGHT, 18);
		fontEntered2 = new Font(Font.DIALOG, Font.LAYOUT_LEFT_TO_RIGHT, 28);
		
		this.setTitle("Schopengame");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		 
		//this.pack();
		this.setLocation(
		        (screenSize.width-this.getWidth())/2,
		        (screenSize.height-this.getHeight())/2
		        );
		
		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int)dimension.getHeight();
		int width  = (int)dimension.getWidth();
		
		this.setExtendedState(JFrame.MAXIMIZED_BOTH); // aligns itself with windows task bar
		//this.setUndecorated(true);
		// set maximum screen   
		//this.setSize((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(), (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight());
		//setExtendedState(JFrame.MAXIMIZED_BOTH);
		
		
		setSize(width, height);
		
		//setSize(height, width);
		
		//setLocation(width,height);
		 
		
		//this.setPreferredSize(new Dimension(1024,768));

		this.addWindowListener((WindowListener) this);
		
		initComponent();
	}
	
	public void initComponent () {

		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			SwingUtilities.updateComponentTreeUI(this);
		} catch (Exception e)
		{
			System.out.println("FrmUsers.FrmUsers : impossible d'appliquer le thème du système");
		}
		
		//JToolBar Display_ToolBar = createDisplayToolBar();
		
				
		//JMenuBar Principal_Menu = createMenu();
		//this.setJMenuBar(Principal_Menu);

		this.ouest = new JPanel(new GridLayout(2, 1));
		this.est = new JPanel(new BorderLayout());

		// Gestion du panel à droite soit le panel Est
		// Création d'un autre panel à l'intérieur de clui-ci pour gérer les
		// différent boutons.

		// Le JPanel elements correspont aux 9 boutons d'ajout d'éléments
		
		centre = new JPanel(new BorderLayout());
		centre.setBackground(Color.white);
		
		pCentre = new JPanel(new BorderLayout());
		pCentre.setBackground(Color.white);
		
		centreHaut = new JPanel(new GridLayout(1, 2,5,5));
		lP1 = new JLabel(e1.getNom() + " : " + e1.getPoints(),JLabel.CENTER);
		lP1.setFont(fontEntered2);
		lP1.addMouseListener(this);
		bCarte = new Button("Carte Saugrenue");
		bCarte.addActionListener(this);
		bCarte.setFont(fontEntered);
		bCarte.setBackground(new Color(0x83A697));
		lP3 = new JLabel(e2.getNom() + " : " + e2.getPoints(),JLabel.CENTER);
		lP3.setFont(fontEntered2);
		lP3.addMouseListener(this);
		
		centreHaut.add(lP1);
		centreHaut.add(bCarte);
		centreHaut.add(lP3);
		
		centre.add(centreHaut,BorderLayout.NORTH);

		/**************************************************************************
		 * Centre bas => Ajouter des points
		 */
		
		
		
		JPanel centreBas = new JPanel(new GridLayout(1, 5,10,10));
		JLabel l1 = new JLabel(e1.getNom());
		l1.setFont(fontEntered);
		tfEquipe1 = new JTextField();
		tfEquipe1.setFont(fontEntered);
		
		JLabel l2 = new JLabel(e2.getNom());
		l2.setFont(fontEntered);
		tfEquipe2 = new JTextField();
		tfEquipe2.setFont(fontEntered);
		
		butPoint = new JButton("OK");
		butPoint.addActionListener(this);
		butPoint.setFont(fontEntered);
		
		centreBas.add(l1);
		centreBas.add(tfEquipe1);
		
		centreBas.add(l2);
		centreBas.add(tfEquipe2);
		
		centreBas.add(butPoint);
		
		
		
		centre.add(centreBas, BorderLayout.SOUTH);
		
		
		/*******************************************************************
		 * FIN
		 */
		centre.add(pCentre);
		
		//pArbo = new JPanel(new BorderLayout());
		ouest.setBackground(new Color(0xF0F0F0));
		ouest.setBorder(BorderFactory.createLineBorder(Color.black));
		ouest.setAlignmentX(ouest.getAlignmentX()+200);
		
		//JLabel j = new JLabel("Liste de tous les tickets");
		
		//pArbo.add(j, BorderLayout.NORTH);
		
		int taille = 10;
		ouest.setLayout(new GridLayout(taille,1,0,3));
			
		Color c1 = new Color(0xa82828);
		Color c2 = new Color(0xE383AD);
		Color c3 = new Color(0x7f7f7f);
		Color c4 = new Color(0x754b4b);
		Color c5 = new Color(0x6276f5);
		Color c6 = new Color(0x2e8c44);
		Color c7 = new Color(0xFFFFFF);
		Color c8 = new Color(0xe36c09);
		Color c9 = new Color(0x5f497a);
		Color c10 = new Color(0x4798af);

		Button b1 = new Button("Slap Test");
		Button b2 = new Button("Le Pictionary Footbalistique !");
		Button b3 = new Button("Débat");
		Button b4 = new Button("Les Défficiles");
		Button b5 = new Button("Mme. Mime");
		Button b6 = new Button("Le Taboulé");
		Button b7 = new Button("Listen");
		Button b8 = new Button("Le calembours raciste");
		Button b9 = new Button("Le pendu thématique");
		Button b10 = new Button("Mr. Mime");

		b1.setFont(fontEntered);
		b2.setFont(fontEntered);
		b3.setFont(fontEntered);
		b4.setFont(fontEntered);
		b5.setFont(fontEntered);
		b6.setFont(fontEntered);
		b7.setFont(fontEntered);
		b8.setFont(fontEntered);
		b9.setFont(fontEntered);
		b10.setFont(fontEntered);

		b1.setBackground(c1);
		b2.setBackground(c2);		
		b3.setBackground(c3);		
		b4.setBackground(c4);		
		b5.setBackground(c5);		
		b6.setBackground(c6);		
		b7.setBackground(c7);		
		b8.setBackground(c8);		
		b9.setBackground(c9);		
		b10.setBackground(c10);		

		ouest.add(b1);
		ouest.add(b2);
		ouest.add(b3);
		ouest.add(b4);
		ouest.add(b5);
		ouest.add(b6);
		ouest.add(b7);
		ouest.add(b8);
		ouest.add(b9);
		ouest.add(b10);

		alB.add(b1);
		alB.add(b2);
		alB.add(b3);
		alB.add(b4);
		alB.add(b5);
		alB.add(b6);
		alB.add(b7);
		alB.add(b8);
		alB.add(b9);
		alB.add(b10);

		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		b4.addActionListener(this);
		b5.addActionListener(this);
		b6.addActionListener(this);
		b7.addActionListener(this);
		b8.addActionListener(this);
		b9.addActionListener(this);
		b10.addActionListener(this);
			
		
	/*	
		try {
			for(Ticket t : db.listTicket())
			{
				//int i = Integer.valueOf(t.getCouleur());
				
				Integer i = t.getCouleur();
				
				Color c = new Color(i);
				
				Button b = new Button(t.getType());
				b.setFont(fontEntered);
				b.setBackground(c);
				//b.setForeground(c);
				
				
				
				ouest.add(b);
				alB.add(b);
				b.addActionListener(this);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		//JToolBar File_ToolBar = createFileToolBar();
		
		JPanel panTools = new JPanel();
		panTools.setLayout(new BorderLayout());
		//panTools.add(Display_ToolBar, BorderLayout.SOUTH);
		//panTools.add(File_ToolBar, BorderLayout.PAGE_START);
		add(panTools, BorderLayout.NORTH);
		
		//this.ouest.add(pArbo);
		//this.ouest.add(pTheme);

		JPanel elements = new JPanel(new GridLayout(9, 1));

		// Gestion du bouton apercu et du bas du panel Est

		this.est.add(elements, BorderLayout.NORTH);

		// Permet de gérer les barres de défilement
		this.splitOuest = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, ouest, centre);
		this.splitOuest.setDividerLocation(250);
		add(splitOuest);
		
		setIconImage(Toolkit.getDefaultToolkit().getImage("")); 

		
		this.pack();

		this.setVisible(true);
	}
	
	public void maj (Equipe eq1, Equipe eq2, ArrayList<CarteS> al) {
		
		e1 = eq1;
		e2 = eq2;
		
		alC = al;
		
		lP1.setText(e1.getNom() + " : " + e1.getPoints());
		lP3.setText(e2.getNom() + " : " + e2.getPoints());
		
		centreHaut.updateUI();
		centreHaut.revalidate();
		
		centre.updateUI();
		centre.revalidate();
		
	}

	public void maj2 (Equipe eq1, Equipe eq2) {
		
		e1 = eq1;
		e2 = eq2;
		
		lP1.setText(e1.getNom() + " : " + e1.getPoints());
		lP3.setText(e2.getNom() + " : " + e2.getPoints());
		
		centreHaut.updateUI();
		centreHaut.revalidate();
		
		centre.updateUI();
		centre.revalidate();
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		
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

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		for(Button b : alB) {
			
			if(b == e.getSource()) {
				
				if(b.getLabel().equals("Slap Test")) {
					
					pCentre.removeAll();
					
					pCentre.setLayout(new GridLayout(6,1,2,2));
					
					JLabel l1 = new JLabel(b.getLabel(), JLabel.CENTER);
					JLabel l2 = new JLabel("Epreuve collective", JLabel.CENTER);
					JLabel l3 = new JLabel("Gain actif : 300 & Gain Passif : 150", JLabel.CENTER);
					JTextPane l4 = new JTextPane();
					l4.setEditable(false);
					StyledDocument doc = l4.getStyledDocument();
					SimpleAttributeSet center = new SimpleAttributeSet();
					StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
					doc.setParagraphAttributes(0, doc.getLength(), center, false);
					l4.setText("Epreuve : Blind test. 3 manches gagnantes. Premier Slap, la musique s'arrête en cas d'erreur, la 2ème équipe propose. Si les 2 équipes se trompent, la musique reprend jusqu'au prochain slap. Au bout de 30 sec, seul l'artiste est requis.");
					JLabel l5 = new JLabel(" Petit + : Il faut slaper son partenaire pour prendre la parole. En cas d'égalité, la slap la plus violente l'emporte.", JLabel.CENTER);
					
					l1.setFont(fontEntered2);
					l2.setFont(fontEntered2);
					l3.setFont(fontEntered2);
					l4.setFont(fontEntered2);
					l5.setFont(fontEntered2);
					
					pCentre.add(l1);
					pCentre.add(l2);
					pCentre.add(l3);
					pCentre.add(l4);
					pCentre.add(l5);
					
					centre.updateUI();
					centre.revalidate();
				}
				
				if(b.getLabel().equals("Le Pictionary Footbalistique !")) {
					
					pCentre.removeAll();
					
					pCentre.setLayout(new GridLayout(6,1,2,2));
					
					JLabel l1 = new JLabel(b.getLabel(), JLabel.CENTER);
					JLabel l2 = new JLabel("Epreuve collective", JLabel.CENTER);
					JLabel l3 = new JLabel("Gain actif : 400 & Gain Passif : 200", JLabel.CENTER);
					JTextPane l4 = new JTextPane();
					l4.setEditable(false);
					StyledDocument doc = l4.getStyledDocument();
					SimpleAttributeSet center = new SimpleAttributeSet();
					StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
					doc.setParagraphAttributes(0, doc.getLength(), center, false);
					l4.setText("Epreuve : 3 manches gagnantes.");
					JLabel l5 = new JLabel("Petit + : Soccer Physics. Il faut gagner les 2 pour gagner.", JLabel.CENTER);
					
					l1.setFont(fontEntered2);
					l2.setFont(fontEntered2);
					l3.setFont(fontEntered2);
					l4.setFont(fontEntered2);
					l5.setFont(fontEntered2);
					
					pCentre.add(l1);
					pCentre.add(l2);
					pCentre.add(l3);
					pCentre.add(l4);
					pCentre.add(l5);
					
					centre.updateUI();
					centre.revalidate();
				}
				
				if(b.getLabel().equals("Débat")) {
					
					pCentre.removeAll();
					
					pCentre.setLayout(new GridLayout(6,1,2,2));
					
					JLabel l1 = new JLabel(b.getLabel(), JLabel.CENTER);
					JLabel l2 = new JLabel("Epreuve collective", JLabel.CENTER);
					JLabel l3 = new JLabel("Gain actif : 300 & Gain Passif : 150", JLabel.CENTER);
					JTextPane l4 = new JTextPane();
					l4.setEditable(false);
					StyledDocument doc = l4.getStyledDocument();
					SimpleAttributeSet center = new SimpleAttributeSet();
					StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
					doc.setParagraphAttributes(0, doc.getLength(), center, false);
					l4.setText("Epreuve : Question à la con avec débat");
					JLabel l5 = new JLabel("Petit + : Mot secret à prononcer durant l'épreuve = + 100 points. Interdiction de spammer de mots. 1 contre = + 100 points.", JLabel.CENTER);
					
					l1.setFont(fontEntered2);
					l2.setFont(fontEntered2);
					l3.setFont(fontEntered2);
					l4.setFont(fontEntered2);
					l5.setFont(fontEntered2);
					
					pCentre.add(l1);
					pCentre.add(l2);
					pCentre.add(l3);
					pCentre.add(l4);
					pCentre.add(l5);
					
					centre.updateUI();
					centre.revalidate();
				}
				
				if(b.getLabel().equals("Les Défficiles")) {
					
					pCentre.removeAll();
					
					pCentre.setLayout(new GridLayout(6,1,2,2));
					
					JLabel l1 = new JLabel(b.getLabel(), JLabel.CENTER);
					JLabel l2 = new JLabel("Epreuve collective", JLabel.CENTER);
					JLabel l3 = new JLabel("Gain actif : 500 & Gain Passif : 250", JLabel.CENTER);
					JTextPane l4 = new JTextPane();
					l4.setEditable(false);
					StyledDocument doc = l4.getStyledDocument();
					SimpleAttributeSet center = new SimpleAttributeSet();
					StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
					doc.setParagraphAttributes(0, doc.getLength(), center, false);
					l4.setText("Epreuve : Les 2 équipes effectuent une série de défis. La première équipe à tout réaliser gagne.");
					JLabel l5 = new JLabel("Petit + : L'équipe gagnante vole également une carte à l'adversaire.", JLabel.CENTER);
					
					l1.setFont(fontEntered2);
					l2.setFont(fontEntered2);
					l3.setFont(fontEntered2);
					l4.setFont(fontEntered2);
					l5.setFont(fontEntered2);
					
					pCentre.add(l1);
					pCentre.add(l2);
					pCentre.add(l3);
					pCentre.add(l4);
					pCentre.add(l5);
					
					centre.updateUI();
					centre.revalidate();
				}
				
				if(b.getLabel().equals("Mme. Mime")) {
					
					pCentre.removeAll();
					
					pCentre.setLayout(new GridLayout(6,1,2,2));
					
					JLabel l1 = new JLabel(b.getLabel(), JLabel.CENTER);
					JLabel l2 = new JLabel("Epreuve collective & individuelle", JLabel.CENTER);
					JLabel l3 = new JLabel("Gain actif : 300 & Gain Passif : 150", JLabel.CENTER);
					JLabel l6 = new JLabel("Gain Indi : 200 & Chrono : 80 s", JLabel.CENTER);
					JTextPane l4 = new JTextPane();
					l4.setEditable(false);
					StyledDocument doc = l4.getStyledDocument();
					SimpleAttributeSet center = new SimpleAttributeSet();
					StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
					doc.setParagraphAttributes(0, doc.getLength(), center, false);
					l4.setText("Epreuve : Mime d’un titre de film. Interdiction de faire un signal sonore. Le MJ stoppe quand l’un des joueurs à trouvé la bonne réponse.");
					JLabel l5 = new JLabel("Petit + : Yeux bandés et musique à fond dans les oreilles. 5 propositions maxi/équipe.", JLabel.CENTER);
					
					l1.setFont(fontEntered2);
					l2.setFont(fontEntered2);
					l3.setFont(fontEntered2);
					l6.setFont(fontEntered2);
					l4.setFont(fontEntered2);
					l5.setFont(fontEntered2);
					
					pCentre.add(l1);
					pCentre.add(l2);
					pCentre.add(l3);
					pCentre.add(l6);
					pCentre.add(l4);
					pCentre.add(l5);
					
					centre.updateUI();
					centre.revalidate();
				}
				
				if(b.getLabel().equals("Le Taboulé")) {
					
					pCentre.removeAll();
					
					pCentre.setLayout(new GridLayout(6,1,2,2));
					
					JLabel l1 = new JLabel(b.getLabel(), JLabel.CENTER);
					JLabel l2 = new JLabel("Epreuve collective & individuelle", JLabel.CENTER);
					JLabel l3 = new JLabel("Gain actif : 300 & Gain Passif : 150", JLabel.CENTER);
					JLabel l6 = new JLabel("Gain Indi : 50/BR & Chrono : 100 s", JLabel.CENTER);
					JTextPane l4 = new JTextPane();
					l4.setEditable(false);
					StyledDocument doc = l4.getStyledDocument();
					SimpleAttributeSet center = new SimpleAttributeSet();
					StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
					doc.setParagraphAttributes(0, doc.getLength(), center, false);
					l4.setText("Epreuve : Taboo. Une erreur fait perdre l'équipe. On peut passer une fois");
					JLabel l5 = new JLabel("Petit + : A chaque mauvaise proposition, le lecteur ingurgite alternativement un verre de lait et une cuillère de taboulé.", JLabel.CENTER);
					
					l1.setFont(fontEntered2);
					l2.setFont(fontEntered2);
					l3.setFont(fontEntered2);
					l6.setFont(fontEntered2);
					l4.setFont(fontEntered2);
					l5.setFont(fontEntered2);
					
					pCentre.add(l1);
					pCentre.add(l2);
					pCentre.add(l3);
					pCentre.add(l6);
					pCentre.add(l4);
					pCentre.add(l5);
					
					centre.updateUI();
					centre.revalidate();
				}
				
				if(b.getLabel().equals("Listen")) {
					
					pCentre.removeAll();
					
					pCentre.setLayout(new GridLayout(6,1,2,2));
					
					JLabel l1 = new JLabel(b.getLabel(), JLabel.CENTER);
					JLabel l2 = new JLabel("Epreuve collective & individuelle", JLabel.CENTER);
					JLabel l3 = new JLabel("Gain actif : 400 & Gain Passif : 200", JLabel.CENTER);
					JLabel l6 = new JLabel("Gain Indi : 40/BR & Chrono : 100 s", JLabel.CENTER);
					JTextPane l4 = new JTextPane();
					l4.setEditable(false);
					StyledDocument doc = l4.getStyledDocument();
					SimpleAttributeSet center = new SimpleAttributeSet();
					StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
					doc.setParagraphAttributes(0, doc.getLength(), center, false);
					l4.setText("Epreuve : Une série de 12 lettres est donnée. Le but est de faire le plus de mot possibles.");
					JLabel l5 = new JLabel("Petit + : Il faut répondre bon à une question du MJ", JLabel.CENTER);
					
					l1.setFont(fontEntered2);
					l2.setFont(fontEntered2);
					l3.setFont(fontEntered2);
					l6.setFont(fontEntered2);
					l4.setFont(fontEntered2);
					l5.setFont(fontEntered2);
					
					pCentre.add(l1);
					pCentre.add(l2);
					pCentre.add(l3);
					pCentre.add(l6);
					pCentre.add(l4);
					pCentre.add(l5);
					
					centre.updateUI();
					centre.revalidate();
				}
				
				if(b.getLabel().equals("Le calembours raciste")) {
					
					pCentre.removeAll();
					
					pCentre.setLayout(new GridLayout(6,1,2,2));
					
					JLabel l1 = new JLabel(b.getLabel(), JLabel.CENTER);
					JLabel l2 = new JLabel("Epreuve collective & individuelle", JLabel.CENTER);
					JLabel l3 = new JLabel("Gain actif : 300 & Gain Passif : 150", JLabel.CENTER);
					JLabel l6 = new JLabel("Gain Indi : LOL & Chrono : 10 s", JLabel.CENTER);
					JTextPane l4 = new JTextPane();
					l4.setEditable(false);
					StyledDocument doc = l4.getStyledDocument();
					SimpleAttributeSet center = new SimpleAttributeSet();
					StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
					doc.setParagraphAttributes(0, doc.getLength(), center, false);
					l4.setText("Epreuve : Le MJ dit une phrase, les Schopers doivent trouver si c'est un proverbe africain ou si c'est une phrase inventée.");
					JLabel l5 = new JLabel("Petit + : Un bonus de +/-100 sera donnée à l'équipe dont le membre a dit la blague la plus raciste lors de la partie.", JLabel.CENTER);
					
					l1.setFont(fontEntered2);
					l2.setFont(fontEntered2);
					l3.setFont(fontEntered2);
					l6.setFont(fontEntered2);
					l4.setFont(fontEntered2);
					l5.setFont(fontEntered2);
					
					pCentre.add(l1);
					pCentre.add(l2);
					pCentre.add(l3);
					pCentre.add(l6);
					pCentre.add(l4);
					pCentre.add(l5);
					
					centre.updateUI();
					centre.revalidate();
				}
				
				if(b.getLabel().equals("Le pendu thématique")) {
					
					pCentre.removeAll();
					
					pCentre.setLayout(new GridLayout(6,1,2,2));
					
					JLabel l1 = new JLabel(b.getLabel(), JLabel.CENTER);
					JLabel l2 = new JLabel("individuelle", JLabel.CENTER);
					JLabel l3 = new JLabel("Gain : 50 à 400 & Chorno : 30s", JLabel.CENTER);
					JTextPane l4 = new JTextPane();
					l4.setEditable(false);
					StyledDocument doc = l4.getStyledDocument();
					SimpleAttributeSet center = new SimpleAttributeSet();
					StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
					doc.setParagraphAttributes(0, doc.getLength(), center, false);
					l4.setText("Petit + : Une lettre est proposée en énoncant un mot qui commencera par cette dernière et qui correspondra au thème du MJ. Par exemple Pays");
					JLabel l5 = new JLabel("Epreuve : Les Schopers font un pendu", JLabel.CENTER);
					
					l1.setFont(fontEntered2);
					l2.setFont(fontEntered2);
					l3.setFont(fontEntered2);
					l4.setFont(fontEntered2);
					l5.setFont(fontEntered2);
					
					pCentre.add(l1);
					pCentre.add(l2);
					pCentre.add(l3);
					pCentre.add(l5);
					pCentre.add(l4);
					
					centre.updateUI();
					centre.revalidate();
				}
				
				if(b.getLabel().equals("Mr. Mime")) {
					
					pCentre.removeAll();
					
					pCentre.setLayout(new GridLayout(6,1,2,2));
					
					JLabel l1 = new JLabel(b.getLabel(), JLabel.CENTER);
					JLabel l2 = new JLabel("individuelle", JLabel.CENTER);
					JLabel l3 = new JLabel("Gain : 40/BR & Chorno : 100", JLabel.CENTER);
					JTextPane l4 = new JTextPane();
					l4.setEditable(false);
					StyledDocument doc = l4.getStyledDocument();
					SimpleAttributeSet center = new SimpleAttributeSet();
					StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
					doc.setParagraphAttributes(0, doc.getLength(), center, false);
					l4.setText("Epreuve : Un des joueurs de l'équipe doit faire deviner le plus de mots possibles. On ne peut pas passer");
					JLabel l5 = new JLabel("Petit + : Le joueur doit obligatoirement utiliser un objet insolite, ou respecter une condition insolite.", JLabel.CENTER);
					
					l1.setFont(fontEntered2);
					l2.setFont(fontEntered2);
					l3.setFont(fontEntered2);
					l4.setFont(fontEntered2);
					l5.setFont(fontEntered2);
					
					pCentre.add(l1);
					pCentre.add(l2);
					pCentre.add(l3);
					pCentre.add(l4);
					pCentre.add(l5);
					
					centre.updateUI();
					centre.revalidate();
				}
			}
		}
		
		if(butPoint == e.getSource()) {
			
			if(!tfEquipe1.getText().isEmpty()) {
				
				String pointE1 = tfEquipe1.getText();
				
				if(!tfEquipe2.getText().isEmpty()) {
					
					String pointE2 = tfEquipe2.getText();
					
					if(! pointE1.contains("-")) {
						
						int point1 = Integer.parseInt(pointE1);
						e1.setPoints(e1.getPoints()+point1);
						
						if(! pointE2.contains("-")) {
							
							int point2 = Integer.parseInt(pointE2);
							e2.setPoints(e2.getPoints()+point2);
						}
						else {
							
							pointE2 = pointE2.substring(1,pointE2.length());
							int point2 = Integer.parseInt(pointE2);
							e2.setPoints(e2.getPoints()-point2);
							
						}
					}
					else {
						
						pointE1 = pointE1.substring(1,pointE1.length());
						int point1 = Integer.parseInt(pointE1);
						e1.setPoints(e1.getPoints()-point1);
						
						if(! pointE2.contains("-")) {
							
							int point2 = Integer.parseInt(pointE2);
							e2.setPoints(e2.getPoints()+point2);
						}
						else {
							
							pointE2 = pointE2.substring(1,pointE2.length());
							int point2 = Integer.parseInt(pointE2);
							e2.setPoints(e2.getPoints()-point2);
						
						}
					}
				}
				else {
					
					if(! pointE1.contains("-")) {
						
						int point1 = Integer.parseInt(pointE1);
						e1.setPoints(e1.getPoints()+point1);
					}
					else {
						
						pointE1 = pointE1.substring(1,pointE1.length());
						int point1 = Integer.parseInt(pointE1);
						e1.setPoints(e1.getPoints()-point1);
						
					}
					
				}
			}
			else {
					
				if(!tfEquipe2.getText().isEmpty()) {
					
					String pointE2 = tfEquipe2.getText();
					
					if(! pointE2.contains("-")) {
						
						int point2 = Integer.parseInt(pointE2);
						e2.setPoints(e2.getPoints()+point2);
					}
					else {
						
						pointE2 = pointE2.substring(1,pointE2.length());
						int point2 = Integer.parseInt(pointE2);
						e2.setPoints(e2.getPoints()-point2);
					
					}
				}
				
			}
			
			tfEquipe1.setText("");
			tfEquipe2.setText("");
			lP1.setText(e1.getNom() + " : " + e1.getPoints());
			lP3.setText(e2.getNom() + " : " + e2.getPoints());
			
			
			
			centre.updateUI();
			centre.revalidate();
			
			/*SwingUtilities.updateComponentTreeUI(vp);*/
		
		}
		
		if (e.getSource() == bCarte) {
			
			if(e1.getPoints() >= 250 || e2.getPoints() >= 250) {
				
				if(alC.size() < 1) {
					
					String chaine="";
					String fichier ="./BDCarte.txt";
					
					alC = new ArrayList<CarteS>();
					
					
					//lecture du fichier texte	
					try{
						InputStream ips=new FileInputStream(fichier); 
						InputStreamReader ipsr=new InputStreamReader(ips);
						BufferedReader br=new BufferedReader(ipsr);
						String ligne;
						while ((ligne=br.readLine())!=null){
							String[] s = ligne.split(":");
							
							System.out.println(s[0]);
							
							alC.add(new CarteS(s[0], s[1], s[2]));
							
							
						}
						br.close(); 
					}		
					catch (Exception ex){
						System.out.println(ex.toString());
					}
				}
				
				new TirageCarte(this, e1, e2, alC);
				
				
			}
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource()==lP1) {
			
			boolean isE1 = true;
			
			new VueCarte(this, e1, e2,isE1);
		}
		
		if(e.getSource()==lP3) {
			
			boolean isE1 = false;
			
			new VueCarte(this, e1, e2,isE1);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
