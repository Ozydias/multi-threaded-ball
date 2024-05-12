import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.IOException;
/**
 * @author xwb
 */
public class Frame extends JFrame {
    private int frameWidth,frameHeight;
    private JPanel jPanel;
    public static int mouseX,mouseY;
    public Frame() throws IOException{
        super();
        init();
    }

    private void init(){
        this.frameWidth = 800;
        this.frameHeight = 750;
        this.setSize(frameWidth,frameHeight);
        this.setTitle("多线程弹球游戏");
        //this.getContentPane().setBackground(Color.white);

        this.setLocationRelativeTo(null);


        jPanel = new JPanel();
        jPanel.setBackground(Color.white);
        this.add(jPanel,BorderLayout.CENTER);

        this.setDefaultCloseOperation(3);
        this.setVisible(true);


//        Image cur = createImage(40,40);
//        cur.getGraphics().fillRect(0,0,40,40);
//
//        Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(cur,new Point(0,0),"A");
//        jPanel.setCursor(cursor);
        BufferedImage bufferedImage = new BufferedImage(40,40,BufferedImage.TYPE_INT_ARGB);

        Graphics graphics2D = bufferedImage.createGraphics();
        graphics2D.setColor(Color.black);
        graphics2D.fillRect(0,0,40,40);
        graphics2D.dispose();

        Cursor cursor = Toolkit.getDefaultToolkit().createCustomCursor(bufferedImage,new Point(0,0),"A");
        jPanel.setCursor(cursor);


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

    public void createBall(int sum){
        for(int i=0;i<sum;i++){
            addBall();
        }
    }
    public void addBall(){

    }


    public static void main(String agrs[]) throws IOException{
        new Frame();
    }
}


