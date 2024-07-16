package com.farm.collector;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;

@AnalyzeClasses(packages = "com.farm.collector", importOptions = {ImportOption.DoNotIncludeTests.class,
        ImportOption.DoNotIncludeJars.class})
public class ArchUnitTests {

    @ArchTest
    public void only_jackson_and_commons_lang_is_allowed(JavaClasses javaClasses) {
        classes()
                .that()
                .resideInAPackage("com.farm.collector..")
                .should()
                .onlyDependOnClassesThat()
                .resideInAnyPackage("java..",
                        "javax..",
                        "org..",
                        "com.fasterxml.jackson..",
                        "com.farm.collector..",
                        "..")
                .check(javaClasses);
    }
}
