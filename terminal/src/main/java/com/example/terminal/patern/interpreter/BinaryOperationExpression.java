package com.example.terminal.patern.interpreter;


public class BinaryOperationExpression implements Expression {
    private final Expression left;
    private final Expression right;
    private final char operation;

    public BinaryOperationExpression(Expression left, Expression right, char operation) {
        this.left = left;
        this.right = right;
        this.operation = operation;
    }

    @Override
    public int interpret() {
        switch (operation) {
            case '+':
                return left.interpret() + right.interpret();
            case '-':
                return left.interpret() - right.interpret();
            case '*':
                return left.interpret() * right.interpret();
            case '/':
                int divisor = right.interpret();
                if (divisor != 0) {
                    return left.interpret() / divisor;
                } else {
                    throw new ArithmeticException("Ділення на нуль!");
                }
            default:
                throw new IllegalArgumentException("Невідома операція: " + operation);
        }
    }
}