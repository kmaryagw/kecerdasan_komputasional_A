/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication1;

import java.util.Random;

/**
 *
 * @author kmaryagw
 */
class TreeGenerator {
    private static final String[] OPERATORS = {"+", "-", "/", "*"};
    private static final String[] CONSTRAINT = {"x1", "x2"};
    private static final int MAX_OPERAND = 25;
    private static final int deep = 4;

    private static Random random = new Random();

    public static Tree full(int depth) {
        if (depth > 1) {
            String operator = OPERATORS[random.nextInt(OPERATORS.length)];
            return new Tree(operator, full(depth - 1), full(depth - 1));
        } else {
            return new Tree(random.nextInt(MAX_OPERAND) + 1);
        }
    }

    public static Tree grow(int depth) {
        if (depth==deep || depth==(deep-1) ) {
            String operator = OPERATORS[random.nextInt(OPERATORS.length)];
            return new Tree(operator, grow(depth - 1), grow(depth - 1));
        } else if (depth > 1 && random.nextBoolean()) {
            String operator = OPERATORS[random.nextInt(OPERATORS.length)];
            return new Tree(operator, grow(depth - 1), grow(depth - 1));
        } else {
            if(random.nextBoolean()){
                return new Tree(CONSTRAINT[random.nextInt(CONSTRAINT.length)]);
            }else{
                return new Tree(random.nextInt(MAX_OPERAND) + 1);
            }
        }
    }

}
