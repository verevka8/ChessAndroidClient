package com.example.websockets.chessPieces;

import android.graphics.Color;

public class Pawn implements Pieces{
    String type;
    int teamColor;

    public Pawn(String type,int teamColor){
        this.type = type;
        this.teamColor = teamColor;
    }

    @Override
    public void setType(String type) {}

    @Override
    public String getType() {
        return " " + type + " ";
    }

    public int getTeamColor() {
        return teamColor;
    }

    public void setTeamColor(int teamColor) {
        this.teamColor = teamColor;
    }
}
