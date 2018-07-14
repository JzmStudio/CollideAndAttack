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
import Components.ViewComponents.ScreenTextView;
import Interfaces.Screen;
import Prefabs.GameObject;
import Scripts.FontScript;
import Scripts.StartScripts.BeardAction;
import Scripts.StartScripts.EyeAction;
import Scripts.StartScripts.FontAction;
import Scripts.StartScripts.TbgAction;
import Scripts.StartTriangle;
import Systems.SystemManager;

/**
 * 所有游戏场景都从StartGameWorld类开始
 */
public class StartGameWorld extends GameWorld {
    GameObject bg;
    GameObject tg;
    GameObject eye;
    GameObject beard;
    GameObject cemera;
    GameObject begin;
    public StartGameWorld()
    {
        bg=addGameObject(new GameObject());
        /*背景*/
        bg.addComponent(new ScreenBitmapView(bg,R.drawable.bg, Bitmap.Config.ARGB_8888,0,0,0));

        /*Triangle background*/
        tg=addGameObject(new GameObject(new Position(1200,1200,-90)));
        BitmapView bitmapView=new BitmapView(tg,R.drawable.tbg,Bitmap.Config.ARGB_8888,new Position(),1);
        bitmapView.position.point.x=-bitmapView.bitmap.getWidth()/2;
        bitmapView.position.point.y=bitmapView.bitmap.getHeight()/2;
        tg.addComponent(bitmapView);
        tg.addComponent(new TbgAction(tg));
        Log.e("Over1",""+1);

        /*eye*/
        eye=addGameObject(new GameObject(new Position(1200,1200,-90)));
        BitmapView bitmapView1=new BitmapView(eye,R.drawable.eye, Bitmap.Config.ARGB_8888,new Position(),2);
        bitmapView1.position.point.x-=bitmapView1.bitmap.getWidth()/2;
        bitmapView1.position.point.y+=30;
        eye.addComponent(bitmapView1);
        eye.addComponent(new EyeAction(eye));
        Log.e("Over2",""+2);

        /*Beard*/
        beard=addGameObject(new GameObject(new Position(1200,1200,-90)));
        BitmapView bitmapView2=new BitmapView(beard,R.drawable.beard, Bitmap.Config.ARGB_8888,new Position(),2);
        bitmapView2.position.point.x-=bitmapView2.bitmap.getWidth()/2;
        bitmapView2.position.point.y-=15;
        beard.addComponent(bitmapView2);
        beard.addComponent(new BeardAction(beard));
        Log.e("Over3",""+3);

        /*cemera*/
        cemera=addGameObject(new GameObject(new Position(1200,1200,0)));
        SystemManager.getViewSystem().setCemeraPosition(cemera.getObjectPosition().position);

        begin=addGameObject(new GameObject(new Position(1200,800,0)));
        ScreenTextView textView=new ScreenTextView(begin,"点击开始",900,1000,0,Color.BLACK,40,2);
        textView.draw=false;
        begin.addComponent(textView);
        begin.addComponent(new FontAction(begin));
    }
}