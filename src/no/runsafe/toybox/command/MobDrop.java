package no.runsafe.toybox.command;

import no.runsafe.framework.api.command.argument.IArgumentList;
import no.runsafe.framework.api.command.argument.RequiredArgument;
import no.runsafe.framework.api.command.player.PlayerCommand;
import no.runsafe.framework.api.player.IPlayer;
import no.runsafe.toybox.handlers.MobDropHandler;

public class MobDrop extends PlayerCommand
{
	public MobDrop(MobDropHandler handler)
	{
		super(
			"mobdrop", "Drops a block full of mobs", "runsafe.toybox.mobdrop",
			new RequiredArgument("mobType"), new RequiredArgument("amount")
		);
		this.handler = handler;
	}

	@Override
	public String OnExecute(IPlayer executor, IArgumentList parameters)
	{
		handler.createMobDropper(executor.getLocation(), parameters.get("mobType"), Integer.valueOf(parameters.get("amount")));
		return null;
	}

	private final MobDropHandler handler;
}
