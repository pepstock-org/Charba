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

import org.pepstock.charba.client.data.DoughnutDataset;
import org.pepstock.charba.client.options.DoughnutOptions;

public final class DoughnutChart extends AbstractChart<DoughnutOptions, DoughnutDataset> {
	
	private final DoughnutOptions options;
	
	public DoughnutChart() {
		options = new DoughnutOptions(this);
	}

	@Override
	public Type getType() {
		return Type.pie;
	}

	@Override
	public DoughnutOptions getOptions() {
		return options;
	}

	@Override
	public DoughnutDataset newDataset() {
		return new DoughnutDataset();
	}
	
}