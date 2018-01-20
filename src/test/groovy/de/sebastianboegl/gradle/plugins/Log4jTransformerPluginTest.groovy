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

package de.sebastianboegl.gradle.plugins

import de.sebastianboegl.gradle.plugins.shadow.transformers.Log4j2PluginsFileTransformer
import de.sebastianboegl.gradle.plugins.util.FunctionalSpecification

import static org.gradle.testkit.runner.TaskOutcome.SUCCESS

class Log4jTransformerPluginTest extends FunctionalSpecification {
    def setup() {
        buildFile << """
            plugins {
                id 'java'
                id 'de.sebastianboegl.shadow.transformer.log4j'
            }
        """
    }

    def 'Applying the plugin applies the shadow plugin'() {
        given:
        buildFile << """
            task checkShadow {
                doLast {
                    println project.tasks.shadowJar.name
                }
            }
        """

        when:
        def result = build('-q', 'checkShadow')

        then:
        result.task(':checkShadow').outcome == SUCCESS
        result.output.trim() == 'shadowJar'
    }

    def 'Applying the plugin adds PluginsCacheFileTransformer as first transformer'() {
        given:
        buildFile << """
            task firstTransformer {
                doLast {
                    def shadow = project.tasks.shadowJar
                    println shadow.transformers.get(0).getClass().toString()
                }
            }
        """

        when:
        def result = build('-q', 'firstTransformer')

        then:
        result.task(':firstTransformer').outcome == SUCCESS
        result.output.trim() == Log4j2PluginsFileTransformer.class.toString()
    }

}
