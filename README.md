# Log4j 2 Transformer for Gradle Shadow Plugin

A [shadow plugin](https://github.com/johnrengelman/shadow) transformer implementation for log4j to concatenate Log4j2Plugins.dat files.

This is a port of the [maven-shaded-log4j-transformer](https://github.com/edwgiz/maven-shaded-log4j-transformer).

## Versioning Scheme
The version numbering scheme is has the following format: `SHADOW_MAJOR`.`MINOR`.`PATCH`. This is no real semantic versioning because the SHADOW_MAJOR version number correspond to the MAJOR shadow plugin version number.


At the time of writing, the latest shadow plugin version was `1.2.4`. Therefore, you should use this plugin's version starting with `1`.

The latest plugin version is `1.0.0`. It requires at least __Java 7__.

## Adding the plugin to your project

This plugin must work with the __shadow plugin__ and will use __Log4j 2__.

### Using the `apply` method
The plugin applies the latest version of the shadow plugin or the one in the dependency block, hence, you don't have to apply the shadow plugin.

```gradle
buildscript {
  repositories {
    maven {
      url "https://plugins.gradle.org/m2/"
    }
  }
  dependencies {
    classpath "com.github.jengelman.gradle.plugins:shadow:1.2.4"
    classpath "de.sebastianboegl.gradle.plugins:shadow-log4j-transformer:1.0.0"
  }
}

apply plugin: "de.sebastianboegl.shadow.transformer.log4j"
```

### Using the Gradle plugin DSL
The order of the plugins doesn't matter:

```gradle
plugins {
  id "com.github.johnrengelman.shadow" version "1.2.4"
  id "de.sebastianboegl.shadow.transformer.log4j" version "1.0.0"
}
```

## Configuration
There is __no__ configuration needed. 
The plugin adds itself as [Transformer](http://imperceptiblethoughts.com/shadow/api/com/github/jengelman/gradle/plugins/shadow/transformers/Transformer.html) (see the shadow plugin documentation [Section Controlling JAR Content Merging](http://imperceptiblethoughts.com/shadow/#controlling_jar_content_merging)).
