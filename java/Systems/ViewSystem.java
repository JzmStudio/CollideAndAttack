package Systems;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.Log;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Queue;

import Android.AndroidGraphics;
import Bases.*;
import Components.PhysicsComponents.Transform;
import Components.ViewComponent;
import Components.ViewComponents.BitmapView;
import Components.ViewComponents.ClosePointView;
import Components.ViewComponents.ScreenBitmapView;
import Components.ViewComponents.TextView;
import Interfaces.UpdateView;

public class ViewSystem implements UpdateView{
    private Queue<OnDraw> drawQueue;
    private AndroidGraphics graphics;
    /**
     * 存储ViewComponent的绘图实例对应表
     */
    private HashMap<ViewComponent,OnDraw> sysDrawCorrelate;
    private ArrayList<BitmapView> bitmapViews;
    private ArrayList<ClosePointView> closePointViews;
    private ArrayList<TextView> textViews;
    private ArrayList<ScreenBitmapView> screenBitmapViews;
    private Position cemeraPosition;
    /*相机的最大可视范围*/
    private float left;
    private float right;
    private float top;
    private float bottom;

    private int targetScreenWidth=1920; //目标绘制坐标横范围
    private int targetScreenHeight=1080;    //目标绘制坐标纵范围
    private float halfTargetWidth; //相机最大显示半径,方便是否显示的计算
    private float halfTargetHeight;
    private Bitmap bitmap=Bitmap.createBitmap(1920,1080, Bitmap.Config.RGB_565);
    private Canvas canvas;  //先绘制到此Canvas上再绘制到手机屏幕
    private RectF dstRect;
    private int screenWidth;
    private int screenHeight;
    private float scaleW;
    private float scaleH;

    public ViewSystem()
    {
        /*初始化需要的数据结构*/
        drawQueue=new PriorityQueue<>(50, new Comparator<OnDraw>() {
            @Override
            public int compare(OnDraw onDraw, OnDraw t1) {
                return onDraw.view.getDeep()-t1.view.getDeep();
            }
        });
        graphics=new AndroidGraphics();
        sysDrawCorrelate=new HashMap<>();
        bitmapViews=SystemManager.addComponentList("BitmapView");
        closePointViews=SystemManager.addComponentList("ClosePointView");
        textViews=SystemManager.addComponentList("TextView");
        screenBitmapViews=SystemManager.addComponentList("ScreenBitmapView");

        /*进行屏幕显示的自适应调整*/
        canvas=new Canvas(bitmap);
        graphics.setCanvasToDraw(canvas);
        screenHeight=SystemManager.getMainActivity().getScreenHeight();
        screenWidth=SystemManager.getMainActivity().getScreenWidth();
        scaleW=screenWidth/targetScreenWidth;
        scaleH=screenHeight/targetScreenHeight;
        dstRect=new RectF();
        if(scaleW>scaleH){
            dstRect.left=(screenWidth-targetScreenWidth*scaleH)/2.0f;
            dstRect.right=(screenWidth+targetScreenWidth*scaleH)/2.0f;
            dstRect.top=0;
            dstRect.bottom=screenHeight;
        }
        else {
            dstRect.left=0;
            dstRect.right=screenWidth;
            dstRect.top=(screenHeight-targetScreenHeight*scaleW)/2.0f;
            dstRect.bottom=(screenHeight+targetScreenHeight*scaleW)/2.0f;
        }
        //Cemera
        cemeraPosition=new Position();
        /*计算目标屏幕长宽的一半*/
        halfTargetWidth=targetScreenWidth/2;
        halfTargetHeight=targetScreenHeight/2;

        SystemManager.getMainActivity().getUpdateView().addToUpdateViewList(this);
    }

    public void addViewCompoent(ViewComponent viewComponent)
    {
        Log.d("addView",viewComponent.getClass().getSimpleName());
        OnDraw draw=null;
        if(viewComponent instanceof BitmapView)
        {
            draw=new BitmapDraw(viewComponent);
        }
        else if(viewComponent instanceof ClosePointView)
        {
            draw=new ClosePointDraw(viewComponent);
        }
        else if(viewComponent instanceof TextView)
        {
            draw=new TextDraw(viewComponent);
        }
        else if(viewComponent instanceof ScreenBitmapView)
        {
            draw=new ScreenBitmapDraw(viewComponent);
        }
        else {
            return;
        }
        drawQueue.add(draw);
        sysDrawCorrelate.put(viewComponent,draw);
    }

