<template>
	<div>
		<div v-show="!isLogin" class="login">
			<span>你尚未登录，登录之后方可评论~</span>
			<div class="login-btn">
				<el-button type="primary" @click="login" round>登录</el-button>
				或
				<el-button type="success" round>注册</el-button>
			</div>
		</div>
		<hr id="comment-list"/>
		<div class="content whisper-content">
			<div class="cont">
				<div class="whisper-list">
					<div v-show="isLogin" id="comment">
						<div class="textarea">
							<h2 style="color: blue;margin-bottom: 10px;" v-show="isReply">{{ replyContent }}</h2>
							<el-input type="textarea" :rows="8" placeholder="赶快来评论吧~~~~" v-model="content"></el-input>
						</div>
						<el-button type="primary" @click="submit()" round>提交</el-button>
						<el-button v-show="isReply" type="primary" @click="cancel()" round>取消回复</el-button>
					</div>
					<div class="item-box" v-for="(comment,index) in data" :key="index">
						<div class="item">
							<div class="whisper-title">
								<i class="el-icon-date"></i>
								<span class="date">{{ comment.rootComment.createTime | dateFormat }}</span>
							</div>
							<div class="img-box">
								<img
									:src="comment.rootComment.headPhoto"
									alt="头像"
									width="50px"
									height="50px"
									style="border-radius:50px;"
								/>
								<p class="tit">
									<span class="name">{{ comment.rootComment.username }}发表评论</span>
								</p>
							</div>
							<p class="text-cont">发表内容：{{ comment.rootComment.content }}</p>
							<el-button
								type="warning"
								round
								size="small"
								@click="replyFunction(comment.rootComment.username,comment.rootComment.id,comment.rootComment.content)"
							>回复
							</el-button>
							<div class="op-list">
								<p class="like" @click="add(comment.rootComment.id,index)">
									<i class="el-icon-thumb"></i>
									<span>{{ comment.rootComment.good }}</span>
								</p>
								<p class="edit">
									<i class="el-icon-chat-line-round"></i>
									<span>1200</span>
								</p>
								<p class="off" @click="open(index)">
									<span>{{ index === current?"收起":"展开" }}</span>
									<i :class="index === current? up : down"></i>
								</p>
							</div>
						</div>
						<div
							:class="index === current? isShow: unShow"
							v-for="(reply,i) in comment.replies"
							:key="reply.id"
							style="margin-bottom:5px;"
						>
							<div class="list-cont">
								<div class="whisper-title">
									<i class="el-icon-date"></i>
									<span class="date">{{ reply.createTime | dateFormat }}</span>
								</div>
								<div class="cont">
									<div class="img">
										<img
											:src="reply.headPhoto"
											alt="头像"
											width="50px"
											height="50px"
											style="border-radius:50px;"
										/>
									</div>
									<div class="text">
										<p class="tit">
											<span class="name">{{ reply.username }}</span>
											回复
											<span class="name">{{ reply.replier }}</span>
										</p>

										<p class="ct">回复内容：{{ reply.content }}</p>
										<p class="like" @click="add1(reply.id,index,i)">
											<i class="el-icon-thumb"></i>
											<span>{{ reply.good }}</span>
										</p>
									</div>
									<el-button
										type="warning"
										round
										size="small"
										@click="replyFunction(reply.username,reply.id,reply.content)"
									>回复
									</el-button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</template>

