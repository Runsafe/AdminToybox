package no.runsafe.toybox.command;

import no.runsafe.framework.api.ILocation;
import no.runsafe.framework.api.command.IBranchingExecution;
import no.runsafe.framework.api.command.argument.IArgumentList;
import no.runsafe.framework.api.command.argument.OptionalArgument;
import no.runsafe.framework.api.command.player.PlayerCommand;
import no.runsafe.framework.api.player.IPlayer;

import java.util.Random;

public class Roll extends PlayerCommand implements IBranchingExecution
{
	public Roll()
	{
		super("roll", "Roll to produce a random number!", "runsafe.toybox.roll", new OptionalArgument("first"), new OptionalArgument("second"));
	}

	@Override
	public String OnExecute(IPlayer executor, IArgumentList parameters)
	{
		ILocation playerLocation = executor.getLocation();

		if (playerLocation == null)
			return null;

		int low = 0;
		int high = 100;

		if (parameters.containsKey("first"))
		{
			if (parameters.containsKey("second"))
			{
				high = Integer.parseInt(parameters.get("second"));
				low = Integer.parseInt(parameters.get("first"));
			}
			else
			{
				high = Integer.parseInt(parameters.get("first"));
			}
		}

		String message = String.format(executor.getPrettyName() + " &erolls %s (%s-%s).", random.nextInt(high) + low, low, high);
		for (IPlayer targetPlayer : playerLocation.getPlayersInRange(20D))
			targetPlayer.sendColouredMessage(message);

		return null;
	}

	protected final Random random = new Random();
}
