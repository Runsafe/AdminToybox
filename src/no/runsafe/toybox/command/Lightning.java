package no.runsafe.toybox.command;

import no.runsafe.framework.command.ExecutableCommand;
import no.runsafe.framework.server.ICommandExecutor;
import no.runsafe.framework.server.RunsafeServer;
import no.runsafe.framework.server.player.RunsafeAmbiguousPlayer;
import no.runsafe.framework.server.player.RunsafePlayer;

import java.util.HashMap;

public class Lightning extends ExecutableCommand
{
	public Lightning()
	{
		super("lightning", "Fires lightning at a player, the location you are looking, or a coordinate", "runsafe.toybox.lightning");
	}

	@Override
	public String getUsageCommandParams()
	{
		return "(<player>|<x> <y> <z>)";
	}

	@Override
	public String OnExecute(ICommandExecutor executor, HashMap<String, String> parameters)
	{
		return null;
	}

	@Override
	public String OnExecute(ICommandExecutor executor, HashMap<String, String> parameters, String[] arguments)
	{
		if (arguments.length == 0)
			return StrikeTarget(executor);
		if (arguments.length == 1)
			return StrikePlayer(arguments[0]);
		if (arguments.length == 3)
			return StrikeCoordinates(executor, arguments[0], arguments[1], arguments[2]);

		return getUsage();
	}

	private String StrikeTarget(ICommandExecutor executor)
	{
		if (executor instanceof RunsafePlayer)
		{
			RunsafePlayer player = (RunsafePlayer) executor;
			player.getWorld().strikeLightning(player.getTarget().getLocation());
			return null;
		}
		return "No block in sight";
	}

	private String StrikeCoordinates(ICommandExecutor executor, String argument, String argument1, String argument2)
	{
		if (executor instanceof RunsafePlayer)
		{
			return null;
		}
		return "The console isn't in a world..";
	}

	private String StrikePlayer(String argument)
	{
		RunsafePlayer target = RunsafeServer.Instance.getPlayer(argument);
		if (target instanceof RunsafeAmbiguousPlayer)
			return target.toString();
		if (!target.isOnline())
			return "Target player is offline!";
		target.strikeWithLightning(false);
		return "Thy will be done.";
	}
}
