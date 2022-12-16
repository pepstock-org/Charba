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
package org.pepstock.charba.client.impl.plugins;

import java.util.List;

import org.pepstock.charba.client.enums.DefaultScaleId;
import org.pepstock.charba.client.enums.InteractionAxis;
import org.pepstock.charba.client.enums.ModifierKey;
import org.pepstock.charba.client.options.ScaleId;

/**
 * Maps default configuration options of {@link Crosshair#ID} plugin.
 * 
 * @author Andrea "Stock" Stocchero
 */
interface IsCrosshairDefaultOptions {

	/**
	 * Returns the options to configure the label at X position.
	 * 
	 * @return the options to configure the label at X position
	 */
	IsCrosshairDefaultLabel getXLabel();

	/**
	 * Returns the options to configure the label at Y position.
	 * 
	 * @return the options to configure the label at Y position
	 */
	IsCrosshairDefaultLabel getYLabel();

	/**
	 * Returns <code>true</code> if plugin is enabled.
	 * 
	 * @return <code>true</code> if plugin is enabled.
	 */
	default boolean isEnabled() {
		return CrosshairOptions.DEFAULT_ENABLED;
	}

	/**
	 * Returns the interaction axis mode.
	 * 
	 * @return the interaction axis mode
	 */
	default InteractionAxis getMode() {
		return CrosshairOptions.DEFAULT_MODE;
	}

	/**
	 * Returns the color of the line.
	 * 
	 * @return the color of the line.
	 */
	default String getLineColorAsString() {
		return CrosshairOptions.DEFAULT_LINE_COLOR_AS_STRING;
	}

	/**
	 * Sets the width of the line.
	 * 
	 * @return the width of the line
	 */
	default int getLineWidth() {
		return CrosshairOptions.DEFAULT_LINE_WIDTH;
	}

	/**
	 * Returns the line dash pattern used when stroking lines, using an array of values which specify alternating lengths of lines and gaps which describe the pattern.
	 * 
	 * @return the line dash pattern used when stroking lines
	 */
	default List<Integer> getLineDash() {
		return CrosshairOptions.DEFAULT_LINE_DASH;
	}

	/**
	 * Returns the line dash pattern offset.
	 * 
	 * @return offset for line dashes.
	 */
	default double getLineDashOffset() {
		return CrosshairOptions.DEFAULT_LINE_DASH_OFFSET;
	}

	/**
	 * Returns the ID of the X scale to bind onto.
	 * 
	 * @return the ID of the X scale to bind onto
	 */
	default ScaleId getXScaleID() {
		return DefaultScaleId.X;
	}

	/**
	 * Returns the ID of the Y scale to bind onto.
	 * 
	 * @return the ID of the Y scale to bind onto
	 */
	default ScaleId getYScaleID() {
		return DefaultScaleId.Y;
	}

	/**
	 * Returns the modifier key to activate the crosshair.
	 * 
	 * @return the modifier key to activate the crosshair
	 */
	default ModifierKey getModifierKey() {
		return null;
	}
}