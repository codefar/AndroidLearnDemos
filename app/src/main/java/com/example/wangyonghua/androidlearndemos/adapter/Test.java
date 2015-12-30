package com.example.wangyonghua.androidlearndemos.adapter;

/**
 * Created by wangyonghua on 15-12-8.
 */
public class Test {

    public static void main(String[] args) {
        float w = 1080;
        //float de = w * 3.375f / 1080f;
        float delta = w  / 320f;
        System.out.println("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                "<resources>");
        for (int i = 1; i < 600; i++) {
            System.out.println(String.format("<dimen name=dimen_%d_dip>" + "%s" +"px</dimen>", i, i * delta));
        }
        System.out.println("</resources>");
    }
}
