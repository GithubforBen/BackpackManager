package mint.backpackmanager.types.json;

import android.os.Environment;
import android.util.Base64;
import android.util.JsonWriter;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
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

    public File createJSONFile(String name, JSONObject jsonObject) {
        String state = Environment.getExternalStorageState();
        if (!Environment.MEDIA_MOUNTED.equals(state)) {
            return null;
        }

        File file = new File(MainActivity.getInstace().getExternalFilesDir("backpack/json/..."), name);

        FileOutputStream fileOutputStream = null;

        try {
            file.createNewFile();
            Toast.makeText(MainActivity.getInstace().getBaseContext(), file.getPath(), Toast.LENGTH_SHORT).show();
            fileOutputStream = new FileOutputStream(file, true);

            //string coud have another charset using getBytes(Charset)
            fileOutputStream.write(jsonObject.toString().getBytes());

            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    public JSONObject getFromByte(byte[] bytes) throws JSONException {
        JSONObject jsonObject = new JSONObject(new String(bytes));

        return jsonObject;
    }

    public File getFromByteFile(byte[] bytes) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        File file = new File(new String(bytes));
        return file;
    }
}
