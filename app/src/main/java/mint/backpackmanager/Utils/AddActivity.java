package mint.backpackmanager.Utils;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import mint.backpackmanager.MainActivity;
import mint.backpackmanager.R;
import mint.backpackmanager.types.Item;

public class AddActivity {

    private Button send;
    private FloatingActionButton floatingActionButton;
    private EditText editText;

    public AddActivity() {
        send = MainActivity.getInstace().findViewById(R.id.add_done);
        editText = (EditText) MainActivity.getInstace().findViewById(R.id.add_text);
        floatingActionButton = MainActivity.getInstace().findViewById(R.id.add_back);
        start();
    }

    public void start() {
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.getInstace().setContentView(R.layout.activity_main);
            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Item i = new Item() {
                    @Override
                    public String name() throws JSONException {
                        return editText.getText().toString();
                    }

                    @Override
                    public boolean inside() throws JSONException {
                        return false;
                    }

                };
                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("add_name", i.name());
                    jsonObject.put("add_inside", i.inside());
                    jsonObject.put("add_manifest", "add_name,add_inside");
                    jsonObject.put("add_sense", "add_name = the name of the new thing in the backpack, add_inside = always false used to show if the item is in the backpack");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                File file = MainActivity.getInstace().getFileHandle().createJSONFile(editText.getText().toString(), jsonObject);

                try {
                    MainActivity.getInstace().getBluetoothservice().send(bytes(file));
                    file.delete();
                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(MainActivity.getInstace().getBaseContext(), "ERROR:" + e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private byte[] bytes(File file) throws IOException{
        FileInputStream fileInputStream = new FileInputStream(file);

        byte[] arr = new byte[(int) file.length()];

        fileInputStream.read(arr);

        fileInputStream.close();
        return arr;
    }
}
