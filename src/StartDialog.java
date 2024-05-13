import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * @author xwb
 */
public class StartDialog extends JFrame implements ActionListener{
    public static int ballNumber;
    private int dialogWidth = 300;
    private int dialogHeight = 180;
    private JLabel jLabel;

    private JTextField jTextField;
    private JButton jButton1;
    private JButton jButton2;
    public StartDialog(){
        this.setSize(dialogWidth,dialogHeight);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(true);

        this.setLayout(new FlowLayout(FlowLayout.CENTER,20,25));

        jLabel = new JLabel("初始化小球的数量：");
        jLabel.setForeground(Color.black);
        //jLabel.setFont(new Font("黑体",Font.PLAIN,14));

        this.add(jLabel);

        jTextField = new JTextField("5",10);
        this.add(jTextField);

        jButton1 = new JButton("开始");
        //jButton1.setFont(new Font("黑体",Font.PLAIN,14));
        jButton1.setBackground(Color.white);
        this.add(jButton1);


        jButton2 = new JButton("退出");
        //jButton2.setFont(new Font("黑体",Font.PLAIN,14));
        jButton2.setBackground(Color.WHITE);
        //jButton2.setBounds(100,200,100,100);
        this.add(jButton2);
        //jButton2.addActionListener(this);



        this.setVisible(true);
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource() == jButton1){
            ballNumber = Integer.parseInt(jTextField.getText());
            try{
                new Frame();
            }catch (IOException error){
                error.printStackTrace();
            }
        }else if(e.getSource() == jButton2){
            System.exit(EXIT_ON_CLOSE);
        }

        this.dispose();
    }





}
