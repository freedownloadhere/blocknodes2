package com.github.freedownloadhere.blocknodes2.mixin;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(FontRenderer.class)
public interface AccessorFontRenderer {
    @Accessor("locationFontTexture")
    ResourceLocation getFontLocation_blocknodes2();
}
