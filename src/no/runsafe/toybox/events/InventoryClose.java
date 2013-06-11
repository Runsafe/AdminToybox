package no.runsafe.toybox.events;

import no.runsafe.framework.api.event.inventory.IInventoryClosed;
import no.runsafe.framework.minecraft.event.inventory.RunsafeInventoryCloseEvent;
import no.runsafe.framework.minecraft.player.RunsafePlayer;
import no.runsafe.toybox.handlers.CarePackageHandler;

public class InventoryClose implements IInventoryClosed
{
	public InventoryClose(CarePackageHandler handler)
	{
		this.handler = handler;
	}

	@Override
	public void OnInventoryClosed(RunsafeInventoryCloseEvent event)
	{
		RunsafePlayer player = event.getPlayer();
		if (this.handler.PlayerHasOpenCarePackage(player))
			this.handler.DropPackage(player);
	}

	private final CarePackageHandler handler;
}
