package Bases;

public class Position {
    public static final double PI=Math.PI;
    public Point point;
    /**
     * 逆时针方向为+,弧度表示
     */
    public float degree;

    public Position()
    {
        point=new Point();
    }

    public Position(float x,float y,float degree)
    {
        point=new Point(x,y);
        this.degree=degree;
    }

    /**
     * 完成从本地坐标系到全局(相对于本地坐标系)坐标系的转换
     * @param localCoordinate
     * @param localPosition
     * @return
     */
    public static Point transferToGlobal(Position localCoordinate,Point localPosition)
    {
        float cos=(float)Math.cos(localCoordinate.degree);
        float sin= (float) Math.sin(localCoordinate.degree);
        return new Point(localPosition.x*cos-localPosition.y*sin+localCoordinate.point.x,
                localPosition.x*sin+localPosition.y*cos+localCoordinate.point.y);
    }

    /**
     * 完成从本地坐标系到全局(相对于本地坐标系)坐标系的转换,此函数不创建新对象
     * @param localCoordinate
     * @param localPosition
     * @param globalPosition 转换完成的坐标
     */
    public static void transferToGlobal(Position localCoordinate,Point localPosition,Point globalPosition)
    {
        float cos=(float)Math.cos(localCoordinate.degree);
        float sin= (float) Math.sin(localCoordinate.degree);
        globalPosition.x=localPosition.x*cos-localPosition.y*sin+localCoordinate.point.x;
        globalPosition.y=localPosition.x*sin+localPosition.y*cos+localCoordinate.point.y;
    }

    /**
     * 完成全局坐标到本地坐标的变换
     * @param localCoordinate
     * @param globalPosition
     * @param localPosition 转换完成写入的坐标
     */
    public static void transferToLocal(Position localCoordinate,Point globalPosition,Point localPosition)
    {
        float cos=(float)Math.cos(localCoordinate.degree);
        float sin= (float) Math.sin(localCoordinate.degree);
        localPosition.x=globalPosition.x*cos+globalPosition.y*sin-localCoordinate.point.x;
        localPosition.y=globalPosition.y*cos-globalPosition.x*sin-localCoordinate.point.y;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new Position(point.x,point.y,degree);
    }
}
