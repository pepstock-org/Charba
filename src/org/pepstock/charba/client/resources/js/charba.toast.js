/**
  Copyright 2021 Andrea "Stock" Stocchero

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
// ------------------------------
// creates toasting GLOBAL object
// ------------------------------
// sets default create function in case
// toasting creation is attempted before dom has finished loading
CharbaToast = {
  init: function() {
    // -------------------------------
    // creates the element for toasting
    // MAIN CONTAINER
    // -------------------------------
    const container = document.createElement('div');
    container.id = 'ct-container';
    document.body.appendChild(container);
    // replaces "create" method when DOM has finished loading
    CharbaToast.create = function (pId, pOptions) {
      // creates 
      const result = Object.assign({}, pOptions || {});
      // manges options 
      const options = Object.assign({}, CharbaToast.defaults, CharbaToast.overrides, result);
      // -------------------------------
      // creates the element for toasting
      // -------------------------------
      const toasting = document.createElement('div');
      toasting.id = 'toast-' + pId;
      toasting.className = 'ct-toast';
      const wrapper = document.createElement('div');
      // -------------------------------
      // TITLE
      // -------------------------------
      const title = document.createElement('h4');
      title.className = 'ct-title';
      title.innerHTML = options.title;
      wrapper.appendChild(title);
      // -------------------------------
      // TEXT
      // -------------------------------
      if (options.text) {
        const text = document.createElement('p');
        text.className = 'ct-text';
        text.innerHTML = options.text;
        wrapper.appendChild(text);
      }
      // -------------------------------
      // ICON
      // -------------------------------
      if (options.icon && options.icon.src) {
        wrapper.classList += ' img';
        const icon = document.createElement('img');
        icon.src = options.icon.src;
        icon.className = 'ct-icon';
        wrapper.appendChild(icon);
      }
      // -------------------------------
      // PROGRESS BAR
      // -------------------------------
      if (!options.hideProgressBar) {
        const cssAnimation = document.createElement('style');
        cssAnimation.id = 'style-' + toasting.id;
        cssAnimation.type = 'text/css';
        const rules = document.createTextNode(`
            @keyframes animate-${pId} {
              0% {
                width: 100%
              }
              100% {
                width: 0%;
              }
            }
        `);
        cssAnimation.appendChild(rules);
        document.getElementsByTagName('head')[0].appendChild(cssAnimation);
        const progressBar = document.createElement('div');
        progressBar.classList = 'progress-bar';
        progressBar.style.animationName = 'animate-' + pId;
        progressBar.style.animationDuration = options.timeout / 1000 + 's';
        progressBar.style.animationTimingFunction = 'linear';
        progressBar.style.animationFillMode = 'forwards';
        if (options.progressBarType) {
          progressBar.classList += ' ' + options.progressBarType;    
        }
        wrapper.appendChild(progressBar);
      }
      // -------------------------------
      // Custom CLICK EVENT callback
      // -------------------------------
      if (typeof options.onClick === 'function') {
        toasting.addEventListener('click', (event) => options.onClick.apply(this, [result, event]));
      }
      // sets status
      result.showing = true;
      // -------------------------------
      // HIDING / AUTO HIDE
      // -------------------------------
      toasting.hide = function () {
        toasting.className += ' ct-fadeOut';
        toasting.addEventListener('animationend', removeToast, false);
        return null;
      };
      if (options.autoHide) {
        setTimeout(toasting.hide, options.timeout + 200);
      }
      // -------------------------------
      // TYPE
      // -------------------------------
      if (options.type) {
        wrapper.className += ' ct-' + options.type;
      } else {
        wrapper.className += ' ct-default';
      }
      // -------------------------------
      // Internal CLICK EVENT callback
      // -------------------------------
      toasting.addEventListener('click', toasting.hide);
      // -------------------------------
      // REMOVE toast
      // -------------------------------
      function removeToast() {
        document.getElementById('ct-container').removeChild(toasting);
        const style = document.querySelector('#style-' + toasting.id);
        if (style) {
          style.remove();
        }
        result.showing = false;
        // checks and calls callback
        if (typeof options.onClose === 'function') {
          const event = new Event('toast-close');
          options.onClose.apply(this, [result, event]);
        }
      }    
      // -------------------------------
      // ADD to DOCUMENT
      // -------------------------------
      toasting.appendChild(wrapper);
      document.getElementById('ct-container').appendChild(toasting);
      result.id = pId;
      result.dateTime = Date.now();
      result.element = toasting;
      // checks and calls callback
      if (typeof options.onOpen === 'function') {
        const event = new Event('toast-open');
        options.onOpen.apply(this, [result, event]);
      }
      return result;
    }
  },
  create: function () {
    console.error(['DOM has not finished loading.', '\tInvoke create method when DOM\s readyState is complete'].join('\n'));
  },
  defaults: {
    autoHide: true,
    hideProgressBar: false,
    icon: undefined,
    onClick: undefined,
    onClose: undefined,
    onOpen: undefined,
    progressBarType: undefined,
    title: "Default title",
    text: undefined,
    timeout: 4000,
    type: undefined
  },
  overrides: {}
};
// freeze defaults
Object.freeze(CharbaToast.defaults);
// needs DOM to be ready
if (document.readyState === 'complete') {
  CharbaToast.init();
} else {
  window.addEventListener('DOMContentLoaded', CharbaToast.init);
}
