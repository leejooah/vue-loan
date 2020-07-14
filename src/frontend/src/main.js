import Vue from 'vue'
import App from './App.vue'
import vuetify from './plugins/vuetify';
import router from './router'
import {store} from './store'
import vuex from 'vuex'
import axios from 'axios'
import InfiniteLoading from 'vue-infinite-loading';

Vue.use(InfiniteLoading, { /* options */ });
Vue.config.productionTip = false
Vue.prototype.$http=axios
Vue.use(vuex)
new Vue({
  vuetify,
  router,
  vuex,
  store,
  axios,
  render: h => h(App)
}).$mount('#app')

if ('scrollRestoration' in history) {
  // Back off, browser, I got this...
  history.scrollRestoration = 'auto';
}