package Systems;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

import Android.AndroidMain;
import Android.AndroidStorage;
import Components.Component;
import World.GameWorld;
import World.StartGameWorld;

/**
 * 此类在游戏开始前先被初始化,保证所有静态变量正确引用,系统层应只初始化一个此类对象
 */
public class SystemManager {
    private static AndroidMain main;

    private static ScriptSystem scriptSystem;
    private static AndroidStorage storage;
    private static ViewSystem viewSystem;

    private static HashMap<String,ArrayList<Component>> components;

    private static GameWorld gameWorld;
    public SystemManager(AndroidMain main)
    {
        this.main=main;

        /*组件列表初始化*/
        components=new HashMap<>();

        /*各系统初始化*/
        scriptSystem=new ScriptSystem();
        storage=main.getStorage();
        viewSystem=new ViewSystem();

        /*游戏场景初始化*/
        setGameWorld(new StartGameWorld()); //-------------定义所有场景都从StartGameWorld类开始-------------
    }

    public static ScriptSystem getScriptSystem(){
        return scriptSystem;
    }

    public static AndroidStorage getStorage(){
        return storage;
    }

    public static ViewSystem getViewSystem(){
        return viewSystem;
    }

    public static GameWorld getCurrentGameWorld() {return gameWorld;}

    public static void setGameWorld(GameWorld world)
    {
        gameWorld=world;
    }

    public static ArrayList getComponents(String name)
    {
        return components.get(name);
    }

    /**
     * 调用此函数应确保组件曾被添加至列表
     * @param component
     */
    public static void removeComponent(Component component)
    {
        ArrayList arrayList=components.get(component.getClass().getSimpleName());
        arrayList.remove(component);
    }

    /**
     * 所有系统所对应的组件都应先调用此函数新建对应组件列表,如果已经存在该组件列表则直接返回
     * @param name
     * @return 返回对应的组件列表
     */
    public static ArrayList addComponentList(String name)
    {
        if(!components.containsKey(name))
        {
            ArrayList<Component> arrayList=new ArrayList<>();
            components.put(name,arrayList);
            return arrayList;
        }
        else {
            return components.get(name);
        }
    }

    public static void addComponent(Component component)
    {
        Log.d("add",component.getClass().getSimpleName());
        if(!components.containsKey(component.getClass().getSimpleName()))
        {
            ArrayList<Component> arrayList=new ArrayList<>();
            arrayList.add(component);
            components.put(component.getClass().getSimpleName(),arrayList);
        }
        else {
            components.get(component.getClass().getSimpleName()).add(component);
        }
    }

    public static AndroidMain getMainActivity(){
        return main;
    }
}
