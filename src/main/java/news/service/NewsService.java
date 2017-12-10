
package news.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import news.domain.Writer;
import news.domain.Category;
import news.domain.NewsItem;
import news.repository.WriterRepository;
import news.repository.CategoryRepository;
import news.repository.NewsItemRepository;

@Service
public class NewsService {

    @Autowired
    private NewsItemRepository newsItemRepository;

    @Autowired
    private WriterRepository writerRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Writer> createAuthorList(String[] writers) {
        List<Writer> writersList = new ArrayList<>();
        for (int i = 0; i < writers.length; i++) {
            writersList.add(new Writer(writers[i]));
        }
        return writersList;
    }

    public List<Category> createCategoryList(String[] categories) {
        List<Category> categoryList = new ArrayList<>();
        for (int i = 0; i < categories.length; i++) {
            categoryList.add(new Category(categories[i]));
        }
        return categoryList;
    }

    public void assignNewsItemToAuthors(NewsItem newsItem, List<Writer> writers) {
        for (int i = 0; i < writers.size(); i++) {
            Writer writer = writerRepository.findByName(writers.get(i).getName());
            writer.addNewsItem(newsItem);
            writerRepository.save(writer);
        }
    }

    public void assignNewsItemToCategories(NewsItem newsItem, List<Category> categories) {
        for (int i = 0; i < categories.size(); i++) {
            Category category = categoryRepository.findByName(categories.get(i).getName());
            category.addNewsItem(newsItem);
            categoryRepository.save(category);
        }
    }
    
    public List<NewsItem> createOtherNewsList(NewsItem newsItem) {
        List<NewsItem> otherNews = new ArrayList<>();
        for (Category category : newsItem.getCategories()) {
            for (NewsItem otherNewsItem : category.getNews()) {
                if(otherNewsItem.getId()!=newsItem.getId()) {
                    otherNews.add(otherNewsItem);
                }
            }
        }    
        return otherNews;
    }
}