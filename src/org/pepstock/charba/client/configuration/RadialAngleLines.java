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
package org.pepstock.charba.client.configuration;

import java.util.List;

import org.pepstock.charba.client.callbacks.ScaleBorderDashCallback;
import org.pepstock.charba.client.callbacks.ScaleBorderDashOffsetCallback;
import org.pepstock.charba.client.callbacks.ScaleColorCallback;
import org.pepstock.charba.client.callbacks.ScaleLineWidthCallback;
import org.pepstock.charba.client.callbacks.ScaleScriptableContext;
import org.pepstock.charba.client.callbacks.ScriptableFunctions;
import org.pepstock.charba.client.callbacks.ScriptableUtils;
import org.pepstock.charba.client.colors.IsColor;
import org.pepstock.charba.client.commons.Array;
import org.pepstock.charba.client.commons.ArrayInteger;
import org.pepstock.charba.client.commons.CallbackProxy;
import org.pepstock.charba.client.commons.JsHelper;
import org.pepstock.charba.client.commons.Key;

/**
 * It is used to configure angled lines that radiate from the center of the chart to the point labels.<br>
 * Note that these options only apply if display is true.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class RadialAngleLines extends AbstractScaleLines {

	// ---------------------------
	// -- CALLBACKS PROXIES ---
	// ---------------------------
	// callback proxy to invoke the border dash function
	private final CallbackProxy<ScriptableFunctions.ProxyArrayCallback> borderDashCallbackProxy = JsHelper.get().newCallbackProxy();

	// border dashoffset callback instance
	private ScaleBorderDashCallback borderDashCallback = null;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		BORDER_DASH("borderDash");

		// name value of property
		private final String value;

		/**
		 * Creates with the property value to use into native object.
		 * 
		 * @param value value of property name
		 */
		private Property(String value) {
			this.value = value;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see org.pepstock.charba.client.commons.Key#value()
		 */
		@Override
		public String value() {
			return value;
		}

	}

	/**
	 * Builds the object storing the axis which this angle lines belongs to.
	 * 
	 * @param axis axis which this angle lines belongs to.
	 */
	RadialAngleLines(Axis axis) {
		super(axis, axis.getScale().getAngleLines(), axis.getScale().getAngleLines());
		// gets value calling callback
		// gets value calling callback
		borderDashCallbackProxy.setCallback((contextFunction, context) -> onBorderDash(new ScaleScriptableContext(new ConfigurationEnvelop<>(context)), borderDashCallback));
	}

	/**
	 * If true, angle lines are shown
	 * 
	 * @param display if true, angle lines are shown
	 */
	public void setDisplay(boolean display) {
		getAxis().getScale().getAngleLines().setDisplay(display);
	}

	/**
	 * If true, angle lines are shown
	 * 
	 * @return if true, angle lines are shown.
	 */
	public boolean isDisplay() {
		return getAxis().getScale().getAngleLines().isDisplay();
	}

	/**
	 * Sets the color of angled lines.
	 * 
	 * @param color color of angled lines.
	 */
	public void setColor(IsColor color) {
		// reset callback if there is
		setColor((ScaleColorCallback) null);
		// stores value
		getAxis().getScale().getAngleLines().setColor(color);
	}

	/**
	 * Sets the color of angled lines.
	 * 
	 * @param color color of angled lines.
	 */
	public void setColor(String color) {
		// reset callback if there is
		setColor((ScaleColorCallback) null);
		// stores value
		getAxis().getScale().getAngleLines().setColor(color);
	}

	/**
	 * Returns the color of angled lines.
	 * 
	 * @return color of angled lines.
	 */
	public String getColorAsString() {
		return getAxis().getScale().getAngleLines().getColorAsString();
	}

	/**
	 * Returns the color of angled lines.
	 * 
	 * @return color of angled lines.
	 */
	public IsColor getColor() {
		return getAxis().getScale().getAngleLines().getColor();
	}

	/**
	 * Sets the width of angled lines.
	 * 
	 * @param lineWidth width of angled lines.
	 */
	public void setLineWidth(int lineWidth) {
		// reset callback if there is
		setLineWidth((ScaleLineWidthCallback) null);
		// stores value
		getAxis().getScale().getAngleLines().setLineWidth(lineWidth);
	}

	/**
	 * Returns the width of angled lines.
	 * 
	 * @return width of angled lines.
	 */
	public int getLineWidth() {
		return getAxis().getScale().getAngleLines().getLineWidth();
	}

	/**
	 * Sets the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 * 
	 * @param borderDash the line dash pattern used when stroking lines
	 */
	public void setBorderDash(int... borderDash) {
		// reset callback if there is
		setBorderDash((ScaleBorderDashCallback) null);
		// stores value
		getAxis().getScale().getAngleLines().setBorderDash(borderDash);
	}

	/**
	 * Returns the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 * 
	 * @return the line dash pattern used when stroking lines
	 */
	public List<Integer> getBorderDash() {
		return getAxis().getScale().getAngleLines().getBorderDash();
	}

	/**
	 * Sets the line dash pattern offset.
	 * 
	 * @param borderDashOffset Offset for line dashes.
	 */
	public void setBorderDashOffset(double borderDashOffset) {
		// reset callback if there is
		setBorderDashOffset((ScaleBorderDashOffsetCallback) null);
		// stores value
		getAxis().getScale().getAngleLines().setBorderDashOffset(borderDashOffset);
	}

	/**
	 * Returns the line dash pattern offset.
	 * 
	 * @return Offset for line dashes.
	 */
	public double getBorderDashOffset() {
		return getAxis().getScale().getAngleLines().getBorderDashOffset();
	}

	/**
	 * Returns the border dash callback when element is hovered, if set, otherwise <code>null</code>.
	 * 
	 * @return the border dash callback when element is hovered, if set, otherwise <code>null</code>.
	 */
	public ScaleBorderDashCallback getBorderDashCallback() {
		return borderDashCallback;
	}

	/**
	 * Sets the border dash callback when element is hovered.
	 * 
	 * @param borderDashCallback the border dash callback when element is hovered.
	 */
	public void setBorderDash(ScaleBorderDashCallback borderDashCallback) {
		// sets the callback
		this.borderDashCallback = borderDashCallback;
		// checks if callback is consistent
		if (borderDashCallback != null) {
			// adds the callback proxy function to java script object
			getAxis().getConfiguration().setCallback(getAxis().getScale().getAngleLines(), Property.BORDER_DASH, borderDashCallbackProxy.getProxy());
		} else {
			// otherwise sets null which removes the properties from java script object
			getAxis().getConfiguration().setCallback(getAxis().getScale().getAngleLines(), Property.BORDER_DASH, null);
		}
	}

	/**
	 * Returns an array of integer when the callback has been activated.
	 * 
	 * @param context native object as context.
	 * @param borderDashCallback border dash callback instance
	 * @return an array of integer
	 */
	private Array onBorderDash(ScaleScriptableContext context, ScaleBorderDashCallback borderDashCallback) {
		// gets value
		List<Integer> result = ScriptableUtils.getOptionValue(getAxis(), context, borderDashCallback);
		// default result
		return ArrayInteger.fromOrEmpty(result);
	}

}