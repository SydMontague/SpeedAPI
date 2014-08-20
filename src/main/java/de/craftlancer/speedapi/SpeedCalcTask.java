package de.craftlancer.speedapi;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class SpeedCalcTask extends BukkitRunnable
{
    private SpeedAPI plugin;
    
    public SpeedCalcTask(SpeedAPI plugin)
    {
        this.plugin = plugin;
    }
    
    @Override
    public void run()
    {
        int max = 0;
        for (Integer i : plugin.getPriorities().keySet())
            max = i > max ? i : max;
        
        for (Player p : plugin.getServer().getOnlinePlayers())
        {
            float speed = plugin.getDefaultSpeed();
            for (int i = max; i > 0; i--)
            {
                float newspeed = speed;
                
                if (plugin.getPriorities().containsKey(i))
                    for (SpeedModifier mod : plugin.getPriorities().get(i))
                    {
                        float change = mod.getSpeedChange(p, speed);
                        
                        newspeed += change;
                        if (mod.isInstant(p))
                            p.setWalkSpeed(p.getWalkSpeed() + change);
                    }
                
                speed = newspeed;
            }
            
            if (speed > 1)
                speed = 1;
            else if (speed < -1)
                speed = -1;
            
            plugin.setSpeed(p, speed);
        }
    }
    
}
