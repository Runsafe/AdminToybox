package no.runsafe.toybox.command;

import no.runsafe.framework.command.player.PlayerCommand;
import no.runsafe.framework.server.item.RunsafeItemStack;
import no.runsafe.framework.server.item.meta.RunsafeMeta;
import no.runsafe.framework.server.player.RunsafePlayer;

import java.util.HashMap;

public class RenameItem extends PlayerCommand
{
	public RenameItem()
	{
		super("renameitem", "Renames the item you are holding", "runsafe.toybox.rename", "name");
		this.captureTail();
	}

	@Override
	public String OnExecute(RunsafePlayer executor, HashMap<String, String> parameters)
	{
		RunsafeItemStack item = executor.getItemInHand();
		if (item == null)
			return "&cYou need to be holding an item.";

		if (item instanceof RunsafeMeta)
		{
			((RunsafeMeta) item).setDisplayName(parameters.get("name"));
			return "&2The item you hold has been renamed.";
		}

		return "&cThat item cannot be renamed.";
	}
}
