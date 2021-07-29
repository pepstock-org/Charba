Charba - J2CL and GWT Charts library based on CHART.JS
======================================================

<p align="center">
  <a href="https://github.com/pepstock-org/Charba/releases/latest"><img alt="Last release" src="https://img.shields.io/github/release/pepstock-org/Charba.svg"></a>
  <a href="https://mvnrepository.com/artifact/org.pepstock/charba"><img alt="Mvn repo release" src="https://maven-badges.herokuapp.com/maven-central/org.pepstock/charba/badge.svg"></a>
  <a href="https://github.com/pepstock-org/Charba/blob/master/LICENSE"><img alt="License" src="https://img.shields.io/github/license/pepstock-org/Charba.svg"></a>
  <a href="https://github.com/pepstock-org/Charba/actions/workflows/build.yaml"><img alt="Build" src="https://github.com/pepstock-org/Charba/workflows/Build/badge.svg?branch=master"></a>
  <a href="https://sonarcloud.io/dashboard?id=pepstock-org_Charba"><img alt="Sonar cloud status" src="https://sonarcloud.io/api/project_badges/measure?project=pepstock-org_Charba&metric=alert_status"></a>
  <a href="https://github.com/chartjs/awesome"><img alt="Chart.js awesome" src="https://awesome.re/badge-flat2.svg"></a>
  <a href="https://pepstock-org.github.io/Charba/current/"><img alt="Last javadoc version" src="https://img.shields.io/badge/Javadoc-Last%20version-F27173.svg"></a>
  <a href="https://pepstock-org.github.io/Charba/next/"><img alt="Next javadoc version" src="https://img.shields.io/badge/Javadoc-Next%20version-F27173.svg"></a>
</p>

Gallery
--------

<table>
  <tr>
    <td align="center">Bar</td>
	<td align="center">Horizontal bar</td>
	<td align="center">Line</td>
	<td align="center">Vertical line</td>
  </tr>
  <tr>
    <td><img width="200px" src="https://raw.githubusercontent.com/pepstock-org/Charba-Wiki/master/static/img/galleryBar.png"></td>
    <td><img width="200px" src="https://raw.githubusercontent.com/pepstock-org/Charba-Wiki/master/static/img/galleryHorizontalBar.png"></td>
	<td><img width="200px" src="https://raw.githubusercontent.com/pepstock-org/Charba-Wiki/master/static/img/galleryLine.png"></td>
    <td><img width="200px" src="https://raw.githubusercontent.com/pepstock-org/Charba-Wiki/master/static/img/galleryVerticalLine.png"></td>
  </tr>
  <tr>
    <td align="center">Scatter</td>
	<td align="center">Doughnut</td>
	<td align="center">Pie</td>
	<td align="center">Polar area</td>
  </tr>
  <tr>
    <td><img width="200px" src="https://raw.githubusercontent.com/pepstock-org/Charba-Wiki/master/static/img/galleryScatter.png"></td>
    <td><img width="200px" src="https://raw.githubusercontent.com/pepstock-org/Charba-Wiki/master/static/img/galleryDoughnut.png"></td>
	<td><img width="200px" src="https://raw.githubusercontent.com/pepstock-org/Charba-Wiki/master/static/img/galleryPie.png"></td>
    <td><img width="200px" src="https://raw.githubusercontent.com/pepstock-org/Charba-Wiki/master/static/img/galleryPolarArea.png"></td>
  </tr>
  <tr>
    <td align="center">Radar</td>
	<td align="center">Bubble</td>
	<td align="center">Time series</td>
	<td align="center">Stacked</td>
  </tr>
  <tr>
    <td><img width="200px" src="https://raw.githubusercontent.com/pepstock-org/Charba-Wiki/master/static/img/galleryRadar.png"></td>
    <td><img width="200px" src="https://raw.githubusercontent.com/pepstock-org/Charba-Wiki/master/static/img/galleryBubble.png"></td>
	<td><img width="200px" src="https://raw.githubusercontent.com/pepstock-org/Charba-Wiki/master/static/img/galleryTimeseries.png"></td>
    <td><img width="200px" src="https://raw.githubusercontent.com/pepstock-org/Charba-Wiki/master/static/img/galleryStacked.png"></td>
  </tr>  
  <tr>
    <td align="center">Meter</td>
	<td align="center">Gauge</td>
	<td align="center">Choropleth</td>
	<td align="center">Bubble map</td>
  </tr>
  <tr>
    <td><img width="200px" src="https://raw.githubusercontent.com/pepstock-org/Charba-Wiki/master/static/img/galleryMeter.png"></td>
    <td><img width="200px" src="https://raw.githubusercontent.com/pepstock-org/Charba-Wiki/master/static/img/galleryGauge.png"></td>
	<td><img width="200px" src="https://raw.githubusercontent.com/pepstock-org/Charba-Wiki/master/static/img/galleryChoropleth.png"></td>
    <td><img width="200px" src="https://raw.githubusercontent.com/pepstock-org/Charba-Wiki/master/static/img/galleryBubbleMap.png"></td>
  </tr>  
</table>

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

