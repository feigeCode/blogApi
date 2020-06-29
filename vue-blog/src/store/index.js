import Vuex from 'vuex'
import Vue from 'vue'
Vue.use(Vuex);
export let store = new Vuex.Store({
	state:{
		isLogin: false,
		user: {
			email: "",
			headPhoto: "",
			hobby: "",
			id: 1,
			other: "",
			password: "",
			selfIntroduce: "",
			sex: 1,
			time: "",
			username: "",
		}
	},
	mutations:{
		login(state,data){
			state.isLogin = data.isLogin;
			state.user = data.user
		}
	}
});


