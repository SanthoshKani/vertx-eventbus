package io.vertx.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.Message;
import io.vertx.core.eventbus.MessageConsumer;

/**
 * @author Santhosh Kani
 */
public class EventConsumer extends AbstractVerticle {
    private static final Logger LOG = LogManager.getLogger();

    /**
     * 
     */
    public EventConsumer() {
    }

    /* (non-Javadoc)
     * @see io.vertx.core.AbstractVerticle#start()
     */
    @Override
    public void start() throws Exception {

        final EventBus eb = vertx.eventBus();

        MessageConsumer<String> messageConsumer = eb.consumer("message-queue");
        messageConsumer.handler(new Handler<Message<String>>() {

            @Override
            public void handle(Message<String> event) {
                System.out.println("Received : " + event.body());
            }
        });

        messageConsumer.completionHandler(new Handler<AsyncResult<Void>>() {
            @Override
            public void handle(AsyncResult<Void> event) {
                if (event.succeeded()) {
                    LOG.info("The handler has been registered with all nodes.");
                } else {
                    LOG.warn("Handler registration failed. " + event.cause().getMessage());
                }
            }
        });
    }

}
