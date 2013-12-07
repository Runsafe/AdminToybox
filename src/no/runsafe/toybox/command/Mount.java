package no.runsafe.toybox.command;

import no.runsafe.framework.api.command.argument.PlayerArgument;
import no.runsafe.framework.api.command.player.PlayerCommand;
import no.runsafe.framework.api.player.IPlayer;
import no.runsafe.framework.minecraft.RunsafeServer;

import java.util.Map;

public class Mount extends PlayerCommand
{
	public Mount()
	{
		super(
			"mount", "Mounts you on the given player or entity ID", "runsafe.toybox.mount",
			new PlayerArgument("target", true)
		);
	}

	@Override
	public String OnExecute(IPlayer executor, Map<String, String> parameters)
	{
		IPlayer player = RunsafeServer.Instance.getOnlinePlayer(executor, parameters.get("target"));
		if (player == null)
		{
			try
			{
				executor.getWorld().getEntityById(Integer.parseInt(parameters.get("target"))).setPassenger(executor);
				return null;
			}
			catch (Exception e)
			{
				return "Unable to find target..";
			}
		}
		player.setPassenger(executor);
		return null;
	}
}