<script>
	import "../../static/css/main.css";
	import $ from "../../static/js/jquery-3.4.1.min";
	import { addComment, getComments } from "../api/comment";
	import { timestampFormat } from "../utils/dateFormat";
	export default {
		name: "comment",
		data() {
			return {
				data: [],
				commentAndReplies: {
					rootComment: this.commentObject,
					replies:[]
				},
				commentObject: {
					content: '',
					createTime: null,
					good: 0,
					headPhoto: this.$store.state.user.headPhoto,
					id: 1000,
					parentId: null,
					replier: null,
					username: this.$store.state.user.username,
				},
				isLogin: this.$store.state.isLogin,
				isShow: "review-version",
				unShow: "feige-hidden",
				up: "el-icon-arrow-up",
				down: "el-icon-arrow-down",
				open1: true,
				current: null,
				userId: this.$store.state.user.id,
				isReply: false,
				replyContent: '',
				content: ''
			};
		},
		methods: {
			open: function (index) {
				if (this.open1) {
					this.current = index;
					this.open1 = false;
				} else {
					this.current = null;
					this.open1 = true;
				}
			},
			submit: function () {
				if (this.content.replace(/(^\s*)|(\s*$)/g, "") === null || this.content.replace(/(^\s*)|(\s*$)/g, "") === ''){
					this.$message({
						message: '评论内容不能为空！',
						type: 'warning'
					});
				}else {
					let params = {
						content: this.content,
						parentId: this.commentObject.parentId,
						good: this.commentObject.good,
						replier: this.commentObject.replier,
						blogId: this.$route.params.id,
						userId: this.userId
					};
					addComment(params).then(res => {
						if (res.data.code === 200){
							this.commentObject.id++;
							this.commentObject.createTime = new Date();
							this.commentObject.content = this.content;
							this.commentAndReplies.rootComment = this.commentObject;
							this.data.unshift(this.commentAndReplies);
							this.content = '';
							this.cancel();
						}else {

						}
					}).catch(err => {
						console.log(err);
					});
				}
			},
			replyFunction: function (name, id , content) {
				if (!this.isLogin){
					this.$message({
						message: '登录之后方可评论！',
						type: 'info'
					});
					return
				}
				if (this.commentObject.username === name){
					this.$message({
						message: '不能回复自己的评论！',
						type: 'info'
					});
				}else {
					$("html").animate({scrollTop:$('#comment').offset().top - 60 },300,function () {
						$(".el-textarea__inner").focus();
					});
					this.replyContent = name+' 评论的内容：'+content;
					this.commentObject.replier = name;
					this.commentObject.parentId = id;
					this.isReply = true;
				}
			},
			cancel: function () {
				this.commentObject.replier = null;
				this.commentObject.parentId = null;
				$(".el-textarea__inner").blur();
				this.isReply = false;
				this.replyContent = '';
			},
			add: function (id, index) {
				this.data.commentAndReplies[index].rootComment.good++;
			},
			add1: function (id, index, i) {
				this.data.commentAndReplies[index].replies[i].good++;
			},
			login: function () {
				this.$router.push({name: 'Login',query: {from: this.$route.path}});
			},
			queryComments(){
				const loading = this.$loading({
					lock: true,
					text: 'Loading',
					spinner: 'el-icon-loading',
					background: 'rgba(0, 0, 0, 0.7)'
				});
				getComments(this.$route.params.id).then(response => {
					this.data = response.data.data;
					loading.close();
				}).catch(err => {
					console.log(err);
				});
			}
		},
		created() {
			this.queryComments();
		},
		filters: {
			dateFormat: timestampFormat
		},
		watch: {
			'$route' (to, from) { //监听路由是否变化
				if(this.$route.params.id){// 判断条件1  判断传递值的变化
					//获取评论数据
					this.queryComments();
				}
			}
		}
	}
</script>

<style>
	.feige-hidden {
		display: none;
	}

	.tit .name {
		width: 290px;
		font-size: 20px;
		font-weight: bolder;
		color: slategrey;
	}

	.whisper-content .item-box {
		font-size: 20px;
		font-weight: bold;
		background: url("../assets/bg1.png");
		margin-bottom: 20px;
		margin-top: 25px;
	}

	.textarea {
		margin-bottom: 10px;
	}

	.review-version {
		margin-left: 40px;
		background: lightgoldenrodyellow;
		padding: 20px 10px 10px 20px;
	}
	.login {
		width: 370px;
		margin: 30px auto;
		font-weight: bolder;
		font-size: 20px;

	}
	.login .login-btn {
		margin: 0 40px auto;
	}
</style>
