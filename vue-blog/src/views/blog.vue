<template>
	<div>
		<Header />
		<div class="container">
			<section class="blog-list">
				<blog-content />
			</section>
			<section class="hidden-mobile">
				<Aside />
			</section>
		</div>
		<Comment />
	</div>
</template>

<script>
	import Header from "../components/header";
	import blogContent from "../components/content";
	import Aside from "../components/aside";
	import Comment from "../components/comment";
	import '../../static/css/container.css'
	export default {
		name: "Blog",
		components: {
			Header,
			blogContent,
			Aside,
			Comment
		},
		methods: {

		}
	};
</script>

<style scoped>
	.blog-list {
		padding: 2px;
		border-radius: 20px;
		background-color: whitesmoke;
	}
</style>
