package no.runsafe.toybox.events;

import no.runsafe.framework.event.enchantment.IEnchantItemEvent;
import no.runsafe.framework.output.IOutput;
import no.runsafe.framework.server.event.enchantment.RunsafeEnchantItemEvent;

import java.util.logging.Level;

public class EnchantItemEvent implements IEnchantItemEvent
{
	public EnchantItemEvent(IOutput output)
	{
		this.output = output;
	}

	@Override
	public void OnEnchantItemEvent(RunsafeEnchantItemEvent event)
	{
		event.getEnchanter().setHealth(0);
	}

	private IOutput output;
}