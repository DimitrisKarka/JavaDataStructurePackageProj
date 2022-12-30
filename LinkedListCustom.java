package DataStructurePackage;
/*Singly Linked List implementation with nodes.
functions:  insert: inserts a node to the end and makes it the new tail (O(1))
            insertAtStart: inserts a node to the start and makes it the new head (O(1))
            insertAt: inserts a node at certain position and reassignes the head or tail if needed
                      Writes a message if the position is longer than the length (O(n))
            delete: deletes and returns the tail and makes the previus node as the tail (O(1))  
            deleteAt: deletes a node at certain position and reassignes the head or tail if needed
                      throws a runTimeError if the position is longer than the length (O(n))
            empty: empties the list (O(n))
            show: shows the list (O(n))
            Head: returns the head. throws a runTimeError if the list is empty (O(1))
            tail: returns the tail. Throws a runTimeError if the list is empty (O(1))
            nodeAt: returns a node at a certain position. Throws a runTimeError if the position is longer than the length (O(n))
            length: returns the length (O(1))
 */                     

public class LinkedListCustom<T>{

    private class Node{
        private T  data;
        private Node next;
    }

    private Node head;
    private Node tail;
    private int length = -1;

    public void insert(T data){
        Node node = new Node();
        node.data = data;
        node.next = null;
        if(head == null){
            head = node;
            tail = node;
        }
        else{
            tail.next = node;
            tail = node;           
        }
        length++;
    }   //O(1)

    public void insertAtStart(T data){
        Node node = new Node();
        node.data = data;
        node.next = head;
        head = node;
        length++;
    }   //O(1)

    public void insertAt(int pos, T data){
        Node node = new Node();
        node.data = data;
        node.next = null;
        if(pos == 0){
            insertAtStart(data);
        }
        else{
            if(pos > length + 1){
                System.out.println("The list is not that long. Current lenght = " + length + ".("+ (length + 1) +" nodes)");
            }
            else{
                Node n = head;
                for(int i = 0; i < pos - 1; i ++){
                    n = n.next;
                }
                node.next = n.next;
                n.next = node;
                if(length == pos){
                    tail = node;
                }
                length++;
            }
        }
    }   //O(n)

    public T delete(){
        if(head == null){
            throw new RuntimeException("List is empty");
        }
        else{
            T temp = null;
            if(length == 0){
                temp = head.data;
                head = null;
                tail = null;
                length--;
                return temp;
            }
            else{
                Node beforeTail = head;
                for(int i = 0; i < length - 1; i++){
                    beforeTail = beforeTail.next;
                }
                temp = tail.data;
                tail = null;
                tail = beforeTail;
                beforeTail = null;
                tail.next = null;
                length--;
                return temp;  
            }  
        }
    }   //O(1)

    public T deleteAt(int pos){
        T temp;
        if(pos > length){
            throw new RuntimeException("The list is not that long. Current lenght = " + length + ".("+ (length + 1) +" nodes)");
        }
        else if(pos == 0){
            Node n = head;
            temp = head.data;
            head = n.next;
            n = null;
            length--;
            if(length == -1){
                tail = null;
            }
            return temp;
        }
        else{
            Node nodeDelMinusOne = head;
            Node nodeDelPlusOne = head;
            Node del = null;
            for(int i = 0; i < pos  + 1; i ++){
                if(i < pos - 1){
                    nodeDelMinusOne = nodeDelMinusOne.next;
                }
                nodeDelPlusOne = nodeDelPlusOne.next;
            }
            del = nodeDelMinusOne.next;
            temp = del.data;
            nodeDelMinusOne.next = nodeDelPlusOne;
            del = null;
            nodeDelPlusOne = null;
            length--;
            if(pos > length){
                tail = nodeDelMinusOne;
            }
            return temp;
        }
    }   //O(n)

    public void empty(){
        int loopsRequired = length + 1;
        for(int i = 0; i < loopsRequired; i++){
            delete();
        }
        head = null;
        tail = null;
    }   //O(n)

    public void show(){
        if(head == null){
            System.out.println("List is empty");
        }
        else{
            Node n = head;
            for(int i = 0; i < length + 1; i++){
                System.out.println(n.data);
                n = n.next;
            }
        }
    }   //O(n)

    public T Head(){
        if(head != null){
            return head.data;
        }
        else{
            throw new RuntimeException("head is null");
        }
    }   //O(1)

    public T Tail(){
        if(tail != null){
            return tail.data;
        }
        else{
            throw new RuntimeException("head is null");
        }
    }   //O(1)

    public T nodeAt(int pos){
        Node n = head;
        if(pos > length){
            throw new RuntimeException("The list is not that long. Current lenght = " + length + ".("+ (length + 1) +" nodes)");
        }
        else if(pos == 0){
            return head.data;
        }
        else{
            n = head;
            for(int i = 0; i < pos; i ++){
                n = n.next;
            }
            return n.data;
        }
    }   //O(n)

    public int length(){
        return length;
    }   //O(1)
}   