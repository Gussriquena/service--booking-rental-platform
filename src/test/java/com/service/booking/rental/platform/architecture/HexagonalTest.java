package com.service.booking.rental.platform.architecture;

import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.library.Architectures;
import org.junit.jupiter.api.DisplayName;

@AnalyzeClasses(packages = "com.service.booking.rental.platform")
public class HexagonalTest {

    @ArchTest
    public static final ArchRule layersValidator = Architectures.layeredArchitecture()
            .consideringOnlyDependenciesInLayers()
            .layer("Entities").definedBy("..entities..")
            .layer("Interactors").definedBy("..interactors..")
            .layer("Repositories").definedBy("..repositories..")
            .layer("Datasources").definedBy("..datasources..")
            .layer("TransportLayers").definedBy("..transportlayers..")
            .layer("Configs").definedBy("..configs..")
            .whereLayer("Interactors").mayOnlyBeAccessedByLayers("TransportLayers", "Configs")
            .whereLayer("Repositories").mayOnlyBeAccessedByLayers("Interactors", "Datasources", "Configs")
            .whereLayer("Datasources").mayOnlyBeAccessedByLayers("Configs")
            .whereLayer("TransportLayers").mayOnlyBeAccessedByLayers("Configs")
            .whereLayer("Configs").mayOnlyBeAccessedByLayers("TransportLayers", "Interactors", "Datasources");
}
