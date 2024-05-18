/**
 * @author xwb
 */
public class TimeCount {
    private Long initMsec;
    private Long curMsec;
    private Long res;

    public TimeCount(){
        initMsec = System.currentTimeMillis();
        res = 0L;
    }

    public void update(){
        curMsec = System.currentTimeMillis();
        res = curMsec - initMsec;
    }

    public String showTime(){
        String time = String.format("%.1f", res/1000.0);
        return time;
    }

}
