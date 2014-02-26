package no.runsafe.toybox.repositories;

import no.runsafe.framework.api.ILocation;
import no.runsafe.framework.api.database.*;

import java.util.ArrayList;
import java.util.List;

public class LockedObjectRepository extends Repository
{
	@Override
	public String getTableName()
	{
		return "toybox_locked_objects";
	}

	public void storeLockedObject(ILocation location)
	{
		database.execute("INSERT INTO toybox_locked_objects (world, x, y, z) VALUES(?, ?, ?, ?)",
			location.getWorld().getName(), location.getBlockX(), location.getBlockY(), location.getBlockZ());
	}

	public void removeLockedObject(ILocation location)
	{
		database.execute(
			"DELETE FROM toybox_locked_objects WHERE world = ? AND x = ? AND y =? AND z = ?",
			location.getWorld().getName(), location.getBlockX(), location.getBlockY(), location.getBlockZ()
		);
	}

	public List<ILocation> getLockedObjects(String worldName)
	{
		List<ILocation> locations = new ArrayList<ILocation>();
		ISet data = database.query("SELECT world, x, y, z FROM toybox_locked_objects WHERE world=?", worldName);

		if (data != null)
			for (IRow node : data)
				locations.add(node.Location());

		return locations;
	}

	@Override
	public ISchemaUpdate getSchemaUpdateQueries()
	{
		ISchemaUpdate update = new SchemaUpdate();

		update.addQueries(
			"CREATE TABLE `toybox_locked_objects` (" +
				"`world` VARCHAR(255) NOT NULL," +
				"`x` DOUBLE NOT NULL," +
				"`y` DOUBLE NOT NULL," +
				"`z` DOUBLE NOT NULL" +
			")"
		);

		return update;
	}
}