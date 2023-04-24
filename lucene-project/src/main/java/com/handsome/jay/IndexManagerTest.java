package com.handsome.jay;

import org.apache.commons.io.FileUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.LongField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class IndexManagerTest {

    @Test
    public void testIndexCreate() throws Exception{
        List<Document> docList = new ArrayList<>();
        File dir = new File("F:\\txt");
        for (File file : dir.listFiles()) {
            String fileName = file.getName();
            String fileContext = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
            long fileSize = FileUtils.sizeOf(file);
            Document doc = new Document();//文档对象
//            TextField nameField = new TextField("fileName", fileName, Field.Store.YES); // 第一个参数叫做域名   第二个参数 域值  第三个参数 是否存储
//            TextField contextField = new TextField("fileContext", fileContext, Field.Store.YES); // 第一个参数叫做域名   第二个参数 域值  第三个参数 是否存储
//            TextField sizeField = new TextField("fileSize", Long.toString(fileSize), Field.Store.YES); // 第一个参数叫做域名   第二个参数 域值  第三个参数 是否存储

            //是否分词  要  因为它要索引，并且不是一个整体，分词有意义
            //是否索引  要  因为要通过它来进行搜索
            //是否存储  要  因为要直接在页面上显示
            TextField nameField = new TextField("fileName", fileName, Field.Store.YES); // 第一个参数叫做域名   第二个参数 域值  第三个参数 是否存储

            //是否分词  要  因为要根据内容进行搜索，并且它分词有意义
            //是否索引  要  因为要通过它来进行搜索
            //是否存储  可要可不要  不存储内容就提取不出来
            TextField contextField = new TextField("fileContext", fileContext, Field.Store.NO); // 第一个参数叫做域名   第二个参数 域值  第三个参数 是否存储

            //是否分词  要  因为数字要对比，搜索文档的时候可以搜大小，lucene内部对数字进行了分词算法
            //是否索引  要  因为要根据大小来进行搜索
            //是否存储  要  因为要显示文档大小
            LongField sizeField = new LongField("fileSize", fileSize, Field.Store.YES); // 第一个参数叫做域名   第二个参数 域值  第三个参数 是否存储
            doc.add(nameField);
            doc.add(contextField);
            doc.add(sizeField);
            docList.add(doc);
        }

        //创建分词器
//        Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_4_10_3);
        Analyzer analyzer = new IKAnalyzer();

        Directory directory = FSDirectory.open(new File("F:\\dic"));
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LUCENE_4_10_3, analyzer);
        //创建索引和文档写对象
        IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);

        for (Document document : docList) {
            indexWriter.addDocument(document);
        }
        indexWriter.commit();
        indexWriter.close();
    }
}
