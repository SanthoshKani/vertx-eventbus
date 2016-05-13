package io.vertx.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.EventBus;

/**
 * @author Santhosh Kani
 */
public class EventGenerator extends AbstractVerticle {
    private static final Logger LOG = LogManager.getLogger();

    /* (non-Javadoc)
     * @see io.vertx.core.AbstractVerticle#start()
     */
    @Override
    public void start() throws Exception {

        final EventBus eb = vertx.eventBus();
        vertx.setPeriodic(1000, new Handler<Long>() {

            @Override
            public void handle(Long event) {
                LOG.debug("Sending a event.");
                eb.publish("message-queue", "Publishing a new message.");
            }
        });
    }

}
