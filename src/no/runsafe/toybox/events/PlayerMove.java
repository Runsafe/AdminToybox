package no.runsafe.toybox.events;

import no.runsafe.framework.event.player.IPlayerMove;
import no.runsafe.framework.minecraft.Item;
import no.runsafe.framework.server.RunsafeLocation;
import no.runsafe.framework.server.player.RunsafePlayer;
import org.bukkit.Effect;

public class PlayerMove implements IPlayerMove
{
	@Override
	public boolean OnPlayerMove(RunsafePlayer player, RunsafeLocation from, RunsafeLocation to)
	{
		if (to.getBlock().is(Item.Redstone.Device.Hopper))
		{
			player.damage(20);
			player.getWorld().playEffect(player.getLocation(), Effect.POTION_BREAK, 16421);
		}
		return true;
	}
}
