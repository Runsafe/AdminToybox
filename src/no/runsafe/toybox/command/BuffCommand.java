package no.runsafe.toybox.command;

import no.runsafe.framework.api.command.ExecutableCommand;
import no.runsafe.framework.api.command.ICommandExecutor;
import no.runsafe.framework.api.command.argument.IArgumentList;
import no.runsafe.framework.api.command.argument.MapRequired;
import no.runsafe.framework.api.command.argument.Player;
import no.runsafe.framework.api.command.argument.WholeNumber;
import no.runsafe.framework.api.player.IPlayer;
import no.runsafe.framework.minecraft.Buff;

import java.util.HashMap;

public class BuffCommand extends ExecutableCommand
{
	public BuffCommand()
	{
		super(
			"buff", "Apply a buff to a target player", "runsafe.toybox.buff",
			new MapRequired<Buff>(EFFECT, buffs),
			new WholeNumber(DURATION).withDefault(36000),
			new WholeNumber(AMPLITUDE).withDefault(5),
			new Player(PLAYER).onlineOnly().defaultToExecutor()
		);
	}

	private static final String EFFECT = "effect";
	private static final String DURATION = "duration";
	private static final String AMPLITUDE = "amplitude";
	private static final String PLAYER = "player";

	@Override
	public String OnExecute(ICommandExecutor executor, IArgumentList parameters)
	{
		Buff effect = parameters.getValue(EFFECT);
		if (effect == null)
			return null;

		IPlayer target = parameters.getValue(PLAYER);
		if (target == null)
			return null;

		Integer amp = parameters.getValue(AMPLITUDE);
		Integer duration = parameters.getValue(DURATION);
		assert (amp != null && duration != null);
		effect.amplification(amp).duration(duration).applyTo(target);
		return null;
	}

	private static final HashMap<String, Buff> buffs = new HashMap<String, Buff>();

	static
	{
		buffs.put("speed", Buff.Utility.Movement.IncreaseSpeed);
		buffs.put("slow", Buff.Utility.Movement.DecreaseSpeed);
		buffs.put("jump", Buff.Utility.Movement.JumpHeight);
		buffs.put("nightvision", Buff.Utility.NightVision);
		buffs.put("invisibility", Buff.Utility.Invisibility);
		buffs.put("waterbreathing", Buff.Utility.WaterBreathing);
		buffs.put("digspeed", Buff.Utility.DigSpeed.Increase);
		buffs.put("digslow", Buff.Utility.DigSpeed.Decrease);
		buffs.put("fireresist", Buff.Resistance.Fire);
		buffs.put("resist", Buff.Resistance.Damage);
		buffs.put("hunger", Buff.Disease.Hunger);
		buffs.put("heal", Buff.Healing.Instant);
		buffs.put("regen", Buff.Healing.Regeneration);
	}
}
