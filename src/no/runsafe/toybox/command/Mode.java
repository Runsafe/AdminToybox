package no.runsafe.toybox.command;

import no.runsafe.framework.api.command.ExecutableCommand;
import no.runsafe.framework.api.command.ICommandExecutor;
import no.runsafe.framework.api.command.argument.EnumArgument;
import no.runsafe.framework.api.command.argument.PlayerArgument;
import no.runsafe.framework.api.player.IPlayer;
import no.runsafe.framework.minecraft.RunsafeServer;
import no.runsafe.framework.minecraft.player.RunsafeAmbiguousPlayer;
import no.runsafe.framework.minecraft.player.RunsafePlayer;
import org.bukkit.GameMode;

import java.util.Map;

public class Mode extends ExecutableCommand
{
	public Mode()
	{
		super(
			"mode", "Changes the game-mode of the player", "runsafe.toybox.mode",
			new EnumArgument("mode", GameMode.values(), true), new PlayerArgument(false)
		);
	}

	@Override
	public String OnExecute(ICommandExecutor executor, Map<String, String> parameters)
	{
		if (!(executor instanceof RunsafePlayer) && !parameters.containsKey("player"))
			return "&cYou need to supply a player for this command when called from the console.";

		IPlayer target = (parameters.containsKey("player") ? RunsafeServer.Instance.getPlayer(parameters.get("player")) : (IPlayer) executor);
		if (target == null)
			return "Player not found";
		if (target instanceof RunsafeAmbiguousPlayer)
			return target.toString();

		if (!target.isOnline())
			return String.format("&cThe player %s is offline.", target.getName());

		String mode = parameters.get("mode");

		if (mode.equalsIgnoreCase("survival") || mode.equalsIgnoreCase("s"))
			target.setGameMode(GameMode.SURVIVAL);

		if (mode.equalsIgnoreCase("creative") || mode.equalsIgnoreCase("c"))
			target.setGameMode(GameMode.CREATIVE);

		if (mode.equalsIgnoreCase("adventure") || mode.equalsIgnoreCase("a"))
			target.setGameMode(GameMode.ADVENTURE);

		return this.getGameModeUpdateMessage(target);
	}

	private String getGameModeUpdateMessage(IPlayer target)
	{
		String gameModeName = "unknown";

		if (target.getGameMode() == GameMode.SURVIVAL)
			gameModeName = "survival";

		if (target.getGameMode() == GameMode.CREATIVE)
			gameModeName = "creative";

		if (target.getGameMode() == GameMode.ADVENTURE)
			gameModeName = "adventure";

		return String.format("%s now has the game mode %s.", target.getPrettyName(), gameModeName);
	}
}

