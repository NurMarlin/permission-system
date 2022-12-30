package net.playlegend.permission;

public class PermissionAPI {

    public static final String SYSTEM_PATH = "plugins/permission-system/";

    private static PermissionAPI instance;

    /**
     *
     */
    private PermissionAPI() { }

    /**
     *
     */
    public static void registerInstance() {
        if (instance == null) {
            instance = new PermissionAPI();
        }
    }

    /**
     *
     */
    public static void unregisterInstance() {
        instance = null;
    }

    /**
     * @return
     */
    public static PermissionAPI getInstance() {
        if (instance == null) {
            throw new IllegalStateException("API-Instance is not initialized");
        }
        return instance;
    }

}
