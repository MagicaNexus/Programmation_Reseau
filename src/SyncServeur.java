import java.io.IOException;
import java.net.ServerSocket;

public class SyncServeur {

	//Connexion du serveur
	public static void main(String[] args) {
			
			int srvport = Integer.parseInt(args[2]);
			String repRacine = args[3]; // r�cuperation des parametres*/
			ServerSocket socket;
			System.out.println("En attente de reception............."); 
			try  
			{
				socket = new ServerSocket(srvport/*srvport*/);
				Thread t = new Thread(new Accepterclients(socket, repRacine));
				t.start(); 
				System.out.println("Le serveur est pr�t pour la connexion");	
			} catch (IOException e) {
				e.printStackTrace();  
			}
		}
	}

