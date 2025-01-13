package org.bluefeather.utils;

import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.util.Formatting;
import net.minecraft.text.Text;

//? if <1.19 {
/* import net.minecraft.text.LiteralText; */
//?}
public class TextHandler {
    public static Text createText(String message, Formatting color) {
        //? if >=1.19 {
        return Text.literal(message).formatted(color);
        //?} else {
        /* return new LiteralText(message).formatted(color); */
        //?}
    }
    public static void sendFeedback(CommandContext<ServerCommandSource> context, String message, Formatting color, boolean broadcastToOps) {
        //? if >=1.20 {
        context.getSource().sendFeedback(() -> TextHandler.createText(message, color),  broadcastToOps);
        //?} else {
        /* context.getSource().sendFeedback(TextHandler.createText(message, color),  broadcastToOps); */
        //?}
    }
}



