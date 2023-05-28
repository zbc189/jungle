package model;

public class RatChessPiece extends ChessPiece {
    protected int rank=1;
    public RatChessPiece(PlayerColor owner, String name) {
        super(owner, name);
        displayName = "鼠";rank=1;
    }
    public boolean isValidMove(ChessboardPoint src,ChessboardPoint target) {
        return calculateDistance(src, target) == 1;
    }



}
