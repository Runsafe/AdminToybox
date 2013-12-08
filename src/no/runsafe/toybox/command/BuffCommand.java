package no.runsafe.toybox.command;

import no.runsafe.framework.api.IServer;
import no.runsafe.framework.api.command.ExecutableCommand;
import no.runsafe.framework.api.command.ICommandExecutor;
import no.runsafe.framework.api.command.argument.EnumArgument;
import no.runsafe.framework.api.command.argument.OptionalArgument;
import no.runsafe.framework.api.command.argument.PlayerArgument;
import no.runsafe.framework.api.player.IPlayer;
import no.runsafe.framework.minecraft.Buff;
import no.runsafe.framework.minecraft.player.RunsafeAmbiguousPlayer;
import no.runsafe.framework.minecraft.player.RunsafePlayer;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class BuffCommand extends ExecutableCommand
{
	public BuffCommand(IServer server)
	{
		super(
			"buff", "Apply a buff to a target player", "runsafe.toybox.buff",
			new EnumArgument("effect", buffs.keySet(), true),
			new OptionalArgument("duration"), new OptionalArgument("amplitude"),
			new PlayerArgument(false)
		);
		this.server = server;
	}

	@Override
	public String OnExecute(ICommandExecutor executor, Map<String, String> parameters)
	{
		String buffName = parameters.get("effect");
		if (!BuffCommand.buffs.containsKey(buffName))
			return "&cAvailable effects: " + StringUtils.join(BuffCommand.buffs.keySet(), ", ");

		IPlayer target = null;
		int duration = 36000;
		int amp = 5;

		if (executor instanceof IPlayer)
			target = (IPlayer) executor;

		if (parameters.containsKey("player"))
			target = server.getOnlinePlayer(
				executor instanceof RunsafePlayer ? (RunsafePlayer) executor : null,
				parameters.get("player")
			);

		if (target instanceof RunsafeAmbiguousPlayer)
			return target.toString();

		if (target == null)
			return "&cYou must provide a player to apply an effect to.";

		if (!target.isOnline())
			return String.format("&cThe player %s is offline.", target.getName());

		if (parameters.containsKey("duration"))
			duration = Integer.parseInt(parameters.get("duration"));
		if (parameters.containsKey("amplitude"))
			amp = Integer.parseInt(parameters.get("amplitude"));

		BuffCommand.buffs.get(buffName).amplification(amp).duration(duration).applyTo(target);
		return null;
	}

	private final IServer server;

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
