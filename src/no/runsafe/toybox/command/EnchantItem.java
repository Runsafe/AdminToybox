package no.runsafe.toybox.command;

import no.runsafe.framework.api.command.argument.Enchant;
import no.runsafe.framework.api.command.argument.IArgumentList;
import no.runsafe.framework.api.command.argument.ListOf;
import no.runsafe.framework.api.command.player.PlayerCommand;
import no.runsafe.framework.api.minecraft.IEnchant;
import no.runsafe.framework.api.player.IPlayer;
import no.runsafe.framework.minecraft.item.RunsafeItemStack;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EnchantItem extends PlayerCommand
{
	public EnchantItem()
	{
		super("enchant",
			"Enchants an item",
			"runsafe.toybox.enchant",
			new ListOf<IEnchant>(new Enchant()).require()
		);
	}

	@Override
	public String OnExecute(IPlayer executor, IArgumentList parameters)
	{
		RunsafeItemStack item = executor.getItemInHand();
		if (item == null)
			return "&cNo item in your hand.";
		StringBuilder feedback = new StringBuilder();
		String[] enchants = parameters.get("enchant").split(" ");
		for (String param : enchants)
		{
			Matcher spec = enchantSpec.matcher(param);
			IEnchant enchant;
			if (spec.matches())
			{
				enchant = no.runsafe.framework.minecraft.Enchant.getByName(spec.group(1));
				if (enchant != null)
					enchant = enchant.power(Integer.parseInt(spec.group(2)));
			}
			else
			{
				enchant = no.runsafe.framework.minecraft.Enchant.getByName(param);
				if (enchant != null)
					enchant = enchant.max();
			}
			if (enchant != null)
			{
				enchant.applyTo(item);
				feedback.append(String.format("Applied enchant %s at power %d to item.\n", enchant.getName(), enchant.power()));
			}
		}
		return "&2Your item has been enchanted:\n" + feedback.toString();
	}

	static final Pattern enchantSpec = Pattern.compile("(.*):(\\d+)");
}
