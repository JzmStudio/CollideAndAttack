package World;

import android.graphics.Bitmap;

import com.example.collideandattack.R;

import Components.ViewComponents.ScreenBitmapView;
import Prefabs.GameObject;

/**
 * 所有游戏场景都从StartGameWorld类开始
 */
public class StartGameWorld extends GameWorld {
    GameObject g1;
    public StartGameWorld()
    {
        g1=new GameObject();
        g1.addComponent(new ScreenBitmapView(g1, R.drawable.bg, Bitmap.Config.RGB_565));
    }
}