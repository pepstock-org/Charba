/**
    Copyright 2017 Andrea "Stock" Stocchero

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

	    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
*/
package org.pepstock.charba.client.callbacks;

import java.util.Collections;
import java.util.List;

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.items.TooltipItem;

/**
 * Abstract implementation of tooltip title callback in order to help who will implement it to override ONLY needed methods and
 * use the default for the others.
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
public abstract class AbstractTooltipTitleCallback implements TooltipTitleCallback {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.callbacks.TooltipTitleCallback#onBeforeTitle(org.pepstock.charba.client.IsChart,
	 * java.util.List)
	 */
	@Override
	public List<String> onBeforeTitle(IsChart chart, List<TooltipItem> items) {
		return Collections.emptyList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.callbacks.TooltipTitleCallback#onTitle(org.pepstock.charba.client.IsChart,
	 * java.util.List)
	 */
	@Override
	public List<String> onTitle(IsChart chart, List<TooltipItem> items) {
		return Collections.emptyList();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.pepstock.charba.client.callbacks.TooltipTitleCallback#onAfterTitle(org.pepstock.charba.client.IsChart,
	 * java.util.List)
	 */
	@Override
	public List<String> onAfterTitle(IsChart chart, List<TooltipItem> items) {
		return Collections.emptyList();
	}

}
