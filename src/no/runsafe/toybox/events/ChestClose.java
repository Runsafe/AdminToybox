package no.runsafe.toybox.events;

import no.runsafe.framework.event.inventory.IChestClosed;
import no.runsafe.framework.output.IOutput;
import no.runsafe.framework.server.inventory.RunsafeInventory;
import no.runsafe.framework.server.player.RunsafePlayer;
import no.runsafe.toybox.handlers.CarePackageHandler;

import java.util.logging.Level;

public class ChestClose implements IChestClosed
{
	public ChestClose(CarePackageHandler handler, IOutput output)
	{
		this.handler = handler;
		this.output = output;
	}

	@Override
	public void OnChestClosed(RunsafePlayer player, RunsafeInventory inventory)
	{
		this.output.outputDebugToConsole("Detected chest close by " + player.getName(), Level.FINE);
		if (this.handler.PlayerHasOpenCarePackage(player))
			this.handler.DropPackage(player);
	}

	private CarePackageHandler handler;
	private IOutput output;
}
