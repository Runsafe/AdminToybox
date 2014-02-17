package no.runsafe.toybox.command;

import no.runsafe.framework.api.command.argument.IArgumentList;
import no.runsafe.framework.api.command.argument.OptionalArgument;
import no.runsafe.framework.api.command.argument.RequiredArgument;
import no.runsafe.framework.api.command.player.PlayerCommand;
import no.runsafe.framework.api.minecraft.IEnchant;
import no.runsafe.framework.api.player.IPlayer;
import no.runsafe.framework.minecraft.Enchant;
import no.runsafe.framework.minecraft.item.RunsafeItemStack;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class EnchantItem extends PlayerCommand
{
	public EnchantItem()
	{
		super(
			"enchant", "Enchants an item", "runsafe.toybox.enchant",
			new RequiredArgument("enchant"), new OptionalArgument("power")
		);
	}

	@Override
	public String OnExecute(IPlayer executor, IArgumentList parameters)
	{
		RunsafeItemStack item = executor.getItemInHand();
		if (item == null)
			return "&cNo item in your hand.";

		String enchantName = parameters.get("enchant");
		if (enchantName.equalsIgnoreCase("help"))
		{
			List<String> enchantNames = new ArrayList<String>();
			for (IEnchant enchant : Enchant.All)
			{
				enchantNames.add(
					String.format(
						item.getItemId() > 0 ? "%s%s" : "%2$s",
						enchant.canEnchant(item) ? "&a" : "&c",
						enchant.getName().toLowerCase()
					)
				);
			}
			return "&3" + StringUtils.join(enchantNames, "&3, ");
		}
		else if (enchantName.equalsIgnoreCase("all"))
		{
			item.enchant(Enchant.All);
			return "&2Applied all available enchants to your item.";
		}
		else
		{
			IEnchant enchant = Enchant.getByName(enchantName);

			if (enchant == null)
				return "&cThe enchantment you are looking for does not exist. Use '/enchant help' for help.";

			if (!enchant.canEnchant(item))
				return "&cThat enchant cannot be applied to that item.";

			if (parameters.containsKey("power"))
				enchant.power(Integer.valueOf(parameters.get("power"))).applyTo(item);
			else
				enchant.max().applyTo(item);

			return "&2Your item has been enchanted.";
		}
	}
}
