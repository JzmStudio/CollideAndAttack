package Android;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.collideandattack.R;

import java.util.ArrayList;
import java.util.zip.Inflater;

import Interfaces.OnBackPressed;


public class AndroidMain extends Activity {
    private Bitmap screenBitmap;
    private int screenWidth;
    private int screenHeight;
    private RelativeLayout layout;
    private AndroidUpdateView updateView;
    private EditText editText;
    private AndroidStorage storage;
    private ArrayList<OnBackPressed> pressList; //返回键按下时的回调队列

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);
        screenWidth = metric.widthPixels;
        screenHeight = metric.heightPixels;

        /*各功能初始化*/
        storage=new AndroidStorage(this);
        pressList=new ArrayList<>(2);

        setContentView(R.layout.main_layout);
        updateView=new AndroidUpdateView(this);
        layout= (RelativeLayout) findViewById(R.id.relative);
        editText=(EditText)findViewById(R.id.editText);
        layout.removeAllViews();
        layout.addView(updateView,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));

        Log.e("mm","success");
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        updateView.resume();
        Log.d("mm","onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("mm", "pause");
        updateView.pause();
    }

    @Override
    protected void onStop()
    {
        super.onStop();
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

    @Override
    public void onBackPressed() {
        for (OnBackPressed o : pressList) {
            o.onBackPress(this);
        }
    }
    public void listenBackPress(OnBackPressed o) { pressList.add(o); }
    public void removeBackPress(OnBackPressed o) { pressList.remove(o); }

    public void addEditText()
    {
        layout.addView(editText);
    }
    public void addEditText(EditText editText, ViewGroup.LayoutParams layoutParams)
    {
        layout.addView(editText,layoutParams);
    }
}