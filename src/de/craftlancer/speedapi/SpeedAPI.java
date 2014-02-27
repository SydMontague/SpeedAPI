package de.craftlancer.speedapi;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;


public class SpeedAPI extends JavaPlugin
{
    private float defaultSpeed = 0.2F;
    private Map<String, SpeedModifier> modMap = new HashMap<String, SpeedModifier>();
    private Map<Integer, List<SpeedModifier>> prioMap = new HashMap<Integer, List<SpeedModifier>>();
    private Map<String, Float> speedMap = new HashMap<String, Float>();
    private static SpeedAPI instance;
    private SpeedCalcTask calcTask;
    private SpeedSetTask setTask;
    
    @Override
    public void onEnable()
    {
        instance = this;
        calcTask = new SpeedCalcTask(this);
        calcTask.runTaskTimer(this, 10L, 10L);
        setTask = new SpeedSetTask(this);
        setTask.runTaskTimer(this, 1L, 1L);
    }
    
    @Override
    public void onDisable()
    {
        getServer().getScheduler().cancelTasks(this);
    }
    
    public static SpeedAPI getInstance()
    {
        return instance;
    }
        
    public static void addModifier(String name, SpeedModifier mod)
    {
        instance.modMap.put(name, mod);
        if(!instance.prioMap.containsKey(mod.getPriority()))
            instance.prioMap.put(mod.getPriority(), new LinkedList<SpeedModifier>());
        instance.prioMap.get(mod.getPriority()).add(mod);
    }
        
    public static void removeModifier(String name)
    {
        SpeedModifier mod = instance.modMap.get(name);
        instance.prioMap.get(mod.getPriority()).remove(mod);
        instance.modMap.remove(name);
    }

    public Map<Integer, List<SpeedModifier>> getPriorities()
    {
        return prioMap;
    }

    public float getDefaultSpeed()
    {
        return defaultSpeed;
    }

    public float getSpeed(Player p)
    {
        if(!speedMap.containsKey(p.getName()))
            return getDefaultSpeed();
        return speedMap.get(p.getName());
    }
    
    public void setSpeed(Player p, float f)
    {
        speedMap.put(p.getName(), f);
    }
}
