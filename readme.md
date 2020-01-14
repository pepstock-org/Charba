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

[![Charba](https://github.com/pepstock-org/Charba/wiki/images/charba_jar_trend.png)](https://github.com/pepstock-org/Charba-Showcase/blob/2.7/src/org/pepstock/charba/showcase/client/views/HomeView.java)
    
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
    <version>2.7</version>
</dependency>
```

If you are using [Apache Ivy](http://ant.apache.org/ivy/):

```xml
<dependency org="org.pepstock" name="charba" rev="2.7"/>
```

If you are using [Gradle](https://gradle.org/):

```json
compile group: 'org.pepstock', name: 'charba', version: '2.7'
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

JsInterop is one of the core features of GWT 2.8. 

As the name suggests, JsInterop is a way of interoperating Java with JavaScript. It offers a better way of communication between the two using annotations instead of having to write java script in your classes (using JSNI).

**Pay attention** that GWT compiler (you are using for your project) requires `-generateJsInteropExports` to be passed.

Documentation
-------------

All **Charba** documentation will be maintained into [GitHub wiki](https://github.com/pepstock-org/Charba/wiki) of **Charba** project.

API JavaDoc is published [here](http://www.pepstock.org/Charba/2.7/index.html).

Showcase
--------

See [Charba showcase](http://www.pepstock.org/Charba-Showcase/Charba_Showcase.html) to have a look what you can do with it.

See also [Charba showcase source code](https://github.com/pepstock-org/Charba-Showcase) on GitHub as starting point.

The samples are going to reflect what CHART.JS samples are showing [here](http://www.chartjs.org/samples/latest/).

Continuous integration and quality gate
---------------------------------------

**Charba** is continuously built at every commit and merge into `master` by [Travis](https://travis-ci.com/pepstock-org/Charba).

At every build, **Charba** is also checked by [Sonar.io](https://sonarcloud.io/dashboard?id=pepstock-org_Charba) in order to have the pulse of its quality.

In the project, it's also provided the [FindBugs](https://github.com/pepstock-org/Charba/blob/2.7/charba.fbp) project to looking offline for bugs.

Going to next release
-------

Here you can find the list of enhancements and updates available on `master` branch before which will be part of new official release:

### Features

 * import last ZOOM CHART.JS plugin version, [0.7.5](https://github.com/chartjs/chartjs-plugin-zoom/releases/tag/v0.7.5).
   * import last HAMMER JS version, [2.0.8](https://github.com/hammerjs/hammer.js/releases/tag/v2.0.8) as dependency of ZOOM CHART.JS plugin.
   * full implementation of configuration for ZOOM plugin.
 * import ANNOTATION CHART.JS plugin, at `master` commit id [7037eea71314613a8af83d2298241841cbc34c4f](https://github.com/chartjs/chartjs-plugin-annotation).
   * full implementation of configuration for ANNOTATION plugin.
 * add `defaultLineHeight` property to options.  
 * move `drawOnAttach` and `destroyOnDetach` chart property into options in order they can be set globally.
 * add the following hover styles (set by callback as well) on lining datasets, from CHART.JS version 2.9.3 (see [PR here](https://github.com/chartjs/Chart.js/pull/6527) and its limitations):
   * `hoverBackgroundColor`
   * `hoverBorderCapStyle`
   * `hoverBorderColor`
   * `hoverBorderDash`
   * `hoverBorderDashOffset`
   * `hoverBorderJoinStyle`
   * `hoverBorderWidth` 
 * add `GoogleChartColor` enumeration with Google Chart colors.
 * add `GoogleChartScheme` enumeration with Google Chart colors scheme to use into `ColorSchemes` plugin.
 * move `DatasetRangeSelectionEvent` and `DatasetRangeSelectionEventHandler` classes into events package (**breaking change**).
 * add `onBeforeConfigure` and `onAfterConfigure` methods to `ChartsLifecycleListener` in order to be able to be notified before and after chart configuration setup. Also invoked when the chart is `reconfigure`.
 * add `IsDefaultsPlugin` interface to map plugins options defaults.
 * change `getOptions` and `setOptions` methods to store and load plugin options into configuration, global chart, global and dataset options in order to use `AbstractPluginOptions` and `AbstractPluginOptionsFactory` classes instead of a simple `NativeObjectContainer` and `NativeObjectContainerFactory` ones (**breaking change**).
   * new `create` method signature to create a plugin options by a `NativeObject` and `IsDefaultsPlugin` object (for default).
 * change `getDefaultChartOptions` method signature into `IsChart` interface in order to get a `IsDefaultScaledOptions` instead of a `ChartOptions` instance (**breaking change**).
 * add `getWholeOptions` method to `IsChart` interface in order to get the whole options configuration of a chart (merged global, global chart and chart options).
 * add constructors to plugin options in order to create the object relating it to a chart instance to get the default values, if needed.
 * add 2 `store` methods to `AbstractPluginOptions` class in order to store the plugin options into a global chart and dataset options.
 * add `setOptions`, `getOptions` and `getOptionsAsList` methods to options, configuration and datasets removing plugin ID as parameter because already set inside the plugin options and factory.
 * add `removeOptions` methods to options, configuration and datasetss in order to remove the plugin options by plugin id.
 * add `borderDashOffset` property to `DatasetsItemsSelector` plugin options.
 * change `AbstractChart` class adding the chart `Type` as argument of constructor(**breaking change**).
   * the `getType` method has been set as `final`.
   * add to all charts implementation new protected constructor in order to extend the existing charts passing the controller type. 

### Fixed Bugs

 * [#29](https://github.com/pepstock-org/Charba/issues/29) add a [section](https://github.com/pepstock-org/Charba/wiki/GettingStarted#polyfilling-your-gwt-project) into [Getting Started](https://github.com/pepstock-org/Charba/wiki/GettingStarted) wiki page how to use polyfill, for IE11 or other aged browsers, without internet connection. Thanks @ssamikk 
 * [#49](https://github.com/pepstock-org/Charba/issues/49) change the implementation in order to maintain correctly the status of chart drawing.
 * [#50](https://github.com/pepstock-org/Charba/issues/50) add checking on selection track in order to be sure that an area has been selected.
 * [#51](https://github.com/pepstock-org/Charba/issues/51) change the implementation about plugin options and how to manage defaults values.

### Development

 * add `empty` protected method to `NativeObjectContainer` in order to know if there is any property into native object and improving performances.
 * add `isValid`, `isConsistent` and `checkIfValid` static methods to `IsColor` interface in order to check the consistency of interface when passed as argument, reducing null pointer exception
 * move `alpha`, `toRGBA`, `toRGB`, `toHex`, `toHSLA`, `toHSL`, `toRGBs`, `brighter`, `brighter`, `darker` and `darker` methods as default methods into `IsColor` interface in order that whoever will implement the interface could provide only red, green, blue and alpha values.
 * move some methods of common `JsHelper` into a dedicated and protected helper in order to hide those methods.
 * add compile dependency with last version [2.0.0](https://github.com/google/jsinterop-annotations/releases/tag/2.0.0) of [JSINTEROP](https://github.com/google/jsinterop-annotations) annotations.
 * add compile dependency with last version v20200101 of [Google Closure Compiler](https://developers.google.com/closure/compiler/). 
 * add 2 `load` methods to `Merger` in order to load default and whole options chart instance, removing the previous `get`.
 	* reduce the accessibility to methods by an `OptionsEnvelop` object
 * add `getAllIds`	method to plugin options and configuration classes in order to get the list of configiured plugin ids.
 * add checking to `setOptions`, `getOptions` and `getOptionsAsList` methods to options, configuration and datasets in order to assure that the plugin id passed as argument is the same of plugin options.
 
License
-------

 **Charba** is available under the [Apache 2.0 license](https://www.apache.org/licenses/LICENSE-2.0).
