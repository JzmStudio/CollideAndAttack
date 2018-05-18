package Android;

import android.graphics.*;

import java.util.ArrayList;

import Bases.Point;

/**
 * 此类里面所有角度顺时针为+
 */
public class AndroidGraphics {
    private Canvas drawCanvas;
    private Paint paint;
    private Path path;
    private final RectF rectToDrawBitmap;   //仅用于画Bitmap
    private RectF rectF;

    public AndroidGraphics()
    {
        rectToDrawBitmap=new RectF();
        rectToDrawBitmap.left=0;
        rectToDrawBitmap.top=0;
        rectF=new RectF();
        paint=new Paint();
        path=new Path();
    }

    public AndroidGraphics(Canvas canvas)
    {
        drawCanvas=canvas;
        rectToDrawBitmap=new RectF();
        rectToDrawBitmap.left=0;
        rectToDrawBitmap.top=0;
        rectF=new RectF();
        paint=new Paint();
        path=new Path();
    }

    public void setCanvasToDraw(Canvas canvas)
    {
        this.drawCanvas=canvas;
    }

    public void drawBackgroundColor(int color)
    {
        drawCanvas.drawColor(color);
    }

    public void drawBackgroundColor(int A,int R,int G,int B) {drawCanvas.drawARGB(A,R,G,B);}

    public void setBitmap(Bitmap bitmap) {drawCanvas.setBitmap(bitmap);}

    /**
     * 画封闭图形
     * @param color
     * @param width 线段宽度
     * @param points
     * @param radius 线段拐点的角度(绘制圆角)
     */
    public void drawCloseLine(int color, int width, Point[] points,float radius)
    {
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(width);
        paint.setColor(color);
        if(radius!=0)
            paint.setPathEffect(new CornerPathEffect(radius));
        path.reset();
        path.moveTo(points[0].x,points[0].y);
        for(int i=1;i<points.length;i++)
        {
            path.lineTo(points[i].x,points[i].y);
        }
        path.close();
        drawCanvas.drawPath(path,paint);
        paint.reset();
    }

    /**
     * 画封闭图形
     * @param color
     * @param width 线段宽度
     * @param points
     * @param radius 线段拐点的角度(绘制圆角)
     */
    public void drawCloseLine(int color, int width, ArrayList<Point> points, float radius)
    {
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(width);
        paint.setColor(color);
        if(radius!=0)
            paint.setPathEffect(new CornerPathEffect(radius));
        path.reset();
        path.moveTo(points.get(0).x,points.get(0).y);
        for(int i=1;i<points.size();i++)
        {
            path.lineTo(points.get(i).x,points.get(i).y);
        }
        path.close();
        drawCanvas.drawPath(path,paint);
        paint.reset();
    }

    /**
     * 画封闭图形
     * @param color
     * @param width 线段宽度
     * @param points
     * @param radius 线段拐点的角度(绘制圆角)
     */
    public void drawCloseLine(int color, int width, ArrayList<Point> points, float radius,Point localPosition)
    {
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(width);
        paint.setColor(color);
        if(radius!=0)
            paint.setPathEffect(new CornerPathEffect(radius));
        path.reset();
        path.moveTo(points.get(0).x+localPosition.x,points.get(0).y+localPosition.y);
        for(int i=1;i<points.size();i++)
        {
            path.lineTo(points.get(i).x+localPosition.x,points.get(i).y+localPosition.y);
        }
        path.close();
        drawCanvas.drawPath(path,paint);
        paint.reset();
    }

    /**
     * 画封闭图形
     * @param color
     * @param width 线段宽度
     * @param points
     * @param radius 线段拐点的角度(绘制圆角)
     * @param localPosition ----本地坐标系在全局坐标系中的位置----
     * @param degree ----本地坐标系旋转角度----
     */
    public void drawCloseLine(int color, int width, ArrayList<Point> points, float radius,Point localPosition,float degree)
    {
        drawCanvas.save();
        drawCanvas.rotate(degree);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(width);
        paint.setColor(color);
        if(radius!=0)
            paint.setPathEffect(new CornerPathEffect(radius));
        path.reset();
        path.moveTo(points.get(0).x+localPosition.x,points.get(0).y+localPosition.y);
        for(int i=1;i<points.size();i++)
        {
            path.lineTo(points.get(i).x+localPosition.x,points.get(i).y+localPosition.y);
        }
        path.close();
        drawCanvas.drawPath(path,paint);
        paint.reset();
        drawCanvas.restore();
    }

    /**
     *
     * @param color
     * @param points
     * @param radius 线段拐点的角度(绘制圆角)
     */
    public void fillCloseLine(int color,ArrayList<Point> points,int radius)
    {
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(color);
        if(radius!=0)
            paint.setPathEffect(new CornerPathEffect(radius));
        path.reset();
        path.moveTo(points.get(0).x,points.get(0).y);
        for(int i=1;i<points.size();i++)
        {
            path.lineTo(points.get(i).x,points.get(i).y);
        }
        path.close();
        drawCanvas.drawPath(path,paint);
        path.reset();
    }

    /**
     *
     * @param color
     * @param points
     * @param radius 线段拐点的角度(绘制圆角)
     */
    public void fillCloseLine(int color,ArrayList<Point> points,float radius,Point localPosition)
    {
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(color);
        if(radius!=0)
            paint.setPathEffect(new CornerPathEffect(radius));
        path.reset();
        path.moveTo(points.get(0).x+localPosition.x,points.get(0).y+localPosition.y);
        for(int i=1;i<points.size();i++)
        {
            path.lineTo(points.get(i).x+localPosition.x,points.get(i).y+localPosition.y);
        }
        path.close();
        drawCanvas.drawPath(path,paint);
        path.reset();
    }

