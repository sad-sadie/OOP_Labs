package com.my;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        try(Socket socket = new Socket("localhost", 1111);
            Scanner scanner = new Scanner(System.in);
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream())) {
            System.out.println("Enter file name: ");
            String name = scanner.next();
            System.out.println("Enter file size: ");
            int size = scanner.nextInt();
            File file = new File(name, size);
            out.writeObject(file);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
