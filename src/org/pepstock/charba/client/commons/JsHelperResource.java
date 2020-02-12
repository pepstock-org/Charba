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

import org.pepstock.charba.client.resources.InjectableResource;

/**
 * Contains the content of <code>charba.helper.min.js</code> to inject.<br>
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class JsHelperResource extends InjectableResource {

	// encoded javascript content of charba.helper.min.js
	private static final String[] CONTENT = {
		"function CharbaJsHelper(){}CharbaJsHelper.typeOf=function $CharbaJsHelper$typeOf$($obj$$){return typeof $obj$$};CharbaJsHelper.remove=function $CharbaJsHelper$remove$($obj$$,$key$$){delete $obj$$[$key$$]};CharbaJsHelper.type=function $CharbaJsHelper$type$($obj$$,$key$$){return typeof $obj$$[$key$$]};CharbaJsHelper.isArray=function $CharbaJsHelper$isArray$($obj$$,$key$$){return Array.isArray($obj$$[$key$$])};CharbaJsHelper.propertyAsString=function $CharbaJsHelper$propertyAsString$($obj$$,$key$$){return $obj$$[$key$$]};",
		"CharbaJsHelper.propertyAsDouble=function $CharbaJsHelper$propertyAsDouble$($obj$$,$key$$){return $obj$$[$key$$]};CharbaJsHelper.propertyAsInt=function $CharbaJsHelper$propertyAsInt$($obj$$,$key$$){return $obj$$[$key$$]};",
		"CharbaJsHelper.newCallbackProxy=function $CharbaJsHelper$newCallbackProxy$(){var $obj$$={callback:null,proxy:function(){if(null!=$obj$$.callback&&\"function\"===typeof $obj$$.callback){var $args_result$$=Array.of(this).concat(Array.prototype.slice.call(arguments));$args_result$$=$obj$$.callback.apply(this,$args_result$$);if(null!==$args_result$$&&void 0!==$args_result$$)return $args_result$$}}};return $obj$$};function CharbaJsControllerHelper(){}",
		"CharbaJsControllerHelper.register=function $CharbaJsControllerHelper$register$($controllerType$$,$instance$$){Chart.controllers[$controllerType$$]=Chart.DatasetController.extend($instance$$)};",
		"CharbaJsControllerHelper.extend=function $CharbaJsControllerHelper$extend$($controllerType$$,$chartType$$,$instance$$){Chart.defaults[$controllerType$$]=Chart.defaults[$chartType$$];Chart.defaults.global.datasets[$controllerType$$]=Chart.defaults.global.datasets[$chartType$$];Chart.controllers[$controllerType$$]=Chart.controllers[$chartType$$].extend($instance$$)};",
		"CharbaJsControllerHelper.initialize=function $CharbaJsControllerHelper$initialize$($controllerType$$,$context$$,$datasetIndex$$){Chart.controllers[$controllerType$$].prototype.initialize.call($context$$,$context$$.chart,$datasetIndex$$)};CharbaJsControllerHelper.addElements=function $CharbaJsControllerHelper$addElements$($controllerType$$,$context$$){Chart.controllers[$controllerType$$].prototype.addElements.call($context$$)};",
		"CharbaJsControllerHelper.addElementAndReset=function $CharbaJsControllerHelper$addElementAndReset$($controllerType$$,$context$$,$index$$){Chart.controllers[$controllerType$$].prototype.addElementAndReset.call($context$$,$index$$)};CharbaJsControllerHelper.draw=function $CharbaJsControllerHelper$draw$($controllerType$$,$context$$,$ease$$){Chart.controllers[$controllerType$$].prototype.draw.call($context$$,$ease$$)};",
		"CharbaJsControllerHelper.removeHoverStyle=function $CharbaJsControllerHelper$removeHoverStyle$($controllerType$$,$context$$,$element$$){Chart.controllers[$controllerType$$].prototype.removeHoverStyle.call($context$$,$element$$)};CharbaJsControllerHelper.setHoverStyle=function $CharbaJsControllerHelper$setHoverStyle$($controllerType$$,$context$$,$element$$){Chart.controllers[$controllerType$$].prototype.setHoverStyle.call($context$$,$element$$)};",
		"CharbaJsControllerHelper.update=function $CharbaJsControllerHelper$update$($controllerType$$,$context$$,$reset$$){Chart.controllers[$controllerType$$].prototype.update.call($context$$,$reset$$)};function CharbaJsWindowHelper(){}CharbaJsWindowHelper.enableResizeOnBeforePrint=function $CharbaJsWindowHelper$enableResizeOnBeforePrint$(){window.onbeforeprint=function $window$onbeforeprint$($event$$){for(var $id$$ in Chart.instances)Chart.instances[$id$$].resize()}};",
		"function CharbaJsPositionerHelper(){}CharbaJsPositionerHelper.register=function $CharbaJsPositionerHelper$register$($name$$,$module$$){null!=$module$$&&\"function\"===typeof $module$$&&(Chart.Tooltip.positioners[$name$$]=$module$$)};CharbaJsPositionerHelper.unregister=function $CharbaJsPositionerHelper$unregister$($name$$){\"undefined\"!=Chart.Tooltip.positioners[$name$$]&&delete Chart.Tooltip.positioners[$name$$]};",
		"CharbaJsPositionerHelper.invoke=function $CharbaJsPositionerHelper$invoke$($name$$,$context$$,$elements$$,$eventPoint$$){return\"undefined\"!=Chart.Tooltip.positioners[$name$$]?Chart.Tooltip.positioners[$name$$].apply($context$$,Array.of($elements$$,$eventPoint$$)):null};function CharbaJsCallbacksHelper(){}",
		"CharbaJsCallbacksHelper.generateDefaultLegend=function $CharbaJsCallbacksHelper$generateDefaultLegend$($chart$$,$options$$){return null!=$options$$&&\"function\"===typeof $options$$.legendCallback?$options$$.legendCallback.call($chart$$,$chart$$):null};",
		"CharbaJsCallbacksHelper.generateDefaultLabels=function $CharbaJsCallbacksHelper$generateDefaultLabels$($chart$$,$options$$){return null!=$options$$&&\"object\"===typeof $options$$.legend&&\"object\"===typeof $options$$.legend.labels&&\"function\"===typeof $options$$.legend.labels.generateLabels?$options$$.legend.labels.generateLabels.call($chart$$,$chart$$):null};",
		"CharbaJsCallbacksHelper.invokeDefaultLegendEvent=function $CharbaJsCallbacksHelper$invokeDefaultLegendEvent$($options$$,$key$$,$chart$$,$event$$,$item$$){null!=$options$$&&\"object\"===typeof $options$$.legend&&\"function\"===typeof $options$$.legend[$key$$]&&$options$$.legend[$key$$].call($chart$$,$event$$,$item$$)};",
		"CharbaJsCallbacksHelper.invokeDefaultChartEvent=function $CharbaJsCallbacksHelper$invokeDefaultChartEvent$($options$$,$key$$,$chart$$,$event$$,$items$$){null!=$options$$&&\"function\"===typeof $options$$[$key$$]&&$options$$[$key$$].call($chart$$,$event$$,$items$$)};function CharbaJsZoomHelper(){}CharbaJsZoomHelper.resetZoom=function $CharbaJsZoomHelper$resetZoom$($chart$$){null!=$chart$$&&\"function\"===typeof $chart$$.resetZoom&&$chart$$.resetZoom.call($chart$$)};function CharbaJsItemsHelper(){}",
		"CharbaJsItemsHelper.isCanvasPattern=function $CharbaJsItemsHelper$isCanvasPattern$($obj$$,$key$$){return\"string\"===typeof $key$$?$obj$$[$key$$]instanceof CanvasPattern:$obj$$ instanceof CanvasPattern};CharbaJsItemsHelper.isCanvasGradient=function $CharbaJsItemsHelper$isCanvasGradient$($obj$$,$key$$){return\"string\"===typeof $key$$?$obj$$[$key$$]instanceof CanvasGradient:$obj$$ instanceof CanvasGradient};CharbaJsItemsHelper.nativeEvent=function $CharbaJsItemsHelper$nativeEvent$($obj$$,$key$$){return $obj$$[$key$$]};"
	};
	
	/**
	 * Creates the injectable resource with <code>charba.helper.min.js</code> content.
	 */
	JsHelperResource() {
		super("charbaHelper", CONTENT);
	}

}
