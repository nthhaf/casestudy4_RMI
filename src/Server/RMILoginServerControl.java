package Server;

import Client.User;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class RMILoginServerControl extends UnicastRemoteObject implements RMILoginInterface {

    private int serverPort = 1234;
    private Registry registry;
    private Connection conn;
    private RMILoginServerView view;
    private String rmiService = "rmiLoginServer";

    public RMILoginServerControl(RMILoginServerView view) throws RemoteException {
        this.view = view;
        getDBConnection("users","root","root");
        try {
            //
            registry = LocateRegistry.createRegistry(serverPort);
            registry.rebind(rmiService, this);
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

    private boolean checkUser(User user){
        String sqlQuery = "SELECT * FROM users WHERE username ='" + user.getUsername() + "' AND password ='" + user.getPassword() +"'";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlQuery);
            if(rs.next()){
                System.out.println("OK OK OK ");
                return true;
            }
        } catch (Exception e) {
            view.showMessage(e.getStackTrace().toString());
        }
        return false;
    }
    public String checkLogin(User user) throws RemoteException{
        String result = "";
        if(checkUser(user)) result = "true";
        return result;
    }
}
