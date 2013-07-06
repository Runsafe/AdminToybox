package no.runsafe.toybox.horses;

import net.minecraft.server.v1_6_R1.EntityHorse;
import net.minecraft.server.v1_6_R1.EntityTypes;
import net.minecraft.server.v1_6_R1.NBTTagCompound;
import net.minecraft.server.v1_6_R1.World;
import no.runsafe.framework.minecraft.RunsafeLocation;
import org.bukkit.craftbukkit.v1_6_R1.CraftWorld;

public class HorseSpawner
{
	public void spawnHorse(RunsafeLocation location, SpawnableHorseType type, SpawnableHorseVariant variant, boolean tamed)
	{
		World world = ((CraftWorld) location.getWorld().getRaw()).getHandle();
		EntityHorse horse = (EntityHorse) EntityTypes.createEntityByName("EntityHorse", world);
		horse.p(type.ordinal());

		if (type == SpawnableHorseType.NORMAL)
			horse.q(variant.getNbtValue());

		NBTTagCompound tags = new NBTTagCompound();
		horse.b(tags);
		tags.setBoolean("Tame", tamed);
		horse.a(tags);

		horse.teleportTo(location.getRaw(), false);
	}
}
