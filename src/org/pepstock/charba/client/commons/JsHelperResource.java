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
public final class JsHelperResource extends AbstractInjectableResource {

	// encoded javascript content of charba.helper.min.js
	private static final String[] CONTENT = {
			"var CharbaCallbackProxy={callback:null,proxy:null,ignoreFunctionContext:!0};function CharbaJsObjectArrayHelper(){}CharbaJsObjectArrayHelper.set=function($obj$$,$key$$,$value$$){$obj$$[$key$$]=$value$$};CharbaJsObjectArrayHelper.get=function($obj$$,$key$$){return $obj$$[$key$$]};function CharbaJsObjectBooleanHelper(){}CharbaJsObjectBooleanHelper.set=function($obj$$,$key$$,$value$$){$obj$$[$key$$]=$value$$};CharbaJsObjectBooleanHelper.get=function($obj$$,$key$$){return $obj$$[$key$$]};",
			"function CharbaJsObjectCallbackProxyHelper(){}CharbaJsObjectCallbackProxyHelper.set=function($obj$$,$key$$,$value$$){$obj$$[$key$$]=$value$$};CharbaJsObjectCallbackProxyHelper.get=function($obj$$,$key$$){return $obj$$[$key$$]};function CharbaJsObjectCallbackHelper(){}CharbaJsObjectCallbackHelper.set=function($obj$$,$key$$,$value$$){$obj$$[$key$$]=$value$$};CharbaJsObjectCallbackHelper.get=function($obj$$,$key$$){return $obj$$[$key$$]};function CharbaJsObjectChartHelper(){}",
			"CharbaJsObjectChartHelper.set=function($obj$$,$key$$,$value$$){$obj$$[$key$$]=$value$$};CharbaJsObjectChartHelper.get=function($obj$$,$key$$){return $obj$$[$key$$]};function CharbaJsObjectDoubleHelper(){}CharbaJsObjectDoubleHelper.set=function($obj$$,$key$$,$value$$){$obj$$[$key$$]=$value$$};CharbaJsObjectDoubleHelper.get=function($obj$$,$key$$){return $obj$$[$key$$]};function CharbaJsObjectIntegerHelper(){}CharbaJsObjectIntegerHelper.set=function($obj$$,$key$$,$value$$){$obj$$[$key$$]=$value$$};",
			"CharbaJsObjectIntegerHelper.get=function($obj$$,$key$$){return $obj$$[$key$$]};function CharbaJsObjectGradientHelper(){}CharbaJsObjectGradientHelper.set=function($obj$$,$key$$,$value$$){$obj$$[$key$$]=$value$$};CharbaJsObjectGradientHelper.get=function($obj$$,$key$$){return $obj$$[$key$$]};function CharbaJsObjectImageHelper(){}CharbaJsObjectImageHelper.set=function($obj$$,$key$$,$value$$){$obj$$[$key$$]=$value$$};CharbaJsObjectImageHelper.get=function($obj$$,$key$$){return $obj$$[$key$$]};",
			"function CharbaJsObjectCanvasHelper(){}CharbaJsObjectCanvasHelper.set=function($obj$$,$key$$,$value$$){$obj$$[$key$$]=$value$$};CharbaJsObjectCanvasHelper.get=function($obj$$,$key$$){return $obj$$[$key$$]};function CharbaJsObjectNativeObjectHelper(){}CharbaJsObjectNativeObjectHelper.set=function($obj$$,$key$$,$value$$){$obj$$[$key$$]=$value$$};CharbaJsObjectNativeObjectHelper.get=function($obj$$,$key$$){return $obj$$[$key$$]};function CharbaJsObjectNativeEventHelper(){}",
			"CharbaJsObjectNativeEventHelper.set=function($obj$$,$key$$,$value$$){$obj$$[$key$$]=$value$$};CharbaJsObjectNativeEventHelper.get=function($obj$$,$key$$){return $obj$$[$key$$]};function CharbaJsObjectPatternHelper(){}CharbaJsObjectPatternHelper.set=function($obj$$,$key$$,$value$$){$obj$$[$key$$]=$value$$};CharbaJsObjectPatternHelper.get=function($obj$$,$key$$){return $obj$$[$key$$]};function CharbaJsObjectStringHelper(){}",
			"CharbaJsObjectStringHelper.set=function($obj$$,$key$$,$value$$){$obj$$[$key$$]=$value$$};CharbaJsObjectStringHelper.get=function($obj$$,$key$$){return $obj$$[$key$$]};function CharbaJsHelper(){}CharbaJsHelper.create=function(){return{}};CharbaJsHelper.cast=function($object$$){return $object$$};CharbaJsHelper.has=function($obj$$,$key$$){return $key$$ in $obj$$};CharbaJsHelper.remove=function($obj$$,$key$$){delete $obj$$[$key$$]};CharbaJsHelper.typeOf=function($obj$$){return typeof $obj$$};",
			"CharbaJsHelper.type=function($obj$$,$key$$){return typeof $obj$$[$key$$]};CharbaJsHelper.isArray=function($obj$$,$key$$){return Array.isArray($obj$$[$key$$])};",
			"CharbaJsHelper.newCallbackProxy=function(){var $obj$$=Object.create(CharbaCallbackProxy);$obj$$.callback=null;$obj$$.proxy=function(){if(\"function\"===typeof $obj$$.callback){if($obj$$.ignoreFunctionContext)return $obj$$.callback.apply(this,arguments);var $args$$=[this].concat(Array.prototype.slice.call(arguments));return $obj$$.callback.apply(this,$args$$)}};return $obj$$};CharbaJsHelper.isCanvasPattern=function($obj$$){return $obj$$ instanceof CanvasPattern};",
			"CharbaJsHelper.isCanvasGradient=function($obj$$){return $obj$$ instanceof CanvasGradient};CharbaJsHelper.isCanvas=function($obj$$,$key$$){return $obj$$[$key$$]instanceof HTMLCanvasElement};CharbaJsHelper.isImage=function($obj$$,$key$$){return $obj$$[$key$$]instanceof HTMLImageElement};function CharbaJsPluginHelper(){}CharbaJsPluginHelper.register=function($obj$$){Chart.register($obj$$)};CharbaJsPluginHelper.unregister=function($obj$$){Chart.registry.remove([$obj$$])};CharbaJsPluginHelper.getAll=function(){return Chart.registry.plugins.items};",
			"function CharbaJsControllerHelper(){}CharbaJsControllerHelper.register=function($controllerType$$,$chartType$$,$obj$$){\"undefined\"===typeof CharbaJsControllerHelper.wrappers&&Object.defineProperty(CharbaJsControllerHelper,\"wrappers\",{value:{},configurable:!1,enumerable:!1,writable:!1});CharbaJsControllerHelper.wrappers[$controllerType$$]=$obj$$};CharbaJsControllerHelper.initialize=function($controllerType$$,$context$$){Chart.controllers[$controllerType$$].prototype.initialize.call($context$$)};",
			"CharbaJsControllerHelper.parse=function($controllerType$$,$context$$,$start$$,$count$$){Chart.controllers[$controllerType$$].prototype.parse.call($context$$,$start$$,$count$$)};CharbaJsControllerHelper.draw=function($controllerType$$,$context$$){Chart.controllers[$controllerType$$].prototype.draw.call($context$$)};CharbaJsControllerHelper.update=function($controllerType$$,$context$$,$mode$$){Chart.controllers[$controllerType$$].prototype.update.call($context$$,$mode$$)};",
			"CharbaJsControllerHelper.linkScales=function($controllerType$$,$context$$){Chart.controllers[$controllerType$$].prototype.linkScales.call($context$$)};function CharbaJsPositionerHelper(){}CharbaJsPositionerHelper.register=function($name$$,$module$$){null!=$module$$&&\"function\"===typeof $module$$&&(Chart.registry.getPlugin(\"tooltip\").positioners[$name$$]=$module$$)};",
			"CharbaJsPositionerHelper.unregister=function($name$$){var $tooltipPlugin$$=Chart.registry.getPlugin(\"tooltip\");\"undefined\"!=$tooltipPlugin$$.positioners[$name$$]&&delete $tooltipPlugin$$.positioners[$name$$]};CharbaJsPositionerHelper.invoke=function($name$$,$context$$,$elements$$,$eventPoint$$){var $tooltipPlugin$$=Chart.registry.getPlugin(\"tooltip\");return\"undefined\"!=$tooltipPlugin$$.positioners[$name$$]?$tooltipPlugin$$.positioners[$name$$].apply($context$$,[$elements$$,$eventPoint$$]):null};",
			"function CharbaJsChartHelper(){}CharbaJsChartHelper.generateDefaultLabels=function($chart$$,$options$$){return null!=$options$$&&\"object\"===typeof $options$$.plugins&&\"object\"===typeof $options$$.plugins.legend&&\"object\"===typeof $options$$.plugins.legend.labels&&\"function\"===typeof $options$$.plugins.legend.labels.generateLabels?$options$$.plugins.legend.labels.generateLabels.call($chart$$,$chart$$):null};",
			"CharbaJsChartHelper.invokeDefaultLegendEvent=function($options$$,$key$$,$chart$$,$event$$,$item$$){null!=$options$$&&\"object\"===typeof $options$$.plugins&&\"object\"===typeof $options$$.plugins.legend&&\"function\"===typeof $options$$.plugins.legend[$key$$]&&$options$$.plugins.legend[$key$$].call($chart$$,$event$$,$item$$,$chart$$.legend)};",
			"CharbaJsChartHelper.invokeDefaultChartEvent=function($options$$,$key$$,$chart$$,$event$$,$items$$){null!=$options$$&&\"function\"===typeof $options$$[$key$$]&&$options$$[$key$$].call($chart$$,$event$$,$items$$,$chart$$)};CharbaJsChartHelper.setTooltipActiveElements=function($chart$$,$items$$){null!=$chart$$&&null!=$chart$$.tooltip&&$chart$$.tooltip.setActiveElements($items$$)};",
			"CharbaJsChartHelper.getTooltipActiveElements=function($chart$$){return null!=$chart$$&&null!=$chart$$.tooltip?$chart$$.tooltip.getActiveElements():[]};CharbaJsChartHelper.getSubtitle=function($chart$$){return null!=$chart$$&&null!=$chart$$.titleBlock&&null!=$chart$$.boxes?$chart$$.boxes.filter(function($box$$){return $box$$.constructor.name===$chart$$.titleBlock.constructor.name&&$box$$!==$chart$$.titleBlock}).pop():null};function CharbaJsZoomHelper(){}",
			"CharbaJsZoomHelper.getZoomLevel=function($chart$$){return null!=$chart$$&&\"function\"===typeof $chart$$.getZoomLevel?$chart$$.getZoomLevel.call($chart$$):NaN};CharbaJsZoomHelper.resetZoom=function($chart$$,$mode$$){null!=$chart$$&&\"function\"===typeof $chart$$.resetZoom&&$chart$$.resetZoom.call($chart$$,$mode$$)};CharbaJsZoomHelper.pan=function($chart$$,$amount$$,$mode$$){null!=$chart$$&&\"function\"===typeof $chart$$.pan&&$chart$$.pan.call($chart$$,$amount$$,void 0,$mode$$)};",
			"CharbaJsZoomHelper.zoom=function($chart$$,$amount$$,$mode$$){null!=$chart$$&&\"function\"===typeof $chart$$.zoom&&$chart$$.zoom.call($chart$$,$amount$$,$mode$$)};CharbaJsZoomHelper.zoomScale=function($chart$$,$scaleId$$,$range$$,$mode$$){null!=$chart$$&&\"function\"===typeof $chart$$.zoomScale&&$chart$$.zoomScale.call($chart$$,$scaleId$$,$range$$,$mode$$)};function CharbaJsDataLabelsHelper(){}CharbaJsDataLabelsHelper.register=function(){Chart.register(ChartDataLabels)};",
			"function CharbaJsItemsHelper(){}CharbaJsItemsHelper.isCanvasPattern=function($obj$$,$key$$){return $obj$$[$key$$]instanceof CanvasPattern};CharbaJsItemsHelper.isCanvasGradient=function($obj$$,$key$$){return $obj$$[$key$$]instanceof CanvasGradient};CharbaJsItemsHelper.getDecimalForPixel=function($obj$$,$pixel$$){return $obj$$.getDecimalForPixel($pixel$$)};CharbaJsItemsHelper.getPixelForDecimal=function($obj$$,$decimal$$){return $obj$$.getPixelForDecimal($decimal$$)};",
			"CharbaJsItemsHelper.getPixelForTick=function($obj$$,$index$$){return $obj$$.getPixelForTick($index$$)};CharbaJsItemsHelper.getLabelForValue=function($obj$$,$value$$){return $obj$$.getLabelForValue($value$$)};CharbaJsItemsHelper.getPixelForStringValue=function($obj$$,$value$$,$index$$){return $obj$$.getPixelForValue($value$$,$index$$)};CharbaJsItemsHelper.getPixelForValue=function($obj$$,$value$$,$index$$){return $obj$$.getPixelForValue($value$$,$index$$)};",
			"CharbaJsItemsHelper.getValueForPixel=function($obj$$,$pixel$$){return $obj$$.getValueForPixel($pixel$$)};CharbaJsItemsHelper.getBaseValue=function($obj$$){return $obj$$.getBaseValue()};CharbaJsItemsHelper.getBasePixel=function($obj$$){return $obj$$.getBasePixel()};CharbaJsItemsHelper.getDatasetControllerStyle=function($controller$$,$dataIndex$$){return $controller$$.getStyle($dataIndex$$)};function CharbaJsDateAdapterHelper(){}CharbaJsDateAdapterHelper.create=function($options$$){return new Chart._adapters._date($options$$)};",
			"function CharbaJsGeoHelper(){}CharbaJsGeoHelper.features=function($parsedFeatures_topojson$$,$featureProperty$$){return\"undefined\"!==typeof $parsedFeatures_topojson$$.objects&&\"undefined\"!==typeof $parsedFeatures_topojson$$.objects[$featureProperty$$]&&($parsedFeatures_topojson$$=ChartGeo.topojson.feature($parsedFeatures_topojson$$,$parsedFeatures_topojson$$.objects[$featureProperty$$]),null!=$parsedFeatures_topojson$$)?$parsedFeatures_topojson$$.features:null};" };

	/**
	 * Creates the injectable resource with <code>charba.helper.min.js</code> content.
	 */
	JsHelperResource() {
		super(ResourceName.CHARBA_HELPER, CONTENT);
	}

}
