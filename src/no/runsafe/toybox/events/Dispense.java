package no.runsafe.toybox.events;

import no.runsafe.framework.api.block.IBlock;
import no.runsafe.framework.api.event.block.IBlockDispense;
import no.runsafe.framework.api.minecraft.IInventoryHolder;
import no.runsafe.framework.minecraft.inventory.RunsafeInventory;
import no.runsafe.framework.minecraft.item.meta.RunsafeMeta;

public class Dispense implements IBlockDispense
{
	@Override
	public boolean OnBlockDispense(IBlock block, RunsafeMeta item)
	{
		RunsafeInventory inventory = null;

		if (block instanceof IInventoryHolder)
			inventory = ((IInventoryHolder) block).getInventory();

		if (inventory != null)
			if (inventory.getTitle().equalsIgnoreCase("Infinite"))
				inventory.addItems(item.clone());

		return true;
	}
}
