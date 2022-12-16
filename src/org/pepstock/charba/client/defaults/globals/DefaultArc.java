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
package org.pepstock.charba.client.defaults.globals;

import org.pepstock.charba.client.defaults.IsDefaultArc;
import org.pepstock.charba.client.enums.BorderAlign;
import org.pepstock.charba.client.items.Undefined;

/**
 * CHART.JS default values for ARC element.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DefaultArc extends AbstractDefaultOptionsElement implements IsDefaultArc {

	private static final int DEFAULT_BORDER_WIDTH = 2;

	private static final String DEFAULT_BORDER_COLOR = "#fff";

	private static final double DEFAULT_WEIGHT = 1D;

	private static final int DEFAULT_OFFSET = 0;

	private static final int DEFAULT_BORDER_RADIUS = 0;

	private static final int DEFAULT_SPACING = 0;

	private static final boolean DEFAULT_CIRCULAR = true;

	/**
	 * Creates a default arc
	 */
	DefaultArc() {
		super(DEFAULT_BORDER_COLOR, DEFAULT_BORDER_WIDTH);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultArc#getBorderAlign()
	 */
	@Override
	public BorderAlign getBorderAlign() {
		return BorderAlign.CENTER;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultArc#getWeight()
	 */
	@Override
	public double getWeight() {
		return DEFAULT_WEIGHT;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultArc#getAngle()
	 */
	@Override
	public double getAngle() {
		return Undefined.DOUBLE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultArc#getOffset()
	 */
	@Override
	public int getOffset() {
		return DEFAULT_OFFSET;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultArc#getBorderRadius()
	 */
	@Override
	public int getBorderRadius() {
		return DEFAULT_BORDER_RADIUS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultArc#getHoverOffset()
	 */
	@Override
	public int getHoverOffset() {
		return getOffset();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultArc#getSpacing()
	 */
	@Override
	public int getSpacing() {
		return DEFAULT_SPACING;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultArc#isCircular()
	 */
	@Override
	public boolean isCircular() {
		return DEFAULT_CIRCULAR;
	}

}