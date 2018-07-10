package chapter6.CompletionRenderPage;

import com.sun.scenario.effect.ImageData;

public class ImageInfo {
    private String fileName;
    private String url;

    public ImageInfo(String fileName, String url) {
        this.fileName = fileName;
        this.url = url;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 模拟下载图片方法，暂未实现
     * @return
     */
    public ImageData downloadImage() {
        return null;
    }
}
