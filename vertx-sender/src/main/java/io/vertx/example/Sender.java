package io.vertx.example;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.VertxOptions;

/**
 * @author Santhosh Kani
 */
public class Sender {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Vertx.factory.clusteredVertx(new VertxOptions().setClustered(true), new Handler<AsyncResult<Vertx>>() {

            @Override
            public void handle(AsyncResult<Vertx> event) {
                if (event.succeeded()) {
                    Vertx vertx = event.result();
                    vertx.deployVerticle(EventGenerator.class.getName());
                }
            }
        });
    }

}
