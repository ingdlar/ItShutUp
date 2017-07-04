/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author E1002498
 */
public class SocketClient {

    String servAddr;
    int servPort;
    Socket socket;
    DataOutputStream sendData;
    DataInputStream getData;
    String msFromServer;

    ObjectOutputStream sendData2;
    ObjectInputStream getData2;

    /**
     *
     */
    public SocketClient() {
        //this.servAddr = "192.168.81.70";
        servAddr = "192.168.38.8";
        servPort = 11222;
    }

    public SocketClient(String serAddr, int servPort) {
        this.servAddr = serAddr;
        this.servPort = servPort;
    }

    public void sendDataObj(Object msToServer) throws ConnectException, IOException {
        //Creating socket for the conection
        socket = new Socket(servAddr, servPort);
        if (this.socket.isConnected()) {
            sendData2 = new ObjectOutputStream(socket.getOutputStream()); //Creating the object to send the data
            sendData2.writeObject(msToServer);
        } else {
            System.out.println("Socket not conected");
        }

    }

    public void sendData(String msToServer) {
        try {
            //Creating socket for the conection
            socket = new Socket(servAddr, servPort);
            sendData = new DataOutputStream(socket.getOutputStream()); //Creating the object to send the data
            sendData.writeUTF(msToServer);
        } catch (IOException ex) {
            Logger.getLogger(SocketClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void closeConnection() {
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(SocketClient.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getResponse() {
        try {
            getData = new DataInputStream(socket.getInputStream());
            msFromServer = getData.readUTF();
        } catch (IOException ex) {
            Logger.getLogger(SocketClient.class.getName()).log(Level.SEVERE, null, ex);
        }
        return msFromServer;
    }
}
