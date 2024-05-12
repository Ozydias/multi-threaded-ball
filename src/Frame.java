import javax.swing.*;
import java.awt.*;
import java.io.IOException;
/**
 * @author xwb
 */
public class Frame extends JFrame {
    private int frameWidth,frameHeight;
    private JPanel jPanel;
    public Frame(){
        super();
        init();
    }

    public void init(){
        this.frameWidth = 700;
        this.frameHeight = 800;
        this.setSize(frameWidth,frameHeight);
        this.setTitle("多线程弹球游戏");
        //this.getContentPane().setBackground(Color.white);

        this.setLocationRelativeTo(null);


        jPanel = new JPanel();
        jPanel.setBackground(Color.white);
        this.add(jPanel,BorderLayout.CENTER);

        Image cur = createImage(40,40);
        cur.getGraphics().fillRect(0,0,40,40);






        this.setDefaultCloseOperation(3);
        this.setVisible(true);
    }

    public static void main(String agrs[]) throws IOException{
        new Frame();
    }
}