Even if **Charba** was born only as GWT chart library, as of version 3, **Charba** has been changed in order to be used not only in GWT but also with other DOM frameworks, based on [J2CL - JavaToClosure](https://github.com/google/j2cl), like [Google Elemental2](https://github.com/google/elemental2) or [Elemento](https://github.com/hal/elemento).

**Charba** has got an own DOM manager which allows to it to be independent from any other DOM frameworks (i.e. GWT, Elemental2 or Elemento) but it is providing a set of hooks in order to use it also over those frameworks.

[![CharbaDiagram](https://github.com/pepstock-org/Charba-Wiki/blob/master/static/img/charbaDiagram.png)](https://pepstock-org.github.io/Charba-Wiki/docs/getting-started/Integration)
    
Building
--------

To build **Charba**, you can check out the project and to run [Ant build.xml](https://github.com/pepstock-org/Charba/blob/4.0/build.xml).

The [Ant build.xml](https://github.com/pepstock-org/Charba/blob/4.0/build.xml) is able to build the 2 artifacts, related to the 2 distributions available.

The first distribution is a **Charba** file without any GWT dependency (but working on GWT anyway), consumable also in other [J2CL - JavaToClosure](https://github.com/google/j2cl) frameworks, like [Google Elemental2](https://github.com/google/elemental2) and [Elemento](https://github.com/hal/elemento).

To build the project, execute `buildBinary` target.

It creates a `charba-[version.release].jar` file in `dist` folder, ready to be included in your project.

The second distribution is a **Charba** file with a hard GWT dependency which contains charts widgets and code splitting capabilities.

To build the project, execute `buildBinaryGwt` target.

It creates a `charba-[version.release]-gwt.jar` file in `dist` folder, ready to be included in your project.

[![Charba](https://github.com/pepstock-org/Charba-Wiki/blob/master/static/img/charba_jar_trend_41.png)](https://github.com/pepstock-org/Charba-Showcase/blob/4.0/src/org/pepstock/charba/showcase/client/views/HomeView.java)

Installation
------------

Currently **Charba** is available on [MVN repository](https://mvnrepository.com/artifact/org.pepstock/charba).

It is available also on [GitHub releases](https://github.com/pepstock-org/Charba/releases).

If you are using [Apache Maven](https://maven.apache.org/):

```xml
<dependency>
    <groupId>org.pepstock</groupId>
    <artifactId>charba</artifactId>
    <version>4.0</version>
    <!-- for GWT -->
    <version>4.0-gwt</version>
</dependency>
```

If you are using [Apache Ivy](http://ant.apache.org/ivy/):

```xml
<dependency org="org.pepstock" name="charba" rev="4.0"/>
<!-- for GWT -->
<dependency org="org.pepstock" name="charba" rev="4.0-gwt"/>
```

If you are using [Gradle](https://gradle.org/):

```json
compile group: 'org.pepstock', name: 'charba', version: '4.0'

compile group: 'org.pepstock', name: 'charba', version: '4.0-gwt'
```

To install in your GWT project, both for GWT and for J2CL artifacts, you must the following configuration in your GWT project module configuration:

```xml
...
    <inherits name="org.pepstock.charba.Charba"/>
...
```

**Charba** is based on [JSINTEROP](http://www.gwtproject.org/doc/latest/DevGuideCodingBasicsJsInterop.html) method to integrate java script objects.

As the name suggests, JsInterop is a way of interoperating Java with JavaScript. It offers a better way of communication between the two using annotations instead of having to write java script in your classes (using JSNI).


Documentation
-------------

**Charba** documentation is published [here](https://pepstock-org.github.io/Charba-Wiki).

All **Charba** documentation will be maintained in [Charba-Wiki](https://github.com/pepstock-org/Charba-Wiki) project.

API JavaDoc for version **4.0** is published [here](https://pepstock-org.github.io/Charba/4.0/index.html).

You can also access the previous API JavaDoc, because every version is published to `https://pepstock-org.github.io/Charba/[version.release]`.

The API JavaDoc of the `master` branch is published [here](https://pepstock-org.github.io/Charba/next/).

Showcase
--------

See [Charba showcase on GWT](https://pepstock-org.github.io/Charba-Showcase) to have a look what you can do with it.

See also [Charba showcase GWT source code](https://github.com/pepstock-org/Charba-Showcase) on GitHub as starting point, if you are going to use on GWT.

See [Charba showcase built by J2CL](https://pepstock-org.github.io/Charba-Showcase-J2CL) and based on [Google Elemental2](https://github.com/google/elemental2), to have a look what you can do with it.

See also [Charba showcase J2CL source code](https://github.com/pepstock-org/Charba-Showcase-J2CL) on GitHub as starting point, if you are going to use on J2CL.

The samples are going to reflect what CHART.JS samples are showing [here](http://www.chartjs.org/samples/latest/).

Continuous integration and quality gate
---------------------------------------

**Charba** is continuously built at every commit and merge in `master` by [GitHub Action](https://github.com/pepstock-org/Charba/actions?query=workflow%3ABuild).

At every build, **Charba** is also checked by [Sonar.io](https://sonarcloud.io/dashboard?id=pepstock-org_Charba) in order to have the pulse of its quality.

In the project, it's also provided the [FindBugs](http://findbugs.sourceforge.net/) project to look for bugs.

Going to next release
---------------------

Here you can find the list of enhancements and updates available on `master` branch before which will be part of new official release:

### Breaking changes
  * rename `AxisType` to `ChartAxisType` in order to enable custom axes type.
  * rename `AxisCalculateTickRotationCallback` class to `AxisCalculateLabelRotationCallback`.
    * rename `onBeforeAxisCalculateTickRotation` method of the interface to `onBeforeAxisCalculateLabelRotation`.
    * rename `onAfterAxisCalculateTickRotation` method of the interface to `onAfterAxisCalculateLabelRotation`.
    * rename `setAxisCalculateTickRotation` method of the Axis class to `setAxisCalculateLabelRotation`.
    * rename `getAxisCalculateTickRotation` method of the Axis class to `getAxisCalculateLabelRotation`.

### Features

  * import CHART.JS [version 3.5.0](https://github.com/chartjs/Chart.js/releases/tag/v3.5.0).
  * import CHART.JS DATALABELS plugin [version 2.0.0](https://github.com/chartjs/chartjs-plugin-datalabels/releases/tag/v2.0.0).
  * import CHART.JS ZOOM plugin [version 1.1.1](https://github.com/chartjs/chartjs-plugin-zoom/releases/tag/v1.1.1).
  * import Luxon library [version 2.0.1](https://github.com/moment/luxon/releases/tag/2.0.1).
  * import CHART.JS GEO controller [version v3.1.0](https://github.com/sgratzl/chartjs-chart-geo/releases/tag/v3.1.0)
    * enable geographic map chart types such as choropleth and bubble map.
  * add `Subtitle` options and configuration items.  
  * add `hover`, `enter` and `leave` axes events.
  * add `enter` and `leave` title events.
  * add `backgroundColor`, `borderColor` and `borderWidth` scriptable options to all `Elements` configuration (bar, line, point, arc).
  * add `hoverBackgroundColor`, `hoverBorderColor` and `hoverBorderWidth` scriptable options to all `Elements` configuration (bar, line, point, arc).
  * add `borderAlign`, `borderRadius`, `angle`, `offset` and `hoverOffset` scriptable options to `Arc` configuration.
  * add `pointStyle`, `borderSkipped`, `borderRadius`, `hoverBorderRadius` and `enableBorderRadius` scriptable options to `Bar` configuration.
  * add `borderCapStyle`, `borderDash`, `borderDashOffset`, `borderJoinStyle`, `fill` and `cubicInterpolationMode` scriptable options to `Line` configuration.
  * add `radius`, `hitRadius`, `hoverRadius`, `pointStyle` and `rotation` scriptable options to `Point` configuration.
  * add `parsing` and `normalized` options to `Dataset` configuration.
  * enable the feature to create custom axes types (for injected controller).
  * enable `enableBorderRadius` and `hoverBorderRadius` scriptable options on `BarDataset` class.
  * enable `stepped` scriptable option on `Line` element and `LineDataset` classes.
  * add `spacing` property to `Arc` element and `PieDataset` and `DoughnutDataset` classes.
  * add `originalMin` and `originalMax` properties to `ScaleLimit` object in the `ZoomPlugin`.
  * add `getZoomLevel` method to the `ZoomPlugin` class.
  * add `threshold` and `modifierKey` properties to `Drag` configuration in the `ZoomPlugin`.
  * add new events items to `Event` enumeration.
  * add `globalCompositeOperation` property to `Context2dItem` and `ChartBackgroundColorOptions` plugin.
  * add options builder for `ChartBackgroundColor` plugin.
  * add options builder for `ChartPointer` plugin.
  * add options builder for `HtmlLegend` plugin.
  * add `Canvas` object as possible value for `pointStyle` option to enable custom point styles.
  * add `toCanvas` method to `CastHelper` class in order to enable casting `HTMLCanvasElement` instance to `Canvas` object.
  * add `numberFormat` scriptable options to all numeric axes configuration (cartesian linear, cartesian logarithmic, radial linear).
  * add `threshold` option to `Decimation` configuration.
  * add `stack` and `stackWeight` options to `CartesianAxis` configuration.
  * add `pointLabel` item to `ContextType` enumeration class.
  * add `middle` item to `BorderSkipped` enumeration class.
  * add `datasetIndex`, `startDataIndex` and `endDataIndex` properties to segment context for Segment callbacks.
  * add `hide` and `show` methods to chart classes in order to hide and/or show specific data element. 
    
### Developing

  * change dependency for Google Closure Compiler, [version v20210601](https://mvnrepository.com/artifact/com.google.javascript/closure-compiler/v20210601).
  * add `parse` method to `JSON` utility class.
  * add `clipArea` and `unclipArea` methods to `Helpers` utility class.
  * hide java script property visibility of `$H` hash code property in java script object mapped by a `NativeObjectContainer`, when new object has been created. 
    
License
-------

 **Charba** is available under the [Apache 2.0 license](https://www.apache.org/licenses/LICENSE-2.0).
