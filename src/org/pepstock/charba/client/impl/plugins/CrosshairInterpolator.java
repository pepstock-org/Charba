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
package org.pepstock.charba.client.impl.plugins;

import java.util.LinkedList;
import java.util.List;

import org.pepstock.charba.client.IsChart;
import org.pepstock.charba.client.callbacks.ChartContext;
import org.pepstock.charba.client.callbacks.DatasetContext;
import org.pepstock.charba.client.callbacks.ParsedData;
import org.pepstock.charba.client.commons.IsPoint;
import org.pepstock.charba.client.data.Dataset;
import org.pepstock.charba.client.items.ChartElement;
import org.pepstock.charba.client.items.DatasetItem;
import org.pepstock.charba.client.items.ScaleItem;
import org.pepstock.charba.client.items.ScaleValueItem;
import org.pepstock.charba.client.items.Undefined;

/**
 * @author Andrea "Stock" Stocchero
 *
 */
public final class CrosshairInterpolator {

	public CrosshairInterpolator() {
	}

	public static List<Double> interpolate(IsChart chart, IsPoint point) {
		List<Double> result = new LinkedList<>();
		List<Dataset> datasets = chart.getData().getDatasets();
		for (int i = 0; i < datasets.size(); i++) {
			if (chart.isDatasetVisible(i)) {
				NextPrev np = getNextPrev(chart, point, i);
				if (np.isValid()) {
					double slope = (np.next.getY() - np.prev.getY()) / (np.next.getX() - np.prev.getX());
					double interpolatedValue = np.prev.getY() + (np.value - np.prev.getX()) * slope;
					result.add(interpolatedValue);
				} else {
					result.add(Undefined.DOUBLE);
				}
			} else {
				result.add(Undefined.DOUBLE);
			}
		}
		return result;
	}

	private static NextPrev getNextPrev(IsChart chart, IsPoint point, int i) {
		NextPrev np = new NextPrev();
		DatasetItem item = chart.getDatasetItem(i);
		List<ChartElement> elements = item.getElements();
		ScaleItem scale = item.getXScale();
		ScaleValueItem xValue = scale.getValueAtPixel(point.getX());
		np.value = xValue.getValue();
		for (ChartElement el : elements) {
			ChartContext c = el.getContext();
			if (c instanceof DatasetContext) {
				DatasetContext dc = (DatasetContext) c;
				ParsedData data = dc.getParsedData();
				if (xValue.getValue() > data.getX()) {
					np.prev = data;
				} else {
					np.next = data;
					return np;
				}
			}
		}
		return np;
	}

	private static class NextPrev {

		private double value = Undefined.DOUBLE;

		private ParsedData next = null;

		private ParsedData prev = null;

		private boolean isValid() {
			return next != null && prev != null;
		}
	}
}
