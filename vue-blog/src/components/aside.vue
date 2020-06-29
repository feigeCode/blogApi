<template>
	<div>
		<div class="aside-block">
			<el-card :body-style="{ padding: '0px' }">
				<el-avatar
					:size="70"
					src="https://dss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1493149227,2054718729&fm=26&gp=0.jpg"
					fit="scale-down"
					style="margin:20px 140px auto;"
				>
				</el-avatar>
				<div class="aside-btm">
					<span class="name">feige</span>
					<span class="aside-btm-tag">一生清贫怎敢入繁化</span>
					<span class="aside-btm-tag">两袖清风怎敢误佳人</span>
					<div class="bottom clearfix">
						<div class="num">
							<span>{{ blogsCount }}</span>
							<span>{{ typesCount }}</span>
							<span>{{ commentsCount }}</span>
						</div>
						<div class="text">
							<span>文章</span>
							<span>分类</span>
							<span>评论</span>
						</div>

					</div>
				</div>
			</el-card>
		</div>
		<div class="aside-block">
			<el-card class="box-card">
				<div slot="header" class="clearfix">
					<span class="aside-title">分类</span>
				</div>
				<ul class="type">
					<li @click="getBlogsByType('全部')">全部</li>
					<li v-for="(type,index) in data.data" :key="index" @click="getBlogsByType(type.typeName)">{{ type.typeName }}</li>
				</ul>
			</el-card>
		</div>
		<div class="aside-block">
			<el-card class="box-card" >
				<div slot="header" class="clearfix">
					<span class="article-title">热门文章</span>
				</div>
				<ul class="article">
					<li v-for="(blog,index) in blogs" :key="index" @click="blogNav(blog.id)">{{ blog.title }}</li>
				</ul>
			</el-card>
		</div>
	</div>
</template>

<script>
	import { getType } from "../api/type";
	import {getBlogsView} from "../api/blog";
	import {count} from "../api/other";

	export default {
		name: "Aside",
		data() {
			return {
				data:[],
				blogs:[],
				commentsCount: 0,
				typesCount: 0,
				blogsCount: 0
			}
		},
		methods: {
			getBlogsByType(value){
				if (this.$route.name !== 'Index'){
					this.$router.push({name: 'Index',query: {"typeName": value}});
				}else {
					this.$emit("getBlogsByType1",value);
				}
			},
			blogNav: function (id) {
				this.$router.push({ name: 'Blog', params: { id: id }})
			},
		},
		created() {
			let params = {
				page: 1,
				limit: 30,
				searchContent: null
			};
			getType(params).then(response => {
				this.data = response.data;
			}).catch(err => {
				console.log(err);
			});
			getBlogsView().then(res => {
				this.blogs = res.data.data;
				//console.log(res);
			}).catch(err => {
				console.log(err);
			});
			count().then(res => {
				this.blogsCount = res.data.blogsCount;
				this.commentsCount = res.data.commentsCount;
				this.typesCount = res.data.typesCount;
			}).catch(err => {
				console.log(err);
			})
		}
	}
</script>

<style scoped>
	.text {
		margin-left: 5px;
	}
	.num,.text {
		width: 160px;
		display: flex;
		justify-content: space-around;
		margin-bottom: 10px;
		font-weight: bolder;
		font-size: 20px;
		color: #66d9ef;
	}
	.aside-block {
		width: 345px;
		height: auto;
		margin-bottom: 10px;
	}
	.aside-btm {
		margin:0 115px auto;
		width: 340px;
	}
	.aside-btm .name {
		font-size: 25px;
		font-weight: 800;
		margin-left: 35px;
	}
	.aside-btm .aside-btm-tag {
		font-size: 20px;
		font-weight: bolder;
		margin-left: -29px;
	}
	.aside-btm .name,.aside-btm-tag {
		display: block;
		margin-bottom: 5px;
	}
	.bottom {
		margin-left: -19px;
	}
	.type {
		display: flex;
		justify-content: flex-start;
		flex-wrap: wrap;
	}
	.type li {
		font-weight: 700;
		font-size: 20px;
		margin: 5px;
		color: slategray;
		cursor: pointer;
	}
	.type li:hover {
		color: #409eff;
	}
	.aside-title,.article-title {
		font-size: 20px;
		font-weight: 700;
		color: slategray;
		margin-left: 120px;
	}
	.article {
		width: 340px;
		display: flex;
		flex-direction: column;
		justify-content: space-around;
		margin: 0 30px auto;
		font-size: 20px;
		font-weight: 700;
		color: #888888;
	}
	.article li:hover {
		color: #e6db74;
		cursor: pointer;
	}
</style>
