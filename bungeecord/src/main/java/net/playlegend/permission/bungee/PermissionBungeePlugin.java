package net.playlegend.permission.bungee;

import lombok.Getter;
import net.md_5.bungee.api.plugin.Plugin;
import net.playlegend.permission.PermissionAPI;
import net.playlegend.permission.database.MySQLConnection;
import net.playlegend.permission.database.MySQLStatement;

import java.util.concurrent.Executors;

/**
 * Currently an API test class
 */
@Getter
public class PermissionBungeePlugin extends Plugin {

    @Getter
    private static PermissionBungeePlugin instance;

    private MySQLConnection mySQLConnection;

    @Override
    public void onLoad() {
        PermissionAPI.registerInstance();
        instance = this;
    }

    @Override
    public void onEnable() {

        mySQLConnection = new MySQLConnection("localhost", 3306, "permission", "admin", "");
        mySQLConnection.connect().thenAccept(connection -> {
            System.out.println("Connected #" + connection);

            String sql = "CREATE TABLE IF NOT EXISTS Users (UUID VARCHAR(100), Name VARCHAR(100))";

            MySQLStatement statement = new MySQLStatement(connection, sql);
            Executors.newCachedThreadPool().submit(statement);

            System.out.println("CLASS=" + mySQLConnection.getConnection());
        });
    }

    @Override
    public void onDisable() {

        //disconnect mysql connection
        mySQLConnection.disconnect(mySQLConnection.getConnection());

        //unregister instances
        PermissionAPI.unregisterInstance();
        instance = null;
    }
}
