package DataStructurePackage;
import java.util.*;
/*Priority Queue Implementation with max binaryheap implemented with an ArrayList.
functions:  push: pushes an element to the last position and bubbles it up if needed O(logn)
            poll: removes the top element by swapping it with the last and then uses  bubbling down 
            to satisfy the heap invariant   O(logn)
            removeElement: Searches for a certain element and if it exists it gets swapped with the last,
            then gets removed and the last element which is put in the original position of the removed 
            element gets bubbled up or down in order for the heap invariant to be satisfied. Else it 
            throws a RuntimeException.   O(n)
            contains: searches for a certain element to see if it exists in the queue. Returns true if 
            it exists else returns false. O(n)
            showLVlOrder: shows the whole queue in LvlvOrder    O(n)
            peek:returns the top element without removing it. Returns null if it is empty and writes
            a message   O(1)
            heightOfHeap: returns the height of heap(in levels) O(1)

****in order for the max heap to become a min heap you have to go to bubbleUp and bubbleDown
    and do the changes that are after the three asterisks(***). 

    some mathematical types used.(zero based)
    left child index = 2i +1
    right child index = 2i + 2
    left child parent index = idiv2
    right child parent index = idiv2 - 1
    height of heap: Math.log(size + 1)/(Math.log(2)) + 1
    if you are at a certain node and want to find if it has 1 or 2 children:
        (2*posOfNode + 2) > size. if true only left child, else both children exist.
*/

public class PriorityQueueCustom <T extends Comparable<T>> {
    
    private List<T> binaryHeap = new ArrayList<T>();
    private int size = -1;

    public void push(T data){
        binaryHeap.add(data);
        size++;    
        if(size > 0){    
            bubbleUp(size);
        }        
    }   //O(n) because of the bubble up

    public T poll(){
        T temp;
        if(size == -1){
            throw new RuntimeException("Queue is empty");
        }
        if(size == 0){
            temp = binaryHeap.get(size);
            binaryHeap.remove(size);
            size--;
            return temp;
        }
        else{
            swap(0, size);
            temp = binaryHeap.get(size);
            binaryHeap.remove(size);
            size--;
            bubbleDown(0, size);
            return temp;
        }
    }   //O(logn)
    
    public T removeElement(T data){
        if(size == -1){
            throw new RuntimeException("Queue is empty");
        }
        else if(size == 0){
            if(binaryHeap.get(size) == data){
                return poll();
            }
            else{
                throw new RuntimeException("The element " + data + " doesnt exist in the Queue.");
            }
        }
        else{
            int pos = 0;
            T temp;
            while(binaryHeap.get(pos) != data && pos != size){//LvlOrder traversal so makes the  
                pos++;                                        //Big O O(n). 
            }
            if(pos == 0){
                if(binaryHeap.get(pos) == data){
                    return poll();
                }
                else{
                    throw new RuntimeException("The element " + data + " doesnt exist in the Queue.");
                }
            }
            if(pos == size){
                if(binaryHeap.get(pos) == data){
                    temp = binaryHeap.get(size);
                    binaryHeap.remove(size);
                    size--;
                    return temp;
                }
                else{
                    throw new RuntimeException("The element " + data + " doesnt exist in the Queue.");
                }
            }
            else{
                swap(pos, size);
                temp = binaryHeap.get(size);
                binaryHeap.remove(size);
                size--;
                int upOrDown = -1;
                if(leftOrRight(pos) == 1){  //left child
                    if(comparison(pos, pos/2) == 1){
                        upOrDown = 1;       // 1 means bubbling up is needed
                    }
                    else upOrDown = 0;      // 0 means bubbling down is needed
                }
                else{                       //right child
                    if(comparison(pos, (pos/2)-1) == 1){
                        upOrDown = 1;
                    }
                    else upOrDown = 0;
                }
                if(upOrDown == 1){      //finally deciding whether to bubble up or down
                    bubbleUp(pos);
                }
                else bubbleDown(pos, size);
                return temp;
            }
        }
    }   //O(n)

    public boolean Contains(T data){
        if(size == -1){
            return false;
        }
        else {
            int i = 0;
            while( i <= size){
                if(binaryHeap.get(i) == data){
                    return true;
                }
                i++;
            }
            return false;
        }
    }   //O(n)

    public void showLvlOrder(){
        if(size == -1){
            System.out.println("Queue is empty");
        }
        else{
            for(Object values:binaryHeap){
                System.out.println(values);
            }
        }
    }   //O(n)

    public T peek(){
        if(size == -1){
            System.out.println("Queue is empty. Null was returned");
            return null;
        }
        else return binaryHeap.get(0);
    }   //O(1)

    public int heightOfHeap(){
        if(size == -1){
            return 0;
        }
        else return (int)(Math.log(size + 1)/(Math.log(2)) + 1);//mathematical type to calculate the height of a binary tree(with size zero based)
    }

    private int leftOrRight(int pos){
        if(pos == 0){
            return 0; //zero means parent node of the tree
        }
        else if(pos % 2 != 0){
            return 1; // 1 means left child
        }
        else return 2; // 2 means right child
    }   //O(1)

    private int comparison(int i , int j){
        T node1 = binaryHeap.get(i);
        T node2 = binaryHeap.get(j);
        return node1.compareTo(node2);
    }   //O(1)

    private void swap(int i, int j){
        T temp = binaryHeap.get(i);
        binaryHeap.set(i, binaryHeap.get(j));
        binaryHeap.set(j, temp);
    }   //O(1)
    
    private void bubbleUp(int pos){ // recursive function for bubbleUp
        if(leftOrRight(pos) == 0){  //check if child is left or right. If 0 then it is parent node of the tree and it returns
            return;                 //All odd indexes are left children
        }
        else if(leftOrRight(pos) == 1){     //left child case
            if(comparison(pos, pos/2) == 1){//comparison with parent.***by changing 1 to -1
                swap(pos, pos/2);           //                          it becomes a min heap
                pos = pos/2;
                bubbleUp(pos); 
            }
            else return;                 
        }
        else {                                  //right child case
            if(comparison(pos, (pos/2)-1) == 1){//comparison with parent.***by changing 1 to -1
                swap(pos, (pos/2)-1);           //                          it becomes a min heap
                pos = (pos/2) - 1;  
                bubbleUp(pos); 
            }
            else return;    
        }
    }   //O(logn)
    
    private void bubbleDown(int pos, int size){         // recursive function for bubbleDown
        if((2*pos + 1) > size){
            return;
        }
        else{
            if((2*pos + 2) > size){                     //only left child exists
                if(comparison(pos, (2*pos + 1)) == -1){ //comparison parent-left child.***by changing -1 to 1
                    swap(pos, (2*pos + 1));             //                                it becomes a min heap  
                    pos = (2*pos + 1);
                    bubbleDown(pos, size);                    
                }
            }
            else{                                               //left and right child
                if(comparison((2*pos + 1), (2*pos + 2)) == 1){  //comparison between children.(left>right)
                    if(comparison(pos, (2*pos + 1)) == -1){     //comparison parent-left child.***by changing -1 to 1
                        swap(pos, (2*pos + 1));                 //                                it becomes a min heap
                        pos = (2*pos + 1);
                        bubbleDown(pos, size);                    
                    }
                }
                else{                                      //(right >= left)
                    if(comparison(pos, (2*pos + 2)) == -1){//comparison parent-right child.***by changing -1 to 1
                        swap(pos, (2*pos + 2));            //                                 it becomes a min heap
                        pos = (2*pos + 2);
                        bubbleDown(pos, size);                    
                    }
                }
            }
        }
    }    //O(logn)
}
