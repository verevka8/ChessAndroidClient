package com.example.websockets;

public class ChessCell {
    private boolean flag;

    public ChessCell(boolean flag) {
        this.flag = flag;
    }

    public boolean getFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }
}
