package Client;

public class ClientRun {
    public static void main(String[] args) {
        RMILoginClientView view = new RMILoginClientView();
        //RMILoginClientControl control = new RMILoginClientControl(view);
        view.setVisible(true);
    }
}