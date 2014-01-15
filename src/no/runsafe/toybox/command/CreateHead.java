package no.runsafe.toybox.command;

import no.runsafe.framework.api.command.argument.AnyPlayerArgument;
import no.runsafe.framework.api.command.argument.IArgumentList;
import no.runsafe.framework.api.command.player.PlayerCommand;
import no.runsafe.framework.api.player.IAmbiguousPlayer;
import no.runsafe.framework.api.player.IPlayer;
import no.runsafe.framework.minecraft.Item;
import no.runsafe.framework.minecraft.item.meta.RunsafeSkull;

public class CreateHead extends PlayerCommand
{
	public CreateHead()
	{
		super(
			"createhead", "Creates the head of a player", "runsafe.toybox.createhead",
			new AnyPlayerArgument()
		);
	}

	@Override
	public String OnExecute(IPlayer executor, IArgumentList parameters)
	{
		IPlayer player = parameters.getPlayer("player");
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
}
