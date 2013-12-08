package no.runsafe.toybox.command;

import no.runsafe.framework.api.IServer;
import no.runsafe.framework.api.IWorld;
import no.runsafe.framework.api.command.ExecutableCommand;
import no.runsafe.framework.api.command.ICommandExecutor;
import no.runsafe.framework.api.command.argument.OptionalArgument;
import no.runsafe.framework.api.command.argument.PlayerArgument;
import no.runsafe.framework.api.player.IAmbiguousPlayer;
import no.runsafe.framework.api.player.IPlayer;

import java.util.Map;

public class Lightning extends ExecutableCommand
{
	public Lightning(IServer server)
	{
		super(
			"lightning", "Fires lightning at a player, the location you are looking, or a coordinate", "runsafe.toybox.lightning",
			new PlayerArgument("playerOrX", false), new OptionalArgument("y"), new OptionalArgument("z")
		);
		this.server = server;
	}

	@Override
	public String getUsageCommandParams()
	{
		return "(<player>|<x> <y> <z>)";
	}

	@Override
	public String OnExecute(ICommandExecutor executor, Map<String, String> parameters)
	{
		if (parameters.size() == 0)
			return StrikeTarget(executor);
		if (parameters.size() == 1)
			return StrikePlayer(parameters.get("playerOrX"));
		if (parameters.size() == 3)
			return StrikeCoordinates(executor, parameters.get("playerOrX"), parameters.get("y"), parameters.get("z"));

		return getUsage(executor);
	}

	private String StrikeTarget(ICommandExecutor executor)
	{
		if (executor instanceof IPlayer)
		{
			IPlayer player = (IPlayer) executor;
			player.getWorld().strikeLightning(player.getTarget().getLocation());
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

	private String StrikePlayer(String argument)
	{
		IPlayer target = server.getPlayer(argument);
		if (target instanceof IAmbiguousPlayer)
			return target.toString();
		if (target == null || !target.isOnline())
			return "Target player is offline!";
		target.strikeWithLightning(false);
		return "Thy will be done.";
	}

	private final IServer server;
}
