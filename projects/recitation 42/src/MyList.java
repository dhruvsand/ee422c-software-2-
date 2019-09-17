
public class MyList<T> {

    private MyNode head;

    public MyList(){

        head= null;

    }

    public void insert(MyNode node){
        MyNode cur =null;
        MyNode prev =head;

        if(prev ==null)
            head = node;
        else {
            while (true) {
                cur = prev.getNext();


                if (cur == null) {
                    prev.setNext(node);
                    return;
                }

                prev = cur;
            }



        }
        return;


    }
    public void remove (T value){
        MyNode cur =null;
        MyNode prev =head;

        if(prev ==null)
            return;
        else {

            if(prev.getValue()==value)
                head = prev.getNext();

            while (true) {
                cur = prev.getNext();


                if (cur == null) {
                    return;
                }
                if(cur.getValue()==value){
                    prev.setNext(cur.getNext());
                    return;
                }

                prev = cur;
            }



        }



    }

    public void printList(){
        String output="";
        MyNode cur=head;

        while (cur!=null){
            output+=" "+cur.toString();

            cur= cur.getNext();
        }

        System.out.println(output);





    }
}
