package no.runsafe.toybox.handlers;

import no.runsafe.framework.server.RunsafeLocation;
import no.runsafe.framework.server.entity.RunsafeEntity;
import no.runsafe.toybox.MobDropData;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;

import java.util.HashMap;

public class MobDropHandler
{
	public MobDropHandler()
	{
		this.fallingMobDrops = new HashMap<Integer, MobDropData>();
	}

	public void createMobDropper(RunsafeLocation location, String entityName, Integer amount)
	{
		RunsafeEntity block = location.getWorld().spawnFallingBlock(location, Material.OBSIDIAN, (byte) 0);
		MobDropData data = new MobDropData(EntityType.fromName(entityName), amount);
		this.fallingMobDrops.put(block.getEntityId(), data);
	}

	public boolean entityIsMobDropper(Integer entityID)
	{
		return this.fallingMobDrops.containsKey(entityID);
	}

	public void processMobDropper(Integer entityID, RunsafeLocation location)
	{
		MobDropData data = this.fallingMobDrops.get(entityID);
		int current = 0;

		while (current < data.getMobAmount())
		{
			location.getWorld().spawnCreature(location, data.getEntityType().getTypeId());
			current++;
		}

		this.fallingMobDrops.remove(entityID);
	}

	private final HashMap<Integer, MobDropData> fallingMobDrops;
}
