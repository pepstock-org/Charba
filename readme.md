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
    <version>2.6</version>
</dependency>
```

If you are using [Apache Ivy](http://ant.apache.org/ivy/):

```xml
<dependency org="org.pepstock" name="charba" rev="2.6"/>
```

If you are using [Gradle](https://gradle.org/):

```json
compile group: 'org.pepstock', name: 'charba', version: '2.6'
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

API JavaDoc is published [here](http://www.pepstock.org/Charba/2.6/index.html).

Showcase
--------

See [Charba showcase](http://www.pepstock.org/Charba-Showcase/Charba_Showcase.html) to have a look what you can do with it.

See also [Charba showcase source code](https://github.com/pepstock-org/Charba-Showcase) on GitHub as starting point.

The samples are going to reflect what CHART.JS samples are showing [here](http://www.chartjs.org/samples/latest/).

Continuous integration and quality gate
---------------------------------------

**Charba** is continuously built at every commit and merge into `master` by [Travis](https://travis-ci.com/pepstock-org/Charba).

At every build, **Charba** is also checked by [Sonar.io](https://sonarcloud.io/dashboard?id=pepstock-org_Charba) in order to have the pulse of its quality.

In the project, it's also provided the [FindBugs](https://github.com/pepstock-org/Charba/blob/2.6/charba.fbp) project to looking offline for bugs.

Going to new version
-------

Here you can find the list of enhancement and updates available on `master` branch before which will be part of new official release:

### Features

  * Every dataset sets automatically own type ([chart type](http://www.pepstock.org/Charba/2.6/org/pepstock/charba/client/ChartType.html)). This is the first step going to new [Chart.JS](http://www.chartjs.org/) feature about `dataset default options`, available from [version 2.9.2](https://github.com/chartjs/Chart.js/releases/tag/v2.9.2).
    * add new constructors in order to extend the dataset for new controllers.
    * add new `HorizontalBarDataset` class.
      * this is a **breaking change** because now the `HorizontalChart` class needs that dataset class instead of `BarDataset`.
    * remove `setType` method from `Dataset` class.
 * add `toCSSBackgroundProperty` methods for `Gradient` and `Pattern` into `Utilities` class in order to get the CSS property value of the canvas object.

### Fixed Bugs

 * [#43](https://github.com/pepstock-org/Charba/issues/43) change the implementation in order to manage whatever chart types, based on dataset or data index.
    
### Development

  * implement `BarStacker` and `HasBarStacker` classes in order to manage `stack` property for bar and horizontal bar charts, reducing duplications of code.
  * add `size` and `isEmpty` methods into `Labels` class.
  * rename `toFont` into `toCSSFontProperty` method into `Utilities` class.
  * the `ChartBackgroundColor` plugin applies the background color (if color or pattern) to the chart GWT widget and not only to canvas.
  * add to `Default` class the `generateLegend` method which will invoke the default HTML legend generator (CHART.JS out of the box implementation) for a chart.
  * create `CallbackFunctionContext` native object to map this of javascript in order to invoke CHART.JS functions.
  * add to `Default` class the `invokeLegendOnClick`, `invokeLegendOnHover` and `invokeLegendOnLeave` methods which enable the invocation of event's callbacks provided out of the box by CHART.JS. These methods can be helpful implementing a custom event handler on legend to delegate CHART.JS to manage the event.
  * add to `Default` class the `invokeChartOnClick` and `invokeChartOnHover` methods which enable the invocation of event's callbacks provided out of the box by CHART.JS. These methods can be helpful implementing a custom event handler on chart to delegate CHART.JS to manage the event.
  

License
-------

 **Charba** is available under the [Apache 2.0 license](https://www.apache.org/licenses/LICENSE-2.0).
