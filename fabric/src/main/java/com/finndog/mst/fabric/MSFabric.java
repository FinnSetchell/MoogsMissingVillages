package com.finndog.mst.fabric;

import com.finndog.mst.MSCommon;
import com.finndog.mst.events.lifecycle.RegisterReloadListenerEvent;
import com.finndog.mst.events.lifecycle.ServerGoingToStartEvent;
import com.finndog.mst.events.lifecycle.ServerGoingToStopEvent;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.minecraft.server.packs.PackType;

public class MSFabric implements ModInitializer {

    @Override
    public void onInitialize() {
        MSCommon.init();

        ServerLifecycleEvents.SERVER_STARTING.register(server -> {
            ServerGoingToStartEvent.EVENT.invoke(new ServerGoingToStartEvent(server));

        });

        ServerLifecycleEvents.SERVER_STOPPING.register(server ->
                ServerGoingToStopEvent.EVENT.invoke(ServerGoingToStopEvent.INSTANCE));

        RegisterReloadListenerEvent.EVENT.invoke(new RegisterReloadListenerEvent((id, listener) ->
                ResourceManagerHelper.get(PackType.SERVER_DATA).registerReloadListener(new FabricReloadListener(id, listener))));
    }
}
