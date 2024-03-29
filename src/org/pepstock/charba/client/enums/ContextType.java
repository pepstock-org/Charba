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
package org.pepstock.charba.client.enums;

import org.pepstock.charba.client.commons.Key;
import org.pepstock.charba.client.configuration.RadialPointLabels;

/**
 * Enumerates the type of scriptable options context.
 * 
 * @author Andrea "Stock" Stocchero
 */
public enum ContextType implements Key
{
	/**
	 * Chart context type.
	 */
	CHART("chart"),
	/**
	 * Dataset of chart context type.
	 */
	DATASET("dataset"),
	/**
	 * Data of dataset context type.
	 */
	DATA("data"),
	/**
	 * Scale of chart context type.
	 */
	SCALE("scale"),
	/**
	 * Tick of scale context type.
	 */
	TICK("tick"),
	/**
	 * Point label context type, only for {@link RadialPointLabels}.
	 */
	POINT_LABEL("pointLabel"),
	/**
	 * Tick of scale context type.
	 */
	TOOLTIP("tooltip"),
	/**
	 * DataLabels plugin context type.
	 */
	DATALABELS("datalabels"),
	/**
	 * Labels plugin context type.
	 */
	LABELS("labels"),
	/**
	 * Annotation plugin context type.
	 */
	ANNOTATION("annotation"),
	/**
	 * Zoom plugin context type.
	 */
	ZOOM("zoom"),
	/**
	 * Meter and gauge controllers context type.
	 */
	METER("meter"),
	/**
	 * Segment line context type.
	 */
	SEGMENT("segment"),
	/**
	 * Unknown context type.
	 */
	UNKNOWN("unknown");

	// name value of property
	private final String value;

	/**
	 * Creates with the property value to use in the native object.
	 * 
	 * @param value value of property name
	 */
	private ContextType(String value) {
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