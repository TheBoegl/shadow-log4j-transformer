/*
 * Copyright (c) 2017 Sebastian Boegl
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

import com.github.jengelman.gradle.plugins.shadow.ShadowPlugin
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import de.sebastianboegl.gradle.plugins.shadow.transformers.Log4j2PluginsFileTransformer
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.logging.Logging
import org.slf4j.Logger

class Log4jTransformerPlugin implements Plugin<Project> {

    private static final Logger logger = Logging.getLogger(Log4jTransformerPlugin.class)

    @Override
    void apply(Project project) {
        project.plugins.apply(ShadowPlugin)
        logger.debug("applied ShadowPlugin")
        project.afterEvaluate {
            project.tasks.withType(ShadowJar).each { shadow ->
                shadow.transform(new Log4j2PluginsFileTransformer())
                logger.debug("add Log4j2PluginsFileTransformer to the task {}", shadow.name)
            }
        }
    }
}
