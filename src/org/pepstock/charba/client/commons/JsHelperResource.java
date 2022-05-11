/**
    Copyright 2017 Andrea "Stock" Stocchero

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

	    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/
package org.pepstock.charba.client.commons;

import org.pepstock.charba.client.resources.AbstractInjectableResource;
import org.pepstock.charba.client.resources.ResourceName;

/**
 * Contains the content of <code>charba.helper.min.js</code> to inject.<br>
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class JsHelperResource extends AbstractInjectableResource {

	// encoded javascript content of charba.helper.min.js
	private static final String[] CONTENT = {
		"var CharbaCallbackProxy={callback:null,proxy:null,ignoreFunctionContext:!0};function CharbaJsHelper(){}CharbaJsHelper.create=function(){return{}};CharbaJsHelper.cast=function($object$$){return $object$$};CharbaJsHelper.has=function($obj$$,$key$$){return Reflect.has($obj$$,$key$$)};CharbaJsHelper.remove=function($obj$$,$key$$){Reflect.deleteProperty($obj$$,$key$$)};CharbaJsHelper.typeOf=function($obj$$){return typeof $obj$$};CharbaJsHelper.type=function($obj$$,$key$$){return typeof $obj$$[$key$$]};",
		"CharbaJsHelper.isArray=function($obj$$,$key$$){return Array.isArray($obj$$[$key$$])};CharbaJsHelper.newCallbackProxy=function(){var $obj$$=Object.create(CharbaCallbackProxy);$obj$$.callback=null;$obj$$.proxy=function(){if(\"function\"===typeof $obj$$.callback){if($obj$$.ignoreFunctionContext)return $obj$$.callback.apply(this,arguments);var $args$$=[this].concat(Array.prototype.slice.call(arguments));return $obj$$.callback.apply(this,$args$$)}};return $obj$$};",
		"CharbaJsHelper.isCanvasPattern=function($obj$$){return $obj$$ instanceof CanvasPattern};CharbaJsHelper.isCanvasGradient=function($obj$$){return $obj$$ instanceof CanvasGradient};CharbaJsHelper.isCanvas=function($obj$$,$key$$){return $obj$$[$key$$]instanceof HTMLCanvasElement};CharbaJsHelper.isImage=function($obj$$,$key$$){return $obj$$[$key$$]instanceof HTMLImageElement};function CharbaJsPluginHelper(){}CharbaJsPluginHelper.register=function($obj$$){Chart.register($obj$$)};",
		"CharbaJsPluginHelper.unregister=function($obj$$){Chart.registry.remove([$obj$$])};CharbaJsPluginHelper.getAll=function(){return Chart.registry.plugins.items};function CharbaJsControllerHelper(){}CharbaJsControllerHelper.register=function($controllerType$$,$chartType$$,$obj$$){\"undefined\"===typeof CharbaJsControllerHelper.wrappers&&Object.defineProperty(CharbaJsControllerHelper,\"wrappers\",{value:{},configurable:!1,enumerable:!1,writable:!1});CharbaJsControllerHelper.wrappers[$controllerType$$]=$obj$$};",
		"CharbaJsControllerHelper.initialize=function($controllerType$$,$context$$){Chart.controllers[$controllerType$$].prototype.initialize.call($context$$)};CharbaJsControllerHelper.parse=function($controllerType$$,$context$$,$start$$,$count$$){Chart.controllers[$controllerType$$].prototype.parse.call($context$$,$start$$,$count$$)};CharbaJsControllerHelper.draw=function($controllerType$$,$context$$){Chart.controllers[$controllerType$$].prototype.draw.call($context$$)};",
		"CharbaJsControllerHelper.update=function($controllerType$$,$context$$,$mode$$){Chart.controllers[$controllerType$$].prototype.update.call($context$$,$mode$$)};CharbaJsControllerHelper.linkScales=function($controllerType$$,$context$$){Chart.controllers[$controllerType$$].prototype.linkScales.call($context$$)};function CharbaJsPositionerHelper(){}CharbaJsPositionerHelper.register=function($name$$,$module$$){null!=$module$$&&\"function\"===typeof $module$$&&(Chart.Tooltip.positioners[$name$$]=$module$$)};",
		"CharbaJsPositionerHelper.unregister=function($name$$){var $positioners$$=Chart.Tooltip.positioners;\"undefined\"!=$positioners$$[$name$$]&&delete $positioners$$[$name$$]};",
		"CharbaJsPositionerHelper.invoke=function($name$jscomp$75_result$$,$context$$,$elements$$,$eventPoint$$){var $positioners$$=Chart.Tooltip.positioners;return\"undefined\"!=$positioners$$[$name$jscomp$75_result$$]&&($name$jscomp$75_result$$=$positioners$$[$name$jscomp$75_result$$].apply($context$$._chart.tooltip,[$elements$$,$eventPoint$$]),\"object\"===typeof $name$jscomp$75_result$$)?$name$jscomp$75_result$$:null};function CharbaJsChartHelper(){}",
		"CharbaJsChartHelper.generateDefaultLabels=function($chart$$,$options$$){return null!=$options$$&&\"object\"===typeof $options$$.plugins&&\"object\"===typeof $options$$.plugins.legend&&\"object\"===typeof $options$$.plugins.legend.labels&&\"function\"===typeof $options$$.plugins.legend.labels.generateLabels?$options$$.plugins.legend.labels.generateLabels.call($chart$$,$chart$$):null};",
		"CharbaJsChartHelper.invokeDefaultLegendEvent=function($options$$,$key$$,$chart$$,$event$$,$item$$){null!=$options$$&&\"object\"===typeof $options$$.plugins&&\"object\"===typeof $options$$.plugins.legend&&\"function\"===typeof $options$$.plugins.legend[$key$$]&&$options$$.plugins.legend[$key$$].call($chart$$,$event$$,$item$$,$chart$$.legend)};",
		"CharbaJsChartHelper.invokeDefaultChartEvent=function($options$$,$key$$,$chart$$,$event$$,$items$$){null!=$options$$&&\"function\"===typeof $options$$[$key$$]&&$options$$[$key$$].call($chart$$,$event$$,$items$$,$chart$$)};CharbaJsChartHelper.setTooltipActiveElements=function($chart$$,$items$$,$point$$){null!=$chart$$&&null!=$chart$$.tooltip&&$chart$$.tooltip.setActiveElements($items$$,$point$$)};",
		"CharbaJsChartHelper.getTooltipActiveElements=function($chart$$){return null!=$chart$$&&null!=$chart$$.tooltip?$chart$$.tooltip.getActiveElements():[]};CharbaJsChartHelper.getSubtitle=function($chart$$){return null!=$chart$$&&null!=$chart$$.titleBlock&&null!=$chart$$.boxes?$chart$$.boxes.filter(function($box$$){return $box$$.constructor.name===$chart$$.titleBlock.constructor.name&&$box$$!==$chart$$.titleBlock}).pop():null};function CharbaJsZoomHelper(){}",
		"CharbaJsZoomHelper.getZoomLevel=function($chart$$){return null!=$chart$$&&\"function\"===typeof $chart$$.getZoomLevel?$chart$$.getZoomLevel.call($chart$$):NaN};CharbaJsZoomHelper.resetZoom=function($chart$$,$mode$$){null!=$chart$$&&\"function\"===typeof $chart$$.resetZoom&&$chart$$.resetZoom.call($chart$$,$mode$$)};CharbaJsZoomHelper.pan=function($chart$$,$amount$$,$mode$$){null!=$chart$$&&\"function\"===typeof $chart$$.pan&&$chart$$.pan.call($chart$$,$amount$$,void 0,$mode$$)};",
		"CharbaJsZoomHelper.zoom=function($chart$$,$amount$$,$mode$$){null!=$chart$$&&\"function\"===typeof $chart$$.zoom&&$chart$$.zoom.call($chart$$,$amount$$,$mode$$)};CharbaJsZoomHelper.zoomScale=function($chart$$,$scaleId$$,$range$$,$mode$$){null!=$chart$$&&\"function\"===typeof $chart$$.zoomScale&&$chart$$.zoomScale.call($chart$$,$scaleId$$,$range$$,$mode$$)};",
		"CharbaJsZoomHelper.isZoomedOrPanned=function($chart$$){return null!=$chart$$&&\"function\"===typeof $chart$$.isZoomedOrPanned?$chart$$.isZoomedOrPanned.call($chart$$):!1};function CharbaJsAnnotationHelper(){}CharbaJsAnnotationHelper.getCenterPoint=function($element$$,$useFinalPosition$$){return null!=$element$$?$element$$.getCenterPoint($useFinalPosition$$):null};",
		"CharbaJsAnnotationHelper.inRange=function($element$$,$x$$,$y$$,$useFinalPosition$$){return null!=$element$$?$element$$.inRange($x$$,$y$$,$useFinalPosition$$):!1};function CharbaJsDataLabelsHelper(){}CharbaJsDataLabelsHelper.register=function(){Chart.register(ChartDataLabels)};function CharbaJsGradientHelper(){}CharbaJsGradientHelper.register=function(){Chart.register(window[\"chartjs-plugin-gradient\"])};function CharbaJsItemsHelper(){}",
		"CharbaJsItemsHelper.isCanvasPattern=function($obj$$,$key$$){return $obj$$[$key$$]instanceof CanvasPattern};CharbaJsItemsHelper.isCanvasGradient=function($obj$$,$key$$){return $obj$$[$key$$]instanceof CanvasGradient};CharbaJsItemsHelper.getDecimalForPixel=function($obj$$,$pixel$$){return $obj$$.getDecimalForPixel($pixel$$)};CharbaJsItemsHelper.getPixelForDecimal=function($obj$$,$decimal$$){return $obj$$.getPixelForDecimal($decimal$$)};CharbaJsItemsHelper.getPixelForTick=function($obj$$,$index$$){return $obj$$.getPixelForTick($index$$)};",
		"CharbaJsItemsHelper.getLabelForValue=function($obj$$,$value$$){return $obj$$.getLabelForValue($value$$)};CharbaJsItemsHelper.getPixelForStringValue=function($obj$$,$value$$,$index$$){return $obj$$.getPixelForValue($value$$,$index$$)};CharbaJsItemsHelper.getPixelForValue=function($obj$$,$value$$,$index$$){return $obj$$.getPixelForValue($value$$,$index$$)};CharbaJsItemsHelper.getValueForPixel=function($obj$$,$pixel$$){return $obj$$.getValueForPixel($pixel$$)};CharbaJsItemsHelper.getBaseValue=function($obj$$){return $obj$$.getBaseValue()};",
		"CharbaJsItemsHelper.getBasePixel=function($obj$$){return $obj$$.getBasePixel()};CharbaJsItemsHelper.getDistanceFromCenterForValue=function($obj$$,$value$$){return $obj$$.getDistanceFromCenterForValue($value$$)};CharbaJsItemsHelper.getValueForDistanceFromCenter=function($obj$$,$value$$){return $obj$$.getValueForDistanceFromCenter($value$$)};CharbaJsItemsHelper.isHorizontal=function($obj$$){return $obj$$.isHorizontal()};CharbaJsItemsHelper.getDatasetControllerStyle=function($controller$$,$dataIndex$$){return $controller$$.getStyle($dataIndex$$)};",
		"function CharbaJsDateAdapterHelper(){}CharbaJsDateAdapterHelper.create=function($options$$){return new Chart._adapters._date($options$$)};CharbaJsDateAdapterHelper.getEpochByWeek=function($weekYear$$,$weekNumber$$,$options$$){return luxon.DateTime.fromObject({weekNumber:$weekNumber$$,weekYear:$weekYear$$},$options$$).toMillis()};CharbaJsDateAdapterHelper.getEpochByOrdinal=function($year$$,$ordinal$$,$options$$){return luxon.DateTime.fromObject({ordinal:$ordinal$$,year:$year$$},$options$$).toMillis()};",
		"function CharbaJsGeoHelper(){}CharbaJsGeoHelper.features=function($parsedFeatures_topojson$$,$featureProperty$$){return\"undefined\"!==typeof $parsedFeatures_topojson$$.objects&&\"undefined\"!==typeof $parsedFeatures_topojson$$.objects[$featureProperty$$]&&($parsedFeatures_topojson$$=ChartGeo.topojson.feature($parsedFeatures_topojson$$,$parsedFeatures_topojson$$.objects[$featureProperty$$]),null!=$parsedFeatures_topojson$$)?$parsedFeatures_topojson$$.features:null};",
		"CharbaJsGeoHelper.projection=function($chart$$,$latitude$$,$longitude$$){return $chart$$.scales.xy.projection([$longitude$$,$latitude$$])};CharbaJsGeoHelper.invert=function($chart$$,$projection_x$$,$y$$){return($projection_x$$=$chart$$.scales.xy.geoPath.projection())&&\"function\"===typeof $projection_x$$.invert?$chart$$.scales.xy.geoPath.projection().invert([p[0],p[1]]):null};CharbaJsGeoHelper.getColorForValue=function($chart$$,$value$$){return $chart$$.scales.color.getColorForValue($value$$)};",
		"CharbaJsGeoHelper.getSizeForValue=function($chart$$,$value$$){return $chart$$.scales.r.getSizeForValue($value$$)};function CharbaJsMLHelper(){}CharbaJsMLHelper.predict=function($regression$$,$values$$){return $regression$$.predict($values$$)};CharbaJsMLHelper.toFormula=function($regression$$,$precision$$){return $regression$$.toString($precision$$)};"
	};
	
	/**
	 * Creates the injectable resource with <code>charba.helper.min.js</code> content.
	 */
	JsHelperResource() {
		super(ResourceName.CHARBA_HELPER, CONTENT);
	}

}