    /**
     * 画圆角矩形
     * @param left
     * @param top
     * @param right
     * @param bottom
     * @param strockWidth 画笔粗细
     * @param radius 圆角的角度
     * @param color
     */
    public void drawRoundRect(float left,float top,float right,float bottom,float strockWidth,float radius,int color)
    {
        paint.setStrokeWidth(strockWidth);
        paint.setColor(color);
        paint.setStyle(Paint.Style.STROKE);
        rectF.left=left;
        rectF.top=top;
        rectF.right=right;
        rectF.bottom=bottom;
        drawCanvas.drawRoundRect(rectF, radius, radius, paint);
    }

    /**
     * 画圆角矩形
     * @param radius 圆角的角度
     */
    public void drawRoundRect(float left,float top,float width,float height,float strockWidth,float radius,int color,float degree)
    {
        drawCanvas.save();
        drawCanvas.translate(left,top);
        drawCanvas.rotate(degree);
        paint.setStrokeWidth(width);
        paint.setColor(color);
        paint.setStyle(Paint.Style.STROKE);
        rectF.left=0;
        rectF.top=0;
        rectF.right=width;
        rectF.bottom=height;
        drawCanvas.drawRoundRect(rectF, radius, radius, paint);
        drawCanvas.restore();
    }

    public void fillRoundRect(float left,float top,float right,float bottom,float radius,int color)
    {
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(color);
        rectF.left=left;
        rectF.top=top;
        rectF.right=right;
        rectF.bottom=bottom;
        drawCanvas.drawRoundRect(rectF, radius, radius, paint);
    }

    public void fillRoundRect(float left,float top,float width,float height,float radius,int color,float degree)
    {
        drawCanvas.save();
        drawCanvas.translate(left,top);
        drawCanvas.rotate(degree);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(color);
        rectF.left=0;
        rectF.top=0;
        rectF.right=width;
        rectF.bottom=height;
        drawCanvas.drawRoundRect(rectF, radius, radius, paint);
        drawCanvas.restore();
    }

    /**
     * 用线条画圆
     * @param color
     * @param x 圆心x坐标
     * @param y 圆心y坐标
     * @param r 半径
     * @param width 线条宽度
     */
    public void drawCircle(int color,float x,float y,float r,float width)
    {
        paint.setColor(color);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(width);
        drawCanvas.drawCircle(x, y, r, paint);
    }

    /**
     * 填充圆
     * @param color
     * @param x 圆心x坐标
     * @param y 圆心y坐标
     * @param r 半径
     */
    public void fillCircle(int color,float x,float y,float r)
    {
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);
        drawCanvas.drawCircle(x,y,r,paint);
    }

    /**
     *
     * @param bitmap
     * @param x left
     * @param y top
     * @param degree
     */
    public void drawBitmap(Bitmap bitmap,float x,float y,float degree)
    {
        drawCanvas.save();
        drawCanvas.translate(x,y);
        drawCanvas.rotate(degree);
        drawCanvas.drawBitmap(bitmap,0,0,null);
        drawCanvas.restore();
    }

    public void drawBitmap(Bitmap bitmap,float x,float y,float degree,Point localPosition)
    {
        drawCanvas.save();
        drawCanvas.translate(x+localPosition.x,y+localPosition.y);
        drawCanvas.rotate(degree);
        drawCanvas.drawBitmap(bitmap,0,0,null);
        drawCanvas.restore();
    }

    /**
     * 可缩放Bitmap到指定位置
     * @param bitmap
     * @param srcRect 定义了原图中被绘制的部分
     * @param dstRect 定义了绘制在目标中的部分
     */
    public void drawBitmap(Bitmap bitmap,Rect srcRect,Rect dstRect)
    {
        drawCanvas.drawBitmap(bitmap,srcRect,dstRect,null);
    }

    /**
     *
     * @param s
     * @param x
     * @param y 基线的位置
     * @param size
     * @param color
     * @param alpha 透明度
     */
    public void drawText(String s,float x,float y,float size,int color,int alpha)
    {
        paint.setTextSize(size);
        paint.setColor(color);
        paint.setStrokeWidth(3);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setAlpha(alpha);
        drawCanvas.drawText(s, x, y, paint);
    }

    /**
     *
     * @param s
     * @param x
     * @param y 基线的位置
     * @param size
     * @param color
     * @param alpha 透明度
     */
    public void drawText(String s,float x,float y,float size,int color,int alpha,Point localPosition)
    {
        paint.setTextSize(size);
        paint.setColor(color);
        paint.setStrokeWidth(3);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setAlpha(alpha);
        drawCanvas.drawText(s, x+localPosition.x, y+localPosition.y, paint);
    }

    /**
     *
     * @param s
     * @param x
     * @param y 基线的位置
     * @param size
     * @param color
     * @param alpha
     * @param degree
     */
    public void drawText(String s,float x,float y,float size,int color,int alpha,float degree)
    {
        drawCanvas.save();
        drawCanvas.translate(x,y);
        drawCanvas.rotate(degree);
        paint.setTextSize(size);
        paint.setColor(color);
        paint.setStrokeWidth(3);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setAlpha(alpha);
        drawCanvas.drawText(s, 0, 0, paint);
        drawCanvas.restore();
    }
}