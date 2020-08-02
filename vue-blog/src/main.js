// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import Axios from 'axios'
import Element from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import { store } from './store/index'
Vue.config.productionTip = false;

Vue.prototype.$axios = Axios;
Vue.prototype.$store = store;

Vue.use(Element);

/* eslint-disable no-new */
new Vue({
	el: '#app',
	router,
	render: h => h(App)
});