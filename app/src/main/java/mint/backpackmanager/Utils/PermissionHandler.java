package mint.backpackmanager.Utils;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.widget.Toast;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import mint.backpackmanager.MainActivity;

public class PermissionHandler {

    /*
    This class is used to grand all necessary permissions for the Android app.
     */

    private ActivityResultLauncher<String[]> mPermissonResultLauncher;
    private boolean isBluetoothPermissionConnectGranted = false;

    @RequiresApi(api = Build.VERSION_CODES.S)
    public void gradPermissions() {
        setBooleans();
        setmPermissonResultLauncher();

        List<String> permissionRequest = new ArrayList<>();

        if (!isBluetoothPermissionConnectGranted) {
            permissionRequest.add(Manifest.permission.BLUETOOTH_CONNECT);
        }

        if (permissionRequest.isEmpty()) {
            return;
        }

        mPermissonResultLauncher.launch(permissionRequest.toArray(new String[0]));
    };

    @RequiresApi(api = Build.VERSION_CODES.S)
    private void setBooleans() {
        isBluetoothPermissionConnectGranted = ContextCompat.checkSelfPermission(MainActivity.getInstace(), Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED;
    }

    private void setmPermissonResultLauncher() {
        mPermissonResultLauncher = MainActivity.getInstace().registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), new ActivityResultCallback<Map<String, Boolean>>() {
            @RequiresApi(api = Build.VERSION_CODES.S)
            @Override
            public void onActivityResult(Map<String, Boolean> result) {

                if (result.get(Manifest.permission.BLUETOOTH_CONNECT) != null) {
                    isBluetoothPermissionConnectGranted = true;
                }else {
                    Toast.makeText(MainActivity.getInstace().getBaseContext(), "Permission " + Manifest.permission.BLUETOOTH_CONNECT + " could not be applied!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public ActivityResultLauncher<String[]> getmPermissonResultLauncher() {
        return mPermissonResultLauncher;
    }

    public boolean isBluetoothPermissionConnectGranted() {
        return isBluetoothPermissionConnectGranted;
    }
}
