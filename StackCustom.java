package DataStructurePackage;
/*Stack implementation with nodes.
functions:  push: pushes an element to the stack(O(1))
            pop: returns the top element if thereis one or throws an exception (O(1))
            peek: prints the top element or prints a message if it is empty (O(1))
            isEmpty: checks if the stack is empty and returns true or false (O(n))
            contains: checks if an element exists in the stack and returns true or false (O(n))
            show: prints the stack or a message if it is empty (O(n))
 */

public class StackCustom<T> {

    private class Node{
        private T  data;
        private Node below;
    }

    private Node top;
    private int height = -1;

    public void push(T data){
        Node newtop = new Node();
        newtop.data = data;
        newtop.below = top;
        top = newtop;
        height++;
    }   //O(1)

    public T pop() {
        if(height == -1){
            throw new RuntimeException("Stack is currently empty");
        }
        else{
            Node temp = top;
            System.out.println(top.data + " has been popped");
            top = top.below;
            height--;
            return temp.data;
        }
    }   //O(1)

    public void peek(){
        if(isEmpty()){
            System.out.println("Stack is currently empty");
        }
        else System.out.println("The top element is " + top.data);
        
    }   //O(1)

    public boolean isEmpty(){
        if(height == -1){
            return true;
        }
        else return false;
    }   //O(1)

    public boolean contains(T data){
        if(height == -1){
            return false;
        }
        else{
            int i = 0;
            Node temp = top;
            while(temp.data != data && i <= height){
                i++;
                if(i <= height){
                    temp = temp.below;
                }
            }
            if(i <= height){
                return true;
            }
            else return false;
        }
    }   //O(N)

    public void show(){
        if(height == -1){
            System.out.println("Stack is currently empty");
        }
        else{
            Node temp = top;
            for(int i = 0; i <= height; i++){
                System.out.println(temp.data);
                temp = temp.below;
            }
        }
    }   //O(N)

    
    

}
