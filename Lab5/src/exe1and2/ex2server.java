package exe1and2;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class ex2server {

	public static void main(String[] args) {
		
		
		
		try {
			// Port to receive and respond to request
			int portNo=0666;
			ServerSocket serverSocket = new ServerSocket(portNo);
			
			System.out.println(" Ready for request");
			
			int itemProductId=0, totalRequest =0;
			
			// Server need to be alive forever thus the while(true)
			while (true)
			{
				
				// Accept client request for connection
				Socket socket = serverSocket.accept();
				
				// Create input stream to read object
				ObjectInputStream objectIS = new ObjectInputStream(socket.getInputStream());
				
				// Read object from stream and cast to ItemProduct
				itemProduct item = (itemProduct) objectIS.readObject();
				
				
				// To validate product name
				itemNameValidation validation = new itemNameValidation();
				
				String result = validation.validateItemName(item);
				
				
				// Process object
				item.setItemProductId(++itemProductId);
				
				// Create output stream to send object
				ObjectOutputStream objectOS = new ObjectOutputStream(socket.getOutputStream());
				
				objectOS.writeObject(item);
				
				
				// Send validation result
				objectOS.writeObject(result);
				objectOS.flush();
				
				
				System.out.println(" Total request : " + ++totalRequest);
				System.out.println(" Validation Result : " + result);
				System.out.println("\n Ready for next request");
				
				// Close all streams
				objectIS.close();
				objectOS.close();		
							
			}
		
		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		

	}

}
