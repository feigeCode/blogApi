import { request } from "../utils/requests";


/**
 *
 * @param params
 * @returns {AxiosPromise}
 */
export const login = (params) => {
	return request({
		url: '/login',
		params: params,
		method: 'post'
	})
}
