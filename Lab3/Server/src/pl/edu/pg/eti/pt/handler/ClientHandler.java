package pl.edu.pg.eti.pt.handler;

import pl.edu.pg.eti.pt.message.Message;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable
{
    final Socket socket;
    final InputStreamReader isr;
    final OutputStreamWriter osw;
    final BufferedReader br;
    final BufferedWriter bw;
    private ObjectInputStream ois;
    private int[] ints;

    final String ready ="ready";
    final String readyForMsg ="ready for messages";
    final String finished ="finished";
    boolean readyToReceive=false;
    private Integer msgFromClient=null;


    public ClientHandler(Socket socket, InputStreamReader isr,OutputStreamWriter osw, BufferedReader br,BufferedWriter bw)
    {
        this.socket=socket;
        this.isr=isr;
        this.osw=osw;
        this.br=br;
        this.bw=bw;
        ObjectInputStream ois=null;
    }

    @Override
    public void run()
    {

        //initial ready after connection
        try
        {
            ois=new ObjectInputStream(socket.getInputStream());
            System.out.println("Client connected");
            bw.write(ready);
            bw.newLine();
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        while(true)
        {
            try
            {
                msgFromClient=br.read();

                //client disconnected
                if(msgFromClient==0 || msgFromClient==null)
                {
                    System.out.println("Client disconnected");
                    break;
                }
                //good input
                else
                {
                    System.out.println("Client: "+msgFromClient);

                    bw.write(readyForMsg);
                    bw.newLine();
                    bw.flush();
                    readyToReceive=true;

                    ints= (int[]) ois.readObject();
                }
                if(readyToReceive)
                {
                    //ois=new ObjectInputStream(socket.getInputStream());
                    for(int i=0;i<msgFromClient;i++)
                    {

                        Message msg=(Message) ois.readObject();
                        System.out.println(msg.getNumber()+" "+msg.getContent());
                    }
                    for(int i=0;i<10;i++)
                        System.out.print(ints[i]+" ");
                    System.out.print("\n");

                    bw.write(finished);
                    bw.newLine();
                    bw.flush();
                }
            } catch (IOException | ClassNotFoundException e)
            {
                System.out.println("exception");
                System.out.println("Client disconnected");
                break;
            }

        }
        try {
            System.out.println("Closing connections");
            socket.close();
            isr.close();
            osw.close();
            br.close();
            bw.close();
            if(this.ois!=null)
                ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
