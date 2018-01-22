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
package org.pepstock.charba.client.defaults.scale;

import java.util.List;

import org.pepstock.charba.client.commons.AbstractList;
import org.pepstock.charba.client.commons.GenericJavaScriptObject;
import org.pepstock.charba.client.commons.JsObjectArrayList;
import org.pepstock.charba.client.commons.JsObjectContainerArrayList;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.defaults.AbstractItem;

/**
 * The configuration element which contains all axes definitions.<br>
 * It maps the CHART.JS object of default, <code>chart.defaults.[chart_type].scales</code>.<br>
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public class Scales extends AbstractItem {

	private static final boolean DEFAULT_DISPLAY = true;
	// buffer to maintain axes
	private final AbstractList<Scale> yAxes = new JsObjectContainerArrayList<Scale>();
	// buffer to maintain axes
	private final AbstractList<Scale> xAxes = new JsObjectContainerArrayList<Scale>();

	/**
	 * Name of fields of JavaScript object.
	 */
	enum Property implements Key
	{
		display,
		xAxes,
		yAxes
	}

	/**
	 * Builds the object with parent item and child. 
	 * 
	 * @param parent parent item.
	 * @param childKey key of child.
	 */
	protected Scales(AbstractItem parent, Key childKey) {
		super(parent, childKey);
		if (!isEmpty()){
			// cast object to reach some methods
			InternalJavaScriptObject obj = (InternalJavaScriptObject)getJavaScriptObject();
			// loads axes
			loadScales(Property.xAxes, obj, xAxes);
			loadScales(Property.yAxes, obj, yAxes);
		}
	}

	/**
	 * Sets if the scales are shown.
	 * 
	 * @param display if the scales are shown.
	 */
	public void setDisplay(boolean display) {
		setValue(Property.display, display);
		// checks if all parents are attached
		checkAndAddToParent();
	}

	/**
	 * Returns if the scales are shown.
	 * 
	 * @return the scales are shown. Default is true.
	 */
	public boolean isDisplay() {
		return getValue(Property.display, DEFAULT_DISPLAY);
	}

	/**
	 * @return the xAxes
	 */
	public List<Scale> getXAxes() {
		return xAxes;
	}

	/**
	 * @return the yAxes
	 */
	public List<Scale> getYAxes() {
		return yAxes;
	}
	
	/**
	 * Loads the axes configuration.
	 * @param key java script object key to load.
	 * @param obj java script object to load.
	 * @param axes list of axes to load.
	 */
	private void loadScales(Key key, InternalJavaScriptObject obj, List<Scale> axes){
		// loads scale
		JsObjectArrayList<GenericJavaScriptObject> axesList = obj.getAxis(key);
		// scans java script objects
		for (GenericJavaScriptObject jsobj : axesList){
			axes.add(new Scale(jsobj));
		}
	}
	
	/**
	 * Is a generic java script object which enables the protected methods.
	 * 
	 * @author Andrea "Stock" Stocchero
	 *
	 */
	protected static class InternalJavaScriptObject extends GenericJavaScriptObject{
		
		/**
		 * Needed for GWt injection
		 */
		protected InternalJavaScriptObject() {
			// do nothing
		}
		
		/**
		 * Returns the axes configuration.
		 * @param key java script object key of axis.
		 * @return array list of java script objects
		 */
		protected final JsObjectArrayList<GenericJavaScriptObject> getAxis(Key key) {
			return getObjectArray(key.name());
		}
		
	}
}