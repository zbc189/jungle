package model;

public class DogChessPiece extends ChessPiece {
//    protected int rank=3;
    public DogChessPiece(PlayerColor owner, String name) {
        super(owner, name);
        displayName = "狗";
        rank = 3;
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
