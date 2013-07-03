package no.runsafe.toybox.horses;

import net.minecraft.server.v1_6_R1.World;

public class SpawnableHorse extends net.minecraft.server.v1_6_R1.EntityHorse
{
	public SpawnableHorse(World world, int type, int variant, int markings)
	{
		super(world);

		this.p(type);

		if (type == 0)
			this.q((markings * 256) + variant);
	}
}
