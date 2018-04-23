package Android;

import android.util.Log;

import java.io.File;

public class AndroidStorage {
    private AndroidMain main;
    private File path;

    public AndroidStorage(AndroidMain main)
    {
        this.main=main;
        path=main.getFilesDir();
        Log.d("mm",path.getPath());
    }
}
