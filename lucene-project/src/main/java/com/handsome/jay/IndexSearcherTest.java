package com.handsome.jay;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.LongField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;

public class IndexSearcherTest {

    @Test
    public void testIndexSearch() throws Exception{
        //创建分词器
//        Analyzer analyzer = new StandardAnalyzer();
        Analyzer analyzer = new IKAnalyzer();
        //创建查询对象, 第一个参数 默认搜索域  第二个参数分词器
        QueryParser queryParser = new QueryParser("fileContext", analyzer);

        //查询语法  域名:搜索的关键字
        Query query = queryParser.parse("fileName:副本");
        Directory dir = FSDirectory.open(new File("F:\\dic"));
        IndexReader indexReader = DirectoryReader.open(dir);

        IndexSearcher indexSearcher = new IndexSearcher(indexReader);


        TopDocs search = indexSearcher.search(query, 10);

        System.out.println("共有:"+ search.totalHits);

        ScoreDoc[] scoreDocs = search.scoreDocs;
        for (ScoreDoc scoreDoc : scoreDocs) {
            //获取docid
            int docId = scoreDoc.doc;
            //通过文档ID从硬盘中读取对应的文档
            Document document = indexReader.document(docId);
            System.out.println("fileName:"+document.get("fileName"));
            System.out.println("fileSize:"+document.get("fileSize"));
            System.out.println("=====================");
        }
    }

    /**
     * shan'chu
     * @throws Exception
     */
    @Test
    public void testIndexDel() throws Exception {
        //创建分词器
//        Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_4_10_3);
        Analyzer analyzer = new IKAnalyzer();

        Directory directory = FSDirectory.open(new File("F:\\dic"));
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LUCENE_4_10_3, analyzer);
        //创建索引和文档写对象
        IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);

