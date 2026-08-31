package dev.pastbound.registry;

import dev.pastbound.ModId;
import java.util.Map;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAsset;
import net.minecraft.world.item.equipment.EquipmentAssets;

public final class ModArmorMaterials {
    public static final ResourceKey<EquipmentAsset> ERVANIUM_ASSET = ResourceKey.create(
            EquipmentAssets.ROOT_ID,
            Identifier.fromNamespaceAndPath(ModId.MOD_ID, "ervanium")
    );

    public static final ArmorMaterial ERVANIUM = new ArmorMaterial(
            37,
            Map.of(
                    ArmorType.BOOTS, 3,
                    ArmorType.LEGGINGS, 6,
                    ArmorType.CHESTPLATE, 8,
                    ArmorType.HELMET, 3,
                    ArmorType.BODY, 8
            ),
            18,
            SoundEvents.ARMOR_EQUIP_NETHERITE,
            4.0F,
            0.15F,
            ItemTags.REPAIRS_NETHERITE_ARMOR,
            ERVANIUM_ASSET
    );

    private ModArmorMaterials() {
    }
}
