import java.awt.*;

/**
 * 多线程小球类
 * @author xwb
 */
public class Ball extends Thread{
    private Color color;
    private int x,y,r,vx,vy;
    private Frame frame;
    boolean isGameOver = true;
    double distX,distY,distance;


    /**
     * 构造函数
     * @param color
     * @param x
     * @param y
     * @param r
     * @param vx
     * @param vy
     * @param frame
     */
    public Ball(Color color,int x,int y,int r,int vx,int vy,Frame frame){
        this.color = color;
        this.x = x;
        this.y = y;
        this.r = r;
        this.vx = vx;
        this.vy = vy;
        this.frame = frame;
    }

    /**
     * 重写run方法
     */
    public void run(){
        super.run();

        while(!isOver()){
            ballMove();
            try{
                Thread.sleep(10);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            frame.repaint();
        }
    }

    /**
     * 判断游戏是否结束
     * @return
     */
    public boolean isOver(){
        distX = Math.abs(Frame.mouseX - x);
        distY = Math.abs(Frame.mouseY - y);
        distance = distX * distX + distY * distY;
        if(distance < 90 * 90 || Frame.mouseX >= 800 - 40 || Frame.mouseY >= 750 - 40 || Frame.mouseX <= 40 || Frame.mouseX <= 40){
            isGameOver = true;
            frame.setIsGameOver(isGameOver);
        }
        return isGameOver;
    }



    /**
     * 小球运动的方法
     */
    public void ballMove(){
        x += vx;
        y += vy;
        //小球在左右两个方向越界时，发生碰撞，向反方向运动
        if(x <= r || x >= frame.getWidth() - r){
            vx = -vx;
            if(x <= r){
                x = r;
            }else if(x > frame.getWidth() - r){
                x = frame.getWidth() - r;
            }
        }
        //小球在上下两个方向越界时，发生碰撞，向反方向运动
        if(y <= r || y >= frame.getHeight() - r){
            vy = -vy;
            if(y <= r){
                y = r;
            }else if(y > frame.getHeight() - r){
                y = frame.getHeight() - r;
            }
        }
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getVx() {
        return vx;
    }

    public void setVx(int vx) {
        this.vx = vx;
    }

    public int getVy() {
        return vy;
    }

    public void setVy(int vy) {
        this.vy = vy;
    }
}
