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
			new MapRequired<>(EFFECT, buffs),
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

	private static final HashMap<String, Buff> buffs = new HashMap<>();

	static
	{
		buffs.put("confusion", Buff.Combat.Confusion);
		buffs.put("blindness", Buff.Combat.Blindness);
		buffs.put("glowing", Buff.Combat.Glowing);
		buffs.put("increaseDamage", Buff.Combat.Damage.Increase);
		buffs.put("decreaseDamage", Buff.Combat.Damage.Decrease);
		buffs.put("instantDamage", Buff.Combat.Damage.Instant);
		buffs.put("poison", Buff.Combat.Damage.Poison);
		buffs.put("wither", Buff.Combat.Damage.Wither);
		buffs.put("instantHealth", Buff.Healing.Instant);
		buffs.put("regeneration", Buff.Healing.Regeneration);
		buffs.put("moreRedHearts", Buff.Healing.HealthBoost);
		buffs.put("moreYellowHearts", Buff.Healing.Absorption);
		buffs.put("damageResistance", Buff.Resistance.Damage);
		buffs.put("fireResistance", Buff.Resistance.Fire);
		buffs.put("invisibility", Buff.Utility.Invisibility);
		buffs.put("nightVision", Buff.Utility.NightVision);
		buffs.put("waterBreathing", Buff.Utility.WaterBreathing);
		buffs.put("goodLuck", Buff.Utility.Luck);
		buffs.put("badLuck", Buff.Utility.Unluck);
		buffs.put("increaseSpeed", Buff.Utility.Movement.IncreaseSpeed);
		buffs.put("decreaseSpeed", Buff.Utility.Movement.DecreaseSpeed);
		buffs.put("jump", Buff.Utility.Movement.JumpHeight);
		buffs.put("levitation", Buff.Utility.Movement.Levitation);
		buffs.put("digFast", Buff.Utility.DigSpeed.Increase);
		buffs.put("digSlow", Buff.Utility.DigSpeed.Decrease);
		buffs.put("hunger", Buff.Disease.Hunger);
		buffs.put("hungerSaturation", Buff.Disease.Saturation);
	}
}
