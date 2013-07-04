package no.runsafe.toybox.horses;

import net.minecraft.server.v1_6_R1.EntityHorse;
import net.minecraft.server.v1_6_R1.EntityTypes;
import net.minecraft.server.v1_6_R1.World;
import no.runsafe.framework.minecraft.RunsafeLocation;
import org.bukkit.craftbukkit.v1_6_R1.CraftWorld;

public class HorseSpawner
{
	public EntityHorse spawnHorse(RunsafeLocation location, SpawnableHorseType type, SpawnableHorseVariant variant)
	{
		World world = ((CraftWorld) location.getWorld().getRaw()).getHandle();
		EntityHorse horse = (EntityHorse) EntityTypes.createEntityByName("EntityHorse", world);
		horse.p(type.ordinal());

		if (type == SpawnableHorseType.NORMAL)
			horse.q(variant.getNbtValue());

		horse.teleportTo(location.getRaw(), false);
		return horse;
	}

	public void tameHorse(EntityHorse horse)
	{
		horse.j(true);
	}
}
