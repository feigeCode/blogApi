import Vue from 'vue'
import Router from 'vue-router'
import Index from '../views/index'

Vue.use(Router);

export default new Router({
	routes: [{
		path: '/',
		name: 'Index',
		component: Index
	}, {
		path: '/blog/:id',
		name: 'Blog',
		component: () => import("../views/blog")
	}, {
		path: '/login',
		name: 'Login',
		component: () => import("../views/login")
	}]
})
