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
    private RMILoginInterface rmiServer;
    private Registry registry;
    private String rmiService = "rmiLoginServer";

    public RMILoginClientControl(RMILoginClientView view){
        this.view = view;
        view.addLoginListener(new LoginListener());
        try {
            registry = LocateRegistry.getRegistry(serverHost,serverPort);
            rmiServer =(RMILoginInterface) registry.lookup(rmiService);
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
                if(rmiServer.checkLogin(user).equals("true")){
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
