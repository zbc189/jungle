package model;

public class LionChessPiece extends ChessPiece {
//    protected int rank=7;
    public LionChessPiece(PlayerColor owner, String name) {
        super(owner, name);
        displayName = "狮";rank=7;
    }


    public boolean isValidMove(ChessboardPoint src,ChessboardPoint target) {
        if (calculateDistance(src, target) == 1) {
            if (!riverCell.contains(target)) {
                return true;
            }
        }
        //横着跳
        if (src.getRow() == target.getRow() && calculateDistance(src, target) == 3) {
            boolean canJumpOnRow = true;
            for (int i = Math.min(src.getCol(),target.getCol()) + 1; i < Math.max(src.getCol(),target.getCol()); i++) {
                if (!riverCell.contains(new ChessboardPoint(src.getRow(), i))) {
                    canJumpOnRow = false;
                    break;
                }
            }
            if (canJumpOnRow){
                return true;
            }
        }
        //竖着跳
        if (src.getCol() == target.getCol() && calculateDistance(src, target) == 4) {
            boolean canJumpOnCol = true;
            for (int i = Math.min(src.getRow(),target.getRow()) + 1; i < Math.max(src.getRow(),target.getRow()); i++) {
                if (!riverCell.contains(new ChessboardPoint(i,src.getCol()))) {
                    canJumpOnCol = false;
                    break;
                }
            }
            if (canJumpOnCol){
                return true;
            }
        }
        return false;
    }



}
