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
package org.pepstock.charba.client.callbacks;

/**
 * Interface to map all callback of scriptable options available for CHART.JS.
 * 
 * @author Andrea "Stock" Stocchero
 * 
 * @param <T> type of result of invocation method.
 * @param <C> type of context to pass to the callback.
 */
public interface Scriptable<T, C extends ChartContext> {

	/**
	 * Returns the value of property at runtime.
	 * 
	 * @param context context instance
	 * @return value of property to be applied
	 */
	T invoke(C context);

}