package com.eco.Economy.Gui;

import com.eco.Economy.Utils.*;
import cpw.mods.fml.common.eventhandler.*;
import cpw.mods.fml.relauncher.*;
import net.minecraft.client.*;
import net.minecraft.client.entity.*;
import net.minecraft.client.gui.*;
import net.minecraft.util.*;
import net.minecraftforge.client.event.*;
import org.lwjgl.opengl.*;

public class MoneyOverlay extends GuiIngame {

    MoneyOverlay Instance = this;

    public MoneyOverlay() {
        super(Minecraft.getMinecraft());
    }




    @SubscribeEvent
    public void onRenderGameOverlay(RenderGameOverlayEvent event) {
        if (event.type == RenderGameOverlayEvent.ElementType.TEXT) {

                Instance.renderOverlay();

        }
    }



    @SideOnly(Side.CLIENT)
    public void renderOverlay()
    {
        ScaledResolution scaledresolution = new ScaledResolution(this.mc, this.mc.displayWidth, this.mc.displayHeight);
        int width = scaledresolution.getScaledWidth();
        int height = scaledresolution.getScaledHeight();

        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        GL11.glDisable(GL11.GL_LIGHTING);
        GL11.glEnable(GL11.GL_BLEND);

        EntityClientPlayerMP player = this.mc.thePlayer;

        InfoStorage money = InfoStorage.get(player);

        int Money;

        if(money != null)
        Money = money.GetMoney();
        else
        Money = 0;


        int x = 0;
        int y = 0;

        if(MoneyUtils.TextArea == 1){
            x = width - 40;
            y = 5;
        }else if (MoneyUtils.TextArea == 2){
            x = 40;
            y = 5;
        }else if (MoneyUtils.TextArea == 3){
            x = width - 40;
            y = height - 5;
        }else if (MoneyUtils.TextArea == 4){
            x = 40;
            y = height - 5;

        }else{
            x = width - 40;
            y = 5;
        }

        String Pre = EnumChatFormatting.DARK_GREEN + MoneyUtils.MoneyMark;

        if(Money < 10000){
            if(Money < 1000){
                if(Money < 100){
                    if(Money < 10){
                        this.drawCenteredString(this.mc.fontRenderer, Pre +"0000" + Money, x, y, 0xffffff);

                    }else{
                        this.drawCenteredString(this.mc.fontRenderer, Pre +"000" + Money, x, y, 0xffffff);
                    }

                }else{
                    this.drawCenteredString(this.mc.fontRenderer, Pre + "00" + Money, x, y, 0xffffff);
                }
            }else{
                this.drawCenteredString(this.mc.fontRenderer, Pre + "0" + Money, x, y, 0xffffff);
            }

        }else{
            this.drawCenteredString(this.mc.fontRenderer, Pre + Money, x, y, 0xffffff);
        }




        GL11.glDisable(GL11.GL_BLEND);
    }
}

