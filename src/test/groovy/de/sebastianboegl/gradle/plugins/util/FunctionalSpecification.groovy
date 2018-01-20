/*
 * Copyright (c) 2018 Sebastian Boegl
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.sebastianboegl.gradle.plugins.util

import groovy.transform.CompileStatic
import org.gradle.testkit.runner.BuildResult
import org.gradle.testkit.runner.GradleRunner
import org.junit.Rule
import org.junit.rules.TemporaryFolder
import spock.lang.Specification

@CompileStatic
class FunctionalSpecification extends Specification {
    private final static boolean DEBUG = Boolean.parseBoolean(System.getProperty("org.gradle.testkit.debug", "false"))

    @Rule
    final TemporaryFolder testProjectDir = new TemporaryFolder()

    File projectDir
    File buildFile

    List<File> pluginClasspath

    def setup() {
        projectDir = testProjectDir.root
        buildFile = testProjectDir.newFile('build.gradle')

        def pluginClasspathResource = ((URLClassLoader) FunctionalSpecification.class.classLoader).findResource('plugin-classpath.txt')
        if (pluginClasspathResource == null) {
            throw new IllegalStateException('Plugin classpath resource file not found. Run the "testClasses" task.')
        }

        pluginClasspath = pluginClasspathResource.readLines().collect { new File(it) }

        buildFile << """
            plugins {
                id 'java'
                id 'de.sebastianboegl.shadow.transformer.log4j'
            }
        """
    }

    protected BuildResult build(String... arguments) {
        createAndConfigureGradleRunner(arguments).build()
    }

    protected GradleRunner createAndConfigureGradleRunner(String... arguments) {
        GradleRunner.create()
            .withProjectDir(projectDir)
            .withDebug(DEBUG)
            .withPluginClasspath(pluginClasspath)
            .withArguments(arguments)
    }
}
