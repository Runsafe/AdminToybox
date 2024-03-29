package no.runsafe.toybox.events;

import no.runsafe.framework.api.block.IBlock;
import no.runsafe.framework.api.block.IDispenser;
import no.runsafe.framework.api.block.IDropper;
import no.runsafe.framework.api.block.ISign;
import no.runsafe.framework.api.event.block.IBlockPlace;
import no.runsafe.framework.api.minecraft.IInventoryHolder;
import no.runsafe.framework.api.player.IPlayer;
import no.runsafe.framework.minecraft.Item;
import no.runsafe.framework.minecraft.inventory.RunsafeInventory;
import no.runsafe.framework.minecraft.item.meta.RunsafeMeta;

public class BlockPlace implements IBlockPlace
{
	@Override
	public boolean OnBlockPlace(IPlayer player, IBlock block)
	{
		if (block instanceof ISign)
		{
			ISign sign = (ISign) block;
			if (sign.getLine(0).equals("[Infinite]"))
				if (!player.hasPermission("runsafe.toybox.infinitedispensers"))
				{
					player.sendColouredMessage(String.format("&cI'm sorry %s, I'm afraid I can't let you do that", player.getName()));
					return false;
				}
		}
		else if (block instanceof IDispenser || block instanceof IDropper)
		{
			RunsafeInventory inventory = ((IInventoryHolder) block).getInventory();
			if (inventory != null && inventory.getTitle().equalsIgnoreCase("Infinite"))
				if (!player.hasPermission("runsafe.toybox.infinitedispensers"))
				{
					RunsafeMeta item = player.getItemInMainHand();
					if (item != null && (item.is(Item.Redstone.Device.Dispenser) || item.is(Item.Redstone.Device.Dropper)))
						item.setDisplayName("§MInfinite.§R");
					item = player.getItemInOffHand();
					if (item != null && (item.is(Item.Redstone.Device.Dispenser) || item.is(Item.Redstone.Device.Dropper)))
						item.setDisplayName("§MInfinite.§R");

					player.sendColouredMessage(String.format("&cI'm sorry %s, I'm afraid I can't let you do that", player.getName()));
					return false;
				}
		}
		return true;
	}
}
