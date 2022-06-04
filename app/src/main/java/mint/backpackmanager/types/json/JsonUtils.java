package mint.backpackmanager.types.json;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.Scanner;

import mint.backpackmanager.MainActivity;

public class JsonUtils {
    public String getJSonStringFromFile(String path) {
        Scanner scanner;
        InputStream inputStream = MainActivity.getInstace().getFileHandle().inputStream(path);
        scanner = new Scanner(inputStream);
        String jason = scanner.useDelimiter("\\z").next();
        scanner.close();
        return jason;
    }

    public JSONObject getJSONObjectFromFile(String path) throws JSONException {
        return new JSONObject(getJSonStringFromFile(path));
    }

    public boolean onjectExists(JSONObject jsonObject, String key) {
        Object o;

        try {
            o = jsonObject.get(key);
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }

        return o != null;
    }
}
