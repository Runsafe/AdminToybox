package no.runsafe.toybox.command;

import no.runsafe.framework.api.command.ExecutableCommand;
import no.runsafe.framework.api.command.ICommandExecutor;
import no.runsafe.framework.api.command.argument.PlayerArgument;
import no.runsafe.framework.api.command.argument.RequiredArgument;
import no.runsafe.framework.minecraft.Item;
import no.runsafe.framework.minecraft.RunsafeServer;
import no.runsafe.framework.minecraft.item.RunsafeItemStack;
import no.runsafe.framework.minecraft.player.RunsafeAmbiguousPlayer;
import no.runsafe.framework.minecraft.player.RunsafePlayer;
import org.bukkit.Material;

import java.util.Map;

public class GiveItem extends ExecutableCommand
{
	public GiveItem()
	{
		super(
			"give", "Give yourself or a player an item", "runsafe.toybox.give",
			new RequiredArgument("item"), new RequiredArgument("amount"), new PlayerArgument(false)
		);
	}

	@Override
	public String OnExecute(ICommandExecutor executor, Map<String, String> parameters)
	{
		RunsafePlayer player;
		if (!parameters.containsKey("player"))
		{
			if (executor instanceof RunsafePlayer)
				player = (RunsafePlayer) executor;
			else
				return "&cYou need to supply a player to give items to.";
		}
		else
		{
			player = RunsafeServer.Instance.getPlayer(parameters.get("player"));
			if (player == null)
				return "&cThat player does not exist.";

			if (player instanceof RunsafeAmbiguousPlayer)
				return player.toString();
		}

		RunsafeItemStack item = this.getItemId(parameters.get("item"));

		if (item == null)
			return "&cInvalid item name or ID.";

		int amount = Integer.valueOf(parameters.get("amount"));
		this.giveItems(player, item, amount);

		return String.format("&fGave %sx %s to %s&f.", amount, item.getNormalName(), player.getPrettyName());
	}

	private RunsafeItemStack getItemId(String itemName)
	{
		for (Material material : Material.values())
		{
			if (material.name().replace("_", "").equalsIgnoreCase(itemName))
				return Item.get(material, (byte) 0).getItem();

			if (itemName.matches("-?\\d+(\\.\\d+)?"))
				if (material.getId() == Integer.valueOf(itemName))
					return Item.get(material, (byte) 0).getItem();
		}
		return null;
	}

	private void giveItems(RunsafePlayer player, RunsafeItemStack item, int amount)
	{
		int needed = amount;
		while (needed > 0)
		{
			if (needed < item.getMaxStackSize())
			{
				item.setAmount(needed);
				needed = 0;
			}
			else
			{
				item.setAmount(item.getMaxStackSize());
				needed -= item.getMaxStackSize();
			}
			player.getInventory().addItems(item.clone());
		}
		player.updateInventory();
	}
}