//        indexWriter.deleteAll();
        indexWriter.deleteDocuments(new Term("fileName", "副本"));
        indexWriter.commit();
        indexWriter.close();
    }

    @Test
    public void testIndexUpdate() throws Exception {
        //创建分词器
//        Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_4_10_3);
        Analyzer analyzer = new IKAnalyzer();

        Directory directory = FSDirectory.open(new File("F:\\dic"));
        IndexWriterConfig indexWriterConfig = new IndexWriterConfig(Version.LUCENE_4_10_3, analyzer);
        //创建索引和文档写对象
        IndexWriter indexWriter = new IndexWriter(directory, indexWriterConfig);
        Document document = new Document();
        document.add(new TextField("fileName", "ceshi", Field.Store.YES));
        document.add(new TextField("fileContext", "think in java", Field.Store.YES));
        document.add(new LongField("fileSize", 100L, Field.Store.YES));
        indexWriter.updateDocument(new Term("fileName", "测试"), document);

        indexWriter.commit();
        indexWriter.close();
    }
    @Test
    public void testIndexTermQuery() throws Exception {
        //创建分词器
//        Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_4_10_3);
        Analyzer analyzer = new IKAnalyzer();
        Term term = new Term("fileName", "副本");
        TermQuery termQuery = new TermQuery(term);


        Directory dir = FSDirectory.open(new File("F:\\dic"));
        IndexReader indexReader = DirectoryReader.open(dir);

        IndexSearcher indexSearcher = new IndexSearcher(indexReader);


        TopDocs search = indexSearcher.search(termQuery, 10);

        System.out.println("共有:"+ search.totalHits);

        ScoreDoc[] scoreDocs = search.scoreDocs;
        for (ScoreDoc scoreDoc : scoreDocs) {
            //获取docid
            int docId = scoreDoc.doc;
            //通过文档ID从硬盘中读取对应的文档
            Document document = indexReader.document(docId);
            System.out.println("fileName:"+document.get("fileName"));
            System.out.println("fileSize:"+document.get("fileSize"));
            System.out.println("=====================");
        }
    }

    /**
     * 数字查询
     * @throws Exception
     */
    @Test
    public void testNumericRangeQuery() throws Exception {
        //创建分词器
//        Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_4_10_3);
        Analyzer analyzer = new IKAnalyzer();
        NumericRangeQuery<Long> query = NumericRangeQuery.newLongRange("fileSize", 0l, 24l, true, true);


        Directory dir = FSDirectory.open(new File("F:\\dic"));
        IndexReader indexReader = DirectoryReader.open(dir);

        IndexSearcher indexSearcher = new IndexSearcher(indexReader);


        TopDocs search = indexSearcher.search(query, 10);

        System.out.println("共有:"+ search.totalHits);

        ScoreDoc[] scoreDocs = search.scoreDocs;
        for (ScoreDoc scoreDoc : scoreDocs) {
            //获取docid
            int docId = scoreDoc.doc;
            //通过文档ID从硬盘中读取对应的文档
            Document document = indexReader.document(docId);
            System.out.println("fileName:"+document.get("fileName"));
            System.out.println("fileSize:"+document.get("fileSize"));
            System.out.println("=====================");
        }
    }

    /**
     * 组合查询
     * @throws Exception
     */
    @Test
    public void testBooleanQuery() throws Exception {
        //创建分词器
//        Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_4_10_3);
        Analyzer analyzer = new IKAnalyzer();
        QueryParser queryParser = new QueryParser("fileContext", analyzer);

        //查询语法  域名:搜索的关键字
        Query queryParse = queryParser.parse("fileName:副本");

        NumericRangeQuery<Long> numericRangeQuery = NumericRangeQuery.newLongRange("fileSize", 0l, 24l, true, true);

        BooleanQuery query = new BooleanQuery();
        // 单独的MUST_NOT没有意义
        query.add(queryParse, BooleanClause.Occur.MUST);
        query.add(numericRangeQuery, BooleanClause.Occur.MUST);


        Directory dir = FSDirectory.open(new File("F:\\dic"));
        IndexReader indexReader = DirectoryReader.open(dir);

        IndexSearcher indexSearcher = new IndexSearcher(indexReader);


        TopDocs search = indexSearcher.search(query, 10);

        System.out.println("共有:"+ search.totalHits);

        ScoreDoc[] scoreDocs = search.scoreDocs;
        for (ScoreDoc scoreDoc : scoreDocs) {
            //获取docid
            int docId = scoreDoc.doc;
            //通过文档ID从硬盘中读取对应的文档
            Document document = indexReader.document(docId);
            System.out.println("fileName:"+document.get("fileName"));
            System.out.println("fileSize:"+document.get("fileSize"));
            System.out.println("=====================");
        }
    }

    /**
     * 查询所有
     * @throws Exception
     */
    @Test
    public void testMatchAllQuery() throws Exception {
        //创建分词器
//        Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_4_10_3);
        Analyzer analyzer = new IKAnalyzer();

        MatchAllDocsQuery query = new MatchAllDocsQuery();

        Directory dir = FSDirectory.open(new File("F:\\dic"));
        IndexReader indexReader = DirectoryReader.open(dir);

        IndexSearcher indexSearcher = new IndexSearcher(indexReader);


        TopDocs search = indexSearcher.search(query, 10);

        System.out.println("共有:"+ search.totalHits);

        ScoreDoc[] scoreDocs = search.scoreDocs;
        for (ScoreDoc scoreDoc : scoreDocs) {
            //获取docid
            int docId = scoreDoc.doc;
            //通过文档ID从硬盘中读取对应的文档
            Document document = indexReader.document(docId);
            System.out.println("fileName:"+document.get("fileName"));
            System.out.println("fileSize:"+document.get("fileSize"));
            System.out.println("=====================");
        }
    }

    /**
     * 从多个搜索域中查询
     * @throws Exception
     */
    @Test
    public void testMultiFieldQuery() throws Exception {
        //创建分词器
//        Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_4_10_3);
        Analyzer analyzer = new IKAnalyzer();

        MultiFieldQueryParser multiFieldQueryParser = new MultiFieldQueryParser(new String[]{"fileName","fileContext"},analyzer);
        Query query = multiFieldQueryParser.parse("ceshi");

        Directory dir = FSDirectory.open(new File("F:\\dic"));
        IndexReader indexReader = DirectoryReader.open(dir);

        IndexSearcher indexSearcher = new IndexSearcher(indexReader);


        TopDocs search = indexSearcher.search(query, 10);

        System.out.println("共有:"+ search.totalHits);

        ScoreDoc[] scoreDocs = search.scoreDocs;
        for (ScoreDoc scoreDoc : scoreDocs) {
            //获取docid
            int docId = scoreDoc.doc;
            //通过文档ID从硬盘中读取对应的文档
            Document document = indexReader.document(docId);
            System.out.println("fileName:"+document.get("fileName"));
            System.out.println("fileSize:"+document.get("fileSize"));
            System.out.println("=====================");
        }
    }

}
