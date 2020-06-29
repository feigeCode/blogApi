import { request } from "../utils/requests";


/**
 *
 * @param params
 * @returns {AxiosPromise}
 */
export const getType = (params) => {
	return request({
		url: "/type/get_types",
		params: params
	});
};
