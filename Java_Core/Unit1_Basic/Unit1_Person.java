/*
Coder:NNKhai
Date:05/09/2022
JSE.Unit01.Basic.1
*/
public class Unit1_Person {

    private String name;

    public Unit1_Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class PersonTest {

    public static void main(String[] args) {
        Unit1_Person person1 = new Unit1_Person(args[0]);
        Unit1_Person person2 = new Unit1_Person(args[1]);
        System.out.println("args" +args.toString());
        System.out.println("Person 1's name is " + person1.getName());
        System.out.println("Person 2's name is " + person2.getName());

    }
}
