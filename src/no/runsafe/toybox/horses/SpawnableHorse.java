package no.runsafe.toybox.horses;

import net.minecraft.server.v1_6_R1.World;

public class SpawnableHorse extends net.minecraft.server.v1_6_R1.EntityHorse
{
	public SpawnableHorse(World world, SpawnableHorseType type, SpawnableHorseVariant variant)
	{
		super(world);
		this.p(type.ordinal());

		if (type == SpawnableHorseType.NORMAL)
			this.q(variant.ordinal());
	}
}
