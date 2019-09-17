public class Main {

    public static void main(String[] args) {
        System.out.println("Hello World!");

        Car c1 = new Car("Car1",1);
        Car c2 = new Car("Car2",2);
        Car c3 = new Car("Car3",3);
        Car c4 = new Car("Car4",4);
        Car c5 = new Car("Car5",5);


        MyNode Node1 = new MyNode(c1,null);
        MyNode Node2 = new MyNode(c2,null);
        MyNode Node3 = new MyNode(c3,null);
        MyNode Node4 = new MyNode(c4,null);
        MyNode Node5 = new MyNode(c5,null);




        MyList List1 = new MyList();

        List1.insert(Node1);
        List1.insert(Node2);
        List1.insert(Node3);
        List1.insert(Node4);
        List1.insert(Node5);

        List1.printList();

        List1.remove(c1);

        List1.printList();


    }
}
