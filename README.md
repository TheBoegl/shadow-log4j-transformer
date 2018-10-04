## Shadow Plugin 4.0.0 and later
**As of shadow plugin version 4.0.0 you don't need this transformer anymore and should use the provided transformer:**
```
shadowjar{
  transform(com.github.jengelman.gradle.plugins.shadow.transformers.Log4j2PluginsCacheFileTransformer)
}
```
# Log4j 2 Transformer implementation for Gradle Shadow Plugin

A [shadow plugin](https://github.com/johnrengelman/shadow) transformer implementation for log4j to concatenate Log4j2Plugins.dat files.

This is a port of the [maven-shaded-log4j-transformer](https://github.com/edwgiz/maven-shaded-log4j-transformer).

## Versioning Scheme
The version numbering scheme is has the following format: `SHADOW_MAJOR`.`MINOR`.`PATCH`. This is no real semantic versioning because the SHADOW_MAJOR version number correspond to the MAJOR shadow plugin version number.


At the time of writing, the latest shadow plugin version was `2.0.2`. Therefore, you should use this library's version starting with `2`.
If you are stuck at version `1.2.4` use the library version starting with `1`.

## Latest Version
[![Download](https://api.bintray.com/packages/theboegl/gradle-plugins/shadow-log4j-transformer/images/download.svg) ](https://bintray.com/theboegl/gradle-plugins/shadow-log4j-transformer/_latestVersion)<br>
The latest library version is `2.2.0`. It requires at least __Java 7__.

You can get version notifications on bintray:<br>[![Get automatic notifications about new "shadow-log4j-transformer" versions](https://www.bintray.com/docs/images/bintray_badge_color.png)](https://bintray.com/theboegl/gradle-plugins/shadow-log4j-transformer?source=watch)

## Adding the transformer implementation to your project

This implementation must work with the __shadow plugin__ and will use __Log4j 2__.

You should use this implementation as library. 
You may use it as gradle plugin though this is not recommended anymore due to issues (e.g. [#2](https://github.com/TheBoegl/shadow-log4j-transformer/issues/2), [#3](https://github.com/TheBoegl/shadow-log4j-transformer/issues/3) and, [#5](https://github.com/TheBoegl/shadow-log4j-transformer/issues/5)).

### Usage as library

The implementation should be used as library to provide the Log4j 2 transformer.
This is as easy as shown below:

```gradle
buildscript {
  dependencies {
    classpath "com.github.jengelman.gradle.plugins:shadow:2.0.2"
    classpath 'de.sebastianboegl.gradle.plugins:shadow-log4j-transformer:2.2.0'
  }
}

apply plugin: 'com.github.johnrengelman.shadow'

shadowJar {
  transform(de.sebastianboegl.gradle.plugins.shadow.transformers.Log4j2PluginsFileTransformer)
}
```

### Not recommended: Using the `apply` method
The plugin applies the latest version of the shadow plugin or the one in the dependency block, hence, you don't have to apply the shadow plugin.

```gradle
buildscript {
  repositories {
    maven {
      url "https://plugins.gradle.org/m2/"
    }
  }
  dependencies {
    classpath "com.github.jengelman.gradle.plugins:shadow:2.0.2"
    classpath "de.sebastianboegl.gradle.plugins:shadow-log4j-transformer:2.2.0"
  }
}

apply plugin: "de.sebastianboegl.shadow.transformer.log4j"
```

### Not recommended: Using the Gradle plugin DSL
The order of the plugins doesn't matter:

```gradle
plugins {
  id "com.github.johnrengelman.shadow" version "2.0.2"
  id "de.sebastianboegl.shadow.transformer.log4j" version "2.2.0"
}
```

## Configuration
There is __no__ configuration needed. 
The plugin adds itself as [Transformer](http://imperceptiblethoughts.com/shadow/api/com/github/jengelman/gradle/plugins/shadow/transformers/Transformer.html) (see the shadow plugin documentation [Section Controlling JAR Content Merging](http://imperceptiblethoughts.com/shadow/#controlling_jar_content_merging)).
