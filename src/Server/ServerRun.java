package Server;

public class ServerRun {
    public static void main(String[] args) {
        RMILoginServerView view = new RMILoginServerView();
        try {
            RMILoginServerControl controller = new RMILoginServerControl(view);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
