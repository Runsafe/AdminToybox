package no.runsafe.toybox.events;

import no.runsafe.framework.api.ILocation;
import no.runsafe.framework.api.IWorld;
import no.runsafe.framework.api.IWorldEffect;
import no.runsafe.framework.api.event.player.IPlayerMove;
import no.runsafe.framework.api.player.IPlayer;
import no.runsafe.framework.minecraft.Item;
import no.runsafe.framework.minecraft.WorldBlockEffect;
import no.runsafe.framework.minecraft.WorldBlockEffectType;

public class PlayerMove implements IPlayerMove
{
	public PlayerMove()
	{
		effect = new WorldBlockEffect(WorldBlockEffectType.BLOCK_DUST, Item.BuildingBlock.Wool.Red);
	}

	@Override
	public boolean OnPlayerMove(IPlayer player, ILocation from, ILocation to)
	{
		if (to.getBlock().is(Item.Redstone.Device.Hopper))
		{
			player.damage(20.0);

			IWorld playerWorld = player.getWorld();
			ILocation playerLocation = player.getLocation();
			if (playerLocation != null && playerWorld != null)
				playerLocation.playEffect(effect, 0.3F, 100, 50);
		}
		return true;
	}

	private final IWorldEffect effect;
}
