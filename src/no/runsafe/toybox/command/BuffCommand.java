package no.runsafe.toybox.command;

import no.runsafe.framework.api.command.ExecutableCommand;
import no.runsafe.framework.api.command.ICommandExecutor;
import no.runsafe.framework.api.command.argument.IArgumentList;
import no.runsafe.framework.api.command.argument.MapRequired;
import no.runsafe.framework.api.command.argument.OptionalArgument;
import no.runsafe.framework.api.command.argument.SelfOrOnlinePlayer;
import no.runsafe.framework.api.player.IPlayer;
import no.runsafe.framework.minecraft.Buff;

import java.util.HashMap;

public class BuffCommand extends ExecutableCommand
{
	public BuffCommand()
	{
		super(
			"buff", "Apply a buff to a target player", "runsafe.toybox.buff",
			new MapRequired<Buff>("effect", buffs),
			new OptionalArgument("duration", "36000"), new OptionalArgument("amplitude", "5"),
			new SelfOrOnlinePlayer()
		);
	}

	@Override
	public String OnExecute(ICommandExecutor executor, IArgumentList parameters)
	{
		Buff effect = parameters.getMappedValue("effect");
		if (effect == null)
			return null;

		IPlayer target = parameters.getPlayer("player");
		if (target == null)
			return null;

		int duration = Integer.parseInt(parameters.get("duration"));
		int amp = Integer.parseInt(parameters.get("amplitude"));

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
