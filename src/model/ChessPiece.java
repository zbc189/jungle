package model;


import java.util.HashSet;
import java.util.Set;

public abstract class ChessPiece {
    // the owner of the chess
    private PlayerColor owner;
    protected final Set<ChessboardPoint> riverCell = new HashSet<>();
    protected final Set<ChessboardPoint> trapCell = new HashSet<>();
    protected final Set<ChessboardPoint> denCell = new HashSet<>();

    // Elephant? Cat? Dog? ...
    private String name;
    protected int rank;
    protected String displayName;

    public ChessPiece(PlayerColor owner, String name) {
        this.owner = owner;
        this.name = name;
        riverCell.add(new ChessboardPoint(3, 1));
        riverCell.add(new ChessboardPoint(3, 2));
        riverCell.add(new ChessboardPoint(4, 1));
        riverCell.add(new ChessboardPoint(4, 2));
        riverCell.add(new ChessboardPoint(5, 1));
        riverCell.add(new ChessboardPoint(5, 2));

        riverCell.add(new ChessboardPoint(3, 4));
        riverCell.add(new ChessboardPoint(3, 5));
        riverCell.add(new ChessboardPoint(4, 4));
        riverCell.add(new ChessboardPoint(4, 5));
        riverCell.add(new ChessboardPoint(5, 4));
        riverCell.add(new ChessboardPoint(5, 5));

        denCell.add(new ChessboardPoint(0, 3));

        denCell.add(new ChessboardPoint(8, 3));

        trapCell.add(new ChessboardPoint(0, 2));
        trapCell.add(new ChessboardPoint(0, 4));
        trapCell.add(new ChessboardPoint(1, 3));

        trapCell.add(new ChessboardPoint(8, 2));
        trapCell.add(new ChessboardPoint(8, 4));
        trapCell.add(new ChessboardPoint(7, 3));
    }

    public String getDisplayName() {
        return displayName;
    }

    public boolean canCapture(ChessboardPoint src, ChessPiece target) {
        //TODO:Finish this method!
        if (riverCell.contains(src)) {
            return false;
        }
        if (target.getOwner() == this.getOwner()) {
            return false;
        }
        if (this.name.equals("Elephant") && target.name.equals("Rat")) {
            return false;
        }
        if (this.name.equals("Rat") && target.name.equals("Elephant")) {
            return true;
        }
        if (this.rank >= target.rank) {
            return true;
        }
        return false;
    }


    public String getName() {
        return name;
    }

    public PlayerColor getOwner() {
        return owner;
    }

    public abstract boolean isValidMove(ChessboardPoint src, ChessboardPoint target);

//    public abstract boolean isValidCapture(ChessPiece dest);

    public int calculateDistance(ChessboardPoint src, ChessboardPoint dest) {
        return Math.abs(src.getRow() - dest.getRow()) + Math.abs(src.getCol() - dest.getCol());
    }

    public void setRank(int i) {
        this.rank = i;
    }
}
