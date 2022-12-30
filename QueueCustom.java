package DataStructurePackage;
/*Queue implementation with nodes.
functions:  enqueue: pushes an element to the end of the queue and assigns it as the new tail (O(1))
            dequeue: removes and returns the head and assigns the next element as head, or throws an
                     exceptions if it is empty (O(1))
            peek: prints the head or writes a message if it is empty (O(1))
            isEmpty: checks if the queue is empty or not and returns true or false (O(1)) 
            Contains: checks if an element exists in the queue and returns true or false (O(n))
            show: prints the queue or a message if it is empty (O(n)) 
*/

public class QueueCustom<T> {

    private class Node{
        private T data;
        private Node prev;
    }

    private Node head;
    private Node tail;
    private int length = -1;

    public void enqueue(T data){
        Node n = new Node();
        n.data = data;
        if(head == null){
            head = n;
            tail = n;
            length++;
        }
        else{
            tail.prev = n;
            tail = n;
            length++;
        }
    } //O(1)

    public T dequeue(){
        if(head == null){
            throw new RuntimeException("Queue is empty");
        }
        else{
            Node temp = head;
            head = null;
            head = temp.prev;
            length--;
            return temp.data;
        }
    }   //O(1)

    public void peek(){
        if(isEmpty()){
            System.out.println("Queue is empty");
        }
        else{
            System.out.println(head.data);
        }
    }   //O(1)

    public boolean isEmpty(){
        if(length == -1){
            return true;
        }
        else return false;
    }   //O(1)

    public boolean contains(T data){
        if(length == -1){
            return false;
        }
        else{
            Node trav = head;
            int i = 0;
            while(trav.data != data && i <= length){
                i++;
                if(i <= length){
                    trav = trav.prev;
                }
            }
            if(i <= length){
                return true;
            }
            else return false;
            
        }
    }   //O(n)

    public void show(){
        if(length == -1){
            System.out.println("Queue is empty");
        }
        else{
            Node trav = head;
            for(int i = -1; i < length; i++){
                System.out.println(trav.data);
                trav = trav.prev;
            }
        }
    }   //O(n)

}
