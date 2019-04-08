Charba - GWT Charts library based on CHART.JS
===============================================

Version 2.3

[![Build Status](https://travis-ci.com/pepstock-org/Charba.svg?branch=master)](https://travis-ci.com/pepstock-org/Charba) [![Sonarcloud Status](https://sonarcloud.io/api/project_badges/measure?project=pepstock-org_Charba&metric=alert_status)](https://sonarcloud.io/dashboard?id=pepstock-org_Charba) [![Awesome](https://awesome.re/badge-flat2.svg)](https://github.com/chartjs/awesome)  

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

It will be available also on [GitHub release](https://github.com/pepstock-org/Charba/releases).

If you are using [Apache Maven](https://maven.apache.org/):

```xml
<dependency>
    <groupId>org.pepstock</groupId>
    <artifactId>charba</artifactId>
    <version>2.3</version>
</dependency>
```

If you are using [Apache Ivy](http://ant.apache.org/ivy/):

```xml
<dependency org="org.pepstock" name="charba" rev="2.3"/>
```

If you are using [Gradle](https://gradle.org/):

```json
compile group: 'org.pepstock', name: 'charba', version: '2.3'
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

API JavaDoc is published [here](http://www.pepstock.org/Charba/2.3/index.html). 

Showcase
--------

See [Charba showcase](http://www.pepstock.org/Charba-Showcase/Charba_Showcase.html) to have a look what you can do with it.

See also [Charba showcase source code](https://github.com/pepstock-org/Charba-Showcase) on GitHub as starting point.

The samples are going to reflect what CHART.JS samples are showing [here](http://www.chartjs.org/samples/latest/).

Going to new version
-------

Here you can find the list of enhancement and updates available on `master` branch before which will be part of new official release:

### Features

 * implement new `Colorschemes` out-of-the-box plugin to enable to pick the color combination for charts from the predefined or custom color schemes.
 * implement `TitleClickHandler` to catch click events on title of charts
 * implement `AxisClickHandler` to catch click events on axes of charts
 * update ChartPointer to change cursor on title, axes or legend when the handlers are added
 * create GWT material scheme
 * enable custom number format to `NoSelectedDatasetTicksCallback` 
 * change `TilesBuilder` into a `TilesFactory` (no singleton)
 * implement `TilesBuilder` as sequence of set methods to create a pattern or canvas pattern
 * add `CharacterShape` for tiles in order to draw a char into a tile, as pattern 
 * integration with [Sonar.io](https://sonarcloud.io/dashboard?id=pepstock-org_Charba) 
  
### Development

 * fix issue about `ChartPointer` setting cursor
 * getType of dataset returns `null` is not set
 * change visibility of `HovingDataset`, `HovingFlexDataset` and `LiningDataset`
 * change visibility of Color to checkAlphaWithinBounds method
 * fix type value of default alpha (from 1F to 1D)
 * `DatasetsItems selector` plugin is not compatible with `Dataset selection` handler
 * move reading of option of `ChartPointer` into `beforeUpdate` method of plugin
 * expose constants of callbacks
 * remove final modifier to methods where class already final
 * changed visibility of some methods, from protected to package
 * change visibility of `IsShape` and `ShapeDrawer` classes
 * change key for tile caching to enable also for image and character shape drawers
 * transform and rotate of 180 degrees `Star` and `EmptyStar` shapes
 * `deprecated` some methods of Meter and Gauge datasets and options

### Fixed Sonar Issue
 * squid:S00115 - Constant names should comply with a naming convention
   * change all enumeration name to uppercase
 * javascript:S3403 - Strict equality operators should not be used with dissimilar types
   * change compare into label plugin
 * squid:S2583 - Conditionally executed blocks should be reachable
   * fix addCollection method removing check if new item is added
 * squid:S1192 - String literals should not be duplicated
   * change string into color schemes as constants
 * squid:S2293 - The diamond operator ("<>") should be used
 * squid:EmptyStatementUsageCheck - Empty statements should be removed
 * squid:S1452 - Generic wildcard types should not be used in return parameters
   * change callbacks, events, plugins and controller to use new interface `IsChart` instead of `AbstractChart<?,?>`
   * remove type on colors and point style callbacks
 * squid:ModifiersOrderCheck - Reorder the modifiers to comply with the Java Language Specification
 * squid:S1161 - "@Override" should be used on overriding and implementing methods
 * squid:S1604 - Anonymous inner classes containing only one method should become lambdas
 * squid:CommentedOutCodeLine - Sections of code should not be commented out
 * squid:S4784 - Using regular expressions is security-sensitive
 
License
-------

Charba is available under the [Apache 2.0 license](https://www.apache.org/licenses/LICENSE-2.0).
