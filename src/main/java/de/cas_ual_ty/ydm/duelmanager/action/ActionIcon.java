package de.cas_ual_ty.ydm.duelmanager.action;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class ActionIcon extends ForgeRegistryEntry<ActionIcon>
{
    public final ResourceLocation sourceFile;
    public final int fileSize;
    public final int iconWidth;
    public final int iconHeight;
    public final byte iconIndex;
    
    public ActionIcon(ResourceLocation sourceFile, int fileSize, int iconWidth, int iconHeight, byte iconIndex)
    {
        this.sourceFile = sourceFile;
        this.fileSize = fileSize;
        this.iconWidth = iconWidth;
        this.iconHeight = iconHeight;
        this.iconIndex = iconIndex;
    }
}
