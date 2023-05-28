package controller;

import listener.GameListener;
import model.*;
import view.CellComponent;
import view.ChessComponent;
import view.ChessboardComponent;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Controller is the connection between model and view,
 * when a Controller receive a request from a view, the Controller
 * analyzes and then hands over to the model for processing
 * [in this demo the request methods are onPlayerClickCell() and onPlayerClickChessPiece()]
 */
public class GameController extends JFrame implements GameListener {


    private Chessboard model;
    private ChessboardComponent view;
    private PlayerColor currentPlayer;
    private int turn = 1;

    public PlayerColor getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(PlayerColor currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    // Record whether there is a selected piece before
    private ChessboardPoint selectedPoint;

    private JLabel statusLabel1;

    private JLabel statusLabel2;

    public JLabel getStatusLabel2() {
        return statusLabel2;
    }

    public void setStatusLabel2(JLabel statusLabel2) {
        this.statusLabel2 = statusLabel2;
    }

    public JLabel getStatusLabel1() {
        return statusLabel1;
    }

    private final Set<ChessboardPoint> riverCell = new HashSet<>();
    private final Set<ChessboardPoint> trapCell = new HashSet<>();
    private final Set<ChessboardPoint> denCell = new HashSet<>();

    public void setStatusLabel1(JLabel statusLabel1) {
        this.statusLabel1 = statusLabel1;
    }

    public GameController(ChessboardComponent view, Chessboard model) {
        this.view = view;
        this.model = model;
        this.currentPlayer = PlayerColor.BLUE;

        view.registerController(this);
        initialize();
        view.initiateChessComponent(model);
        view.repaint();
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

    private void initialize() {
        for (int i = 0; i < Constant.CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum(); j++) {

            }
        }
    }

    // after a valid move swap the player
    private void swapColor() {
        currentPlayer = currentPlayer == PlayerColor.BLUE ? PlayerColor.RED : PlayerColor.BLUE;
        statusLabel1.setText(currentPlayer.name());
    }

    private void Turn() {
        if (currentPlayer == PlayerColor.BLUE) {
            turn++;
            statusLabel2.setText("Turn: " + String.valueOf(turn));
        }
    }

    private void win() {
        // TODO: Check the board if there is a winner
        if (model.blueWin()) {
            blueWin();
        }
        if (model.redWin()) {
            redWin();
        }
    }


    // click an empty cell
    @Override
    public void onPlayerClickCell(ChessboardPoint point, CellComponent component) {
        if (selectedPoint != null && model.isValidMove(selectedPoint, point)) {
            model.moveChessPiece(selectedPoint, point);
            view.setChessComponentAtGrid(point, view.removeChessComponentAtGrid(selectedPoint));
            selectedPoint = null;
            swapColor();
            Turn();
            view.repaint();
            // TODO: if the chess enter Dens or Traps and so on
            if (trapCell.contains(point)) {
                model.enterChessIntoTrap(point);
            }
            if (denCell.contains(point)) {
                if (point.getRow() == 0) {
                    blueWin();
                } else {
                    redWin();
                }
            }
        }
    }

    private void redWin() {
        System.out.println("redd win!");
    }

    private void blueWin() {
        System.out.println("blue win!");
    }

    // click a cell with a chess
    @Override
    public void onPlayerClickChessPiece(ChessboardPoint point, ChessComponent component) {
        if (selectedPoint == null) {
            if (model.getChessPieceOwner(point).equals(currentPlayer)) {
                selectedPoint = point;
                component.setSelected(true);
                component.repaint();
            }
        } else if (selectedPoint.equals(point)) {
            selectedPoint = null;
            component.setSelected(false);
            component.repaint();
        }
        // TODO: Implement capture function
        else {
            if (model.isValidCapture(selectedPoint, point)) {
                //todo: capture chess process
//                selectedPoint = targetPoint;
                //(src->dest) 将targetPoint(第二个参数)位置设置成selectedPoint(第一个参数)位置的棋子，并移除src位置的棋子
                model.moveChessPiece(selectedPoint, point);
                //view层
                view.removeChessComponentAtGrid(point);
                ChessComponent source = view.removeChessComponentAtGrid(selectedPoint);
                view.setChessComponentAtGrid(point, source);
                selectedPoint = null;
                swapColor();
                view.repaint();
                win();
            }
        }

    }

    /*
1.model 清除所有棋子
2.model添加初始化的棋子
3.view 清除所有绘制过的棋子
4.view中，根据现阶段model的内容重新add棋子
5. view.repaint()
     */
    public void restartGame() {
        setCurrentPlayer(PlayerColor.BLUE);
        setTurn(0);
        Turn();
        model.removeAllPieces();
        model.initPieces();
        view.removeAllPieces();
        view.initiateChessComponent(model);
        view.repaint();
    }

    public void ChangeBackground() {
        view.changeBackground();
    }

    public void RegretGame() {

    }

    public void loadGameFromFile(String path) {
        try {
            List<String> lines = Files.readAllLines(Path.of(path));
            //TODO : checking wrong
            for (String s : lines) {
                System.out.println(s);
            }
            model.removeAllPieces();
            model.initPieces(lines);
            view.removeAllPieces();
            view.initiateChessComponent(model);
            view.repaint();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void writeFileFromGame(String path) {
        try {
            FileWriter fileWriter = new FileWriter(path);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            List<String> lines = this.convertToList();
            for (String line : lines) {
                writer.write(line);
                writer.write(System.lineSeparator());
            }
            writer.close();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> convertToList() {
        List<String> lines = new ArrayList<>();
        String s = "";
        for (int i = 0; i < Constant.CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum(); j++) {
                if (model.getChessPieceAt(new ChessboardPoint(i, j)) != null && model.getChessPieceAt(new ChessboardPoint(i, j)).getOwner() == PlayerColor.BLUE) {
                    if (model.getChessPieceAt(new ChessboardPoint(i, j)).getName().equals("Rat")) {
                        s += "a";
                    }
                    if (model.getChessPieceAt(new ChessboardPoint(i, j)).getName().equals("Cat")) {
                        s += "b";
                    }
                    if (model.getChessPieceAt(new ChessboardPoint(i, j)).getName().equals("Dog")) {
                        s += "c";
                    }
                    if (model.getChessPieceAt(new ChessboardPoint(i, j)).getName().equals("Wolf")) {
                        s += "d";
                    }
                    if (model.getChessPieceAt(new ChessboardPoint(i, j)).getName().equals("Leopard")) {
                        s += "e";
                    }
                    if (model.getChessPieceAt(new ChessboardPoint(i, j)).getName().equals("Tiger")) {
                        s += "f";
                    }
                    if (model.getChessPieceAt(new ChessboardPoint(i, j)).getName().equals("Lion")) {
                        s += "g";
                    }
                    if (model.getChessPieceAt(new ChessboardPoint(i, j)).getName().equals("Elephant")) {
                        s += "h";
                    }
                } else if (model.getChessPieceAt(new ChessboardPoint(i, j)) != null && model.getChessPieceAt(new ChessboardPoint(i, j)).getOwner() == PlayerColor.RED) {
                    if (model.getChessPieceAt(new ChessboardPoint(i, j)).getName().equals("Rat")) {
                        s += "1";
                    }
                    if (model.getChessPieceAt(new ChessboardPoint(i, j)).getName().equals("Cat")) {
                        s += "2";
                    }
                    if (model.getChessPieceAt(new ChessboardPoint(i, j)).getName().equals("Dog")) {
                        s += "3";
                    }
                    if (model.getChessPieceAt(new ChessboardPoint(i, j)).getName().equals("Wolf")) {
                        s += "4";
                    }
                    if (model.getChessPieceAt(new ChessboardPoint(i, j)).getName().equals("Leopard")) {
                        s += "5";
                    }
                    if (model.getChessPieceAt(new ChessboardPoint(i, j)).getName().equals("Tiger")) {
                        s += "6";
                    }
                    if (model.getChessPieceAt(new ChessboardPoint(i, j)).getName().equals("Lion")) {
                        s += "7";
                    }
                    if (model.getChessPieceAt(new ChessboardPoint(i, j)).getName().equals("Elephant")) {
                        s += "8";
                    }
                } else {
                    if (model.getChessPieceAt(new ChessboardPoint(i, j)) == null) {
                        s += '0';
                    }
                }
            }
            lines.add(s);
            s = "";
        }
        return lines;
    }


}
