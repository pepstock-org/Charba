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
package org.pepstock.charba.client.treemap.callbacks;

import java.util.List;

import org.pepstock.charba.client.callbacks.DatasetContext;
import org.pepstock.charba.client.callbacks.Scriptable;
import org.pepstock.charba.client.treemap.TreeMapChart;

/**
 * Callback interface of {@link TreeMapChart} controller to set <code>formatter</code> property at runtime, using the dataset context.<br>
 * Data values are converted to string.<br>
 * If values are grouped, the value of the group and the value (as string) are shown.<br>
 * This default behavior can be overridden by this interface implementation.<br>
 * A formatter can return a {@link String} (for a single line) or a {@link List} of {@link String}s (for multiple lines, where each item represents a new line).
 * 
 * @author Andrea "Stock" Stocchero
 */
public interface FormatterCallback extends Scriptable<Object, DatasetContext> {

}