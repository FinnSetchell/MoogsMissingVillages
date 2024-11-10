package com.finndog.mst;

import com.finndog.mst.events.lifecycle.RegisterReloadListenerEvent;
import com.finndog.mst.events.lifecycle.ServerGoingToStartEvent;
import com.finndog.mst.events.lifecycle.ServerGoingToStopEvent;
import com.finndog.mst.events.lifecycle.SetupEvent;
import com.finndog.mst.modinit.MSPlacements;
import com.finndog.mst.modinit.MSProcessors;
import com.finndog.mst.modinit.MSStructurePieces;
import com.finndog.mst.modinit.MSStructurePlacementType;
import com.finndog.mst.modinit.MSStructures;
import com.finndog.mst.modinit.MSTags;
import com.finndog.mst.utils.AsyncLocator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class MSCommon {
    public static final String MODID = "mst";
    public static final Logger LOGGER = LogManager.getLogger();

    public static void init() {
        MSTags.initTags();

        MSStructures.STRUCTURE_TYPE.init();
        MSPlacements.PLACEMENT_MODIFIER.init();
        MSProcessors.STRUCTURE_PROCESSOR.init();
        MSStructurePieces.STRUCTURE_PIECE.init();
        MSStructurePieces.STRUCTURE_POOL_ELEMENT.init();
        MSStructurePlacementType.STRUCTURE_PLACEMENT_TYPE.init();

        SetupEvent.EVENT.addListener(MSCommon::setup);
        RegisterReloadListenerEvent.EVENT.addListener(MSCommon::registerDatapackListener);
        ServerGoingToStartEvent.EVENT.addListener(MSCommon::serverAboutToStart);
        ServerGoingToStopEvent.EVENT.addListener(MSCommon::onServerStopping);
    }

    private static void setup(final SetupEvent event) {
    }

    private static void serverAboutToStart(final ServerGoingToStartEvent event) {

        AsyncLocator.handleServerAboutToStartEvent();
    }

    private static void onServerStopping(final ServerGoingToStopEvent event) {
        AsyncLocator.handleServerStoppingEvent();
    }

    public static void registerDatapackListener(final RegisterReloadListenerEvent event) {
    }
}
