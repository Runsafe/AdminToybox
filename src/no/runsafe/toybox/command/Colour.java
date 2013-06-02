package no.runsafe.toybox.command;

import no.runsafe.framework.command.player.PlayerCommand;
import no.runsafe.framework.minecraft.Item;
import no.runsafe.framework.server.item.RunsafeItemStack;
import no.runsafe.framework.server.item.meta.RunsafeLeatherArmorMeta;
import no.runsafe.framework.server.player.RunsafePlayer;

import java.awt.*;
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
			meta.setColor(Color.decode(parameters.get("hex")).getRGB());
		}
		return "&cYou cannot colour that item.";
	}
}
