package model;

public class ElephantChessPiece extends ChessPiece{
//    protected int rank=8;
    public ElephantChessPiece(PlayerColor owner,String name){
        super(owner,name);
        displayName="象";
        rank=8;

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
