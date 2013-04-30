package no.runsafe.toybox.events;

import no.runsafe.framework.event.inventory.IChestClosed;
import no.runsafe.framework.server.inventory.RunsafeInventory;
import no.runsafe.framework.server.player.RunsafePlayer;
import no.runsafe.toybox.handlers.CarePackageHandler;

public class ChestClose implements IChestClosed
{
	public ChestClose(CarePackageHandler handler)
	{
		this.handler = handler;
	}

	@Override
	public void OnChestClosed(RunsafePlayer player, RunsafeInventory inventory)
	{
		this.handler.DropPackage(player);
	}

	private CarePackageHandler handler;
}
