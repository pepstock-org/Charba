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
package org.pepstock.charba.client.geo;

import org.pepstock.charba.client.geo.enums.ClipMap;

/**
 * Maps all options which are common between GEO options and dataset.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
interface HasCommonOptions {

/**
	 * Returns the common options handler.
	 * 
	 * @return the common options handler
	 */
	CommonOptionsHandler getCommonOptionsHandler();

	/**
	 * Sets the outline used to scale and centralize the projection in the chart area.
	 * By default a sphere is used.
	 * 
	 * @param outline the outline used to scale and centralize the projection in the chart area
	 */
	default void setOutline(Feature outline) {
		// checks if handler is consistent
		if (getCommonOptionsHandler() != null) {
			// stores value
			getCommonOptionsHandler().setOutline(outline);
		}
	}

	/**
	 * Returns the outline used to scale and centralize the projection in the chart area.
	 * By default a sphere is used.
	 * 
	 * @return the outline used to scale and centralize the projection in the chart area
	 */
	default Feature getOutline() {
		// checks if handler is consistent
		if (getCommonOptionsHandler() != null) {
			// returns value
			return getCommonOptionsHandler().getOutline();
		}
		// if here, handler is not consistent
		// then returns default
		return CommonOptionsHandler.DEFAULT_FEATURE;
	}

	/**
	 * Sets <code>true</code> to render the outline in the background.
	 * 
	 * @param showOutline <code>true</code> to render the outline in the background
	 */
	default void setShowOutline(boolean showOutline) {
		// checks if handler is consistent
		if (getCommonOptionsHandler() != null) {
			// stores value
			getCommonOptionsHandler().setShowOutline(showOutline);
		}
	}

	/**
	 * Returns <code>true</code> to render the outline in the background.
	 * 
	 * @return <code>true</code> to render the outline in the background
	 */
	default boolean isShowOutline() {
		// checks if handler is consistent
		if (getCommonOptionsHandler() != null) {
			// returns value
			return getCommonOptionsHandler().isShowOutline();
		}
		// if here, handler is not consistent
		// then returns default
		return CommonOptionsHandler.DEFAULT_SHOW_LINE;
	}

	/**
	 * Sets <code>true</code> to render the lines in the background.
	 *
	 * @param showGraticule <code>true</code> to render the lines in the background
	 */
	default void setShowGraticule(boolean showGraticule) {
		// checks if handler is consistent
		if (getCommonOptionsHandler() != null) {
			// stores value
			getCommonOptionsHandler().setShowGraticule(showGraticule);
		}
	}

	/**
	 * Sets the graticule object to render the lines in the background.
	 *
	 * @param showGraticule the graticule to render the lines in the background
	 */
	default void setShowGraticule(Graticule showGraticule) {
		// checks if handler is consistent
		if (getCommonOptionsHandler() != null) {
			// stores value
			getCommonOptionsHandler().setShowGraticule(showGraticule);
		}
	}

	/**
	 * Returns <code>true</code> to render a graticule in the background.
	 *
	 * @return <code>true</code> to render a graticule in the background
	 */
	default boolean isShowGraticule() {
		// checks if handler is consistent
		if (getCommonOptionsHandler() != null) {
			// returns value
			return getCommonOptionsHandler().isShowGraticule();
		}
		// if here, handler is not consistent
		// then returns default
		return false;
	}

	/**
	 * Returns the graticule to render the lines in the background.
	 *
	 * @return the graticule to render the lines in the background or <code>null</code> is any object has been set
	 */
	default Graticule getShowGraticule() {
		// checks if handler is consistent
		if (getCommonOptionsHandler() != null) {
			// returns value
			return getCommonOptionsHandler().getShowGraticule();
		}
		// if here, handler is not consistent
		// then returns default
		return null;
	}

	/**
	 * Sets whether to clip the rendering to the chart area of the graph.
	 * 
	 * @param clipMap whether to clip the rendering to the chart area of the graph
	 */
	default void setClipMap(ClipMap clipMap) {
		// checks if handler is consistent
		if (getCommonOptionsHandler() != null) {
			// stores value
			getCommonOptionsHandler().setClipMap(clipMap);
		}
	}

	/**
	 * Returns whether to clip the rendering to the chart area of the graph.
	 * 
	 * @return whether to clip the rendering to the chart area of the graph
	 */
	default ClipMap getClipMap() {
		// checks if handler is consistent
		if (getCommonOptionsHandler() != null) {
			// returns value
			return getCommonOptionsHandler().getClipMap();
		}
		// if here, handler is not consistent
		// then returns default
		return null;
	}
	
	/**
	 * Sets whether to clip the rendering to the chart area of the graph.
	 * 
	 * @param clipMap whether to clip the rendering to the chart area of the graph
	 */
	default void setClipMap(boolean clipMap) {
		setClipMap(clipMap ? ClipMap.TRUE : ClipMap.FALSE);
	}

	/**
	 * Returns whether to clip the rendering to the chart area of the graph.
	 * 
	 * @return whether to clip the rendering to the chart area of the graph
	 */
	default boolean isClipMap() {
		return !ClipMap.FALSE.equals(getClipMap());
	}
}
