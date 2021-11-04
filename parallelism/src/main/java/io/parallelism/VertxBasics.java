package io.parallelism;

import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;

public class VertxBasics {

    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();

        vertx.deployVerticle(new BasicVerticle(), new Handler<AsyncResult<String>>() {
            @Override
            public void handle(AsyncResult<String> stringAsyncResult) {
                if (stringAsyncResult.succeeded()) {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Basic Verticle deployment complete");
                } else if (stringAsyncResult.failed()) {
                    System.out.println("Basic Verticle deployment failed");
                    System.exit(1);
                }
            }
        });
    }
}
