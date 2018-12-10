package lanse505.mysticutils;

import lanse505.mysticutils.utils.CompatModuleBase;
import lanse505.mysticutils.utils.MysticUtilsConstants;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = MysticUtilsConstants.MOD_ID, name = MysticUtilsConstants.MOD_NAME, version = MysticUtilsConstants.VERSION, acceptedMinecraftVersions = MysticUtilsConstants.MCVER, dependencies = MysticUtilsConstants.DEPENDENCIES)
public class MysticUtils {

    private static Logger logger;

    public static Logger getLogger() {
        return logger;
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
        CompatModuleBase.doModulesPreInit();
    }

    @EventHandler
    public void init(FMLInitializationEvent event) {

    }

    @EventHandler
    public void postInit(FMLPostInitializationEvent event) {

    }
}
