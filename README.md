Charba - J2CL and GWT Charts library based on CHART.JS
======================================================

<p align="center">
  <a href="https://github.com/pepstock-org/Charba/releases/latest"><img alt="Last release" src="https://img.shields.io/github/release/pepstock-org/Charba.svg"></a>
  <a href="https://mvnrepository.com/artifact/org.pepstock/charba"><img alt="Mvn repo release" src="https://img.shields.io/maven-central/v/org.pepstock/charba"></a>
  <a href="https://github.com/pepstock-org/Charba/blob/master/LICENSE"><img alt="License" src="https://img.shields.io/github/license/pepstock-org/Charba.svg"></a>
  <a href="https://github.com/pepstock-org/Charba/actions/workflows/build.yaml"><img alt="Build" src="https://github.com/pepstock-org/Charba/workflows/Build/badge.svg?branch=master"></a>
  <a href="https://sonarcloud.io/dashboard?id=pepstock-org_Charba"><img alt="Sonar cloud status" src="https://sonarcloud.io/api/project_badges/measure?project=pepstock-org_Charba&metric=alert_status"></a>
  <a href="https://sonarcloud.io/component_measures?id=pepstock-org_Charba&metric=ncloc"><img alt="Lines of Code" src="https://sonarcloud.io/api/project_badges/measure?project=pepstock-org_Charba&metric=ncloc"></a>
  <a href="https://github.com/chartjs/awesome"><img alt="Chart.js awesome" src="https://awesome.re/badge-flat2.svg"></a>
  <a href="https://pepstock-org.github.io/Charba/current/"><img alt="Last javadoc version" src="https://img.shields.io/badge/Javadoc-Last%20version-F27173.svg"></a>
  <a href="https://pepstock-org.github.io/Charba/next/"><img alt="Next javadoc version" src="https://img.shields.io/badge/Javadoc-Next%20version-F27173.svg"></a>
  <a href="https://pepstock-org.github.io/Charba-Wiki"><img alt="Documentation" src="https://img.shields.io/badge/static-Documentation-F27173.svg"></a>
</p>

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

