package Android;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.collideandattack.R;


public class AndroidMain extends Activity {
    private Bitmap screenBitmap;
    private int screenWidth;
    private int screenHeight;
    private ViewGroup viewGroup;
    private AndroidUpdateView updateView;
    private AndroidStorage storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        screenWidth = metric.widthPixels;
        screenHeight = metric.heightPixels;

        storage=new AndroidStorage(this);

        updateView=new AndroidUpdateView(this);
        //viewGroup = (ViewGroup) findViewById(R.layout.viewgroup);
        viewGroup= (ViewGroup)LayoutInflater.from(this).inflate(R.layout.viewgroup,null);
        if(viewGroup==null)
        {
            Log.e("mm","null");
            return;
        }
        Log.e("mm","success");
        viewGroup.addView(updateView,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        setContentView(viewGroup);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        updateView.resume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        updateView.pause();
    }

    public AndroidUpdateView getUpdateView() {
        return updateView;
    }

    public int getScreenWidth()
    {
        return screenWidth;
    }
    public int getScreenHeight()
    {
        return screenHeight;
    }
}
