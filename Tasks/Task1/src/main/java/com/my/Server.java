package com.my;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        File file;
        try	(ServerSocket server = new ServerSocket(1111)){
            Socket client = server.accept();
            ObjectInputStream in = new ObjectInputStream(client.getInputStream());
            while(!client.isClosed()) {
                file = (File) in.readObject();
                System.out.println("Got: " + file);
            }
            in.close();
            client.close();
        } catch (ClassNotFoundException | IOException e) {
            System.exit(0);
        }
    }
}