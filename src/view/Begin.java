//package view;
//
//import controller.GameController;
//import model.Chessboard;
//
//import javax.swing.*;
//import java.awt.*;
//
//public class Begin extends JFrame {
//    private final int WIDTH = 400;
//    private final int Height = 300;
//
//    ChessGameFrame mainFrame = new ChessGameFrame(1100, 810);
//    GameController gameController = new GameController(mainFrame.getChessboardComponent(), new Chessboard());
//
//    public Begin() {
//        // 设置窗口标题
//        setTitle("Begin the Game ");
//
//        // 设置窗口大小
//        setSize(4*WIDTH, 3*Height);
//
//
//        Image image = new ImageIcon("resource/1ade3a915bf496ee7f31cda1933b239d.jpeg").getImage();
//        image = image.getScaledInstance(800, 600,Image.SCALE_DEFAULT);
//        ImageIcon icon = new ImageIcon(image);
//        JLabel backGround = new JLabel(icon);
//        backGround.setSize(800, 600);
//        backGround.setLocation(0, 0);
//        add(backGround);
//
//        addBeginButton();
//        // 关闭窗口时退出程序
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    }
//    private void addBeginButton() {
//        JButton button = new JButton("Begin the game");
//        button.setLocation(0, 0);
//        button.setSize(WIDTH/4, Height/4);
//        button.setFont(new Font("Rockwell", Font.BOLD, 20));
//        add(button);
//        button.addActionListener(e -> {
//            this.setVisible(false);
//            mainFrame.setVisible(true);
//        });
//    }
//}
//
