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

			if (title.startsWith("Infinite:"))
			{
				String[] data = title.split(":");
				if (data.length > 1)
				{
					int itemID = Integer.valueOf(data[1]);
					RunsafeItemStack items = new RunsafeItemStack(itemID, 64, (short) 0);

					if (data.length > 2)
						items.setDurability(Short.valueOf(data[2]));

					inventory.addItems(items);
				}
			}
		}
		return true;
	}
}
