package exe1and2;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;


public class ex2client {

	public static void main(String[] args) {
		
		System.out.println("ClientSideApp: Demo of Object Stream\n");

		// Request data
		itemProduct item = new itemProduct();
		item.setName("BIT COIN");
		item.setPrice(259872);
		
		
		try {
			
			// Data to establish connection to server
			int portNo = 0666;
			InetAddress serverAddress = InetAddress.getLocalHost();
			
			// Connect to the server at localhost, port 4228
			Socket socket = new Socket(serverAddress, portNo);
			
			
			// Open stream to send object
			ObjectOutputStream objectOS = new ObjectOutputStream(socket.getOutputStream());
			
			
			// Send request to server
			System.out.println(" Send object to server: " + item.toString());
			objectOS.writeObject(item);
			objectOS.flush();
			
			// Open stream to receive object
			ObjectInputStream objectIS = new ObjectInputStream(socket.getInputStream());
			
			// Get object from stream and display details
			item = (itemProduct) objectIS.readObject();
			
			String result = (String)objectIS.readObject();
			
			System.out.println("\n Validation Result : "+ result+ "\n");
			System.out.print (" Product Id : " + item.getItemProductId() + "\n Product Name : " + item.getName() );
			System.out.print("\n Product Price : RM "+ item.getPrice()+"\n");
			
			
			// Close all closable objects
			objectOS.close();
			objectIS.close();
			socket.close();
			
		
		} catch (IOException | ClassNotFoundException e) {
			
			e.printStackTrace();
		}
		
		

	}

}
