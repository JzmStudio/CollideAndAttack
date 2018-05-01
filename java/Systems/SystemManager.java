package Systems;

import Android.AndroidMain;

public class SystemManager {
    private AndroidMain main;
    public static final ScriptSystem scriptSystem = new ScriptSystem();
    public SystemManager(AndroidMain main)
    {
        main.getUpdateView().addToUpdateList(scriptSystem);
    }
}
