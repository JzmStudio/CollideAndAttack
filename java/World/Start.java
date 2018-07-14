package World;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;

import com.example.collideandattack.R;

import Components.ViewComponents.ScreenBitmapView;
import Components.ViewComponents.ScreenCircleView;
import Components.ViewComponents.ScreenRectView;
import Components.ViewComponents.ScreenTextView;
import Prefabs.GameObject;
import Scripts.BeginScreenScripts.SelectAction;

public class Start extends GameWorld {
    private GameObject bg;
    private GameObject start;
    private GameObject exit;
    public Start()
    {
        Log.d("GW","changed");
        bg=addGameObject(new GameObject());
        bg.addComponent(new ScreenBitmapView(bg, R.drawable.bg, Bitmap.Config.ARGB_8888,0,0,0));

        start=addGameObject(new GameObject());
        start.addComponent(new ScreenRectView(start,760,310,1,400,100,0xEDEDEDFF,10,true));
        start.addComponent(new ScreenTextView(start,"开始游戏",760,400,0, Color.BLACK,100,2));
        start.addComponent(new ScreenRectView(start,760,460,1,400,100,0xEDEDEDFF,10,true));
        start.addComponent(new ScreenTextView(start,"局域网",810,550,0,Color.BLACK,100,2));

        //exit
        start.addComponent(new ScreenRectView(start,760,610,1,400,100,0xEDEDEDFF,10,true));
        start.addComponent(new ScreenTextView(start,"退出",860,700,0,Color.BLACK,100,2));

        start.addComponent(new SelectAction(start));
    }
}
