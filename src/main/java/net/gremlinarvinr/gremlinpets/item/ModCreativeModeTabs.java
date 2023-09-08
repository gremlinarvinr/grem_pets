package net.gremlinarvinr.gremlinpets.item;

import net.gremlinarvinr.gremlinpets.GremlinPets;
import net.gremlinarvinr.gremlinpets.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, GremlinPets.MOD_ID);

    public static final RegistryObject<CreativeModeTab> GREMLIN_PETS = CREATIVE_MODE_TABS.register("gremlin_pets",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.ALEXANDRITE.get()))
                    .title(Component.translatable("creativetab.gremlin_pets"))
                    .displayItems((displayParameters, output) -> {
                        output.accept(ModItems.ALEXANDRITE.get());
                        output.accept(ModItems.RAW_ALEXANDRITE.get());

                        output.accept(ModItems.RHINO_SPAWN_EGG.get());
                        output.accept(ModItems.RAT_SPAWN_EGG.get());

                        output.accept(ModBlocks.ALEXANDRITE_BLOCK.get());
                        output.accept(ModBlocks.RAW_ALEXANDRITE_BLOCK.get());
                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
