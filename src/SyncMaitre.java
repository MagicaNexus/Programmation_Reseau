import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;

//Client de base pris sur OpenClassRoom

public class SyncMaitre {
	
	public static void main(String[] args) {
		
		/*int svrNomPort = Integer.parseInt(args[2]);
		String repSrc = args[3], repRacine = args[4];*/
		Socket socket;
		File contenu = new File ("Maitre");
		File file = new File("Maitre\\ATransferer.txt");
		String doc = "ATransferer.txt";
		try {
			System.out.println("Je suis le Maitre et je viens de me connecter");
		    socket = new Socket(InetAddress.getLocalHost(),8082/*svrNomPort*/);	
		    System.out.println("Choix du mode de transfert de fichier :\n"
		    		+ "1 : mode ecrasement fichier existant deja dans "
		    		+ "le repertoire destination est ecrase par le fichier du repertoire source.\n"
		    		+ "2 : mode suppression ou un fichier existant dans le repertoire destination "
		    		+ "mais pas dans le repertoire source est supprime dans le repertoire destination.\n"
		    		+ "3 : mode watchdog ou un fichier existant deja dans le repertoire destination"
		    		+ " est ecrase uniquement par une version plus recente du fichier du repertoire source.\n");
			System.out.println("Est un repertoire : " + contenu.isDirectory());
			System.out.println("Qui contient :");
	        afficheDocument(contenu.list(),contenu.getName());
	        /*transfertDocument(contenu.list() ,contenu.getName(), socket);*/
	        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
	       
	        out.writeUTF(doc);
	        Transfer.transfert(new FileInputStream(file), out, true);
		    socket.close();

		}catch (UnknownHostException e) {
			
			e.printStackTrace();
		}catch (IOException e) {
			
			e.printStackTrace();
		}
	}

	public static void transfertDocument(String[] paths ,String parent, ObjectOutputStream out) throws IOException
	{
		for(String path:paths) {
			File f = new File (parent+"\\"+path);
			if (f.isDirectory())
			{
				parent += "\\" + f.getName();
				transfertDocument(f.list(),f.getName(), out);
			}
			out.writeUTF(path); 
			Metadonnee m = new Metadonnee (f.getName(),f.getCanonicalPath(),f.length(),f.lastModified());
			out.writeObject(m); 
			Transfer.transfert(new FileInputStream(f), out, true);
         }
	}
	public static void afficheDocument(String[] paths ,String parent) throws IOException
	{
		for(String path:paths) {
			File f = new File (parent+"\\"+path);
			if (f.isDirectory())
			{
				parent += "\\" + f.getName();
				afficheDocument(f.list(),parent);
			}
            afficheMetaDonnee(f);
         }
	}
	
	public static void afficheMetaDonnee(File f) throws IOException
	{
		 Metadonnee m = new Metadonnee (f.getName(),f.getCanonicalPath(),f.length(),f.lastModified());
		 System.out.println(m.toString());
	}
	/*public static Metadonnee transfertMetaDonnee(File f) throws IOException
	{
		 Metadonnee m = new Metadonnee (f.getName(),f.getCanonicalPath(),f.length(),f.lastModified());
		 System.out.println(m.toString());
		 return m;
		 
	}*/
}
