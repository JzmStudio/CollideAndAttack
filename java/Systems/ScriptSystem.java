package Systems;

import java.util.ArrayList;

import Android.AndroidUpdateView;
import Components.UpdateComponent;
import Interfaces.CanUpdate;
import Interfaces.Start;

public class ScriptSystem implements CanUpdate, Start{
	private ArrayList<UpdateComponent> list;
	private float deltaTime;
	
	public ScriptSystem()
	{
		list=new ArrayList<UpdateComponent>();
	}
	
	public void addScript(UpdateComponent script)
	{
		list.add(script);
	}
	
	public void removeScript(UpdateComponent script)
	{
		list.remove(script);
	}

	@Override
	public void start() {
		for(UpdateComponent o:list)
			o.Start();
	}
	@Override
	public void update(long deltaMillisecond) {
		deltaTime=deltaMillisecond/1000.0f;
		for(UpdateComponent component:list){
			component.Update(deltaTime);
		}
	}
}