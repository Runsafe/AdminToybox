package no.runsafe.toybox.command;

import no.runsafe.framework.command.player.PlayerCommand;
import no.runsafe.framework.server.RunsafeEntityEquipment;
import no.runsafe.framework.server.enchantment.RunsafeEnchantment;
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
			RunsafeEntity entity = executor.getWorld().spawnCreature(executor.getLocation(), parameters.get("mobName"));
			if (entity instanceof RunsafeLivingEntity)
			{
				RunsafeLivingEntity livingEntity = (RunsafeLivingEntity) entity;
				RunsafeEntityEquipment equipment = livingEntity.getEquipment();

				equipment.setBoots(this.makeGodArmor(313));
				equipment.setChestplate(this.makeGodArmor(311));
				equipment.setHelmet(this.makeGodArmor(310));
				equipment.setLeggings(this.makeGodArmor(312));
				equipment.setItemInHand(this.makeGodSword(276));

				livingEntity.addPotionEffect(RunsafePotionEffect.create(RunsafePotionEffectType.INCREASE_DAMAGE, 1200, 5));
				livingEntity.addPotionEffect(RunsafePotionEffect.create(RunsafePotionEffectType.FIRE_RESISTANCE, 1200, 5));
				livingEntity.addPotionEffect(RunsafePotionEffect.create(RunsafePotionEffectType.DAMAGE_RESISTANCE, 1200, 5));
			}
		}

		return null;
	}

	private RunsafeItemStack makeGodArmor(int itemID)
	{
		RunsafeItemStack item = new RunsafeItemStack(itemID);
		item.addEnchantment(RunsafeEnchantment.DURABILITY, 5);
		item.addEnchantment(RunsafeEnchantment.PROTECTION_ENVIRONMENTAL, 5);
		item.addEnchantment(RunsafeEnchantment.PROTECTION_FALL, 5);
		item.addEnchantment(RunsafeEnchantment.PROTECTION_FIRE, 5);
		item.addEnchantment(RunsafeEnchantment.PROTECTION_PROJECTILE, 5);
		item.addEnchantment(RunsafeEnchantment.THORNS, 5);
		return item;
	}

	private RunsafeItemStack makeGodSword(int itemID)
	{
		RunsafeItemStack item = new RunsafeItemStack(itemID);
		item.addEnchantment(RunsafeEnchantment.FIRE_ASPECT, 5);
		item.addEnchantment(RunsafeEnchantment.DAMAGE_ALL, 5);
		item.addEnchantment(RunsafeEnchantment.DURABILITY, 5);
		item.addEnchantment(RunsafeEnchantment.KNOCKBACK, 5);
		item.addEnchantment(RunsafeEnchantment.DAMAGE_ALL, 5);
		return item;
	}
}
