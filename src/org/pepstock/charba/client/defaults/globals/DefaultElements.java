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
import org.pepstock.charba.client.defaults.IsDefaultBar;
import org.pepstock.charba.client.defaults.IsDefaultElements;
import org.pepstock.charba.client.defaults.IsDefaultLine;
import org.pepstock.charba.client.defaults.IsDefaultPoint;

/**
 * CHART.JS default values for ELEMENTS element.
 * 
 * @author Andrea "Stock" Stocchero
 */
public class DefaultElements implements IsDefaultElements {

	private final IsDefaultArc arc;

	private final IsDefaultLine line;

	private final IsDefaultPoint point;

	private final IsDefaultBar bar;

	/**
	 * Creates the object using inner default elements.
	 */
	DefaultElements() {
		this(new DefaultArc(), new DefaultLine(), new DefaultPoint(), new DefaultBar());
	}

	/**
	 * Creates the object using the inner elements are arguments.
	 * 
	 * @param arc arc element instance
	 * @param line line element instance
	 * @param point point element instance
	 * @param bar bar element instance
	 */
	protected DefaultElements(IsDefaultArc arc, IsDefaultLine line, IsDefaultPoint point, IsDefaultBar bar) {
		super();
		this.arc = arc;
		this.line = line;
		this.point = point;
		this.bar = bar;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.IsDefaultOptions#getArc()
	 */
	@Override
	public final IsDefaultArc getArc() {
		return arc;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.IsDefaultOptions#getLine()
	 */
	@Override
	public final IsDefaultLine getLine() {
		return line;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.IsDefaultOptions#getPoint()
	 */
	@Override
	public final IsDefaultPoint getPoint() {
		return point;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.options.IsDefaultOptions#getBar()
	 */
	@Override
	public final IsDefaultBar getBar() {
		return bar;
	}

}