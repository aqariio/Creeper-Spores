/*
 * Creeper Spores
 * Copyright (C) 2019-2022 Ladysnake
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; If not, see <https://www.gnu.org/licenses>.
 */
package io.github.ladysnake.creeperspores.client;

import io.github.ladysnake.creeperspores.CreeperEntry;
import io.github.ladysnake.creeperspores.CreeperSpores;
import io.github.ladysnake.creeperspores.common.CreeperlingEntity;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class CreeperSporesClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(
                CreeperEntry.getVanilla().creeperlingType(),
                (context) -> new CreeperlingEntityRenderer(context, CreeperlingEntityRenderer.DEFAULT_SKIN)
        );
        ClientPlayNetworking.registerGlobalReceiver(
                CreeperSpores.CREEPERLING_FERTILIZATION_PACKET,
                (client, handler, buf, responseSender) -> CreeperlingEntity.createParticles(client, client.player, buf)
        );
    }
}
