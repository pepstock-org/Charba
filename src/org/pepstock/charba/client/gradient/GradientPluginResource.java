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
		" * chartjs-plugin-gradient v0.6.1",
		" * https://github.com/kurkle/chartjs-plugin-gradient#readme",
		" * (c) 2022 Jukka Kurkela",
		" * Released under the MIT License",
		" */",
		"!function(t,e){\"object\"==typeof exports&&\"undefined\"!=typeof module?module.exports=e(require(\"chart.js/helpers\"),require(\"chart.js\")):\"function\"==typeof define&&define.amd?define([\"chart.js/helpers\",\"chart.js\"],e):(t=\"undefined\"!=typeof globalThis?globalThis:t||self)[\"chartjs-plugin-gradient\"]=e(t.Chart.helpers,t.Chart)}(this,(function(t,e){\"use strict\";const o=e.Chart.version,n=o?(t,e)=>t.parse(e):(t,e)=>e;const s=t=>t&&t.right>t.left&&t.bottom>t.top;function r(t,e,o){return\"r\"===e?t.createRadialGradient(o.xCenter,o.yCenter,0,o.xCenter,o.yCenter,o.drawingArea):\"y\"===e?t.createLinearGradient(0,o.bottom,0,o.top):t.createLinearGradient(o.left,0,o.right,0)}function a(t,e){e.forEach((function(e){t.addColorStop(e.stop,e.color.rgbString())}))}function i(t,e,o){if(t.options.has(e.key)){const n=t.options.get(e.key).filter((t=>t.datasetIndex===o));if(n.length)return n[0]}}function l(e,o){if(\"radialLinear\"===e.type){const t=e.getDistanceFromCenterForValue(o);return{pixel",
		":t,stop:t/e.drawingArea}}const s=e.options.reverse,r=function(e,o){const s=t.isNumber(o)?parseFloat(o):n(e,o);return e.getPixelForValue(s)}(e,o),a=e.getDecimalForPixel(r);return{pixel:r,stop:s?1-a:a}}const c=t=>t<=.0031308?12.92*t:1.055*Math.pow(t,1/2.4)-.055,d=t=>t<=.04045?t/12.92:Math.pow((t+.055)/1.055,2.4);function f(e,o,n,s){const r=i(e,o,n);if(!r||!r.stopColors.length)return;const{stop:a}=l(r.scale,s);let f,p;for(const t of r.stopColors){if(t.stop===a)return t.color;t.stop<a?f=t:t.stop>a&&!p&&(p=t)}return p?function(e,o,n){const s=o.color.rgb,r=d(s.r/255),a=d(s.g/255),i=d(s.b/255),l=n.color.rgb,f=d(l.r/255),p=d(l.g/255),u=d(l.b/255);return t.color({r:Math.round(255*c(r+e*(f-r))),g:Math.round(255*c(a+e*(p-a))),b:Math.round(255*c(i+e*(u-i))),a:s.a+e*Math.abs(l.a-s.a)})}(a,f,p):f}const p=[{key:\"backgroundColor\",legendItemKey:\"fillStyle\"},{key:\"borderColor\",legendItemKey:\"strokeStyle\"}];function u(t,e,o,n){const l=function(t,{boxWidth:e,boxHeight:o}){return{top:t.top,left:t.le",
		"ft,bottom:t.top+o,right:t.left+e,xCenter:t.left+e/2,yCenter:t.top+o/2,drawingArea:Math.max(e,o)/2}}(t.legend.legendHitBoxes[o.datasetIndex],n);s(l)&&p.forEach((function(n){!function(t,e,o,n,s){const l=i(t,o,n.datasetIndex);if(!l||!l.stopColors.length)return;const c=r(e,l.axis,s);a(c,l.stopColors),n[o.legendItemKey]=c}(e,t.ctx,n,o,l)}))}function g(t,e,o,n){for(const s of t.legend.legendItems)p.forEach((function(t){const r=o.data[s.index],a=f(e,t,n,r);a&&a.valid&&(s[t.legendItemKey]=a.rgbString())}))}function h(e,o){const n=e.legend,s=n.options,r=s.labels.boxHeight?s.labels.boxHeight:((e,o)=>o.labels&&o.labels.font&&t.defined(o.labels.font.size)?o.labels.font.size:e.options.font.size)(e,s),a=s.labels.boxWidth,i=e.data.datasets;for(let t=0;t<i.length;t++){const s=n.legendItems[t];s.datasetIndex===t?u(e,o,s,{boxWidth:a,boxHeight:r}):g(e,o,i[t],t)}}const b=new Map,x=o?(t,e)=>t[e+\"Scale\"]:(t,e)=>t.controller[\"_\"+e+\"Scale\"];function y(e,o,n){for(const s of Object.keys(o)){const{pixel:r,s",
		"top:a}=l(e,s);if(isFinite(r)&&isFinite(a)){const e=t.color(o[s]);e&&e.valid&&n.push({stop:Math.max(0,Math.min(1,a)),color:e})}}n.sort(((t,e)=>t.stop-e.stop))}function C(t,e,o,n){e[o]=n,t.dataset&&(t.dataset.options?t.dataset.options[o]=n:t.dataset[o]=n)}function m(t,e,o,n){let s=t.options.get(o);return s?e.hidden||(s=s.filter((t=>t.datasetIndex!==n)),t.options.set(o,s)):(s=[],t.options.set(o,s)),s}function I(t,e,o,n,s){const i=t.ctx,l=t.getDatasetMeta(s);if(!l.hidden)for(const[c,d]of Object.entries(o)){const{axis:o,colors:f}=d;if(!f)continue;const p=x(l,o);if(!p){console.warn(`Scale not found for '${o}'-axis in datasets[${s}] of chart id ${t.id}, skipping.`);continue}const u={datasetIndex:s,axis:o,scale:p,stopColors:[]};m(e,l,c,s).push(u);const g=r(i,o,p);y(p,f,u.stopColors),u.stopColors.length&&(a(g,u.stopColors),C(l,n,c,g))}}return{id:\"gradient\",beforeInit(t){const e={};e.options=new Map,b.set(t,e)},beforeDatasetsUpdate(t){const e=t.chartArea;if(!s(e))return;const o=b.get(t),n=t.dat",
		"a.datasets;for(let e=0;e<n.length;e++){const s=n[e],r=s.gradient;r&&I(t,o,r,s,e)}},afterUpdate(t){const e=b.get(t);t.legend&&!1!==t.legend.options.display&&o&&h(t,e)},destroy(t){b.delete(t)}}}));",
		"//# sourceMappingURL=chartjs-plugin-gradient.min.js.map"
	};
	
	/**
	 * Creates the injectable resource with <code>chartjs-plugin-gradient.min.js</code> content.
	 */
	GradientPluginResource() {
		super(ResourceName.GRADIENT_PLUGIN, CONTENT);
	}

}