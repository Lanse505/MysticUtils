package lanse505.mysticutils.common.compat.soulshardsrespawn;

import info.tehnut.soulshardsrespawn.api.BindingEvent;
import info.tehnut.soulshardsrespawn.api.IBinding;
import info.tehnut.soulshardsrespawn.api.IShardTier;
import info.tehnut.soulshardsrespawn.core.data.Tier;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class SoulHandler {
    @SubscribeEvent
    public void gainSoulsEvent(BindingEvent.GainSouls event) {
        IBinding binding = event.getBinding();
        int killCount = binding.getKills();
        IShardTier currentTier = binding.getTier();
        int nextTierIndex = currentTier.getIndex() + 1;
        if (Tier.INDEXED.size() > nextTierIndex) {
            IShardTier nextTier = Tier.INDEXED.get(nextTierIndex);
            if (killCount >= nextTier.getKillRequirement()) {
                event.setAmount(nextTier.getKillRequirement() - 1);
            }
        }
    }
}