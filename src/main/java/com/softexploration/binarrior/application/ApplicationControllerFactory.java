package com.softexploration.binarrior.application;

public class ApplicationControllerFactory {

    private final ApplicationControllerDependencyResolver applicationControllerDependencyResolver = new ApplicationControllerDependencyResolver();

    public ApplicationController createApplicationController() {
        final ApplicationController applicationController = new ApplicationController();
        applicationControllerDependencyResolver.resolveDependencies(applicationController);
        return applicationController;
    }
}
