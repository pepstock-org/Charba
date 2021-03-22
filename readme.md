Charba - J2CL and GWT Charts library based on CHART.JS
======================================================

[![Release](https://img.shields.io/github/release/pepstock-org/Charba.svg)](https://github.com/pepstock-org/Charba/releases/latest) [![MvnRepo](https://maven-badges.herokuapp.com/maven-central/org.pepstock/charba/badge.svg)](https://mvnrepository.com/artifact/org.pepstock/charba) [![License](https://img.shields.io/github/license/pepstock-org/Charba.svg)](https://github.com/pepstock-org/Charba/blob/master/LICENSE-2.0.txt) [![Build](https://github.com/pepstock-org/Charba/workflows/Build/badge.svg?branch=master)](https://github.com/pepstock-org/Charba/actions/) [![Sonarcloud Status](https://sonarcloud.io/api/project_badges/measure?project=pepstock-org_Charba&metric=alert_status)](https://sonarcloud.io/dashboard?id=pepstock-org_Charba) [![Awesome](https://awesome.re/badge-flat2.svg)](https://github.com/chartjs/awesome) [![CodedIsArtAndPassion](https://img.shields.io/badge/coding%20is-art%20and%20passion-E760A4.svg)](https://img.shields.io/badge/coding%20is-art%20and%20passion-E760A4.svg)

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

To build **Charba**, you can check out the project and to run [Ant build.xml](https://github.com/pepstock-org/Charba/blob/3.3/build.xml).

The [Ant build.xml](https://github.com/pepstock-org/Charba/blob/3.3/build.xml) is able to build the 2 artifacts, related to the 2 distributions available.

The first distribution is a **Charba** file without any GWT dependency (but working on GWT anyway), consumable also in other [J2CL - JavaToClosure](https://github.com/google/j2cl) frameworks, like [Google Elemental2](https://github.com/google/elemental2) and [Elemento](https://github.com/hal/elemento).

To build the project, execute `buildBinary` target.

It creates a `charba-[version.release].jar` file in `dist` folder, ready to be included in your project.

The second distribution is a **Charba** file with a hard GWT dependency which contains charts widgets and code splitting capabilities.

To build the project, execute `buildBinaryGwt` target.

It creates a `charba-[version.release]-gwt.jar` file in `dist` folder, ready to be included in your project.

[![Charba](https://github.com/pepstock-org/Charba/wiki/images/charba_jar_trend.png)](https://github.com/pepstock-org/Charba-Showcase/blob/3.3/src/org/pepstock/charba/showcase/client/views/HomeView.java)

Installation
------------

Currently **Charba** is available on [MVN repository](https://mvnrepository.com/artifact/org.pepstock/charba).

It is available also on [GitHub releases](https://github.com/pepstock-org/Charba/releases).

If you are using [Apache Maven](https://maven.apache.org/):

```xml
<dependency>
    <groupId>org.pepstock</groupId>
    <artifactId>charba</artifactId>
    <version>3.3</version>
    <!-- for GWT -->
    <version>3.3-gwt</version>
</dependency>
```

If you are using [Apache Ivy](http://ant.apache.org/ivy/):

```xml
<dependency org="org.pepstock" name="charba" rev="3.3"/>
<!-- for GWT -->
<dependency org="org.pepstock" name="charba" rev="3.3-gwt"/>
```

If you are using [Gradle](https://gradle.org/):

```json
compile group: 'org.pepstock', name: 'charba', version: '3.3'

compile group: 'org.pepstock', name: 'charba', version: '3.3-gwt'
```

To install in your GWT project, both for GWT and for J2CL artifacts, you must the following configuration in your GWT project module configuration:

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

All **Charba** documentation will be maintained in [GitHub wiki](https://github.com/pepstock-org/Charba/wiki) of **Charba** project.

API JavaDoc is published [here](http://www.pepstock.org/Charba/3.3/index.html).

Showcase
--------

See [Charba showcase on GWT](http://www.pepstock.org/Charba-Showcase/Charba_Showcase.html) to have a look what you can do with it.

See also [Charba showcase GWT source code](https://github.com/pepstock-org/Charba-Showcase) on GitHub as starting point, if you are going to use on GWT.

See [Charba showcase built by J2CL](http://www.pepstock.org/Charba-Showcase-J2CL/Charba_Showcase_J2CL.html) and based on [Google Elemental2](https://github.com/google/elemental2), to have a look what you can do with it.

See also [Charba showcase J2CL source code](https://github.com/pepstock-org/Charba-Showcase-J2CL) on GitHub as starting point, if you are going to use on J2CL.

The samples are going to reflect what CHART.JS samples are showing [here](http://www.chartjs.org/samples/latest/).

Continuous integration and quality gate
---------------------------------------

**Charba** is continuously built at every commit and merge in `master` by [GitHub Action](https://github.com/pepstock-org/Charba/actions?query=workflow%3ABuild).

At every build, **Charba** is also checked by [Sonar.io](https://sonarcloud.io/dashboard?id=pepstock-org_Charba) in order to have the pulse of its quality.

In the project, it's also provided the [FindBugs](https://github.com/pepstock-org/Charba/blob/2.8/charba.fbp) project to looking offline for bugs.

Going to next release
---------------------

Here you can find the list of enhancements and updates available on `master` branch before which will be part of new official release:

### Breaking changes
 
 * remove `ResourcesType` and `EntryPointStarter` classes as objects to initialize **Charba**.
   * There are 2 new entry points to initialize **Charba**:
     * `Charba.enable()` or `Charba.enable(boolean)` for embedded resources
     * `DeferredCharba.enable(Runnable)` or `Charba.enable(Runnable, boolean)` for deferred resources
 * changes by new **CHART.JS version**:
   * **Drop Internet Explorer 11 support**.
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
   * change structure of dataset elements.
   * rename `SteppedLine` enumeration to `Stepped`.
   * move `beginAtZero` property from ticks to axis/scale.
   * move `min` property from ticks to axis/scale.
   * move `max` property from ticks to axis/scale.
   * move `reverse` property from ticks to axis/scale.
   * move `suggestedMax` property from ticks to axis/scale.
   * move `suggestedMin` property from ticks to axis/scale.
   * move `min` property from time object to axis/scale.
   * move `max` property from time object to axis/scale.
   * move `labels` property from tick (cartesian category) object to axis/scale.
   * remove `getCartesianType()` method from `CartesianAxis` class, use `getAxis` method.
   * remove `DEFAULT_X_AXIS_ID`, `DEFAULT_Y_AXIS_ID` and `DEFAULT_SINGLE_AXIS_ID` constants from `Scales` class, use `DefaultScaleId` enumeration.
   * move `Weight` enumeration from `org.pepstock.charba.client.datalabels.enums` to `org.pepstock.charba.client.enums` because new `Font` implementation must be used.
   * remove `fontSize`, `fontStyle`, `fontFamily`, `lineHeight` and add `color` property (to set font color) to the following options classes, because new `Font` implementation must be used:
      * `Options`
      * `LegendLabels`
      * `Title`
      * `Tooltips`
      * `PointLabels`
      * `ScaleLabel`
      * `Ticks`
   * remove `fontSize`, `fontStyle`, `fontFamily`, `lineHeight` and add `color` property (to set font color) to the following configuration classes, because new `Font` implementation must be used:
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
   * change the chart events constructor using `ChartEventContext` which contains the chart and the native event.
   * remove `generateLegend` method from `Defaults` class and from `IsChart` class because the prototype is not available anymore.
   * remove `LegendCallback` interface and its usage from `ConfigurationOptions` class because the prototype is not available anymore.
   * remove `easing` properties from `DatasetPluginItem` and `TooltipPluginItem` classes because it is not provided anymore.
   * remove `HORIZONTAL_BAR` item of `ChartType` enumeration because the horizontal bar is not a chart type anymore.
   * remove `updateScale` method from `Defaults` because is not available anymore. Use `getScale(AxisType type)` instead.
   * redesign of `TooltipModel` class in according with new Chart.js model.
   * remove `ScaleDistribution` enumeration. Use new scale `CartesianTimeSeriesAxis`, introduced in its place.
   * remove `ease` argument from `onBeforeDraw`, `onAfterDraw`, `onBeforeDatasetsDraw`, `onAfterDatasetsDraw`, `onBeforeDatasetDraw` and `onBeforeDatasetDraw` methods of `Plugin` interface because is not passed by CHART.JS anymore.
   * remove `hidden` attribute from `LegendItem` and `LegendLabelItem` classes because not supported anymore. Use new `isHidden(chart)` method of `LegendItem` class.
   * change `Controller` interface in order to be align with new interface implemented in new CHART.JS.
   * remove `TimeTickItem` class, use `ScaleTickItem` class.
   * add `getValueAsDate()` method to `ScaleTickItem` class in order to get the tick value as `Date`.
   * rename `getValueAsDouble` method of `ScaleTickItem` class to `getValue`.
   * remove `getTimeTickItems` method from `ScaleItem` class, use `getTickItems`.
   * change `onCallback` method signature of `TimeTickCallback` class in order to get a list of `ScaleTickItem` instead of `TimeTickItem`.  
   * change `onAfterBuildTicks` method signature of `TimeAxisBuildTicksCallback` class in order to get a list of `ScaleTickItem` instead of `TimeTickItem`.
   * remove `getEasing` method from `AnimationItem` class because is not provided anymore by new animation engine.
   * remove the list of ticks as argument from `AxisBuildTicksCallback` class.
   * change `isoWeekday` property of `Time` options and configuration class does not store a `boolean` anymore but new `IsoWeekDay` enumeration with all days of week.
   * rename `Rectangle` options and configuration in `Bar`, inside the `Elements` node of the options and configuration.
   * rename `showLines` property to `showLine` property for line and radar chart options and configuration.
   * add `usePointStyle` property to `Tooltips` class for options and configuration.
   * add the update mode to the `onAfterDatasetsUpdate`, `onAfterUpdate`, `onBeforeDatasetsUpdate`, and `onBeforeUpdate`methods of `Plugin` interface.
   * change the structure of scriptable options context.
     * remove `getIndex` method from `ScriptableContext` class, use getDataIndex instead.
     * add type of the context.
   * change `fill` default option for `Line` element from `Fill.ORIGIN` to `Fill.FALSE`.
   * change the signatures of `Plugin` interface in order to manage new arguments of functions.
   * rename `lineTension` property to `tension` in `LiningDataset` class.
   * rename `tickMarkLength` property to `tickLength` in `GridLines` configuration and options classes.
   * change `dataPoint` property in `parsed` one in `ScriptableContext` class.
   * change `cutoutPercentage` property from double to string in order to manage a percentage `[number]%` format in the options and configuration classes, for pie and doughnut charts.
   * rename `ScaleLabel` class to `ScaleTitle` in order to keep it aligned with CHART.JS name spaces.
   * rename `labelString` property name to `text` in `ScaleTitle` class for options and configuration.
   * rename `TooltipCustomCallback` class to `TooltipExternalCallback` in order to keep it aligned with CHART.JS name spaces.
   * remove `xPadding` and `yPadding` properties from `Tooltips` class for options and configuration, use `Padding` instead.
   * change `fullWidth` property to `fullSize` in `Title` and `Legend` classes for options and configuration.
   * rename `Gridlines` class to `Grid` for options and configuration in order to keep it aligned with CHART.JS name spaces.
   * rename `offsetGridlines` property to `offset` in `Grid` class for options and configuration.
 * change `getXAxisID` and `getXAxisID` methods in `LineDataset` class in order to return a `IsScaleId` instance instead of a `String`.
 * change `getXAxisID` and `getXAxisID` methods in `BarDataset` class in order to return a `IsScaleId` instance instead of a `String`.
 * change `getXAxisID` method in `DatasetsItemsSelectorOptions` class in order to return a `IsScaleId` instance instead of a `String`.
 * remove `Event.TOUCHEND` item
 * rename `LegendAlign` class to `ElementAlign` in order to be able to use for `Title` options and configuration classes.
 * rename `CartesianAxisType` class to `AxisKind` in order to manage axis type for radial linear and not only cartesian ones.
 * remove `MarginsItem`, `AxisMarginsItem` and `AxisMinSizeItem` classes because they are not visible in CHART.JS anymore.
 * remove `setAxis` methods from `Scale` and `Axis` classes because it must be set when an axis is built.
 * remove the feature to create custom controller without extending an existing one. You can only extend existing chart type.
 * rename `extend` method of `Controllers` class to `register`.
 * remove `setEnabled` method for `DefaultPluginId` enum from `Plugin` options class in order to avoid an inconsistent default options of plugin. Use `setDisplay` method for legend and title in legend and title options, and `setEnabled` method for tooltips in tooltips options.
 * remove `setT(Date)` and `Date getT` methods from `DataPoint` class. Use `setX(Date)` and `Date getXAsDate()` instead.
 * rename `getValue` and `getIndex` methods to `getFormattedValue` and `getDataIndex` ones in `TooltipItem` class in order to be aligned with new CHART.JS tooltip item interface. 
 * change the return value of `getTooltip` method of `TooltipPluginItem` class, returning now a `TooltipModel` object instead of `TooltipNode`.
 * reduce visibility of `setHidden` method of `Dataset` class and add hidden argument to `Dataset` constructor in order to set the initial visibility. To change the dataset visibility, use the `setDatasetVisibility` chart method.
 * remove `render(UpdateConfiguration)` method has been removed because it is not available anymore on CHART.JS.
 * remove `JsWindowHelper` class. Use `Window.enableResizeOnBeforePrint()`.
 * rename `HtmlLegendTextCallback` class to `HtmlLegendItemCallback`.
 * remove `CLEAR_SELECTION` constant from `DatasetRangeSelectionEvent` class because the event is containing the selected values and not the indexes anymore.
 * reduces the visibility of `Gradient` and `Pattern` class constructors in order to use new `GradientBuilder` and `PatternBuilder` classes. This change avoids inconsistent gradient and pattern objects, improving some capabilities of them (like the `equals` and `hashCode` methods in case they will be needed).
   * adds `setColors` methods (getting a list or an array of `IsColor`) to `GradientBuilder` in order to enable the gradient creation to the `ColorScheme` instances. 
 * rename `ValueCallback` to `MeterFormatCallback` class.
   * rename `setValueCallback` and `getValueCallback` of `MeterOptions` class to `setFormatCallback` and `getFormatCallback`.
 * change the type for some properties of LuxonOptions class:
   * `locale` is now a `CLocale` object instead of a string.
   * `numberingSystem` is now a `NumberingSystem` object instead of a string.
   * `zone` is now a `TimeZone` object instead of a string.
   * `outputCalendar` is now a `Calendar` object instead of a string.
 * remove the usage of `Moment` as date library because it does not use the web internationalization.
 * remove the usage of `Date-fns` as date library because it does not use the web internationalization.
 * add `BarBorderWidthCallback` interface to use for `borderWidth` and `hoverBorderWidth` callbacks in `BarDataset` class.
 * rename `getDatasetMeta(int)` method to `getDatasetItem(int)` in the `IsChart` interface.
 * rename `getDatasets()` method to `getElements()` in the `DatasetItem` class.
 * rename `getMeta()` method to `getDatasetItem()` in the `DatasetPluginItem` class.
 * change the constructor signature of `ControllerType` class adding a mandatory argument of `ControllerProvider` instance which can provide the instance of controller to register.
   * add an optional argument (boolean) in order to decide if the base default options of the chart which is being extended must be cloned or not. 
   * reduce the visibility of `register` method of `Controller` class   
 * remove `AbstractPluginCachedOptions` and `AbstractPluginCachedOptionsFactory` classes. Use instead callbacks cache.
 * change `LabelsPlugin` in order to :
   * add new `color` property for the font color, in order to be aligned with CHART.JS 3 implementation.
   * change `FontColorCallback` in favor of `ColorCallback` where you can decide the font color.
   * use `font` object instead of the single properties.
   * add `FontColorCallback` in favor of `FontCallback` where you can decide the whole content of font.
   * pass a `Context` object instance as argument to the callback instead of `RenderItem` and `FontColorItem` ones.
 * change `AbstractPlugin` class, removing all methods leaving only `getId` (because the `Plugin` interface has got all default methods) and new constructor where the plugin id is mandatory.  
 * change `borderDashOffset` option from int to double in all classes where referenced.
 * remove `setOptions(List<T>)`, `getOptionsAsList` and `getOptionsType` methods from `Plugins` configuration and options classes.
 * rename `ScaleLabelAlign` enumeration class to `ScaleTitleAlign` in order to keep it align with CHART.JS names.
 * remove `ScaleLabelPadding` options,  use `Padding` instead.
 * remove `CartesianPadding` options,  use `Padding` instead.
 * rename `ScaleBounds` class to `Bounds`.
  
### Features

 * **import last CHART.JS version,** [version 3.0.0-rc.2](https://github.com/chartjs/Chart.js/releases/tag/v3.0.0-rc.2) on March 22nd, 2021.
   * add `setDatasetVisibility` method to `AbstractChart` class in order to set the visibility for a given dataset. 
   * add `toggleDataVisibility` method to `AbstractChart` class in order to toggle the visibility of an item in all datasets.
   * add `isDataVisible` method to `AbstractChart` class in order to get the stored visibility state of an data index for all datasets. 
   * add `hide` method to `AbstractChart` class in order to hide a dataset. 
   * add `show` method to `AbstractChart` class in order to show a dataset. 
   * add axis id parameter to cartesian axes classes constructor.
   * add `toFont` method to `Utilities` class in order to get a `Font` object as parameter.
   * add `align` property to `ScaleLabel` options and `CartesianScaleLabel` configuration classes.
   * add `borderColor` and `borderWidth` properties to `GridLines` options and configuration classes.
   * add `ScaleLineWidthCallback` interface to `GridLines` or `RadialAngleLines` configuration in order to set `lineWidth` property at runtime.
   * add `ScaleColorCallback` interface to `GridLines`, `RadialAngleLines` or `RadialLinearTick` configuration in order to set `color` and `backdropColor` property at runtime.
   * add `ScaleBorderDashOffsetCallback` interface to `GridLines` or `RadialAngleLines` configuration in order to set `borderDashOffset` property at runtime.
   * add `ScaleBorderDashCallback` interface to `RadialAngleLines` configuration in order to set `borderDash` property at runtime.
   * add `ScaleFontCallback` interface to `RadialPointLabels` or `Tick` configuration in order to set `font` property at runtime.
   * add `ScaleShowLabelBackdropCallback` interface to `RadialLinearTick` or `Tick` configuration in order to set `showLabelBackdrop` property at runtime.
   * add `boxHeight` property to `LegendLabels` options and configuration classes.
   * add `title` property to `Legend` options and configuration classes in order to manage a title on legend
   * add `align` property to `Title` options and configuration classes.
   * add `boxWidth` and `boxHeight` properties to `Tooltips` options and configuration classes.
   * add `Datasets` class in order to manage datasets options and configuration classes.
   * add `START` and `END` items to `BorderSkipped` enumeration
   * add `width` and `height` options to `ChartAreaNode` class
   * add `onReset` method to `Plugin` interface in order to enable to catch when a chart is resetting.
   * add `spanGaps` number property (double) to `LiningDataset` class in order to manage the value where there are some gaps.
   * add `indexAxis` property to `BarDataset` and `BarOptions` classes in order to manage the horizontal bars.
   * add `clip` property to all datasets types.
   * add `CartesianTimeSeriesAxis` class in order to manage time series scales.
   * add `dataPoint` property to `TooltipItem` class.
   * enable the feature to manage floating bars on `time` or `timeseries` axes and bar charts adding to `DataPoint` class the possibility to add a `FloatingData` object as `Y` value.
   * implement new animation options and configuration for new engine:
     * add new animation entities (collection of properties, transitions) and new namespaces where the options must be stored. 
     * add `delay` property to animation options.
     * add `loop` property to animation options.
     * add `animation` options and configuration to datasets.
     * add `animation` options and configuration to tooltips.
     * add `setAnimationEnabled` and `isAnimationEnabled` methods to animation containers to configuration, options and datasets.
   * add `update(IsTransitionKey)` and `reconfigure(IsTransitionKey)` methods to `IsChart` interface in order to update the chart by an animation update mode.
   * change `borderSkipped` property in `BarDataset` class in order to manage it as indexable options.
   * add `offset` and `hoverOffset` properties to `PieDataset` and `DoughnutDataset` classes.
   * add `minIndex` and `maxIndex` properties to `CartesianCategoryAxis` class in order to manage minimum and maximum by index of the label, instead of its content.
   * add `labels` property to `CartesianCategoryAxis` class.
   * add `stack` item to `Fill` enumeration.
   * change `borderAlign` property for `PieDataset`, `PolarAreaDataset` and `DoughnutDataset` in order to be set as indexable.
   * change `borderWidth` property for `BarDataset` in order to be set as indexable when set as an object.
   * add `maxWidth` and `maxHeight` properties to `Legend` options and configuration.
   * add `skipNull` property to `BarOptions` configuration.
   * change `circumference` and `rotation` properties on `PieChart` and `DoughnutChart` options in order to set the value in degrees instead of radians.
   * add `sort` callback (by `LegendItemSortCallback` class) to the `LegendLabels` configuration in order to sort the legend items. 
   * add `beginAtZero` property to `CartesianLogarithmicAxis` class.
   * add `locale` property to options and configuration.
   * add `format` property to all numeric ticks (cartesian linear and log, radial linear) in order to apply the number formatting by [ECMAScript Internationalization API](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Intl/NumberFormat), leveraging on **Charba INTL NumberFormat** implementation.
   * add `borderRadius` and `hoverBorderRadius` properties to `BarDataset` class.
   * add `suggestedMin` and `suggestedMax` properties to cartesian logarithmic axes.
   * add `align` and `crossAlign` properties to cartesian ticks.
   * add the `center` axes position and the position of the axis with respect to a data value.
   * enable overriding the legend `pointStyle` (from dataset) using new `pointStyle` option.
   * add `onLabelPointStyle` method to `TooltipLabelCallback` interface and `TooltipLabelPointStyle` class in order to manage the point style in tooltip.
   * add `backgroundColor` and `borderColor` default options.
   * add new hooks to `Plugin` interface:
     * `onInstall`, `onStart`, `onStop`, `onUninstall` to manage plugin life-cycle.
     * `onBeforeDataLimits`, `onAfterDataLimits`, `onBeforeBuildTicks`, `onAfterBuildTicks` to manage scale setup.
   * add `resize(width height)` method to `IsChart` interface.
   * add `textStrokeColor` and `textStrokeWidth` properties to `Ticks` configuration and options classes.
   * add `Interaction` class in order to manage datasets options and configuration classes.
   * add `base` property to `BarDataset` configuration and to `Datasets` configuration and options classes.
   * add `tickBorderDash` property to `GridLines` configuration and options classes.
   * add `tickBorderDashOffset` property to `GridLines` configuration and options classes.
   * add `tickColor` property to `GridLines` configuration and options classes.
   * add `tickWidth` property to `GridLines` configuration and options classes.
   * add `animate` property to `RadialAxis` configuration and `Scale` options classes.
   * add `onBeforeElementsUpdate` method to the `Plugin` interface.
   * add `pointStyle` property to `Bar` element configuration and options classes.
   * add `pointStyle` property to `BarDataset` class.
   * add `cutout` property in the options and configuration classes, for pie and doughnut charts.
   * add `radius` and `radiusPercentage` properties in the options and configuration classes, for pie and doughnut charts.
   * add multiple text lines for `text` property of ScaleTitle class.
   * implement `decimation` plugin options and configuration.
   * implement `filler` plugin options and configuration.
   * add `resizeDelay` property to options and configuration.
   * add `VerticalLine` chart, options and data set classes to manage this new kind of chart.
   * add `grace` property to scale options and to cartesian linear axis configuration.
   * add `backdropColor`, `backdropPadding` and `padding` properties to `PointLabels` options and `RadialPointLabels` configuration classes.
 * add `setScaleID` method to `LineAnnotation` class of Annotation plugin in order to set the scale id using `IsScaleId` implementation.
 * add `setXScaleID` and `setYScaleID` methods to `BoxAnnotation` class of Annotation plugin in order to set the scale id using `IsScaleId` implementation.
 * add `setXAxisID` and `setYAxisID` methods to `LineDataset` class in order to set the scale id using `IsScaleId` implementation.
 * add `setXAxisID` and `setYAxisID` methods to `BarDataset` class in order to set the scale id using `IsScaleId` implementation.
 * add `setXAxisID` method to `DatasetsItemsSelectorOptions` class in order to set the scale id using `IsScaleId` implementation.
 * add `getAxis(scaleId, axisKind)` method from `IsDefaultScale`s interface in order to get the default scale and remove `getXAxis` and `getYAxis` methods.
 * add `onBeginDrawing` and `onEndDrawing` methods to `Plugin` interface in order to invoke the plugin once before starting and after ending any drawing.
 * add `linkScales` and `buildOrUpdateElements` methods to Controller interface in order to have the complete mapping of CHART.JS controller interface.
 * add `newDataset(boolean)` methods for all charts (by new `IsDatasetCreator` interface) in order to get new dataset with the initial visibility status.
 * add the following methods to `ScaleItem` class in order to improve the interaction with scale elements:
   * `getDecimalForPixel`
   * `getPixelForDecimal` 
   * `getPixelForTick`
   * `getLabelForValue`
   * `getPixelForValue`
   * `getPixelForValue`
   * `getValueForPixel`
   * `getBaseValue`
   * `getBasePixel`
 * manage new legend title in `HtmlLegend` plugin.
   * add `HtmlLegendTitleCallback` callback to apply a custom legend title in HTML.
   * rename `HtmlLegendTextCallback` callback to `HtmlLegendItemCallback`.
 * add the selected value of the scale by the click event to the `AxisClickEvent` class.
 * **import last CHART.JS Datalabels plugin version,** [version v2.0.0-beta.1](https://github.com/chartjs/chartjs-plugin-datalabels/releases/tag/v2.0.0-beta.1) on March 12th, 2021.
 * **import last CHART.JS LUXON adapter version,** [version v1.0.0-beta.2](https://github.com/chartjs/chartjs-adapter-luxon/releases/tag/v1.0.0-beta.2) on March 9th, 2021.
 * **import last LUXON library version,** [version 1.26.0](https://github.com/moment/luxon/releases/tag/1.26.0) on March 9th, 2021.
 * **import last CHART.JS Annotation plugin version,** [version v1.0.0-beta.4](https://github.com/chartjs/chartjs-plugin-annotation/releases/tag/v1.0.0-beta.4) on March 7th, 2021.
   * implement only click, dblclick, enter and leave events.
   * add `ellipse` and `point` annotation types.
   * add `display` property to all annotations in order to enable or disable the display of annotation.
   * remove `mode` property from annotation line options because new plugin is using the `axis` property of scales for line orientation.
   * remove `events` property from annotation options because the event listeners will be added based on the callbacks definitions.
   * add `autoRotation` property to annotation line label options in order to enable the automatic calculation of label rotation.
   * remove `name` property from annotation options because is not needed anymore. Use `id` property instead.
   * change `getScaleID` method in `LineAnnotation` class of Annotation plugin in order to return a `IsScaleId` instance instead of a `String`.  
   * change `getXScaleID` and `getXScaleID` methods in `BoxAnnotation` class of Annotation plugin in order to return a `IsScaleId` instance instead of a `String`.
   * move `AnnotationType` enumeration from `org.pepstock.charba.client.annotation.enums` to `org.pepstock.charba.client.annotation` in order to maintain the low visibility of internal classes of the annotation plugin implementation.
   * change the constructors of `LineAnnotation` and `BoxAnnotation` in order to set an ID to the object. This will enable the possibility to defines annotations items as default.
 * add new `DatasetRangeClearSelectionEvent` event for `DatasetsItemsSelector` plugin in to order to notify when a clear action has been performed on chart.
   * remove `fireEventOnClearSelection` properties has been removed from DatasetsItemsSelectorOptions class because an clear selection event will fire only if there is a event handler. 
 * implement **INTL NumberFormat** of [ECMAScript Internationalization API](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Intl/NumberFormat).
   * implement `CLocale` class to manage locale objects.
   * implement the retrieving of locale from query string, `<meta>` element (as GWT is doing) or from navigator instance.  
 * implement **INTL DateTimeFormat** of [ECMAScript Internationalization API](https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/Intl/DateTimeFormat).
 * change the default date adapter library, using as default `Luxon` instead of `Moment` in order to enable I18N by default.
 * change the `Adapter` options and configuration of time axis in order to get a date adapter options (LUXON options) by `date` property.
 * change all methods of `Plugin` interface becoming all default ones.
 * add `enabled` property to DatasetsItemsSelector plugin in order to disable/enable the plugin at runtime.
  
### Development

 * change visibility of `CartesianAxis` class, now it is public.
 * change visibility of `AbstractModel` class, now it is public.
 * add `checkAndGetValue` static method to `IsColor` class.
 * add `@Override` annotation to all overriding methods. 
 * override the hashCode `$H` property for `NativeObject` objects that GWT is adding to objects in order to set the property as NOT enumerable and NOT configurable.
 * change `compare` static method of `Key` interface in order to test if the keys are valid and not only not `null`.
 * change the registering of tooltips positioner in order to apply to CHART.JS tooltips plugin.
 * remove `SingleScaleOptions` class because the radial linear axis is managed like the cartesian ones.
 * rename `MultiScalesOptions` class to `ScalesOptions`.
 * remove `getAxis` method from `IsDefaultScale` interface because the axis kind does not any default and, when not set, depends on scale id.
 * move `width` and `height` options to `BaseBoxitem` class.
 * change all properties of `BaseBoxItem`, `SizeItem`, `LegendHitBoxItem` classes from `int` to `double`.
 * change `getLineWidths` and `getColumnWidths` methods of `LegendNode` class in order to return a list of doubles instead of a list of integers.
 * add `columnHeights` property to `LegendNode` class.
 * improve the defaults management for scales.
 * add `JsPluginHelper` class in order to use CHART.JS registry feature to manage plugins.
 * create a controller template code generator and add a specific target in `build.xml`.
 * reintroduce the `Findbugs` project in order to check the bugs on project.
 * reduce the visibility of objects which must get a javascript native object as argument on constructor, using an envelop. This reduces the possibility to map a native object with a wrong wrapper.
 * remove `D` dataset type from `AbstractChart` class by `IsDatasetCreator` interface.
 * rename the controller id for `Meter` and `Gauge` charts adding `charba` prefix in order to avoid overlapping with possible other controllers with `meter` and `gauge` ids.
 * `Window.enableResizeOnBeforePrint()` method has been fully implemented by JSINTEROP.
 * improve the code of `HtmlLegend` plugin in order to manage texts (for legend items and title) in the same way.
 * reduce visibility of property handler classes leveraging on the interface.
 * improve the logic of `DatasetsItemsSelector` plugin in order to leverage on new capabilities of scale item to retrieve the selected data instead of the index.
 * implement `ImmutableDate` class to use to return the date value stored to chart configuration and to callbacks.
 * implement easing value management for `Meter` and `Gauge` charts (and their controller) because in the new version of CHART.JS, easing value is not provided anymore.
 * change `Travis` configuration in order to use `JDK11` to compile the project and install `NodeJs12` in order to be compliant with new restrictions of `SonarCloud`.That means that Charba is compilable also on `JDK11` but it will be release on `JDK8` in order to be consumed by applications which are still using `JDK8`.
 * create `ItemSortCallback` interface in order to be extended by legend and tooltip callback interfaces.
 * remove static cache for canvas patterns and gradients creation, going to a cache for each canvas object factory in order that every factory must manage own objects.
 * add `groups` method to `RegExpResult` in order to get the object to refer to certain token by string that a regular expression matches.
 * add `equals` and `hashCode` methods to standard keys implementation in `options` package.
 * add the following properties to `RegExp` class;
   * `lastIndex` (static)
   * `dotAll`
   * `global` 
   * `ignoreCase`
   * `multiline`
   * `source`
   * `sticky`
   * `unicode`
 * add `BaseLocation` and `BaseNavigator` native object classes to get the location and navigator from `DOM`.
 * add `Meta` native object class to map a `<meta>` element from `DOM` document.
 * add `boolean has(Object,String)` method to `JsHelper` class in order to check if a property (by its key) belongs to an object or not. THis is done because CHART.JS merges objects without `prototype`, for security reason.
 * improve the visibility and reuse of the native object factories in order to have only 1 static instance from the object which must be created.
 * add `NativeConfiguration` native JSType class to get the `config` from chart instance and used to update the configuration at runtime.
 * improve the management of properties used cross classes or packages.
 * improve the tooltip callbacks management providing the right defaults.
 * improve the gradients and patterns management at dataset level in order to be managed by a callback instead of by a plugin in order to have the right chart area size for gradients.
 * improve `Annotation` plugin in order to leverage on callbacks cache which enables the complete configuration also at default or chart type levels.
 * improve `DataLabels` plugin in order to leverage on callbacks cache which enables the complete configuration also at default or chart type levels.
 * improve `DataLabelsOptionsBuilder` class in order to manage the multi-labels configuration.
 * improve `Labels` plugin in order to leverage on callbacks cache which enables the complete configuration also at default or chart type levels.
 * improve `LabelsOptionsBuilder` class in order to manage the multi-labels configuration.
 * improve `Zoom` plugin in order to leverage on callbacks cache which enables the complete configuration also at default or chart type levels.
 * use new location for legend, title and tooltips options, in `plugins` options node.
 * enable CI by GitHub Action, disabling Travis.
 * fix font and color fallbacks to the defaults values.
 * add `CScheduler` class in order to provide asynchronous and delayed task scheduling.
 * add `CTimer` class in order to provide asynchronous and repeatedly task scheduling.
 * re-factor the `configuration` package in order to manage both configuration and chart options (at runtime).

License
-------

 **Charba** is available under the [Apache 2.0 license](https://www.apache.org/licenses/LICENSE-2.0).
