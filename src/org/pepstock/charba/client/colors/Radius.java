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
package org.pepstock.charba.client.colors;

/**
 * Contains the radius of inner and outer of an arc.<br>
 * Used inside the canvas object factory.
 * 
 * @author Andrea "Stock" Stocchero
 * @see CanvasObjectFactory
 *
 */
public final class Radius {

	private double inner = 0D;

	private double outer = 0D;

	/**
	 * Returns the inner radius of arc.
	 * 
	 * @return the inner radius of arc
	 */
	public double getInner() {
		return inner;
	}

	/**
	 * Sets the inner radius of arc.
	 * 
	 * @param inner the inner radius of arc
	 */
	public void setInner(double inner) {
		this.inner = inner;
	}

	/**
	 * Returns the outer radius if an arc.
	 * 
	 * @return the outer radius if an arc
	 */
	public double getOuter() {
		return outer;
	}

	/**
	 * Sets the outer radius if an arc.
	 * 
	 * @param outer the outer radius if an arc to set
	 */
	public void setOuter(double outer) {
		this.outer = outer;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Radius [inner=" + inner + ", outer=" + outer + "]";
	}

}