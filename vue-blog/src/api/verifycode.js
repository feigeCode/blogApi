import { request } from "../utils/requests";

/**
 * 生成验证码
 * @returns {AxiosPromise}
 */
export const getCodeImg = () => {
	return request({
		url: '/captcha_image',
		method: 'get'
	})
};
