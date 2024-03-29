/**
    Licensed to the Apache Software Foundation (ASF) under one
    or more contributor license agreements.  See the NOTICE file
    distributed with this work for additional information
    regarding copyright ownership.  The ASF licenses this file
    to you under the Apache License, Version 2.0 (the
    "License"); you may not use this file except in compliance
    with the License.  You may obtain a copy of the License at
    
      http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on an
    "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied.  See the License for the
    specific language governing permissions and limitations
    under the License.
*/
package org.pepstock.charba.client.geo;

import java.util.List;

import org.pepstock.charba.client.commons.AbstractNode;
import org.pepstock.charba.client.commons.ArrayListHelper;
import org.pepstock.charba.client.commons.ArrayObject;
import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.commons.NativeObject;
import org.pepstock.charba.client.commons.ObjectType;
import org.pepstock.charba.client.geo.enums.ClipMap;
import org.pepstock.charba.client.items.Undefined;

/**
 * Handles the common properties to configure GEO data set and options.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
class CommonOptionsHandler extends AbstractNode {

	// default value of show outline
	static final boolean DEFAULT_SHOW_LINE = false;

	/**
	 * Name of properties of native object.
	 */
	private enum Property implements Key
	{
		OUTLINE("outline"),
		SHOW_OUTLINE("showOutline"),
		SHOW_GRATICULE("showGraticule"),
		CLIP_MAP("clipMap");

		// name value of property
		private final String value;

		/**
		 * Creates with the property value to use in the native object.
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
	 * Creates an handler at data set level.
	 * 
	 * @param nativeObject native object of data set
	 */
	CommonOptionsHandler(NativeObject nativeObject) {
		super(nativeObject);
	}

	/**
	 * Sets the outline used to scale and centralize the projection in the chart area. By default a sphere is used.
	 * 
	 * @param outline the outline used to scale and centralize the projection in the chart area
	 */
	final void setOutline(Feature... outline) {
		setValueOrArray(Property.OUTLINE, outline);
	}

	/**
	 * Sets the outline used to scale and centralize the projection in the chart area. By default a sphere is used.
	 * 
	 * @param outline the outline used to scale and centralize the projection in the chart area
	 */
	final void setOutline(List<Feature> outline) {
		// checks if argument is consistent
		if (outline != null) {
			// creates array
			Feature[] features = new Feature[outline.size()];
			// loads all features in the array
			for (int i = 0; i < outline.size(); i++) {
				features[i] = outline.get(i);
			}
			// stores value
			setValueOrArray(Property.OUTLINE, features);
		} else {
			// if here the argument is null
			// then removes the property
			remove(Property.OUTLINE);
		}
	}

	/**
	 * Returns the outline used to scale and centralize the projection in the chart area. By default a sphere is used.
	 * 
	 * @return the outline used to scale and centralize the projection in the chart area
	 */
	final List<Feature> getOutline() {
		ArrayObject array = getArrayValue(Property.OUTLINE);
		return ArrayListHelper.list(array, Feature.FACTORY);
	}

	/**
	 * Sets <code>true</code> to render the outline in the background.
	 * 
	 * @param showOutline <code>true</code> to render the outline in the background
	 */
	final void setShowOutline(boolean showOutline) {
		setValue(Property.SHOW_OUTLINE, showOutline);
	}

	/**
	 * Returns <code>true</code> to render the outline in the background.
	 * 
	 * @return <code>true</code> to render the outline in the background
	 */
	final boolean isShowOutline() {
		return getValue(Property.SHOW_OUTLINE, DEFAULT_SHOW_LINE);
	}

	/**
	 * Sets <code>true</code> to render a graticule in the background.
	 *
	 * @param showGraticule <code>true</code> to render a graticule in the background
	 */
	final void setShowGraticule(boolean showGraticule) {
		setValue(Property.SHOW_GRATICULE, showGraticule);
	}

	/**
	 * Sets the graticule object to render the lines in the background.
	 *
	 * @param showGraticule the graticule to render the lines in the background
	 */
	final void setShowGraticule(Graticule showGraticule) {
		// checks if consistent
		if (showGraticule != null) {
			setValue(Property.SHOW_GRATICULE, showGraticule);
		} else {
			// if here, the argument is not consistent
			// then removes the property
			remove(Property.SHOW_GRATICULE);
		}
	}

	/**
	 * Returns <code>true</code> to render a graticule in the background.
	 *
	 * @return <code>true</code> to render a graticule in the background
	 */
	final boolean isShowGraticule() {
		// the default is based on the presence of the property
		return getValue(Property.SHOW_GRATICULE, has(Property.SHOW_GRATICULE));
	}

	/**
	 * Returns the graticule to render the lines in the background.
	 *
	 * @return the graticule to render the lines in the background or <code>null</code> is any object has been set
	 */
	final Graticule getShowGraticule() {
		// checks the property is set as object
		if (isType(Property.SHOW_GRATICULE, ObjectType.OBJECT)) {
			return new Graticule(getValue(Property.SHOW_GRATICULE));
		}
		// if here, the argument is not an object or missing
		// then returns null
		return null;
	}

	/**
	 * Sets whether to clip the rendering to the chart area of the graph.
	 * 
	 * @param clipMap whether to clip the rendering to the chart area of the graph
	 */
	final void setClipMap(ClipMap clipMap) {
		// checks if clip map is consistent
		if (Key.isValid(clipMap)) {
			// checks if a boolean value must be set
			if (ClipMap.TRUE.equals(clipMap)) {
				// stores true as boolean
				setValue(Property.CLIP_MAP, true);
			} else if (ClipMap.FALSE.equals(clipMap)) {
				// stores true as boolean
				setValue(Property.CLIP_MAP, false);
			} else {
				// stores the string value of clip map
				setValue(Property.CLIP_MAP, clipMap);
			}
		} else {
			// if here, the argument is not consistent
			// then removes the property
			remove(Property.CLIP_MAP);
		}
	}

	/**
	 * Returns whether to clip the rendering to the chart area of the graph.
	 * 
	 * @return whether to clip the rendering to the chart area of the graph
	 */
	final ClipMap getClipMap() {
		// checks if is a string
		if (isType(Property.CLIP_MAP, ObjectType.STRING)) {
			// the clip map is stored as string
			return Key.getKeyByValue(ClipMap.values(), getValue(Property.CLIP_MAP, ClipMap.FALSE));
		} else if (isType(Property.CLIP_MAP, ObjectType.BOOLEAN)) {
			// the clip map is stored as boolean
			// checks what it has to return
			if (getValue(Property.CLIP_MAP, Undefined.BOOLEAN)) {
				return ClipMap.TRUE;
			}
			// if here is false
			return ClipMap.FALSE;
		}
		// if here the property is not set
		// then returns default
		return ClipMap.FALSE;
	}
}