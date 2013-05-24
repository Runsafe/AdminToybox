package no.runsafe.toybox.events;

import no.runsafe.framework.event.block.IBlockDispense;
import no.runsafe.framework.server.block.RunsafeBlock;
import no.runsafe.framework.server.block.RunsafeBlockState;
import no.runsafe.framework.server.block.RunsafeDispenser;
import no.runsafe.framework.server.inventory.RunsafeInventory;
import no.runsafe.framework.server.item.RunsafeItemStack;

public class Dispense implements IBlockDispense
{
	@Override
	public boolean OnBlockDispense(RunsafeBlock block, RunsafeItemStack item)
	{
		RunsafeBlockState blockState = block.getBlockState();
		if (blockState instanceof RunsafeDispenser)
		{
			RunsafeDispenser dispenser = (RunsafeDispenser) blockState;
			RunsafeInventory inventory = dispenser.getInventory();
			String title = inventory.getTitle();

			if (title.equalsIgnoreCase("Infinite"))
				inventory.addItems(item.clone());
		}
		return true;
	}
}
