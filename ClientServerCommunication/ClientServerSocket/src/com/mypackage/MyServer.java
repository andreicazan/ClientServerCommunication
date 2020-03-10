package com.mypackage;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer implements Runnable{
    private Socket socketReturn;
    InputStream in = null;
    OutputStream out = null;
    DataInputStream DataIn = null;
    DataOutputStream DataOut = null;
    public MyServer(Socket s) throws IOException {
        socketReturn = s;
        receiveInt(); // Clientul incepe, deci serverul asteapta valoarea
    }
    public MyServer() {
        Thread thread = new Thread(this);
        thread.start();
    }
    public void sendInt(int val) throws IOException{
        out = socketReturn.getOutputStream();
        DataOut = new DataOutputStream(out);
        DataOut.writeInt(val);
    }
    public void receiveInt() throws IOException{
        in = socketReturn.getInputStream();
        DataIn = new DataInputStream(in);
        int val = DataIn.readInt();
        System.out.println(val);
    }
    public void run(){
        ServerSocket s = null;
        try {
            s = new ServerSocket(8000);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            while(true){
                Socket socket = s.accept();
                try {
                    new MyServer(socket);
                }
                catch(IOException e) {
                    socket.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                s.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String args[]){
        new MyServer();
    }
}