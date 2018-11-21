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
package org.pepstock.charba.client.jsinterop.options;

import org.pepstock.charba.client.jsinterop.commons.CallbackProxy;
import org.pepstock.charba.client.jsinterop.commons.NativeName;
import org.pepstock.charba.client.jsinterop.commons.NativeObject;

import jsinterop.annotations.JsPackage;
import jsinterop.annotations.JsProperty;
import jsinterop.annotations.JsType;

/**
 * The chart legend displays data about the datasets that area appearing on the chart.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
@JsType(isNative = true, namespace = JsPackage.GLOBAL, name=NativeName.OBJECT)
final class NativeLegend extends NativeObject {

	/**
	 * @return the labels
	 */
	@JsProperty
	native NativeLegendLabels getLabels();

	/**
	 * @return the labels
	 */
	@JsProperty
	native void setLabels(NativeLegendLabels labels);

	/**
	 * Sets if the legend is shown.
	 * 
	 * @param display if the legend is shown.
	 */
	@JsProperty
	native void setDisplay(boolean display);

	/**
	 * Returns if the legend is shown.
	 * 
	 * @return if the legend is shown. Default is true.
	 */
	@JsProperty
	native boolean isDisplay();

	/**
	 * Marks that this box should take the full width of the canvas (pushing down other boxes).
	 * 
	 * @param fullWidth Marks that this box should take the full width of the canvas (pushing down other boxes)
	 */
	@JsProperty
	native void setFullWidth(boolean fullWidth);

	/**
	 * Returns if marks that this box should take the full width of the canvas (pushing down other boxes)
	 * 
	 * @return Marks that this box should take the full width of the canvas (pushing down other boxes). Default is true.
	 */
	@JsProperty
	native boolean isFullWidth();

	/**
	 * Sets the legend will show datasets in reverse order.
	 * 
	 * @param reverse legend will show datasets in reverse order.
	 */
	@JsProperty
	native void setReverse(boolean reverse);

	/**
	 * Returns if the legend will show datasets in reverse order.
	 * 
	 * @return Legend will show datasets in reverse order. Default is false.
	 */
	@JsProperty
	native boolean isReverse();
	
	/**
	 * Sets the position of the legend.
	 * 
	 * @param position Position of the legend.
	 * @see org.pepstock.charba.client.enums.Position
	 */
	@JsProperty
	native void setPosition(String position);

	/**
	 * Returns the position of the legend.
	 * 
	 * @return Position of the legend. Default is {@link org.pepstock.charba.client.enums.Position#top}.
	 * @see org.pepstock.charba.client.enums.Position
	 */
	@JsProperty
	native String getPosition();
	

	@JsProperty
	native void setOnClick(CallbackProxy.Proxy proxy);

	@JsProperty
	native void setOnHover(CallbackProxy.Proxy proxy);
}