/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itshutup;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import javax.swing.JOptionPane;

/**
 *
 * @author E1002498
 */
public class ItShutUp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        String serverAddr = "192.168.81.70";
        int serverPort = 9999;
        Socket socket;
        DataOutputStream dataToServer = null;
        DataInputStream dataFromServer = null;
        String msFromServer = null;
        
        System.out.println("Opeinng the socket to: 192.168.81.251:9999");
        try {
            socket = new Socket(serverAddr,serverPort); //Creating socket for the conection
            dataToServer = new DataOutputStream(socket.getOutputStream()); //Creating the object to send the data
            dataToServer.writeUTF("Hola Que tal");
            
            System.out.print("Message from de server: ");
            dataFromServer = new DataInputStream(socket.getInputStream()); //Creating data to be sent to socket
            msFromServer = dataFromServer.readUTF();
            System.out.println(msFromServer);   
                    
            socket.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
    
}
