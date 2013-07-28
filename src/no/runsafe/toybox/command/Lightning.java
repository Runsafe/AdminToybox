package no.runsafe.toybox.command;

import no.runsafe.framework.api.command.ExecutableCommand;
import no.runsafe.framework.api.command.ICommandExecutor;
import no.runsafe.framework.api.command.argument.OptionalArgument;
import no.runsafe.framework.api.command.argument.PlayerArgument;
import no.runsafe.framework.minecraft.RunsafeServer;
import no.runsafe.framework.minecraft.RunsafeWorld;
import no.runsafe.framework.minecraft.player.RunsafeAmbiguousPlayer;
import no.runsafe.framework.minecraft.player.RunsafePlayer;

import java.util.Map;

public class Lightning extends ExecutableCommand
{
	public Lightning()
	{
		super(
			"lightning", "Fires lightning at a player, the location you are looking, or a coordinate", "runsafe.toybox.lightning",
			new PlayerArgument("playerOrX", false), new OptionalArgument("y"), new OptionalArgument("z")
		);
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
		if (executor instanceof RunsafePlayer)
		{
			RunsafePlayer player = (RunsafePlayer) executor;
			player.getWorld().strikeLightning(player.getTarget().getLocation());
			return null;
		}
		return "No block in sight";
	}

	private String StrikeCoordinates(ICommandExecutor executor, String x, String y, String z)
	{
		if (executor instanceof RunsafePlayer)
		{
			RunsafeWorld world = ((RunsafePlayer) executor).getWorld();
			if (world != null)
				world.strikeLightning(world.getLocation(Double.valueOf(x), Double.valueOf(y), Double.valueOf(z)));
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
