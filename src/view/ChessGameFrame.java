package view;

import controller.GameController;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * 这个类表示游戏过程中的整个游戏界面，是一切的载体
 */
public class ChessGameFrame extends JFrame {
    //    public final Dimension FRAME_SIZE ;
    private final int WIDTH;
    private final int HEIGHT;


    private final int ONE_CHESS_SIZE;

    private ChessboardComponent chessboardComponent;
    private GameController controller;


    private JLabel statusLabel1;
    private JLabel statusLabel2;


    public JLabel getStatusLabel2() {
        return statusLabel2;
    }

    public void setStatusLabel2(JLabel statusLabel2) {
        this.statusLabel2 = statusLabel2;
    }

    public ChessGameFrame(int width, int height) {
        setTitle("2023 CS109 Project Demo");//设置标题
        this.WIDTH = width;
        this.HEIGHT = height;
        this.ONE_CHESS_SIZE = (HEIGHT * 4 / 5) / 9;
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null); // Center the window.
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); //设置程序关闭按键，如果点击右上方的叉就游戏全部关闭了
        setLayout(null);
        addChessboard();
        addLabel();
        initImage();
       // backgroundTry();
        addRestartButton();
        addLoadButton();
        addSaveButton();
        addUndoButton();
        //addChangeBackgroundButton();
        Image image=new ImageIcon("resource/img.png").getImage();
        image=image.getScaledInstance(1100,810,Image.SCALE_DEFAULT);
        ImageIcon icon=new ImageIcon(image);
        JLabel background=new JLabel(icon);
        background.setSize(1100,810);
        background.setLocation(0,0);
        add(background);
    }

    private void addChangeBackgroundButton() {
        JButton ChangeBackgroundButton = new JButton("changeBackground");
        ChangeBackgroundButton.addActionListener((e) -> {
            JOptionPane.showMessageDialog(this, "Let us change the background!");
            controller.ChangeBackground();
        });
        ChangeBackgroundButton.setLocation(HEIGHT, HEIGHT / 10 + 440);
        ChangeBackgroundButton.setSize(250, 60);
        ChangeBackgroundButton.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(ChangeBackgroundButton);
    }
    public ChessboardComponent getChessboardComponent() {
        return chessboardComponent;
    }

    public void setChessboardComponent(ChessboardComponent chessboardComponent) {
        this.chessboardComponent = chessboardComponent;
    }

    /**
     * 在游戏面板中添加棋盘
     */
    private void addChessboard() {
        chessboardComponent = new ChessboardComponent(ONE_CHESS_SIZE);
        chessboardComponent.setLocation(HEIGHT / 5, HEIGHT / 10);
        add(chessboardComponent);
    }
    /*private void backgroundTry(){
        ImageIcon img=new ImageIcon(".resource/img.png");
        JLabel Label = new JLabel(img);
        this.getLayeredPane().setLayout(null);
        Label.setBounds(0,0,300,300);
        this.getLayeredPane().add(Label ,  Integer.MIN_VALUE);
        JButton button= new JButton("左因子");
        this.getContentPane().setLayout(new FlowLayout());
        JPanel panel=(JPanel) this.getContentPane();
        panel.add(button);
       // panel.setOpaque(false);
        this.setVisible(true);
    }
*/


    private void initImage(){
        BufferedImage img1 = null;
        BufferedImage img2 = null;
        //Image img3 = null;
        try{
            img1= ImageIO.read(new File("./resource/Elephant-red.png"));
            img2= ImageIO.read(new File("./resource/Elephant-blue.png"));
            //img3= ImageIO.read(new File("./resource/img.png"));
        }
        catch (IOException e ){
            e.printStackTrace();
        }
        getContentPane().setLayout(null);
        JLabel jLabel1 =new JLabel( new ImageIcon(img1));
        JLabel jLabel2 =new JLabel( new ImageIcon(img2));
       // JLabel jLabel3 =new JLabel( new ImageIcon(img3));
        jLabel1.setSize(150, 300);
        jLabel2.setSize(150, 300);
      // jLabel3.setBounds(-300,-1000, 18000,30000);//TODO
         //jLabel3.setSize(3000, 3000);
        jLabel1.setLocation(0,0);
        jLabel2.setLocation(0,500);
       // jLabel3.setLocation(-1000,-1000);
        getContentPane().add(jLabel1);
        getContentPane().add(jLabel2);
       // getContentPane().add(jLabel3);
    }
    /**
     * 在游戏面板中添加标签
     */
    private void addLabel() {
        statusLabel1 = new JLabel("Blue");
        statusLabel1.setLocation(HEIGHT, HEIGHT / 10);
        statusLabel1.setSize(200, 60);
        statusLabel1.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(statusLabel1);
        statusLabel2=new JLabel("Turn: 1");
        statusLabel2.setLocation(HEIGHT,HEIGHT/15);
        statusLabel2.setSize(200,60);
        statusLabel2.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(statusLabel2);
    }

    public JLabel getStatusLabel1() {
        return statusLabel1;
    }

    public void setStatusLabel1(JLabel statusLabel1) {
        this.statusLabel1 = statusLabel1;
    }

    /**
     * 在游戏面板中增加一个按钮，如果按下的话就会显示Hello, world!
     */

    private void addRestartButton() {
        //lxx
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("resource\\restart-button.tif"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        JButton RestartButton = new JButton(new ImageIcon(image));
        RestartButton.addActionListener((e) -> {
            JOptionPane.showMessageDialog(this, "Let us restart the game!");
            controller.restartGame();
        });
        RestartButton.setLocation(HEIGHT, HEIGHT / 10 + 120);
        RestartButton.setSize(200, 60);
        RestartButton.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(RestartButton);
    }
        private void addUndoButton() {
            //lxx
            BufferedImage image = null;
            try {
                image = ImageIO.read(new File("resource\\regret-button.tif"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            JButton UndoButton = new JButton(new ImageIcon(image));

            UndoButton.addActionListener((e) -> {
            int result = JOptionPane.showConfirmDialog(null, "Are you sure ?", "Confirm", JOptionPane.YES_NO_CANCEL_OPTION);
           if(result==JOptionPane.YES_OPTION){
               controller.UndoGame();
           }
        });
        UndoButton.setLocation(HEIGHT, HEIGHT / 10 + 360);
        UndoButton.setSize(200, 60);
        UndoButton.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(UndoButton);
    }



    private void addLoadButton() {
        //lxx
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("resource\\load-button.tif"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        JButton LoadButton = new JButton(new ImageIcon(image));
        LoadButton.setLocation(HEIGHT, HEIGHT / 10 + 200);
        LoadButton.setSize(200, 60);
        LoadButton.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(LoadButton);
        LoadButton.addActionListener(e -> {
            System.out.println("Click load");
            String path = JOptionPane.showInputDialog(this, "Input Path here");
            System.out.println(path);
            controller.loadGameFromFile(path);
        });
    }
    private void addSaveButton() {
        //lxx
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("resource\\save-button.tif"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JButton SaveButton = new JButton(new ImageIcon(image));
        SaveButton.setLocation(HEIGHT, HEIGHT / 10 + 280);
        SaveButton.setSize(200, 60);
        SaveButton.setFont(new Font("Rockwell", Font.BOLD, 20));
        add(SaveButton);
        SaveButton.addActionListener(e -> {
            System.out.println("Click load");
            String path = JOptionPane.showInputDialog(this, "Input Path here");
            System.out.println(path);
            controller.writeFileFromGame(path);
        });
    }
    public void setGameController(GameController gameController) {
        this.controller = gameController;
    }

}
