package com.kilkags.touchecho.commond;

import com.kilkags.touchecho.TouchEcho;
import com.kilkags.touchecho.capability.CapabilityRegistryHandler;
import com.kilkags.touchecho.capability.DirtBallPower;
import com.kilkags.touchecho.toolkits.LotusSymphony;
import com.kilkags.touchecho.network.NetworkRegistryHandler;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.server.MinecraftServer;
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
        return "commands.touchecho.usage1";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        EntityPlayer player= CommandBase.getCommandSenderAsPlayer(sender);
        DirtBallPower power = player.getCapability(CapabilityRegistryHandler.DIRT_BALL_POWER, null);
        if(args.length > 0) {
            switch (args[0]) {
                case "set":
                    if(args.length > 2 && LotusSymphony.isNumber(args[2])) {
                        int amount = Integer.parseInt(args[2]);

                        switch (args[1]) {
                            case "orange":
                                assert power != null;
                                power.setOrangePower(amount);
                                break;
                            case "blue":
                                assert power != null;
                                power.setBluePower(amount);
                                break;
                            case "green":
                                assert power != null;
                                power.setGreenPower(amount);
                                break;
                            default:
                                this.sendErrorMessage(player);
                                break;
                        }

                        /* 同步数据 */
                        NetworkRegistryHandler.Power.sendClientCustomPacket(player);
                    }
                    else {
                        this.sendErrorMessage(player);
                    }
                    break;
                case "help":
                    player.sendMessage(new TextComponentTranslation("commands.touchecho.help.line1"));
                    break;
                default:
                    break;
            }
        }
        else {
            player.sendMessage(new TextComponentTranslation("commands.touchecho.usage2"));
        }

    }

    @Override
    public int getRequiredPermissionLevel() {
        return 1;
    }

    private void sendErrorMessage(EntityPlayer player) {
        player.sendMessage(new TextComponentTranslation("commands.touchecho.error"));
    }
}
