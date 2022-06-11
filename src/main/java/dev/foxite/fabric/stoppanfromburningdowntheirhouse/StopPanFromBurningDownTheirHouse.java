package dev.foxite.fabric.stoppanfromburningdowntheirhouse;

import com.google.gson.GsonBuilder;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigHolder;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StopPanFromBurningDownTheirHouse implements ModInitializer {
	private static StopPanFromBurningDownTheirHouse s_Instance;

	public static final String MODID = "SPFBDTH";
	public static final Logger LOGGER = LoggerFactory.getLogger(MODID);
	private ConfigHolder<ModConfig> m_Config;

	@Override
	public void onInitialize() {
		s_Instance = this;

		AutoConfig.register(ModConfig.class, (config, aClass) -> new GsonConfigSerializer<>(config, aClass, new GsonBuilder().setPrettyPrinting().serializeNulls().create()));
		m_Config = AutoConfig.getConfigHolder(ModConfig.class);
	}

	public static StopPanFromBurningDownTheirHouse getInstance() {
		return s_Instance;
	}

	public ConfigHolder<ModConfig> getConfig() {
		return m_Config;
	}
}
