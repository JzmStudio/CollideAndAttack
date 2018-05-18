package Components;

import android.util.Log;

import Prefabs.GameObject;
import Systems.SystemManager;

/**
 * 所有类的资源加载到内存都直接在构造函数里,组件引用由GameObject与相应ComponentSystem持有
 * ---------所有Component不可重名----------
 */
public abstract class Component {
	protected GameObject gameObject;

	public Component(GameObject gameObject)
	{
		this.gameObject=gameObject;
		SystemManager.addComponent(this);
	}

	/**
	 * ----------此函数仅供依附的GameObject被摧毁时回调----------
	 * ----------实现时无需考虑从GameObject中移除----------
	 * -----子类若需额外动作可只重写此函数,不用重写removeThis-------
	 */
	public void onRemove()
	{
		Log.d("Remove",this.getClass().getSimpleName());
		SystemManager.removeComponent(this);
	}

	/**
	 * 移除这个组件(主动移除)
	 * ------此方法不要重写-----
	 */
	public final void removeThis()
	{
		gameObject.removeComponent(this);
		onRemove();
	}

	public GameObject getGameObject() {
		return gameObject;
	}
}