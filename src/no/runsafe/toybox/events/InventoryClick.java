package no.runsafe.toybox.events;

import no.runsafe.framework.api.event.inventory.IInventoryClick;
import no.runsafe.framework.api.player.IPlayer;
import no.runsafe.framework.minecraft.event.inventory.RunsafeInventoryClickEvent;
import no.runsafe.framework.minecraft.item.meta.RunsafeMeta;

public class InventoryClick implements IInventoryClick
{
	@Override
	public void OnInventoryClickEvent(RunsafeInventoryClickEvent event)
	{
		RunsafeMeta item = event.getCurrentItem();
		if (item == null)
			return;

		String name = item.getDisplayName();
		if (name == null)
			return;

		if (!name.equalsIgnoreCase("Infinite"))
			return;

		IPlayer player = event.getWhoClicked();
		if (player.hasPermission("runsafe.toybox.infinitedispensers"))
			return;

		player.sendColouredMessage("&cYou do not have permission to make those.");
		event.cancel();
	}
}
