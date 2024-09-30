package com.bogdan;

import java.util.ArrayList;
import java.util.List;

public class LexemeBuffer {
    int pos;

    List<Lexeme> lexemes;

    public LexemeBuffer(List<Lexeme> lexemes) {
        this.lexemes = lexemes;
    }

    public Lexeme next() {
        return lexemes.get(pos++);
    }

    public Lexeme back() {
        return lexemes.get(--pos);
    }

}
