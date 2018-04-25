package com.softexploration.binarrior.application;

public class Application {

    public static void main(final String[] args) {

        final ApplicationController controller = new ApplicationControllerFactory().createApplicationController();
        controller.launchUI();

    }
}
