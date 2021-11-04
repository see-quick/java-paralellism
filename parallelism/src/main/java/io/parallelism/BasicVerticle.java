package io.parallelism;

import io.vertx.core.AbstractVerticle;

public class BasicVerticle extends AbstractVerticle {

    @Override
    public void start() {
        System.out.println("BasicVerticle started");
    }

    @Override
    public void stop() {
        System.out.println("BasicVerticle stopped");
    }
}

