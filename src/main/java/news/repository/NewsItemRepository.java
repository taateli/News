
package news.repository;
import news.domain.NewsItem;
import org.springframework.data.jpa.repository.JpaRepository;


public interface NewsItemRepository extends JpaRepository<NewsItem, Long> {
    
}
