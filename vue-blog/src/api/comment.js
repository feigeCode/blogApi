import { request } from "../utils/requests";


/**
 *
 * @param blogId
 * @returns {AxiosPromise}
 */
export const getComments = (blogId) => {
	return request({
		url: "/comment/get_comments/" + blogId,
		method: 'get'
	});
};

/**
 *
 * @param params
 * @returns {AxiosPromise}
 */
export const addComment = (params) => {
	return request({
		url: "/comment/add_comment",
		method: 'post',
		params: params
	});
};
