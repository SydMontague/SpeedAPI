package de.craftlancer.speedapi;

import org.bukkit.entity.Player;

public abstract class SpeedModifier
{
    private int priority;
    
    public SpeedModifier(int priority)
    {
        if(priority <= 0)
            throw new IllegalArgumentException("Priority must be greater than 0");
        
        this.priority = priority;
    }
    
    public int getPriority()
    {
        return priority;
    }

    public abstract float getSpeedChange(Player p, float speed);

    public abstract boolean isApplicable(Player p);

    public abstract boolean isInstant(Player p);
}
