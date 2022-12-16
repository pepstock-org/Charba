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

import org.pepstock.charba.client.IsChart;

/**
 * Allows sorting of items, for tooltip or legend.
 * 
 * @author Andrea "Stock" Stocchero
 * @param <T> type of item to be sorted
 */
interface ItemSortCallback<T> {

	/**
	 * Allows sorting of items, for tooltip or legend
	 * 
	 * @param chart chart instance
	 * @param item1 the first object to be compared.
	 * @param item2 the second object to be compared.
	 * @return a negative integer, zero, or a positive integer as the first argument is less than, equal to, or greater than the second.
	 */
	int onItemSort(IsChart chart, T item1, T item2);

}