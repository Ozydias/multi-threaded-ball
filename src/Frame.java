import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * 窗口类
 * @author xwb
 */
public class Frame extends JFrame {
    private int frameWidth,frameHeight;
    private JPanel jPanel;
    public static int mouseX,mouseY;
    Random random = new Random();
    private boolean isGameOver = true;

    private int count = 0;

    List<Ball> list = new ArrayList<>();

    /**
     * 窗口类的构造函数
     * @throws IOException
     */
    public Frame() throws IOException{
        super();
        init();
    }

    /**
     * 窗口的初始化方法
     */
    private void init(){
        this.frameWidth = 800;
        this.frameHeight = 750;
        this.setSize(frameWidth,frameHeight);
        this.setTitle("多线程弹球游戏");
        //this.getContentPane().setBackground(Color.white);

        this.setLocationRelativeTo(null);


        jPanel = new JPanel();
        jPanel.setBackground(Color.WHITE);
        this.add(jPanel,BorderLayout.CENTER);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);

        BufferedImage cur = new BufferedImage(40,40,BufferedImage.TYPE_INT_ARGB);

        Graphics graphics2D = cur.createGraphics();
        graphics2D.setColor(Color.black);
        graphics2D.fillRect(0,0,40,40);
        graphics2D.dispose();

        Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(cur,new Point(0,0),"A");
        jPanel.setCursor(cursor);


        createBall(StartDialog.ballNumber);
        repaint();;
        //start();

        /**
         *
         * 使用内部类
         */
        addMouseMotionListener(new MouseMotionAdapter() {
//            @Override
//            public void mouseDragged(MouseEvent e) {
//                super.mouseDragged(e);
//                mouseX = e.getX();
//                mouseY = e.getY();
//            }
            @Override
            public void mouseMoved(MouseEvent e) {
                super.mouseMoved(e);
                mouseX = e.getX();
                mouseY = e.getY();
                System.out.println("(" + mouseX + "," + mouseY + ")");

            }

        });

        JLabel jLabel = new JLabel("0");
        jLabel.setBackground(Color.white);
        jLabel.setBounds(400,0,10,2);
        jPanel.add(jLabel);

        Timer timer = new Timer(1000, new ActionListener() {
            int count = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
                jLabel.setText(count++ + " s");
            }
        });

        timer.start();

    }


    /**
     * 创建多线程小球
     * @param ballNumber
     */
    public void createBall(int ballNumber){
        for(int i=0;i<ballNumber;i++){
            addBall();
        }
    }

    /**
     * 添加多线程小球方法
     */
    public void addBall(){
        Ball ball = new Ball(new Color(random.nextInt(255),random.nextInt(255),
                random.nextInt(255)),random.nextInt(750),random.nextInt(700),
                30,random.nextInt(3)+ 1,random.nextInt(3)+1,this);
        ball.start();;
        list.add(ball);
    }

    /**
     * 重写paint方法
     * @param graphics the specified Graphics window
     */
    public void paint(Graphics graphics){
        if(!isGameOver){
            drawAll();
        }
    }

    public void drawAll(){
        Image image = createImage(frameWidth,frameHeight);
        Graphics graphics = image.getGraphics();
        graphics.setColor(Color.white);
        graphics.fillRect(0,0,frameWidth,frameHeight);
        for(int i=0;i<list.size();i++){
            graphics.setColor(list.get(i).getColor());
            graphics.fillOval(list.get(i).getX(),list.get(1).getY(),list.get(i).getR(),list.get(i).getR());
        }
        graphics.setColor(new Color(123,123,123));
        graphics.setFont(new Font("Serif",Font.PLAIN,30));
        this.getGraphics().drawImage(image,0,0,null);

    }

    public void setIsGameOver(Boolean isGameOver){
        this.isGameOver = isGameOver;
    }

}


