package com.bogdan;

import java.util.ArrayList;
import java.util.List;

public class Main {


    public static void main(String[] args) {

        String exp = "2+2*2-(2+22)";//example
        List<Lexeme> lexemes = lexAnalyze(exp);
        LexemeBuffer lexemeBuffer = new LexemeBuffer(lexemes);
        Parser parser = new Parser(lexemeBuffer);
        int val = parser.expr(lexemeBuffer);
        System.out.println(val);
    }


    public static List<Lexeme> lexAnalyze(String expression) {
        List<Lexeme> lexeme = new ArrayList<>();
        int pos = 0;
        while (pos < expression.length()) {
            char ch = expression.charAt(pos);
            switch (ch) {
                case '(':
                    lexeme.add(new Lexeme(LexemeType.LEFT_BRACKETS, ch));
                    pos++;
                    break;
                case ')':
                    lexeme.add(new Lexeme(LexemeType.RIGHT_BRACKETS, ch));
                    pos++;
                    break;
                case '+':
                    lexeme.add(new Lexeme(LexemeType.PLUS, ch));
                    pos++;
                    break;
                case '-':
                    lexeme.add(new Lexeme(LexemeType.MINUS, ch));
                    pos++;
                    break;
                case '*':
                    lexeme.add(new Lexeme(LexemeType.MULTIPLY, ch));
                    pos++;
                    break;
                case '/':
                    lexeme.add(new Lexeme(LexemeType.DIVIDE, ch));
                    pos++;
                    break;
                default:
                    if (ch >= '0' && ch <= '9') {
                        StringBuffer buf = new StringBuffer();

                        do {
                            buf.append(ch);
                            pos++;
                            if (pos >= expression.length()) {
                                break;
                            }
                            ch = expression.charAt(pos);

                        } while (ch >= '0' && ch <= '9');
                        lexeme.add(new Lexeme(LexemeType.NUMBER, buf.toString()));
                    } else {
                        if (ch != ' ') {
                            throw new RuntimeException("Unknown lexeme " + ch);
                        }
                        pos++;
                    }
            }
        }

        lexeme.add(new Lexeme(LexemeType.EOF, ""));
        return lexeme;
    }
}