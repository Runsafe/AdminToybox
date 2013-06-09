package no.runsafe.toybox.command;

import no.runsafe.framework.command.player.PlayerCommand;
import no.runsafe.framework.minecraft.Item;
import no.runsafe.framework.server.RunsafeServer;
import no.runsafe.framework.server.item.meta.RunsafeSkull;
import no.runsafe.framework.server.player.RunsafeAmbiguousPlayer;
import no.runsafe.framework.server.player.RunsafePlayer;

import java.util.HashMap;

public class CreateHead extends PlayerCommand
{
	public CreateHead()
	{
		super("createhead", "Creates the head of a player", "runsafe.toybox.createhead", "player");
	}

	@Override
	public String OnExecute(RunsafePlayer executor, HashMap<String, String> parameters)
	{
		RunsafePlayer player = RunsafeServer.Instance.getPlayer(parameters.get("player"));
		if (player instanceof RunsafeAmbiguousPlayer)
			return player.toString();

		if (player == null)
			return "Unable to find a player named " + parameters.get("player");

		RunsafeSkull heads = (RunsafeSkull) Item.Decoration.Head.Human.getItem();
		heads.setAmount(1);
		heads.setPlayer(player);
		executor.give(heads);

		return "Creating the head of " + player.getName();
	}
}
