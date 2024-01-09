package no.runsafe.toybox.command;

import no.runsafe.framework.api.block.IBlock;
import no.runsafe.framework.api.command.argument.IArgumentList;
import no.runsafe.framework.api.command.player.PlayerCommand;
import no.runsafe.framework.api.event.player.IPlayerInteractEvent;
import no.runsafe.framework.api.player.IPlayer;
import no.runsafe.framework.minecraft.event.player.RunsafePlayerInteractEvent;
import no.runsafe.toybox.handlers.LockedObjectHandler;

import java.util.ArrayList;
import java.util.List;

public class Lock extends PlayerCommand implements IPlayerInteractEvent
{
	public Lock(LockedObjectHandler handler)
	{
		super("lock", "Locks an object.", "runsafe.toybox.lock");
		this.handler = handler;
	}

	@Override
	public String OnExecute(IPlayer executor, IArgumentList parameters)
	{
		if (this.lockingPlayers.contains(executor))
		{
			this.lockingPlayers.remove(executor);
			return "&eLocking disabled.";
		}

		this.lockingPlayers.add(executor);
		return "&2Locking enabled: Right click objects to lock them.";
	}

	@Override
	public void OnPlayerInteractEvent(RunsafePlayerInteractEvent event)
	{
		IBlock block = event.getBlock();
		IPlayer player = event.getPlayer();

		if (block == null)
			return;

		if (!this.lockingPlayers.contains(player))
		{
			if (!this.handler.isLockedBlock(block))
				return;

			player.sendColouredMessage("&cThis object has been locked with magic.");
			event.cancel();
			return;
		}

		if (this.handler.isLockedBlock(block))
		{
			this.handler.unlockBlock(block);
			player.sendColouredMessage("&2Object unlocked");
			event.cancel();
			return;
		}

		if (this.handler.canLockBlock(block))
		{
			this.handler.lockBlock(block);
			player.sendColouredMessage("&2Object locked.");
		}
		else
		{
			player.sendColouredMessage("&cYou cannot lock that object.");
		}
		event.cancel();
	}

	private final List<IPlayer> lockingPlayers = new ArrayList<>();
	private final LockedObjectHandler handler;
}
