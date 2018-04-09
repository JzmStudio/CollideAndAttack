package Components;

import Events.CollideEvent;

public abstract class UpdateComponent {
	UpdateComponent()
	{
		
	}
	
	/**
	 * ��Updateǰ������
	 */
	abstract public void Start();
	
	/**
	 * ������¼��߼����µ�
	 */
	abstract public void Update(float deltaTime);
	
	/**
	 * ��ײ�ص�
	 */
	abstract public void collide(CollideEvent event);
}
