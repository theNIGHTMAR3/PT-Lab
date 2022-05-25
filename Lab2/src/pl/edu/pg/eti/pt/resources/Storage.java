package pl.edu.pg.eti.pt.resources;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Storage {

    private HashMap <Integer,Boolean> values=new HashMap<>();
    boolean readyToPut=false;

    public synchronized void put(int value,Boolean isPrime) {
        this.values.put(value,isPrime);
        readyToPut=true;
        notifyAll();
    }


    public void PrintMap()
    {
        System.out.println("historia obliczeń:");
        values.entrySet().forEach(entry ->{
            System.out.println(entry.getKey()+ " -> "+entry.getValue());
        });
    }

}
