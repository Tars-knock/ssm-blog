package cn.tarsknock.lucene;

import cn.tarsknock.entity.Blog;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import javax.print.Doc;
import java.nio.file.Paths;
import java.util.Dictionary;
import java.util.LinkedList;
import java.util.List;

/**
 * 使用lucene对博客增删改查
 */
public class BlogIndex {
    private Directory dir = null;
    private String lucenePath = "D://lucene";
    /**
     *
     * @return 对lucene的写入方法
     */
    private IndexWriter getWriter() throws Exception{
        dir = FSDirectory.open(Paths.get(lucenePath, new String[0]));

        SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer();
        IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
        IndexWriter writer = new IndexWriter(dir, iwc);
        return writer;
    }

    /**
     *
     * @param blog 增加索引
     * @throws Exception a
     */
    public void addIndex(Blog blog) throws Exception{
        IndexWriter writer = getWriter();
        Document doc = new Document();
        doc.add(new StringField("id", String.valueOf(blog.getId()), Field.Store.YES));
        doc.add(new TextField("title", blog.getTitle(), Field.Store.YES));
//        doc.add(new StringField(""));
        doc.add(new TextField("content", blog.getContent(), Field.Store.YES));
        doc.add(new TextField("keyWord", blog.getKeyWord(), Field.Store.YES));
        writer.addDocument(doc);
        writer.close();
    }

    /**
     * 更新索引
     * @param blog
     * @throws Exception
     */
    public void updateIndex(Blog blog) throws Exception{
        IndexWriter writer = getWriter();
        Document doc = new Document();
        doc.add(new StringField("id", String.valueOf(blog.getId()), Field.Store.YES));
        doc.add(new TextField("title", blog.getTitle(), Field.Store.YES));
//        doc.add(new StringField(""));
        doc.add(new TextField("content", blog.getContent(), Field.Store.YES));
        doc.add(new TextField("keyWord", blog.getKeyWord(), Field.Store.YES));
        writer.updateDocument(new Term("id", String.valueOf(blog.getId())), doc);
        writer.close();
    }

    public void deleteIndex(Integer id) throws Exception{
        IndexWriter writer = getWriter();
        writer.deleteDocuments(new Term[] {new Term("id", String.valueOf(id))});
        writer.forceMergeDeletes();
        writer.commit();
        writer.close();
    }


    public List<Blog> searchBlog(String q) throws Exception{
        List<Blog> blogList = new LinkedList<>();
        dir = FSDirectory.open(Paths.get(lucenePath, new String[0]));
        IndexReader reader = DirectoryReader.open(dir);
        IndexSearcher is = new IndexSearcher(reader);

        //存入查询条件
        BooleanQuery.Builder booleanQuery = new BooleanQuery.Builder();
        SmartChineseAnalyzer analyzer = new SmartChineseAnalyzer();
        QueryParser parser = new QueryParser("title", analyzer);
        Query query = parser.parse(q);
        QueryParser parser2 = new QueryParser("content", analyzer);
        Query query2 = parser.parse(q);
        QueryParser parser3 = new QueryParser("keyWord", analyzer);
        Query query3 = parser.parse(q);

        booleanQuery.add(query, BooleanClause.Occur.SHOULD);
        booleanQuery.add(query2, BooleanClause.Occur.SHOULD);
        booleanQuery.add(query3, BooleanClause.Occur.SHOULD);

        TopDocs hits = is.search(booleanQuery.build(), 100);

        //高亮搜索字？

        for(ScoreDoc scoreDoc:hits.scoreDocs){
            Document doc = is.doc(scoreDoc.doc);
            Blog blog = new Blog();

            blog.setId(Integer.parseInt(doc.get("id")));
            String title = doc.get("title");
            String content = doc.get("content");
            String keyWord = doc.get("keyWord");

            if(title!=null){
                blog.setTitle(title);
            }
            if(content!=null){
                if(content.length()<=200) {
                    blog.setContent(content);
                }
                else{
                    blog.setContent(content.substring(0,200));
                }
            }
            if(keyWord!=null){
                blog.setKeyWord(keyWord);
            }

            blogList.add(blog);
        }


        return blogList;
    }
}
