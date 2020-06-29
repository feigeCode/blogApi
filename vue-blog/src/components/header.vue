<template>
	<div style="margin:0;">
		<header class="header">
			<div class="nav-lg">
				<div class="box">
					<nav class="nav">
						<li>
							<router-link to="/">首页</router-link>
						</li>
						<li>分类</li>
						<li>归档</li>
						<li>帖子</li>
						<li>留言</li>
						<li>
							<el-autocomplete
								size="mini"
								class="inline-input"
								v-model="search"
								:fetch-suggestions="querySearch"
								placeholder="请输入内容"
								:trigger-on-focus="false"
								@select="handleSelect"
							></el-autocomplete>
						</li>
					</nav>
				</div>
				<div class="banner">
					<img :src="sw4" alt="背景图片"/>
				</div>
			</div>
			<div ref="down">
				<div class="open-nav" style="position:fixed;">
					<a href="javascript:void(0);" @click="isOpen = !isOpen">
						<i class="el-icon-s-unfold"></i>
					</a>
				</div>
				<div class="down-nav">
					<div :class="isOpen?class1:class2">
						<nav class="nav-mobile">
							<li>
								<router-link to="/">首页</router-link>
							</li>
							<li>分类</li>
							<li>归档</li>
							<li>帖子</li>
							<li>留言</li>
							<li>
								<el-autocomplete
									class="inline-input"
									v-model="search"
									:fetch-suggestions="querySearch"
									placeholder="请输入内容"
									:trigger-on-focus="false"
									@select="handleSelect"
								></el-autocomplete>
							</li>
						</nav>
					</div>
					<div class="banner">
						<img :src="sw4" alt="背景图片"/>
					</div>
				</div>
			</div>
		</header>
	</div>
</template>

<script>
	import sw4 from "../../static/img/sw4.jpg";

	export default {
		name: "Header",
		data() {
			return {
				isOpen: false,
				class1: "box-mobile",
				class2: "none",
				search: "",
				blogs: [],
				sw4: sw4
			};
		},
		methods: {
			querySearch(queryString, cb) {
				let blogs = this.blogs;
				let results = queryString
					? blogs.filter(this.createFilter(queryString))
					: blogs;
				// 调用 callback 返回建议列表的数据
				if (results.length === 0) {
					let no = [{value: "无匹配结果"}];
					cb(no);
				} else {
					cb(results);
				}
			},
			createFilter(queryString) {
				return restaurant => {
					return (
						restaurant.value.toLowerCase().indexOf(queryString.toLowerCase()) ===
						0
					);
				};
			},
			loadAll() {
				return [
					{value: "三全鲜食（北新泾店）", address: "长宁区新渔路144号"},
					{
						value: "Hot honey 首尔炸鸡（仙霞路）",
						address: "上海市长宁区淞虹路661号"
					},
					{
						value: "新旺角茶餐厅",
						address: "上海市普陀区真北路988号创邑金沙谷6号楼113"
					},
					{value: "泷千家(天山西路店)", address: "天山西路438号"},
					{
						value: "胖仙女纸杯蛋糕（上海凌空店）",
						address: "上海市长宁区金钟路968号1幢18号楼一层商铺18-101"
					}
				];
			},
			handleSelect(item) {
				console.log(item);
			}
		},
		mounted() {
			let _this = this
			document.addEventListener("click", function (e) {
				if (_this.$refs.down.contains(e.target)) return;
				_this.isOpen = false;
			});
			this.blogs = this.loadAll();
		}
	};
</script>

<style scoped>
	@media screen and (min-width: 769px) {
		.header {
			width: 100%;
			height: 420px;
			margin: 0;
		}
	}

	@media screen and (max-width: 769px) {
		.header {
			width: 100%;
			height: 299px;
			margin: 0;
		}
	}

	.none {
		display: none;
	}

	@media (min-width: 769px) {
		.banner img {
			position: absolute;
			width: 100%;
			height: 400px;
			object-fit: cover;
			-webkit-transition: 0.5s ease-in-out opacity;
			transition: 0.5s ease-in-out opacity;
		}

		.down-nav {
			display: none;
		}

		.box {
			z-index: 100;
			width: 100%;
			height: 58px;
			position: fixed;
			box-sizing: content-box;
			background-color: rgba(0, 0, 0, 0.4);
			backdrop-filter: blur(10px);
			box-shadow: 2px 2px 1px rgba(0, 0, 0, 0.15);
			margin-top: 0;
		}

		.nav {
			font-size: 20px;
			font-weight: bolder;
			display: flex;
			justify-content: space-evenly;
			color: wheat !important;
			align-content: space-around;
			margin-bottom: 10px;
			margin-top: 10px;
		}
	}

	@media screen and (max-width: 769px) {
		.banner img {
			position: absolute;
			width: 100%;
			height: 300px;
			object-fit: cover;
			-webkit-transition: 0.5s ease-in-out opacity;
			transition: 0.5s ease-in-out opacity;
		}

		.open-nav {
			z-index: 3;
			height: 58px;
			width: 100%;
			position: fixed;
			box-sizing: border-box;
			background-color: rgba(0, 0, 0, 0.95);
			-webkit-backdrop-filter: blur(10px);
			backdrop-filter: blur(10px);

			margin-bottom: 10px;
		}

		.open-nav a {
			font-size: 40px;
			color: white !important;
			line-height: 58px;
		}

		.nav-lg {
			display: none;
		}

		.box-mobile {
			z-index: 100;
			margin-top: 59px;
			width: 100%;
			position: fixed;
			box-sizing: border-box;
			background-color: rgba(0, 0, 0, 0.75);
			backdrop-filter: blur(10px);
			box-shadow: 2px 2px 1px rgba(0, 0, 0, 0.15);
		}

		.nav-mobile {
			font-size: 20px;
			font-weight: bolder;
			height: 100%;
			display: flex;
			color: wheat;
			flex-direction: column;
			justify-content: space-evenly;
			align-content: space-around;
			align-items: center;
		}

		.box-mobile .nav-mobile li {
			margin-top: 19px;
		}
	}

	.header li a:hover {
		color: #409EFF !important;
	}

	.header li a {
		color: wheat !important;
	}
</style>
