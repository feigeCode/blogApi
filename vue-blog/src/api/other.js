import { request } from "../utils/requests";


/**
 * 获取博客、评论、类别的数量
 * @returns {AxiosPromise}
 */
export const count = () => {
	return request({
		url: '/count',
		method: 'get'
	})
};
