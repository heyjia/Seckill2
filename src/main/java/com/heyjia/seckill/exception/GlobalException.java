package com.heyjia.seckill.exception;

import com.heyjia.seckill.result.CodeMsg;

public class GlobalException extends RuntimeException {
    private CodeMsg cm;
    public  GlobalException(CodeMsg cm){
        super(cm.toString());
        this.cm = cm;
    }

    public void setCm(CodeMsg cm) {
        this.cm = cm;
    }

    public CodeMsg getCm() {
        return cm;
    }
}
