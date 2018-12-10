package lanse505.mysticutils.utils;

import lanse505.mysticutils.MysticUtils;
import lanse505.mysticutils.common.compat.soulshardsrespawn.SoulShardHandler;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class CompatModuleBase {
    static HashMap<String, Class<? extends CompatModuleBase>> moduleClasses = new HashMap<>();
    private static Set<CompatModuleBase> modules = new HashSet<>();
    private static boolean serverStartingDone;

    static {
        moduleClasses.put("soulshardsrespawn", SoulShardHandler.class);
    }

    public static void doModulesPreInit() {
        for (Map.Entry<String, Class<? extends CompatModuleBase>> e : moduleClasses.entrySet()) {
            if (Loader.isModLoaded(e.getKey()) || e.getKey().equals("minecraft")) {
                try {
                    Boolean enabled = MysticUtilsConfig.Modules.compat.get(e.getKey());
                    if (enabled == null || !enabled) {
                        continue;
                    }
                    CompatModuleBase m = e.getValue().newInstance();
                    modules.add(m);
                    m.preInit();
                } catch (Exception exception) {
                    MysticUtils.getLogger().error("Compat module for " + e.getKey() + " could not be preInitialized. Report this!");
                }
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public static void doModulesPreInitClient() {
        for (CompatModuleBase compat : modules) {
            try {
                compat.clientPreInit();
            } catch (Exception exception) {
                MysticUtils.getLogger().error("Client compat module for " + compat + " could not be preInitialized. Report this!");
            }
        }
    }

    public static void doModulesInit() {
        for (CompatModuleBase compat : modules) {
            try {
                compat.init();
            } catch (Exception exception) {
                MysticUtils.getLogger().error("Compat module for " + compat + " could not be initialized");
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public static void doModulesInitClient() {
        for (CompatModuleBase compat : modules) {
            try {
                compat.clientInit();
            } catch (Exception exception) {
                MysticUtils.getLogger().error("Client compat module for " + compat + " could not be initialized");
            }
        }
    }

    public static void doModulesPostInit() {
        for (CompatModuleBase compat : modules) {
            try {
                compat.postInit();
            } catch (Exception exception) {
                MysticUtils.getLogger().error("Compat module for " + compat + " could not be postInitialized");
            }
        }
    }

    @SideOnly(Side.CLIENT)
    public static void doModulesPostInitClient() {
        for (CompatModuleBase compat : modules) {
            try {
                compat.clientPostInit();
            } catch (Exception exception) {
                MysticUtils.getLogger().error("Client compat module for " + compat + " could not be postInitialized");
            }
        }
    }

    public static void doModulesLoadComplete() {
        if (!serverStartingDone) {
            serverStartingDone = true;
            for (CompatModuleBase compat : modules) {
                try {
                    compat.loadComplete();
                } catch (Exception exception) {
                    MysticUtils.getLogger().error("Compat module for " + compat + " could not be initialized");
                    exception.printStackTrace();
                }
            }
        }
    }

    public abstract void preInit();

    public void init() {
    }

    public void postInit() {
    }

    public void loadComplete() {
    }

    @SideOnly(Side.CLIENT)
    public void clientPreInit() {
    }

    @SideOnly(Side.CLIENT)
    public void clientInit() {
    }

    @SideOnly(Side.CLIENT)
    public void clientPostInit() {
    }
}