package com.xuyao.test.pattern.template;

public class Sub1 extends Base{
    @Override
    public String execute() {
        long phone = 18388888888L;
        int count = 100000;
        String phones = "";
        for (int i = 0; i < count; i++) {
            if(i > 0) phones += ",";
            phones += phone++;
            //sb.append(phone++);
        }
        //String phones = sb.toString();
        return phones;
    }
}
