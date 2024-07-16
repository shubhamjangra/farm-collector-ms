package com.farm.collector.

{{cookiecutter.service_package}}.archunit;

import com.farm.collector.archunit.AbstractArchUnitTests;
import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;

@AnalyzeClasses(packages = "com.farm.collector",
        importOptions = {ImportOption.DoNotIncludeTests.class, ImportOption.DoNotIncludeJars.class})
public class ArchUnitTests extends AbstractArchUnitTests {

}
