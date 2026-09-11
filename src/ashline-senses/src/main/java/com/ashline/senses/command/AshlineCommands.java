package com.ashline.senses.command;

import com.ashline.senses.faction.FactionId;
import com.ashline.senses.faction.StandingData;
import com.ashline.senses.world.BuildingTracker;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;

import java.util.Collection;

public final class AshlineCommands {
    private static final DynamicCommandExceptionType UNKNOWN_FACTION = new DynamicCommandExceptionType(
            id -> Component.literal("Unknown faction '" + id + "'. Use: " + FactionId.listIds()));

    private AshlineCommands() {}

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("ashline")
                .then(Commands.literal("remaining")
                        .executes(ctx -> remaining(ctx.getSource())))
                .then(Commands.literal("seal")
                        .executes(ctx -> seal(ctx.getSource(), true)))
                .then(Commands.literal("unseal")
                        .executes(ctx -> seal(ctx.getSource(), false)))
                .then(Commands.literal("standing")
                        .then(Commands.literal("add")
                                .requires(s -> s.hasPermission(2))
                                .then(Commands.argument("targets", EntityArgument.players())
                                        .then(Commands.argument("faction", StringArgumentType.word())
                                                .then(Commands.argument("amount", IntegerArgumentType.integer(-2000, 2000))
                                                        .executes(ctx -> standingAdd(
                                                                ctx.getSource(),
                                                                EntityArgument.getPlayers(ctx, "targets"),
                                                                StringArgumentType.getString(ctx, "faction"),
                                                                IntegerArgumentType.getInteger(ctx, "amount")))))))
                        .then(Commands.literal("get")
                                .then(Commands.argument("targets", EntityArgument.players())
                                        .then(Commands.argument("faction", StringArgumentType.word())
                                                .executes(ctx -> standingGet(
                                                        ctx.getSource(),
                                                        EntityArgument.getPlayers(ctx, "targets"),
                                                        StringArgumentType.getString(ctx, "faction"))))))
                        .then(Commands.literal("set")
                                .requires(s -> s.hasPermission(2))
                                .then(Commands.argument("targets", EntityArgument.players())
                                        .then(Commands.argument("faction", StringArgumentType.word())
                                                .then(Commands.argument("amount", IntegerArgumentType.integer(
                                                                StandingData.MIN, StandingData.MAX))
                                                        .executes(ctx -> standingSet(
                                                                ctx.getSource(),
                                                                EntityArgument.getPlayers(ctx, "targets"),
                                                                StringArgumentType.getString(ctx, "faction"),
                                                                IntegerArgumentType.getInteger(ctx, "amount")))))))));
    }

    private static FactionId requireFaction(String raw) throws CommandSyntaxException {
        return FactionId.parse(raw).orElseThrow(() -> UNKNOWN_FACTION.create(raw));
    }

    private static int standingAdd(CommandSourceStack source, Collection<ServerPlayer> players,
                                   String factionRaw, int amount) throws CommandSyntaxException {
        FactionId faction = requireFaction(factionRaw);
        StandingData data = StandingData.get(source.getServer());
        int last = 0;
        for (ServerPlayer player : players) {
            last = data.add(player.getUUID(), faction, amount);
            final int shown = last;
            source.sendSuccess(() -> Component.literal(
                    player.getGameProfile().getName() + " " + faction.id() + " standing=" + shown
                            + " (delta " + amount + ")"), true);
        }
        return last;
    }

    private static int standingGet(CommandSourceStack source, Collection<ServerPlayer> players,
                                   String factionRaw) throws CommandSyntaxException {
        FactionId faction = requireFaction(factionRaw);
        StandingData data = StandingData.get(source.getServer());
        int last = 0;
        for (ServerPlayer player : players) {
            last = data.get(player.getUUID(), faction);
            final int shown = last;
            source.sendSuccess(() -> Component.literal(
                    player.getGameProfile().getName() + " " + faction.id() + " standing=" + shown), false);
        }
        return last;
    }

    private static int standingSet(CommandSourceStack source, Collection<ServerPlayer> players,
                                   String factionRaw, int amount) throws CommandSyntaxException {
        FactionId faction = requireFaction(factionRaw);
        StandingData data = StandingData.get(source.getServer());
        int last = 0;
        for (ServerPlayer player : players) {
            last = data.set(player.getUUID(), faction, amount);
            final int shown = last;
            source.sendSuccess(() -> Component.literal(
                    player.getGameProfile().getName() + " " + faction.id() + " standing=" + shown), true);
        }
        return last;
    }

    private static int remaining(CommandSourceStack source) {
        ServerPlayer player = source.getPlayer();
        if (player == null) {
            return 0;
        }
        ServerLevel level = player.serverLevel();
        String msg = BuildingTracker.get(level).describe(level, player.blockPosition());
        source.sendSuccess(() -> Component.literal(msg), false);
        return 1;
    }

    private static int seal(CommandSourceStack source, boolean sealed) {
        ServerPlayer player = source.getPlayer();
        if (player == null) {
            return 0;
        }
        ServerLevel level = player.serverLevel();
        boolean ok = BuildingTracker.get(level).setSealed(level, player.blockPosition(), sealed);
        source.sendSuccess(() -> Component.literal(ok ? ("sealed=" + sealed) : "no structure here"), false);
        return ok ? 1 : 0;
    }
}
