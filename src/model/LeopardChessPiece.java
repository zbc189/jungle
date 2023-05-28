package model;

public class LeopardChessPiece extends ChessPiece {
//    protected int rank=5;
    public LeopardChessPiece(PlayerColor owner, String name) {
        super(owner, name);
        displayName = "豹";
        rank=5;
    }

    @Override
    public boolean isValidMove(ChessboardPoint src,ChessboardPoint target) {
        if (calculateDistance(src, target) == 1){
            if (!riverCell.contains(target)){
                return true;
            }
        }
        return false;
    }




}
