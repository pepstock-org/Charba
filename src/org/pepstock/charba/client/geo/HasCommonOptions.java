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

import java.util.Collections;
import java.util.List;

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
	CommonOptionsHandler getHandler();

	/**
	 * Sets the outline used to scale and centralize the projection in the chart area. By default a sphere is used.
	 * 
	 * @param outline the outline used to scale and centralize the projection in the chart area
	 */
	default void setOutline(Feature... outline) {
		// checks if handler is consistent
		if (getHandler() != null) {
			// stores value
			getHandler().setOutline(outline);
		}
	}

	/**
	 * Sets the outline used to scale and centralize the projection in the chart area. By default a sphere is used.
	 * 
	 * @param outline the outline used to scale and centralize the projection in the chart area
	 */
	default void setOutline(List<Feature> outline) {
		// checks if handler is consistent
		if (getHandler() != null) {
			// stores value
			getHandler().setOutline(outline);
		}
	}

	/**
	 * Returns the outline used to scale and centralize the projection in the chart area. By default a sphere is used.
	 * 
	 * @return the outline used to scale and centralize the projection in the chart area
	 */
	default List<Feature> getOutline() {
		// checks if handler is consistent
		if (getHandler() != null) {
			// returns value
			return getHandler().getOutline();
		}
		// if here, handler is not consistent
		// then returns default
		return Collections.emptyList();
	}

	/**
	 * Sets <code>true</code> to render the outline in the background.
	 * 
	 * @param showOutline <code>true</code> to render the outline in the background
	 */
	default void setShowOutline(boolean showOutline) {
		// checks if handler is consistent
		if (getHandler() != null) {
			// stores value
			getHandler().setShowOutline(showOutline);
		}
	}

	/**
	 * Returns <code>true</code> to render the outline in the background.
	 * 
	 * @return <code>true</code> to render the outline in the background
	 */
	default boolean isShowOutline() {
		// checks if handler is consistent
		if (getHandler() != null) {
			// returns value
			return getHandler().isShowOutline();
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
		if (getHandler() != null) {
			// stores value
			getHandler().setShowGraticule(showGraticule);
		}
	}

	/**
	 * Sets the graticule object to render the lines in the background.
	 *
	 * @param showGraticule the graticule to render the lines in the background
	 */
	default void setShowGraticule(Graticule showGraticule) {
		// checks if handler is consistent
		if (getHandler() != null) {
			// stores value
			getHandler().setShowGraticule(showGraticule);
		}
	}

	/**
	 * Returns <code>true</code> to render a graticule in the background.
	 *
	 * @return <code>true</code> to render a graticule in the background
	 */
	default boolean isShowGraticule() {
		// checks if handler is consistent
		if (getHandler() != null) {
			// returns value
			return getHandler().isShowGraticule();
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
		if (getHandler() != null) {
			// returns value
			return getHandler().getShowGraticule();
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
		if (getHandler() != null) {
			// stores value
			getHandler().setClipMap(clipMap);
		}
	}

	/**
	 * Returns whether to clip the rendering to the chart area of the graph.
	 * 
	 * @return whether to clip the rendering to the chart area of the graph
	 */
	default ClipMap getClipMap() {
		// checks if handler is consistent
		if (getHandler() != null) {
			// returns value
			return getHandler().getClipMap();
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