To build **Charba**, you can check out the project and to run [Ant build.xml](https://github.com/pepstock-org/Charba/blob/6.1/build.xml).

The [Ant build.xml](https://github.com/pepstock-org/Charba/blob/6.1/build.xml) is able to build the 2 artifacts, related to the 2 distributions available.

The first distribution is a **Charba** file without any GWT dependency (but working on GWT anyway), consumable also in other [J2CL - JavaToClosure](https://github.com/google/j2cl) frameworks, like [Google Elemental2](https://github.com/google/elemental2) and [Elemento](https://github.com/hal/elemento).

To build the project, execute `buildBinary` target.

It creates a `charba-[version.release].jar` file in `dist` folder, ready to be included in your project.

The second distribution is a **Charba** file with a hard GWT dependency which contains charts widgets and code splitting capabilities.

To build the project, execute `buildBinaryGwt` target.

It creates a `charba-[version.release]-gwt.jar` file in `dist` folder, ready to be included in your project.

[![Charba](https://github.com/pepstock-org/Charba-Wiki/blob/master/static/img/charba_jar_trend_61.png)](https://github.com/pepstock-org/Charba-Showcase/blob/6.1/src/org/pepstock/charba/showcase/client/views/HomeView.java)

Installation
------------

Currently **Charba** is available on [MVN repository](https://mvnrepository.com/artifact/org.pepstock/charba).

It is available also on [GitHub releases](https://github.com/pepstock-org/Charba/releases).

If you are using [Apache Maven](https://maven.apache.org/):

```xml
<dependency>
    <groupId>org.pepstock</groupId>
    <artifactId>charba</artifactId>
    <version>6.1</version>
    <!-- for GWT -->
    <version>6.1-gwt</version>
</dependency>
```

If you are using [Apache Ivy](http://ant.apache.org/ivy/):

```xml
<dependency org="org.pepstock" name="charba" rev="6.1"/>
<!-- for GWT -->
<dependency org="org.pepstock" name="charba" rev="6.1-gwt"/>
```

To install in your GWT project, both for GWT and for J2CL artifacts, you must the following configuration in your GWT project module configuration:

```xml
...
    <inherits name="org.pepstock.charba.Charba"/>
...
```

**Charba** is based on [JSINTEROP](http://www.gwtproject.org/doc/latest/DevGuideCodingBasicsJsInterop.html) method to integrate java script objects.

As the name suggests, JsInterop is a way of interoperating Java with JavaScript. It offers a better way of communication between the two using annotations instead of having to write java script in your classes (using JSNI).

Gallery
--------

<table>
  <tr>
    <td style="background-color: 'white' !important">
      <a href="https://pepstock-org.github.io/Charba-Showcase/index.html?gallery=bar">
        <img width="200px" src="https://raw.githubusercontent.com/pepstock-org/Charba-Wiki/master/static/img/galleryBar.png"/>
      </a>
    </td>
    <td style="background-color: white">
      <a href="https://pepstock-org.github.io/Charba-Showcase/index.html?gallery=horizontalbar">
        <img width="200px" src="https://raw.githubusercontent.com/pepstock-org/Charba-Wiki/master/static/img/galleryHorizontalBar.png"/>
      </a>
    </td>
    <td style="background-color: white">
      <a href="https://pepstock-org.github.io/Charba-Showcase/index.html?gallery=line">
        <img width="200px" src="https://raw.githubusercontent.com/pepstock-org/Charba-Wiki/master/static/img/galleryLine.png"/>
      </a>
    </td>
    <td style="background-color: white">
      <a href="https://pepstock-org.github.io/Charba-Showcase/index.html?gallery=verticalline">
        <img width="200px" src="https://raw.githubusercontent.com/pepstock-org/Charba-Wiki/master/static/img/galleryVerticalLine.png"/>
      </a>
    </td>
  </tr>
  <tr>
    <td align="center">Bar</td>
	<td align="center">Horizontal bar</td>
	<td align="center">Line</td>
	<td align="center">Vertical line</td>
  </tr>
  <tr>
    <td>
      <a href="https://pepstock-org.github.io/Charba-Showcase/index.html?gallery=scatter">
        <img width="200px" src="https://raw.githubusercontent.com/pepstock-org/Charba-Wiki/master/static/img/galleryScatter.png"/>
      </a>
    </td>  
    <td>
      <a href="https://pepstock-org.github.io/Charba-Showcase/index.html?gallery=doughnut">
        <img width="200px" src="https://raw.githubusercontent.com/pepstock-org/Charba-Wiki/master/static/img/galleryDoughnut.png"/>
      </a>
    </td>  
    <td>
      <a href="https://pepstock-org.github.io/Charba-Showcase/index.html?gallery=pie">
        <img width="200px" src="https://raw.githubusercontent.com/pepstock-org/Charba-Wiki/master/static/img/galleryPie.png"/>
      </a>
    </td>  
    <td>
      <a href="https://pepstock-org.github.io/Charba-Showcase/index.html?gallery=polararea">
        <img width="200px" src="https://raw.githubusercontent.com/pepstock-org/Charba-Wiki/master/static/img/galleryPolarArea.png"/>
      </a>
    </td>  
  </tr>
  <tr>
    <td align="center">Scatter</td>
	<td align="center">Doughnut</td>
	<td align="center">Pie</td>
	<td align="center">Polar area</td>
  </tr>
  <tr>
    <td>
      <a href="https://pepstock-org.github.io/Charba-Showcase/index.html?gallery=radar">
        <img width="200px" src="https://raw.githubusercontent.com/pepstock-org/Charba-Wiki/master/static/img/galleryRadar.png"/>
      </a>
    </td>  
    <td>
      <a href="https://pepstock-org.github.io/Charba-Showcase/index.html?gallery=bubble">
        <img width="200px" src="https://raw.githubusercontent.com/pepstock-org/Charba-Wiki/master/static/img/galleryBubble.png"/>
      </a>
    </td>      
    <td>
      <a href="https://pepstock-org.github.io/Charba-Showcase/index.html?gallery=timeseries">
        <img width="200px" src="https://raw.githubusercontent.com/pepstock-org/Charba-Wiki/master/static/img/galleryTimeseries.png"/>
      </a>
    </td>      
    <td>
      <a href="https://pepstock-org.github.io/Charba-Showcase/index.html?gallery=stacked">
        <img width="200px" src="https://raw.githubusercontent.com/pepstock-org/Charba-Wiki/master/static/img/galleryStacked.png"/>
      </a>
    </td>      
  </tr>  
  <tr>
    <td align="center">Radar</td>
	<td align="center">Bubble</td>
	<td align="center">Time series</td>
	<td align="center">Stacked</td>
  </tr>
  <tr>
    <td>
      <a href="https://pepstock-org.github.io/Charba-Showcase/index.html?gallery=meter">
        <img width="200px" src="https://raw.githubusercontent.com/pepstock-org/Charba-Wiki/master/static/img/galleryMeter.png"/>
      </a>
    </td>      
    <td>
      <a href="https://pepstock-org.github.io/Charba-Showcase/index.html?gallery=gauge">
        <img width="200px" src="https://raw.githubusercontent.com/pepstock-org/Charba-Wiki/master/static/img/galleryGauge.png"/>
      </a>
    </td>      
    <td>
      <a href="https://pepstock-org.github.io/Charba-Showcase/index.html?gallery=choropleth">
        <img width="200px" src="https://raw.githubusercontent.com/pepstock-org/Charba-Wiki/master/static/img/galleryChoropleth.png"/>
      </a>
    </td>      
    <td>
      <a href="https://pepstock-org.github.io/Charba-Showcase/index.html?gallery=bubblemap">
        <img width="200px" src="https://raw.githubusercontent.com/pepstock-org/Charba-Wiki/master/static/img/galleryBubbleMap.png"/>
      </a>
    </td>      
  </tr>  
  <tr>
    <td align="center">Meter</td>
	<td align="center">Gauge</td>
	<td align="center">Choropleth</td>
	<td align="center">Bubble map</td>
  </tr>
  <tr>
    <td>
      <a href="https://pepstock-org.github.io/Charba-Showcase/index.html?gallery=area">
        <img width="200px" src="https://raw.githubusercontent.com/pepstock-org/Charba-Wiki/master/static/img/galleryArea.png"/>
      </a>
    </td>      
    <td>
      <a href="https://pepstock-org.github.io/Charba-Showcase/index.html?gallery=treemap">
        <img width="200px" src="https://raw.githubusercontent.com/pepstock-org/Charba-Wiki/master/static/img/galleryTreemap.png"/>
      </a>
    </td>      
    <td>
      <a href="https://pepstock-org.github.io/Charba-Showcase/index.html?gallery=matrix">
        <img width="200px" src="https://raw.githubusercontent.com/pepstock-org/Charba-Wiki/master/static/img/galleryMatrix.png"/>
      </a>
    </td>      
    <td>
      <a href="https://pepstock-org.github.io/Charba-Showcase/index.html?gallery=sankey">
        <img width="200px" src="https://raw.githubusercontent.com/pepstock-org/Charba-Wiki/master/static/img/gallerySankey.png"/>
      </a>
    </td>      
  </tr>  
  <tr>
    <td align="center">Area</td>
	<td align="center">Treemap</td>
	<td align="center">Matrix</td>
	<td align="center">Sankey</td>
  </tr>
</table>

Documentation
-------------

**Charba** documentation is published [here](https://pepstock-org.github.io/Charba-Wiki).

All **Charba** documentation will be maintained in [Charba-Wiki](https://github.com/pepstock-org/Charba-Wiki) project.

API JavaDoc for version **6.1** is published [here](https://pepstock-org.github.io/Charba/6.1/index.html).

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

Going to next release
---------------------

Here you can find the list of enhancements and updates available on `master` branch before which will be part of new official release:

### Breaking changes

 * rename `InteractionMode` enumeration to `DefaultInteractionMode`.
 * rename `InteractionItem` enumeration to `InteractionOptions`.
 * change the generic type from `Date` to `Object` for `MinMaxCallback` instance on time and time series axes. In this way the callback can return also a `Number` (i.e. a double) as epoch.
 
### Features

 * import CHART.JS version [v4.2.1](https://github.com/chartjs/Chart.js/releases/tag/v4.2.1).
 * add `Fill.SHAPE` item to the filling options.
 * add `FillBaseline` class to enable the baseline on axis for filling.
 * add `FillColors` class to enable different colors above and below the baseline.
 * add `interpolate` methods to `Helpers` class to enable the values interpolation for numbers, colors and booleans.
 * add `register` method to `GlobalPlugin` class to inject and register CHART.JS plugins not included in this library.
 * add `group` option to `CrosshairOptions` class by `Crosshair` plugin to enable crosshairs on linked chart instances.
 * enable the capabilities to create and use custom interaction mode. All classes needed for that are available in new package `org.pepstock.charba.client.interaction`.
 * add `getSortedVisibleDatasetMetas` method to the chart classes to get all dataset items in the order that they are drawn on the canvas that are not hidden.
  
### Fixed Bugs

 * [#87](https://github.com/pepstock-org/Charba/issues/87): remove check, which tested if the epoch must be greater than 1, when dates are managed. Thanks @Speykious.
 * [#88](https://github.com/pepstock-org/Charba/issues/88): add `register` method to `GlobalPlugin` class to inject and register CHART.JS plugins not included in this library. Thanks @Speykious.
 * [#89](https://github.com/pepstock-org/Charba/issues/89): change the generic type from `Date` to `Object` for `MinMaxCallback` instance on time and time series axes. In this way the callback can return also a `Number` (i.e. a double) as epoch. Thanks @Speykious.

### Development

 * change dependency for Google Closure Compiler, version [v20230206](https://mvnrepository.com/artifact/com.google.javascript/closure-compiler/v20230206). 

License
-------

**Charba** is available under the [Apache 2.0 license](https://www.apache.org/licenses/LICENSE-2.0).
