package Systems;

import java.util.ArrayList;
import Components.UpdateComponent;
import Interfaces.CanUpdate;

public class ScriptSystem implements CanUpdate{
	private ArrayList<UpdateComponent> list;
	private float deltaTime;
	private ArrayList<UpdateComponent> removelist;
	
	public ScriptSystem()
	{
		list=SystemManager.addComponentList("UpdateComponent");
		SystemManager.getMainActivity().getUpdateView().addToUpdateList(this);
		removelist=new ArrayList<>();
	}

	@Override
	public void update(long deltaMillisecond) {
		deltaTime=deltaMillisecond;
		int i;
		for(i=0;i<removelist.size();i++)
		{
			list.remove(removelist.get(i));
		}
		removelist.clear();
		for(i=0;i<list.size();i++)
		{
			list.get(i).Update(deltaTime);
		}
	}

	public void removeScript(UpdateComponent component)
	{
		removelist.add(component);
	}
}