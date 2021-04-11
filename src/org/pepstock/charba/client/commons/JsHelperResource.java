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
		"var CharbaCallbackProxy={callback:null,proxy:null};function CharbaJsObjectArrayHelper(){}CharbaJsObjectArrayHelper.set=function($obj$$,$key$$,$value$$){$obj$$[$key$$]=$value$$};CharbaJsObjectArrayHelper.get=function($obj$$,$key$$){return $obj$$[$key$$]};function CharbaJsObjectBooleanHelper(){}CharbaJsObjectBooleanHelper.set=function($obj$$,$key$$,$value$$){$obj$$[$key$$]=$value$$};CharbaJsObjectBooleanHelper.get=function($obj$$,$key$$){return $obj$$[$key$$]};",
		"function CharbaJsObjectCallbackProxyHelper(){}CharbaJsObjectCallbackProxyHelper.set=function($obj$$,$key$$,$value$$){$obj$$[$key$$]=$value$$};CharbaJsObjectCallbackProxyHelper.get=function($obj$$,$key$$){return $obj$$[$key$$]};function CharbaJsObjectChartHelper(){}CharbaJsObjectChartHelper.set=function($obj$$,$key$$,$value$$){$obj$$[$key$$]=$value$$};CharbaJsObjectChartHelper.get=function($obj$$,$key$$){return $obj$$[$key$$]};function CharbaJsObjectDoubleHelper(){}",
		"CharbaJsObjectDoubleHelper.set=function($obj$$,$key$$,$value$$){$obj$$[$key$$]=$value$$};CharbaJsObjectDoubleHelper.get=function($obj$$,$key$$){return $obj$$[$key$$]};function CharbaJsObjectEventHelper(){}CharbaJsObjectEventHelper.set=function($obj$$,$key$$,$value$$){$obj$$[$key$$]=$value$$};CharbaJsObjectEventHelper.get=function($obj$$,$key$$){return $obj$$[$key$$]};function CharbaJsObjectGradientHelper(){}CharbaJsObjectGradientHelper.set=function($obj$$,$key$$,$value$$){$obj$$[$key$$]=$value$$};",
		"CharbaJsObjectGradientHelper.get=function($obj$$,$key$$){return $obj$$[$key$$]};function CharbaJsObjectImageHelper(){}CharbaJsObjectImageHelper.set=function($obj$$,$key$$,$value$$){$obj$$[$key$$]=$value$$};CharbaJsObjectImageHelper.get=function($obj$$,$key$$){return $obj$$[$key$$]};function CharbaJsObjectObjectHelper(){}CharbaJsObjectObjectHelper.set=function($obj$$,$key$$,$value$$){$obj$$[$key$$]=$value$$};CharbaJsObjectObjectHelper.get=function($obj$$,$key$$){return $obj$$[$key$$]};",
		"function CharbaJsObjectPatternHelper(){}CharbaJsObjectPatternHelper.set=function($obj$$,$key$$,$value$$){$obj$$[$key$$]=$value$$};CharbaJsObjectPatternHelper.get=function($obj$$,$key$$){return $obj$$[$key$$]};function CharbaJsObjectStringHelper(){}CharbaJsObjectStringHelper.set=function($obj$$,$key$$,$value$$){$obj$$[$key$$]=$value$$};CharbaJsObjectStringHelper.get=function($obj$$,$key$$){return $obj$$[$key$$]};function CharbaJsHelper(){}CharbaJsHelper.cast=function($object$$){return $object$$};",
		"CharbaJsHelper.has=function($obj$$,$key$$){return $key$$ in $obj$$};CharbaJsHelper.remove=function($obj$$,$key$$){delete $obj$$[$key$$]};CharbaJsHelper.typeOf=function($obj$$){return typeof $obj$$};CharbaJsHelper.type=function($obj$$,$key$$){return typeof $obj$$[$key$$]};CharbaJsHelper.isArray=function($obj$$,$key$$){return Array.isArray($obj$$[$key$$])};",
		"CharbaJsHelper.newCallbackProxy=function(){var $obj$$=Object.create(CharbaCallbackProxy);$obj$$.callback=null;$obj$$.proxy=function(){if(null!=$obj$$.callback&&\"function\"===typeof $obj$$.callback){var $args_result$$=[this].concat(Array.prototype.slice.call(arguments));$args_result$$=$obj$$.callback.apply(this,$args_result$$);if(null!==$args_result$$&&void 0!==$args_result$$)return $args_result$$}};return $obj$$};CharbaJsHelper.isCanvasPattern=function($obj$$){return $obj$$ instanceof CanvasPattern};",
		"CharbaJsHelper.isCanvasGradient=function($obj$$){return $obj$$ instanceof CanvasGradient};function CharbaJsPluginHelper(){}CharbaJsPluginHelper.register=function($obj$$){Chart.register($obj$$)};CharbaJsPluginHelper.unregister=function($obj$$){Chart.registry.remove([$obj$$])};CharbaJsPluginHelper.getAll=function(){return Chart.registry.plugins.items};function CharbaJsControllerHelper(){}",
		"CharbaJsControllerHelper.register=function($controllerType$$,$chartType$$,$obj$$){\"undefined\"===typeof CharbaJsControllerHelper.wrappers&&Object.defineProperty(CharbaJsControllerHelper,\"wrappers\",{value:{},configurable:!1,enumerable:!1,writable:!1});CharbaJsControllerHelper.wrappers[$controllerType$$]=$obj$$};CharbaJsControllerHelper.initialize=function($controllerType$$,$context$$){Chart.controllers[$controllerType$$].prototype.initialize.call($context$$)};",
		"CharbaJsControllerHelper.addElements=function($controllerType$$,$context$$){Chart.controllers[$controllerType$$].prototype.addElements.call($context$$)};CharbaJsControllerHelper.draw=function($controllerType$$,$context$$){Chart.controllers[$controllerType$$].prototype.draw.call($context$$)};",
		"CharbaJsControllerHelper.removeHoverStyle=function($controllerType$$,$context$$,$element$$,$datasetIndex$$,$index$$){Chart.controllers[$controllerType$$].prototype.removeHoverStyle.call($context$$,$element$$,$datasetIndex$$,$index$$)};CharbaJsControllerHelper.setHoverStyle=function($controllerType$$,$context$$,$element$$,$datasetIndex$$,$index$$){Chart.controllers[$controllerType$$].prototype.setHoverStyle.call($context$$,$element$$,$datasetIndex$$,$index$$)};",
		"CharbaJsControllerHelper.update=function($controllerType$$,$context$$,$mode$$){Chart.controllers[$controllerType$$].prototype.update.call($context$$,$mode$$)};CharbaJsControllerHelper.linkScales=function($controllerType$$,$context$$){Chart.controllers[$controllerType$$].prototype.linkScales.call($context$$)};",
		"CharbaJsControllerHelper.buildOrUpdateElements=function($controllerType$$,$context$$,$resetNewElements$$){Chart.controllers[$controllerType$$].prototype.buildOrUpdateElements.call($context$$,$resetNewElements$$)};function CharbaJsPositionerHelper(){}CharbaJsPositionerHelper.register=function($name$$,$module$$){null!=$module$$&&\"function\"===typeof $module$$&&(Chart.registry.getPlugin(\"tooltip\").positioners[$name$$]=$module$$)};",
		"CharbaJsPositionerHelper.unregister=function($name$$){var $tooltipPlugin$$=Chart.registry.getPlugin(\"tooltip\");\"undefined\"!=$tooltipPlugin$$.positioners[$name$$]&&delete $tooltipPlugin$$.positioners[$name$$]};CharbaJsPositionerHelper.invoke=function($name$$,$context$$,$elements$$,$eventPoint$$){var $tooltipPlugin$$=Chart.registry.getPlugin(\"tooltip\");return\"undefined\"!=$tooltipPlugin$$.positioners[$name$$]?$tooltipPlugin$$.positioners[$name$$].apply($context$$,[$elements$$,$eventPoint$$]):null};",
		"function CharbaJsChartHelper(){}CharbaJsChartHelper.generateDefaultLabels=function($chart$$,$options$$){return null!=$options$$&&\"object\"===typeof $options$$.plugins&&\"object\"===typeof $options$$.plugins.legend&&\"object\"===typeof $options$$.plugins.legend.labels&&\"function\"===typeof $options$$.plugins.legend.labels.generateLabels?$options$$.plugins.legend.labels.generateLabels.call($chart$$,$chart$$):null};",
		"CharbaJsChartHelper.invokeDefaultLegendEvent=function($options$$,$key$$,$chart$$,$event$$,$item$$){null!=$options$$&&\"object\"===typeof $options$$.plugins&&\"object\"===typeof $options$$.plugins.legend&&\"function\"===typeof $options$$.plugins.legend[$key$$]&&$options$$.plugins.legend[$key$$].call($chart$$,$event$$,$item$$,$chart$$.legend)};",
		"CharbaJsChartHelper.invokeDefaultChartEvent=function($options$$,$key$$,$chart$$,$event$$,$items$$){null!=$options$$&&\"function\"===typeof $options$$[$key$$]&&$options$$[$key$$].call($chart$$,$event$$,$items$$,$chart$$)};function CharbaJsZoomHelper(){}CharbaJsZoomHelper.resetZoom=function($chart$$){null!=$chart$$&&\"function\"===typeof $chart$$.resetZoom&&$chart$$.resetZoom.call($chart$$)};function CharbaJsDataLabelsHelper(){}CharbaJsDataLabelsHelper.register=function(){Chart.register(ChartDataLabels)};",
		"function CharbaJsItemsHelper(){}CharbaJsItemsHelper.isCanvasPattern=function($obj$$,$key$$){return $obj$$[$key$$]instanceof CanvasPattern};CharbaJsItemsHelper.isCanvasGradient=function($obj$$,$key$$){return $obj$$[$key$$]instanceof CanvasGradient};CharbaJsItemsHelper.getDecimalForPixel=function($obj$$,$pixel$$){return $obj$$.getDecimalForPixel($pixel$$)};CharbaJsItemsHelper.getPixelForDecimal=function($obj$$,$decimal$$){return $obj$$.getPixelForDecimal($decimal$$)};",
		"CharbaJsItemsHelper.getPixelForTick=function($obj$$,$index$$){return $obj$$.getPixelForTick($index$$)};CharbaJsItemsHelper.getLabelForValue=function($obj$$,$value$$){return $obj$$.getLabelForValue($value$$)};CharbaJsItemsHelper.getPixelForStringValue=function($obj$$,$value$$,$index$$){return $obj$$.getPixelForValue($value$$,$index$$)};CharbaJsItemsHelper.getPixelForValue=function($obj$$,$value$$,$index$$){return $obj$$.getPixelForValue($value$$,$index$$)};",
		"CharbaJsItemsHelper.getValueForPixel=function($obj$$,$pixel$$){return $obj$$.getValueForPixel($pixel$$)};CharbaJsItemsHelper.getBaseValue=function($obj$$){return $obj$$.getBaseValue()};CharbaJsItemsHelper.getBasePixel=function($obj$$){return $obj$$.getBasePixel()};CharbaJsItemsHelper.getDatasetControllerStyle=function($controller$$,$dataIndex$$){return $controller$$.getStyle($dataIndex$$)};function CharbaJsDateAdapterHelper(){}CharbaJsDateAdapterHelper.create=function($options$$){return new Chart._adapters._date($options$$)};"
	};
	
	/**
	 * Creates the injectable resource with <code>charba.helper.min.js</code> content.
	 */
	JsHelperResource() {
		super(ResourceName.CHARBA_HELPER, CONTENT);
	}

}
