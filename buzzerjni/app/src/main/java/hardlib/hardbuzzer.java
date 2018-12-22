package hardlib;

public class hardbuzzer {
    public native int buzzerfdopen(String fdname);
    public native int buzzerioctl(int cmd);
    public native int buzzerfdclose();
}
