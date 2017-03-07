package no.runsafe.toybox.horses;

import net.minecraft.server.v1_8_R3.EntityHorse;
import net.minecraft.server.v1_8_R3.EntityTypes;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.World;
import no.runsafe.framework.api.ILocation;
import no.runsafe.framework.internal.wrapper.ObjectUnwrapper;
import org.bukkit.Location;

public class HorseSpawner
{
	public void spawnHorse(ILocation location, SpawnableHorseType type, SpawnableHorseVariant variant, boolean tamed)
	{
		World world = ObjectUnwrapper.getMinecraft(location.getWorld());
		EntityHorse horse = (EntityHorse) EntityTypes.createEntityByName("EntityHorse", world);
		horse.setType(type.ordinal());

		if (type == SpawnableHorseType.NORMAL)
			horse.setVariant(variant.getNbtValue());

		NBTTagCompound tags = new NBTTagCompound();
		horse.b(tags);
		tags.setBoolean("Tame", tamed);
		horse.a(tags);

		horse.teleportTo((Location) ObjectUnwrapper.convert(location), false);
	}
}
