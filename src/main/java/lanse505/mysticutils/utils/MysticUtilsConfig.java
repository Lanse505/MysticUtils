package lanse505.mysticutils.utils;

import com.google.common.collect.Maps;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;

import java.util.Map;

public class MysticUtilsConfig {
    public static void postInit(FMLPostInitializationEvent event){
    }

    @Config(modid = MysticUtilsConstants.MOD_ID, name = MysticUtilsConstants.MOD_NAME, type = Config.Type.INSTANCE)
    public static class Configs {
        public static Modules modules;

        public static class Modules {
            @Config.Comment({"A list of all mods that CompatSkills has integrated compatability with.", "Setting any of these to false disables the respective compat:"})
            public static Map<String, Boolean> compat = Maps.newHashMap(Maps.toMap(CompatModuleBase.moduleClasses.keySet(), (k) -> Boolean.TRUE));
        }
    }
}
