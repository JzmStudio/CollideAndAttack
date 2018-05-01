package Android;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.File;
import java.io.InputStream;

public class AndroidStorage {
    private AndroidMain main;
    private File path;
    private Resources resources;

    public AndroidStorage(AndroidMain main)
    {
        this.main=main;
        path=main.getFilesDir();
        Log.d("debug",path.getPath());
        resources=main.getResources();
    }

    public Bitmap getBitmapFromAssets(int id, Bitmap.Config config)
    {
        Bitmap bitmap;
        InputStream input=resources.openRawResource(id);
        if(config!=null)
        {
            BitmapFactory.Options options=new BitmapFactory.Options();
            options.inPreferredConfig=config;
            bitmap=BitmapFactory.decodeStream(input,null,options);
        }
        else
        {
            bitmap=BitmapFactory.decodeStream(input);
        }
        return bitmap;
    }
}