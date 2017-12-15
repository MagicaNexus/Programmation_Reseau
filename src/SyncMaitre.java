import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

//Client de base pris sur OpenClassRoom

public class SyncMaitre {
	
	public static void main(String[] zero) {
		
		Socket socket;

		try {
			System.out.println("Je suis le Maitre");
		    socket = new Socket(InetAddress.getLocalHost(),2008);	
	        socket.close();

		}catch (UnknownHostException e) {
			
			e.printStackTrace();
		}catch (IOException e) {
			
			e.printStackTrace();
		}
	}

}