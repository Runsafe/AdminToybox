package no.runsafe.toybox.command;

import no.runsafe.framework.api.command.ExecutableCommand;

import no.runsafe.framework.api.command.ICommandExecutor;
import no.runsafe.framework.api.command.argument.IArgumentList;
import no.runsafe.framework.api.command.argument.Player;
import no.runsafe.framework.api.player.IPlayer;

public class BitePlayer extends ExecutableCommand
{
	public BitePlayer()
	{
		super(
			"bite", "Bites a lpayer", "runsafe.toybox.bite",
			new Player(PLAYER).onlineOnly()
		);
	}

	private static final String PLAYER = "player";

	@Override
	public String OnExecute(ICommandExecutor executor, IArgumentList parameters)
	{
		IPlayer player = parameters.getValue(PLAYER);
		if (player == null || player.getWorld() == null)
			return "&cTarget player is offline!";
		player.getWorld().spawnCreature(player.getLocation(), "EvokerFangs");
		return "&aThy will be bit.";
	}
}
