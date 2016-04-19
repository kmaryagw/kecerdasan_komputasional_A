/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author kmaryagw
 */
public class JavaApplication1_1 {

    /**
     * @param args the command line arguments
     */
    /*private static int [][] x;
    private static int [] y;
    private static int lamda,miu,generasi,lt;
    private static Tree [] ind;
    private static Random random = new Random();
    private static double fitness [];
    private static Object tarray [];
    private static List<Object> tarr;
    
    public static void main(String[] args) {
        // TODO code application logic here
        
        int i,j,k;
        initCase();
        initInd();
        evalFit();
        
        /*Tree full3 = TreeGenerator.grow(4);
        Tree grow4 = TreeGenerator.grow(4);
        while(!grow4.value.equals("+") && !grow4.value.equals("-") && 
                !grow4.value.equals("*") && !grow4.value.equals("/")){
            grow4 = TreeGenerator.grow(4);
        }
        
        while(!full3.value.equals("+") && !full3.value.equals("-") && 
                !full3.value.equals("*") && !full3.value.equals("/")){
            full3 = TreeGenerator.grow(4);
        }*/
        
        /*Tree head;
        head=full3;
        inOrder(head);
        System.out.print("\n");
        postOrder(head);*/
        /*
        for(i=0;i<x.length;i++){
            System.out.println(x[i][0]+" | "+x[i][1]+" | "+y[i]);
        }
        System.out.print("\n");
        
        Tree head;
        for(j=0;j<miu;j++){
            System.out.print("Individu "+(j+1)+"\n");
            head=ind[j];
            inOrder(head);
            System.out.print("\n");
            postOrder(head);
            System.out.print("\n\n");
        }
        System.out.println(random.nextGaussian());
    }
    
    public static void evalFit(){
        int i,j,k,flag=0;
        int hasil,opr1,opr2;
        Tree head;
        //System.out.println(random.nextGaussian());
        for(i=0;i<miu;i++){
            hasil = 0;
            head = ind[i];
            getFitness(head);
            
            System.out.print("Individu "+(i+1)+" : \n");
            for(k=0;k<10;k++){
                System.out.print("\tConstraint "+(k+1)+" : ");
                StackLinkedList stackLinkedList=new StackLinkedList();
                int temp = 0;
                for(Object dt : tarr){
                    if(fungsi(dt.toString())==1){
                        opr1 = stackLinkedList.pop();
                        opr2 = stackLinkedList.pop();
                        if(opr1 == 9991)
                            opr1 = x[k][0];
                        
                        if(opr2 == 9992)
                            opr1 = x[k][1];
                        
                        if(dt.equals("+")){
                            temp = opr1 + opr2;
                        }else if(dt.equals("-")){
                            temp = opr1 - opr2;
                        }else if(dt.equals("*")){
                            temp = opr1 * opr2;
                        }else if(dt.equals("/")){
                            temp = opr1 / opr2;
                        }
                        stackLinkedList.push(temp);
                    } else {
                        if(dt.equals("x1")){
                            j=9991;
                        }else if(dt.equals("x2")){
                            j=9992;
                        }else{
                            j = (int) dt;
                        }
                        stackLinkedList.push(j);
                    }
                }
                hasil = stackLinkedList.pop();
                System.out.print(hasil+"\n");
            }
            /*for(Object dt : tarr){
                //if(flag==0 && !dt.equals("x1") && !dt.equals("x2")){
                  //  hasil = hasil + new Double(dt.toString());
                //}
                //System.out.print(dt+" ");
                if(dt.equals("+")){
                    flag = 1;
                }else if(dt.equals("-")){
                    flag = 2;
                }else if(dt.equals("*")){
                    flag = 3;
                }else if(dt.equals("/")){
                    flag = 4;
                }
                
                StackLinkedList stackLinkedList=new StackLinkedList();
                j = (int) dt;
                stackLinkedList.push(j);
                
            }*//*
            tarr.clear();
            System.out.print("\n");
        }
        System.out.print("\n------------------\n");
    }
    
    public static int fungsi(String kt){
        if(kt.equals("+") || kt.equals("-") || kt.equals("/") || kt.equals("*")){
            return 1;
        } else {
            return 0;
        }
    }
    
    
    public static void initCase(){
        
        x = new int[10][2];
        y = new int[10];
        
        x[0][0]=3; x[0][1]=4; y[0]=18;
        x[1][0]=4; x[1][1]=2; y[1]=24;
        x[2][0]=6; x[2][1]=3; y[2]=51;
        x[3][0]=6; x[3][1]=4; y[3]=48;
        x[4][0]=7; x[4][1]=1; y[4]=87;
        x[5][0]=8; x[5][1]=2; y[5]=104;
        x[6][0]=9; x[6][1]=3; y[6]=123;
        x[7][0]=10; x[7][1]=4; y[7]=144;
        x[8][0]=10; x[8][1]=6; y[8]=128;
        x[9][0]=11; x[9][1]=9; y[9]=143;
        
        lt = 4; //kedalaman tree start dari 1
        generasi = 200;
        miu = 10;
        lamda = 1*miu;
        ind = new Tree[miu];
        fitness = new double[miu];
        tarray = new Object[2*(2^lt)-1];
        tarr = new ArrayList<>();
    }
    
    public static void initInd(){
        
        int i;
        for(i=0;i<miu;i++){
            ind[i] = TreeGenerator.grow(4);
            while(!ind[i].value.equals("+") && !ind[i].value.equals("-") && !ind[i].value.equals("*") && !ind[i].value.equals("/")){
                ind[i] = TreeGenerator.grow(lt);
            }
        }
    }
    
    public static void inOrder(Tree root)
    {
        if(root == null) return;
        inOrder(root.getLeft());
        System.out.print(root.value);
        inOrder(root.getRight()); 
    }
    public static void getFitness(Tree root)
    {
        if(root == null) return;
        getFitness(root.getLeft());
        //System.out.print(root.value);
        //tarray = (Object[]) root.value;
        getFitness(root.getRight());
        tarr.add(root.value);
    }
    
    public static void postOrder(Tree root)
    {
        if(root == null) return;
        postOrder(root.getLeft());
        postOrder(root.getRight());
        System.out.print(root.value);
    }
    */
}
