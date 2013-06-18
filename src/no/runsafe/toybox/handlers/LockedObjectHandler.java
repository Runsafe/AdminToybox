package no.runsafe.toybox.handlers;

import no.runsafe.framework.api.event.block.IBlockBreakEvent;
import no.runsafe.framework.api.event.plugin.IPluginDisabled;
import no.runsafe.framework.api.event.plugin.IPluginEnabled;
import no.runsafe.framework.minecraft.Item;
import no.runsafe.framework.minecraft.RunsafeLocation;
import no.runsafe.framework.minecraft.block.RunsafeBlock;
import no.runsafe.framework.minecraft.event.block.RunsafeBlockBreakEvent;
import no.runsafe.toybox.repositories.LockedObjectRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LockedObjectHandler implements IPluginEnabled, IPluginDisabled, IBlockBreakEvent
{
	public LockedObjectHandler(LockedObjectRepository repository)
	{
		this.repository = repository;
	}

	public boolean isLockedBlock(RunsafeBlock block)
	{
		RunsafeLocation blockLocation = block.getLocation();
		String worldName = blockLocation.getWorld().getName();

		if (this.lockedObjects.containsKey(worldName))
		{
			List<RunsafeLocation> locations = this.lockedObjects.get(worldName);
			for (RunsafeLocation location : locations)
				if (location.distance(blockLocation) < 1)
					return true;
		}
		return false;
	}

	public boolean canLockBlock(RunsafeBlock block)
	{
		for (Item item : LockedObjectHandler.lockableItems)
			if (block.is(item))
				return true;

		return false;
	}

	public void lockBlock(RunsafeBlock block)
	{
		if (!this.isLockedBlock(block))
		{
			RunsafeLocation location = block.getLocation();
			String worldName = location.getWorld().getName();

			this.repository.storeLockedObject(location);
			if (!this.lockedObjects.containsKey(worldName))
				this.lockedObjects.put(worldName, new ArrayList<RunsafeLocation>());

			this.lockedObjects.get(worldName).add(location);
		}
	}

	public void unlockBlock(RunsafeBlock block)
	{
		if (this.isLockedBlock(block))
		{
			RunsafeLocation location = block.getLocation();
			String worldName = location.getWorld().getName();

			if (this.lockedObjects.containsKey(worldName))
				for (RunsafeLocation checkLocation : this.lockedObjects.get(worldName))
					if (checkLocation.distance(location) < 1)
						this.lockedObjects.get(worldName).remove(checkLocation);

			this.repository.removeLockedObject(location);
		}
	}

	@Override
	public void OnBlockBreakEvent(RunsafeBlockBreakEvent event)
	{
		RunsafeBlock block = event.getBlock();

		if (block != null)
		{
			if (this.isLockedBlock(block))
			{
				event.setCancelled(true);
				if (event.getPlayer() != null)
					event.getPlayer().sendColouredMessage("&cThe block is impenetrable to your attempts.");
			}
		}
	}

	@Override
	public void OnPluginEnabled()
	{
		List<RunsafeLocation> locations = this.repository.getLockedObjects();
		for (RunsafeLocation location : locations)
		{
			String worldName = location.getWorld().getName();
			if (!this.lockedObjects.containsKey(worldName))
				this.lockedObjects.put(worldName, new ArrayList<RunsafeLocation>());

			this.lockedObjects.get(worldName).add(location);
		}
	}

	@Override
	public void OnPluginDisabled()
	{
		this.lockedObjects.clear(); // Dump any objects we have in memory.
	}

	private HashMap<String, List<RunsafeLocation>> lockedObjects = new HashMap<String, List<RunsafeLocation>>();
	private LockedObjectRepository repository;
	private static List<Item> lockableItems = new ArrayList<Item>();
	static
	{
		lockableItems.add(Item.Brewing.BrewingStand);
		lockableItems.add(Item.Redstone.Lever);
		lockableItems.add(Item.Redstone.Diode);
		lockableItems.add(Item.Redstone.Comparator);
		lockableItems.add(Item.Redstone.Button.Stone);
		lockableItems.add(Item.Redstone.Button.Wood);
		lockableItems.add(Item.Redstone.Device.Dispenser);
		lockableItems.add(Item.Redstone.Device.NoteBlock);
		lockableItems.add(Item.Redstone.Device.Hopper);
		lockableItems.add(Item.Redstone.Device.Dropper);
		lockableItems.add(Item.Decoration.Chest);
		lockableItems.add(Item.Decoration.EnchantmentTable);
		lockableItems.add(Item.Decoration.EnderChest);
		lockableItems.add(Item.Decoration.Anvil.Any);
	}
}
