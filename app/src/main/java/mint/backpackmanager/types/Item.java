package mint.backpackmanager.types;

import org.json.JSONException;

import java.io.File;

public interface Item {
    //contains the name
    public String name() throws JSONException;
    //contains if it is inside or outside
    public boolean inside() throws JSONException;
}
