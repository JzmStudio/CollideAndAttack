package Systems;

import Android.AndroidMain;
import Android.AndroidStorage;
import World.GameWorld;
import World.StartGameWorld;

/**
 * 此类在游戏开始前先被初始化,保证所有静态变量正确引用,系统层应只初始化一个此类对象
 */
public class SystemManager {
    private AndroidMain main;
    private static ScriptSystem scriptSystem;
    private static AndroidStorage storage;

    private static GameWorld gameWorld;
    public SystemManager(AndroidMain main)
    {
        /*各系统初始化*/
        scriptSystem=new ScriptSystem();
        main.getUpdateView().addToUpdateList(scriptSystem);
        main.getUpdateView().addToStartList(scriptSystem);
        storage=main.getStorage();

        /*游戏场景初始化*/
        gameWorld=new StartGameWorld(); //定义所有场景都从StartGameWorld类开始
    }

    public static ScriptSystem getScriptSystem(){
        return scriptSystem;
    }

    public static void changeGameWorld(GameWorld world)
    {
        gameWorld=world;
    }

    public static AndroidStorage getStorage(){
        return storage;
    }
}