    public void changeDeep(ViewComponent viewComponent)
    {
        OnDraw sysdraw=sysDrawCorrelate.get(viewComponent);
        drawQueue.remove(sysdraw);
        drawQueue.add(sysdraw);
    }

    @Override
    public void updateView(Canvas updateCanvas) {
        left=cemeraPosition.point.x-halfTargetWidth;
        right=cemeraPosition.point.x+halfTargetWidth;
        top=cemeraPosition.point.y+halfTargetHeight;
        bottom=cemeraPosition.point.y-halfTargetHeight;
        for(OnDraw com:drawQueue)
        {
            com.onDraw();
        }
        updateCanvas.drawBitmap(bitmap,null,dstRect,null);
    }

    public void removeViewComponent(ViewComponent viewComponent)
    {
        drawQueue.remove(sysDrawCorrelate.remove(viewComponent));
    }

    /**
     * 设置相机跟随的坐标,相机为画面正中
     * @param position
     */
    public void setCemeraPosition(Position position)
    {
        cemeraPosition=position;
    }

    /**
     * 将绘图方式变为平铺
     */
    public void changeTileDraw()
    {
        dstRect.top=0;
        dstRect.bottom=screenHeight;
        dstRect.left=0;
        dstRect.right=screenWidth;
    }

    /**
     * 将绘图方式变为按比例绘制
     */
    public void changeScaleDraw()
    {
        if(scaleW>scaleH){
            dstRect.left=(screenWidth-targetScreenWidth*scaleH)/2.0f;
            dstRect.right=(screenWidth+targetScreenWidth*scaleH)/2.0f;
            dstRect.top=0;
            dstRect.bottom=screenHeight;
        }
        else {
            dstRect.left=0;
            dstRect.right=screenWidth;
            dstRect.top=(screenHeight-targetScreenHeight*scaleW)/2.0f;
            dstRect.bottom=(screenHeight+targetScreenHeight*scaleW)/2.0f;
        }
    }

    private abstract class OnDraw{
        ViewComponent view;
        OnDraw(ViewComponent viewComponent) {this.view=viewComponent;}
        abstract void onDraw();
    }

    private class BitmapDraw extends OnDraw{
        Point globalPoint;
        Point cemeraPoint;  //相机坐标系下的坐标
        float len;
        BitmapDraw(ViewComponent viewComponent) {
            super(viewComponent);
            globalPoint=new Point();
            cemeraPoint=new Point();
            BitmapView b=(BitmapView)view;
            int w=b.bitmap.getWidth();
            int h=b.bitmap.getHeight();
            len= (float) Math.sqrt(w*w+h*h);
        }

        @Override
        void onDraw() {
            BitmapView b=(BitmapView)view;
            Transform localCoordinate=view.getGameObject().getObjectPosition();
            Position.transferToGlobal(localCoordinate.position,b.position.point,globalPoint);
            Position.transferToLocal(localCoordinate.position,globalPoint,cemeraPoint);
            if(cemeraPoint.x+len<left||cemeraPoint.x-len>right||cemeraPoint.y+len<bottom||cemeraPoint.y-len>top) return;
            graphics.drawBitmap(b.bitmap,halfTargetWidth+cemeraPoint.x,halfTargetHeight-cemeraPoint.y,cemeraPosition.degree-b.position.degree-localCoordinate.position.degree);
        }
    }

    private class ClosePointDraw extends OnDraw{
        ArrayList<Bases.Point> points;
        ClosePointDraw(ViewComponent viewComponent) {
            super(viewComponent);
            points=new ArrayList<>();
        }
        @Override
        void onDraw() {
            /*ClosePointView c=(ClosePointView) view;
            Transform localCoordinate=c.getGameObject().getObjectPosition();
            Bases.Point local=position.position;
            Collections.copy(points,c.points);
            for(Bases.Point p:points)
            {
                p.x=
            }
            graphics.drawCloseLine(c.color,c.width,c.points,c.radius,c.getGameObject().getObjectPosition().position,c.degree);*/
        }
    }

    private class TextDraw extends OnDraw{
        TextDraw(ViewComponent viewComponent) {
            super(viewComponent);
        }
        @Override
        void onDraw() {

        }
    }

    private class ScreenBitmapDraw extends OnDraw{

        ScreenBitmapDraw(ViewComponent viewComponent) {
            super(viewComponent);
        }

        @Override
        void onDraw() {
            ScreenBitmapView b= (ScreenBitmapView) view;
            graphics.drawBitmap(b.bitmap,b.position.point.x,b.position.point.y,b.position.degree);
        }
    }
}