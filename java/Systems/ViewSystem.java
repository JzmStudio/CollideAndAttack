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
import Components.ViewComponents.ScreenRectView;
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
    //private ArrayList<OnDraw> initList; //绘画内部类的初始化,以避免空引用及计算浪费
    private Position cemeraPosition;

    public static final int targetScreenWidth=1920; //目标绘制坐标横范围
    public static final int targetScreenHeight=1080;    //目标绘制坐标纵范围
    public static final float halfTargetWidth=targetScreenWidth/2; //相机最大显示半径,方便是否显示的计算
    public static final float halfTargetHeight=targetScreenHeight/2;
    /*相机的最大可视范围*/
    private final float left=-halfTargetWidth;
    private final float right=halfTargetWidth;
    private final float top=halfTargetHeight;
    private final float bottom=-halfTargetHeight;

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
        //initList=new ArrayList<>();

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

        SystemManager.getMainActivity().getUpdateView().addToUpdateViewList(this);
    }

    /*public void addViewComponent(ViewComponent viewComponent)
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
        drawQueue.add(draw);    //加入绘图优先队列
        initList.add(draw);     //加入初始化队列
        sysDrawCorrelate.put(viewComponent,draw);
    }*/

    public void addViewComponent(BitmapView bitmapView)
    {
        addToDraw(new BitmapDraw(bitmapView));
    }

    public void addViewComponent(ClosePointView closePointView)
    {
        addToDraw(new ClosePointDraw(closePointView));
    }

    public void addViewComponent(ScreenBitmapView screenBitmapView)
    {
        addToDraw(new ScreenBitmapDraw(screenBitmapView));
    }

    public void addViewComponent(ScreenRectView screenRectView)
    {
        addToDraw(new ScreenRectDraw(screenRectView));
    }

    public void addViewComponent(TextView textView)
    {
        addToDraw(new TextDraw(textView));
    }

    private void addToDraw(OnDraw draw)
    {
        drawQueue.add(draw);    //加入绘图优先队列
        //initList.add(draw);     //加入初始化队列
        sysDrawCorrelate.put(draw.view,draw);
    }

    public void changeDeep(ViewComponent viewComponent)
    {
        OnDraw sysdraw=sysDrawCorrelate.get(viewComponent);
        drawQueue.remove(sysdraw);
        drawQueue.add(sysdraw);
    }

    @Override
    public void updateView(Canvas updateCanvas) {
        /*若有绘图内部类未初始化则先初始化,并移出队列*/
//        for(int i=0;i<initList.size();i++)
//        {
//            initList.remove(i).init();
//        }
        /*绘图*/
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

    public Position getCemeraPosition() {
        return cemeraPosition;
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
        //String name;
        BitmapDraw(ViewComponent viewComponent) {
            super(viewComponent);
            globalPoint=new Point();
            cemeraPoint=new Point();
            //name=viewComponent.getClass().getSimpleName();
            BitmapView b=(BitmapView)view;
            //由于尚未初始化完CPU控制权交给了这里导致出现异常
            int w=b.bitmap.getWidth();
            int h=b.bitmap.getHeight();
            len= (float) Math.sqrt(w*w+h*h);
        }

        @Override
        void onDraw() {
            BitmapView b=(BitmapView)view;
            if(!b.isVisible) return;
            Transform localCoordinate=view.getGameObject().getObjectPosition();
            //Log.d("Local",""+view.position.point.x+" "+view.position.point.y);
            //Log.d("Draw",name);
            Position.transferToGlobal(localCoordinate.position,b.position.point,globalPoint);
            //Log.d("Global",""+globalPoint.x+" "+globalPoint.y);
            Position.transferToLocal(cemeraPosition,globalPoint,cemeraPoint);
            //Log.d("Cem",""+cemeraPoint.x+" "+cemeraPoint.y);
            //Log.d("LLL",""+left+" "+right+" "+bottom+" "+top+" "+cemeraPoint.x+" "+cemeraPoint.y);
            if(cemeraPoint.x+len<left||cemeraPoint.x-len>right||cemeraPoint.y+len<bottom||cemeraPoint.y-len>top) return;
            graphics.drawBitmap(b.bitmap,halfTargetWidth+cemeraPoint.x,halfTargetHeight-cemeraPoint.y,cemeraPosition.degree-b.position.degree-localCoordinate.position.degree);
            //Log.d("Location",""+(halfTargetWidth+cemeraPoint.x)+(halfTargetHeight-cemeraPoint.y));
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
        Point globalPoint;
        Point cemeraPoint;
        TextDraw(ViewComponent viewComponent) {
            super(viewComponent);
            globalPoint=new Point();
            cemeraPoint=new Point();
        }
        @Override
        void onDraw() {
            TextView t= (TextView) view;
            Position localCoordinate=t.getGameObject().getObjectPosition().position;
            Position.transferToGlobal(localCoordinate,t.position.point,globalPoint);
            Position.transferToLocal(cemeraPosition,globalPoint,cemeraPoint);
            graphics.drawText(t.text,cemeraPoint.x,cemeraPoint.y,t.size,t.color,t.alpha, cemeraPosition.degree-t.position.degree-localCoordinate.degree);
        }
    }

    private class ScreenBitmapDraw extends OnDraw{
        String name;
        ScreenBitmapDraw(ViewComponent viewComponent) {
            super(viewComponent);
            name=viewComponent.getClass().getSimpleName();
        }

        @Override
        void onDraw() {
            Log.d("Draw",name);
            ScreenBitmapView b= (ScreenBitmapView) view;
            graphics.drawBitmap(b.bitmap,b.position.point.x,b.position.point.y,-b.position.degree);
        }
    }

    private class ScreenRectDraw extends OnDraw{
        ScreenRectDraw(ViewComponent viewComponent) {
            super(viewComponent);
        }

        @Override
        void onDraw() {
            ScreenRectView v= (ScreenRectView) view;
            if(v.isFill)
                graphics.fillRoundRect(v.position.point.x,v.position.point.y,v.width,v.height,v.radius,v.color,-v.position.degree);
            else
                graphics.drawRoundRect(v.position.point.x,v.position.point.y,v.width,v.height,v.strockWidth,v.radius,v.color,-v.position.degree);
        }
    }
}