package no.runsafe.toybox.command;

import no.runsafe.framework.command.player.PlayerCommand;
import no.runsafe.framework.enchant.Enchant;
import no.runsafe.framework.minecraft.Item;
import no.runsafe.framework.server.entity.RunsafeEntity;
import no.runsafe.framework.server.entity.RunsafeLivingEntity;
import no.runsafe.framework.server.item.RunsafeItemStack;
import no.runsafe.framework.server.player.RunsafePlayer;
import no.runsafe.framework.server.potion.RunsafePotionEffect;
import no.runsafe.framework.server.potion.RunsafePotionEffectType;

import java.util.HashMap;

public class SpawnGodMob extends PlayerCommand
{
	public SpawnGodMob()
	{
		super("spawngodmob", "Spawns a god-like mob", "runsafe.toybox.spawngodmob", "mobName", "amount");
	}

	@Override
	public String OnExecute(RunsafePlayer executor, HashMap<String, String> parameters)
	{
		int n = Integer.parseInt(parameters.get("amount"));

		for (int i = 0; i < n; ++i)
		{
			String mobName = parameters.get("mobName");
			RunsafeEntity entity = executor.getWorld().spawnCreature(executor.getLocation(), mobName);
			if (entity instanceof RunsafeLivingEntity)
			{
				RunsafeLivingEntity livingEntity = (RunsafeLivingEntity) entity;

				RunsafeItemStack weapon;
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

				livingEntity.addPotionEffect(RunsafePotionEffect.create(RunsafePotionEffectType.INCREASE_DAMAGE, 1200, 5));
				livingEntity.addPotionEffect(RunsafePotionEffect.create(RunsafePotionEffectType.FIRE_RESISTANCE, 1200, 5));
				livingEntity.addPotionEffect(RunsafePotionEffect.create(RunsafePotionEffectType.DAMAGE_RESISTANCE, 1200, 5));
			}
		}

		return null;
	}
}
