package com.kilkags.touchecho.commond;

import com.kilkags.touchecho.TouchEcho;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.command.WrongUsageException;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextComponentTranslation;

/**
 * @author DustW
 */
public class ModCommandCommon extends CommandBase {
    @Override
    public String getName() {
        return TouchEcho.MOD_ID;
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "commands.touchecho.usage";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        if (args.length > 1) {
            throw new WrongUsageException("commands.touchecho.usage1");
        }
        else {
            EntityPlayerMP entityPlayerMP = args.length > 0 ? CommandBase.getPlayer(server, sender, args[0])
                    : CommandBase.getCommandSenderAsPlayer(sender);
            entityPlayerMP.sendMessage(new TextComponentTranslation("commands.touchecho.usage2"));
        }
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 1;
    }
}
