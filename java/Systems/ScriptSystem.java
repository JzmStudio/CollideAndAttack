package Systems;

import java.util.ArrayList;

import Android.AndroidUpdateView;
import Components.UpdateComponent;
import Interfaces.CanUpdate;
import Interfaces.Start;

public class ScriptSystem implements CanUpdate{
	private ArrayList<UpdateComponent> list;
	private float deltaTime;
	
	public ScriptSystem()
	{
		list=SystemManager.addComponentList("UpdateComponent");
		SystemManager.getMainActivity().getUpdateView().addToUpdateList(this);
	}

	@Override
	public void update(long deltaMillisecond) {
		deltaTime=deltaMillisecond/1000.0f;
		for(UpdateComponent component:list){
			component.Update(deltaTime);
		}
	}
}