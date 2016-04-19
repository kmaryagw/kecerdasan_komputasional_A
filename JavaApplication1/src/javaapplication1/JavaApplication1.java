/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

/**
 *
 * @author kmaryagw
 */
public class JavaApplication1 {

    /**
     * @param args the command line arguments
     */
    private static int [][] x;
    private static int [] y;
    private static int lamda,miu,generasi,lt;
    private static Tree [] ind;
    private static Tree [] indNew;
    private static Tree [] indTotal;
    private static Tree indBest;
    private static double fitnessBest;
    private static Random random = new Random();
    private static double fitness [][];
    private static int generasiBest;
    private static List<Object> tarr;
    
    
    private static final String[] OPERATORS = {"+", "-", "/", "*"};
    private static final String[] CONSTRAINT = {"x1", "x2"};
    private static final int MAX_OPERAND = 25;
    
    public static void main(String[] args) {
        // TODO code application logic here
        
        int i,j,k,temp;
        Tree head;
        initCase();
        initInd();
        //evalFit();
        
        for(i=1;i<=generasi;i++){
            System.out.print("\n==============================================\n");
            System.out.print("GENERASI "+i+"\n");
            System.out.print("==============================================\n");
            k=0;
            //tiap parent dilakukan mutasi, lamda 1*miu
            for(j=0;j<miu;j++){
                
                //indNew[j] = new Tree(ind[j].getValue(),ind[j].getLeft(),ind[j].getRight());
                indNew[j] = copy(ind[j]);
                head = indNew[j];
                mutasi(head);
                
                indTotal[k] = copy(ind[j]); k++;
                indTotal[k] = copy(indNew[j]); k++;
            }
            /*System.out.print("\n\n-------\nPARENT\n---------\n");
            for(j=0;j<miu;j++){
                System.out.print("PARENT "+(j+1)+"\n");
                //head=indNew[j];
                inOrder(ind[j]);
                System.out.print("\t");
                postOrder(ind[j]);
                System.out.print("\t\n");
            }*/
            
            System.out.print("-------------------\nOffspring\n-------------------\n");
            for(j=0;j<lamda;j++){
                System.out.print("Offspring "+(j+1)+" | inorder : ");
                //head=indNew[j];
                inOrder(indNew[j]);
                System.out.print("\t| postorder : ");
                postOrder(indNew[j]);
                System.out.print("\n");
            }
            
            /*System.out.print("\n\n-------\nINDIVIDU TOTAL\n---------\n");
            for(j=0;j<(miu+lamda);j++){
                System.out.print("INDIVIDU "+(j+1)+"\n");
                //head=indTotal[j];
                inOrder(indTotal[j]);
                System.out.print("\t");
                postOrder(indTotal[j]);
                System.out.print("\t\n");
            }*/
            
            System.out.print("\n----------------------\nEVALUASI FITNESS\n");
            System.out.print("----------------------");
            evalFitnes();
            
            System.out.print("\n\nFITNESS SEBELUM DI SORTING\n");
            for(j=0;j<fitness.length;j++){
                System.out.println(fitness[j][0]+" | "+fitness[j][1]);
            }
            System.out.print("\nHASIL SORTING FITNESS : \n");
            Arrays.sort(fitness, (double[] o1, double[] o2) -> Double.compare(o1[0], o2[0]));
            
            int xx;
            for(j=0;j<fitness.length;j++){
                System.out.println(fitness[j][0]+" | "+fitness[j][1]);
                if(fitnessBest > fitness[j][0]){
                    fitnessBest = fitness[j][0];
                    generasiBest = i;
                    xx = (int) fitness[j][1];
                    indBest = copy(indTotal[xx]);
                }
            }
            
            for(j=0;j<miu;j++){
                temp = (int) fitness[j][1];
                ind[j] = copy(indTotal[temp]);
            }
            System.out.print("-------------------\nINDIVIDU TERPILIH\n-------------------\n");
            for(j=0;j<miu;j++){
                System.out.print("Individu "+(j+1)+" | ");
                //head=indNew[j];
                inOrder(ind[j]);
                System.out.print("\n");
                //postOrder(ind[j]);
                //System.out.print("\t\n");
            }
        }
        
        System.out.println("\n\n=======================\nNILAI FITNES TERBAIK : "+fitnessBest+
                "\nGenerasi : "+generasiBest);
        System.out.print("In order : ");
        inOrder(indBest);
        System.out.print("\nPost order : ");postOrder(indBest);
        System.out.println("\n=======================");
    }
    
    public static Tree copy(Tree root){
        if(root == null) {
            return root;
        }else{
            return new Tree(root.getValue(),copy(root.getLeft()),copy(root.getRight()));
        }
    }
        
