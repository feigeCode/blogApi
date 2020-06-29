import { request } from "../utils/requests";

/**
 * 通过ID获取博客
 * @param id
 * @returns {AxiosPromise}
 */
export const getBlog = (id) => {
	return request({
		url: "/blog/get_blog/" + id,
		method: 'get'
	})
};

/**
 * 分页查询博客
 * @param params
 * @returns {AxiosPromise}
 */
export const getBlogs = (params) => {
	return request({
		url: "/blog/get_blogs",
		method: 'get',
		params: params
	})
};

/**
 * 通过类型查找博客
 * @param params
 * @returns {AxiosPromise}
 */
export const getBlogByTypeName = (params) => {
	return request({
		url: "/blog/get_blog_by_type",
		method: 'get',
		params: params
	})
};

/**
 * 查询浏览量最高的十篇博客
 * @returns {AxiosPromise}
 */
export const getBlogsView = () => {
	return request({
		url: '/blog/get_blogs_view',
		method: 'get'
	})
};
