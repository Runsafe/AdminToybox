package no.runsafe.toybox.command;

import no.runsafe.framework.command.player.PlayerCommand;
import no.runsafe.framework.server.item.RunsafeItemStack;
import no.runsafe.framework.server.item.meta.RunsafeItemMeta;
import no.runsafe.framework.server.player.RunsafePlayer;

import java.util.HashMap;

public class RenameItem extends PlayerCommand
{
	public RenameItem()
	{
		super("renameitem", "Renames the item you are holding", "runsafe.toybox.rename", "name");
	}

	@Override
	public String OnExecute(RunsafePlayer executor, HashMap<String, String> parameters)
	{
		RunsafeItemStack item = executor.getItemInHand();

		if (item == null)
			return "&cYou need to be holding an item.";

		RunsafeItemMeta meta = item.getItemMeta();

		if (meta == null)
			return "&cThat item cannot be renamed.";

		meta.setDisplayName(parameters.get("name"));
		item.setItemMeta(meta);

		return "&2The item you hold has been renamed.";
	}
}