    public static void mutasi(Tree root){
        if(root==null) return ;
        mutasi(root.getLeft());
        mutasi(root.getRight());
        if(random.nextBoolean()){ //apakah node dimutasi atau tidak, bisa pada terminal atau fungsi
            //if(random.nextBoolean()){ //mutasi dengan terminal
                if(fungsi(root.getValue().toString())==1){ //mutasi fungsi
                    root.setValue(OPERATORS[random.nextInt(OPERATORS.length)]);
                }else{ //mutasi terminal
                    if(random.nextBoolean()){
                        root.setValue(CONSTRAINT[random.nextInt(CONSTRAINT.length)]);
                    }else{
                        root.setValue(random.nextInt(MAX_OPERAND) + 1);
                    }
                }
            //}
            //return root;
        }else{
            //return root;
        }
    }
    
    public static void evalFitnes(){
        int i,k,flag=0;
        float j,opr1,opr2;
        float hasil,temp = 0,temp2;
        Tree head;
        
        for(i=0;i<(miu+lamda);i++){
            
            hasil = 0;
            head = indTotal[i];
            getPostOrder(head);
            //postOrder(head);
            System.out.print("\nIndividu TOTAL "+(i+1)+" | ");
            for(k=0;k<10;k++){ //hitung sebanyak constraint
                //System.out.print("Constraint "+(k+1)+" ("+x[k][0]+"|"+x[k][1]+"|"+y[k]+") : ");
                StackLinkedList stackLinkedList = new StackLinkedList();
                //hitung fitnes menggunakan stack
                for(Object dt : tarr){
                    if(fungsi(dt.toString())==1){
                        opr1 = stackLinkedList.pop();
                        opr2 = stackLinkedList.pop();
                        if(opr1 == 9991)
                            opr1 = x[k][0];
                        else if(opr1 == 9992)
                            opr1 = x[k][1];
                        
                        if(opr2 == 9991)
                            opr2 = x[k][0];
                        else if(opr2 == 9992)
                            opr2 = x[k][1];
                        
                        if(dt.equals("+")){
                            temp = opr2 + opr1;
                        }else if(dt.equals("-")){
                            temp = opr2 - opr1;
                        }else if(dt.equals("*")){
                            temp = opr2 * opr1;
                        }else if(dt.equals("/")){
                            temp = opr2 / opr1;
                        }
                        stackLinkedList.push(temp);
                        
                    } else {
                        if(dt.equals("x1")){
                            j = (float) 9991;
                        }else if(dt.equals("x2")){
                            j = (float) 9992;
                        }else{
                            j = (int) dt;
                        }
                        stackLinkedList.push(j);
                    }
                }
                temp2 = stackLinkedList.pop();
                hasil = hasil + (abs(y[k] - temp2));
                //System.out.print(temp2+"\n");
            }
            fitness[i][0]=hasil;
            fitness[i][1]=i;
            tarr.clear();
            System.out.print("Fitness = "+hasil);
        }
        //System.out.print("\n------------------\n");
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
        indNew = new Tree[lamda];
        indTotal = new Tree[miu+lamda];
        fitness = new double[miu+lamda][2];
        tarr = new ArrayList<>();
        fitnessBest = 999999999.0; //set sebesarnya
        
        int i;
        for(i=0;i<x.length;i++){
            System.out.println(x[i][0]+" | "+x[i][1]+" | "+y[i]);
        }
        System.out.print("\n");
    }
    
    public static void initInd(){
        
        int i;
        for(i=0;i<miu;i++){
            ind[i] = TreeGenerator.grow(4);
            while(!ind[i].value.equals("+") && !ind[i].value.equals("-") && !ind[i].value.equals("*") && !ind[i].value.equals("/")){
                ind[i] = TreeGenerator.grow(lt);
            }
        }
        
        Tree head;
        int j;
        for(j=0;j<miu;j++){
            System.out.print("Individu "+(j+1)+"\n");
            head=ind[j];
            inOrder(ind[j]);
            System.out.print("\t");
            postOrder(ind[j]);
            System.out.print("\t\n");
        }
    }
    
    public static void inOrder(Tree root)
    {
        if(root == null) return;
        inOrder(root.getLeft());
        System.out.print(root.value);
        inOrder(root.getRight()); 
    }
    
    public static void getPostOrder(Tree root)
    {
        if(root == null) return;
        getPostOrder(root.getLeft());
        getPostOrder(root.getRight());
        tarr.add(root.getValue());
    }
    
    public static void postOrder(Tree root)
    {
        if(root == null) return;
        postOrder(root.getLeft());
        postOrder(root.getRight());
        System.out.print(root.value);
    }
}
