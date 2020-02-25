package com.xuyao.test.other.orm;


import ognl.Ognl;
import ognl.OgnlContext;
import ognl.OgnlException;

public class OgnlUtils {

    private OgnlUtils(){};

    /**
     * 计算ognl表达式值
     * @param object 参数
     * @param test ognl字符串表达式
     * @return
     * @throws OgnlException
     */
    public static Boolean getOgnlValue(Object object, String test) throws OgnlException {
        //初始ognl表达式
        OgnlContext context = (OgnlContext) Ognl.createDefaultContext(object,
                new OgnlMemberAccess(true),
                null,
                null);
        Object expression = Ognl.parseExpression(test);
        //计算表达式值
        return (Boolean) Ognl.getValue(expression, context, context.getRoot());
    }

}
