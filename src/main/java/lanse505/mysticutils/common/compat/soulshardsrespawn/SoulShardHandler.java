package lanse505.mysticutils.common.compat.soulshardsrespawn;

import lanse505.mysticutils.utils.CompatModuleBase;
import net.minecraftforge.common.MinecraftForge;

public class SoulShardHandler extends CompatModuleBase {

    @Override
    public void preInit() {
        MinecraftForge.EVENT_BUS.register(new SoulHandler());
    }
}
