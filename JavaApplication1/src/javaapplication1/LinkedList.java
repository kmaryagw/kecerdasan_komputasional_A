/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

/**
 *
 * @author kmaryagw
 */

class LinkedListEmptyException extends RuntimeException{
    public LinkedListEmptyException(){
        super();
    }
      
    public LinkedListEmptyException(String message){
        super(message);
    }  
}
 
/**
 *Exception to indicate that Stack is empty.
 */
class StackEmptyException extends RuntimeException {
    public StackEmptyException(){
        super();
    }
    
    public StackEmptyException(String message){
        super(message);
    }
}

class Node {
    public float data; // data in Node.
    public Node next; // points to next Node in list.
 
    /**
     * Constructor
     */
    public Node(float data){
           this.data = data;
    }
 
    /**
     * Display Node's data
     */
    public void displayNode() {
        System.out.print( data + " ");
    }
}
 
 
/**
 * LinkedList class
 */
class LinkedList {
    private Node first; // ref to first link on list
 
    /**
     * LinkedList constructor
     */
    public LinkedList(){
        first = null;
    }
 
    /**
     * Insert New Node at first position
     */
    public void insertFirst(float data) {
        Node newNode = new Node(data); //Creation of New Node.
        newNode.next = first;   //newLink ---> old first
        first = newNode;  //first ---> newNode
    }
 
    /**
     * Deletes first Node
     */
    public float deleteFirst()
    {
        if(first==null){  //means LinkedList in empty, throw exception.              
            throw new LinkedListEmptyException("LinkedList doesn't contain any Nodes.");
        }
        Node tempNode = first; // save reference to first Node in tempNode- so that we could return saved reference.
        first = first.next; // delete first Node (make first point to second node)
        return tempNode.data; // return tempNode (i.e. deleted Node)
    }
    
    /**
     * Display LinkedList
     */
    public void displayLinkedList() {
        Node tempDisplay = first; // start at the beginning of linkedList
        while (tempDisplay != null){ // Executes until we don't find end of list.
            tempDisplay.displayNode();
            tempDisplay = tempDisplay.next; // move to next Node
        }
        System.out.println();       
    }
}
 
/**
 * For implementing stack using using LinkedList- This StackLinkedList
 * class internally maintains LinkedList reference in java.
 */
 
class StackLinkedList{
    
    LinkedList linkedList = new LinkedList(); // creation of Linked List
    
    /**
     * Push items in stack, it will put items on top of Stack.
     */
    public void push(float value){
        linkedList.insertFirst(value);
    }
 
    /**
     * Pop items in stack, it will remove items from top of Stack.
     */
    public float pop() throws StackEmptyException {
        try{
            return linkedList.deleteFirst();
        }catch(LinkedListEmptyException llee){
            throw new StackEmptyException();
        }
    }
 
    /**
     * Display stack.
     */
    public void displayStack() {
        System.out.print("Displaying Stack >  Top to Bottom : ");
        linkedList.displayLinkedList();
    }
}
