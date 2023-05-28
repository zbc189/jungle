package model;

import java.util.List;

/**
 * This class store the real chess information.
 * The Chessboard has 9*7 cells, and each cell has a position for chess
 */
public class Chessboard {
    private Cell[][] grid;

    public Chessboard() {
        this.grid =
                new Cell[Constant.CHESSBOARD_ROW_SIZE.getNum()][Constant.CHESSBOARD_COL_SIZE.getNum()];//19X19

        initGrid();
        initPieces();
    }

    private void initGrid() {
        for (int i = 0; i < Constant.CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum(); j++) {
                grid[i][j] = new Cell();
            }
        }
    }

    public void removeAllPieces() {
        for (int i = 0; i < Constant.CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum(); j++) {
                if (grid[i][j].getPiece() != null) {
                    grid[i][j].setPiece(null);
                }
            }
        }
    }


    public void initPieces() {
        grid[6][0].setPiece(new ElephantChessPiece(PlayerColor.BLUE, "Elephant"));
        grid[2][6].setPiece(new ElephantChessPiece(PlayerColor.RED, "Elephant"));

        grid[8][6].setPiece(new LionChessPiece(PlayerColor.BLUE, "Lion"));
        grid[0][0].setPiece(new LionChessPiece(PlayerColor.RED, "Lion"));

        grid[8][0].setPiece(new TigerChessPiece(PlayerColor.BLUE, "Tiger"));
        grid[0][6].setPiece(new TigerChessPiece(PlayerColor.RED, "Tiger"));

        grid[6][4].setPiece(new LeopardChessPiece(PlayerColor.BLUE, "Leopard"));
        grid[2][2].setPiece(new LeopardChessPiece(PlayerColor.RED, "Leopard"));

        grid[6][2].setPiece(new WolfChessPiece(PlayerColor.BLUE, "Wolf"));
        grid[2][4].setPiece(new WolfChessPiece(PlayerColor.RED, "Wolf"));

        grid[7][5].setPiece(new DogChessPiece(PlayerColor.BLUE, "Dog"));
        grid[1][1].setPiece(new DogChessPiece(PlayerColor.RED, "Dog"));

        grid[7][1].setPiece(new CatChessPiece(PlayerColor.BLUE, "Cat"));
        grid[1][5].setPiece(new CatChessPiece(PlayerColor.RED, "Cat"));


        grid[6][6].setPiece(new RatChessPiece(PlayerColor.BLUE, "Rat"));
        grid[2][0].setPiece(new RatChessPiece(PlayerColor.RED, "Rat"));
    }

    public void initPieces(List<String> lines) {
        for (int i = 0; i < Constant.CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum(); j++) {
                if (lines.get(i).charAt(j) == '1') {
                    grid[i][j].setPiece(new RatChessPiece(PlayerColor.RED, "Rat"));
                }

                if (lines.get(i).charAt(j) == '2') {
                    grid[i][j].setPiece(new CatChessPiece(PlayerColor.RED, "Cat"));
                }
                if (lines.get(i).charAt(j) == '3') {
                    grid[i][j].setPiece(new DogChessPiece(PlayerColor.RED, "Dog"));
                }
                if (lines.get(i).charAt(j) == '4') {
                    grid[i][j].setPiece(new WolfChessPiece(PlayerColor.RED, "Wolf"));
                }
                if (lines.get(i).charAt(j) == '5') {
                    grid[i][j].setPiece(new LeopardChessPiece(PlayerColor.RED, "Leopard"));
                }
                if (lines.get(i).charAt(j) == '6') {
                    grid[i][j].setPiece(new TigerChessPiece(PlayerColor.RED, "Tiger"));
                }
                if (lines.get(i).charAt(j) == '7') {
                    grid[i][j].setPiece(new LionChessPiece(PlayerColor.RED, "Lion"));
                }
                if (lines.get(i).charAt(j) == '8') {
                    grid[i][j].setPiece(new ElephantChessPiece(PlayerColor.RED, "Elephant"));
                }
                if (lines.get(i).charAt(j) == 'a') {
                    grid[i][j].setPiece(new RatChessPiece(PlayerColor.BLUE, "Rat"));
                }

                if (lines.get(i).charAt(j) == 'b') {
                    grid[i][j].setPiece(new CatChessPiece(PlayerColor.BLUE, "Cat"));
                }
                if (lines.get(i).charAt(j) == 'c') {
                    grid[i][j].setPiece(new DogChessPiece(PlayerColor.BLUE, "Dog"));
                }
                if (lines.get(i).charAt(j) == 'd') {
                    grid[i][j].setPiece(new WolfChessPiece(PlayerColor.BLUE, "Wolf"));
                }
                if (lines.get(i).charAt(j) == 'e') {
                    grid[i][j].setPiece(new LeopardChessPiece(PlayerColor.BLUE, "Leopard"));
                }
                if (lines.get(i).charAt(j) == 'f') {
                    grid[i][j].setPiece(new TigerChessPiece(PlayerColor.BLUE, "Tiger"));
                }
                if (lines.get(i).charAt(j) == 'g') {
                    grid[i][j].setPiece(new LionChessPiece(PlayerColor.BLUE, "Lion"));
                }
                if (lines.get(i).charAt(j) == 'h') {
                    grid[i][j].setPiece(new ElephantChessPiece(PlayerColor.BLUE, "Elephant"));
                }
            }
        }
    }

    public ChessPiece getChessPieceAt(ChessboardPoint point) {
        return getGridAt(point).getPiece();
    }

    private Cell getGridAt(ChessboardPoint point) {
        return grid[point.getRow()][point.getCol()];
    }

    private int calculateDistance(ChessboardPoint src, ChessboardPoint dest) {
        return Math.abs(src.getRow() - dest.getRow()) + Math.abs(src.getCol() - dest.getCol());
    }

    private ChessPiece removeChessPiece(ChessboardPoint point) {
        ChessPiece chessPiece = getChessPieceAt(point);
        getGridAt(point).removePiece();
        return chessPiece;
    }

    private void setChessPiece(ChessboardPoint point, ChessPiece chessPiece) {
        getGridAt(point).setPiece(chessPiece);
    }

    public void moveChessPiece(ChessboardPoint src, ChessboardPoint dest) {
        if (!isValidMove(src, dest)) {
            throw new IllegalArgumentException("Illegal chess move!");
        }
        setChessPiece(dest, removeChessPiece(src));
    }

    public void captureChessPiece(ChessboardPoint src, ChessboardPoint dest) {
        if (isValidCapture(src, dest)) {
            throw new IllegalArgumentException("Illegal chess capture!");
        }
        // TODO: Finish the method.
    }

    public Cell[][] getGrid() {
        return grid;
    }

    public PlayerColor getChessPieceOwner(ChessboardPoint point) {
        return getGridAt(point).getPiece().getOwner();
    }

    public boolean isValidMove(ChessboardPoint src, ChessboardPoint dest) {
        //不能进自家兽穴
        if (getChessPieceAt(src).getOwner() == PlayerColor.BLUE && dest.getRow() == 8 && dest.getCol() == 3) {
            return false;
        }
        if (getChessPieceAt(src).getOwner() == PlayerColor.RED && dest.getRow() == 0 && dest.getCol() == 3) {
            return false;
        }
        //判断有无棋子（老鼠）在路径中间
        if (calculateDistance(src, dest) != 1 && src.getRow() == dest.getRow()) {
            for (int i = Math.min(src.getCol(), dest.getCol()) + 1; i < Math.max(src.getCol(), dest.getCol()); i++) {
                if (grid[src.getRow()][i].getPiece() != null) {
                    return false;
                }
            }
        }
        if (calculateDistance(src, dest) != 1 && src.getCol() == dest.getCol()) {
            for (int i = Math.min(src.getRow(), dest.getRow()) + 1; i < Math.max(src.getRow(), dest.getRow()); i++) {
                if (grid[i][src.getCol()].getPiece() != null) {
                    return false;
                }
            }
        }
        //返回能否move
        return getChessPieceAt(src).isValidMove(src, dest) && getChessPieceAt(src) != null;
        //c没有棋子或dest有棋子
//        if (getChessPieceAt(src) == null || getChessPieceAt(dest) != null) {
//            return false;
//        }
//        return calculateDistance(src, dest) == 1;
    }

    public boolean isValidCapture(ChessboardPoint src, ChessboardPoint dest) {
        // TODO:Fix this method
        return isValidMove(src, dest) && getChessPieceAt(src).canCapture(src, getChessPieceAt(dest));
    }

    public void enterChessIntoTrap(ChessboardPoint targetPoint) {
        getChessPieceAt(targetPoint).setRank(0);
    }

    public boolean blueWin() {
        for (int i = 0; i < Constant.CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum(); j++) {
                if (grid[i][j].getPiece()!=null&&grid[i][j].getPiece().getOwner()==PlayerColor.RED){
                    return false;
                }
            }
        }
        return true;
    }

    public boolean redWin() {
        for (int i = 0; i < Constant.CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum(); j++) {
                if (grid[i][j].getPiece()!=null&&grid[i][j].getPiece().getOwner()==PlayerColor.BLUE){
                    return false;
                }
            }
        }
        return true;
    }
}


