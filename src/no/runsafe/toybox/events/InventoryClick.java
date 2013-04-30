package no.runsafe.toybox.events;

import no.runsafe.framework.event.inventory.IInventoryClick;
import no.runsafe.framework.output.IOutput;
import no.runsafe.framework.server.event.inventory.RunsafeInventoryClickEvent;

public class InventoryClick implements IInventoryClick
{
	public InventoryClick(IOutput output)
	{
		this.output = output;
	}

	@Override
	public void OnInventoryClickEvent(RunsafeInventoryClickEvent event)
	{
		try
		{
			this.output.write("Enchant detected: " + event.getCurrentItem().getItemMeta().getDisplayName());
		}
		catch (NullPointerException e)
		{
			// Do fuck all.
		}
	}

	private IOutput output;
}
