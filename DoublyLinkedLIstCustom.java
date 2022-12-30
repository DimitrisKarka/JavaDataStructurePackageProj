package DataStructurePackage;
/*Doubly Linked List implenetation with nodes.
functions:  insert: inerts a node to the end and makes it the new tail (O(1))
            insertAtStart: inserts a node to the start and makes it the new head (O(1))
            insertAt: inserts a node at certain position and reassignes the head or tail if needed
                      Writes a message if the position is longer than the length (O(n))
            delete: deletes and returns the tail and makes the previus node as the tail (O(1)) 
            deleteAt: deletes a node at certain position and reassignes the head or tail if needed
                      throws a runTimeError if the position is longer than the length (O(n))
            empty:  empties the list (O(n))
            show: shows the list (O(n))
            showRevOrder: shows the list in reverse order (O(n))
            Head: returns the head. throws a runTimeError if the list is empty (O(1))
            tail: returns the tail. Throws a runTimeError if the list is empty (O(1))
            nodeAt: returns a node at a certain position. Throws a runTimeError if the position is longer than the length (O(n))
            length: returns the length (O(1))
*/
public class DoublyLinkedLIstCustom <T> {

    private class Node{
        private T data;
        private Node next;
        private Node prev;
    }

    private Node head;
    private Node tail;
    private int length = -1;

    public void insert(T data){
        Node node = new Node();
        node.data = data;
        node.next = null;
        node.prev = null;
        if(head == null){
            head = node;
            tail = node;
            length++;
        }
        else{
            tail.next = node;
            node.prev = tail;
            tail = node;
            length++;
        }
    }   //O(1)

    public void insertAtStart(T data){
        Node node = new Node();
        node.data = data;
        node.next = head;
        head.prev = node;
        head = node;
        length++;
    }   //O(1)

    public void insertAt(int pos, T data){
        Node insertionNode = new Node();
        insertionNode.data = data;
        insertionNode.next = null;
        insertionNode.prev = null;
        if(pos > length + 1){
            System.out.println("The list is not that long. Current lenght = " + length + ".("+ (length + 1) +" nodes)");
        }
        else{
            if(pos == 0){
                insertAtStart(data);
            }
            else{
                Node posPlusOne = head;
                Node posMinusOne;
                for(int i = 0; i < pos; i ++){
                    posPlusOne = posPlusOne.next;
                }
                if(pos == length + 1){
                    tail.next = insertionNode;
                    insertionNode.prev = tail;
                    tail = insertionNode;
                }
                else{
                    posMinusOne = posPlusOne.prev;
                    posMinusOne.next = insertionNode;
                    posPlusOne.prev = insertionNode;
                    insertionNode.prev = posMinusOne;
                    insertionNode.next = posPlusOne;
                }
                length++;
            }
        }
    }   //O(n)

    public T delete(){
        if(length == -1){
           throw new RuntimeException("List is empty");
        }
        else{
            if(length == 0){
                T temp = head.data;
                head = null;
                tail = null;
                length--;
                return temp;
            }
            else{
                T temp;
                Node tempNode;
                temp = tail.data;
                tempNode = tail;
                tail = tempNode.prev;
                tempNode = null;
                tail.next = null;
                length--;
                return temp;
            }
        }   
    }   //O(1)

    public T deleteAt(int pos){
        if(length == -1){
            throw new RuntimeException("List is empty");
        }
        else if(pos > length){
            throw new RuntimeException("The list is not that long. Current lenght = " + length + ".("+ (length + 1) +" nodes)");
        }
        else{
            Node deletionNode = head;
            Node posMinusOne;
            Node posPlusOne;
            T temp;
            for(int i = 0; i < pos; i ++){
                deletionNode = deletionNode.next;
            }
            if(length == 0){
                temp = tail.data;
                head = null;
                tail = null;
                length--;
                return temp;
            }
            else if(pos == length){
                temp = tail.data;
                posMinusOne = tail.prev;
                tail = null;
                tail = posMinusOne;
                tail.next = null;
                length--;
                return temp;
            }
            else{
                if(pos != 0){
                    temp = deletionNode.data;
                    posMinusOne = deletionNode.prev;
                    posPlusOne = deletionNode.next;
                    posMinusOne.next = posPlusOne;
                    posPlusOne.prev = posMinusOne;
                    deletionNode = null;
                    length--;
                    return temp;
                }
                else{
                    temp = head.data;
                    posPlusOne = head.next;
                    head = null;
                    head = posPlusOne;
                    head.prev = null;
                    posMinusOne = null;
                    length--;
                    return temp;
                }
            }
        }        
    }   //O(n)
    
    public void empty(){
        Node n = head;
        for(int i = 0; i < length; i++){
            n = head.next;
            head = null;
            head = n;
        } 
        head = null;
        tail = null;
        length = -1;
    }   //O(n);

    public void show(){
        Node n = head;
        if(length != -1){
            for(int i = 0; i < length; i ++){
                System.out.println(n.data);
                n = n.next;
            }
            System.out.println(n.data);
        }
        else System.out.println("empty");
    }   //O(n)

    public void showRevOrder(){
        Node n = tail;
        if(length != -1){
            for(int i = 0; i < length; i ++){
                System.out.println(n.data);
                n = n.prev;
            }
            System.out.println(n.data);
        }
        else System.out.println("empty");
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
