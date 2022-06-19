package mint.backpackmanager.types.json;

import org.json.JSONObject;

import java.io.File;

import mint.backpackmanager.MainActivity;

public class JsonConfiguration {
    private File file;
    private JSONObject jsonObject;

    public JsonConfiguration() {
        file = new File("tmp/conf1.json");
    }

    public File getFile() {
        return file;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }
}
