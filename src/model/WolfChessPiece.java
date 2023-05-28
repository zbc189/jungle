package model;

public class WolfChessPiece extends ChessPiece {
    //protected int rank = 4;

    public WolfChessPiece(PlayerColor owner, String name) {
        super(owner, name);
        displayName = "狼";
        rank = 4;
    }

    @Override
    public boolean isValidMove(ChessboardPoint src, ChessboardPoint target) {
        if (calculateDistance(src, target) == 1) {
            if (!riverCell.contains(target)) {
                return true;
            }
        }
        return false;
    }


}
