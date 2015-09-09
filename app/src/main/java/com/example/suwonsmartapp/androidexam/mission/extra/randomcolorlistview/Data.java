
package com.example.suwonsmartapp.androidexam.mission.extra.randomcolorlistview;

/**
 * Created by junsuk on 15. 9. 9..
 * Data 클래스 샘플
 */
public class Data {
    private int imageResource;
    private String textView1;
    private String textView2;

    public Data(int imageResource, String textView1, String textView2) {
        this.imageResource = imageResource;
        this.textView1 = textView1;
        this.textView2 = textView2;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    public String getTextView1() {
        return textView1;
    }

    public void setTextView1(String textView1) {
        this.textView1 = textView1;
    }

    public String getTextView2() {
        return textView2;
    }

    public void setTextView2(String textView2) {
        this.textView2 = textView2;
    }
}
