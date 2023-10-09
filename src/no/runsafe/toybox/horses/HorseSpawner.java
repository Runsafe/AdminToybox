package no.runsafe.toybox.horses;

import no.runsafe.framework.api.ILocation;
import no.runsafe.framework.api.entity.animals.horses.INormalHorse;
import no.runsafe.framework.api.minecraft.RunsafeEntityType;
import no.runsafe.framework.minecraft.entity.EntityType;
import no.runsafe.framework.minecraft.entity.animals.horses.HorseColour;
import no.runsafe.framework.minecraft.entity.animals.horses.HorseStyle;
import no.runsafe.framework.minecraft.entity.animals.horses.RunsafeNormalHorse;

public class HorseSpawner
{
	public void spawnHorse(ILocation location, HorseColour colour, HorseStyle style, boolean tamed)
	{
		INormalHorse horse = (RunsafeNormalHorse) EntityType.Get("horse");

		horse.setTamed(tamed);

		if (colour == null)
			horse.setRandomColour();
		else
			horse.setColour(colour);

		if (style == null)
			horse.setRandomStyle();
		else
			horse.setStyle(style);

		location.getWorld().spawn(location, (RunsafeEntityType) horse);
	}
}
