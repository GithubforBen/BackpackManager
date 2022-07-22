package mint.backpackmanager;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import mint.backpackmanager.Utils.AddActivity;
import mint.backpackmanager.Utils.Bluetoothservice;
import mint.backpackmanager.Utils.PermissionHandler;
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
    private BluetoothServerSocket bluetoothServerSocket;
    private BluetoothSocket bluetoothSocket;
    private AddActivity addActivity;
    private PermissionHandler permissionHandler;

    //create method of the app calls all important listeners + initilises variables
    @RequiresApi(api = 31)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //sets the content to the normal view
        setContentView(R.layout.activity_main);
        //if the variables are already set it won't trigger if the variables aren't set it will initialise the vars.
        if (!loaded) {
            try {
                load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Toast.makeText(getBaseContext(), "Loaded!", Toast.LENGTH_LONG).show();
        }
        if (!loaded) {
            System.out.println("d");
            return;
        }

        //register events
        events();
        Toast.makeText(getBaseContext(), "Registrated!", Toast.LENGTH_LONG).show();

    }

    //stop methot not used
    @Override
    protected void onStop() {

        super.onStop();
    }

    //loading method initialise variables
    //@RequiresApi(api = Build.VERSION_CODES.Q)
    @RequiresApi(api = 31)
    private void load() throws IOException {
        instace = this;
        fileHandle = new FileHandle();
        jsonUtils = new JsonUtils();

        permissionHandler = new PermissionHandler();

        permissionHandler.gradPermissions();

        ADD = findViewById(R.id.addStuff);

        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

            Toast.makeText(getBaseContext(), "nope", Toast.LENGTH_LONG).show();
            return;
        }
        bluetoothServerSocket = bluetoothAdapter.listenUsingL2capChannel();
        bluetoothSocket = bluetoothServerSocket.accept();
        bluetoothservice = new Bluetoothservice();

        loaded = true;
    }

    //events initilises all the events
    private void events() {
        //click listener for the add activity button
        ADD.setOnClickListener(v -> {
            Toast.makeText(getBaseContext(), "hgjksjhdgfhjgsdjahgljk", Toast.LENGTH_LONG).show();
            setContentView(R.layout.add);
            Toast.makeText(getBaseContext(), "bvgljk", Toast.LENGTH_LONG).show();
            if (addActivity == null) {
                addActivity = new AddActivity();
            }
        });
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

    public BluetoothServerSocket getBluetoothServerSocket() {
        return bluetoothServerSocket;
    }

    public BluetoothSocket getBluetoothSocket() {
        return bluetoothSocket;
    }
}
