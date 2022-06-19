package mint.backpackmanager;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import mint.backpackmanager.Utils.AddActivity;
import mint.backpackmanager.Utils.Bluetoothservice;
import mint.backpackmanager.types.json.FileHandle;
import mint.backpackmanager.types.json.JsonConfiguration;
import mint.backpackmanager.types.json.JsonUtils;

public class MainActivity extends AppCompatActivity {

    private static MainActivity instace; //instance
    private FileHandle fileHandle; // file handle
    private JsonUtils jsonUtils; //json utils
    private JsonConfiguration jsonConfiguration;//jsonconfiguration
    private boolean loaded = false;
    private Button ADD, SHOW;
    private Bluetoothservice bluetoothservice;
    private BluetoothAdapter bluetoothAdapter;
    private AddActivity addActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //if the variables are already set it won't trigger if the variables aren't set it will initialise the vars.
        if (!loaded) {
            load();
            Toast.makeText(getBaseContext(), "LOL", Toast.LENGTH_LONG).show();
        }
        if (!loaded) {
            System.out.println("d");
            return;
        }

        //register events
        events();
        Toast.makeText(getBaseContext(), "GG", Toast.LENGTH_LONG).show();

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

        ADD = findViewById(R.id.addStuff);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        bluetoothservice = new Bluetoothservice();

        loaded = true;
    }

    private void events() {
        ADD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.add);
                if (addActivity == null) {
                    addActivity = new AddActivity();
                }
            }
        });
        //bluetoothOFF();
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

    public AddActivity getAddActivity() {
        return addActivity;
    }

    public Bluetoothservice getBluetoothservice() {
        return bluetoothservice;
    }

    public BluetoothAdapter getBluetoothAdapter() {
        return bluetoothAdapter;
    }
}