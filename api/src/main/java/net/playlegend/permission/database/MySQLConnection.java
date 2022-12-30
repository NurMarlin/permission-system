package net.playlegend.permission.database;

import lombok.Getter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.CompletableFuture;

@Getter
public class MySQLConnection {

    private final String mySQL_driver = "com.mysql.cj.jdbc.Driver";
    private final String mySQL_url_prefix = "jdbc:mysql://";

    private Connection connection;

    private final String host;
    private final int port;
    private final String database;
    private final String username;
    private final String password;

    /**
     * @param host
     * @param port
     * @param database
     * @param username
     * @param password
     */
    public MySQLConnection(String host, int port, String database, String username, String password) {
        this.host = host;
        this.port = port;
        this.database = database;
        this.username = username;
        this.password = password;
    }

    /**
     * @return
     */
    public CompletableFuture<Connection> connect() {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Class.forName(mySQL_driver);
                String url = mySQL_url_prefix + host + ":" + port + "/" + database;
                return this.connection = DriverManager.getConnection(url, username, password);
            } catch (ClassNotFoundException | SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }

    /**
     * @param connection
     * @return
     */
    public CompletableFuture<Void> disconnect(Connection connection) {
        return CompletableFuture.runAsync(() -> {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
