package no.runsafe.toybox.events;

import no.runsafe.framework.event.block.IBlockRedstone;
import no.runsafe.framework.server.RunsafeLocation;
import no.runsafe.framework.server.RunsafeWorld;
import no.runsafe.framework.server.block.RunsafeBlock;
import no.runsafe.framework.server.block.RunsafeBlockState;
import no.runsafe.framework.server.block.RunsafeChest;
import no.runsafe.framework.server.event.block.RunsafeBlockRedstoneEvent;
import org.bukkit.Effect;

public class BlockRedstone implements IBlockRedstone
{
	@Override
	public void OnBlockRedstoneEvent(RunsafeBlockRedstoneEvent event)
	{
		RunsafeBlock block = event.getBlock();
		RunsafeWorld world = block.getWorld();
		RunsafeLocation location = block.getLocation();
		try
		{
			if (event.getNewCurrent() > 0)
			{
				RunsafeBlockState blockState = block.getBlockState();
				if (blockState instanceof RunsafeChest)
				{
					String name = ((RunsafeChest) blockState).getInventory().getTitle();
					if (name.startsWith("Sound:"))
					{
						if (name.endsWith(":Ghast"))
							world.playEffect(location, Effect.GHAST_SHRIEK, 0);
					}
				}
			}
		}
		catch (NullPointerException e)
		{
			// Ignore
		}
	}
}
