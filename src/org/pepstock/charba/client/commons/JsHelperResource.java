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
		"var CharbaCallbackProxy={callback:null,proxy:null};function CharbaJsHelper(){}CharbaJsHelper.has=function $CharbaJsHelper$has$($object$$,$key$$){return Object.prototype.hasOwnProperty.call($object$$,$key$$)};CharbaJsHelper.typeOf=function $CharbaJsHelper$typeOf$($obj$$){return typeof $obj$$};CharbaJsHelper.remove=function $CharbaJsHelper$remove$($obj$$,$key$$){delete $obj$$[$key$$]};CharbaJsHelper.type=function $CharbaJsHelper$type$($obj$$,$key$$){return typeof $obj$$[$key$$]};",
		"CharbaJsHelper.isArray=function $CharbaJsHelper$isArray$($obj$$,$key$$){return Array.isArray($obj$$[$key$$])};",
		"CharbaJsHelper.newCallbackProxy=function $CharbaJsHelper$newCallbackProxy$(){var $obj$$=Object.create(CharbaCallbackProxy);$obj$$.callback=null;$obj$$.proxy=function $$obj$$$proxy$(){if(null!=$obj$$.callback&&\"function\"===typeof $obj$$.callback){var $args_result$$=[this].concat(Array.prototype.slice.call(arguments));$args_result$$=$obj$$.callback.apply(this,$args_result$$);if(null!==$args_result$$&&void 0!==$args_result$$)return $args_result$$}};return $obj$$};",
		"CharbaJsHelper.isCanvasPattern=function $CharbaJsHelper$isCanvasPattern$($obj$$){return $obj$$ instanceof CanvasPattern};CharbaJsHelper.isCanvasGradient=function $CharbaJsHelper$isCanvasGradient$($obj$$){return $obj$$ instanceof CanvasGradient};function CharbaJsPluginHelper(){}CharbaJsPluginHelper.register=function $CharbaJsPluginHelper$register$($instance$$){Chart.register($instance$$)};CharbaJsPluginHelper.unregister=function $CharbaJsPluginHelper$unregister$($instance$$){Chart.registry.remove([$instance$$])};",
		"CharbaJsPluginHelper.getAll=function $CharbaJsPluginHelper$getAll$(){return Chart.registry.plugins.items};function CharbaJsControllerHelper(){}CharbaJsControllerHelper.register=function $CharbaJsControllerHelper$register$($controllerType$$,$chartType$$,$instance$$){\"undefined\"===typeof CharbaJsControllerHelper.wrappers&&Object.defineProperty(CharbaJsControllerHelper,\"wrappers\",{value:{},configurable:!1,enumerable:!1,writable:!1});CharbaJsControllerHelper.wrappers[$controllerType$$]=$instance$$};",
		"CharbaJsControllerHelper.initialize=function $CharbaJsControllerHelper$initialize$($controllerType$$,$context$$){Chart.controllers[$controllerType$$].prototype.initialize.call($context$$)};CharbaJsControllerHelper.addElements=function $CharbaJsControllerHelper$addElements$($controllerType$$,$context$$){Chart.controllers[$controllerType$$].prototype.addElements.call($context$$)};CharbaJsControllerHelper.draw=function $CharbaJsControllerHelper$draw$($controllerType$$,$context$$){Chart.controllers[$controllerType$$].prototype.draw.call($context$$)};",
		"CharbaJsControllerHelper.removeHoverStyle=function $CharbaJsControllerHelper$removeHoverStyle$($controllerType$$,$context$$,$element$$,$datasetIndex$$,$index$$){Chart.controllers[$controllerType$$].prototype.removeHoverStyle.call($context$$,$element$$,$datasetIndex$$,$index$$)};",
		"CharbaJsControllerHelper.setHoverStyle=function $CharbaJsControllerHelper$setHoverStyle$($controllerType$$,$context$$,$element$$,$datasetIndex$$,$index$$){Chart.controllers[$controllerType$$].prototype.setHoverStyle.call($context$$,$element$$,$datasetIndex$$,$index$$)};CharbaJsControllerHelper.update=function $CharbaJsControllerHelper$update$($controllerType$$,$context$$,$mode$$){Chart.controllers[$controllerType$$].prototype.update.call($context$$,$mode$$)};",
		"CharbaJsControllerHelper.linkScales=function $CharbaJsControllerHelper$linkScales$($controllerType$$,$context$$){Chart.controllers[$controllerType$$].prototype.linkScales.call($context$$)};CharbaJsControllerHelper.buildOrUpdateElements=function $CharbaJsControllerHelper$buildOrUpdateElements$($controllerType$$,$context$$){Chart.controllers[$controllerType$$].prototype.buildOrUpdateElements.call($context$$)};function CharbaJsPositionerHelper(){}",
		"CharbaJsPositionerHelper.register=function $CharbaJsPositionerHelper$register$($name$$,$module$$){null!=$module$$&&\"function\"===typeof $module$$&&(Chart.registry.getPlugin(\"tooltip\").positioners[$name$$]=$module$$)};CharbaJsPositionerHelper.unregister=function $CharbaJsPositionerHelper$unregister$($name$$){var $tooltipPlugin$$=Chart.registry.getPlugin(\"tooltip\");\"undefined\"!=$tooltipPlugin$$.positioners[$name$$]&&delete $tooltipPlugin$$.positioners[$name$$]};",
		"CharbaJsPositionerHelper.invoke=function $CharbaJsPositionerHelper$invoke$($name$$,$context$$,$elements$$,$eventPoint$$){var $tooltipPlugin$$=Chart.registry.getPlugin(\"tooltip\");return\"undefined\"!=$tooltipPlugin$$.positioners[$name$$]?$tooltipPlugin$$.positioners[$name$$].apply($context$$,[$elements$$,$eventPoint$$]):null};function CharbaJsCallbacksHelper(){}",
		"CharbaJsCallbacksHelper.generateDefaultLabels=function $CharbaJsCallbacksHelper$generateDefaultLabels$($chart$$,$options$$){return null!=$options$$&&\"object\"===typeof $options$$.legend&&\"object\"===typeof $options$$.legend.labels&&\"function\"===typeof $options$$.legend.labels.generateLabels?$options$$.legend.labels.generateLabels.call($chart$$,$chart$$):null};",
		"CharbaJsCallbacksHelper.invokeDefaultLegendEvent=function $CharbaJsCallbacksHelper$invokeDefaultLegendEvent$($options$$,$key$$,$chart$$,$event$$,$item$$){null!=$options$$&&\"object\"===typeof $options$$.legend&&\"function\"===typeof $options$$.legend[$key$$]&&$options$$.legend[$key$$].call($chart$$,$event$$,$item$$,$chart$$.legend)};",
		"CharbaJsCallbacksHelper.invokeDefaultChartEvent=function $CharbaJsCallbacksHelper$invokeDefaultChartEvent$($options$$,$key$$,$chart$$,$event$$,$items$$){null!=$options$$&&\"function\"===typeof $options$$[$key$$]&&$options$$[$key$$].call($chart$$,$event$$,$items$$,$chart$$)};function CharbaJsZoomHelper(){}CharbaJsZoomHelper.resetZoom=function $CharbaJsZoomHelper$resetZoom$($chart$$){null!=$chart$$&&\"function\"===typeof $chart$$.resetZoom&&$chart$$.resetZoom.call($chart$$)};",
		"function CharbaJsItemsHelper(){}CharbaJsItemsHelper.isCanvasPattern=function $CharbaJsItemsHelper$isCanvasPattern$($obj$$,$key$$){return $obj$$[$key$$]instanceof CanvasPattern};CharbaJsItemsHelper.isCanvasGradient=function $CharbaJsItemsHelper$isCanvasGradient$($obj$$,$key$$){return $obj$$[$key$$]instanceof CanvasGradient};CharbaJsItemsHelper.nativeEvent=function $CharbaJsItemsHelper$nativeEvent$($obj$$,$key$$){return $obj$$[$key$$]};",
		"CharbaJsItemsHelper.getDecimalForPixel=function $CharbaJsItemsHelper$getDecimalForPixel$($obj$$,$pixel$$){return $obj$$.getDecimalForPixel($pixel$$)};CharbaJsItemsHelper.getPixelForDecimal=function $CharbaJsItemsHelper$getPixelForDecimal$($obj$$,$decimal$$){return $obj$$.getPixelForDecimal($decimal$$)};CharbaJsItemsHelper.getPixelForTick=function $CharbaJsItemsHelper$getPixelForTick$($obj$$,$index$$){return $obj$$.getPixelForTick($index$$)};",
		"CharbaJsItemsHelper.getLabelForValue=function $CharbaJsItemsHelper$getLabelForValue$($obj$$,$value$$){return $obj$$.getLabelForValue($value$$)};CharbaJsItemsHelper.getPixelForStringValue=function $CharbaJsItemsHelper$getPixelForStringValue$($obj$$,$value$$,$index$$){return $obj$$.getPixelForValue($value$$,$index$$)};CharbaJsItemsHelper.getPixelForValue=function $CharbaJsItemsHelper$getPixelForValue$($obj$$,$value$$,$index$$){return $obj$$.getPixelForValue($value$$,$index$$)};",
		"CharbaJsItemsHelper.getValueForPixel=function $CharbaJsItemsHelper$getValueForPixel$($obj$$,$pixel$$){return $obj$$.getValueForPixel($pixel$$)};CharbaJsItemsHelper.getBaseValue=function $CharbaJsItemsHelper$getBaseValue$($obj$$){return $obj$$.getBaseValue()};CharbaJsItemsHelper.getBasePixel=function $CharbaJsItemsHelper$getBasePixel$($obj$$){return $obj$$.getBasePixel()};function CharbaJsDateAdapterHelper(){}CharbaJsDateAdapterHelper.create=function $CharbaJsDateAdapterHelper$create$($options$$){return new Chart._adapters._date($options$$)};"
	};
	
	/**
	 * Creates the injectable resource with <code>charba.helper.min.js</code> content.
	 */
	JsHelperResource() {
		super(ResourceName.CHARBA_HELPER, CONTENT);
	}

}
