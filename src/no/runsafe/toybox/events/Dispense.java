package no.runsafe.toybox.events;

import no.runsafe.framework.api.event.block.IBlockDispense;
import no.runsafe.framework.minecraft.block.RunsafeBlock;
import no.runsafe.framework.minecraft.block.RunsafeBlockState;
import no.runsafe.framework.minecraft.block.RunsafeDispenser;
import no.runsafe.framework.minecraft.block.RunsafeDropper;
import no.runsafe.framework.minecraft.inventory.RunsafeInventory;
import no.runsafe.framework.minecraft.item.meta.RunsafeMeta;

public class Dispense implements IBlockDispense
{
	@Override
	public boolean OnBlockDispense(RunsafeBlock block, RunsafeMeta item)
	{
		RunsafeBlockState blockState = block.getBlockState();
		RunsafeInventory inventory = null;

		if (blockState instanceof RunsafeDispenser)
			inventory = ((RunsafeDispenser) blockState).getInventory();
		else if (blockState instanceof RunsafeDropper)
			inventory = ((RunsafeDropper) blockState).getInventory();

		if (inventory != null)
			if (inventory.getTitle().equalsIgnoreCase("Infinite"))
				inventory.addItems(item.clone());

		return true;
	}
}
