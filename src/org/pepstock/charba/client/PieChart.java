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

import org.pepstock.charba.client.data.PieDataset;
import org.pepstock.charba.client.options.PieOptions;

public final class PieChart extends AbstractChart<PieOptions, PieDataset> {
	
	private final PieOptions options;
	
	public PieChart() {
		options = new PieOptions(this);
	}

	@Override
	public Type getType() {
		return Type.pie;
	}

	@Override
	public PieOptions getOptions() {
		return options;
	}

	@Override
	public PieDataset newDataset() {
		return new PieDataset();
	}
}