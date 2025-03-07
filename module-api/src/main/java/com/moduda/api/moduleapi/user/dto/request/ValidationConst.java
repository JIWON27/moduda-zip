package com.moduda.api.moduleapi.user.dto.request;

public final class ValidationConst {
    protected static final String USERID_VALUE = "아이디를 입력해주세요.";
    protected static final int MIN_USERID_LENGTH = 6;
    protected static final int MAX_USERID_LENGTH = 12;
    protected static final String USERID_LENGTH = "아이디는 6자~12자 사이로 입력해주세요.";

    protected static final String EMAIL_VALUE = "이메일을 입력해주세요.";
    protected static final String EMAIL_FORMAT = "이메일 형식이 다릅니다.";

    protected static final String NICKNAME_VALUE = "닉네임을 입력해주세요.";
    protected static final String NICKNAME_REGEXP = "^(?!\\d+$)[a-zA-Z0-9가-힣]+$";
    protected static final String NICKNAME_FORMAT = "영문자, 숫자, 한글만 사용 가능하고, 1자 이상이어야 합니다. 특수문자나 공백은 허용되지 않습니다.";
    protected static final int MIN_NICKNAME_LENGTH = 2;
    protected static final int MAX_NICKNAME_LENGTH = 8;
    protected static final String NICKNAME_LENGTH = "닉네임은 2자~8자리 이내로 입력해주세요.";

    protected static final String PASSWORD_VALUE = "비밀번호를 입력해주세요.";
    protected static final String PASSWORD_REGEXP = "[a-zA-Z1-9]{6,12}";
    protected static final String PASSWORD_FORMAT = "비밀번호는 영어와 숫자를 포함해서 6~12자리 이내로 입력해주세요.";

    protected static final String BIRTH_VALUE = "생년월일 형식이 다릅니다.";
}
