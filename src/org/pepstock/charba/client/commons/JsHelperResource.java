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
		"var CharbaCallbackProxy={callback:null,proxy:null,ignoreFunctionContext:!0};function CharbaJsObjectArrayHelper(){}CharbaJsObjectArrayHelper.set=function $CharbaJsObjectArrayHelper$set$($obj$$,$key$$,$value$$){$obj$$[$key$$]=$value$$};CharbaJsObjectArrayHelper.get=function $CharbaJsObjectArrayHelper$get$($obj$$,$key$$){return $obj$$[$key$$]};function CharbaJsObjectBooleanHelper(){}CharbaJsObjectBooleanHelper.set=function $CharbaJsObjectBooleanHelper$set$($obj$$,$key$$,$value$$){$obj$$[$key$$]=$value$$};",
		"CharbaJsObjectBooleanHelper.get=function $CharbaJsObjectBooleanHelper$get$($obj$$,$key$$){return $obj$$[$key$$]};function CharbaJsObjectCallbackProxyHelper(){}CharbaJsObjectCallbackProxyHelper.set=function $CharbaJsObjectCallbackProxyHelper$set$($obj$$,$key$$,$value$$){$obj$$[$key$$]=$value$$};CharbaJsObjectCallbackProxyHelper.get=function $CharbaJsObjectCallbackProxyHelper$get$($obj$$,$key$$){return $obj$$[$key$$]};function CharbaJsObjectCallbackHelper(){}",
		"CharbaJsObjectCallbackHelper.set=function $CharbaJsObjectCallbackHelper$set$($obj$$,$key$$,$value$$){$obj$$[$key$$]=$value$$};CharbaJsObjectCallbackHelper.get=function $CharbaJsObjectCallbackHelper$get$($obj$$,$key$$){return $obj$$[$key$$]};function CharbaJsObjectChartHelper(){}CharbaJsObjectChartHelper.set=function $CharbaJsObjectChartHelper$set$($obj$$,$key$$,$value$$){$obj$$[$key$$]=$value$$};CharbaJsObjectChartHelper.get=function $CharbaJsObjectChartHelper$get$($obj$$,$key$$){return $obj$$[$key$$]};",
		"function CharbaJsObjectElementHelper(){}CharbaJsObjectElementHelper.set=function $CharbaJsObjectElementHelper$set$($obj$$,$key$$,$value$$){$obj$$[$key$$]=$value$$};CharbaJsObjectElementHelper.get=function $CharbaJsObjectElementHelper$get$($obj$$,$key$$){return $obj$$[$key$$]};function CharbaJsObjectDoubleHelper(){}CharbaJsObjectDoubleHelper.set=function $CharbaJsObjectDoubleHelper$set$($obj$$,$key$$,$value$$){$obj$$[$key$$]=$value$$};",
		"CharbaJsObjectDoubleHelper.get=function $CharbaJsObjectDoubleHelper$get$($obj$$,$key$$){return $obj$$[$key$$]};function CharbaJsObjectIntegerHelper(){}CharbaJsObjectIntegerHelper.set=function $CharbaJsObjectIntegerHelper$set$($obj$$,$key$$,$value$$){$obj$$[$key$$]=$value$$};CharbaJsObjectIntegerHelper.get=function $CharbaJsObjectIntegerHelper$get$($obj$$,$key$$){return $obj$$[$key$$]};function CharbaJsObjectGradientHelper(){}",
		"CharbaJsObjectGradientHelper.set=function $CharbaJsObjectGradientHelper$set$($obj$$,$key$$,$value$$){$obj$$[$key$$]=$value$$};CharbaJsObjectGradientHelper.get=function $CharbaJsObjectGradientHelper$get$($obj$$,$key$$){return $obj$$[$key$$]};function CharbaJsObjectImageHelper(){}CharbaJsObjectImageHelper.set=function $CharbaJsObjectImageHelper$set$($obj$$,$key$$,$value$$){$obj$$[$key$$]=$value$$};CharbaJsObjectImageHelper.get=function $CharbaJsObjectImageHelper$get$($obj$$,$key$$){return $obj$$[$key$$]};",
		"function CharbaJsObjectCanvasHelper(){}CharbaJsObjectCanvasHelper.set=function $CharbaJsObjectCanvasHelper$set$($obj$$,$key$$,$value$$){$obj$$[$key$$]=$value$$};CharbaJsObjectCanvasHelper.get=function $CharbaJsObjectCanvasHelper$get$($obj$$,$key$$){return $obj$$[$key$$]};function CharbaJsObjectNativeObjectHelper(){}CharbaJsObjectNativeObjectHelper.set=function $CharbaJsObjectNativeObjectHelper$set$($obj$$,$key$$,$value$$){$obj$$[$key$$]=$value$$};",
		"CharbaJsObjectNativeObjectHelper.get=function $CharbaJsObjectNativeObjectHelper$get$($obj$$,$key$$){return $obj$$[$key$$]};function CharbaJsObjectNativeEventHelper(){}CharbaJsObjectNativeEventHelper.set=function $CharbaJsObjectNativeEventHelper$set$($obj$$,$key$$,$value$$){$obj$$[$key$$]=$value$$};CharbaJsObjectNativeEventHelper.get=function $CharbaJsObjectNativeEventHelper$get$($obj$$,$key$$){return $obj$$[$key$$]};function CharbaJsObjectPatternHelper(){}",
		"CharbaJsObjectPatternHelper.set=function $CharbaJsObjectPatternHelper$set$($obj$$,$key$$,$value$$){$obj$$[$key$$]=$value$$};CharbaJsObjectPatternHelper.get=function $CharbaJsObjectPatternHelper$get$($obj$$,$key$$){return $obj$$[$key$$]};function CharbaJsObjectStringHelper(){}CharbaJsObjectStringHelper.set=function $CharbaJsObjectStringHelper$set$($obj$$,$key$$,$value$$){$obj$$[$key$$]=$value$$};CharbaJsObjectStringHelper.get=function $CharbaJsObjectStringHelper$get$($obj$$,$key$$){return $obj$$[$key$$]};",
		"function CharbaJsHelper(){}CharbaJsHelper.create=function $CharbaJsHelper$create$(){return{}};CharbaJsHelper.cast=function $CharbaJsHelper$cast$($object$$){return $object$$};CharbaJsHelper.has=function $CharbaJsHelper$has$($obj$$,$key$$){return $key$$ in $obj$$};CharbaJsHelper.remove=function $CharbaJsHelper$remove$($obj$$,$key$$){delete $obj$$[$key$$]};CharbaJsHelper.typeOf=function $CharbaJsHelper$typeOf$($obj$$){return typeof $obj$$};CharbaJsHelper.type=function $CharbaJsHelper$type$($obj$$,$key$$){return typeof $obj$$[$key$$]};",
		"CharbaJsHelper.isArray=function $CharbaJsHelper$isArray$($obj$$,$key$$){return Array.isArray($obj$$[$key$$])};",
		"CharbaJsHelper.newCallbackProxy=function $CharbaJsHelper$newCallbackProxy$(){var $obj$$=Object.create(CharbaCallbackProxy);$obj$$.callback=null;$obj$$.proxy=function $$obj$$$proxy$(){if(\"function\"===typeof $obj$$.callback){if($obj$$.ignoreFunctionContext)return $obj$$.callback.apply(this,arguments);var $args$$=Array.of(this).concat(Array.prototype.slice.call(arguments));return $obj$$.callback.apply(this,$args$$)}};return $obj$$};",
		"CharbaJsHelper.isCanvasPattern=function $CharbaJsHelper$isCanvasPattern$($obj$$){return $obj$$ instanceof CanvasPattern};CharbaJsHelper.isCanvasGradient=function $CharbaJsHelper$isCanvasGradient$($obj$$){return $obj$$ instanceof CanvasGradient};CharbaJsHelper.isCanvas=function $CharbaJsHelper$isCanvas$($obj$$,$key$$){return $obj$$[$key$$]instanceof HTMLCanvasElement};CharbaJsHelper.isImage=function $CharbaJsHelper$isImage$($obj$$,$key$$){return $obj$$[$key$$]instanceof HTMLImageElement};",
		"function CharbaJsPluginHelper(){}CharbaJsPluginHelper.register=function $CharbaJsPluginHelper$register$($obj$$){Chart.register($obj$$)};CharbaJsPluginHelper.unregister=function $CharbaJsPluginHelper$unregister$($obj$$){Chart.registry.remove([$obj$$])};CharbaJsPluginHelper.getAll=function $CharbaJsPluginHelper$getAll$(){return Chart.registry.plugins.items};function CharbaJsControllerHelper(){}",
		"CharbaJsControllerHelper.register=function $CharbaJsControllerHelper$register$($controllerType$$,$chartType$$,$obj$$){\"undefined\"===typeof CharbaJsControllerHelper.wrappers&&Object.defineProperty(CharbaJsControllerHelper,\"wrappers\",{value:{},configurable:!1,enumerable:!1,writable:!1});CharbaJsControllerHelper.wrappers[$controllerType$$]=$obj$$};CharbaJsControllerHelper.initialize=function $CharbaJsControllerHelper$initialize$($controllerType$$,$context$$){Chart.controllers[$controllerType$$].prototype.initialize.call($context$$)};",
		"CharbaJsControllerHelper.parse=function $CharbaJsControllerHelper$parse$($controllerType$$,$context$$,$start$$,$count$$){Chart.controllers[$controllerType$$].prototype.parse.call($context$$,$start$$,$count$$)};CharbaJsControllerHelper.draw=function $CharbaJsControllerHelper$draw$($controllerType$$,$context$$){Chart.controllers[$controllerType$$].prototype.draw.call($context$$)};",
		"CharbaJsControllerHelper.update=function $CharbaJsControllerHelper$update$($controllerType$$,$context$$,$mode$$){Chart.controllers[$controllerType$$].prototype.update.call($context$$,$mode$$)};CharbaJsControllerHelper.linkScales=function $CharbaJsControllerHelper$linkScales$($controllerType$$,$context$$){Chart.controllers[$controllerType$$].prototype.linkScales.call($context$$)};function CharbaJsPositionerHelper(){}",
		"CharbaJsPositionerHelper.register=function $CharbaJsPositionerHelper$register$($name$$,$module$$){null!=$module$$&&\"function\"===typeof $module$$&&(Chart.Tooltip.positioners[$name$$]=$module$$)};CharbaJsPositionerHelper.unregister=function $CharbaJsPositionerHelper$unregister$($name$$){var $positioners$$=Chart.Tooltip.positioners;\"undefined\"!=$positioners$$[$name$$]&&delete $positioners$$[$name$$]};",
		"CharbaJsPositionerHelper.invoke=function $CharbaJsPositionerHelper$invoke$($name$$,$context$$,$elements$$,$eventPoint$$){var $positioners$$=Chart.Tooltip.positioners;return\"undefined\"!=$positioners$$[$name$$]?$positioners$$[$name$$].apply($context$$,Array.of($elements$$,$eventPoint$$)):null};function CharbaJsChartHelper(){}",
		"CharbaJsChartHelper.generateDefaultLabels=function $CharbaJsChartHelper$generateDefaultLabels$($chart$$,$options$$){return null!=$options$$&&\"object\"===typeof $options$$.plugins&&\"object\"===typeof $options$$.plugins.legend&&\"object\"===typeof $options$$.plugins.legend.labels&&\"function\"===typeof $options$$.plugins.legend.labels.generateLabels?$options$$.plugins.legend.labels.generateLabels.call($chart$$,$chart$$):null};",
		"CharbaJsChartHelper.invokeDefaultLegendEvent=function $CharbaJsChartHelper$invokeDefaultLegendEvent$($options$$,$key$$,$chart$$,$event$$,$item$$){null!=$options$$&&\"object\"===typeof $options$$.plugins&&\"object\"===typeof $options$$.plugins.legend&&\"function\"===typeof $options$$.plugins.legend[$key$$]&&$options$$.plugins.legend[$key$$].call($chart$$,$event$$,$item$$,$chart$$.legend)};",
		"CharbaJsChartHelper.invokeDefaultChartEvent=function $CharbaJsChartHelper$invokeDefaultChartEvent$($options$$,$key$$,$chart$$,$event$$,$items$$){null!=$options$$&&\"function\"===typeof $options$$[$key$$]&&$options$$[$key$$].call($chart$$,$event$$,$items$$,$chart$$)};CharbaJsChartHelper.setTooltipActiveElements=function $CharbaJsChartHelper$setTooltipActiveElements$($chart$$,$items$$){null!=$chart$$&&null!=$chart$$.tooltip&&$chart$$.tooltip.setActiveElements($items$$)};",
		"CharbaJsChartHelper.getTooltipActiveElements=function $CharbaJsChartHelper$getTooltipActiveElements$($chart$$){return null!=$chart$$&&null!=$chart$$.tooltip?$chart$$.tooltip.getActiveElements():[]};",
		"CharbaJsChartHelper.getSubtitle=function $CharbaJsChartHelper$getSubtitle$($chart$$){return null!=$chart$$&&null!=$chart$$.titleBlock&&null!=$chart$$.boxes?$chart$$.boxes.filter(function($box$$){return $box$$.constructor.name===$chart$$.titleBlock.constructor.name&&$box$$!==$chart$$.titleBlock}).pop():null};function CharbaJsZoomHelper(){}",
		"CharbaJsZoomHelper.getZoomLevel=function $CharbaJsZoomHelper$getZoomLevel$($chart$$){return null!=$chart$$&&\"function\"===typeof $chart$$.getZoomLevel?$chart$$.getZoomLevel.call($chart$$):NaN};CharbaJsZoomHelper.resetZoom=function $CharbaJsZoomHelper$resetZoom$($chart$$,$mode$$){null!=$chart$$&&\"function\"===typeof $chart$$.resetZoom&&$chart$$.resetZoom.call($chart$$,$mode$$)};",
		"CharbaJsZoomHelper.pan=function $CharbaJsZoomHelper$pan$($chart$$,$amount$$,$mode$$){null!=$chart$$&&\"function\"===typeof $chart$$.pan&&$chart$$.pan.call($chart$$,$amount$$,void 0,$mode$$)};CharbaJsZoomHelper.zoom=function $CharbaJsZoomHelper$zoom$($chart$$,$amount$$,$mode$$){null!=$chart$$&&\"function\"===typeof $chart$$.zoom&&$chart$$.zoom.call($chart$$,$amount$$,$mode$$)};",
		"CharbaJsZoomHelper.zoomScale=function $CharbaJsZoomHelper$zoomScale$($chart$$,$scaleId$$,$range$$,$mode$$){null!=$chart$$&&\"function\"===typeof $chart$$.zoomScale&&$chart$$.zoomScale.call($chart$$,$scaleId$$,$range$$,$mode$$)};CharbaJsZoomHelper.isZoomedOrPanned=function $CharbaJsZoomHelper$isZoomedOrPanned$($chart$$){return null!=$chart$$&&\"function\"===typeof $chart$$.isZoomedOrPanned?$chart$$.isZoomedOrPanned.call($chart$$):!1};function CharbaJsAnnotationHelper(){}",
		"CharbaJsAnnotationHelper.getCenterPoint=function $CharbaJsAnnotationHelper$getCenterPoint$($element$$,$useFinalPosition$$){return null!=$element$$?$element$$.getCenterPoint($useFinalPosition$$):null};CharbaJsAnnotationHelper.inRange=function $CharbaJsAnnotationHelper$inRange$($element$$,$x$$,$y$$,$useFinalPosition$$){return null!=$element$$?$element$$.inRange($x$$,$y$$,$useFinalPosition$$):!1};function CharbaJsDataLabelsHelper(){}CharbaJsDataLabelsHelper.register=function $CharbaJsDataLabelsHelper$register$(){Chart.register(ChartDataLabels)};",
		"function CharbaJsItemsHelper(){}CharbaJsItemsHelper.isCanvasPattern=function $CharbaJsItemsHelper$isCanvasPattern$($obj$$,$key$$){return $obj$$[$key$$]instanceof CanvasPattern};CharbaJsItemsHelper.isCanvasGradient=function $CharbaJsItemsHelper$isCanvasGradient$($obj$$,$key$$){return $obj$$[$key$$]instanceof CanvasGradient};CharbaJsItemsHelper.getDecimalForPixel=function $CharbaJsItemsHelper$getDecimalForPixel$($obj$$,$pixel$$){return $obj$$.getDecimalForPixel($pixel$$)};",
		"CharbaJsItemsHelper.getPixelForDecimal=function $CharbaJsItemsHelper$getPixelForDecimal$($obj$$,$decimal$$){return $obj$$.getPixelForDecimal($decimal$$)};CharbaJsItemsHelper.getPixelForTick=function $CharbaJsItemsHelper$getPixelForTick$($obj$$,$index$$){return $obj$$.getPixelForTick($index$$)};CharbaJsItemsHelper.getLabelForValue=function $CharbaJsItemsHelper$getLabelForValue$($obj$$,$value$$){return $obj$$.getLabelForValue($value$$)};",
		"CharbaJsItemsHelper.getPixelForStringValue=function $CharbaJsItemsHelper$getPixelForStringValue$($obj$$,$value$$,$index$$){return $obj$$.getPixelForValue($value$$,$index$$)};CharbaJsItemsHelper.getPixelForValue=function $CharbaJsItemsHelper$getPixelForValue$($obj$$,$value$$,$index$$){return $obj$$.getPixelForValue($value$$,$index$$)};CharbaJsItemsHelper.getValueForPixel=function $CharbaJsItemsHelper$getValueForPixel$($obj$$,$pixel$$){return $obj$$.getValueForPixel($pixel$$)};",
		"CharbaJsItemsHelper.getBaseValue=function $CharbaJsItemsHelper$getBaseValue$($obj$$){return $obj$$.getBaseValue()};CharbaJsItemsHelper.getBasePixel=function $CharbaJsItemsHelper$getBasePixel$($obj$$){return $obj$$.getBasePixel()};CharbaJsItemsHelper.getDatasetControllerStyle=function $CharbaJsItemsHelper$getDatasetControllerStyle$($controller$$,$dataIndex$$){return $controller$$.getStyle($dataIndex$$)};function CharbaJsDateAdapterHelper(){}CharbaJsDateAdapterHelper.create=function $CharbaJsDateAdapterHelper$create$($options$$){return new Chart._adapters._date($options$$)};",
		"function CharbaJsGeoHelper(){}CharbaJsGeoHelper.features=function $CharbaJsGeoHelper$features$($topojson$$,$featureProperty$$){if(\"undefined\"!==typeof $topojson$$.objects&&\"undefined\"!==typeof $topojson$$.objects[$featureProperty$$]){var $parsedFeatures$$=ChartGeo.topojson.feature($topojson$$,$topojson$$.objects[$featureProperty$$]);if(null!=$parsedFeatures$$)return $parsedFeatures$$.features}return null};",
		"CharbaJsGeoHelper.projection=function $CharbaJsGeoHelper$projection$($chart$$,$latitude$$,$longitude$$){return $chart$$.scales.xy.projection([$longitude$$,$latitude$$])};CharbaJsGeoHelper.invert=function $CharbaJsGeoHelper$invert$($chart$$,$projection_x$$,$y$$){return($projection_x$$=$chart$$.scales.xy.geoPath.projection())&&\"function\"===typeof $projection_x$$.invert?$chart$$.scales.xy.geoPath.projection().invert([p[0],p[1]]):null};",
		"CharbaJsGeoHelper.getColorForValue=function $CharbaJsGeoHelper$getColorForValue$($chart$$,$value$$){return $chart$$.scales.color.getColorForValue($value$$)};CharbaJsGeoHelper.getSizeForValue=function $CharbaJsGeoHelper$getSizeForValue$($chart$$,$value$$){return $chart$$.scales.r.getSizeForValue($value$$)};"
	};
	
	/**
	 * Creates the injectable resource with <code>charba.helper.min.js</code> content.
	 */
	JsHelperResource() {
		super(ResourceName.CHARBA_HELPER, CONTENT);
	}

}
