package org.jeecg.modules.demo.utils.wechat;

import java.util.Arrays;
import java.util.List;

/**
 * @author henry(zhanghai)
 * @date 2022-02-10 13:24
 */
public interface Constant {
    // ******************************* 通用 *******************************

    /**
     * 删除标志-正常
     */
    int DELFLAG_NORMAL = 0;

    /**
     * 删除标志-已删除
     */
    int DELFLAG_DEL = 1;

    /**
     * 验证码有效时间，默认5分钟，单位秒
     */
    long V_CODE_TIME = 5 * 60;

    /**
     * 验证码间隔时间，默认1分钟，单位秒
     */
    long V_CODE_SEND_INTERVAL_TIME = 60;

    /**
     * 验证码前缀
     */
    String V_CODE_PREFIX = "validationCode:";

    List<String> V_CODE_TYPE_LIST = Arrays.asList("register", "login", "bind-wechat", "bind-apple", "password", "pay-password");

    /**
     * 验证码-注册
     */
    String V_CODE_REGISTER = "register";

    /**
     * 验证码-登录
     */
    String V_CODE_LOGIN = "login";

    /**
     * 验证码-绑定微信
     */
    String V_CODE_BIND_WECHAT = "bind-wechat";

    /**
     * 验证码-绑定微信
     */
    String V_CODE_BIND_APPLE = "bind-apple";

    /**
     * 验证码-修改密码
     */
    String V_CODE_PASSWORD = "password";

    /**
     * 验证码-修改密码
     */
    String V_CODE_PAY_PASSWORD = "pay-password";


    // ******************************* 用户 *******************************

    /**
     * 手机号未注册
     */
    int PHONE_UNREGISTERED = 5001;

    /**
     * 微信未注册
     */
    int WECHAT_UNREGISTERED = 5002;

    /**
     * 状态-启用
     **/
    int STATUS_ENABLE = 1;

    /**
     * 状态-禁用
     **/
    int STATUS_DISABLE = 2;

    /**
     * 状态-注销
     **/
    int STATUS_CANCEL = 3;


    /**
     * 普通用户
     */
    String IM_USER = "im:cache:user";
    /**
     * 普通用户精简版
     */
    String IM_USER_MIN = "im:cache:user:min";

    String DEL_USER = "delUser";
    /**
     * 普通用户 Token 缓存
     */
    String IM_USER_TOKEN_PREFIX = "im:cache:user:token:";

    /**
     * 登录密码错误次数缓存前缀
     */
    String PASSWORD_ERROR_NUMBER_PREFIX = "im:passwordErrorNumber:";
    /**
     * 支付密码错误次数缓存前缀
     */
    String PAY_PASSWORD_ERROR_NUMBER_PREFIX = "im:payPasswordErrorNumber:";


    /**
     * 用户类型：0普通用户 1 平台用户 2 机器人 3 人工充值客服 4 游戏问题客服 5公告 6零钱助手 7创聊小助手
     */
    int USER_TYPE_0 = 0;


    // ******************************* 分布式锁 *******************************

    interface Lock {
        interface UserMoney {
            String NAME = "user.money";
            long WAIT_TIME = 10;
            long LEASE_TIME = 60;
        }

    }

    // ******************************* 文件存储 *******************************
    enum FileStorage {
        /**
         * 后台上传的系统文件
         */
        SYSTEM("system", "/system/"),
        /**
         * 普通用户头像
         */
        IM_USER_HEAD("imUserHead", "user/head/"),
        /**
         * 用户上传
         */
        IM_USER_UPLOAD("imUserUpload", "im/user/upload/"),
        /**
         * 用户合同
         */
        CONTRACT("imUserContract", "im/user/contract/");

        public String type;
        public String path;

        FileStorage(String type, String path) {
            this.type = type;
            this.path = path;
        }

    }
}
