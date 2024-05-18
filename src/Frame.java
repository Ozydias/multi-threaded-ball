import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.TimerTask;
import java.util.Timer;


/**
 * 窗口类
 * @author xwb
 */
public class Frame extends JFrame {
    private int frameWidth, frameHeight;
    private JPanel jPanel;
    public static int mouseX = 350, mouseY = 350;
    Random random = new Random();
    private boolean isGameOver = false;
    private boolean isLastOver = false;
    private int count;
    ArrayList<Ball> list = new ArrayList<>();
    private Timer timer;
    private TimeCount timeCount;
    private int response;

    /**
     * 窗口类的构造函数
     *
     * @throws IOException
     */
    public Frame() throws IOException {
        super();
        this.frameWidth = 820;
        this.frameHeight = 750;
        this.setSize(frameWidth, frameHeight);
        this.setTitle("多线程弹球游戏");
        this.getContentPane().setBackground(Color.white);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        this.count = 0;
        jPanel = new JPanel();
        jPanel.setBackground(Color.WHITE);
        this.add(jPanel, BorderLayout.CENTER);
        this.setVisible(true);

        BufferedImage cur = new BufferedImage(40, 40, BufferedImage.TYPE_INT_ARGB);
        Graphics graphics2D = cur.createGraphics();
        graphics2D.setColor(Color.black);
        graphics2D.fillRect(0, 0, 40, 40);
        Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(cur, new Point(0, 0),"xwb");
        jPanel.setCursor(cursor);

        createBall(StartDialog.ballNumber);
        start();

        /**
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
            }

        });

    }


    /**
     * 创建多线程小球
     *
     * @param ballNumber
     */
    public void createBall(int ballNumber) {
        for (int i = 0; i < ballNumber; i++) {
            addBall();
        }
    }

    /**
     * 添加多线程小球方法
     */
    public void addBall() {
        Ball ball = new Ball(new Color(random.nextInt(255), random.nextInt(255),
                random.nextInt(255)), random.nextInt(750), random.nextInt(700),
                50, random.nextInt(5) + 1, random.nextInt(5) + 1, this);
        ball.start();
        list.add(ball);
    }

    /**
     * 重写paint方法
     * @param graphics the specified Graphics window
     */
    public void paint(Graphics graphics) {
        if (!isGameOver) {
            drawAll();
            double[][] dis = new double[list.size()][list.size()];
            for (int i = 0; i < list.size(); i++) {
                for (int j = 0; j < list.size(); j++) {
                    dis[i][j] = Math.sqrt(Math.pow(list.get(i).getX() - list.get(j).getX(),
                            2) + Math.pow(list.get(i).getY() - list.get(j).getY(), 2));
                }
            }
            for (int i = 0; i < list.size(); i++) {
                for (int j = i + 1; j < list.size(); j++) {
                    if (dis[i][j] < (list.get(i).getR() + list.get(j).getR()) / 2) {
                        int t;
                        t = list.get(i).getVx();
                        list.get(i).setVx(list.get(j).getVx());
                        list.get(j).setVx(t);
                        t = list.get(i).getVy();
                        list.get(i).setVy(list.get(j).getVy());
                        list.get(j).setVy(t);
                        int x2 = list.get(j).getX() - list.get(i).getX(), y2 = list.get(j)
                                .getY() - list.get(i).getY();
                        list.get(j).setX(list.get(i).getX() + x2);
                        list.get(j).setY(list.get(j).getY() + y2);
                    }
                }
            }
        }
    }

    public void drawAll() {
        Image image = createImage(frameWidth, frameHeight);
        Graphics graphics = image.getGraphics();
        graphics.setColor(Color.white);
        graphics.fillRect(0, 0, frameWidth, frameHeight);
        for (int i = 0; i < list.size(); i++) {
            graphics.setColor(list.get(i).getColor());
            graphics.fillOval(list.get(i).getX(), list.get(i).getY(), list.get(i).getR(), list.get(i).getR());
        }
        graphics.setColor(new Color(122, 122, 122));
        graphics.setFont(new Font("Serif", Font.BOLD, 30));
        graphics.drawString(timeCount.showTime(), 355,70);
        this.getGraphics().drawImage(image, 0, 0, null);

    }

    public void setIsGameOver(Boolean isGameOver) {
        this.isGameOver = isGameOver;
    }

    //计时线程启动
    public void start() {
        timeCount = new TimeCount();
        timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                if (!isGameOver) {
                    count++;
                    timeCount.update();
                    repaint();
                    if (count % 200 == 0) {
                        addBall();
                    }
                }
                if (isGameOver != isLastOver) {
                    isAgain();
                }
                isLastOver = isGameOver;
            }
        }, 0, 50);
    }

    public void isAgain() {
        response = JOptionPane.showConfirmDialog(this, "存活时间" + timeCount.showTime() + "秒", "GAMEOVER", JOptionPane.OK_CANCEL_OPTION);
        if (response == 0) {
            if (list.size() != 0) {
                list.removeAll(list);
                isGameOver = true;
                new StartDialog();
            }
        } else if (response == -1 || response == 1) {
            list.removeAll(list);
        } else {
            System.exit(0);
        }
    }

}


