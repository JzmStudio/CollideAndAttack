package World;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;

import com.example.collideandattack.R;

import Bases.Position;
import Components.PhysicsComponents.Transform;
import Components.ViewComponents.BitmapView;
import Components.ViewComponents.ScreenBitmapView;
import Components.ViewComponents.ScreenRectView;
import Prefabs.GameObject;
import Scripts.FontScript;
import Scripts.StartTriangle;
import Systems.SystemManager;

/**
 * 所有游戏场景都从StartGameWorld类开始
 */
public class StartGameWorld extends GameWorld {
    GameObject bg;
    GameObject g2;
    GameObject cemera;
    GameObject begin;
    public StartGameWorld()
    {
        bg=new GameObject();
        bg.addComponent(new ScreenRectView(bg,0f,0f,0,1920,1080, 0xFFFAFAFA,0,true));
        g2=new GameObject(new Position(1200,1200,-90));
        BitmapView bitmapView=new BitmapView(g2,R.drawable.anim0,Bitmap.Config.ARGB_8888,new Position(),1);
        bitmapView.position.point.x=-bitmapView.bitmap.getWidth()/2;
        bitmapView.position.point.y=bitmapView.bitmap.getHeight()/2;
        g2.addComponent(bitmapView);
        g2.addComponent(new StartTriangle(g2));
        cemera=new GameObject(new Position(1200,1200,0));
        SystemManager.getViewSystem().setCemeraPosition(cemera.getObjectPosition().position);
        begin=new GameObject(new Position(1200,800,0));
        BitmapView startBitmap=new BitmapView(begin,R.drawable.begin, Bitmap.Config.ARGB_8888);
        startBitmap.position.point.x=-startBitmap.bitmap.getWidth()/2;
        startBitmap.position.point.y=startBitmap.bitmap.getHeight()/2;
        begin.addComponent(startBitmap);
        begin.addComponent(new FontScript(begin));
    }
}