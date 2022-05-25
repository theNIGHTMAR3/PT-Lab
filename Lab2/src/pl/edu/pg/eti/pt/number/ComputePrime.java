package pl.edu.pg.eti.pt.number;

import pl.edu.pg.eti.pt.resources.Resource;
import pl.edu.pg.eti.pt.resources.Storage;

public class ComputePrime implements Runnable{
    private final Resource resource;
    private Storage storage;
    private int number;

    //constructor
    public ComputePrime(Resource resource, Storage storage)
    {
        this.resource=resource;
        this.storage=storage;
    }

    //sprawdza czy liczba jest podzielna przez daną
    public boolean IsDividor(int a) {
        return this.number % a == 0;
    }

    @Override
    public void run() {
        while(true) {

            try {
                this.number = resource.take();
                System.out.println("Sprawdzanie czy liczba " + this.number + " jest pierwsza");
                //pętla szukająca dzielnika
                for (int i = 2; i < this.number / 2; i++) {

                    System.out.println("sprawdzam czy jest podzielna przez " + i);

                    //jest podzielna
                    if (IsDividor(i)) {
                        System.out.println("Liczba " + this.number + " nie jest pierwsza, ponieważ jest podzielna przez " + i);
                        storage.put(this.number, false);
                        return;
                    }
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException ex) {
                        System.out.println("Wyrzucono wyjątek");
                    }
                }
                //jest pierwsza
                System.out.println("Liczba " + this.number + " jest pierwsza");
                storage.put(this.number, true);



            } catch (InterruptedException e) {
                //e.printStackTrace();
                //System.out.println("wątek zakończony poprawnie");
            }


        }
    }
}
