package Components;

import Bases.Point;
import Bases.Position;
import Prefabs.GameObject;
import Systems.SystemManager;
import Systems.ViewSystem;

/**
 * 若视图组件不再需要绘制则应从ViewSystem绘制队列中移除
 * ---------所有视图组件均为本地坐标系---------------
 */
public class ViewComponent extends Component {
    /**
     * 绘图深度,决定了绘制先后顺序,深度越低越先绘制
     * -------deep>=0--------
     */
    private int deep;
    /**
     * 若需要绘制则为true,否则为false
     */
    public boolean draw=true;

    /**
     * 此处由于只定义
     */
    public Position position;   //本地坐标系相对于全局坐标系的角度,逆时针为+

    /**
     * 默认deep=0
     * @param gameObject
     */
    public ViewComponent(GameObject gameObject) {
        super(gameObject);
        deep=0;
        SystemManager.getViewSystem().addViewCompoent(this);
        position=new Position();
    }

    /**
     *
     * @param gameObject
     * @param deep 绘图深度,决定了绘制先后顺序,深度越低越先绘制-------deep>=0--------
     */
    public ViewComponent(GameObject gameObject,int deep) {
        super(gameObject);
        this.deep=deep;
        SystemManager.getViewSystem().addViewCompoent(this);
        position=new Position();
    }

    public ViewComponent(GameObject gameObject,float x,float y,int deep) {
        super(gameObject);
        this.deep=deep;
        SystemManager.getViewSystem().addViewCompoent(this);
        position=new Position(x,y,0);
    }

    @Override
    public void onRemove() {
        super.onRemove();
        SystemManager.getViewSystem().removeViewComponent(this);
    }

    public void changeDeep(int deep){
        this.deep=deep;
        SystemManager.getViewSystem().changeDeep(this);
    }

    public int getDeep(){
        return deep;
    }
}
