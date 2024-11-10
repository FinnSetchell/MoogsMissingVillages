package com.finndog.mmv;

import com.finndog.mmv.events.lifecycle.RegisterReloadListenerEvent;
import com.finndog.mmv.events.lifecycle.ServerGoingToStartEvent;
import com.finndog.mmv.events.lifecycle.ServerGoingToStopEvent;
import com.finndog.mmv.events.lifecycle.SetupEvent;
import com.finndog.mmv.modinit.MSPlacements;
import com.finndog.mmv.modinit.MSProcessors;
import com.finndog.mmv.modinit.MSStructurePieces;
import com.finndog.mmv.modinit.MSStructurePlacementType;
import com.finndog.mmv.modinit.MSStructures;
import com.finndog.mmv.modinit.MSTags;
import com.finndog.mmv.utils.AsyncLocator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class MSCommon {
    public static final String MODID = "mmv";
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
