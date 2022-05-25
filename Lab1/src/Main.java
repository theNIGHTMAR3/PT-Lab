import Person.Employee;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        Employee employee4=new Employee("Michał","Aaa",1000,null);
        Employee employee5=new Employee("Ania","Bbbb",1000,null);
        Employee employee6=new Employee("Łukasz","Cccc",1000,null);
        Employee employee7=new Employee("Jan","Dddd",1000,null);

        Employee employee8=new Employee("Paweł","Dddd",1000,null);
        Employee employee9=new Employee("Dawid","Eeee",1000,null);
        Employee employee10=new Employee("Kacper","Ffff",1000,null);

        Set <Employee> below1=new TreeSet<>();
        Set <Employee> below2=new TreeSet<>();
        below1.add(employee4);
        below1.add(employee5);
        below1.add(employee6);
        below1.add(employee7);

        below2.add(employee8);
        below2.add(employee9);
        below2.add(employee10);

        Employee employee2=new Employee("Jan","Nowak",1200,below1);
        Employee employee3=new Employee("Bartek","Kowalski",1200,below2);

        Set <Employee> below3=new TreeSet<>();
        below3.add(employee2);
        below3.add(employee3);
        Employee employee1=new Employee("Michał","Kuprianowicz",1337,below3);


        //System.out.println(employee2.subordinates);
        //System.out.println(employee3.compareTo(employee4));
        //System.out.println(employee2.supervisor);

        employee1.Print();


    }
}
