Charba - J2CL and GWT Charts library based on CHART.JS
======================================================

[![Release](https://img.shields.io/github/release/pepstock-org/Charba.svg)](https://github.com/pepstock-org/Charba/releases/latest) [![MvnRepo](https://maven-badges.herokuapp.com/maven-central/org.pepstock/charba/badge.svg)](https://mvnrepository.com/artifact/org.pepstock/charba) [![License](https://img.shields.io/github/license/pepstock-org/Charba.svg)](https://github.com/pepstock-org/Charba/blob/master/LICENSE-2.0.txt) [![Build Status](https://travis-ci.com/pepstock-org/Charba.svg?branch=master)](https://travis-ci.com/pepstock-org/Charba) [![Sonarcloud Status](https://sonarcloud.io/api/project_badges/measure?project=pepstock-org_Charba&metric=alert_status)](https://sonarcloud.io/dashboard?id=pepstock-org_Charba) [![Awesome](https://awesome.re/badge-flat2.svg)](https://github.com/chartjs/awesome) [![CodedIsArtAndPassion](https://img.shields.io/badge/coding%20is-art%20and%20passion-E760A4.svg)](https://img.shields.io/badge/coding%20is-art%20and%20passion-E760A4.svg)

What's Charba
--------

[GWT Web toolkit](http://www.gwtproject.org/) doesn't have charting library available out of the box.

There are some open source charting libraries for GWT available to be used but with some constraints or unclear items:

 * internet connection needed
 * open source license not completely clear, sometimes with some obligations like to add specific labels
 * old packages not longer maintained

For all these reasons, **Charba** has been developed, leveraging on [Chart.JS](http://www.chartjs.org/) capabilities which are now available to GWT developers.

Not only GWT
------------

Even if **Charba** was born only as GWT chart library, since version **3**, **Charba** has been changed in order to be used not only in GWT but also with other DOM frameworks, base on [J2CL - JavaToClosure](https://github.com/google/j2cl), like [Google Elemental2](https://github.com/google/elemental2) or [Elemento](https://github.com/hal/elemento).

Since version **3**, **Charba** has got an own DOM tree manager which allows to it to be independent from any other DOM tree frameworks (i.e. GWT, Elemental2 or Elemento) but it is providing a set of hooks in order to use it also over those frameworks.

[![CharbaDiagram](https://github.com/pepstock-org/Charba/wiki/images/charbaDiagram.png)](https://github.com/pepstock-org/Charba/wiki/Integration)
    
Building
--------

To build **Charba**, you can check out the project and to run [Ant build.xml](https://github.com/pepstock-org/Charba/blob/3.1/build.xml).

The [Ant build.xml](https://github.com/pepstock-org/Charba/blob/3.1/build.xml) is able to build the 2 artifacts, related to the 2 distributions available.

The first distribution is a **Charba** file without any GWT dependency (but working on GWT anyway), consumable also in other [J2CL - JavaToClosure](https://github.com/google/j2cl) frameworks, like [Google Elemental2](https://github.com/google/elemental2) and [Elemento](https://github.com/hal/elemento).

To build the project, execute `buildBinary` target.

It creates a `charba-[version.release].jar` file into `dist` folder, ready to be included into your project.

The second distribution is a **Charba** file with a hard GWT dependency which contains charts widgets and code splitting capabilities.

To build the project, execute `buildBinaryGwt` target.

It creates a `charba-[version.release]-gwt.jar` file into `dist` folder, ready to be included into your project.

[![Charba](https://github.com/pepstock-org/Charba/wiki/images/charba_jar_trend.png)](https://github.com/pepstock-org/Charba-Showcase/blob/3.1/src/org/pepstock/charba/showcase/client/views/HomeView.java)

Installation
------------

Currently **Charba** is available on [MVN repository](https://mvnrepository.com/artifact/org.pepstock/charba).

It is available also on [GitHub releases](https://github.com/pepstock-org/Charba/releases).

If you are using [Apache Maven](https://maven.apache.org/):

```xml
<dependency>
    <groupId>org.pepstock</groupId>
    <artifactId>charba</artifactId>
    <version>3.1</version>
    <!-- for GWT -->
    <version>3.1-gwt</version>
</dependency>
```

If you are using [Apache Ivy](http://ant.apache.org/ivy/):

```xml
<dependency org="org.pepstock" name="charba" rev="3.1"/>
<!-- for GWT -->
<dependency org="org.pepstock" name="charba" rev="3.1-gwt"/>
```

If you are using [Gradle](https://gradle.org/):

```json
compile group: 'org.pepstock', name: 'charba', version: '3.1'

compile group: 'org.pepstock', name: 'charba', version: '3.1-gwt'
```

To install in your GWT project, both for GWT and for J2CL artifacts, you must the following configuration into your GWT project module configuration:

```xml
...
    <inherits name="org.pepstock.charba.Charba"/>
...
```

**Charba** version 1.x is based on [JSNI](http://www.gwtproject.org/doc/latest/DevGuideCodingBasicsJSNI.html) method to integrate java script objects. 

Since version **2.x**, **Charba** is based on [JSINTEROP](http://www.gwtproject.org/doc/latest/DevGuideCodingBasicsJsInterop.html) method to integrate java script objects.

As the name suggests, JsInterop is a way of interoperating Java with JavaScript. It offers a better way of communication between the two using annotations instead of having to write java script in your classes (using JSNI).

**PAY ATTENTION** that if you are using a **Charba** version between **2.0** and **3.0** (included), GWT compiler (you are using for your project) requires `-generateJsInteropExports` to be passed. 

If you are using [Apache Maven](https://maven.apache.org/):

```xml
<plugin>
  <groupId>org.codehaus.mojo</groupId>
  <artifactId>gwt-maven-plugin</artifactId>
  ...
  <configuration>
    <generateJsInteropExports>true</generateJsInteropExports>
  </configuration>
</plugin>
```

Since **Charba** version **3.1**, this is NOT needed anymore. 

Documentation
-------------

All **Charba** documentation will be maintained into [GitHub wiki](https://github.com/pepstock-org/Charba/wiki) of **Charba** project.

API JavaDoc is published [here](http://www.pepstock.org/Charba/3.1/index.html).

Showcase
--------

See [Charba showcase on GWT](http://www.pepstock.org/Charba-Showcase/Charba_Showcase.html) to have a look what you can do with it.

See also [Charba showcase GWT source code](https://github.com/pepstock-org/Charba-Showcase) on GitHub as starting point, if you are going to use on GWT.

See [Charba showcase built by J2CL](http://www.pepstock.org/Charba-Showcase-J2CL/Charba_Showcase_J2CL.html) and based on [Google Elemental2](https://github.com/google/elemental2), to have a look what you can do with it.

See also [Charba showcase J2CL source code](https://github.com/pepstock-org/Charba-Showcase-J2CL) on GitHub as starting point, if you are going to use on J2CL.

The samples are going to reflect what CHART.JS samples are showing [here](http://www.chartjs.org/samples/latest/).

Continuous integration and quality gate
---------------------------------------

**Charba** is continuously built at every commit and merge into `master` by [Travis](https://travis-ci.com/pepstock-org/Charba).

At every build, **Charba** is also checked by [Sonar.io](https://sonarcloud.io/dashboard?id=pepstock-org_Charba) in order to have the pulse of its quality.

Going to next release
---------------------

Here you can find the list of enhancements and updates available on `master` branch before which will be part of new official release:

### Breaking changes

 * change `FormatterCallback` interface for `DataLabelsPlugin` enabling the formatting different kind of values of datasets which can be doubles, strings (for lining datasets) and arrays of doubles (for floating bars datasets).
 * change `RenderItem` object for `LabelsPlugin` managing different kinds of values of datasets which can be doubles, strings (for lining datasets) and arrays of doubles (for floating bars datasets).

### Features

 * Charba GWT showcase successfully tested on [GWT 2.9.0](http://www.gwtproject.org/release-notes.html#Release_Notes_2_9_0).
 * implement **floating-bars** for BAR datasets. Thanks @rr22x.
 * change `DataPoint` class setting `void setT(Date)` and `Date getT()` to **deprecated**. Use `void setX(Date)` and `Date getXAsDate()` instead.
 * add `setX` and `getXAsString` methods to `DataPoint` class in order to manage X values as strings.
 * import new [MOMENT.js](https://momentjs.com/) library, version [2.25.3](https://github.com/moment/moment/releases/tag/2.25.3).
 * import new [Luxon](https://moment.github.io/luxon/) library, version [1.24.1](https://github.com/moment/luxon/releases/tag/1.24.1).
 * import new [Luxon adapter](https://github.com/chartjs/chartjs-adapter-luxon), at version [0.2.1](https://github.com/chartjs/chartjs-adapter-luxon/releases/tag/v0.2.1).
 * import new [Annotation](https://github.com/chartjs/chartjs-plugin-annotation) plugin library, currently at `master` commit id [53630421b5d2e5d6790de3d32c1fb7d3cdc590e2](https://github.com/chartjs/chartjs-plugin-annotation)
 * add `rotation` property into `LineLabel` class of [Annotation](https://github.com/chartjs/chartjs-plugin-annotation) plugin. 
 
### Fixed Bugs

 * [#55](https://github.com/pepstock-org/Charba/issues/55) implement **floating-bars** for BAR datasets. Thanks @rr22x. 
 
License
-------

 **Charba** is available under the [Apache 2.0 license](https://www.apache.org/licenses/LICENSE-2.0).
