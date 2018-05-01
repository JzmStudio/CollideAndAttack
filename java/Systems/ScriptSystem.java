package Systems;

import java.util.ArrayList;

import Components.UpdateComponent;
import Interfaces.CanUpdate;

public class ScriptSystem implements CanUpdate{
	private ArrayList<UpdateComponent> list;
	private float deltaTime;
	
	public ScriptSystem()
	{
		list=new ArrayList<UpdateComponent>();
		Start();
	}
	
	public void addToUpdateList(UpdateComponent script)
	{
		list.add(script);
	}
	
	public void removeFromUpdateList(UpdateComponent script)
	{
		list.remove(script);
	}
	
	public void Start()
	{
		for(UpdateComponent s:list)
		{
			s.Start();
		}
	}

	@Override
	public void update(long deltaMillisecond) {
		deltaTime=deltaMillisecond/1000.0f;
		for(UpdateComponent component:list){
			component.Update(deltaTime);
		}
	}
}