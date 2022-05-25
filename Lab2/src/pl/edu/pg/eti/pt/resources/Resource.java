package pl.edu.pg.eti.pt.resources;

import java.util.Arrays;
import java.util.LinkedList;

public class Resource {

    private LinkedList<Integer> values=new LinkedList<>();
    private int index=0;


    public synchronized Integer take() throws InterruptedException {
        while (values.size()==index) {
            wait();//Wait, maybe someone will put sth here.
        }
        index++;
        int ret = values.get(index-1);
        return ret;
    }
    public synchronized void put(int value) {
        this.values.add(value);

        notifyAll();
    }

    //prints number history
    public void PrintList(){
        System.out.println("Podane liczby: ");
        System.out.println(Arrays.toString(values.toArray()));
    }

}
