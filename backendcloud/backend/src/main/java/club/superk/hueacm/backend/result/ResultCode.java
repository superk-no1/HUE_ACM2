package club.superk.hueacm.backend.result;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultCode {

    SUCCESS(200, "成功"),
    FAILED(500, "失败"),
    INPUT_IS_NULL(401, "输入禁止为空");

    private final Integer code;
    private final String message;

}
