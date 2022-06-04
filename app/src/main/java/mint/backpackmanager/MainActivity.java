package mint.backpackmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothSocket;
import android.os.Bundle;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import mint.backpackmanager.types.json.FileHandle;
import mint.backpackmanager.types.json.JsonConfiguration;
import mint.backpackmanager.types.json.JsonUtils;

public class MainActivity extends AppCompatActivity {

    private static MainActivity instace; //instance
    private FileHandle fileHandle; // file handle
    private JsonUtils jsonUtils; //json utils
    private JsonConfiguration jsonConfiguration;
    private boolean loaded = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //if the variables are already set it won't trigger if the variables aren't set it will initialise the vars.
        if (!loaded) {
            load();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStop() {

        super.onStop();
    }

    //loading method initialise variables
    private void load() {
        instace = this;
        fileHandle = new FileHandle();
        jsonUtils = new JsonUtils();
        //jsonConfiguration = new JsonConfiguration();


        loaded = true;
    }

    //getter and setter
    public static MainActivity getInstace() {
        return instace;
    }

    public FileHandle getFileHandle() {
        return fileHandle;
    }

    public JsonUtils getJsonUtils() {
        return jsonUtils;
    }

}