package lanse505.mysticutils.common.compat.soulshardsrespawn;

import info.tehnut.soulshardsrespawn.api.BindingEvent;
import info.tehnut.soulshardsrespawn.api.IBinding;
import info.tehnut.soulshardsrespawn.api.IShardTier;
import info.tehnut.soulshardsrespawn.core.data.Tier;
import lanse505.mysticutils.MysticUtils;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class SoulHandler {
    @SubscribeEvent
    public void gainSoulsEvent(BindingEvent.GainSouls event) {
        MysticUtils.getLogger().info("boop");
        IBinding binding = event.getBinding();
        int killCount = binding.getKills();
        IShardTier currentTier = binding.getTier();
        int nextTierIndex = currentTier.getIndex() + 1;
        if (Tier.INDEXED.size() > nextTierIndex) {
            IShardTier nextTier = Tier.INDEXED.get(nextTierIndex);
            if (killCount == nextTier.getKillRequirement() - 1) {
                event.setAmount(0);
            } else if ((killCount + event.getAmount()) > (nextTier.getKillRequirement() - 1)) {
                int amount = Math.min(event.getAmount(), nextTier.getKillRequirement() - 1 - killCount);
                event.setAmount(Math.max(amount, 0));
            }
        }
    }
}