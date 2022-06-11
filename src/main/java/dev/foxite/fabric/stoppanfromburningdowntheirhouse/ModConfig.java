package dev.foxite.fabric.stoppanfromburningdowntheirhouse;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

import java.util.UUID;

@Config(name = StopPanFromBurningDownTheirHouse.MODID)
public class ModConfig implements ConfigData {
	public String[] targetUuids;
	public int range;

	private transient UUID[] targetUuidsParsed;

	public int getSquaredRange() {
		return range * range;
	}

	@Override
	public void validatePostLoad() throws ValidationException {
		if (targetUuids == null) {
			targetUuids = new String[0];
		}

		targetUuidsParsed = new UUID[targetUuids.length];
		for (int i = 0; i < targetUuids.length; i++) {
			targetUuidsParsed[i] = UUID.fromString(targetUuids[i]);
		}
	}

	public UUID[] getTargetUuidsParsed() {
		return targetUuidsParsed;
	}
}
