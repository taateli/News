package news.domain;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Writer extends AbstractPersistable<Long> {

    private String name;

    @ManyToMany
    private List<NewsItem> newsItems;

    public Writer(String writer) {
        this.name = writer;
    }

    public List<NewsItem> getNews() {
        if (this.newsItems == null) {
            this.newsItems = new ArrayList<NewsItem>();
        }
        return this.newsItems;
    }

    public void setNews(List<NewsItem> news) {
        this.newsItems = news;
    }

    public void addNewsItem(NewsItem newsItem) {
        for (NewsItem newsItem2 : getNews()) {
            if (newsItem2.getId() == newsItem.getId()) {
                return;
            }
        }
        getNews().add(newsItem);
    }
}
