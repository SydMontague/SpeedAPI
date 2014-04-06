package de.craftlancer.speedapi;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class SpeedSetTask extends BukkitRunnable
{
    private SpeedAPI plugin;
    private final static float CHANGE_PER_TICK = 0.002F;
    
    public SpeedSetTask(SpeedAPI plugin)
    {
        this.plugin = plugin;
    }
    
    @Override
    public void run()
    {
        for (Player p : plugin.getServer().getOnlinePlayers())
        {
            float speed = plugin.getSpeed(p);
            float delta = speed - p.getWalkSpeed();
            
            if (delta > CHANGE_PER_TICK)
                speed = p.getWalkSpeed() + CHANGE_PER_TICK;
            else if (delta < -CHANGE_PER_TICK)
                speed = p.getWalkSpeed() - CHANGE_PER_TICK;
            
            p.setWalkSpeed(speed);
        }
    }
    
}
