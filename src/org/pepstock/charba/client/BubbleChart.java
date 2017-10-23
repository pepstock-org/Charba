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
package org.pepstock.charba.client;

import org.pepstock.charba.client.data.BubbleDataset;
import org.pepstock.charba.client.options.BubbleOptions;

public class BubbleChart extends AbstractChart<BubbleOptions, BubbleDataset> {
	
	private final BubbleOptions options;
	
	public BubbleChart() {
		options = new BubbleOptions(this);
	}

	@Override
	public Type getType() {
		return Type.bubble;
	}

	@Override
	public BubbleOptions getOptions() {
		return options;
	}

	@Override
	public BubbleDataset newDataset() {
		return new BubbleDataset();
	}

}