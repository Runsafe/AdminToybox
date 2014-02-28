package no.runsafe.toybox.events;

import no.runsafe.framework.api.ILocation;
import no.runsafe.framework.api.IWorld;
import no.runsafe.framework.api.event.player.IPlayerMove;
import no.runsafe.framework.api.player.IPlayer;
import no.runsafe.framework.minecraft.Item;
import org.bukkit.Effect;

public class PlayerMove implements IPlayerMove
{
	@Override
	public boolean OnPlayerMove(IPlayer player, ILocation from, ILocation to)
	{
		if (to.getBlock().is(Item.Redstone.Device.Hopper))
		{
			player.damage(20.0);

			IWorld playerWorld = player.getWorld();
			ILocation playerLocation = player.getLocation();
			if (playerLocation != null && playerWorld != null)
				playerWorld.playEffect(playerLocation, Effect.POTION_BREAK, 16421);
		}
		return true;
	}
}
