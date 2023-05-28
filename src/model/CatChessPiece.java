package model;

import model.ChessPiece;
import model.ChessboardPoint;
import model.PlayerColor;

import javax.swing.*;
import java.awt.*;

public class CatChessPiece extends ChessPiece {
    //protected int rank=2;
    public CatChessPiece(PlayerColor owner, String name) {
        super(owner, name);
        displayName = "猫";
        Image image=new ImageIcon("Elephant-red.png").getImage();
        rank = 2;
    }


    public boolean isValidMove(ChessboardPoint src, ChessboardPoint target) {
        if (calculateDistance(src, target) == 1){
            if (!riverCell.contains(target)){
                return true;
            }
        }
        return false;
    }

}
