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

package de.sebastianboegl.gradle.plugins.shadow.transformers

import org.apache.logging.log4j.core.config.plugins.processor.PluginProcessor
import org.gradle.api.file.FileTreeElement
import org.gradle.api.file.RelativePath
import org.gradle.api.internal.file.DefaultFileTreeElement
import spock.lang.Specification
import spock.lang.Unroll

@Unroll
class Log4j2PluginsFileTransformerSpec extends Specification {

    def "path #path #transform transformed"() {
        given:
        def transformer = new Log4j2PluginsFileTransformer()

        when:
        def actual = transformer.canTransformResource(getFileElement(path))

        then:
        actual == expected

        where:
        path                                            | expected
        PluginProcessor.PLUGIN_CACHE_FILE               | true
        'META-INF/org/apache/logging/Log4j2Plugins.dat' | false
        'META-INF/services/Log4j2Plugins.dat'           | false
        'META-INF/Log4j2Plugins.dat'                    | false
        'Log4j2Plugins.dat'                             | false

        transform = expected ? 'can be' : 'can not be'
    }

    protected static FileTreeElement getFileElement(String path) {
        return new DefaultFileTreeElement(null, RelativePath.parse(true, path), null, null)
    }

}
