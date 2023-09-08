package net.gremlinarvinr.gremlinpets.event;

import net.gremlinarvinr.gremlinpets.GremlinPets;
import net.gremlinarvinr.gremlinpets.item.ModItems;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = GremlinPets.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModEvents {

    @SubscribeEvent // necessary if Mod.Event subscribe is at top
    public static void livingDamage(LivingDamageEvent event) {
        if(event.getEntity() instanceof Sheep) {
            if(event.getSource().getDirectEntity() instanceof Player player) {
                if (player.getItemInHand(InteractionHand.MAIN_HAND).getItem() == ModItems.ALEXANDRITE.get()) {
                    GremlinPets.LOGGER.info(("Sheep was hit with Alexandrite by " + player.getName().getString()));
                } else if (player.getItemInHand(InteractionHand.MAIN_HAND).getItem() == Items.DIAMOND) {
                    GremlinPets.LOGGER.info("Sheep was hit with Diamond by " + player.getName().getString());
                } else {
                    GremlinPets.LOGGER.info("Sheep was hit by something else by " + player.getName().getString());
                }
            }
        }
    }
}
