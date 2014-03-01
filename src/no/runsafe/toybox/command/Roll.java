package no.runsafe.toybox.command;

import no.runsafe.framework.api.ILocation;
import no.runsafe.framework.api.command.argument.IArgumentList;
import no.runsafe.framework.api.command.player.PlayerCommand;
import no.runsafe.framework.api.player.IPlayer;

import java.util.Random;

public class Roll extends PlayerCommand
{
	public Roll()
	{
		super("roll", "Roll to produce a random number!", "runsafe.toybox.roll");
	}

	@Override
	public String OnExecute(IPlayer executor, IArgumentList parameters)
	{
		ILocation playerLocation = executor.getLocation();

		if (playerLocation == null)
			return null;

		String message = executor.getPrettyName() + " &erolls " + random.nextInt(101) + " (0-100).";
		for (IPlayer targetPlayer : playerLocation.getPlayersInRange(20D))
			targetPlayer.sendColouredMessage(message);

		return null;
	}

	protected final Random random = new Random();
}
