import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class binaryTree<T> {
    
    private class Node<T>{
        T data;
        Node left;
        Node right;
    }
    
    private Node root;  

    public void insertLvlOrder(T data){

        Node temp = new Node();
        temp.data = data;

        if(root == null){
            root = temp;
            return;
        }
        else{
            Queue<Node> nodePosFinder = new LinkedList<>();
            nodePosFinder.add(root);
            while(!nodePosFinder.isEmpty()){
                Node trav = new Node();
                trav = nodePosFinder.poll();
                if(trav.left == null){
                    trav.left = temp;
                    return;
                }
                else if(trav.right == null){
                    trav.right = temp;
                    return;
                }
                else{
                    nodePosFinder.add(trav.left);
                    nodePosFinder.add(trav.right);
                }
            }
        }
    }

    //level order traversal = breadth first traversal
    public void showLvlOrder(){
        if(root == null){
            System.out.println("Binary tree is empty");
            return;
        }
        else{
            Queue<Node> lvlOrder = new LinkedList<>();
            lvlOrder.add(root);
            Node temp = new Node();
            while(!lvlOrder.isEmpty()){
                temp = lvlOrder.poll();
                System.out.println(temp.data);
                if(temp.left != null){
                    lvlOrder.add(temp.left);
                }
                if(temp.right != null){
                    lvlOrder.add(temp.right);
                }
            }
        }
    }

    /*deapth first search traversals: preorder, inorder, postorder
        preorder: visit-> check left-> check right
        inorder: check left-> visit-> check right
        post order: check left-> check right-> visit
    */
    public void showPreOrder(){
        if(root == null){
            System.out.println("Binary tree is empty");
            return;
        }
        else{
            Stack<Node> preorder = new Stack<>();
            preorder.add(root);
            Node temp = new Node();
            while(!preorder.empty()){
                temp = preorder.pop();
                System.out.println(temp.data);
                if(temp.right != null){
                    preorder.add(temp.right);
                }
                if(temp.left != null){
                    preorder.add(temp.left);
                }
            }
        }
    }

    public void showInOrder(){
        if(root == null){
            System.out.println("Binary tree is empty");
            return;
        }
        else{
            Stack<Node> inorder = new Stack<>();
            Node temp = new Node();
            temp = root;
            while(true){
                if(temp != null){
                    inorder.add(temp);                                        
                    temp = temp.left;
                }
                else {
                    if(inorder.empty()) break;
                    temp = inorder.pop();
                    System.out.println(temp.data);
                    temp = temp.right;
                }

            }

        }
    }

    public void showPostOrder(){
        if(root == null){
            System.out.println("Binary tree is empty");
            return;
        }
        else{
            Stack<Node> inorder = new Stack<>();
            Node temp = new Node();
            temp = root;
            while(true){
            }

        }
    }
    
}
