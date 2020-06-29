<template>
	<div style="margin:0">
		<Header/>
		<div class="container">
			<section class="blog-list">
				<ul>
					<li v-for="(blog,index) in data" :key="index">
						<article class="blog">
							<span class="title" @click="blogNav(blog.id)">{{ blog.title }}</span>
							<div class="time">
								<span>
									<i class="el-icon-date"></i>&nbsp;&nbsp;{{ blog.createTime | dateFormat }}
								</span>&nbsp;
								<span>
									<i class="el-icon-chat-dot-round"></i>&nbsp;&nbsp;{{ blog.view }}
								</span>
								<span>
									<i class="el-icon-user-solid"></i>&nbsp;&nbsp;{{ blog.author }}
								</span>
							</div>
							<span class="type">
								<i class="el-icon-discount"></i>
								<el-link class="type-name" type="primary" @click="getBlogsByType(blog.typeName)">{{ blog.typeName }}</el-link>
							</span>
						</article>
					</li>
				</ul>
				<div class="block">
					<el-pagination
						@size-change="handleSizeChange"
						@current-change="handleCurrentChange"
						:current-page="currentPage"
						:page-sizes="[10, 30, 50, 100]"
						:page-size="count"
						layout="total, sizes, prev, pager, next, jumper"
						:total="total"
						style="white-space: pre-wrap;">
					</el-pagination>
				</div>
			</section>
			<section class="hidden-mobile">
				<Aside @getBlogsByType1="getBlogsByType"/>
			</section>
		</div>
	</div>
</template>

<script>
	import Header from "../components/header";
	import Aside from "../components/aside";
	import '../../static/css/container.css';
	import { getBlogByTypeName, getBlogs } from "../api/blog";
	import { timestampFormat } from "../utils/dateFormat";

	export default {
		name: "index",
		data() {
			return {
				data: {},
				currentPage: 1,
				count: 10,
				searchContent: null,
				total: 0,
				typeName: '',
				flag: false

			};
		},
		components: {
			Header,
			Aside
		},
		created() {
			const loading = this.$loading({
				lock: true,
				text: 'Loading',
				spinner: 'el-icon-loading',
				background: 'rgba(0, 0, 0, 0.7)'
			});
			if (this.$route.query.typeName === undefined){
				this.flag = true;
				this.queryBlogs();
			}else {
				this.flag = false;
				this.getBlogsByType(this.$route.query.typeName);
			}
			loading.close();
		},
		methods: {
			change(){
				if (this.flag){
					this.queryBlogs();
				}else {
					this.queryBlogsByTypeName();
				}
			},
			handleSizeChange: function (val) {
				this.count = val;
				this.change();
			},
			handleCurrentChange: function (val) {
				this.currentPage = val;
				this.change();
			},
			blogNav: function (id) {
				this.$router.push({ name: 'Blog', params: { id: id }})
			},
			queryBlogs(){
				//console.log("queryBlogs");
				let params = {
					page: this.currentPage,
					limit: this.count,
					searchContent: this.searchContent
				};
				getBlogs(params).then(res => {
					//console.log(res.data);
					this.data = res.data.data;
					this.total = res.data.count;
				}).catch(err => {
					console.log(err);
				})
			},
			getBlogsByType(value){
				//console.log(value);
				this.currentPage = 1;
				this.count = 10;
				if (value === '全部'){
					this.flag = true;
					this.queryBlogs();
					return
				}
				this.typeName = value;
				this.queryBlogsByTypeName();
				this.flag = false;
			},
			queryBlogsByTypeName(){
				//console.log("queryBlogsByTypeName");
				let params = {
					page: this.currentPage,
					limit: this.count,
					typeName: this.typeName
				};
				getBlogByTypeName(params).then(res => {
					//console.log(res.data);
					this.data = res.data.data;
					this.total = res.data.count;
				}).catch(err => {
					console.log(err);
				})
			},
		},
		filters: {
			dateFormat: timestampFormat
		}
	};
</script>

<style scoped>
	.blog {
		width: 100%;
		height: 150px;
		margin-bottom: 20px;
		padding: 5px 10px;
		border-radius: 20px;
		background-image: url("../assets/bg1.png");
		overflow-y: hidden;
		box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
	}

	.blog .title {
		font-size: 30px;
		font-weight: bolder;
		cursor: pointer;
		background-color: #F2F6FC;
		box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
	}

	.blog .title:hover {
		color: #409EFF;
		font-size: 35px;
	}

	.blog .time {
		margin-bottom: 5px;
		margin-top: 5px;
		color: darkcyan;
		width: 340px;
		background-color: #F2F6FC;
		box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
	}

	.blog .type {
		font-size: 25px;
		margin-top: 5px;
		margin-bottom: 9px;
		background-color: #F2F6FC;
		box-shadow: 0 2px 4px rgba(0, 0, 0, .12), 0 0 6px rgba(0, 0, 0, .04);
	}
	.blog .type .type-name {
		font-size: 20px;
		color: #606266;
		padding: 5px;
		margin-bottom: 9px;
	}
</style>
