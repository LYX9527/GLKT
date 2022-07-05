package com.orange.errorcode;

public class ErrorCode {
    // 请求方式不支持
    public static final int REQUEST_METHOD_NOT_SUPPORT = 50001;
    public static final String REQUEST_METHOD_NOT_SUPPORT_MSG = "请求方式不支持！";
    // 请求参数错误
    public static final int REQUEST_PARAM_ERROR = 50002;
    public static final String REQUEST_PARAM_ERROR_MSG = "请求参数错误！";

    // 请求参数缺失
    public static final int REQUEST_PARAM_MISSING = 50003;
    public static final String REQUEST_PARAM_MISSING_MSG = "请求参数缺失！";

    // 请求参数类型错误
    public static final int REQUEST_PARAM_TYPE_ERROR = 50004;
    public static final String REQUEST_PARAM_TYPE_ERROR_MSG = "请求参数类型错误！";

    // 验证码错误
    public static final int VERIFY_CODE_ERROR = 50005;
    public static final String VERIFY_CODE_ERROR_MSG = "验证码错误！";

    // 用户名或密码错误
    public static final int USERNAME_OR_PASSWORD_ERROR = 50006;
    public static final String USERNAME_OR_PASSWORD_ERROR_MSG = "用户名或密码错误！";

    // 用户不存在
    public static final int USER_NOT_EXIST = 50007;
    public static final String USER_NOT_EXIST_MSG = "用户不存在！";

    // 用户已存在
    public static final int USER_EXIST = 50008;
    public static final String USER_EXIST_MSG = "用户已存在！";

    // 用户已被禁用
    public static final int USER_DISABLED = 50009;
    public static final String USER_DISABLED_MSG = "用户已被禁用！";

    // 验证码已过期
    public static final int VERIFY_CODE_EXPIRED = 50010;
    public static final String VERIFY_CODE_EXPIRED_MSG = "验证码已过期！";

    // 导入失败
    public static final int IMPORT_FAIL = 50011;
    public static final String IMPORT_FAIL_MSG = "导入失败！";

    // 导出失败
    public static final int EXPORT_FAIL = 50012;
    public static final String EXPORT_FAIL_MSG = "导出失败！";

    // 上传文件失败
    public static final int UPLOAD_FILE_FAIL = 50013;
    public static final String UPLOAD_FILE_FAIL_MSG = "上传文件失败！";

    // 下载文件失败
    public static final int DOWNLOAD_FILE_FAIL = 50014;
    public static final String DOWNLOAD_FILE_FAIL_MSG = "下载文件失败！";

    // 注册失败
    public static final int REGISTER_FAIL = 50015;
    public static final String REGISTER_FAIL_MSG = "注册失败！";

    // 登录失败
    public static final int LOGIN_FAIL = 50016;
    public static final String LOGIN_FAIL_MSG = "登录失败！";

    // 修改失败
    public static final int UPDATE_FAIL = 50017;
    public static final String UPDATE_FAIL_MSG = "修改失败！";
}
