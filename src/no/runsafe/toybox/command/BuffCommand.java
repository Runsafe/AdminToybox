package no.runsafe.toybox.command;

import no.runsafe.framework.api.command.ICommandExecutor;
import no.runsafe.framework.api.command.ExecutableCommand;
import no.runsafe.framework.minecraft.Buff;
import no.runsafe.framework.minecraft.RunsafeServer;
import no.runsafe.framework.minecraft.player.RunsafeAmbiguousPlayer;
import no.runsafe.framework.minecraft.player.RunsafePlayer;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;

public class BuffCommand extends ExecutableCommand
{
	public BuffCommand()
	{
		super("buff", "Apply a buff to a target player", "runsafe.toybox.buff", "effect");
	}

	@Override
	public String OnExecute(ICommandExecutor executor, HashMap<String, String> parameters)
	{
		return null;
	}

	@Override
	public String OnExecute(ICommandExecutor executor, HashMap<String, String> parameters, String[] arguments)
	{
		String buffName = parameters.get("effect");
		if (!BuffCommand.buffs.containsKey(buffName))
			return "&cAvailable effects: " + StringUtils.join(BuffCommand.buffs.keySet(), ", ");

		RunsafePlayer target;
		int duration = 36000;
		int amp = 5;

		if (executor instanceof RunsafePlayer)
		{
			if (arguments.length < 3)
			{
				target = (RunsafePlayer) executor;
			}
			else
			{
				target = RunsafeServer.Instance.getPlayer(arguments[0]);
				if (target instanceof RunsafeAmbiguousPlayer)
					return target.toString();

				if (!target.isOnline())
					return String.format("&cThe player %s is offline.", target.getName());
			}
		}
		else
		{
			if (arguments.length < 1)
				return "&cYou must provide a player to apply an effect to.";

			target = RunsafeServer.Instance.getPlayer(arguments[0]);
			if (target instanceof RunsafeAmbiguousPlayer)
				return target.toString();

			if (!target.isOnline())
				return String.format("&cThe player %s is offline.", target.getName());
		}

		if (arguments.length > 1)
		{
			duration = Integer.parseInt(arguments[arguments.length - 2]);
			amp = Integer.parseInt(arguments[arguments.length - 1]);
		}

		BuffCommand.buffs.get(buffName).amplification(amp).duration(duration).applyTo(target);

		return null; // Remove
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
