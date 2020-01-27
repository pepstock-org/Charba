Charba - GWT Charts library based on CHART.JS
===============================================

[![Release](https://img.shields.io/github/release/pepstock-org/Charba.svg)](https://github.com/pepstock-org/Charba/releases/latest) [![MvnRepo](https://maven-badges.herokuapp.com/maven-central/org.pepstock/charba/badge.svg)](https://mvnrepository.com/artifact/org.pepstock/charba) [![License](https://img.shields.io/github/license/pepstock-org/Charba.svg)](https://github.com/pepstock-org/Charba/blob/master/LICENSE-2.0.txt) [![Build Status](https://travis-ci.com/pepstock-org/Charba.svg?branch=master)](https://travis-ci.com/pepstock-org/Charba) [![Sonarcloud Status](https://sonarcloud.io/api/project_badges/measure?project=pepstock-org_Charba&metric=alert_status)](https://sonarcloud.io/dashboard?id=pepstock-org_Charba) [![Awesome](https://awesome.re/badge-flat2.svg)](https://github.com/chartjs/awesome) [![CodedIsArtAndPassion](https://img.shields.io/badge/coding%20is-art%20and%20passion-E760A4.svg)](https://img.shields.io/badge/coding%20is-art%20and%20passion-E760A4.svg)

What's Charba
--------

[Google Web toolkit](http://www.gwtproject.org/) doesn't have charting library available out of the box.

There are some open source charting libraries for GWT available to be used but with some constraints or unclear items:

 * internet connection needed
 * open source license not completely clear, sometimes with some obligations like to add specific labels
 * old packages not longer maintained

For all these reasons, **Charba** has been developed, leveraging on [Chart.JS](http://www.chartjs.org/) capabilities which are now available to GWT developers.

[![Charba](https://github.com/pepstock-org/Charba/wiki/images/charba_jar_trend.png)](https://github.com/pepstock-org/Charba-Showcase/blob/2.8/src/org/pepstock/charba/showcase/client/views/HomeView.java)
    
Building
--------

To build **Charba**, you can check out the project and to run [Ant build.xml](https://github.com/pepstock-org/Charba/blob/master/build.xml).

To build the project, execute `build-bin` target.

It creates a `charba-[version.release].jar` file into `dist` folder, ready to be included into your project.

Installation
------------

Currently **Charba** is available on [MVN repository](https://mvnrepository.com/artifact/org.pepstock/charba).

It is available also on [GitHub releases](https://github.com/pepstock-org/Charba/releases).

If you are using [Apache Maven](https://maven.apache.org/):

```xml
<dependency>
    <groupId>org.pepstock</groupId>
    <artifactId>charba</artifactId>
    <version>2.8</version>
</dependency>
```

If you are using [Apache Ivy](http://ant.apache.org/ivy/):

```xml
<dependency org="org.pepstock" name="charba" rev="2.8"/>
```

If you are using [Gradle](https://gradle.org/):

```json
compile group: 'org.pepstock', name: 'charba', version: '2.8'
```

To install in your GWT project, you must the following configuration into your GWT project module configuration:

```xml
...
    <inherits name="org.pepstock.charba.Charba"/>
...
```

**Charba** version 1.x is based on [JSNI](http://www.gwtproject.org/doc/latest/DevGuideCodingBasicsJSNI.html) method to integrate java script objects. 

**Charba** version 2.x is based on [JSINTEROP](http://www.gwtproject.org/doc/latest/DevGuideCodingBasicsJsInterop.html) method to integrate java script objects.

JSNI will be removed with GWT 3.

JsInterop is one of the core features of GWT 2.8.x. 

As the name suggests, JsInterop is a way of interoperating Java with JavaScript. It offers a better way of communication between the two using annotations instead of having to write java script in your classes (using JSNI).

**Pay attention** that GWT compiler (you are using for your project) requires `-generateJsInteropExports` to be passed.

Documentation
-------------

All **Charba** documentation will be maintained into [GitHub wiki](https://github.com/pepstock-org/Charba/wiki) of **Charba** project.

API JavaDoc is published [here](http://www.pepstock.org/Charba/2.8/index.html).

Showcase
--------

See [Charba showcase](http://www.pepstock.org/Charba-Showcase/Charba_Showcase.html) to have a look what you can do with it.

See also [Charba showcase source code](https://github.com/pepstock-org/Charba-Showcase) on GitHub as starting point.

The samples are going to reflect what CHART.JS samples are showing [here](http://www.chartjs.org/samples/latest/).

Continuous integration and quality gate
---------------------------------------

**Charba** is continuously built at every commit and merge into `master` by [Travis](https://travis-ci.com/pepstock-org/Charba).

At every build, **Charba** is also checked by [Sonar.io](https://sonarcloud.io/dashboard?id=pepstock-org_Charba) in order to have the pulse of its quality.

In the project, it's also provided the [FindBugs](https://github.com/pepstock-org/Charba/blob/2.8/charba.fbp) project to looking offline for bugs.

Going to next release
-------

Here you can find the list of enhancements and updates available on `master` branch before which will be part of new official release:

### Features

 * add `adapters.date` property into scale options and configuration in order to configure date time adapters.
 * change the injection of CHART.JS, using the `Chart.min.js`
   * the `Chart.bundle.min.js`, used till version 2.8, won't be provided anymore from **CHART.JS 3.x**.
   * due to the previous item, the java script date time library `Moment` won't be provided by CHART.JS
 * enable the options to decide which java script date time library (available for CHART.JS out of the box) to use:
   * the injection of java script date time library (and its CHART.JS adapter) will be provided out of the box by **Charba**. The different library can be chosen using a different resource client bundle, available both embedded and deferred mode. The current resource client bundle injects [MOMENT.js](https://momentjs.com/). 
   * CHART.JS has got the adapters for:
     * [MOMENT.js](https://momentjs.com/) which remains the default for **Charba**, enabled by `EmbeddedResources` or `DeferredResources` classes.
     * [Luxon](https://moment.github.io/luxon/) which can be enabled by `LuxonEmbeddedResources` or `LuxonDeferredResources` classes. 
     * [Date-fns](https://date-fns.org/) which can be enabled by `DatefnsEmbeddedResources` or `DatefnsDeferredResources` classes. 
   * change the `EntryPointStarter` (for deferred injection) in order to accept ONLY deferred resource client bundle.  
   * provide some classes in order to use the date time adapter in GWT project as well, to manage, parse and format dates using the same object of CHART.JS.
   
### Development

 * copy the `ZoomPugin.ID` into a private constant into `DatasetsItemsSelector` plugin in order to avoid to add the `Zoom` java script plugin if not used.
 * remove `Extensions` client bundle moving the text resources of extensions java scripts into the classes which are enabled the capabilities in order to avoid to add the java script sources if not used.
 * change `Date` storing into native object from `JsDate` to `double`.
 * change name to `OptionsEnvelop` class into `Envelop` because it can contain a generic object.
 * change from BODY to HEAD element where the resources will be injected.
 * change unique ID of injected resources using the package name instead of the resource class name to avoid to inject the same resource twice, invoking the `Injector` programmatically.
 * normalize the override method name of a `NativeObjectConatiner` implementation.
 * improve the `DefaultPlugin` implementation in order to use the factory and returns an empty options instead of `null`.
 * change `TimeUnit` and `DefaultFormats` to be aligned with new date time library adoption.
 * move resources (not related to CHART.JS and date time library injection) into the objects which needs (`Charba-Helper` and images for `DatasetsItemsSelector`), improving the the initial loading and GWT project build if not used.
 * change format from `JsDate` to `double` to store `Date` into configuration and options element.
 * change and reduce visibility of `ClientBundle` interfaces in order to avoid any extension on predefined ones.
 * add checking on arguments in `JSON` class in order to avoid any `NullPointerExcpetion`.
 
License
-------

 **Charba** is available under the [Apache 2.0 license](https://www.apache.org/licenses/LICENSE-2.0).
