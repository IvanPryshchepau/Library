package by.tr.library.dao.pool;

import java.util.ResourceBundle;

/**
 * Created by Ivan on 16.07.2016.
 */
public class DBResourceManager {

    private final static DBResourceManager instance = new DBResourceManager();

    private ResourceBundle bundle = ResourceBundle.getBundle("db");

    public static DBResourceManager getInstance() {
        return instance;
    }

    public String getValue(String key) {
        return bundle.getString(key);
    }
}
