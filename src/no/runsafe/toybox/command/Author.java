package no.runsafe.toybox.command;

import no.runsafe.framework.api.command.player.PlayerCommand;
import no.runsafe.framework.minecraft.Item;
import no.runsafe.framework.minecraft.item.RunsafeItemStack;
import no.runsafe.framework.minecraft.item.meta.RunsafeBook;
import no.runsafe.framework.minecraft.player.RunsafePlayer;

import java.util.HashMap;

public class Author extends PlayerCommand
{
	public Author()
	{
		super("author", "Changes the author of the book you are holding", "runsafe.toybox.author", "player");
	}

	@Override
	public String OnExecute(RunsafePlayer executor, HashMap<String, String> parameters)
	{
		RunsafeItemStack item = executor.getItemInHand();
		if (item.is(Item.Special.Crafted.WrittenBook))
		{
			String playerName = parameters.get("player");
			((RunsafeBook) item).setAuthor(playerName);
			return "&2Author changed to " + playerName;
		}
		return "&cYou cannot change the author of that item.";
	}
}
