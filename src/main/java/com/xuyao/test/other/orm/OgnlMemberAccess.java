package com.xuyao.test.other.orm;


import ognl.MemberAccess;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Member;
import java.util.Map;

/**
 * ognl变量访问规则
 */
public class OgnlMemberAccess implements MemberAccess {
    private boolean allowAccess;
    public OgnlMemberAccess(boolean allowAccess) {
        this.allowAccess = allowAccess;
    }

    /**
     * Sets the member up for accessibility
     *
     * @param context
     * @param target
     * @param member
     * @param propertyName
     */
    @Override
    public Object setup(Map context, Object target, Member member, String propertyName) {
        Object result = null;
        if (isAccessible(context, target, member, propertyName)) {
            AccessibleObject accessible = (AccessibleObject) member;
            if (!accessible.isAccessible()) {
                result = Boolean.TRUE;
                accessible.setAccessible(true);
            }
        }
        return result;
    }

    /**
     * Restores the member from the previous setup call.
     *
     * @param context
     * @param target
     * @param member
     * @param propertyName
     * @param state
     */
    @Override
    public void restore(Map context, Object target, Member member, String propertyName, Object state) {
        if (state != null) {
            ((AccessibleObject) member).setAccessible((Boolean) state);
        }
    }

    /**
     * Returns true if the given member is accessible or can be made accessible
     * by this object.
     *
     * @param context
     * @param target
     * @param member
     * @param propertyName
     */
    @Override
    public boolean isAccessible(Map context, Object target, Member member, String propertyName) {
        return allowAccess;
    }
}
