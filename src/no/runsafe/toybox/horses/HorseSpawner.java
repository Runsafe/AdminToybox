package no.runsafe.toybox.horses;

import net.minecraft.server.v1_6_R3.EntityHorse;
import net.minecraft.server.v1_6_R3.EntityTypes;
import net.minecraft.server.v1_6_R3.NBTTagCompound;
import net.minecraft.server.v1_6_R3.World;
import no.runsafe.framework.internal.wrapper.ObjectUnwrapper;
import no.runsafe.framework.minecraft.RunsafeLocation;
import org.bukkit.craftbukkit.v1_6_R3.CraftWorld;

public class HorseSpawner
{
	public void spawnHorse(RunsafeLocation location, SpawnableHorseType type, SpawnableHorseVariant variant, boolean tamed)
	{
		World world = ObjectUnwrapper.convert(location.getWorld());
		EntityHorse horse = (EntityHorse) EntityTypes.createEntityByName("EntityHorse", world);
		horse.setType(type.ordinal());

		if (type == SpawnableHorseType.NORMAL)
			horse.setVariant(variant.getNbtValue());

		NBTTagCompound tags = new NBTTagCompound();
		horse.b(tags);
		tags.setBoolean("Tame", tamed);
		horse.a(tags);

		horse.teleportTo(location.getRaw(), false);
	}
}
