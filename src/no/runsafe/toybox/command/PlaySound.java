package no.runsafe.toybox.command;

import no.runsafe.framework.api.ILocation;
import no.runsafe.framework.api.command.argument.DecimalNumber;
import no.runsafe.framework.api.command.argument.Enumeration;
import no.runsafe.framework.api.command.argument.IArgumentList;
import no.runsafe.framework.api.command.player.PlayerCommand;
import no.runsafe.framework.api.player.IPlayer;
import no.runsafe.framework.minecraft.Sound;

public class PlaySound extends PlayerCommand
{
	public PlaySound()
	{
		super(
			"playsound", "Plays a sound", "runsafe.toybox.playsound",
			new Enumeration(SOUND, org.bukkit.Sound.values()).require(),
			new DecimalNumber(VOLUME).withDefault(1.0F),
			new DecimalNumber(PITCH).withDefault(1.0F)
		);
	}

	private static final String SOUND = "sound";
	private static final String VOLUME = "volume";
	private static final String PITCH = "pitch";

	@Override
	public String OnExecute(IPlayer executor, IArgumentList parameters)
	{
		Sound sound = Sound.Get(parameters.getRequired(SOUND).toString());
		float volume = parameters.getRequired(VOLUME);
		float pitch = parameters.getRequired(PITCH);
		ILocation location = executor.getLocation();
		if (location == null)
		{
			return "&4Location not found";
		}
		location.playSound(sound, volume, pitch);
		return "&2Sound played.";
	}
}
