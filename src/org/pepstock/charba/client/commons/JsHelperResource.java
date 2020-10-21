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
		"var CharbaCallbackProxy={callback:null,proxy:null};function CharbaJsHelper(){}CharbaJsHelper.typeOf=function($obj$$){return typeof $obj$$};CharbaJsHelper.remove=function($obj$$,$key$$){delete $obj$$[$key$$]};CharbaJsHelper.type=function($obj$$,$key$$){return typeof $obj$$[$key$$]};CharbaJsHelper.isArray=function($obj$$,$key$$){return Array.isArray($obj$$[$key$$])};",
		"CharbaJsHelper.newCallbackProxy=function(){var $obj$$=Object.create(CharbaCallbackProxy);$obj$$.callback=null;$obj$$.proxy=function(){if(null!=$obj$$.callback&&\"function\"===typeof $obj$$.callback){var $args_result$$=[this].concat(Array.prototype.slice.call(arguments));$args_result$$=$obj$$.callback.apply(this,$args_result$$);if(null!==$args_result$$&&void 0!==$args_result$$)return $args_result$$}};return $obj$$};CharbaJsHelper.isCanvasPattern=function($obj$$){return $obj$$ instanceof CanvasPattern};",
		"CharbaJsHelper.isCanvasGradient=function($obj$$){return $obj$$ instanceof CanvasGradient};function CharbaJsControllerHelper(){}CharbaJsControllerHelper.register=function($controllerType$$,$instance$$){Chart.controllers[$controllerType$$]=Chart.DatasetController.extend($instance$$)};",
		"CharbaJsControllerHelper.extend=function($controllerType$$,$chartType$$,$instance$$){Chart.defaults[$controllerType$$]=Chart.defaults[$chartType$$];Chart.defaults.global.datasets[$controllerType$$]=Chart.defaults.global.datasets[$chartType$$];Chart.controllers[$controllerType$$]=Chart.controllers[$chartType$$].extend($instance$$)};",
		"CharbaJsControllerHelper.initialize=function($controllerType$$,$context$$,$datasetIndex$$){Chart.controllers[$controllerType$$].prototype.initialize.call($context$$,$context$$.chart,$datasetIndex$$)};CharbaJsControllerHelper.addElements=function($controllerType$$,$context$$){Chart.controllers[$controllerType$$].prototype.addElements.call($context$$)};",
		"CharbaJsControllerHelper.addElementAndReset=function($controllerType$$,$context$$,$index$$){Chart.controllers[$controllerType$$].prototype.addElementAndReset.call($context$$,$index$$)};CharbaJsControllerHelper.draw=function($controllerType$$,$context$$,$ease$$){Chart.controllers[$controllerType$$].prototype.draw.call($context$$,$ease$$)};",
		"CharbaJsControllerHelper.removeHoverStyle=function($controllerType$$,$context$$,$element$$){Chart.controllers[$controllerType$$].prototype.removeHoverStyle.call($context$$,$element$$)};CharbaJsControllerHelper.setHoverStyle=function($controllerType$$,$context$$,$element$$){Chart.controllers[$controllerType$$].prototype.setHoverStyle.call($context$$,$element$$)};",
		"CharbaJsControllerHelper.update=function($controllerType$$,$context$$,$reset$$){Chart.controllers[$controllerType$$].prototype.update.call($context$$,$reset$$)};function CharbaJsWindowHelper(){}CharbaJsWindowHelper.enableResizeOnBeforePrint=function(){window.onbeforeprint=function($event$$){for(var $id$$ in Chart.instances)Chart.instances[$id$$].resize()}};function CharbaJsPositionerHelper(){}",
		"CharbaJsPositionerHelper.register=function($name$$,$module$$){null!=$module$$&&\"function\"===typeof $module$$&&(Chart.Tooltip.positioners[$name$$]=$module$$)};CharbaJsPositionerHelper.unregister=function($name$$){\"undefined\"!=Chart.Tooltip.positioners[$name$$]&&delete Chart.Tooltip.positioners[$name$$]};",
		"CharbaJsPositionerHelper.invoke=function($name$$,$context$$,$elements$$,$eventPoint$$){return\"undefined\"!=Chart.Tooltip.positioners[$name$$]?Chart.Tooltip.positioners[$name$$].apply($context$$,[$elements$$,$eventPoint$$]):null};function CharbaJsCallbacksHelper(){}CharbaJsCallbacksHelper.generateDefaultLegend=function($chart$$,$options$$){return null!=$options$$&&\"function\"===typeof $options$$.legendCallback?$options$$.legendCallback.call($chart$$,$chart$$):null};",
		"CharbaJsCallbacksHelper.generateDefaultLabels=function($chart$$,$options$$){return null!=$options$$&&\"object\"===typeof $options$$.legend&&\"object\"===typeof $options$$.legend.labels&&\"function\"===typeof $options$$.legend.labels.generateLabels?$options$$.legend.labels.generateLabels.call($chart$$,$chart$$):null};",
		"CharbaJsCallbacksHelper.invokeDefaultLegendEvent=function($options$$,$key$$,$chart$$,$event$$,$item$$){null!=$options$$&&\"object\"===typeof $options$$.legend&&\"function\"===typeof $options$$.legend[$key$$]&&$options$$.legend[$key$$].call($chart$$,$event$$,$item$$)};CharbaJsCallbacksHelper.invokeDefaultChartEvent=function($options$$,$key$$,$chart$$,$event$$,$items$$){null!=$options$$&&\"function\"===typeof $options$$[$key$$]&&$options$$[$key$$].call($chart$$,$event$$,$items$$)};",
		"function CharbaJsZoomHelper(){}CharbaJsZoomHelper.resetZoom=function($chart$$){null!=$chart$$&&\"function\"===typeof $chart$$.resetZoom&&$chart$$.resetZoom.call($chart$$)};function CharbaJsItemsHelper(){}CharbaJsItemsHelper.isCanvasPattern=function($obj$$,$key$$){return $obj$$[$key$$]instanceof CanvasPattern};CharbaJsItemsHelper.isCanvasGradient=function($obj$$,$key$$){return $obj$$[$key$$]instanceof CanvasGradient};CharbaJsItemsHelper.nativeEvent=function($obj$$,$key$$){return $obj$$[$key$$]};",
		"function CharbaJsDateAdapterHelper(){}CharbaJsDateAdapterHelper.create=function($options$$){return new Chart._adapters._date($options$$)};"
	};
	
	/**
	 * Creates the injectable resource with <code>charba.helper.min.js</code> content.
	 */
	JsHelperResource() {
		super(ResourceName.CHARBA_HELPER, CONTENT);
	}

}
