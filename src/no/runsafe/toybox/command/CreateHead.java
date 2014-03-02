package no.runsafe.toybox.command;

import no.runsafe.framework.api.command.argument.IArgumentList;
import no.runsafe.framework.api.command.argument.Player;
import no.runsafe.framework.api.command.player.PlayerCommand;
import no.runsafe.framework.api.player.IPlayer;
import no.runsafe.framework.minecraft.Item;
import no.runsafe.framework.minecraft.item.meta.RunsafeSkull;

public class CreateHead extends PlayerCommand
{
	public CreateHead()
	{
		super(
			"createhead", "Creates the head of a player", "runsafe.toybox.createhead",
			new Player.Any("player", true, true)
		);
	}

	@Override
	public String OnExecute(IPlayer executor, IArgumentList parameters)
	{
		IPlayer player = parameters.getValue("player");
		if (player == null)
			return null;

		RunsafeSkull heads = (RunsafeSkull) Item.Decoration.Head.Human.getItem();
		heads.setAmount(1);
		heads.setPlayer(player);
		executor.give(heads);

		return "Creating the head of " + player.getName();
	}
}
