package de.craftlancer.speedapi;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class SpeedSetTask extends BukkitRunnable
{
    private SpeedAPI plugin;
    
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
            float s = speed - p.getWalkSpeed();
            
            if (s > 0.002F)
                speed = p.getWalkSpeed() + 0.002F;
            else if (s < -0.002F)
                speed = p.getWalkSpeed() - 0.002F;
            
            p.setWalkSpeed(speed);
        }
    }
    
}
