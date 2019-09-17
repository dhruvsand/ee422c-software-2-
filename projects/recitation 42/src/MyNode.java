public class MyNode<T> {

    private T value;
    private MyNode next;

    public  MyNode(T v, MyNode nex){

        value =v;
        next =nex;


    }


    public  void setNext(MyNode myNode){

        this.next =  myNode;


    }

    public  MyNode getNext(){

        return next;


    }
    public  T getValue(){

        return value;


    }
    public  void setValue(T val){

         value=val;


    }

    @Override
    public String toString() {
        return value.toString();
    }


}
