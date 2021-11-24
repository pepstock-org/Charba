Charba - J2CL and GWT Charts library based on CHART.JS
======================================================

<p align="center">
  <a href="https://github.com/pepstock-org/Charba/releases/latest"><img alt="Last release" src="https://img.shields.io/github/release/pepstock-org/Charba.svg"></a>
  <a href="https://mvnrepository.com/artifact/org.pepstock/charba"><img alt="Mvn repo release" src="https://maven-badges.herokuapp.com/maven-central/org.pepstock/charba/badge.svg"></a>
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

To build **Charba**, you can check out the project and to run [Ant build.xml](https://github.com/pepstock-org/Charba/blob/4.2/build.xml).

The [Ant build.xml](https://github.com/pepstock-org/Charba/blob/4.2/build.xml) is able to build the 2 artifacts, related to the 2 distributions available.

The first distribution is a **Charba** file without any GWT dependency (but working on GWT anyway), consumable also in other [J2CL - JavaToClosure](https://github.com/google/j2cl) frameworks, like [Google Elemental2](https://github.com/google/elemental2) and [Elemento](https://github.com/hal/elemento).

To build the project, execute `buildBinary` target.

It creates a `charba-[version.release].jar` file in `dist` folder, ready to be included in your project.

The second distribution is a **Charba** file with a hard GWT dependency which contains charts widgets and code splitting capabilities.

To build the project, execute `buildBinaryGwt` target.

It creates a `charba-[version.release]-gwt.jar` file in `dist` folder, ready to be included in your project.

[![Charba](https://github.com/pepstock-org/Charba-Wiki/blob/master/static/img/charba_jar_trend_42.png)](https://github.com/pepstock-org/Charba-Showcase/blob/4.2/src/org/pepstock/charba/showcase/client/views/HomeView.java)

Installation
------------

Currently **Charba** is available on [MVN repository](https://mvnrepository.com/artifact/org.pepstock/charba).

It is available also on [GitHub releases](https://github.com/pepstock-org/Charba/releases).

If you are using [Apache Maven](https://maven.apache.org/):

```xml
<dependency>
    <groupId>org.pepstock</groupId>
    <artifactId>charba</artifactId>
    <version>4.2</version>
    <!-- for GWT -->
    <version>4.2-gwt</version>
</dependency>
```

If you are using [Apache Ivy](http://ant.apache.org/ivy/):

```xml
<dependency org="org.pepstock" name="charba" rev="4.2"/>
<!-- for GWT -->
<dependency org="org.pepstock" name="charba" rev="4.2-gwt"/>
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

API JavaDoc for version **4.2** is published [here](https://pepstock-org.github.io/Charba/4.2/index.html).

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

  * drop support for Java 8 going to Java 11 as minimum requirement.
  * change `onBeforeLabel`, `onLabel` and `onAfterLabel` methods to `TooltipLabelCallback` interface in order to return a `List<String>` instead of `String` instances to enable tooltip multilines labels.
  * change `invokeTooltipsCallbackOnLabel` method to `Defaults` class in order to return a `List<String>` instead of `String` instances.
  * refactoring of `Meter` and `Gauge` controller:
    * add `ValueLabel` and `DescriptionLabel` elements in the meter and gauge dataset in order to configure how to render the labels.
    * move all methods to configure the value label from options to dataset class.
    * remove `Render` enumeration because is not used anymore because the rendering as percentage can be set by `ValueLabel` and the label can be configured in `DescriptionLabel`.
  * remove `CornerRadiusCallback` class. Use `BorderRadiusCallback` one.
  * change signature of `BorderRadiusCallback`, adding the generic for scriptable options context class.
  * rename `cornerRadius` option to `borderRadius` in `LineLabel` and `BoxAnnotation` classes.
  * remove `divider*` and `groupDividers` options from `TreemapDataset` class in favor of `Dividers` node.
  * remove `font`, `hoverFont`, `color`, `hoverColor` and `groupLabels` options from `TreemapDataset` class in favor of `Labels` and `Captions` nodes.
  * remove `Dash` class from `TreemapDataset` because not needed anymore.

### Features

  * import CHART.JS [version v3.6.0](https://github.com/chartjs/Chart.js/releases/tag/v3.6.0).
  * import CHART.JS GEO controller [version v3.6.0](https://github.com/sgratzl/chartjs-chart-geo/releases/tag/v3.6.0).
  * import CHART.JS TREEMAP controller [version v2.0.0](https://github.com/kurkle/chartjs-chart-treemap/releases/tag/v2.0.0).
  * import CHART.JS ZOOM plugin [version v1.2.0](https://github.com/chartjs/chartjs-plugin-zoom/releases/tag/v1.2.0).
  * import CHART.JS ANNOTATION plugin [version v1.1.0](https://github.com/chartjs/chartjs-plugin-annotation/releases/tag/v1.1.0).
  * enable all options of `SizeAxis` and `ColorAxis` (GEO charts) as scriptable ones.
  * add `projection` and `projectionInvert` methods to GEO charts in order to translates latitude and longitude in XY coordinates and viceversa.
  * add `getColorForValue` and `getColorForValueAsString` methods to GEO color axis in order to get the color for a specific value.
  * add `getSizeForValue` method to GEO size axis in order to get the size for a specific value.
  * enable the capability to set `cornerRadius` of the tooltip by `BarBorderRadius` object.
  * add `boxPadding` option to `Tooltips` options and configuration classes.
  * add `inflateAmount` option to `Bar` options and configuration classes and to `BarDataset` class.
  * add `autoPadding` option to `Layout` options and configuration classes.
  * enable the capability to hide the opened toast items programmatically.
  * add `formatNumber` methods to Helpers, provided out-of-the-box by CHART.JS.
  * add `isZoomedOrPanned` method to `ZoomPlugin` in order to get if the chart is zoomed or panned.
  * add `rotation` option to `EllipseAnnotation` class.
  * enable to set a `BarBorderRadius` object to `borderRadius` option in `LineLabel` and `BoxAnnotation` classes. 
  * add `borderCapStyle`, `borderColor`, `borderDash`, `borderDashOffset`, `borderJoinStyle` and `borderWidth` options to `LineLabel` class.
  * add `align`, `position` and `formatter` options to `Labels` node in `TreemapDataset` class in order to improve labels rendering.
  * add `align` and `formatter` options to `Captions` node in `TreemapDataset` class in order to improve captions rendering.
    
### Developing

  * change dependency for Google Closure Compiler, [version v20211006](https://mvnrepository.com/artifact/com.google.javascript/closure-compiler/v20211006).
  * add consistent checking for scriptable options which are returning a number.

License
-------

 **Charba** is available under the [Apache 2.0 license](https://www.apache.org/licenses/LICENSE-2.0).
