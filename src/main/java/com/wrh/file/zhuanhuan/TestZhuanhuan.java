package com.wrh.file.zhuanhuan;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import org.jsoup.Jsoup;
import org.jsoup.helper.W3CDom;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.FileSystems;

/**
 * @author wuruohong
 * @Classname TestZhuanhuan
 * @Description TODO
 * @Date 2022/1/4 18:41
 */
public class TestZhuanhuan {
    public static void main(String[] args) throws IOException {
        TestZhuanhuan htmlToPDFOpenSource = new TestZhuanhuan();
        htmlToPDFOpenSource.generatePdfByOpenhtmltopdf();
    }

    private  void generatePdfByOpenhtmltopdf() throws IOException {
        File inputHtml = new File("D:\\pro\\mynetty\\src\\main\\resources\\templates\\hello.html");

        //加载html文件
        Document document = Jsoup.parse(inputHtml, "UTF-8");
        document.outputSettings().syntax(Document.OutputSettings.Syntax.html);

        //引入资源目录，可以单独引入css，图片文件等
        String baseUri = FileSystems.getDefault()
                .getPath("javaOpenSource\\src\\main\\resources")
                .toUri().toString();

        try (OutputStream os = new FileOutputStream("D:\\pro\\mynetty\\src\\main\\resources\\templates\\hello.pdf")) {
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.withUri("D:\\pro\\mynetty\\src\\main\\resources\\templates\\hello.pdf");
            builder.toStream(os);
            builder.withW3cDocument(new W3CDom().fromJsoup(document), baseUri);

            //引入指定字体，注意字体名需要和css样式中指定的字体名相同
            //builder.useFont(new File("javaOpenSource\\src\\main\\resources\\fonts\\msyh.ttf"),"msyh",1, BaseRendererBuilder.FontStyle.NORMAL, true);
            builder.run();
        }
    }
}
