package sdgp.azure.routeapp;

import android.os.AsyncTask;
import java.sql.*;

public class CloudDataHandler {

    private class InnerPush extends AsyncTask<String, Void, String>{
    public String doInBackground(String... params) {
        String connectionString
                = "jdbc:sqlserver://sdgpsqldb.database.windows.net:1433;"
                + "database=sdgplocationdb;user=dbadmin@sdgpsqldb;password="
                + "Smarthelmetv1;encrypt=true;trustServerCertificate="
                + "false;hostNameInCertificate=*.database.windows.net;loginTim"
                + "eout=30;";

        // Declare the JDBC objects.
        try{
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
        } catch (ClassNotFoundException e){
            System.err.println("Class not found");
        }
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        if ((params[0] == null)||(params[1] == null)) {
            System.err.println("data is null");
            System.exit(0);
        }

        try {
            connection = DriverManager.getConnection("jdbc:jtds:sqlserver://appmaskdb.database." +
                    "windows.net:1433;instance=SQLEXPRESS;DatabaseName=sdgplocationdb;",
                    "dbadmin@sdgpsqldb", "Smarthelmetv1");

            String sql_c = "CREATE TABLE locations(loc varchar(100),"
                    + "dest varchar(100),"
                    + "PRIMARY KEY(loc, dest));";
            String sql_d = "DROP TABLE locations;";
            String sql_i = "INSERT INTO locations (loc, dest) "
                    + "VALUES ('" + params[0] + "','"+params[1]+"');";
            String sql_u = "UPDATE locations "
                    + "SET loc='" + params[0] + "',"
                    + "   dest='" + params[1] + "' "
                    + "WHERE loc = 'loc' AND dest = 'dest';";
            String sql_s = "SELECT * FROM locations;";
            statement = connection.createStatement();
            String sql = sql_u;
            statement.execute(sql);
            sql = sql_s;
            resultSet = statement.executeQuery(sql);

            // Print results from select statement
            while (resultSet.next()) {
                return (resultSet.getString(1) + " "
                        + resultSet.getString(2));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the connections after the data has been handled.
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (Exception e) {
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (Exception e) {
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (Exception e) {
                }
            }
        }
        return "";
    }}
}