import controller.GameController;
import model.Chessboard;
//import view.Begin;
import view.ChessGameFrame;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void changebackground(){

    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ChessGameFrame mainFrame = new ChessGameFrame(1100, 810);

            GameController gameController = new GameController(mainFrame.getChessboardComponent(), new Chessboard());
            mainFrame.setGameController(gameController);
            gameController.setStatusLabel1(mainFrame.getStatusLabel1());
            gameController.setStatusLabel2(mainFrame.getStatusLabel2());
            mainFrame.setVisible(true);
            Music audioPlayWave = new Music("resource/BGM.wav");// 开音乐(冒号里的内容与音乐文件名一致)
            audioPlayWave.start();
            @SuppressWarnings("unused")
            int musicOpenLab = 1;
//            Begin beginFrame = new Begin();
//            beginFrame.setVisible(true);
        });
    }
}
