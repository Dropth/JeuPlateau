import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class Test {

	public static void main(String[] args) {
		
		/*String s = "-50";
		
		String lol = s.substring(1, s.length());
				
				System.out.println(lol);
		
		if(s.contains("-"))
			System.out.println("IS OK MAGGLE");
		else
			System.out.println("MERDE");*/
		
		// TODO Auto-generated method stub
		
		String chaine="";
		String fichier ="./BDCarte.txt";
		
		Equipe e1 = new Equipe("Lol", 0, new ArrayList<CarteS>());
		
		ArrayList<CarteS> al = new ArrayList<CarteS>();
		
		
		//lecture du fichier texte	
		try{
			InputStream ips=new FileInputStream(fichier); 
			InputStreamReader ipsr=new InputStreamReader(ips);
			BufferedReader br=new BufferedReader(ipsr);
			String ligne;
			while ((ligne=br.readLine())!=null){
				String[] s = ligne.split(":");
				
				System.out.println(s[0]);
				
				al.add(new CarteS(s[0], s[1], s[2]));
				
				
			}
			br.close(); 
		}		
		catch (Exception e){
			System.out.println(e.toString());
		}
		
		
		System.out.println(al.size());
		
		int max = al.size()-1;
		int min = 0;
		
		int random = 0;//(int) (Math.random() * ( max - min ));
		
		//System.out.println(random);
		
		//System.out.println(al.get(random));

	}

}
