package no.runsafe.toybox.command;

import no.runsafe.framework.api.command.player.PlayerCommand;
import no.runsafe.framework.minecraft.item.meta.RunsafeMeta;
import no.runsafe.framework.minecraft.player.RunsafePlayer;

import java.util.Map;

public class RenameItem extends PlayerCommand
{
	public RenameItem()
	{
		super("renameitem", "Renames the item you are holding", "runsafe.toybox.rename", "name");
		this.captureTail();
	}

	@Override
	public String OnExecute(RunsafePlayer executor, Map<String, String> parameters)
	{
		RunsafeMeta item = executor.getItemInHand();
		if (item == null)
			return "&cYou need to be holding an item.";

		String name = parameters.get("name");
		item.setDisplayName(name);
		if (name.equals(item.getDisplayName()))
			return "&2The item you hold has been renamed.";

		return "&cThat item cannot be renamed.";
	}
}
