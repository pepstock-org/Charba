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
package org.pepstock.charba.client.gradient;

import org.pepstock.charba.client.resources.AbstractInjectableResource;
import org.pepstock.charba.client.resources.ResourceName;

/**
 * Contains the content of <code>chartjs-plugin-gradient.min.js</code> to inject.<br>
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class GradientPluginResource extends AbstractInjectableResource {

	// encoded javascript content of chartjs-plugin-gradient.min.js
	private static final String[] CONTENT = {
		"/*!",
		" * chartjs-plugin-gradient v0.2.2",
		" * https://github.com/kurkle/chartjs-plugin-gradient#readme",
		" * (c) 2022 Jukka Kurkela",
		" * Released under the MIT License",
		" */",
		"!function(t,e){\"object\"==typeof exports&&\"undefined\"!=typeof module?module.exports=e(require(\"chart.js\"),require(\"chart.js/helpers\")):\"function\"==typeof define&&define.amd?define([\"chart.js\",\"chart.js/helpers\"],e):(t=\"undefined\"!=typeof globalThis?globalThis:t||self)[\"chartjs-plugin-gradient\"]=e(t.Chart,t.Chart.helpers)}(this,(function(t,e){\"use strict\";function r(t,e,r){return\"r\"===e?t.createRadialGradient(r.xCenter,r.yCenter,0,r.xCenter,r.yCenter,r.drawingArea):\"y\"===e?t.createLinearGradient(0,r.bottom,0,r.top):t.createLinearGradient(r.left,0,r.right,0)}const n=t.Chart.version?(t,e)=>t.parse(e):(t,e)=>e;function o(t,r){if(\"radialLinear\"===t.type){const e=t.getDistanceFromCenterForValue(r);return{pixel:e,stop:e/t.drawingArea}}const o=t.options.reverse,a=function(t,r){const o=e.isNumber(r)?parseFloat(r):n(t,r);return t.getPixelForValue(o)}(t,r),i=t.getDecimalForPixel(a);return{pixel:a,stop:o?1-i:i}}function a(t,r,n){for(const a of Object.keys(n)){const{pixel:i,",
		"stop:s}=o(r,a);isFinite(i)&&isFinite(s)&&t.addColorStop(Math.max(0,Math.min(1,s)),e.color(n[a]).rgbString())}}function i(t,e,r,n){e[r]=n,t.dataset&&(t.dataset.options?t.dataset.options[r]=n:t.dataset[r]=n)}const s=t.Chart.version?(t,e)=>t[e+\"Scale\"]:(t,e)=>t.controller[\"_\"+e+\"Scale\"];return{id:\"gradient\",beforeDatasetsUpdate(t){if(!(t=>t&&t.right>t.left&&t.bottom>t.top)(t.chartArea))return;const e=t.ctx,n=t.data.datasets;for(let o=0;o<n.length;o++){const c=n[o],l=c.gradient;if(!l)continue;const d=t.getDatasetMeta(o);for(const[n,f]of Object.entries(l)){const{axis:l,colors:p}=f,u=s(d,l);if(u){if(u){const t=r(e,l,u);a(t,u,p),i(d,c,n,t)}}else console.warn(`Scale not found for '${l}'-axis in datasets[${o}] of chart id ${t.id}, skipping.`)}}}}}));",
		"//# sourceMappingURL=chartjs-plugin-gradient.min.js.map"
	};
	
	/**
	 * Creates the injectable resource with <code>chartjs-plugin-gradient.min.js</code> content.
	 */
	GradientPluginResource() {
		super(ResourceName.GRADIENT_PLUGIN, CONTENT);
	}

}
