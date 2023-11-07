package no.runsafe.toybox.command;

import no.runsafe.framework.api.command.argument.IArgumentList;
import no.runsafe.framework.api.command.player.PlayerCommand;
import no.runsafe.framework.api.event.player.IPlayerInteractEntityEvent;
import no.runsafe.framework.api.player.IPlayer;
import no.runsafe.framework.minecraft.entity.RunsafeEntity;
import no.runsafe.framework.minecraft.entity.RunsafeLivingEntity;
import no.runsafe.framework.minecraft.event.player.RunsafePlayerInteractEntityEvent;
import no.runsafe.toybox.PendingLasso;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class Lasso extends PlayerCommand implements IPlayerInteractEntityEvent
{
	public Lasso()
	{
		super("lasso", "Lasso two entities together.", "runsafe.toybox.lasso");
	}

	@Override
	public String OnExecute(IPlayer executor, IArgumentList parameters)
	{
		UUID playerID = executor.getUniqueId();
		if (stages.containsKey(playerID))
		{
			stages.remove(playerID);
			return "&cLasso disabled.";
		}
		else
		{
			stages.put(playerID, new PendingLasso());
			return "&aLasso enabled: Right click the entity that will hold the leash.";
		}
	}

	@Override
	public void OnPlayerInteractEntityEvent(RunsafePlayerInteractEntityEvent event)
	{
		RunsafeEntity entity = event.getRightClicked();
		if (entity == null)
			return;

		IPlayer player = event.getPlayer();
		UUID playerID = player.getUniqueId();

		if (stages.containsKey(playerID))
		{
			PendingLasso pending = stages.get(playerID);
			if (!pending.hasEntity())
			{
				stages.get(playerID).setEntity(entity);
				player.sendColouredMessage("&aAttached. Now right click something for the leash to lasso.");
			}
			else
			{
				if (entity instanceof RunsafeLivingEntity)
				{
					((RunsafeLivingEntity) entity).setLeashHolder(stages.get(playerID).getEntity());
					stages.remove(playerID);
					player.sendColouredMessage("&aLasso complete.");
				}
				else
				{
					player.sendColouredMessage("&cA leash can only have something living on that end.");
				}
			}
		}
	}

	private final ConcurrentHashMap<UUID, PendingLasso> stages = new ConcurrentHashMap<>(0);
}
