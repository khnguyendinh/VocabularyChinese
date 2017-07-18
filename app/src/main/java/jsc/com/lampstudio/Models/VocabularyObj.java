package jsc.com.lampstudio.Models;

/**
 * Created by khoa on 7/18/2017.
 */

public class VocabularyObj {
    private String english;
    private String image;
    private String pinyin;
    private String hanzi;
    private String listen;

    public VocabularyObj(String english, String image, String pinyin, String hanzi, String listen) {
        this.english = english;
        this.image = image;
        this.pinyin = pinyin;
        this.hanzi = hanzi;
        this.listen = listen;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getHanzi() {
        return hanzi;
    }

    public void setHanzi(String hanzi) {
        this.hanzi = hanzi;
    }

    public String getListen() {
        return listen;
    }

    public void setListen(String listen) {
        this.listen = listen;
    }
}
