package Server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.Connection;
import java.sql.DriverManager;

public class RMILoginServerControl {

    private int serverPort = 1234;
    private Registry registry;
    private Connection conn;
    private RMILoginServerView view;
    private String rmService = "rmLoginServer";

    public RMILoginServerControl(RMILoginServerView view) throws RemoteException {
        this.view = view;
        getDBConnection("users","root","root");
        try {
            //
            registry = LocateRegistry.createRegistry(serverPort);
            registry.rebind(rmService,this);
        }catch (RemoteException e){
            throw e;
        }
    }

    private void getDBConnection(String dbName, String username, String password) {
        String url = "jdbc:mysql://localhost:3306/" + dbName;
        String dbClass = "com.mysql.cj.jdbc.Driver";

        try {
            Class.forName(dbClass);
            conn = DriverManager.getConnection(url,username,password);

        } catch(Exception e) {
            view.showMessage(e.getStackTrace().toString());
        }
    }
}
