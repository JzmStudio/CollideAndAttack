package Systems;

import java.util.ArrayList;
import java.util.HashMap;

import Events.Event;
import Interfaces.EventAction;

public class EventSystem {
	
	private static HashMap<String, ArrayList<EventAction>> hash=new HashMap<String, ArrayList<EventAction>>();
	
	/**
	 * ע��ָ���¼��ļ�������
	 * @param name �����¼���name�����¼�
	 */
	public static void newEventListenerList(String name)
	{
		if(!hash.containsKey(name))
		{
			hash.put(name, new ArrayList<EventAction>());
		}
	}
	
	/**
	 * �������Ӧ�۲��߱���֪ͨ��Ӧ�۲��߲�����true�����򷵻�false
	 * @param e ? extends Event
	 * @return
	 */
	public static boolean pushEvent(Event e)
	{
		ArrayList<EventAction> list=hash.get(e.name);
		if(list==null) return false;
		for(EventAction script:list)
		{
			script.action(e);
		}
		return true;
	}
	
	/**
	 * ע�ᵽ��Ӧʱ���ע�����
	 * @param name ��Ӧ�¼�������
	 * @param script ע����ʵ��EventAction�ӿ�
	 * @return
	 */
	public static boolean registerToEventList(String name,EventAction script)
	{
		ArrayList<EventAction> list=hash.get(name);
		if(list==null) return false;
		list.add(script);
		return true;
	}
	
	/**
	 * ɾ����Ӧ�¼��ļ�������
	 * @param name
	 */
	public static void removeEventListenerList(String name)
	{
		hash.remove(name);
	}
}