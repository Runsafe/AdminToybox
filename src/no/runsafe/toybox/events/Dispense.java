package no.runsafe.toybox.events;

import no.runsafe.framework.event.block.IBlockDispense;
import no.runsafe.framework.server.RunsafeServer;
import no.runsafe.framework.server.block.RunsafeBlock;
import no.runsafe.framework.server.block.RunsafeBlockState;
import no.runsafe.framework.server.block.RunsafeDispenser;
import no.runsafe.framework.server.block.RunsafeDropper;
import no.runsafe.framework.server.inventory.RunsafeInventory;
import no.runsafe.framework.server.item.meta.RunsafeMeta;

public class Dispense implements IBlockDispense
{
	@Override
	public boolean OnBlockDispense(RunsafeBlock block, RunsafeMeta item)
	{
		RunsafeBlockState blockState = block.getBlockState();
		RunsafeInventory inventory = null;

		RunsafeServer.Instance.broadcastMessage(blockState.getClass().getName());

		if (blockState instanceof RunsafeDispenser)
		{
			inventory = ((RunsafeDispenser) blockState).getInventory();
			RunsafeServer.Instance.broadcastMessage("Dispenser detected");
		}
		else if (blockState instanceof RunsafeDropper)
		{
			inventory = ((RunsafeDropper) blockState).getInventory();
			RunsafeServer.Instance.broadcastMessage("Dropper detected");
		}

		if (inventory != null)
			if (inventory.getTitle().equalsIgnoreCase("Infinite"))
				inventory.addItems(item.clone());

		return true;
	}
}
