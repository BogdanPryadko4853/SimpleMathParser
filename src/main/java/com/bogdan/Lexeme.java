package com.bogdan;

public class Lexeme {
    private LexemeType type;
    private String val;

    public String getVal() {
        return val;
    }

    public LexemeType getType() {
        return type;
    }

    public Lexeme(LexemeType type, String val) {
        this.type = type;
        this.val = val;
    }

    public Lexeme(LexemeType type,Character val) {
        this.type = type;
        this.val = val.toString();
    }
}
