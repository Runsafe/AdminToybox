package no.runsafe.toybox.command;

import no.runsafe.framework.api.command.argument.Enchant;
import no.runsafe.framework.api.command.argument.IArgumentList;
import no.runsafe.framework.api.command.argument.WholeNumber;
import no.runsafe.framework.api.command.player.PlayerCommand;
import no.runsafe.framework.api.minecraft.IEnchant;
import no.runsafe.framework.api.player.IPlayer;
import no.runsafe.framework.minecraft.item.RunsafeItemStack;

public class EnchantItem extends PlayerCommand
{
	public EnchantItem()
	{
		super(
			"enchant", "Enchants an item", "runsafe.toybox.enchant",
			new Enchant(ENCHANT).require(), new WholeNumber(LEVEL)
		);
	}

	private static final String ENCHANT = "enchant";
	private static final String LEVEL = "level";

	@Override
	public String OnExecute(IPlayer executor, IArgumentList parameters)
	{
		RunsafeItemStack item = executor.getItemInMainHand();
		if (item == null)
			return "&cNo item in your hand.";

		IEnchant enchant = parameters.getRequired(ENCHANT);
		Integer enchantLevel = parameters.getValue(LEVEL);

		if (enchantLevel == null)
			enchantLevel = enchant.getMaxLevel();
		else if (enchantLevel < 1)
			enchantLevel = 1;

		enchant.power(enchantLevel).applyUnsafeTo(item);
		return String.format("&aApplied enchant %s at power %d to item in main hand.", enchant.getName(), enchant.power());
	}
}
