<template>
	<div id="app" style="margin:0;">
		<router-view/>
	</div>
</template>

<script>
	export default {
		name: 'App',
		data() {
			return {}
		}
	}
</script>

<style>
	html, body, p, h1, ul {
		margin: 0;
		padding: 0;
	}

	li {
		list-style: none;
	}

	body {
		background-image: url(./assets/bg.jpg);
	}

	.wrap {
		width: 1024px !important;
		margin: 0 auto;
	}

	a {
		text-decoration: none !important;
	}
	.el-popover__title {
		font-size: 25px!important;
		text-align: center!important;
	}
</style>
