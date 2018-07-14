package World;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;

import com.example.collideandattack.R;

import Components.ViewComponents.ScreenBitmapView;
import Components.ViewComponents.ScreenTextView;
import Prefabs.GameObject;

public class Start extends GameWorld {
    private GameObject bg;
    private GameObject start;
    public Start()
    {
        Log.d("GW","changed");
        bg=addGameObject(new GameObject());
        bg.addComponent(new ScreenBitmapView(bg, R.drawable.bg, Bitmap.Config.ARGB_8888,0,0,0));

        start=addGameObject(new GameObject());
        start.addComponent(new ScreenTextView(start,"开始游戏",900,600,0, Color.BLACK,100,1));
    }
}
