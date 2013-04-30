package no.runsafe.toybox.events;

import no.runsafe.framework.event.block.IBlockDispense;
import no.runsafe.framework.output.IOutput;
import no.runsafe.framework.server.RunsafeLocation;
import no.runsafe.framework.server.block.RunsafeBlock;
import no.runsafe.framework.server.block.RunsafeBlockState;
import no.runsafe.framework.server.block.RunsafeDispenser;
import no.runsafe.framework.server.block.RunsafeSign;
import no.runsafe.framework.server.item.RunsafeItemStack;

public class Dispense implements IBlockDispense
{
	public Dispense(IOutput output)
	{
		this.output = output;
	}

	@Override
	public boolean OnBlockDispense(RunsafeBlock block, RunsafeItemStack item)
	{
		RunsafeLocation location = block.getLocation();
		this.check(block, location, 0, 1, 0);
		this.check(block, location, 0, -1, 0);
		this.check(block, location, 1, 0, 0);
		this.check(block, location, -1, 0, 0);
		this.check(block, location, 0, 0, 1);
		this.check(block, location, 0, 0, -1);

		return true;
	}

	private void check(RunsafeBlock block, RunsafeLocation location, int x, int y, int z)
	{
		location.offset(x, y, z);
		RunsafeBlockState blockState = location.getBlock().getBlockState();
		if (blockState instanceof RunsafeSign)
		{
			this.output.write("Found sign around the dispenser");
			RunsafeSign sign = (RunsafeSign) blockState;
			if (sign.getLine(0).equals("[Infinite]"))
				this.fillDispenser(block, Integer.valueOf(sign.getLine(1)));
		}
	}

	private void fillDispenser(RunsafeBlock block, Integer itemID)
	{
		RunsafeDispenser dispenser = (RunsafeDispenser) block.getBlockState();
		dispenser.getInventory().addItems(new RunsafeItemStack(itemID, 64, (short) 0));
	}

	private IOutput output;
}
