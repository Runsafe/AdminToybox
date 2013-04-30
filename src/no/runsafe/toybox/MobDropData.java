package no.runsafe.toybox;

import org.bukkit.entity.EntityType;

public class MobDropData
{
	public MobDropData(EntityType entityType, Integer mobAmount)
	{
		this.setEntityType(entityType);
		this.setMobAmount(mobAmount);
	}

	public void setEntityType(EntityType entityType)
	{
		this.entityType = entityType;
	}

	public void setMobAmount(Integer mobAmount)
	{
		this.mobAmount = mobAmount;
	}

	public EntityType getEntityType()
	{
		return this.entityType;
	}

	public Integer getMobAmount()
	{
		return this.mobAmount;
	}

	private EntityType entityType;
	private Integer mobAmount;
}
