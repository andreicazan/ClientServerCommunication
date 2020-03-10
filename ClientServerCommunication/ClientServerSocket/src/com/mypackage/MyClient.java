package com.mypackage;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class MyClient {
    InputStream in = null;
    OutputStream out = null;
    DataInputStream DataIn = null;
    DataOutputStream DataOut = null;
    Socket clientSocket = null;
    public MyClient() {
        try {
            clientSocket = new Socket("localhost", 8000);
            sendInt(100);
        }
        catch( UnknownHostException e) {
            e.printStackTrace();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }
    public void sendInt(int val) throws IOException {
        out = clientSocket.getOutputStream();
        DataOut = new DataOutputStream(out);
        DataOut.writeInt(val);
    }

    public void receiveInt() throws IOException{
        in = clientSocket.getInputStream();
        DataIn = new DataInputStream(in);
        int val = DataIn.readInt();
        System.out.println(val);
    }
    public static void main(String args[]){
        new MyClient();
    }
}
