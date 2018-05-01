package Systems;

import java.util.ArrayList;
import java.util.HashMap;

import Events.Event;
import Interfaces.EventAction;

public class EventSystem {
	
	private static HashMap<String, ArrayList<EventAction>> hash=new HashMap<String, ArrayList<EventAction>>();
	
	/**
	 * 创建新回调事件,以事件的名称区分事件
	 * @param eventName 回调时间的名称(独有)
	 */
	public static void newEventListenerList(String eventName)
	{
		if(!hash.containsKey(eventName))
		{
			hash.put(eventName, new ArrayList<EventAction>());
		}
	}

	/**
	 * 取消某回调事件的相关监听者列表
	 * @param eventName
	 */
	public static void removeEventListenerList(String eventName)
	{
		hash.remove(eventName);
	}
	
	/**
	 * 回调监听该事件的回调函数
	 * @param e ? extends Event 以事件的名称区分事件
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
	 * 设置需要监听的事件
	 * @param eventName 所监听事件的名称
	 * @param script ? implements EventAction 回调函数
	 * @return 成功为true
	 */
	public static boolean registerToEventList(String eventName,EventAction script)
	{
		ArrayList<EventAction> list=hash.get(eventName);
		if(list==null) return false;
		list.add(script);
		return true;
	}

	public static void removeFromEventList(String eventName,EventAction scrpit)
	{
		ArrayList<EventAction> list=hash.get(eventName);
		if(list==null) return;
		list.remove(scrpit);
	}
}