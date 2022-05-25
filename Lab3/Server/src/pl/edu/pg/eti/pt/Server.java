package pl.edu.pg.eti.pt;

import pl.edu.pg.eti.pt.handler.ClientHandler;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {

    public static void main(String[] args) throws IOException {

        Socket socket=null;
        InputStreamReader inputStreamReader=null;
        OutputStreamWriter outputStreamWriter=null;
        BufferedReader br=null;
        BufferedWriter bw=null;

        ObjectInputStream ois=null;

        System.out.println("Server started");

        ServerSocket serverSocket=new ServerSocket(1337);

        while(true)
        {
            try {
                socket = serverSocket.accept();

                inputStreamReader = new InputStreamReader(socket.getInputStream());
                outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());

                br = new BufferedReader(inputStreamReader);
                bw = new BufferedWriter(outputStreamWriter);

                //handle new client
                Thread newClient=new Thread(new ClientHandler(socket, inputStreamReader,outputStreamWriter,br,bw));
                newClient.start();


            } catch (IOException e) {
                System.out.println(e);
                }

        }
    }
}

