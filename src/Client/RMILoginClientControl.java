package Client;

import Server.RMILoginInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMILoginClientControl {
    private RMILoginClientView view;
    private String serverHost = "localhost";
    private int serverPort = 1234;
    private RMILoginInterface rmServer;
    private Registry registry;
    private String rmService = "rmLoginServer";

    public RMILoginClientControl(RMILoginClientView view){
        this.view = view;
        view.addLoginListener(new LoginListener());
        try {
            registry = LocateRegistry.getRegistry(serverHost,serverPort);
            rmServer =(RMILoginInterface) registry.lookup(rmService);
        }catch (RemoteException e){
            view.showMessage(e.getStackTrace().toString());
            e.printStackTrace();
        }catch (NotBoundException e){
            view.showMessage(e.getStackTrace().toString());
            e.printStackTrace();
        }
    }

    class LoginListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                User user = view.getUser();
                if(rmServer.checkLogin(user).equals("true")){
                    view.showMessage("Login Successfully!");
                }else {
                    view.showMessage("Invalid username and/or password!");
                }
            } catch (Exception ex){
                view.showMessage(ex.getStackTrace().toString());
                ex.printStackTrace();
            }
        }
    }
}
