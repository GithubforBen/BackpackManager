package mint.backpackmanager.types.json;

import android.util.JsonWriter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import mint.backpackmanager.MainActivity;

public class FileHandle {

    public InputStream inputStream(String path) {
        try {
            InputStream inputStream = FileHandle.class.getResourceAsStream(path);
            return inputStream;
        }catch (Exception e) {

        }
        return null;
    }

    public JSONObject createJSONFile(String path) {
        try {
            JSONObject jsonObject = MainActivity.getInstace().getJsonUtils().getJSONObjectFromFile(path);
            return jsonObject;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
