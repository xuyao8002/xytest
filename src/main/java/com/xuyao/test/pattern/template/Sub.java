package com.xuyao.test.pattern.template;

public class Sub  extends Base{
    @Override
    public String execute() {
        long phone = 18321704496L;
        int count = 100000;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            if(i > 0) sb.append(",");
            sb.append(phone++);
        }
        String phones = sb.toString();
        return phones;
    }

}
