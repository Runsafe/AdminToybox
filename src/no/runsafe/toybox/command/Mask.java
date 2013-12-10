package no.runsafe.toybox.command;

import no.runsafe.framework.api.IServer;
import no.runsafe.framework.api.command.ExecutableCommand;
import no.runsafe.framework.api.command.ICommandExecutor;
import no.runsafe.framework.api.command.argument.PlayerArgument;
import no.runsafe.framework.api.command.argument.RequiredArgument;
import no.runsafe.framework.api.event.INetworkEvent;
import no.runsafe.framework.api.player.IAmbiguousPlayer;
import no.runsafe.framework.api.player.IPlayer;
import no.runsafe.framework.minecraft.event.networking.RunsafeLoginVerifiedEvent;
import no.runsafe.framework.minecraft.event.networking.RunsafeNetworkEvent;

import java.util.HashMap;
import java.util.Map;

public class Mask extends ExecutableCommand implements INetworkEvent
{
	public Mask(IServer server)
	{
		super("mask", "Behold the power of pure water!", "runsafe.toybox.mask", new PlayerArgument(), new RequiredArgument("name"));
		this.server = server;
	}

	@Override
	public String OnExecute(ICommandExecutor executor, Map<String, String> parameters)
	{
		IPlayer player = server.getPlayerExact(parameters.get("player"));
		if (player instanceof IAmbiguousPlayer)
			return player.toString();

		if (player == null)
			return "Unable to find a player named " + parameters.get("player");

		String playerName = player.getName();
		String target = parameters.get("name");

		masks.put(player.getName(), target);

		return "Mask applied: " + playerName + " -> " + target;
	}

	@Override
	public void onNetworkEvent(RunsafeNetworkEvent event)
	{
		if (event instanceof RunsafeLoginVerifiedEvent)
		{
			RunsafeLoginVerifiedEvent verifyEvent = (RunsafeLoginVerifiedEvent) event;
			String playerName = verifyEvent.getPlayerName();

			if (masks.containsKey(playerName))
			{
				verifyEvent.setPlayerName(masks.get(playerName));
				masks.remove(playerName);
			}
		}
	}

	private IServer server;
	private HashMap<String, String> masks = new HashMap<String, String>(0);
}
