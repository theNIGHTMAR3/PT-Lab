package Person;

import java.util.Comparator;
import java.util.Objects;
import java.util.Set;


public class Employee implements Comparable<Employee>, Comparator<Employee> {
    public  String name = null;
    public  String surname = null;
    public double wage;
    public Set<Employee> subordinates;
    public Employee supervisor=null;

    public Employee(String _name,String _surname,double _wage,Set<Employee> _subordinates){
        name=_name;
        surname=_surname;
        wage=_wage;
        subordinates=_subordinates;
        SetSupervisor();
    }

    public void SetSupervisor()
    {
        if(subordinates!=null)
        {
            for(Employee sub : this.subordinates)
            {
                sub.supervisor=this;
            }
        }
    }
    public void Print()
    {
        String tabs="";
        Employee temp=this;
        while(temp.supervisor!=null) {
            tabs += "-";
            temp=temp.supervisor;

        }
        System.out.println(tabs+this);


        if(this.subordinates!=null)
        {
            for(Employee employee : subordinates)
            {
                employee.Print();
            }
        }
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Employee other = (Employee) obj;
        if (name == null)
            return false;
        else if (!name.equals(other.name))
            return false;

        if (surname == null)
        return false;
        else if (!surname.equals(other.surname))
            return false;

        if (wage != other.wage)
            return false;

        return subordinates == other.subordinates;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name,surname,wage,subordinates);
    }

    @Override
    public String toString() {
        return "Employee{ name= "+this.name+", surname= "+this.surname+", wage= "+this.wage+" }";
    }

    @Override
    public int compareTo(Employee o) {
        int comp=name.compareTo(o.name);
        if(comp==0)
            comp=surname.compareTo(o.surname);

        if(comp==0) {
            return Double.compare(wage, o.wage);
        }

        return Integer.compare(comp, 0);
    }
    @Override
    public int compare(Employee o1, Employee o2) {
       // int comp=name.compareTo(o.name);
        return 0;
    }
}

