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
public class Tree{
    Object value;
    Tree left, right;

    public Tree(Object value)
    {
        this.value=value;
    }   

    public Tree(Object value, Tree left, Tree right) 
    {
        this.value = value;
        this.left = left;
        this.right = right;
    } 

    // Getter & setter for the value.
    public Object getValue(){
        return value;}
    public void setValue(Object value){
        this.value = value;}

    // Getters & setters for left & right nodes.
    public Tree getLeft(){
        return left;}
    public Tree getRight(){
        return right;}
    public void setLeft(Tree ln){
        left = ln;}
    public void setRight(Tree rn){
        right = rn;}
}
