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
import org.pepstock.charba.client.dom.safehtml.SafeHtml;
import org.pepstock.charba.client.impl.plugins.HtmlLegend;
import org.pepstock.charba.client.items.HasLegendText;

/**
 * This callback is the base interface for HTML legend callbacks to change the text of legend for a specific item or legend's title, as HTML.<br>
 * This callback is used ONLY by {@link HtmlLegend} plugin.
 * 
 * @author Andrea "Stock" Stocchero
 * @param <T> type of legend element which contains the text
 */
public interface HtmlLegendCallback<T extends HasLegendText> {

	/**
	 * Returns a text of legend for a specific item or legend's title, as HTML
	 * 
	 * @param chart chart instance
	 * @param item item which represents the text to create
	 * @param currentText current text provided by legend labels callback.
	 * @return HTML legend representation as SafeHTML
	 */
	SafeHtml generateText(IsChart chart, T item, String currentText);

}