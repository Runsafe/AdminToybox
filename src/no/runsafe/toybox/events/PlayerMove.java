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
		effectBlood = new WorldBlockEffect(WorldBlockEffectType.BLOCK_DUST, Item.Redstone.Block);
		effectGuts = new WorldBlockEffect(WorldBlockEffectType.BLOCK_DUST, Item.BuildingBlock.Netherrack);
		effectBone = new WorldBlockEffect(WorldBlockEffectType.BLOCK_DUST, Item.BuildingBlock.Quartz.Normal);
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
			{
				playerLocation.playEffect(effectBlood, 0.3F, 70, 50);
				playerLocation.playEffect(effectGuts, 0.3F, 20, 50);
				playerLocation.playEffect(effectBone, 0.3F, 10, 50);
			}
		}
		return true;
	}

	private final IWorldEffect effectBlood;
	private final IWorldEffect effectGuts;
	private final IWorldEffect effectBone;
}
