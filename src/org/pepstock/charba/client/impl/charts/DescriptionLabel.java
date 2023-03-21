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
package org.pepstock.charba.client.impl.charts;

import org.pepstock.charba.client.callbacks.MeterContentCallback;
import org.pepstock.charba.client.defaults.IsDefaultFont;

/**
 * Meter element class to define the description to render in the meter chart under the {@link ValueLabel}.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public final class DescriptionLabel extends AbstractMeterElement {

	private String content = null;

	private MeterContentCallback contentCallback = null;

	/**
	 * Creates the element getting the font defaults as argument.
	 * 
	 * @param defaultValues font defaults to use to initialize the font option
	 */
	DescriptionLabel(IsDefaultFont defaultValues) {
		super(defaultValues);
		// disables the display
		setDisplay(false);
	}

	/**
	 * Sets the content of the label.
	 * 
	 * @param content the content of the label
	 */
	public void setContent(String content) {
		// resets callback
		setContent((MeterContentCallback) null);
		// stores value
		this.content = content;
	}

	/**
	 * Returns the content of the label.
	 * 
	 * @return the content of the label
	 */
	public String getContent() {
		return content;
	}

	// --------------------------------------
	// CALLBACK
	// --------------------------------------

	/**
	 * Returns the callback to customize the description label string in the chart.
	 * 
	 * @return the callback to customize the description label string in the chart
	 */
	public MeterContentCallback getContentCallback() {
		return contentCallback;
	}

	/**
	 * Sets the callback to customize the description label string in the chart.
	 * 
	 * @param contentCallback the callback to description label the value string in the chart
	 */
	public void setContent(MeterContentCallback contentCallback) {
		this.contentCallback = contentCallback;
	}
}