package no.runsafe.toybox.command;

import no.runsafe.framework.command.player.PlayerCommand;
import no.runsafe.framework.server.RunsafeEntityEquipment;
import no.runsafe.framework.server.entity.RunsafeEntity;
import no.runsafe.framework.server.entity.RunsafeLivingEntity;
import no.runsafe.framework.server.player.RunsafePlayer;
import no.runsafe.framework.server.potion.RunsafePotionEffect;
import no.runsafe.framework.server.potion.RunsafePotionEffectType;
import no.runsafe.toybox.handlers.Enchanter;

import java.util.HashMap;

public class SpawnGodMob extends PlayerCommand
{
	public SpawnGodMob(Enchanter enchanter)
	{
		super("spawngodmob", "Spawns a god-like mob", "runsafe.toybox.spawngodmob", "mobName", "amount");
		this.enchanter = enchanter;
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
				RunsafeEntityEquipment equipment = livingEntity.getEquipment();

				equipment.setBoots(this.enchanter.createFullyEnchanted(313));
				equipment.setChestplate(this.enchanter.createFullyEnchanted(311));
				equipment.setHelmet(this.enchanter.createFullyEnchanted(310));
				equipment.setLeggings(this.enchanter.createFullyEnchanted(312));

				if (mobName.equalsIgnoreCase("skeleton"))
					equipment.setItemInHand(this.enchanter.createFullyEnchanted(261));
				else
					equipment.setItemInHand(this.enchanter.createFullyEnchanted(276));

				livingEntity.addPotionEffect(RunsafePotionEffect.create(RunsafePotionEffectType.INCREASE_DAMAGE, 1200, 5));
				livingEntity.addPotionEffect(RunsafePotionEffect.create(RunsafePotionEffectType.FIRE_RESISTANCE, 1200, 5));
				livingEntity.addPotionEffect(RunsafePotionEffect.create(RunsafePotionEffectType.DAMAGE_RESISTANCE, 1200, 5));
			}
		}

		return null;
	}

	private Enchanter enchanter;
}
