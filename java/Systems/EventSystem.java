package Systems;

import java.util.ArrayList;
import java.util.HashMap;

import Events.Event;
import Interfaces.EventAction;

public class EventSystem {
	
	private static HashMap<String, ArrayList<EventAction>> hash=new HashMap<String, ArrayList<EventAction>>();
	
	/**
	 * 注册指定事件的监听链表
	 * @param name 根据事件名name区分事件
	 */
	public static void newEventListenerList(String name)
	{
		if(!hash.containsKey(name))
		{
			hash.put(name, new ArrayList<EventAction>());
		}
	}
	
	/**
	 * 如果有相应观察者表，则通知相应观察者并返回true，否则返回false
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
	 * 注册到相应时间的注册表中
	 * @param name 相应事件的名称
	 * @param script 注册需实现EventAction接口
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
	 * 删除相应事件的监听链表
	 * @param name
	 */
	public static void removeEventListenerList(String name)
	{
		hash.remove(name);
	}
}