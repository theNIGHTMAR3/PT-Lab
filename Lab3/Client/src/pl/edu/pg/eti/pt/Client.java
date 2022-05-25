package pl.edu.pg.eti.pt;

import pl.edu.pg.eti.pt.message.Message;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.Random;

public class Client {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Integer inputNumber=null;

        InputStreamReader inputStreamReader=null;
        OutputStreamWriter outputStreamWriter=null;

        BufferedReader br=null;
        BufferedWriter bw=null;

        ObjectOutputStream oos=null;

        Socket socket=null;

        try
        {

            socket=new Socket("localhost", 1337);
            inputStreamReader=new InputStreamReader(socket.getInputStream());
            outputStreamWriter=new OutputStreamWriter(socket.getOutputStream());
            oos=new ObjectOutputStream(socket.getOutputStream());



            br=new BufferedReader(inputStreamReader);
            bw=new BufferedWriter(outputStreamWriter);


            while(true)
            {
                //read answer from server
                String answer = br.readLine();

                //if answer received
                if(answer!=null)
                {
                    System.out.println("Server: "+answer);

                    //waiting for input
                    if(answer.equals("ready"))
                    {
                        System.out.println("Podaj ilość elementów do przesłania");
                        inputNumber=scanner.nextInt();
                        if(inputNumber<2 && inputNumber!=0)
                        {
                            System.out.println("Wrong input");
                            break;
                        }
                        bw.write(inputNumber);
                        bw.flush();

                        Random rand = new Random();
                        int[] array =new int[10];

                        for(int i=0;i<10;i++)
                            array[i]=rand.nextInt(100);

                        oos.writeObject(array);
                        oos.flush();

                        answer =br.readLine();
                        System.out.println("Server: "+answer);
                    }

                    //disconnect
                    if (inputNumber==0)
                    {
                        System.out.println("Disconnected from the server");
                        break;
                    }
                    //ready for messages
                    if(answer.equals("ready for messages"))
                    {
                        //oos=new ObjectOutputStream(socket.getOutputStream());
                        for(int i=1;i<=inputNumber;i++)
                        {
                            Message msg=new Message(1000+i,"test msg");
                            oos.writeObject(msg);
                        }
                    }

                    if(answer.equals("finished"))
                    {
                        bw.write(0);
                        bw.flush();
                        break;
                    }

                }

            }

        } catch (IOException e) {
            System.out.println(e);
        }
        //closing streams
        finally {
            try{
                if(socket!=null)
                    socket.close();
                if(inputStreamReader!=null)
                    inputStreamReader.close();
                if(outputStreamWriter!=null)
                    outputStreamWriter.close();
                if(oos!=null)
                    oos.close();
                if(br!=null)
                    br.close();
                if(bw!=null)
                    bw.close();


            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}
