package Components;

import Interfaces.EngineAndControl;

public class Component {
	protected String name;	
	protected EngineAndControl engine;
	
	public Component(String name,EngineAndControl engine)
	{
		this.name=name;
		this.engine=engine;
	}
}