package no.runsafe.toybox.command;

import no.runsafe.framework.api.command.argument.EnchantArgument;
import no.runsafe.framework.api.command.argument.IArgumentList;
import no.runsafe.framework.api.command.argument.ListOf;
import no.runsafe.framework.api.command.player.PlayerCommand;
import no.runsafe.framework.api.minecraft.IEnchant;
import no.runsafe.framework.api.player.IPlayer;
import no.runsafe.framework.minecraft.Enchant;
import no.runsafe.framework.minecraft.item.RunsafeItemStack;

public class EnchantItem extends PlayerCommand
{
	public EnchantItem()
	{
		super(
			"enchant", "Enchants an item", "runsafe.toybox.enchant",
			new ListOf<EnchantArgument>(new EnchantArgument(true))
		);
	}

	@Override
	public String OnExecute(IPlayer executor, IArgumentList parameters)
	{
		RunsafeItemStack item = executor.getItemInHand();
		if (item == null)
			return "&cNo item in your hand.";
		String[] enchants = parameters.get("enchant").split(" ");
		for (String enchantName : enchants)
		{
			IEnchant enchant = Enchant.getByName(enchantName);
			if (enchant != null)
				enchant.max().applyTo(item);

		}
		return "&2Your item has been enchanted.";
	}
}
