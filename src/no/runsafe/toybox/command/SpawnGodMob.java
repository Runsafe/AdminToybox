package no.runsafe.toybox.command;

import no.runsafe.framework.api.command.argument.RequiredArgument;
import no.runsafe.framework.api.command.player.PlayerCommand;
import no.runsafe.framework.api.player.IPlayer;
import no.runsafe.framework.minecraft.Buff;
import no.runsafe.framework.minecraft.Enchant;
import no.runsafe.framework.minecraft.Item;
import no.runsafe.framework.minecraft.entity.RunsafeEntity;
import no.runsafe.framework.minecraft.entity.RunsafeLivingEntity;
import no.runsafe.framework.minecraft.item.meta.RunsafeMeta;

import java.util.Map;

public class SpawnGodMob extends PlayerCommand
{
	public SpawnGodMob()
	{
		super(
			"spawngodmob", "Spawns a god-like mob", "runsafe.toybox.spawngodmob",
			new RequiredArgument("mobName"), new RequiredArgument("amount")
		);
	}

	@Override
	public String OnExecute(IPlayer executor, Map<String, String> parameters)
	{
		int n = Integer.parseInt(parameters.get("amount"));

		for (int i = 0; i < n; ++i)
		{
			String mobName = parameters.get("mobName");
			RunsafeEntity entity = executor.getWorld().spawnCreature(executor.getLocation(), mobName);
			if (entity instanceof RunsafeLivingEntity)
			{
				RunsafeLivingEntity livingEntity = (RunsafeLivingEntity) entity;

				RunsafeMeta weapon;
				if (mobName.equalsIgnoreCase("skeleton"))
					weapon = Item.Combat.Bow.enchant(Enchant.All).getItem();
				else
					weapon = Item.Combat.Sword.Diamond.enchant(Enchant.All).getItem();

				livingEntity.getEquipment()
					.setBoots(Item.Combat.Boots.Diamond.enchant(Enchant.All).getItem())
					.setChestplate(Item.Combat.Chestplate.Diamond.enchant(Enchant.All).getItem())
					.setHelmet(Item.Combat.Helmet.Diamond.enchant(Enchant.All).getItem())
					.setLeggings(Item.Combat.Leggings.Diamond.enchant(Enchant.All).getItem())
					.setItemInHand(weapon);

				Buff.Combat.Damage.Increase.duration(1200).amplification(5).applyTo(livingEntity);
				Buff.Resistance.Fire.duration(1200).amplification(5).applyTo(livingEntity);
				Buff.Resistance.Damage.duration(1200).amplification(5).applyTo(livingEntity);
			}
		}

		return null;
	}
}
