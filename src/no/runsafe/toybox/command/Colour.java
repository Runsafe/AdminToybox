package no.runsafe.toybox.command;

import no.runsafe.framework.command.player.PlayerCommand;
import no.runsafe.framework.minecraft.Item;
import no.runsafe.framework.server.item.RunsafeItemStack;
import no.runsafe.framework.server.item.meta.RunsafeLeatherArmorMeta;
import no.runsafe.framework.server.player.RunsafePlayer;

import java.util.HashMap;

public class Colour extends PlayerCommand
{
	public Colour()
	{
		super("colour", "Colours an item you are holding.", "runsafe.toybox.colour", "hex");
	}

	@Override
	public String OnExecute(RunsafePlayer executor, HashMap<String, String> parameters)
	{
		RunsafeItemStack item = executor.getItemInHand();

		if (item.is(Item.Combat.Leggings.Leather) || item.is(Item.Combat.Boots.Leather)	|| item.is(Item.Combat.Chestplate.Leather) || item.is(Item.Combat.Helmet.Leather))
		{
			RunsafeLeatherArmorMeta meta = (RunsafeLeatherArmorMeta) item.getItemMeta();
			String hex = parameters.get("hex");

			meta.setColor(
					Integer.valueOf(hex.substring(0, 2), 16),
					Integer.valueOf(hex.substring(2, 4), 16),
					Integer.valueOf(hex.substring(4, 6), 16)
			);
			item.setItemMeta(meta);
			return "&2Coloured!";
		}
		return "&cYou cannot colour that item.";
	}
}
