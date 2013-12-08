package no.runsafe.toybox.command;

import no.runsafe.framework.api.IServer;
import no.runsafe.framework.api.command.argument.PlayerArgument;
import no.runsafe.framework.api.command.player.PlayerCommand;
import no.runsafe.framework.api.player.IAmbiguousPlayer;
import no.runsafe.framework.api.player.IPlayer;
import no.runsafe.framework.minecraft.Item;
import no.runsafe.framework.minecraft.item.meta.RunsafeSkull;

import java.util.Map;

public class CreateHead extends PlayerCommand
{
	public CreateHead(IServer server)
	{
		super(
			"createhead", "Creates the head of a player", "runsafe.toybox.createhead",
			new PlayerArgument()
		);
		this.server = server;
	}

	@Override
	public String OnExecute(IPlayer executor, Map<String, String> parameters)
	{
		IPlayer player = server.getPlayer(parameters.get("player"));
		if (player instanceof IAmbiguousPlayer)
			return player.toString();

		if (player == null)
			return "Unable to find a player named " + parameters.get("player");

		RunsafeSkull heads = (RunsafeSkull) Item.Decoration.Head.Human.getItem();
		heads.setAmount(1);
		heads.setPlayer(player);
		executor.give(heads);

		return "Creating the head of " + player.getName();
	}

	private final IServer server;
}
