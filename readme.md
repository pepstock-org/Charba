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

  * **import last CHART.JS version,** [2.9.3](https://github.com/chartjs/Chart.js/releases/tag/v2.9.3) 
  * Every dataset sets automatically own type ([chart type](http://www.pepstock.org/Charba/2.6/org/pepstock/charba/client/ChartType.html)). This is the first step going to new [Chart.JS](http://www.chartjs.org/) feature about `dataset default options`, available from [version 2.9.2](https://github.com/chartjs/Chart.js/releases/tag/v2.9.2).
    * add new constructors in order to extend the dataset for new controllers.
    * add new `HorizontalBarDataset` class.
      * this is a **breaking change** because now the `HorizontalChart` class needs that dataset class instead of `BarDataset`.
    * remove `setType` method from `Dataset` class.
 * add `toCSSBackgroundProperty` methods for `Gradient` and `Pattern` into `Utilities` class in order to get the CSS property value of the canvas object.
 * change the `Plugin` interface into to have `afterInit` the native chart instance.
 * change `LegendCallback` interface in order to return a `SafeHtml` object instead of use `SafeHtmlBuilder`.
 * create `HtmlLegend` plugin in order to create HTML legend instead of using the CHART.JS one
    * add some methods to `LegendLabelItem` class in order to manage text as HTML.
    * add new Html legend builder options (globally and per chart instance)
    * add `LegendTextCallback` interface to manage text of legend as HTML

### Fixed Bugs

 * [#43](https://github.com/pepstock-org/Charba/issues/43) change the implementation in order to manage whatever chart types, based on dataset or data index.
 * [#44](https://github.com/pepstock-org/Charba/issues/44) check if the plugin is forcedly disable (by boolean) instead of checking if there is at least an option instance. New `isForcedlyDisabled` method has been added to `Plugins` classes into options and configuration and the check has been changed into `GlobalPlugins` class.   
    
### Development

  * implement `BarStacker` and `HasBarStacker` classes in order to manage `stack` property for bar and horizontal bar charts, reducing duplications of code.
  * add `size` and `isEmpty` methods into `Labels` class.
  * rename `toFont` into `toCSSFontProperty` method into `Utilities` class.
  * the `ChartBackgroundColor` plugin applies the background color (if color or pattern) to the chart GWT widget and not only to canvas.
  * add to `Default` class the `generateLegend` method which will invoke the default HTML legend generator (CHART.JS out of the box implementation) for a chart.
  * create `CallbackFunctionContext` native object to map this of javascript in order to invoke CHART.JS functions.
  * add to `Default` class the `invokeLegendOnClick`, `invokeLegendOnHover` and `invokeLegendOnLeave` methods which enable the invocation of event's callbacks provided out of the box by CHART.JS. These methods can be helpful implementing a custom event handler on legend to delegate CHART.JS to manage the event.
  * add to `Default` class the `invokeChartOnClick` and `invokeChartOnHover` methods which enable the invocation of event's callbacks provided out of the box by CHART.JS. These methods can be helpful implementing a custom event handler on chart to delegate CHART.JS to manage the event.
  * change `LegendLabelsCallback` interface in order to return a list of `LegendLabelItem` instead of an array.
  * change `LegendLabelsCallback` interface adding a parameter with a list of `LegendLabelItem`, as default ones provided by CHART.JS.
  * add to `Default` class the `generateLabels` methods which enable the invocation of the callback provided out of the box by CHART.JS.
  * add to `Charts` object a map to manage `Chart` native objects and methods to retrive it by chart or its id.
  * add by default to all charts, an internal plugin to track `Chart` native object instances into `Charts`.
  * change event for chart and legend adding `Chart` native object instance as context.
  * create a `LegendEventProperty` enumeration with all property names to set legend event callbacks.
  * create a `ChartEventProperty` enumeration with all property names to set chart event callbacks.
  * add `getElement` method to `IsChart` interface in order to get the element of widget.
  * add `isValid`, `checkIfValid` and `isAbstractChart` static methods to `IsChart` interface.
  * change JS helpers static names.
  * add `AbstractCursorPointerOptions` class as options for plugins in order to have a unique options to extend when plugins need to interact with cursor of an element.
  * add `getBaseType` to `IsChart` interface in order to get the base chart type which if is a `ControllerType` is the chart type extension or chart type itself.
  * add checking into `ControllerType` do not override existing chart types by its name.
  * add checking when a plugin has been added:
     * if plugin id is already loaded in the chart, it overrides the current one
     * if plugin id is already loaded in the default global plugins, it will be removed from chart instance 
  * add `charba` prefix to all plugins provided out of the box by **Charba**   

License
-------

 **Charba** is available under the [Apache 2.0 license](https://www.apache.org/licenses/LICENSE-2.0).
