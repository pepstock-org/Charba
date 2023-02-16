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

import org.pepstock.charba.client.defaults.IsDefaultLine;
import org.pepstock.charba.client.enums.CapStyle;
import org.pepstock.charba.client.enums.CubicInterpolationMode;
import org.pepstock.charba.client.enums.Fill;
import org.pepstock.charba.client.enums.IsFill;
import org.pepstock.charba.client.enums.JoinStyle;
import org.pepstock.charba.client.enums.Stepped;
import org.pepstock.charba.client.items.FillBaseline;
import org.pepstock.charba.client.items.FillColors;

/**
 * CHART.JS default values for LINE element.
 * 
 * @author Andrea "Stock" Stocchero
 */
public final class DefaultLine extends AbstractDefaultOptionsElement implements IsDefaultLine {

	// default line tension
	private static final double DEFAULT_TENSION = 0D;
	// default border width
	private static final int DEFAULT_BORDER_WIDTH = 3;
	// default border dash offset
	private static final double DEFAULT_BORDER_DASH_OFFSET = 0D;
	// default cap bezier points
	private static final boolean DEFAULT_CAP_BEZIER_POINTS = true;

	/**
	 * To avoid any instantiation
	 */
	DefaultLine() {
		super(DEFAULT_BORDER_WIDTH);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.elements.line.IsDefaultLine#getTension()
	 */
	@Override
	public double getTension() {
		return DEFAULT_TENSION;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.elements.line.IsDefaultLine#getBorderCapStyle()
	 */
	@Override
	public CapStyle getBorderCapStyle() {
		return CapStyle.BUTT;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.elements.line.IsDefaultLine#getBorderDashOffset()
	 */
	@Override
	public double getBorderDashOffset() {
		return DEFAULT_BORDER_DASH_OFFSET;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.elements.line.IsDefaultLine#getBorderJoinStyle()
	 */
	@Override
	public JoinStyle getBorderJoinStyle() {
		return JoinStyle.MITER;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.elements.line.IsDefaultLine#isCapBezierPoints()
	 */
	@Override
	public boolean isCapBezierPoints() {
		return DEFAULT_CAP_BEZIER_POINTS;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultLine#getCubicInterpolationMode()
	 */
	@Override
	public CubicInterpolationMode getCubicInterpolationMode() {
		return CubicInterpolationMode.DEFAULT;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.elements.line.IsDefaultLine#getFill()
	 */
	@Override
	public IsFill getFill() {
		return Fill.FALSE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultFillHandler#getFillBaseline()
	 */
	@Override
	public FillBaseline getFillBaseline() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultFillHandler#getFillColors()
	 */
	@Override
	public FillColors getFillColors() {
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.defaults.IsDefaultLine#getStepped()
	 */
	@Override
	public Stepped getStepped() {
		return Stepped.FALSE;
	}

}