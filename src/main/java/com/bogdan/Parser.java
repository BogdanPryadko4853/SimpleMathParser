package com.bogdan;

public class Parser {

    LexemeBuffer lexemeBuffer;

    public Parser(LexemeBuffer lexemeBuffer) {
        this.lexemeBuffer = lexemeBuffer;
    }

    public int expr(LexemeBuffer lexemeBuffer) {
        Lexeme lexeme = lexemeBuffer.next();
        if (lexeme.getType() == LexemeType.EOF) {
            return 0;
        } else {
            lexemeBuffer.back();
            return plusMinus(lexemeBuffer);
        }

    }

    public int plusMinus(LexemeBuffer lexemeBuffer) {
        int value = multDiv(lexemeBuffer);
        while (true) {
            Lexeme lexeme = lexemeBuffer.next();
            switch (lexeme.getType()) {
                case PLUS:
                    value += multDiv(lexemeBuffer);
                    break;
                case MINUS:
                    value -= multDiv(lexemeBuffer);
                    break;
                case EOF:
                case RIGHT_BRACKETS:
                    lexemeBuffer.back();
                    return value;
                default:
                    throw new RuntimeException("unexpected lexeme: " + lexeme);
            }
        }
    }

    public int multDiv(LexemeBuffer lexemeBuffer) {
        int value = factor(lexemeBuffer);
        while (true) {
            Lexeme lexeme = lexemeBuffer.next();
            switch (lexeme.getType()) {
                case MULTIPLY:
                    value *= factor(lexemeBuffer);
                    break;
                case DIVIDE:
                    value /= factor(lexemeBuffer);
                    break;
                case EOF:
                case RIGHT_BRACKETS:
                case PLUS:
                case MINUS:
                    lexemeBuffer.back();
                    return value;
                default:
                    throw new RuntimeException("unexpected lexeme: " + lexeme);
            }
        }

    }

    public int factor(LexemeBuffer lexemeBuffer) {
        Lexeme lexeme = lexemeBuffer.next();
        switch (lexeme.getType()) {
            case NUMBER:
                return Integer.parseInt(lexeme.getVal());
            case LEFT_BRACKETS:
                int val = plusMinus(lexemeBuffer);
                lexeme = lexemeBuffer.next(); // Move to the next lexeme after processing the expression inside parentheses
                if (lexeme.getType() != LexemeType.RIGHT_BRACKETS) {
                    throw new RuntimeException("unexpected right brackets: " + lexeme);
                }
                return val;
            default:
                throw new RuntimeException("unexpected lexeme: " + lexeme);
        }
    }

}
