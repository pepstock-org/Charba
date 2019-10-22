Charba - GWT Charts library based on CHART.JS
===============================================

[![Release](https://img.shields.io/github/release/pepstock-org/Charba.svg)](https://github.com/pepstock-org/Charba/releases/latest) [![MvnRepo](https://maven-badges.herokuapp.com/maven-central/org.pepstock/charba/badge.svg)](https://mvnrepository.com/artifact/org.pepstock/charba) [![License](https://img.shields.io/github/license/pepstock-org/Charba.svg)](https://github.com/pepstock-org/Charba/blob/master/LICENSE-2.0.txt) [![Build Status](https://travis-ci.com/pepstock-org/Charba.svg?branch=master)](https://travis-ci.com/pepstock-org/Charba) [![Sonarcloud Status](https://sonarcloud.io/api/project_badges/measure?project=pepstock-org_Charba&metric=alert_status)](https://sonarcloud.io/dashboard?id=pepstock-org_Charba) [![Awesome](https://awesome.re/badge-flat2.svg)](https://github.com/chartjs/awesome)

What's Charba
--------

[Google Web toolkit](http://www.gwtproject.org/) doesn't have charting library available out of the box.

There are some open source charting libraries for GWT available to be used but with some constraints or unclear items:

 * internet connection needed
 * open source license not completely clear, sometimes with some obligations like to add specific labels
 * old packages not longer maintained

For all these reasons, **Charba** has been developed, leveraging on [Chart.JS](http://www.chartjs.org/) capabilities which are now available to GWT developers.
    
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
    <version>2.5</version>
</dependency>
```

If you are using [Apache Ivy](http://ant.apache.org/ivy/):

```xml
<dependency org="org.pepstock" name="charba" rev="2.5"/>
```

If you are using [Gradle](https://gradle.org/):

```json
compile group: 'org.pepstock', name: 'charba', version: '2.5'
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

API JavaDoc is published [here](http://www.pepstock.org/Charba/2.5/index.html).

Showcase
--------

See [Charba showcase](http://www.pepstock.org/Charba-Showcase/Charba_Showcase.html) to have a look what you can do with it.

See also [Charba showcase source code](https://github.com/pepstock-org/Charba-Showcase) on GitHub as starting point.

The samples are going to reflect what CHART.JS samples are showing [here](http://www.chartjs.org/samples/latest/).

Continuous integration and quality gate
---------------------------------------

**Charba** is continuously built at every commit and merge into `master` by [Travis](https://travis-ci.com/pepstock-org/Charba).

At every build, **Charba** is also checked by [Sonar.io](https://sonarcloud.io/dashboard?id=pepstock-org_Charba) in order to have the pulse of its quality.

In the project, it's also provided the [FindBugs](https://github.com/pepstock-org/Charba/blob/2.5/charba.fbp) project to looking offline for bugs.

Going to new version
-------

Here you can find the list of enhancement and updates available on `master` branch before which will be part of new official release:

### Features
 
 * import last CHART.JS [package](https://www.chartjs.org/dist/master/Chart.bundle.min.js) from `development build`. **PAY ATTENTION** that [CHART.JS](https://www.chartjs.org/docs/latest/developers/#development-releases) report the following warning using NOT tagged version: **Development builds MUST not be used for production purposes**.
    * add `align` property to legend configuration object. 
    * add `rotation` property to legend item object.
    * add `spanGaps` property to radar chart dataset and to radar options configuration.
    * line and radar datasets are now scriptable by callbacks for following options:
       * `backgroundColor`
       * `borderCapStyle`
       * `borderColor`
       * `borderDash`
       * `borderDashOffset`
       * `borderJoinStyle`
       * `borderWidth`
       * `cubicInterpolationMode` (only line chart)
       * `fill`
    * remove `min` and `max`properties from `Time` class (both options and configuration) and add them to `CartesianTimeTick` (for configuration) and `Ticks` (for options), as new CHART.JS implemented.
    * implement `scaleService` to get and set scale defaults by scale type.  
    * add `z` property to gridline configuration object.
    * add `z` property to tick configuration object.
    * add `sampleSize` property to cartesian tick configuration object.
    * add `display` property to point labels configuration object.
    * add `angle` property to arc configuration object.
    * add `rtl` and `textDirection` and property to legend configuration object.
    * add `rtl` and `textDirection` and property to tooltips configuration object.
 * import last DATALABELS CHART.JS plugin version, [0.7.0](https://github.com/chartjs/chartjs-plugin-datalabels/releases/tag/v0.7.0)
    * Implement multiple `labels` per data element  
 * add `getPointStyleAsImage` and `isPointStyleAsImage` methods to `LegendLabelItem` class in order to manage images as point styles.
 * add `setPointStyle` methods, setting a image instance, to `LegendLabelItem` class in order to manage images as point styles.
 * add `CategoryTickCallback`and `TimeTickCallback` to manage axes which are managing strings and dates as data types. 
 * add `TimeTickItem` in order to manage the different signature of CHART.JS callback when there is a cartesian time axis.
 * update `AxisBuildTicksCallback` adding the list of created ticks as double into `onAfterBuildTicks` method.
 * add `CategoryAxisBuildTicksCallback`and `TimeAxisBuildTicksCallback` to manage axis build ticks callback which are managing strings and dates as data types.
 * add `minNotZero` property into `ScaleItem`.
 * add methods to get `min` and `max` properties as a date from `ScaleItem`.
 * add methods to get ticks items (not only values) from `ScaleItem`.
 * add methods `updateOptions` to charts in order to update the options, mutating the options property in place, and update the whole chart. This is addressing the issue [23](https://github.com/pepstock-org/Charba/issues/23).

### Fixed Bugs

 * [#36](https://github.com/pepstock-org/Charba/issues/36) removed `setShowLine` method (which it will set always to `false` the options) from `ScatterDataset` in order to be able to set it freely. Thanks @lightingft
 * [#37](https://github.com/pepstock-org/Charba/issues/37) added ANT target `build-all-sources` to create a ZIP file (`charba-[version.release]-sources.zip`) which will be added to GitHub release page as artifact to be consumed, every time new release will be published. The file will contain all sources (java, js) for security scanning purposes. Thanks @jake1164
 * [#40](https://github.com/pepstock-org/Charba/issues/40) added scale configuration to the chart one, only for chart with a single scale.
 * [#42](https://github.com/pepstock-org/Charba/issues/42) added `CategoryTickCallback`and `TimeTickCallback` to manage axes which are managing strings and dates as data types. Thanks @ak80 
 
### Development

 * reduce visibility to `StandardKey` class constructor in order to use `Key.create(String key)` method.
 * set `private` constructor to `JSON` class in order to avoid any instantiation.
 * add `compare` and `equals` methods to `Key` class.
 * move (from `LegenItem` class) and change `getLineWidth`, `getStrokeStyle` and `getPointStyle` methods into `LegendLabelItem` class in order to return a single item instead of a list of them.
 * change default `cubicInterpolationMode` property to return into `LineDataset` class.
 * use ARC element to get default background color, border color and border width properties because used by most cases of datasets (reduce code duplications).
 * change visibility to `Filler` class, making it also extendable, in order to be able to implement `fill` scriptable callback.
 * reset of callback instances when the property has been set with object object type.
 * improve the single scale options management when a `null` is passed as argument to chart options.
 * change type from `int` to `double` for `min` and `max` properties into `ScaleItem`
 * remove the inner options from cache of `AbstractPluginCachedOptions` when chart is destroy
 
License
-------

 **Charba** is available under the [Apache 2.0 license](https://www.apache.org/licenses/LICENSE-2.0).
