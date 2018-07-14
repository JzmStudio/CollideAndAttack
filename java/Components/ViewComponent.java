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
     * 某些绘图组件使用,表示其位置,逆时针为+
     */
    public Position position;

    /*显示的透明度*/
    public int alpha=255;

    /**
     * 默认deep=0
     * @param gameObject
     */
    public ViewComponent(GameObject gameObject) {
        super(gameObject);
        deep=0;
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
        position=new Position();
    }

    public ViewComponent(GameObject gameObject,float x,float y,int deep) {
        super(gameObject);
        this.deep=deep;
        position=new Position(x,y,0);
    }

    public ViewComponent(GameObject gameObject,Position position,int deep) {
        super(gameObject);
        this.deep=deep;
        this.position=position;
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
