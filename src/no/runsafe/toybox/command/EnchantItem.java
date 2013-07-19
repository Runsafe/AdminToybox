package no.runsafe.toybox.command;

import no.runsafe.framework.api.command.player.PlayerCommand;
import no.runsafe.framework.api.minecraft.IEnchant;
import no.runsafe.framework.minecraft.Enchant;
import no.runsafe.framework.minecraft.item.RunsafeItemStack;
import no.runsafe.framework.minecraft.player.RunsafePlayer;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EnchantItem extends PlayerCommand
{
	public EnchantItem()
	{
		super("enchant", "Enchants an item", "runsafe.toybox.enchant", "enchant");
	}

	@Override
	public String OnExecute(RunsafePlayer executor, Map<String, String> parameters)
	{
		return null;
	}

	@Override
	public String OnExecute(RunsafePlayer executor, Map<String, String> parameters, String[] arguments)
	{
		RunsafeItemStack item = executor.getItemInHand();
		String enchantName = parameters.get("enchant");

		if (enchantName.equalsIgnoreCase("help"))
		{
			RunsafeItemStack itemHeld = executor.getItemInHand();
			List<String> enchantNames = new ArrayList<String>();
			for (IEnchant enchant : Enchant.All)
			{
				enchantNames.add(
					String.format(
						itemHeld.getItemId() > 0 ? "%s%s" : "%2$s",
						enchant.canEnchant(itemHeld) ? "&a" : "&c",
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

			if (arguments.length > 0)
				enchant.power(Integer.valueOf(arguments[0])).applyTo(item);
			else
				enchant.max().applyTo(item);

			return "&2Your item has been enchanted.";
		}
	}
}
