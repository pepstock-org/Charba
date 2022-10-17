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
package org.pepstock.charba.client.matrix;

import org.pepstock.charba.client.resources.AbstractInjectableResource;
import org.pepstock.charba.client.resources.ResourceName;

/**
 * Contains the content of <code>chartjs-chart-matrix.min.js</code> to inject.<br>
 * 
 * @author Andrea "Stock" Stocchero
 *
 */
final class MatrixControllerResource extends AbstractInjectableResource {

	// encoded javascript content of chartjs-chart-matrix.min.js
	private static final String[] CONTENT = {
		"/*!",
		" * chartjs-chart-matrix v1.2.0",
		" * https://chartjs-chart-matrix.pages.dev/",
		" * (c) 2022 Jukka Kurkela",
		" * Released under the MIT license",
		" */",
		"!function(t,e){\"object\"==typeof exports&&\"undefined\"!=typeof module?e(require(\"chart.js\"),require(\"chart.js/helpers\")):\"function\"==typeof define&&define.amd?define([\"chart.js\",\"chart.js/helpers\"],e):e((t=\"undefined\"!=typeof globalThis?globalThis:t||self).Chart,t.Chart.helpers)}(this,(function(t,e){\"use strict\";function i(t){return t&&\"object\"==typeof t&&\"default\"in t?t:{default:t}}var r=i(t);class n extends t.DatasetController{initialize(){this.enableOptionSharing=!0,super.initialize()}update(t){const e=this._cachedMeta;this.updateElements(e.data,0,e.data.length,t)}updateElements(t,e,i,r){const n=this,s=\"reset\"===r,{xScale:h,yScale:d}=n._cachedMeta,l=n.resolveDataElementOptions(e,r),u=n.getSharedOptions(r,t[e],l);for(let l=e;l<e+i;l++){const e=!s&&n.getParsed(l),i=s?h.getBasePixel():h.getPixelForValue(e.x),u=s?d.getBasePixel():d.getPixelForValue(e.y),c=n.resolveDataElementOptions(l,r),{width:f,height:g,anchorX:p,anchorY:x}=c,y={x:o(p,i,f),y:a(x,u,g),width:f,heigh",
		"t:g,options:c};n.updateElement(t[l],l,y,r)}n.updateSharedOptions(u,r)}draw(){const t=this,e=t.getMeta().data||[];let i,r;for(i=0,r=e.length;i<r;++i)e[i].draw(t._ctx)}}function o(t,e,i){return\"left\"===t||\"start\"===t?e:\"right\"===t||\"end\"===t?e-i:e-i/2}function a(t,e,i){return\"top\"===t||\"start\"===t?e:\"bottom\"===t||\"end\"===t?e-i:e-i/2}function s(t,e){const{x:i,y:r,width:n,height:o}=t.getProps([\"x\",\"y\",\"width\",\"height\"],e);return{left:i,top:r,right:i+n,bottom:r+o}}function h(t,e,i){return Math.max(Math.min(t,i),e)}function d(t){const i=s(t),r=i.right-i.left,n=i.bottom-i.top,o=function(t,i,r){const n=t.options.borderWidth;let o,a,s,d;return e.isObject(n)?(o=+n.top||0,a=+n.right||0,s=+n.bottom||0,d=+n.left||0):o=a=s=d=+n||0,{t:h(o,0,r),r:h(a,0,i),b:h(s,0,r),l:h(d,0,i)}}(t,r/2,n/2);return{outer:{x:i.left,y:i.top,w:r,h:n},inner:{x:i.left+o.l,y:i.top+o.t,w:r-o.l-o.r,h:n-o.t-o.b}}}function l(t,e,i,r){const n=null===e,o=null===i,a=!(!t||n&&o)&&s(t,r);return a&&(n||e>=a.left&",
		"&e<=a.right)&&(o||i>=a.top&&i<=a.bottom)}n.id=\"matrix\",n.version=\"1.2.0\",n.defaults={dataElementType:\"matrix\",animations:{numbers:{type:\"number\",properties:[\"x\",\"y\",\"width\",\"height\"]}},anchorX:\"center\",anchorY:\"center\"},n.overrides={interaction:{mode:\"nearest\",intersect:!0},scales:{x:{type:\"linear\",offset:!0},y:{type:\"linear\",reverse:!0}}};class u extends t.Element{constructor(t){super(),this.options=void 0,this.width=void 0,this.height=void 0,t&&Object.assign(this,t)}draw(t){const i=this.options,{inner:r,outer:n}=d(this),o=e.toTRBLCorners(i.borderRadius);t.save(),n.w!==r.w||n.h!==r.h?(t.beginPath(),e.addRoundedRectPath(t,{x:n.x,y:n.y,w:n.w,h:n.h,radius:o}),e.addRoundedRectPath(t,{x:r.x,y:r.y,w:r.w,h:r.h,radius:o}),t.fillStyle=i.backgroundColor,t.fill(),t.fillStyle=i.borderColor,t.fill(\"evenodd\")):(t.beginPath(),e.addRoundedRectPath(t,{x:r.x,y:r.y,w:r.w,h:r.h,radius:o}),t.fillStyle=i.backgroundColor,t.fill()),t.restore()}inRange(t,e,i){return l(this,t,e,i)}in",
		"XRange(t,e){return l(this,t,null,e)}inYRange(t,e){return l(this,null,t,e)}getCenterPoint(t){const{x:e,y:i,width:r,height:n}=this.getProps([\"x\",\"y\",\"width\",\"height\"],t);return{x:e+r/2,y:i+n/2}}tooltipPosition(){return this.getCenterPoint()}getRange(t){return\"x\"===t?this.width/2:this.height/2}}u.id=\"matrix\",u.defaults={backgroundColor:void 0,borderColor:void 0,borderWidth:void 0,borderRadius:0,anchorX:void 0,anchorY:void 0,width:20,height:20},r.default.register(n,u)}));",
		"//# sourceMappingURL=chartjs-chart-matrix.min.js.map"
	};
	
	/**
	 * Creates the injectable resource with <code>chartjs-chart-matrix.min.js</code> content.
	 */
	MatrixControllerResource() {
		super(ResourceName.MATRIX_CONTROLLER, CONTENT);
	}

}
