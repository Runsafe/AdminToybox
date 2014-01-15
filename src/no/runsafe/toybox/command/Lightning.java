package no.runsafe.toybox.command;

import no.runsafe.framework.api.IWorld;
import no.runsafe.framework.api.command.ExecutableCommand;
import no.runsafe.framework.api.command.ICommandExecutor;
import no.runsafe.framework.api.command.argument.IArgumentList;
import no.runsafe.framework.api.command.argument.OptionalArgument;
import no.runsafe.framework.api.player.IAmbiguousPlayer;
import no.runsafe.framework.api.player.IPlayer;

public class Lightning extends ExecutableCommand
{
	public Lightning()
	{
		super(
			"lightning", "Fires lightning at a player, the location you are looking, or a coordinate", "runsafe.toybox.lightning",
			new OptionalArgument("playerOrX"), new OptionalArgument("y"), new OptionalArgument("z")
		);
	}

	@Override
	public String getUsageCommandParams()
	{
		return "(<player>|<x> <y> <z>)";
	}

	@Override
	public String OnExecute(ICommandExecutor executor, IArgumentList parameters)
	{
		if (parameters.size() == 0)
			return StrikeTarget(executor);
		if (parameters.size() == 1)
			return StrikePlayer(parameters.getPlayer("playerOrX"));
		if (parameters.size() == 3)
			return StrikeCoordinates(executor, parameters.get("playerOrX"), parameters.get("y"), parameters.get("z"));

		return getUsage(executor);
	}

	private String StrikeTarget(ICommandExecutor executor)
	{
		if (executor instanceof IPlayer)
		{
			IPlayer player = (IPlayer) executor;
			player.getWorld().strikeLightning(player.getTargetBlock().getLocation());
			return null;
		}
		return "No block in sight";
	}

	private String StrikeCoordinates(ICommandExecutor executor, String x, String y, String z)
	{
		if (executor instanceof IPlayer)
		{
			IWorld world = ((IPlayer) executor).getWorld();
			if (world != null)
				world.strikeLightning(world.getLocation(Double.valueOf(x), Double.valueOf(y), Double.valueOf(z)));
			return null;
		}
		return "The console isn't in a world..";
	}

	private String StrikePlayer(IPlayer player)
	{
		if (player instanceof IAmbiguousPlayer)
			return player.toString();
		if (player == null || !player.isOnline())
			return "Target player is offline!";
		player.strikeWithLightning(false);
		return "Thy will be done.";
	}
}
