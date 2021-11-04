package io.parallelism;

import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Promise;
import io.vertx.core.Vertx;

public class ExampleFutures {

    public static void deployVerticleWithoutLoop(int i) {
        // think on Promise as producer (used by producer on one side of async operation) and Future as consumer (used by consumer on the other side).
        Promise<String> promise = Promise.promise();
        Future<String> future = promise.future();
        Vertx vertx = Vertx.vertx();

        vertx.deployVerticle(new BasicVerticle(), stringAsyncResult -> {
            if (stringAsyncResult.succeeded()) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Basic Verticle deployment complete " + i);
            } else if (stringAsyncResult.failed()) {
                System.out.println("Basic Verticle deployment failed " + i);
                System.exit(1);
            }
            promise.handle(stringAsyncResult);
        });

        while(!future.isComplete()) {
            System.out.println("Complete? -> " + i + " - " + future.isComplete());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void deployVerticleWithLoop() {
        for (int i = 0; i < 10; i++) {
            deployVerticleWithoutLoop(i);
        }
    }


    public static void main(String[] args) {
        deployVerticleWithLoop();
    }
}
