package Systems;

import java.util.ArrayList;

import Components.UpdateComponent;

public class UpdateSystem {
	private ArrayList<UpdateComponent> list;
	
	public UpdateSystem()
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
	
	public void Update(float deltaTime)
	{
		for(UpdateComponent s:list)
		{
			s.Update(deltaTime);;
		}
	}
}
