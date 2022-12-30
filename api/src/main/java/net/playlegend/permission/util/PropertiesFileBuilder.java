package net.playlegend.permission.util;

import lombok.Getter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

@Getter
public class PropertiesFileBuilder {

    private final File file;
    private final Properties properties;

    /**
     *
     * @param file
     */
    public PropertiesFileBuilder(File file) {
        this.file = file;
        this.properties = new Properties();

        if (file.exists()) {
            try {
                this.properties.load(new FileInputStream(file.getName()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @param key
     * @param value
     */
    public void addProperty(String key, String value) {
        this.properties.setProperty(key, value);
    }

    /**
     * @param key
     * @return
     */
    public String getProperty(String key) {
        return this.properties.getProperty(key);
    }

    /**
     *
     */
    public void save() {
        if (new File(file.getParent()).mkdirs()) {
            try {
                this.properties.store(new FileOutputStream(file.getPath()), null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
