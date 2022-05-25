package pl.edu.pg.eti.pt;

import pl.edu.pg.eti.pt.number.ComputePrime;
import pl.edu.pg.eti.pt.resources.Resource;
import pl.edu.pg.eti.pt.resources.Storage;

import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {

    public static void EndAllThreads(LinkedList<Thread> threads,int n)
    {
        for(int i=0;i<n;i++)
        {
            threads.get(i).interrupt();
            System.out.println("Wątek "+ i + " został zamknięty");
        }

    }


    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        Resource resource = new Resource();
        Storage storage = new Storage();

        LinkedList <Thread> threads=new LinkedList<>();


        int n= Integer.parseInt(args[0]);


        for(int i=0;i<n;i++)
        {
            threads.add(new Thread(new ComputePrime(resource,storage)));
            threads.get(i).start();
        }


        try {
            while (true) {

                System.out.println("Podaj liczbę do sprawdzenia");
                int number = scanner.nextInt();

                //0 - koniec programu
                if(number==0)
                {
                    System.out.println("Koniec pętli");

                    break;
                }
                else
                {
                    resource.put(number);


                }
            }
        } catch(IllegalStateException | NoSuchElementException e) {
            // System.in has been closed
            System.out.println("System.in was closed; exiting");
            e.printStackTrace();
        }

        resource.PrintList();
        System.out.println();
        storage.PrintMap();
        EndAllThreads(threads,n);
        System.exit(0);
    }
}
