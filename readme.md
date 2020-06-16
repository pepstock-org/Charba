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

To build **Charba**, you can check out the project and to run [Ant build.xml](https://github.com/pepstock-org/Charba/blob/3.2/build.xml).

The [Ant build.xml](https://github.com/pepstock-org/Charba/blob/3.2/build.xml) is able to build the 2 artifacts, related to the 2 distributions available.

The first distribution is a **Charba** file without any GWT dependency (but working on GWT anyway), consumable also in other [J2CL - JavaToClosure](https://github.com/google/j2cl) frameworks, like [Google Elemental2](https://github.com/google/elemental2) and [Elemento](https://github.com/hal/elemento).

To build the project, execute `buildBinary` target.

It creates a `charba-[version.release].jar` file into `dist` folder, ready to be included into your project.

The second distribution is a **Charba** file with a hard GWT dependency which contains charts widgets and code splitting capabilities.

To build the project, execute `buildBinaryGwt` target.

It creates a `charba-[version.release]-gwt.jar` file into `dist` folder, ready to be included into your project.

[![Charba](https://github.com/pepstock-org/Charba/wiki/images/charba_jar_trend.png)](https://github.com/pepstock-org/Charba-Showcase/blob/3.2/src/org/pepstock/charba/showcase/client/views/HomeView.java)

Installation
------------

Currently **Charba** is available on [MVN repository](https://mvnrepository.com/artifact/org.pepstock/charba).

It is available also on [GitHub releases](https://github.com/pepstock-org/Charba/releases).

If you are using [Apache Maven](https://maven.apache.org/):

```xml
<dependency>
    <groupId>org.pepstock</groupId>
    <artifactId>charba</artifactId>
    <version>3.2</version>
    <!-- for GWT -->
    <version>3.2-gwt</version>
</dependency>
```

If you are using [Apache Ivy](http://ant.apache.org/ivy/):

```xml
<dependency org="org.pepstock" name="charba" rev="3.2"/>
<!-- for GWT -->
<dependency org="org.pepstock" name="charba" rev="3.2-gwt"/>
```

If you are using [Gradle](https://gradle.org/):

```json
compile group: 'org.pepstock', name: 'charba', version: '3.2'

compile group: 'org.pepstock', name: 'charba', version: '3.2-gwt'
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

API JavaDoc is published [here](http://www.pepstock.org/Charba/3.2/index.html).

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

 * changes by new **CHART.JS version**:
   * remove `isAnimating` method from `ChartNode` class.
   * remove `getBorderWidth` method from `ChartNode` class.
   * remove `getOuterRadius` method from `ChartNode` class.
   * remove `getInnerRadius` method from `ChartNode` class.
   * remove `getRadiusLength` method from `ChartNode` class.
   * remove `getOffsetX` method from `ChartNode` class.
   * remove `getOffsetY` method from `ChartNode` class.
   * remove `isHover` method from `ScriptableContext` class.
   * remove `getXAxes` and `setXAxes` methods from `Scales` classes, use `getAxes` and `setAxes` methods.
   * remove `getYAxes` and `setYAxes` methods from `Scales` classes, use `getAxes` and `setAxes` methods.
   * rename `steppedLine` from the dataset options to `stepped`.
   * rename `tension` from the dataset option to `lineTension`.
   * rename `defaultColor` from `Defaults` global options to `color`. 
   * change structure of `DatasetMetaItem`.
   * rename `SteppedLine` enumeration to `Stepped`.
   * move `beginAtZero` property from ticks to axis/scale.
   * move `min` property from ticks to axis/scale.
   * move `max` property from ticks to axis/scale.
   * move `reverse` property from ticks to axis/scale.
   * move `suggestedMax` property from ticks to axis/scale.
   * move `suggestedMin` property from ticks to axis/scale.
   * move `min` property from time object to axis/scale.
   * move `max` property from time object to axis/scale.
   * remove `getCartesianType()` method from `CartesianAxis` class, use `getAxis` method.
   * remove `DEFAULT_X_AXIS_ID`, `DEFAULT_Y_AXIS_ID` and `DEFAULT_SINGLE_AXIS_ID` constants from `Scales` class, use `DefaultScaleId` enumeration.
   * add mandatory scale id and type parameters to `Scale` class constructor.
   * move `Weight` enumeration from `org.pepstock.charba.client.datalabels.enums` to `org.pepstock.charba.client.enums` because new `Font` implementation must be used.
   * remove `fontSize`, `fontStyle`, `fontFamily`, `lineHeight` from the following options classes, because new `Font` implementation must be used:
      * `Options`
      * `LegendLabels`
      * `Title`
      * `Tooltips`
      * `PointLabels`
      * `ScaleLabel`
      * `Ticks`
   * remove `fontSize`, `fontStyle`, `fontFamily`, `lineHeight` from the following configuration classes, because new `Font` implementation must be used:
      * `LegendLabels`
      * `Title`
      * `Tooltips`
      * `PointLabels`
      * `CartesianScaleLabel`
      * `Ticks`
   * change `toFont` method to `Utilities` class in order to get the weight of the font.
   * remove `DefaultFontItem` class, use `DefaultFont`. 
   * remove the following classes because the ticks implementation is updated:
       * `TickMinor`, `IsDefaultMinorTick`, `AbstractTick`, `IsDefaultBaseTick`, `DefaultTickItem`, `DefaultMinorTickItem`, `BaseTickMinor` and `BaseTick`
   * remove `getMinor` method from `IsDefaultTicks` interface and then from `DefaultTicks` and `DefaultChartTicks` classes.
   * rename `TickMajor` option class to `Major`.
   * rename `DefaultMajorTickItem` class to `DefaultMajor`.
   * rename `BaseTickMinor` configuration class renamed to `Major`.
   * remove `zeroLineWidth`, `zeroLineColor`, `zeroLineBorderDash` and `zeroLineBorderDashOffset` properties from `GridLines` options and configuration classes.
   * remove `x` and `y` properties from `TooltipItem` class.
   * change `setHoverStyle` and `removeHoverStyle` methods of `Controller` interface now additionally take the `datasetIndex` and `index` as arguments.
 * remove `TimeTickItem` class, use `ScaleTickItem` class.
   * add `getValueAsDate()` method to `ScaleTickItem` class in order to get the tick value as `Date`.
   * rename `getValueAsDouble` method of `ScaleTickItem` class to `getValue`.
   * remove `getTimeTickItems` method from `ScaleItem` class, use `getTickItems`.
   * change `onCallback` method signature of `TimeTickCallback` class in order to get a list of `ScaleTickItem` instead of `TimeTickItem`.  
   * change `onAfterBuildTicks` method signature of `TimeAxisBuildTicksCallback` class in order to get a list of `ScaleTickItem` instead of `TimeTickItem`.
 * change `getScaleID` method into `LineAnnotation` class of Annotation plugin in order to return a `IsScaleId` instance instead of a `String`.  
 * change `getXScaleID` and `getXScaleID` methods into `BoxAnnotation` class of Annotation plugin in order to return a `IsScaleId` instance instead of a `String`.  
 * change `getXAxisID` and `getXAxisID` methods into `LineDataset` class in order to return a `IsScaleId` instance instead of a `String`.
 * change `getXAxisID` and `getXAxisID` methods into `BarDataset` class in order to return a `IsScaleId` instance instead of a `String`.
 * change `getXAxisID` method into `DatasetsItemsSelectorOptions` class in order to return a `IsScaleId` instance instead of a `String`.
 * remove `Event.TOUCHEND` item
 * rename `LegendAlign` class into `ElementAlign` in order to be able to use for `Title` options and configuration classes.
         
### Features

 * **import last CHART.JS version,** [3.0.0.alpha](https://github.com/chartjs/Chart.js/releases/tag/v3.0.0-alpha).
   * add `setDatasetVisibility` method to `AbstractChart` class in order to set the visibility for a given dataset. 
   * add `toggleDataVisibility` method to `AbstractChart` class in order to toggle the visibility of an item in all datasets.
   * add `isDataVisible` method to `AbstractChart` class in order to get the stored visibility state of an data index for all datasets. 
   * add `hide` method to `AbstractChart` class in order to hide a dataset. 
   * add `show` method to `AbstractChart` class in order to show a dataset. 
   * add axis id parameter to cartesian axes classes constructor.
   * add `toFont` method to `Utilities` class in order to get a `Font` object as parameter.
   * add `align` property to `ScaleLabel` options and `CartesianScaleLabel` configuration classes.
   * add `borderColor` and `borderWidth` properties to `GridLines` options and configuration classes.
   * add `LineWidthCallback` interface to use into a `GridLines` options in order to set `lineWidth` property at runtime.
   * add `ColorCallback` interface to use into a `GridLines` options in order to set `color` property at runtime.
   * add `boxHeight` property to `LegendLabels` options and configuration classes.
   * add `title` property to `Legend` options and configuration classes in order to manage a title on legend
   * add `align` property to `Title` options and configuration classes.
   * add `boxWidth` and `boxHeight` properties to `Tooltips` options and configuration classes.
 * add `setScaleID` method to `LineAnnotation` class of Annotation plugin in order to set the scale id using `IsScaleId` implementation.
 * add `setXScaleID` and `setYScaleID` methods to `BoxAnnotation` class of Annotation plugin in order to set the scale id using `IsScaleId` implementation.
 * add `setXAxisID` and `setYAxisID` methods to `LineDataset` class in order to set the scale id using `IsScaleId` implementation.
 * add `setXAxisID` and `setYAxisID` methods to `BarDataset` class in order to set the scale id using `IsScaleId` implementation.
 * add `setXAxisID` methods to `DatasetsItemsSelectorOptions` class in order to set the scale id using `IsScaleId` implementation.
 
### Development

 * change visibility of `CartesianAxis` class, now it is public.
 * change visibility of `AbstractModel` class, now it is public.
 * add `checkAndGetValue` static method to `IsColor` class.
 * add `@Override` annotation to all overriding methods. 
 * override the hashCode `$H` property for `NativeObject` objects that GWT is adding to objects in order to set the property as NOT enumerable and NOT configurable.
 * change `compare` static method of `Key` interface in order to test if the keys are valid and not only not `null`.
 * change the registering of tooltips positioner in order to apply to CHART.JS tooltips plugin. 

License
-------

 **Charba** is available under the [Apache 2.0 license](https://www.apache.org/licenses/LICENSE-2.0).
