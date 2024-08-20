package com.example.websockets.chessPieces;

public interface Pieces {
    String type = "";

    void setType(String type);
    String getType();
    void setTeamColor(int teamColor);
    int getTeamColor();


}
