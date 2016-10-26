package com.mycompany.myapp;

import cn.bmob.v3.BmobObject;

public class message extends BmobObject
 {
    private String name;

    private String message;
    private String fenqu;

//��ȡ���
    public String getfenqu() {
        return fenqu;
    }
//�ϴ����
    public void setfenqu(String fenqu) {this.fenqu=fenqu;}
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name=name;
    }
    private String ok;

    public String getok() {
        return ok;
    }

    public void setOke(String ok) {
        this.ok=ok;
    }


}
